package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Reina extends Pieza {
    public Reina(int posY, int posX, boolean color) {
        super(posY, posX, color);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
    	
    	// Para recopilar los movimientos posibles de la reina, usamos la union de los movimientos
    	// legales de una torre y alfil a la vez
    	
    	ArrayList<Tupla> validos1 = this.movimientosValidosPorAlfil();
    	ArrayList<Tupla> validos2 = this.movimientosValidosPorTorre();
    	
    	for (int i = 0; i != validos2.size(); i++) {
    		validos1.add(validos2.get(i));
    	}
    	
    	return validos1;
    	
    	
    }
    
    
    protected ArrayList<Tupla> movimientosValidosPorTorre() {
    	
        // Implementa las reglas de movimiento de la torre aquí
        // La torre se mueve ortogonalmente las casillas que quiera
        // Debes verificar todos los movimientos posibles
        // Aquí puedes incluir la lógica específica del movimiento de la torre.
    	return null;
    }
    
    protected ArrayList<Tupla> movimientosValidosPorAlfil() {
    	
        // Implementa las reglas de movimiento del alfil aquí
        // El alfil se mueve diagonalmente las casillas que quiera
        // Debes verificar todos los movimientos posibles
        // Aquí puedes incluir la lógica específica del movimiento del alfil.
    	
    	return null;
    }
    

}
