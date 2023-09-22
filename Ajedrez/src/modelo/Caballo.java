package modelo;

import java.util.ArrayList;


public class Caballo extends Pieza {
    public Caballo(int posY, int posX, boolean color) {
        super(posY, posX, color,1);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
        // Implementa las reglas de movimiento del caballo aquí
        // El caballo se mueve en forma de "L" en cualquier dirección: dos pasos en una dirección y uno en la otra.
        // Debes verificar todos los movimientos posibles
        // Aquí puedes incluir la lógica específica del movimiento del caballo.
    	 ArrayList<Tupla> movimientosValidos = new ArrayList<>();
         int x = getPosX();
         int y = getPosY();
         Juego j = Juego.getJuego();
         boolean color = pBando(); // Obtener el color de la pieza (blanco o negro)
         int[][] movimientosPosibles = {
             {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
             {1, -2}, {1, 2}, {2, -1}, {2, 1}
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

         return movimientosValidos;
     

    }

	@Override
	public void procesarMovimiento(int f, int c) {
		// Caballo no procesa nada adicional
	}
	
	public String toString() {
		return "n";
	}
	

}