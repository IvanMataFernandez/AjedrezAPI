package modelo;

import java.util.ArrayList;


public class Reina extends Pieza {
    public Reina(int posY, int posX, boolean color, int pTipo) {
        super(posY, posX, color, pTipo);
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
    	
    	
    	ArrayList<Tupla> movimientosValidos = new ArrayList<>();
        int x = super.getPosX();
        int y= super.getPosY();
    	Juego j = Juego.getJuego();
    	 boolean color = super.pBando(); // Obtener el color de la pieza (blanco o negro)
    	 
	    // Movimientos hacia la derecha
    	 
	    for (int i = 1; x + i < 8; i++) {
	        if (!j.hayPieza(y, x+i)) {
	            movimientosValidos.add(new Tupla(y, x + i, false));
	        } else if (j.esBlanco(y, x+i) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y, x + i, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }
	    
	 // Movimientos hacia la izquierda
	    for (int i = 1; x - i >= 0; i++) {
	    	 if (!j.hayPieza(y, x-i)) {
	            movimientosValidos.add(new Tupla(y, x - i, false));
	        } else if (j.esBlanco(y, x-i) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y, x - i, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }
	    
	    // Movimientos hacia abajo

	    for (int i = 1; y + i < 8; i++) {
	        if (!j.hayPieza(y+i, x)) {
	            movimientosValidos.add(new Tupla(y+i, x, false));
	        } else if (j.esBlanco(y+i, x) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y+i, x, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }
	    
	 // Movimientos hacia arriba
	    for (int i = 1; y - i >= 0; i++) {
	    	 if (!j.hayPieza(y-i, x)) {
	            movimientosValidos.add(new Tupla(y-i, x, false));
	        } else if (j.esBlanco(y-i, x) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y-i, x, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }
	    
	   return movimientosValidos;
    	
    	
    }
    
    protected ArrayList<Tupla> movimientosValidosPorAlfil() {
    	ArrayList<Tupla> movimientosValidos = new ArrayList<>();
        int x = super.getPosX();
        int y= super.getPosY();
    	Juego j = Juego.getJuego();
    	 boolean color = super.pBando(); // Obtener el color de la pieza (blanco o negro)
	    // Movimientos en diagonal superior izquierda
	    for (int i = 1; x - i >= 0 && y - i >= 0; i++) {
	        if (!j.hayPieza(y-i, x-i)) {
	            movimientosValidos.add(new Tupla(y - i, x - i, false));
	        } else if (j.esBlanco(y-i, x-i) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y - i, x - i, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }
	    
	 // Movimientos en diagonal superior derecha
	    for (int i = 1; x + i < 8 && y - i >= 0; i++) {
	    	 if (!j.hayPieza(y-i, x+i)) {
	            movimientosValidos.add(new Tupla(y - i, x + i, false));
	        } else if (j.esBlanco(y-i, x+i) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y - i, x + i, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }

	    // Movimientos en diagonal inferior izquierda
	    for (int i = 1; x - i >= 0 && y + i < 8; i++) {
	        if (!j.hayPieza(y+i, x-i)) {
	            movimientosValidos.add(new Tupla(y + i, x - i, false));
	        } else if (j.esBlanco(y+i, x-i) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y + i, x - i, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }

	    // Movimientos en diagonal inferior derecha
	    for (int i = 1; x + i < 8 && y + i < 8; i++) {
	        if (!j.hayPieza(y+i,x+i)) {
	            movimientosValidos.add(new Tupla(y+ i, x + i,false));
	        } else if (j.esBlanco(y+i, x+i) != color) {
	            // La casilla contiene una pieza del oponente, podemos capturarla
	            movimientosValidos.add(new Tupla(y + i, x + i, true));
	            break; // No se pueden seguir moviendo en esta dirección después de una captura
	        } else {
	            break; // No se pueden seguir moviendo en esta dirección si hay una pieza del mismo color
	        }
	    }
	    
	   return movimientosValidos;
    }

	public void procesarMovimiento(int f, int c) {
		// La reina no actualiza flags adicionales
		
	}
    
	
	public String toString() {
		return "q";
	}

}
