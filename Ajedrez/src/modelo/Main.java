package modelo;

import controlador.*;
import vista.ReproductorDeAudio;

public class Main {

	
	
	public static void main (String[] args) {
		
		Dibujador dib = Dibujador.getDibujador();
		Juego j = Juego.getJuego();
		dib.inicializarPantalla();
		ReproductorDeAudio.getRep().reproducirSFX("tetris mario");
		j.jugar();
		

		
		
		
	}
}
