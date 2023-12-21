package juegoOnline.cliente.modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import juegoBase.controlador.ComandoAInterfazAscension;
import juegoBase.controlador.ComandoAInterfazBorrarPieza;
import juegoBase.modelo.Movimiento;
import juegoOnline.cliente.controlador.ControladorDePantalla;
import juegoOnline.server.controlador.MovimientosPosibleDePieza;

public class Cliente {
	
	private static Cliente c;

	private ObjectOutputStream salida;
	private ObjectInputStream entrada;
	private Socket cliente;
	private boolean esBlanco;
	private boolean miTurno;
	private String IP;
	
	private ArrayList<MovimientosPosibleDePieza> movimientos;

	

	
	private Cliente() {this.miTurno = false; this.IP = "";}
	
	public static Cliente getCliente() {
		if (Cliente.c == null) {
			Cliente.c = new Cliente();
		}
		
		return Cliente.c;
	}
		

	public MovimientosPosibleDePieza inicioLegal (int f, int c) {
		
		// Ver si la casilla marcada tiene una pieza que podemos mover
		
		for (MovimientosPosibleDePieza mov: this.movimientos) {
			if (mov.empiezaAqui(f, c)) {
				return mov;
			}
		}
		return null;
	}
	
	public boolean miTurno() {return this.miTurno;}
	

	
	
	public void informarAServerDeMove(Movimiento mov) throws IOException {
		
		// Mandar por red el movimiento elegido al servidor y acabar el turno
		
		this.salida.writeObject(mov);
		this.salida.flush();
		this.miTurno = false;
		
	}
	
	private int jugar() throws ClassNotFoundException, IOException, InterruptedException {
		
		Object mensaje = null; 

		ControladorDePantalla c = ControladorDePantalla.getControladorDePantalla();

		

		

		
		// Gameloop del cliente
		
		boolean seguir = true;
		
		while (seguir) {
			
			// Esperar a que el servidor nos de el turno
			
			mensaje = this.entrada.readObject();

			// Descifrar el mensaje del servidor, los casos posibles se analizan a continuación:
			
			if (mensaje instanceof ArrayList) {
				
				// El mensaje dice que la partida debe continuar
									
				if (((ArrayList)mensaje).get(0) instanceof MovimientosPosibleDePieza) {
					
					this.miTurno = true;
					
					// El mensaje nos informa de los movimientos legales posibles a ejecutar, toca mover!
					
					this.movimientos = (ArrayList<MovimientosPosibleDePieza>) mensaje;
					
					// Esperar a que por interfaz se mande un movimiento
					
					while (this.miTurno) {
						Thread.sleep(50);
					}
					
					
					
					
				} else {
					
					// El mensaje nos informa de los cambios se que deben hacer en UI
					
					
					ArrayList<ComandoAInterfazBorrarPieza> comandos = (ArrayList<ComandoAInterfazBorrarPieza>) mensaje;

					c.procesarInstruccionesAPantalla(comandos);
					
					
				}
				
				
			} else if (mensaje instanceof ComandoAInterfazAscension) {
				
				// El servidor nos ha interrumpido para que elijamos a que ascender el peón, indicar la ascensión
				
				this.salida.writeObject(c.procesarVentanaPromociones());

				
			} else {
				
				// El mensaje nos informa de fin del juego
				
				seguir = false;
			}
		
			
		}
		

		
		return (int) mensaje;
	}
	
	
	public void setIP(String ip) {
		this.IP = ip;
	}
	
	public void ejecutarCliente() {
		
		this.IP = "";

		// Mostrar UI de selección de conexión IP
		
		ControladorDePantalla.getControladorDePantalla().abrirSelectorDeServidor();

		
		try {
			// Esperar a que el usuario interactúe con los botones de la interfaz
			
			while (this.IP.contentEquals("")) {
				Thread.sleep(500);
				
			}
			
			
			
			this.cliente = new Socket(this.IP, 12345);
			
			// Recoger streams de entrada y salida
			
			this.salida = new ObjectOutputStream(cliente.getOutputStream());
			this.entrada = new ObjectInputStream(cliente.getInputStream());
			
			// limpiar stream de salida para inicializarlo
			
			this.salida.flush();
			
			// Recibir del servidor el número de jugador asignado (el primero en conectar es blanco, el segundo es negro)
			
			int numPlayersReady = this.entrada.readInt();
			
			this.esBlanco = numPlayersReady == 1;
			
			// Cerrar UI de selección de conexión IP

			ControladorDePantalla.getControladorDePantalla().cerrarSelectorDeServidor();

			// Mostrar UI del lobby
			
			ControladorDePantalla.getControladorDePantalla().abrirLobby(this.IP,numPlayersReady);
			
		
			
			
			
			// Esperar a confirmación de iniciar partida / salir
			
			String respServer = "";
			
			while (!respServer.contentEquals("Ready")) {
				respServer = (String) this.entrada.readObject();
				
				if (respServer.contentEquals("Player 2")) {
					
					// Marcar ahora el player 2 como ready

					ControladorDePantalla.getControladorDePantalla().player2ReadyEnLobby();
				}

			}
			
			// Mostrar Countdown
			
			while (!respServer.contentEquals("0")) {
				respServer = (String) this.entrada.readObject();
				ControladorDePantalla.getControladorDePantalla().ponerCountDownEnLobbyEn(respServer);

			}
			
			// Cerrar UI de Lobby
			
			ControladorDePantalla.getControladorDePantalla().cerrarLobby();

			
			// Crear UI del tablero

			ControladorDePantalla.getControladorDePantalla().inicilizarPantalla(this.esBlanco);
			
			// Empezar a jugar, obtener el resultado de la partida
			
			int resultado = this.jugar();
			
			// Cerrar la interfaz del tablero
			
			ControladorDePantalla.getControladorDePantalla().cerrarPantalla();
		
			// Cerrar la conexión al servidor
			
			this.cliente.close();
			
			// Mostrar la UI de resultados
			
			ControladorDePantalla.getControladorDePantalla().abrirResultados(resultado);
			
			this.IP = "mostrando";
			
			// Esperar a que se pulse el botón de confirmar en la pantalla de resultados
			
			while (this.IP.contentEquals("mostrando")) {
				Thread.sleep(500);
			}
			
			// Cerrar la UI de resultados
			
			ControladorDePantalla.getControladorDePantalla().cerrarResultados();
			
			
			
			// Fin de ejecución de wireless, se vuelve al mainloop del menu principal


		} catch (NullPointerException e) {
			
			// Se dio al botón de volver, cerrar la UI
			
			ControladorDePantalla.getControladorDePantalla().cerrarSelectorDeServidor();

		
     	} catch (Exception e) {
     		
     		// Falló la conexión al servidor
     		
			ControladorDePantalla.getControladorDePantalla().cerrarSelectorDeServidor();
			ControladorDePantalla.getControladorDePantalla().abrirErrorDeConexion();
			
			// Esperar a que se de al botón para volver
			
			while (!this.IP.contentEquals("0.0.0.0")) {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e1) {}

			}
			ControladorDePantalla.getControladorDePantalla().cerrarErrorDeConexion();


     		
		} 
		


	}
	
	
	/*
	public void procesarTurno() throws IOException, ClassNotFoundException {
		ArrayList<MovimientosPosibleDePieza> movimientos = (ArrayList<MovimientosPosibleDePieza>) this.entrada.readObject();
		
		// PROCESAR CASILLA A ELEGIR
		MovimientosPosibleDePieza mov = null;
		this.salida.writeObject(mov);
		this.salida.flush();
	} */
	
}


