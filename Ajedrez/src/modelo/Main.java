package modelo;

import controlador.*;
import vista.Pieza;
import vista.ReproductorDeAudio;

public class Main {

	
	
	public static void main (String[] args) {
		
		
		/*
		 	TODO LIST:
		 	
		 	Para ver donde en el codigo añadir las cosas que faltan, en eclipse:
		 	
		 	Window -> Show View -> Tasks (se abre una pestaña más debajo donde la terminal
		 	donde pinchando en cada todo te lleva a la parte del codigo donde se debe
		 	implementar)
		 	
		 	IMPLEMENTAR:
		 	- Programar check para promocion de peones (En Movimiento)
            - Programar la clase ComandoAInterfazAñadirPieza extendiendo del de Borrar
              para poder dibujar nuevas piezas ascendidas de peon en la interfaz
            - Añadir sonido de mover pieza al movimiento
            TESTEAR:
            - Debuggear casos críticos		 	
		 	
		    
		  
		 */
		
		Dibujador dib = Dibujador.getDibujador();
		Juego j = Juego.getJuego();
		dib.inicializarPantalla();
		j.jugar();
		

		
		
		
	}
}
