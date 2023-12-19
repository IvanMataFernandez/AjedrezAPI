package juegoBase.controlador;

public class ComandoAInterfazAñadirPieza extends ComandoAInterfazBorrarPieza {

	
	private int tipo;  // Que tipo de pieza se desea poner (solo usado en
	                   // promociones de peon, -1 si todavia se debe elegir)
	private boolean blanco;
	
	public ComandoAInterfazAñadirPieza(int f1, int c1, boolean pBlanco) {
		super(f1, c1);
		this.tipo = -1;
		this.blanco = pBlanco;
	}
	
	public void setTipo(int pTipo) {this.tipo = pTipo;}
	
	public int getTipo() {return this.tipo;}
	
	public boolean esBlanco() {return this.blanco;}
	
	
	

}
