package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Peon extends Pieza {
	
	
	private boolean vulnerableAEnPassant; // vulnerable <--> Su primer y unico movimiento ha sido avanzar dos casillas
	
    public Peon(int posX, int posY, boolean color) {
        super(posX, posY, color);
    	this.vulnerableAEnPassant = false;

    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
        // Implementa las reglas de movimiento del peón aquí
        // Por ejemplo, un peón puede avanzar una casilla hacia adelante o dos casillas en su primer movimiento.
    	// Pista: Para saber si es su primer movimiento puedes mirar la fila en la que esta
        // También puede capturar piezas en diagonal.
    	// Un peon puede eliminar a otro si esta en estado vulnerable a EnPassant en un estado de tablero concreto
    	// Información: https://es.wikipedia.org/wiki/Captura_al_paso
        // Debes verificar todos los movimientos posibles
        // Aquí puedes incluir la lógica específica del movimiento del peón.
    	
        return null;
    }
    

    public boolean vulnerableAEnPassant() {
    	return this.vulnerableAEnPassant;
    }
}