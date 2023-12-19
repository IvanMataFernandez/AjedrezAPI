package juegoBase.modelo;

import java.util.ArrayList;


public class Torre extends Reina {
	
	private boolean seMovio; // // El enroque (castling) solo se puede hacer si la torre no se movio
	
    public Torre(int posY, int posX, boolean color) {
        super(posY, posX, color,3);
    	this.seMovio = false;

    }
    

    
    public ArrayList<Tupla> movimientosValidos() {
    	
    	return super.movimientosValidosPorTorre();
    }
    
    public boolean seMovio() {return this.seMovio;}

	public void procesarMovimiento(int f, int c) {

		// Se está aceptando el movimiento, así que se habrá movido
		
		this.seMovio = true;
	}
	
	public String toString() {
		return "r";
	}
    
}