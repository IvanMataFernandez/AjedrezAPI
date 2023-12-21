package juegoBase.controlador;

import juegoBase.vista.*;

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
		Pantalla.getPantalla().inicializarPantalla();
	}
	
	public void matarPantalla() {
		Pantalla.getPantalla().dispose();
	}
	
	public void inicializarResultado(int pResultado, int numVictoriasBlanco, int numVictoriasNegro) {
		Resultado.crearVentanaResultados(pResultado, numVictoriasBlanco, numVictoriasNegro);
		
	}
	
}
