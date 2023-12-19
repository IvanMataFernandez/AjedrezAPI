package juegoOnline.server.modelo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
		
		for (int jug = 1; jug != 3; jug ++) {
			
			System.out.println("Esperando a jugador "+jug);
			try {
				
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


				
			} catch (IOException e) {
				e.printStackTrace();
			} 
			
			
			

		}

		this.jugar();
	}
	

	
	
	
	private int jugar() {
		
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
			
			System.out.println("Turno de Jugador "+turno%2+1+ ". Estado de juego:");
			
			tab.imprimirTablero();

			
			// Obtener dichos movimientos posibles y mapearlos a un objeto para enviarlo por red
			
			ArrayList<MovimientosPosibleDePieza> movimientos = this.generarJugadasPosibles(j);

			
			try {
				
				// Enviar al jugador las jugadas posibles por red
				this.salidas[turno%2].writeObject(movimientos);
				
				System.out.println();
				System.out.println("Esperando respuesta del jugador...");
				
				// Recoger la jugada elegida
				 mov = (Movimiento) this.entradas[turno%2].readObject();
					
				 System.out.println("Procesando: " + mov.toString());


			} catch (Exception e) {
				e.printStackTrace();
			}
			
			// Ejecutar el movimiento y confirmarlo
			
			mov.ejecutarMovimiento();
			mov.confirmarMovimiento();
			
			// Dar turno al siguiente jugador
			
			l.cambiarJugador();
			j = l.obtenerJugadorActual();
			turno++;
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
