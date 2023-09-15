package modelo;

import java.util.ArrayList;

import modelo.Pieza.Tupla;

public class Caballo extends Pieza {
    public Caballo(int posX, int posY, boolean color) {
        super(posX, posY, color);
    }

    @Override
    public ArrayList<Tupla> movimientosValidos() {
        // Implementa las reglas de movimiento del caballo aquí
        // El caballo se mueve en forma de "L" en cualquier dirección: dos pasos en una dirección y uno en la otra.
        // Debes verificar todos los movimientos posibles
        // Aquí puedes incluir la lógica específica del movimiento del caballo.
        return null;
    }

}