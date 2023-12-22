package juegoOnline.cliente.vista;


import java.awt.GridLayout;

import javax.swing.*;

import juegoOnline.cliente.controlador.ControladorDeCasilla;

import juegoBase.vista.Casilla;
import juegoBase.vista.Pieza;


@SuppressWarnings("serial")



// TODO: Opción visual para ver si es tu turno o no 


public class Pantalla extends JFrame {
	
	// La mayoría del código se reusa de juegoBase.vista.Pantalla, se ha rescrito pq se requieren algunos cambios menores
	// como implementar la vista para los dos jugadores (orientación de la matriz)
	
	private class Posicion {
		public Posicion(int f1, int c1) {f = f1; c = c1;}
		int f;
		int c;
	}
	
	
	private static Pantalla pan;
	private JPanel panelTablero;
	private Casilla[][] tablero;
	private Posicion[] clicks;
	
	
	private Pantalla () {

		
	}
	
	public void inicializarPantalla(boolean pBlanco) {
		this.inicializarTablero(pBlanco);
		this.colocarPiezas();
	}
	
	public static Pantalla getPantalla() {
		if (Pantalla.pan == null) {
			Pantalla.pan = new Pantalla();
		}
		return Pantalla.pan;
		
	}
	
	public Casilla getCasilla(int f, int c) {
		// Pre: f, c integers en rango [0,7]
		return this.tablero[f][c];
		
	}
	


	
	private void inicializarTablero(boolean pBlanco) {
		
		this.clicks = new Posicion[2];
		super.setBounds(0, 0, 680, 680); // 85* 8 = 680
		super.setResizable(false);
		this.panelTablero = new JPanel();
		this.panelTablero.setBounds(0, 0, 680, 680);
		this.panelTablero.setLayout(new GridLayout(8,8,0,0));
		super.setContentPane(this.panelTablero);

		this.tablero = new Casilla[8][8];
		
		// Crear el tablero

		for (int f = 0; f != 8; f++) {
			for (int c = 0; c != 8; c++) {

				this.tablero[f][c] = new Casilla(f,c);
				this.tablero[f][c].addMouseListener(new ControladorDeCasilla(this.tablero[f][c]));
			

			}
		}
		
		// Cambiar la orientación dependiendo del jugador que es
		
		if (pBlanco) {
			for (int f = 0; f != 8; f++) {
				for (int c = 0; c != 8; c++) {
					this.panelTablero.add(this.tablero[f][c]);

				}
			}
				
			

		} else {
			for (int f = 7; f != -1; f--) {
				for (int c = 7; c != -1; c--) {
					this.panelTablero.add(this.tablero[f][c]);

				}
			}
		}


		super.setVisible(true);

	}
	
