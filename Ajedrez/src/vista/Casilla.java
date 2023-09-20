package vista;
import java.awt.Color;

import javax.swing.*;

@SuppressWarnings("serial")
public class Casilla extends JPanel {
	
	private int f;
	private int c;
	private Pieza pieza;
	
	public Casilla (int f, int c) {
		
		if ((f+c)%2==1) {
			super.setBackground(Color.BLACK);
		} else {
			super.setBackground(Color.WHITE);
		}
		
		super.setBounds(f*64, c*64, 64, 64);
		super.setOpaque(true);
		this.f = f;
		this.c = c;
	}
	
	
	public int getFila() {
		return this.f;
	}
	
	public int getCol() {
		return this.c;
		
	}
	
	public Pieza quitarPieza() {
		
		// Se usa para alterar la primera casilla del movimiento (de donde mueve)
		
		Pieza p = null;
		if (this.pieza != null) {
			p = this.pieza;
			super.remove(this.pieza);
			this.pieza = null;
		}
		
		return p;
	}
	
	public void ponerPieza(Pieza pPieza) {
		
		// Se usa para alterar la segunda casilla del movimiento (a donde mueve), o al restablecer el tablero
		
		
		if (this.pieza != null) {
			super.remove(this.pieza);
		}
		
		this.pieza = pPieza;
		super.add(this.pieza);
		
		
	}
	
	public void marcarComoCasillaActual() {
		super.setBackground(Color.green);
	}
	
	public void marcarComoEspacioAMover() {
		super.setBackground(Color.yellow);
	}
	
	public void marcarComoEspacioADondeComer() {
		super.setBackground(Color.red);
	}
	
	public void desmarcar() {
		if ((f+c)%2==1) {
			super.setBackground(Color.BLACK);
		} else {
			super.setBackground(Color.WHITE);
		}
		
		
	}

}
