package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Torre extends Reina {
	
	private boolean seMovio; // // El enroque (castling) solo se puede hacer si la torre no se movio
	
    public Torre(int posY, int posX, boolean color) {
        super(posY, posX, color);
    	this.seMovio = false;

    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
    	
    	return super.movimientosValidosPorTorre();
    }

}