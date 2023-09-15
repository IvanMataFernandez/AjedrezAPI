package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Torre extends Reina {
	
	private boolean seMovio; // // El enroque (castling) solo se puede hacer si la torre no se movio
	
    public Torre(int posX, int posY, boolean color) {
        super(posX, posY, color);
    	this.seMovio = false;

    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
    	
    	return super.movimientosValidosPorTorre();
    }

}