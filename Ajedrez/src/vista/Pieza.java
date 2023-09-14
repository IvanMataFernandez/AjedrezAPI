package vista;
import javax.swing.*;


public class Pieza extends JLabel {
	
    private static final String RUTA_IMAGENES = "piezaspng"; // Ruta del paquete de imágenes
		
	public Pieza (int pTipoDePieza, boolean pBlanco) {
		// Pre: Tipo de pieza: 0 -> Peon, 1 -> Caballo, 2 -> Alfil, 3 -> Torre, 4 -> Reina, 5 -> Rey

		
		super.setBounds(pTipoDePieza, pTipoDePieza, pTipoDePieza, pTipoDePieza);
		// Valores discutibles, depende de como de grande es el dibujo de la pieza
		// Estos valores se escriben relativos a la casilla a la que estan colocados
		
		switch (pTipoDePieza) {
		case 0:
			
			if (pBlanco) {
				String rutaCompleta = RUTA_IMAGENES + "peonb.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen peon blanco
			} else {
				String rutaCompleta = RUTA_IMAGENES + "peonn.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen peon negro
			}
			
			break;
		case 1: 
			
			if (pBlanco) {
				String rutaCompleta = RUTA_IMAGENES + "caballob.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen caballo blanco
			} else {
				String rutaCompleta = RUTA_IMAGENES + "caballon.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen caballo negro
			}
			
			break;
		case 2:
			
			if (pBlanco) {
				String rutaCompleta = RUTA_IMAGENES + "alfilb.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen alfil blanco
			} else {
				String rutaCompleta = RUTA_IMAGENES + "alfiln.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen alfil negro
			}
			
			break;
		case 3:
			
			if (pBlanco) {
				String rutaCompleta = RUTA_IMAGENES + "torreb.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen torre blanco
			} else {
				String rutaCompleta = RUTA_IMAGENES + "torren.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen torre negro
			}
			
			break;
		case 4:
			
			if (pBlanco) {
				String rutaCompleta = RUTA_IMAGENES + "reinab.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen reina blanca
			} else {
				String rutaCompleta = RUTA_IMAGENES + "reinan.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen reina negra
			}
			
			break;
		case 5:
				
			if (pBlanco) {
				String rutaCompleta = RUTA_IMAGENES + "reyb.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen rey blanco
			} else {
				String rutaCompleta = RUTA_IMAGENES + "reyn.png";
		        // Cargar la imagen y establecerla como icono de la etiqueta
		        ImageIcon imagen = new ImageIcon(getClass().getResource(rutaCompleta));
		        setIcon(imagen);
				// Generar imagen rey negro
			}
			
		}
		
		
	}

}
