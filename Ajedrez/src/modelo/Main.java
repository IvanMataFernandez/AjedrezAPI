package modelo;

import controlador.*;
import vista.ReproductorDeAudio;

public class Main {

	
	
	public static void main (String[] args) {
		
		
		/*
		 	TODO LIST:
		 	
		 	- Computar si se puede hacer castling
		 	- Filtrar los movimientos "legales" que dejan en jaque al rey tras ejecutarlos
		 	- Finalizar el codigo que hace que las casillas se iluminen
		 	- Programar el movimiento internamente del programa
		 	- Programar el movimiento en la interfaz
		 	
		  
		  
		 */
		
		Dibujador dib = Dibujador.getDibujador();
		Juego j = Juego.getJuego();
		dib.inicializarPantalla();
		ReproductorDeAudio.getRep().reproducirSFX("tetris mario");
		j.jugar();
		

		
		
		
	}
}
