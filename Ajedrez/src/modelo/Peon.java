package modelo;

public class Peon extends Pieza {
    public Peon(int posX, int posY, String color) {
        super(posX, posY, color);
    }

    @Override
    public boolean esMovimientoValido(int nuevaX, int nuevaY) {
        // Implementa las reglas de movimiento del peón aquí
        // Por ejemplo, un peón puede avanzar una casilla hacia adelante o dos casillas en su primer movimiento.
        // También puede capturar piezas en diagonal.
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento del peón.
        return false;
    }
}