package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Alfil extends Reina {
    public Alfil(int posX, int posY, boolean color) {
        super(posX, posY, color);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
    	
    	return super.movimientosValidosPorAlfil();
    }
}
