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
	
	
	private void inicializarTablero() {
		// Post: Generar tablero con la POV del jugador blanco, (0,0) es arriba izquierda (7,0) abajo izquierda
		//       Las piezas van en su sitio, si no hay pieza en casilla entonces null
		
		this.tablero = new Pieza[8][8];
		
		// TODO
		// Poner las piezas en su sitio
		
		//Posicion inicial Piezas Blancas
		
		//Figuras
	 	tablero[0][0] = new Torre(0,0,true);
	    tablero[0][1] = new Caballo(0,1,true);
	    tablero[0][2] = new Alfil(0,2,true);
	    tablero[0][3] = new Reina(0,3,true);
	    tablero[0][4] = new Rey(0,4,true);
	    tablero[0][5] = new Alfil(0,5,true);
	    tablero[0][6] = new Caballo(0,6,true);
	    tablero[0][7] = new Torre(0,7,true);
	    //Peones
	    for (int c = 0; c < 8; c++) {
	        tablero[1][c] = new Peon(1,c,true);
	    }
	    //Posicion inicial Piezas Negras
	
  		//Figuras
  	 	tablero[7][0] = new Torre(7,0,false);
  	    tablero[7][1] = new Caballo(7,1,false);
  	    tablero[7][2] = new Alfil(7,2,false);
  	    tablero[7][3] = new Reina(7,3,false);
  	    tablero[7][4] = new Rey(7,4,false);
  	    tablero[7][5] = new Alfil(7,5,false);
  	    tablero[7][6] = new Caballo(7,6,false);
  	    tablero[7][7] = new Torre(7,7,false);
  	    //Peones
  	    for (int c = 0; c < 8; c++) {
  	        tablero[6][c] = new Peon(6,c,false);
  	    }
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
	
	
}

