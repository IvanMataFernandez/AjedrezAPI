package modelo;

import java.util.ArrayList;

public abstract class Pieza {
    // Campos  para todas las piezas
    private int posX;
    private int posY;
    private boolean blancoYNoNegro;

    // Constructor
    public Pieza(int posY, int posX, boolean pBando) {
        this.posX = posX;
        this.posY = posY;
        this.blancoYNoNegro = pBando;
    }

    // Métodos abstractos que las demas piezas deben implementar
    public abstract ArrayList<Tupla> movimientosValidos();


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

    public boolean pBando() {
        return this.blancoYNoNegro;
    }
    /*
    public void setColor(String color) {
        this.color = color;
    } 
    Las piezas no cambian de bando tras ser creadas, no deberia ser necesario este
    
    */
    
    protected class Tupla {
    	public Tupla (int f1, int c1) {f = f1; c = c1;}
    	
    	int f;
    	int c;
    	
    }
}
