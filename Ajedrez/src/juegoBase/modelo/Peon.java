package juegoBase.modelo;

import java.util.ArrayList;


public class Peon extends Pieza {
	
	
	private boolean vulnerableAEnPassant; // vulnerable <--> Su primer y unico movimiento ha sido avanzar dos casillas
	
    public Peon(int posY, int posX, boolean color) {
        super(posY, posX, color,0);
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
    	
    	ArrayList<Tupla> movimientosValidos = new ArrayList<>();
        int x = super.getPosX();
        int y= super.getPosY();
    	Matriz j = Matriz.getMatriz();
    	boolean color = super.pBando(); // Obtener el color de la pieza (blanco o negro)
    	
    	// si es primer movimiento
    	if (color) {
    		
    			if(!j.hayPieza(y-1, x)) {
    				movimientosValidos.add(new Tupla(y-1, x, false ));
    				if(y == 6 && !j.hayPieza(y-2, x)) {
    					movimientosValidos.add(new Tupla(y-2, x, false ));
    				}    				
    			}
    			
    			if (x + 1 < 8) {
        			if (j.hayPieza(y-1, x+1) && j.esBlanco(y-1, x+1) != color) {
    					movimientosValidos.add(new Tupla(y-1, x+1, true ));
        			}   
        			
        			if (j.hayPieza(y, x+1) && j.esBlanco(y, x+1) != color && j.vulnerableAEnPassant(y, x+1)) {
    					movimientosValidos.add(new Tupla(y-1, x+1, true ));
        			}
    			}
    			
    			if (x -1 > -1) {
        			if (j.hayPieza(y-1, x-1) && j.esBlanco(y-1, x-1) != color) {
    					movimientosValidos.add(new Tupla(y-1, x-1, true ));
        			}
        			
        			if (j.hayPieza(y, x-1) && j.esBlanco(y, x-1) != color && j.vulnerableAEnPassant(y, x-1)) {
    					movimientosValidos.add(new Tupla(y-1, x-1, true ));
        			}
    			}
    			

    		

    			
    		
    	}else {
    			if(!j.hayPieza(y+1, x)) {
    				movimientosValidos.add(new Tupla(y+1, x,false ));
    				if(y == 1 && !j.hayPieza(y+2, x)) {
    					movimientosValidos.add(new Tupla(y+2, x, false ));
    				}

    			}
    			
    			if (x + 1 < 8) {
        			if (j.hayPieza(y+1, x+1) && j.esBlanco(y+1, x+1) != color) {
    					movimientosValidos.add(new Tupla(y+1, x+1, true ));
        			}    
        			
        			if (j.hayPieza(y, x+1) && j.esBlanco(y, x+1) != color && j.vulnerableAEnPassant(y, x+1)) {
    					movimientosValidos.add(new Tupla(y+1, x+1, true ));
        			}
    			}
    			
    			if (x - 1 > -1) {
        			if (j.hayPieza(y+1, x-1) && j.esBlanco(y+1, x-1) != color) {
    					movimientosValidos.add(new Tupla(y+1, x-1, true ));
        			}
        			
        			if (j.hayPieza(y, x-1) && j.esBlanco(y, x-1) != color && j.vulnerableAEnPassant(y, x-1)) {
    					movimientosValidos.add(new Tupla(y-1, x-1, true ));
        			}
    			}
    		

    			
    	}
    	

        return movimientosValidos;
    }
    

    public boolean vulnerableAEnPassant() {
    	return this.vulnerableAEnPassant;
    }

	public void procesarMovimiento(int f, int c) {
		// Comprobar si puede ser eliminado por enPassant
		
		// Si acaba de mover dos casillas adelante es vulnerable, si no, no
		
		this.vulnerableAEnPassant = Math.abs(super.getPosY() - f) == 2;
		

		
	}
	
	public String toString() {
		return "p";
	}
	
}