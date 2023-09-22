package modelo;

import controlador.*;
import vista.Pieza;
import vista.ReproductorDeAudio;

public class Main {

	
	
	public static void main (String[] args) {
		
		
		/*
		 	TODO LIST:
		 	
		 	- Programar check para promocion de peones (En Movimiento)
            - Programar la clase ComandoAInterfazAñadirPieza extendiendo del de Borrar
              para poder dibujar nuevas piezas ascendidas de peon en la interfaz
            - Debuggear casos críticos		 	
		 	
		    
		  
		 */
		
		Dibujador dib = Dibujador.getDibujador();
		Juego j = Juego.getJuego();
		dib.inicializarPantalla();
		j.jugar();
		

		
		
		
	}
}
