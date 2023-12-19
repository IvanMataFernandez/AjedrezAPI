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
import juegoOnline.Controlador.MovimientosPosibleDePieza;


public class Servidor {
	
	private static final long serialVersionUID = 1L;
	private ObjectOutputStream[] salidas;
	private ObjectInputStream[] entradas;
	private ServerSocket servidor;
	private Socket[] conexiones;
	
	
	public Servidor() {}
	
	public void ejecutarServidor() {
		
		
		// Inicializar arrays + socket del servidor (alojado en port 12345)
		
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



		
			

			

		}
			this.jugar();
		
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
			
			tab.imprimirTablero();

			
			// Obtener dichos movimientos posibles y mapearlos a un objeto para enviarlo por red
			
			ArrayList<MovimientosPosibleDePieza> movimientos = this.generarJugadasPosibles(j);
	
			// Enviar al jugador las jugadas posibles por red
			
			this.salidas[turno%2].writeObject(movimientos);
				
			System.out.println();
		    System.out.println("Esperando respuesta del jugador...");
		 		
			// Recoger la jugada elegida
		    
			mov = (Movimiento) this.entradas[turno%2].readObject();
					
		    System.out.println("Procesando: " + mov.toString());

		    
			
			// Ejecutar el movimiento y confirmarlo
			
			mov.ejecutarMovimiento();
			mov.confirmarMovimiento();
			
			// Informar a los clientes de los cambios que deben hacer en UI
			
			ArrayList<ComandoAInterfazBorrarPieza> comandos = mov.informarPantalla();
			
			// Comprobar que las instrucciones pueden ser ejecutadas ya sin mas feedback de algun cliente
			
			int i = 0;
			while (i < comandos.size()) {
				
				ComandoAInterfazBorrarPieza com = comandos.get(i);
				
				if (com instanceof ComandoAInterfazAscension) {
					
					// Si hay un comando de ascensión de peón, preguntar al cliente a que quiere ascender para
					// representar los cambios después
					
					ComandoAInterfazAscension com2 = (ComandoAInterfazAscension) com;
					this.salidas[turno%2].writeObject(com2);
					int tipo = (int) this.entradas[turno%2].readObject();

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
		
		// Fin de la partida, informar a los players que se acabó el juego
		Thread.sleep(100);
		for (int i = 0; i != 2; i++) {

			this.salidas[i].writeObject(0);
			this.salidas[i].flush();

		}
		

		
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
        		
        		// Generar la posición a donde movernos
        		
        		mov.addDestino(t.getF(), t.getC(), t.come());
        	}
        	movimientos.add(mov);
        }
        
        return movimientos;
	}

}
