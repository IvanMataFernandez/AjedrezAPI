package modelo;

import java.util.ArrayList;

public class Alfil extends Reina {
    public Alfil(int posY, int posX, boolean color) {
        super(posY, posX, color,2);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() { 
    	
    	return super.movimientosValidosPorAlfil();
    }
}
