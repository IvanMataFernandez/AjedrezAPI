package modelo;

public class Reina extends Pieza {
    public Reina(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        // Implementa las reglas de movimiento de la reina aquí
        // La reina puede moverse en línea recta en cualquier dirección (horizontal, vertical o diagonal).
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento de la reina.
        return false;
    }

}
