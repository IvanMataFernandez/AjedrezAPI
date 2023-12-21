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
	
	private ArrayList<MovimientosPosibleDePieza> movimientos;

	

	
	private Cliente() {this.miTurno = false;}
	
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
	
	private void jugar() throws ClassNotFoundException, IOException, InterruptedException {
		
		ControladorDePantalla c = ControladorDePantalla.getControladorDePantalla();

		
		// Gameloop del cliente
		
		boolean seguir = true;
		
		while (seguir) {
			
			// Esperar a que el servidor nos de el turno
			
			Object mensaje = this.entrada.readObject();

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
		
		// Cerrar la conexión
		
		this.cliente.close();
		
		// Cerrar la interfaz
		
		c.cerrarPantalla();
		
	}
	
	public void ejecutarCliente() {
		System.out.println("Intentando Realizar Conexion Con Servidor");
		
		// Crear Socket para realizar la conexión con el servidor
		
		try {
			// Conectar con servidor...
			
			Scanner s = new Scanner(System.in);
			System.out.println("Escribe la IP del server");
			
			this.cliente = new Socket(s.next(), 12345);
			
			// Recoger streams de entrada y salida
			
			this.salida = new ObjectOutputStream(cliente.getOutputStream());
			this.entrada = new ObjectInputStream(cliente.getInputStream());
			
			// limpiar stream de salida para inicializarlo
			
			this.salida.flush();
			
			// Recibir del servidor el número de jugador asignado (el primero en conectar es blanco, el segundo es negro)
			
			this.esBlanco = this.entrada.readInt() == 1;


			System.out.println("SOY BLANCO -> "+this.esBlanco);
			
			
			// Inicializar la UI para empezar a jugar

			ControladorDePantalla.getControladorDePantalla().inicilizarPantalla(this.esBlanco);

			
			this.jugar();


		} catch (Exception e) {
		//	e.printStackTrace();
			System.out.println("No se pudo conectar");
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


