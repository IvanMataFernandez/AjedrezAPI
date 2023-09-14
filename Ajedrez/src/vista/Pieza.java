package vista;
import javax.swing.*;

public class Pieza extends JLabel {
		
	public Pieza (int pTipoDePieza, boolean pBlanco) {
		// Pre: Tipo de pieza: 0 -> Peon, 1 -> Caballo, 2 -> Alfil, 3 -> Torre, 4 -> Reina, 5 -> Rey

		
		super.setBounds(pTipoDePieza, pTipoDePieza, pTipoDePieza, pTipoDePieza);
		// Valores discutibles, depende de como de grande es el dibujo de la pieza
		// Estos valores se escriben relativos a la casilla a la que estan colocados
		
		switch (pTipoDePieza) {
		case 0:
			
			if (pBlanco) {
				// Generar imagen peon blanco
			} else {
				// Generar imagen peon negro
			}
			
			break;
		case 1: 
			
			if (pBlanco) {
				// Generar imagen caballo blanco
			} else {
				// Generar imagen caballo negro
			}
			
			break;
		case 2:
			
			if (pBlanco) {
				// Generar imagen alfil blanco
			} else {
				// Generar imagen alfil negro
			}
			
			break;
		case 3:
			
			if (pBlanco) {
				// Generar imagen torre blanco
			} else {
				// Generar imagen torre negro
			}
			
			break;
		case 4:
			
			if (pBlanco) {
				// Generar imagen reina blanca
			} else {
				// Generar imagen reina negra
			}
			
			break;
		case 5:
				
			if (pBlanco) {
				// Generar imagen rey blanco
			} else {
				// Generar imagen rey negro
			}
			
		}
		
		
	}

}
