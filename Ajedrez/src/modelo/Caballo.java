package modelo;

public class Caballo extends Pieza {
    public Caballo(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        // Implementa las reglas de movimiento del caballo aquí
        // El caballo se mueve en forma de "L" en cualquier dirección: dos pasos en una dirección y uno en la otra.
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento del caballo.
        return false;
    }

}