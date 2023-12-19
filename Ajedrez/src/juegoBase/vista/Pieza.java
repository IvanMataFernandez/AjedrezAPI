package juegoBase.vista;
import java.io.File;

import javax.swing.*;

@SuppressWarnings("serial")
public class Pieza extends JLabel {
	
//	private final String RUTA_IMAGENES = ".\\piezaspng\\";
	private final String RUTA_IMAGENES = "."+File.separator+"piezaspng"+File.separator;

	
	public Pieza (int pTipoDePieza, boolean pBlanco) {
		// Pre: Tipo de pieza: 0 -> Peon, 1 -> Caballo, 2 -> Alfil, 3 -> Torre, 4 -> Reina, 5 -> Rey

		String rutaCompleta = "";
		
		if (pBlanco) {
			rutaCompleta = RUTA_IMAGENES + "w";

		} else {
			rutaCompleta = RUTA_IMAGENES + "b";

		}	

		
		switch (pTipoDePieza) {
		case 0:
			rutaCompleta = rutaCompleta + "p.png";

			break;
		case 1: 
			rutaCompleta = rutaCompleta + "n.png";
			
			break;
		case 2:
			rutaCompleta = rutaCompleta + "b.png";

			break;
		case 3:
			rutaCompleta =  rutaCompleta + "r.png";

			break;
		case 4:
			rutaCompleta =  rutaCompleta + "q.png";

			break;
		case 5:
			rutaCompleta =  rutaCompleta + "k.png";

		} 
		

		super.setBounds(0, 0, 85, 85);
		super.setIcon(new ImageIcon(rutaCompleta));
		
	}

}
