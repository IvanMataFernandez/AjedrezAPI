package modelo;

import controlador.*;

public class Main {

	
	
	public static void main (String[] args) {
		
		Dibujador dib = Dibujador.getDibujador();
		Juego j = Juego.getJuego();
		dib.inicializarPantalla();
		j.jugar();
		

		
		
		
	}
}
