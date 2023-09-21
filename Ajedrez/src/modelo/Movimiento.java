package modelo;
import modelo.Pieza;
import modelo.Tupla;
public class Movimiento {
	
	private int f1;
 	private int c1;
 	private int f2;
 	private int c2;
 	private boolean enPassant;
 	Pieza piezaComida;

 	public Movimiento(int f1, int c1, int f2, int c2) {
		super();
		this.f1 = f1;
		this.c1 = c1;
		this.f2 = f2;
		this.c2 = c2;
	}
 	

    public void ejecutarMovimiento(Pieza[][] matriz, Pieza piezaAMover, Tupla infoDeMove) {
        // Pre: piezaComida == null
        // Post: Actualizar el movimiento, pero no definitivo y se puede deshacer

    	
        // Eliminar la ficha comida (Si hubiera)
        if (infoDeMove.come()) {
            if (matriz[f2][c2] != null) { // Eliminacion por poner tu ficha en la de el/ella
                piezaComida = matriz[f2][c2];
                matriz[f2][c2] = null;
            } else { // Eliminacion enPassant
                if (piezaAMover.pBando()) {
                    piezaComida = matriz[f2 + 1][c2]; // cuando blanco, el negro eliminado una fila abajo
                    matriz[f2 + 1][c2] = null;
                } else {
                    piezaComida = matriz[f2 - 1][c2];
                    matriz[f2 - 1][c2] = null;
                }
            }
        }

        // Mover la pieza

        matriz[piezaAMover.getPosY()][piezaAMover.getPosX()] = null;
        matriz[f2][c2] = piezaAMover;
        piezaAMover.setPosY(f2);
        piezaAMover.setPosX(c2);
    }

    // Añade el método deshacerMovimiento aquí
    public void deshacerMovimiento(Pieza[][] matriz) {
        // Pre: Este ha sido el último movimiento ejecutado

        // Mover atrás de nuevo la pieza
        matriz[f1][c1] = matriz[f2][c2];
        matriz[f2][c2] = null;
        matriz[f1][c1].setPosY(f1);
        matriz[f1][c1].setPosX(c1);

        // Respawnear la ficha comida (Si hubiera) y
        if (piezaComida != null) {
            if (!enPassant) { // Eliminación por poner tu ficha en la de él/ella
                matriz[f2][c2] = piezaComida;
            } else { // Eliminación enPassant
                matriz[piezaComida.getPosY()][piezaComida.getPosX()] = piezaComida;
            }
        }
    }
}