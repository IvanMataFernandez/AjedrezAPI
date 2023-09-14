package modelo;

public abstract class Pieza {
    // Campos  para todas las piezas
    private int posX;
    private int posY;
    private String color;

    // Constructor
    public Pieza(int posX, int posY, String color) {
        this.posX = posX;
        this.posY = posY;
        this.color = color;
    }

    // Métodos abstractos que las demas piezas deben implementar
    public abstract boolean esMovimientoValido(int nuevaX, int nuevaY);


    // Getters y setters para los campos privados
    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
