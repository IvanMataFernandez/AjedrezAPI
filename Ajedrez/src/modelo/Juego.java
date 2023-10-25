package modelo;
import java.util.ArrayList;

import controlador.Dibujador;



public class Juego {

	private static Juego j;
	
	private Pieza[][] tablero;
	
	public static final int VICTORIA_BLANCO = 1;
	public static final int VICTORIA_NEGRO = 2;
	public static final int EMPATE = 0;
	
	private boolean esperandoMovimiento;
	
	private int respuestaTrasFinDeJuego; // -1 -> En espera | 0 -> Salir | 1 -> Jugar de nuevo
	
	private Juego() {
		// Constructora privada MAE
		
	}
	
	
	public static Juego getJuego() {
		if (Juego.j == null) {
			Juego.j = new Juego();
		}
		return Juego.j;
	
	}
	
	
	public void setRespuestaTrasJuego(int valor) {
		
		// Pre:  0 -> Salir | 1 -> Jugar de nuevo
		
		this.respuestaTrasFinDeJuego = valor;
	}
	
	private void imprimirTablero() {
		// Función para debuggear, enseña el estado del tablero desde el punto
		// de vista del motor del juego. Las piezas usan su letra de denotación oficial en inglés
		
		for (int f = 0; f != 8; f++) {
			for (int c = 0; c != 8; c++) {
				if (this.tablero[f][c] == null) {
					System.out.print("- ");
					
				} else {
					System.out.print(this.tablero[f][c].toString()+ " ");
					
				}
				
			}
			System.out.println();
		}
	}
	
