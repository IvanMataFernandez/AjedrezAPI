package vista;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

import controlador.ControladorDeCasilla;

@SuppressWarnings("serial")



public class Pantalla extends JFrame {
	
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
	//	this.metodoParaDebuggear();
		this.inicializarTablero();
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
	
	private void metodoParaDebuggear() {
		super.setVisible(true);
		super.setBounds(0, 0, 512, 512);
		super.setResizable(false);
		JPanel a = new JPanel(null);
		a.setBackground(Color.red);
		a.setOpaque(true);
		a.setBounds(0, 0, 512, 512);
		super.setContentPane(a);
		JLabel b = new JLabel();
		b.setBounds(0, 0, 64, 64);
		b.setIcon(new ImageIcon(".\\piezaspng\\alfilb.png"));
		a.add(b);
	}
	
	private void inicializarTablero() {
		
		this.clicks = new Posicion[2];
		super.setBounds(0, 0, 512, 512);
		super.setResizable(false);
		this.panelTablero = new JPanel();
		this.panelTablero.setBounds(0, 0, 512, 512);
		this.panelTablero.setLayout(new GridLayout(8,8,0,0));
		super.setContentPane(this.panelTablero);

		this.tablero = new Casilla[8][8];
		
		for (int f = 0; f != 8; f++) {
			for (int c = 0; c != 8; c++) {

				
				this.tablero[f][c] = new Casilla(f,c);
				this.tablero[f][c].addMouseListener(new ControladorDeCasilla(this.tablero[f][c]));
			
				this.panelTablero.add(this.tablero[f][c]);

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
	
	public void eliminarClick(int val) {
		// Pre: val = 0-1
		this.clicks[val] = null;
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

	
	
	
	
	
	
}
