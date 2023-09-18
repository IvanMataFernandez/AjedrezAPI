package modelo;

import java.util.ArrayList;


public class Rey extends Pieza {
	
	private boolean seMovio; // El enroque (castling) solo se puede hacer si el rey no se movio
	
    public Rey(int posY, int posX, boolean color) {
        super(posY, posX, color,5);
    	this.seMovio = false;

    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
        // Implementa las reglas de movimiento del rey aquí
        // El rey puede moverse en cualquier dirección, pero solo una casilla a la vez.
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento del rey.
    	
    	
    	ArrayList<Tupla> movimientosValidos = new ArrayList<>();
        int x = super.getPosX();
        int y= super.getPosY();
    	Juego j = Juego.getJuego();
    	boolean color = super.pBando(); // Obtener el color de la pieza (blanco o negro)
        int[][] movimientosPosibles = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 1}, {1, -1}, {1, 0}, {1, 1}
            };

            for (int[] movimiento : movimientosPosibles) {
                int newX = x + movimiento[1];
                int newY = y + movimiento[0];

                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                    if (!j.hayPieza(newY, newX) || j.esBlanco(newY, newX) != color) {
                        movimientosValidos.add(new Tupla(newY, newX, j.hayPieza(newY, newX)));
                    }
                }
            } 
    	 
    	
    	// CALCULAR CASTLING
    	
    	
        return movimientosValidos;
    }

}
