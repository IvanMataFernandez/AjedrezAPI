package controlador;

public class ComandoAInterfazMoverPieza extends ComandoAInterfazBorrarPieza {

	private int f1; // Fila de donde mover
	private int c1; // Columna de donde mover
//  super int f    // Fila a donde mover
//  super int c    // Columna a donde mover
	
	public ComandoAInterfazMoverPieza (int f1, int c1, int f2, int c2) {
		super(f2, c2);
		this.f1 = f1; 
		this.c1 = c1;
	}

	
	public int getF1() {return this.f1;}
	public int getC1() {return this.c1;}
	
}	