package modelo;

import java.util.ArrayList;
import modelo.Pieza.Tupla;
import modelo.Juego;

public class Alfil extends Reina {
    public Alfil(int posY, int posX, boolean color) {
        super(posY, posX, color);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() { //Math.abs(f1 – f2) == Math.abs(c1 – c2)
    	
    	return super.movimientosValidosPorAlfil();
    }
}
