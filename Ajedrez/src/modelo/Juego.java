package modelo;
import java.util.ArrayList;


public class Juego {

	private static Juego j;
	
	private Pieza[][] tablero;
	
	public static final int VICTORIA_BLANCO = 1;
	public static final int VICTORIA_NEGRO = 2;
	public static final int EMPATE = 0;
	
	private Juego() {
		// Constructora privada MAE
		
	}
	
	
	public static Juego getJuego() {
		if (Juego.j == null) {
			Juego.j = new Juego();
		}
		return Juego.j;
	
	}
	
	
	public ArrayList<Tupla> obtenerMovimientosLegalesDe(int f, int c) {
		// Pre: f y c en rango [0,7]
		// Post: La AL con los movimientos ya calculados al principio del turno de la ficha del jugador
		return this.tablero[f][c].obtenerMovimientosLegales();
	}
	
	public boolean hayPieza(int f, int c) {
		// Pre: f y c en rango [0,7]
		// Post: Hay pieza en la casilla (f, c)
		
		return this.tablero[f][c] != null;
	}
	
	
	
	public int estadoDeCasilla(int f, int c) {
		// Pre: f y c en rango [0,7]
		// Post: -1 (no hay pieza), 0 (peon), 1 (caballo), 2 (alfil), 3 (torre), 4 (reina), 5 (rey)
		//       no da información sobre el color de la pieza
		
		if (this.tablero[f][c] == null) {
			return -1;
		} else {
			return this.tablero[f][c].tipo();
		}
		
	}
	
	public boolean esBlanco(int f, int c) {
		// Pre: f y c en rango [0,7] y hay pieza ahi
		// Post: Si es pieza blanca o no
		
		return this.tablero[f][c].pBando();
		
	}
	
	public boolean vulnerableAEnPassant(int f, int c) {
		// Pre: f y c en rango [0,7] y hay pieza ahi
		// Post: Si la ficha es peon Y hizo un unico movimiento el cual era ir dos casillas adelante

		return this.tablero[f][c].vulnerableAEnPassant();
		
	}
	
	
	private boolean condicionDeVictoria() {
	    // condiciones de victoria, como capturar el rey del oponente, etc.
	    // Retorna true si se cumple una condición de victoria, de lo contrario, retorna false.
	    // Ejemplo:

	    return false;
	}
	
	private boolean condicionDeEmpate() {
	    // condiciones de empate, como repetición de movimientos, falta de material, etc.
	    // Retorna true si se cumple una condición de empate, de lo contrario, retorna false.
	    // Ejemplo:
	    // return seRepiteElMismoTablero();
	    return false;
	}
	
	private boolean reyEnJaque(Pieza rey) {
	    // Obtener la posición del rey
	    int posXRey = rey.getPosX();
	    int posYRey = rey.getPosY();

	    // Obtener el color del rey (blanco o negro)
	    boolean esBlanco = rey.pBando();

	    // Tablero del juego
	    Pieza[][] tablero = Juego.getJuego().getTablero();

	    // Direcciones en las que un atacante puede poner al rey en jaque
	    int[][] direccionesAtaque = {
	        {-1, -1}, {-1, 0}, {-1, 1},
	        {0, -1},           {0, 1},
	        {1, -1}, {1, 0}, {1, 1}
	    };

	    // Verificar si hay alguna pieza del oponente que amenaza al rey
	    for (int[] direccion : direccionesAtaque) {
	        int deltaX = direccion[1];
	        int deltaY = direccion[0];

	        // Calcular la nueva posición a verificar
	        int nuevaPosX = posXRey + deltaX;
	        int nuevaPosY = posYRey + deltaY;

	        // Verificar si la nueva posición está dentro del tablero
	        if (nuevaPosX >= 0 && nuevaPosX < 8 && nuevaPosY >= 0 && nuevaPosY < 8) {
	            Pieza posibleAtacante = tablero[nuevaPosY][nuevaPosX];

	            // Verificar si la casilla contiene una pieza del oponente
	            if (posibleAtacante != null && posibleAtacante.pBando() != esBlanco) {
	                // Verificar si la pieza puede moverse a la posición del rey
	                ArrayList<Tupla> movimientosLegales = posibleAtacante.obtenerMovimientosLegales();

	                for (Tupla movimiento : movimientosLegales) {
	                    if (movimiento.getF() == posXRey && movimiento.getC() == posYRey) {
	                        // El rey está en jaque
	                        return true;
	                    }
	                }
	            }
	        }
	    }

	    // Si no se encontraron atacantes que amenacen al rey, no está en jaque
	    return false;
	}


	
	private void inicializarTablero() {
		// Post: Generar tablero con la POV del jugador blanco, (0,0) es arriba izquierda (7,0) abajo izquierda
		//       Las piezas van en su sitio, si no hay pieza en casilla entonces null
		
		this.tablero = new Pieza[8][8];
		
		
		// Posicion inicial Piezas Blancas
		
		// Figuras
		
	 	this.tablero[0][0] = new Torre(0,0,false);
	    this.tablero[0][1] = new Caballo(0,1,false);
	    this.tablero[0][2] = new Alfil(0,2,false);
	    this.tablero[0][3] = new Reina(0,3,false,4); // por cuestiones de herencia el id de la reina debe ir ahi, es 4
	    this.tablero[0][4] = new Rey(0,4,false);
	    this.tablero[0][5] = new Alfil(0,5,false);
	    this.tablero[0][6] = new Caballo(0,6,false);
	    this.tablero[0][7] = new Torre(0,7,false);
	  
	    // Peones
	    for (int c = 0; c < 8; c++) {
	        this.tablero[1][c] = new Peon(1,c,false);
	    }
	    // Posicion inicial Piezas Negras
	
  		// Figuras
  	 	this.tablero[7][0] = new Torre(7,0,true);
  	    this.tablero[7][1] = new Caballo(7,1,true);
  	    this.tablero[7][2] = new Alfil(7,2,true);
  	    this.tablero[7][3] = new Reina(7,3,true,4); // por cuestiones de herencia el id de la reina debe ir ahi, es 4
  	    this.tablero[7][4] = new Rey(7,4,true);
  	    this.tablero[7][5] = new Alfil(7,5,true);
  	    this.tablero[7][6] = new Caballo(7,6,true);
  	    this.tablero[7][7] = new Torre(7,7,true);
  	  
  	    // Peones
  	    for (int c = 0; c < 8; c++) {
  	        this.tablero[6][c] = new Peon(6,c,true);
  	    }
}
	
	public int jugar() {
		
		// Post: 0 -> Empate | 1 -> Blanco | 2 -> Negro
		
		// TODO: El main loop del juego
		
		ListaJugadores l = ListaJugadores.getListaJugadores();
		Jugador j = l.obtenerJugadorActual();
		
		this.inicializarTablero();

		while (j.recalcularMovimientosLegales()) {
		l.cambiarJugador();
		j = l.obtenerJugadorActual();
	
		}
		j.recalcularMovimientosLegales();
		
		
		
		return 0; // Provisional
		
		
	}


	public  Pieza[][] getTablero() {
		return tablero;
	}
	
	
	
	
}

