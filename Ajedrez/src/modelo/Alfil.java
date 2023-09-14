package modelo;

public class Alfil extends Pieza {
    public Alfil(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        // Implementa las reglas de movimiento del alfil aquí
        // El alfil se mueve en diagonal en cualquier dirección.
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento del alfil.
        return false;
    }
}
