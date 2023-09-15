package vista;
import javax.swing.*;


public class Pieza extends JLabel {
	
	private final String RUTA_IMAGENES = ".\\piezaspng\\";
		
	public Pieza (int pTipoDePieza, boolean pBlanco) {
		// Pre: Tipo de pieza: 0 -> Peon, 1 -> Caballo, 2 -> Alfil, 3 -> Torre, 4 -> Reina, 5 -> Rey

		String rutaCompleta = "";
		

		
		switch (pTipoDePieza) {
		case 0:
			
			if (pBlanco) {
				rutaCompleta = RUTA_IMAGENES + "peonb.png";

			} else {
				rutaCompleta = RUTA_IMAGENES + "peond.png";

			}
			
			break;
		case 1: 
			
			if (pBlanco) {
				rutaCompleta = RUTA_IMAGENES + "caballon.png";
		
			} else {
				rutaCompleta = RUTA_IMAGENES + "caballod.png";

			}
			
			break;
		case 2:
			
			if (pBlanco) {
				rutaCompleta = RUTA_IMAGENES + "alfilb.png";

			} else {
				rutaCompleta = RUTA_IMAGENES + "alfild.png";

			}
			
			break;
		case 3:
			
			if (pBlanco) {
				rutaCompleta = RUTA_IMAGENES + "torreb.png";

			} else {
				rutaCompleta = RUTA_IMAGENES + "torred.png";

			}
			
			break;
		case 4:
			
			if (pBlanco) {
				rutaCompleta = RUTA_IMAGENES + "reinab.png";

			} else {
				rutaCompleta = RUTA_IMAGENES + "reinad.png";

			}
			
			break;
		case 5:
				
			if (pBlanco) {
				rutaCompleta = RUTA_IMAGENES + "reyb.png";

			} else {
				rutaCompleta = RUTA_IMAGENES + "reyd.png";

			}
			
		} 
		super.setBounds(0, 0, 64, 64);
		super.setIcon(new ImageIcon(rutaCompleta));
		
	}

}
