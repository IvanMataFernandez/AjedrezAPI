package juegoBase.modelo;

import java.util.ArrayList;


public class Torre extends Reina {
	
	private int vecesMovido; // // El enroque (castling) solo se puede hacer si la torre no se movio
	
    public Torre(int posY, int posX, boolean color) {
        super(posY, posX, color,3);
    	this.vecesMovido = 0;

    }
    

    
    public ArrayList<Tupla> movimientosValidos() {
    	
    	return super.movimientosValidosPorTorre();
    }
    
    public boolean seMovio() {return this.vecesMovido != 0;}

	public void procesarMovimiento(int f, int c) {

		// Se está aceptando el movimiento, así que se habrá movido
		
		this.vecesMovido++;
	}
	
	public void antiProcesarMovimiento(int f, int c) {
		this.vecesMovido--;
	}
	
	public String toString() {
		return "r";
	}
    
}