	private void checkPiezas() {
		// Metodo para debuggear
		
		for (int f = 0; f != 8; f++) {
			for (int c = 0; c != 8; c++) {
				if (this.tablero[f][c] == null) {
					// NOP
				} else {
					if (this.tablero[f][c].getPosY() == f && this.tablero[f][c].getPosX() == c) {
						// NOP
					} else {
						System.out.println();
						System.out.println("ERROR DETECTADO:");
						System.out.println("Pieza en matriz ("+f+","+c+") piensa que está en ("+this.tablero[f][c].getPosY()+","+this.tablero[f][c].getPosX()+")");
					}
				}
				
			}
		}
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
	
	public boolean seMovio(int f, int c) {
		// Pre: Hay pieza en (f, c) y es Rey / Torre
		// Post: Si se movió o no, en el caso de no ser Torre / Rey devuelve False.
		
		return this.tablero[f][c].seMovio();
	}
	
	public void procesarMovimientoEnPieza (int f1, int c1, int f2, int c2) {
		// Pre: Posiciones validas para el movimiento (f1, c1)  --> (f2, c2)
		// Post: Se han actualizado los flags relevantes de la pieza que se ha movido
		// Nota: Para este momento la pieza ya está movida a (f2,c2), pero no confirmado
		//       el movimiento, aquí es cuando lo confirmamos
		
		this.tablero[f2][c2].procesarMovimiento(f1, c1);
	}

	
	public Movimiento moverPieza(int f1, int c1, int f2, int c2) {
		// Post: Se movió la pieza en (f1, c1) a (f2, c2), se devuelve el objeto movimiento
		
		Tupla t = this.tablero[f1][c1].encontrarTuplaConMovimiento(f2, c2);
		Movimiento mov = new Movimiento(f1, c1, t);
		mov.ejecutarMovimiento();
		mov.confirmarMovimiento();
		return mov;
		
		
	}

	// Usado para borrar peones en promociones
	public void eliminarPieza(int f, int c) {
		// Pre: Hay pieza en (f, c)
		this.tablero[f][c].eliminarseDeListaDeJugador();
		this.tablero[f][c] = null;
		
		
	}
	
    public void añadirPieza(int f, int c, boolean pBlanco, int pTipo) {
    	
    	
    	switch(pTipo) {
    	case 0:
        	this.tablero[f][c] = new Peon(f,c,pBlanco);

    		break;
    	case 1:
        	this.tablero[f][c] = new Caballo(f,c,pBlanco);
    		
    		break;
    	case 2:
        	this.tablero[f][c] = new Alfil(f,c,pBlanco);

    		break;
    	case 3:
        	this.tablero[f][c] = new Torre(f,c,pBlanco);

    		
    		break;
    	case 4:
        	this.tablero[f][c] = new Reina(f,c,pBlanco,4);

    		break;
    	case 5: 
        	this.tablero[f][c] = new Rey(f,c,pBlanco);


    	}
    	
    	
    }
    
	

    
	private void inicializarTablero() {
		// Post: Generar tablero con la POV del jugador blanco, (0,0) es arriba izquierda (7,0) abajo izquierda
		//       Las piezas van en su sitio, si no hay pieza en casilla entonces null
		
		this.tablero = new Pieza[8][8];
		
		
		// Posicion inicial Piezas Blancas
		
		// Figuras
		this.añadirPieza(0, 0, false, 3);
		this.añadirPieza(0, 1, false, 1);
		this.añadirPieza(0, 2, false, 2);
		this.añadirPieza(0, 3, false, 4);
		this.añadirPieza(0, 4, false, 5);
		this.añadirPieza(0, 5, false, 2);
		this.añadirPieza(0, 6, false, 1);
		this.añadirPieza(0, 7, false, 3);

		
	    // Peones
	    for (int c = 0; c < 8; c++) {
	    	this.añadirPieza(1, c, false, 0);
	    }
	    // Posicion inicial Piezas Negras
	
  		// Figuras
	    
		this.añadirPieza(7, 0, true, 3);
		this.añadirPieza(7, 1, true, 1);
		this.añadirPieza(7, 2, true, 2);
		this.añadirPieza(7, 3, true, 4);
		this.añadirPieza(7, 4, true, 5);
		this.añadirPieza(7, 5, true, 2);
		this.añadirPieza(7, 6, true, 1);
		this.añadirPieza(7, 7, true, 3);
	    
  
  	  
  	    // Peones
  	    for (int c = 0; c < 8; c++) {
  	        this.tablero[6][c] = new Peon(6,c,true);
  	    }
}
	
	
	public void iniciarPrograma() {
		
		Dibujador dib = Dibujador.getDibujador();
		ListaJugadores l = ListaJugadores.getListaJugadores();

		
		boolean jugar = true;
		
		while (jugar) {
			
			// Inicializar la interfaz del tablero
			
			dib.inicializarPantalla();
			
			// Jugar
			
			int resultado = this.jugar();
			
			
			// Quitar la interfaz del tablero
			
			dib.matarPantalla();
			
			// Sumar victoria a quien ganó (o nada si fue empate) y reiniciar las piezas de cada jugador
			
			l.procesarResultado(resultado);
			
			// Crear la interfaz del menú de resultados de partida y esperar a input de botón
			
			
			
			this.respuestaTrasFinDeJuego = -1;
			dib.inicializarResultado(resultado, l.obtenerVictoriasDe(true), l.obtenerVictoriasDe(false));
			
			
			while (this.respuestaTrasFinDeJuego == -1) {
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {}
			}
			
			jugar = this.respuestaTrasFinDeJuego == 1;
			
			
		}
	}
	
	private int jugar() {
		
		// Post: 0 -> Empate | 1 -> Blanco | 2 -> Negro
		
		
		ListaJugadores l = ListaJugadores.getListaJugadores();
		l.reiniciarPunteroDeJugadores();
		Jugador j = l.obtenerJugadorActual();
		
		this.inicializarTablero();
		

		while (j.recalcularMovimientosLegales(this.tablero)) {
			this.imprimirTablero();
			this.checkPiezas();
			this.esperandoMovimiento = true;
			
			// Esperar a que se mueva una ficha
			
			while (this.esperandoMovimiento) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {}
			}
			
			// WAIT FOR MOVE
			
			
			l.cambiarJugador();
			j = l.obtenerJugadorActual();
	
		}
		
		
		System.out.println("GG Fin de partida");
		
		if (j.reyEnJaque()) {
			
			
			if (j.esBlanco()) {
				return Juego.VICTORIA_NEGRO ;
			
			} else {
				return Juego.VICTORIA_BLANCO;
			}
			
		} else {
			
			return Juego.EMPATE;
			
		}
		
		
		
		
		
		
		
		
		
		
		
	}


	public void setSeMovio() {this.esperandoMovimiento = false;}
	
	public  Pieza[][] getTablero() {
		return tablero;
	}
	
	
	
	
}

