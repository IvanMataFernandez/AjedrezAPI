package modelo;

public class Torre extends Pieza {
    public Torre(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        // Implementa las reglas de movimiento de la torre aquí
        // La torre se mueve en líneas rectas, horizontal o verticalmente.
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento de la torre.
        return false;
    }

}