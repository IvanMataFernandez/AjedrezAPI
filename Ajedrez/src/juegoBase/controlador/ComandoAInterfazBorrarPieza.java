package juegoBase.controlador;

public class ComandoAInterfazBorrarPieza {

	private int f; // Fila sobre opera
	private int c; // Columna sobre opera

	
	public ComandoAInterfazBorrarPieza (int f1, int c1) {f = f1; c = c1;}

	
	public int getF() {return this.f;}
	public int getC() {return this.c;}
	
}	
