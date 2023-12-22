package juegoBase.vista;
import java.io.File;

import javax.swing.*;

@SuppressWarnings("serial")
public class Pieza extends JLabel {
	
//	private final String RUTA_IMAGENES = ".\\piezaspng\\";
	private final String RUTA_IMAGENES = "."+File.separator+"assets"+File.separator+"piezaspng"+File.separator;

	
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
		
		// TODO: Mejorar posición de las piezas
		/* Inicio block : Handle temporal cropping de las piezas 
		  
		  Solución temporal: Mover los labels arriba un poco todos
		  y dar 320ms al programa a que mueva los labels correctamente
		  (genera lag al inicio de la partida
		  
		 */

		super.setBounds(0, -5, 85, 85);
		
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {}

		// Fin block: Handle cropping de las piezas
		
		
		
		
		super.setIcon(new ImageIcon(rutaCompleta));
		
	}

}
