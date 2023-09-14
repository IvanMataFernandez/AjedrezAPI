package vista;

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
		
	}
	
	
	
	
	public void procesarClick(int f, int c) {
		
		// TODO
		
		if (this.clicks[0] == null) {
			this.clicks[0] = new Posicion(f,c);
		}
		
	}
		

	
	
	
	
	
	
}
