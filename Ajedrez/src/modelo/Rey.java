package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Rey extends Pieza {
	
	private boolean seMovio; // El enroque (castling) solo se puede hacer si el rey no se movio
	
    public Rey(int posY, int posX, boolean color) {
        super(posY, posX, color);
    	this.seMovio = false;

    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
        // Implementa las reglas de movimiento del rey aquí
        // El rey puede moverse en cualquier dirección, pero solo una casilla a la vez.
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento del rey.
        return null;
    }

}
