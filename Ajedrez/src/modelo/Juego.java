package modelo;

public class Juego {

	private static Juego j;
	
	private Pieza[][] tablero;
	
	private Juego() {
		
		
	}
	
	
	public static Juego getJuego() {
		if (Juego.j == null) {
			Juego.j = new Juego();
		}
		return Juego.j;
	
	}
	
	public boolean hayPieza(int f, int c) {
		return this.tablero[f][c] != null;
	}
	public boolean esBlanco(int f, int c) {
		// Pre: Espacio no vacio
		return this.tablero[f][c].pBando();
		
	}
	
	public boolean vulnerableAEnPassant(int f, int c) {
		// Pre: Espacio no vacio
		return this.tablero[f][c].vulnerableAEnPassant();
		
	}
	
	
	private void inicializarTablero() {
		// Post: Generar tablero con la POV del jugador blanco, (0,0) es arriba izquierda (7,0) abajo izquierda
		//       Las piezas van en su sitio, si no hay pieza en casilla entonces null
		
		this.tablero = new Pieza[8][8];
		
		// TODO
		// Poner las piezas en su sitio
		
	}
	
	public int jugar() {
		
			
		// Post: 0 -> Empate | 1 -> Blanco | 2 -> Negro

		
		this.inicializarTablero();

		
		// TODO:
		
		// Establecer turno del jugador
		
		// Calcular movimientos legales
		
		// Esperar a los clicks para que se de la orden de mover
		
		// Cambiar el turno
		
		
		return 0; // Provisional
		
		
	}


	public  Pieza[][] getTablero() {
		return tablero;
	}
	
	
	
	
}

