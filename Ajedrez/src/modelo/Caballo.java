package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Caballo extends Pieza {
    public Caballo(int posY, int posX, boolean color) {
        super(posY, posX, color);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
        // Implementa las reglas de movimiento del caballo aquí
        // El caballo se mueve en forma de "L" en cualquier dirección: dos pasos en una dirección y uno en la otra.
        // Debes verificar todos los movimientos posibles
        // Aquí puedes incluir la lógica específica del movimiento del caballo.
    	 ArrayList<Tupla> movimientos = new ArrayList<>();
         int x = getPosX();
         int y = getPosY();
         Juego j = Juego.getJuego();
     	 Pieza[][] tablero=j.getTablero();
         boolean color = pBando(); // Obtener el color de la pieza (blanco o negro)
         int[][] movimientosPosibles = {
             {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
             {1, -2}, {1, 2}, {2, -1}, {2, 1}
         };

         for (int[] movimiento : movimientosPosibles) {
             int newX = x + movimiento[0];
             int newY = y + movimiento[1];

             if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                 Pieza destino = tablero[newX][newY];
                 if (!j.hayPieza(y, x) || j.esBlanco(y, x) != color) {
                     movimientos.add(new Tupla(y, x));
                 }
             }
         }

         return movimientos;
     

    }

}