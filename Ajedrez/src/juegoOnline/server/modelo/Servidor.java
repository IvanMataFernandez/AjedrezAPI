package juegoOnline.server.modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import juegoBase.controlador.ComandoAInterfazAscension;
import juegoBase.controlador.ComandoAInterfazAñadirPieza;
import juegoBase.controlador.ComandoAInterfazBorrarPieza;
import juegoBase.modelo.Jugador;
import juegoBase.modelo.ListaJugadores;
import juegoBase.modelo.MainLoop;
import juegoBase.modelo.Matriz;
import juegoBase.modelo.Movimiento;
import juegoBase.modelo.Pieza;
import juegoBase.modelo.Tupla;
import juegoOnline.server.controlador.MovimientosPosibleDePieza;


public class Servidor {
	
	private ObjectOutputStream[] salidas;
	private ObjectInputStream[] entradas;
	private ServerSocket servidor;
	private Socket[] conexiones;
	
	
	public Servidor() {}
	
	public void ejecutarServidor() {
		
		
		// Inicializar arrays + socket del servidor 
		// El programa se aloja en el puerto 12345 del servidor. La IP de la máquina obviamente no se define aquí,
		// java no maneja la asignación de IPs.
		
		this.salidas = new ObjectOutputStream[2];
		this.entradas = new ObjectInputStream[2];
		this.conexiones = new Socket[2];
		
		try {
			this.servidor = new ServerSocket(12345, 100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		// Establecer las conexiones TCP/IP con ambos jugadores
		// Esperar uno a uno a los jugadores y crear las conexiones (objetos input y output para leer / escribir mensajes al otro)
		try {

			for (int jug = 1; jug != 3; jug ++) {
			
				System.out.println("Esperando a jugador "+jug);
				
				// Aceptar conexion
				
				this.conexiones[jug-1] = this.servidor.accept();
				System.out.println("Conexion de user "+jug + " recibida de:  " + this.conexiones[jug-1].getInetAddress().getHostName());
                
				// Crear streams
				
				this.entradas[jug-1] = new ObjectInputStream(this.conexiones[jug-1].getInputStream());
                this.salidas[jug-1] = new ObjectOutputStream(this.conexiones[jug-1].getOutputStream());
                
                // Vaciar stream de output para inicializarlo
                
                this.salidas[jug-1].flush();
                
                // Decir al jugador si es el j1 o j2
                
                this.salidas[jug-1].writeInt(jug);
                this.salidas[jug-1].flush();
                
                Thread.sleep(100);
                
                // Informar a todos los jugadores restantes actuales de que alguien se unió a la sala

                for (int i = 1; i != jug; i++) {
                    this.salidas[i-1].writeObject("Player "+ Integer.toString(jug));
                    this.salidas[i-1].flush();	
                }


		
			

			

		}
			
			// Conexiones preparadas, hacer countdown
			
			for (int jug = 0; jug != 2; jug ++) {
                this.salidas[jug].writeObject("Ready");
                this.salidas[jug].flush();
			}
			
			
			int espera = 7;
			
			while (espera != -1) {
				for (int jug = 0; jug != 2; jug ++) {
	                this.salidas[jug].writeObject(Integer.toString(espera));
	                this.salidas[jug].flush();
				}
				
				Thread.sleep(1000);
				
				espera--;
			}
			
			
			// Una vez las conexiones establecidas, el servidor puede empezar a ejecutar la partida.
			// El servidor funciona de arbitro entre ambas parties, siendo este el único que tiene acceso
			// al estado interno de la partida. El servidor se encarga de informar a los jugadores de lo que deben
			// hacer y como actualizar los gráficos. Los clientes no pueden hablar entre ellos, solo con el server.
			
			int resultado = this.jugar();
		
			
			// Fin de la partida, informar a los players de quien ganó y cerrar la conexión
			
			Thread.sleep(100);
			
			for (int i = 0; i != 2; i++) {

				this.salidas[i].writeObject(resultado);
				this.salidas[i].flush();

			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} 

	}
	

	
	
	
	private int jugar() throws IOException, ClassNotFoundException, InterruptedException {
		
		// Post: 0 -> Empate | 1 -> Blanco | 2 -> Negro
		
		// Inicializar lista de jugadores y el tablero de partida
		
		int turno = 0;
		Movimiento mov = null;
		
		ListaJugadores l = ListaJugadores.getListaJugadores();
		l.reiniciarPunteroDeJugadores();
		Jugador j = l.obtenerJugadorActual();
		Matriz tab = Matriz.getMatriz();

		tab.inicializarTablero();
		

		// Main loop del programa ejecutado en servidor:
		
		// Comprobar que el jugador actual tiene movimientos legales
		
		while (j.recalcularMovimientosLegales(tab.getTablero())) {
			
			// Mostrar estado de partida por terminal
			
			System.out.println("Turno de Jugador "+turno%2+1+ ". Estado de juego:");
			
			// Imprimir el estado del tablero por caracteres ASCII (server no usa UI por razones obvias)
			
			tab.imprimirTablero();

			
			// Obtener dichos movimientos posibles y mapearlos a un objeto para enviarlo por red
			
			ArrayList<MovimientosPosibleDePieza> movimientos = this.generarJugadasPosibles(j);
	
			// Enviar al jugador las jugadas posibles por red
			
			this.salidas[turno%2].writeObject(movimientos);
				
			System.out.println();
		    System.out.println("Esperando respuesta del jugador...");
		 		
			// Recoger el movimiento elegido, la ejecución se queda trabada aquí hasta que el player respectivo
		    // manda la jugada que quiere realizar por red
		    
			mov = (Movimiento) this.entradas[turno%2].readObject();
					
		    System.out.println("Procesando: " + mov.toString());

		    
			
			// Ejecutar el movimiento y confirmarlo
			
			mov.ejecutarMovimiento();
			mov.confirmarMovimiento();
			
			// Recoger las instrucciones que los clientes deberían ejecutar para updatear su UI
			
			ArrayList<ComandoAInterfazBorrarPieza> comandos = mov.informarPantalla();
			
			// Comprobar que las instrucciones pueden ser ejecutadas ya sin mas feedback de algun cliente
			// (o sea, que no hay promociones de peon por medio)
			
			int i = 0;
			while (i < comandos.size()) {
				
				ComandoAInterfazBorrarPieza com = comandos.get(i);
				
				// Comprobar comando a comando si alguno es de ascension de peon, si no, lo ignoramos
				
				if (com instanceof ComandoAInterfazAscension) {
					
					// Si hay un comando de ascensión de peón, preguntar al cliente a que quiere ascender para
					// representar los cambios después
					
					ComandoAInterfazAscension com2 = (ComandoAInterfazAscension) com;
					this.salidas[turno%2].writeObject(com2); // informar al jugador de que debe elegir a que ascender por red
					int tipo = (int) this.entradas[turno%2].readObject(); // recoger por red la respuesta del jugador

					// Añadir a la lista de instrucciones de updates por UI el cambiar el peon por la pieza ascendida
					
					ComandoAInterfazAñadirPieza comando = new ComandoAInterfazAñadirPieza(com2.getF(),com2.getC(), com2.esBlanco());
					comando.setTipo(tipo);
					comandos.set(i, comando);
					
					// Cambiar internamente la ficha en el servidor
					
					tab.eliminarPieza(comando.getF(), comando.getC());
					tab.añadirPieza(comando.getF(), comando.getC(), comando.esBlanco(), comando.getTipo());
					
				}
				i++;
			}
			
			// Mandar a ambos clientes la información a actualizar en UI
			
			for (int k = 0; k != 2; k++) {
	
				this.salidas[k].writeObject(comandos);
				this.salidas[k].flush();

			}
			
			
			// Dar turno al siguiente jugador
			
			l.cambiarJugador();
			j = l.obtenerJugadorActual();
			turno++;
		}
		
		System.out.println("Fin del juego");
		
		

		// Mirar quien gano

		
		if (j.reyEnJaque()) {
			
			
			if (j.esBlanco()) {
				return MainLoop.VICTORIA_NEGRO ;
			
			} else {
				return MainLoop.VICTORIA_BLANCO;
			}
			
		} else {
			
			return MainLoop.EMPATE;
			
		}
		
		
	}	
	
	
	private ArrayList<MovimientosPosibleDePieza> generarJugadasPosibles(Jugador j) {
		
		// Método para mapear las posiciones de las casillas que representan los movimientos posibles del jugador,
		// de manera que se puedan mandar después por la red
		
		HashMap<Pieza, ArrayList<Tupla>> h = j.getPosiblesMovientos();
		ArrayList<MovimientosPosibleDePieza> movimientos = new ArrayList<MovimientosPosibleDePieza>();

        for (Map.Entry<Pieza, ArrayList<Tupla>> set : h.entrySet()) {	
        	
        	// Obtener los valores de la pieza y sus destinos posibles
        	
        	Pieza p = set.getKey();
        	ArrayList<Tupla> posiciones = set.getValue();
        	
        	// Generar la posición base desde donde movernos
        	
        	MovimientosPosibleDePieza mov = new MovimientosPosibleDePieza(p.getPosY(), p.getPosX());
        	
        	for (Tupla t: posiciones) {
        		
        		// Generar las posiciones a donde movernos, y meterlos en el objeto de la posición de inicio base
        		
        		mov.addDestino(t.getF(), t.getC(), t.come());
        	}
        	movimientos.add(mov);
        }
        
        return movimientos;
	}

}
