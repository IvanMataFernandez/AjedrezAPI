package modelo;

import controlador.Dibujador;

public class Main {

	
	
	public static void main (String[] args) {
		
		
		/*
		 	TODO LIST:
		 	
		 	Para ver donde en el codigo añadir las cosas que faltan, en eclipse:
		 	
		 	Window -> Show View -> Tasks (se abre una pestaña más debajo donde la terminal
		 	donde pinchando en cada todo te lleva a la parte del codigo donde se debe
		 	implementar)
		 	
		 	IMPLEMENTAR:
		 	- La ventana enseñado fin de partida, las puntuaciones (victorias)
		 	  respectivas y dar la opcion de revancha	 	
		 	
		    
		  
		 */
		

//		Dibujador dib = Dibujador.getDibujador();
		Juego j = Juego.getJuego();
//		dib.inicializarPantalla();
//		dib.inicializarResultado(1);

		
		
		
		Juego.getJuego().iniciarPrograma();

		
		

		
		
		
	}
}