	public void colocarPiezas() {
		// Duh, hacer lo de poner los dibujos aquí
	    // Crear las piezas y colocarlas en el tablero


		
	    // Peones negros
	    for (int c = 0; c < 8; c++) {
	        this.tablero[1][c].ponerPieza(new Pieza(0, false)); // 0 representa el peón
	    }

	    // Peones blancos
	    for (int c = 0; c < 8; c++) {
	        this.tablero[6][c].ponerPieza(new Pieza(0, true)); // 0 representa el peón
	    }

	    // Resto de las piezas negras
	    this.tablero[0][0].ponerPieza(new Pieza(3, false)); // 3 representa la torre
	    this.tablero[0][7].ponerPieza(new Pieza(3, false)); // 3 representa la torre
	    this.tablero[0][1].ponerPieza(new Pieza(1, false)); // 1 representa el caballo
	    this.tablero[0][6].ponerPieza(new Pieza(1, false)); // 1 representa el caballo
	    this.tablero[0][2].ponerPieza(new Pieza(2, false)); // 2 representa el alfil
	    this.tablero[0][5].ponerPieza(new Pieza(2, false)); // 2 representa el alfil
	    this.tablero[0][3].ponerPieza(new Pieza(4, false)); // 4 representa la reina
	    this.tablero[0][4].ponerPieza(new Pieza(5, false)); // 5 representa el rey

	    // Resto de las piezas blancas
	    this.tablero[7][0].ponerPieza(new Pieza(3, true)); // 3 representa la torre
	    this.tablero[7][7].ponerPieza(new Pieza(3, true)); // 3 representa la torre
	    this.tablero[7][1].ponerPieza(new Pieza(1, true)); // 1 representa el caballo
	    this.tablero[7][6].ponerPieza(new Pieza(1, true)); // 1 representa el caballo
	    this.tablero[7][2].ponerPieza(new Pieza(2, true)); // 2 representa el alfil
	    this.tablero[7][5].ponerPieza(new Pieza(2, true)); // 2 representa el alfil
	    this.tablero[7][3].ponerPieza(new Pieza(4, true)); // 4 representa la reina
	    this.tablero[7][4].ponerPieza(new Pieza(5, true)); // 5 representa el rey

	    

	    
	    super.repaint();
	}
	
	
	public int procesarClick(int f, int c) {
		
		
		
		// Post: Se añade el click a la posicion adecuada del stack, se returnea el tamaño de ella
		
		if (this.clicks[0] == null) {
			this.clicks[0] = new Posicion(f,c);
			return 1;
		} else {
			this.clicks[1] = new Posicion(f,c);
			return 2;
		}
		
	}
	
	public int primerClickFila() {
		// Pre: Hay un click al menos registrado
		return this.clicks[0].f;
	}
	public int primerClickCol() {
		// Pre: Hay un click al menos registrado
		return this.clicks[0].c;
	}
	
	public void eliminarClick(int val) {
		// Pre: Val usa base 1 de indexado, es decir val en rango [1,2]
		this.clicks[val-1] = null;
	}
		
	
	public void marcarComoCasillaActual(int f, int c) {
		this.tablero[f][c].marcarComoCasillaActual();
	}
	
	public void marcarComoEspacioAMover(int f, int c) {
		this.tablero[f][c].marcarComoEspacioAMover();
	}
	
	public void marcarComoEspacioADondeComer(int f, int c) {
		this.tablero[f][c].marcarComoEspacioADondeComer();

	}
	
	public void desmarcarTodo() {
		for (int i = 0; i != 8; i++) {
			for (int j = 0; j != 8; j++) {
				this.tablero[i][j].desmarcar();
			}
		}
		
		
	}

	public void moverPieza(int f1, int c1, int f2, int c2) {
		// Pre: (f1, c1) tiene pieza. (f2, c2) está en dominio del tablero
		// Post: Mover la pieza (f1, c1) -> (f2, c2) en la interfaz
		
		
		
		Pieza p = this.tablero[f1][c1].quitarPieza();
		this.tablero[f2][c2].ponerPieza(p);
		
		
		// Marcar en otro color estas dos casillas para saber que movimiento se hizo
		
		for (int i = 0; i != 8; i++) {
			for (int j = 0; j != 8; j++) {
				this.tablero[i][j].setUsadoEnTurnoAnterior(false);
				
			}
		}
		
		this.tablero[f1][c1].setUsadoEnTurnoAnterior(true);
		this.tablero[f2][c2].setUsadoEnTurnoAnterior(true);
		
	}
	
	public void quitarPieza(int f, int c) {
		// Pre: f y c en casilla de tablero con una pieza 
		// Post: se elimina visualmente la pieza
		this.tablero[f][c].quitarPieza();
	}
	
	public void ponerPieza(int f, int c, int tipo, boolean bando) {
		// Pre: en (f,c) hay peon
		// Post: Se sustituye por la pieza indicada 
		
		this.tablero[f][c].quitarPieza();
		this.tablero[f][c].ponerPieza(new Pieza(tipo, bando));
	}
	
	

	
	
	
	
}