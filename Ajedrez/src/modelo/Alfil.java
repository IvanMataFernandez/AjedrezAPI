package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Alfil extends Reina {
    public Alfil(int posY, int posX, boolean color) {
        super(posY, posX, color);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
    	
    	return super.movimientosValidosPorAlfil();
    }
}
