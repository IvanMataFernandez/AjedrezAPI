package controlador;

import vista.*;

public class Dibujador {

	private static Dibujador dib;
	
	private Dibujador() {}
	
	public static Dibujador getDibujador() {
		if (Dibujador.dib == null) {
			Dibujador.dib = new Dibujador();
		}
		return Dibujador.dib;
	}
	
	public void inicializarPantalla() {
		Pantalla.getPantalla();
	}
	
	public void inicializarResultado() {
		Resultado.getResultado();
		
	}
	
}
