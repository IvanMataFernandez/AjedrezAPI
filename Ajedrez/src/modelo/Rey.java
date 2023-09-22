package modelo;

import java.util.ArrayList;


public class Rey extends Pieza {
	
	private boolean seMovio; // El enroque (castling) solo se puede hacer si el rey no se movio
	
    public Rey(int posY, int posX, boolean color) {
        super(posY, posX, color,5);
    	this.seMovio = false;

    }
    
    public boolean seMovio() {return this.seMovio;}
    

    @Override
    public ArrayList<Tupla> movimientosValidos() {
        // Implementa las reglas de movimiento del rey aquí
        // El rey puede moverse en cualquier dirección, pero solo una casilla a la vez.
        // Debes verificar si el movimiento es válido según las reglas del ajedrez.
        // Retorna true si es válido, false en caso contrario.
        // Aquí puedes incluir la lógica específica del movimiento del rey.
    	
    	
    	ArrayList<Tupla> movimientosValidos = new ArrayList<>();
        int x = super.getPosX();
        int y= super.getPosY();
    	Juego j = Juego.getJuego();
    	boolean color = super.pBando(); // Obtener el color de la pieza (blanco o negro)
        int[][] movimientosPosibles = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 1}, {1, -1}, {1, 0}, {1, 1}
            };

            for (int[] movimiento : movimientosPosibles) {
                int newX = x + movimiento[1];
                int newY = y + movimiento[0];

                if (newX >= 0 && newX < 8 && newY >= 0 && newY < 8) {
                    if (!j.hayPieza(newY, newX) || j.esBlanco(newY, newX) != color) {
                        movimientosValidos.add(new Tupla(newY, newX, j.hayPieza(newY, newX)));
                    }
                }
            } 
            
            if (!this.seMovio && !super.enRangoDelRival()) {
                // Enroque largo (0-0-0)
        
                if (j.estadoDeCasilla(y, 4) == 3 && !(j.seMovio(y, 4))) {
                	
                        // Verificar si el camino entre el rey y la torre está despejado y la torre no se ha movido
                        // En ninguna casilla por la que pasa el rey debe estar en jaque
                	
                		boolean caminoDespejado = true;
                		Movimiento mov;
                		
                        for (int i = 1; i <= 3; i++) {
                        	
                        	// Mirar si hay pieza
                        	
                            if (j.hayPieza(y, i)) {
                                caminoDespejado = false;
                                break;
                            } else {
                            	
                            	// Mirar si estaría en jaque al mover ahí
                            	
                            	mov = new Movimiento(y, x, new Tupla(y, i, false));
                            	mov.ejecutarMovimiento();
                            	
                            	if (super.enRangoDelRival()) {
                                    caminoDespejado = false;
                                    mov.deshacerMovimiento();
                                    break;
                            	} else {
                                    mov.deshacerMovimiento();	
                            	}

                            	
                            }
                            
                        }
                        if (caminoDespejado) {
                            movimientosValidos.add(new Tupla(y, x - 2,false)); // Enroque largo
                        }
                    }
                
                if (j.estadoDeCasilla(y, 7) == 3 && !(j.seMovio(y, 7))) {
                        // Verificar si el camino entre el rey y la torre está despejado y la torre no se ha movido
                       
            		boolean caminoDespejado = true;
            		Movimiento mov;
                	
                    for (int i = 5; i <= 6; i++) {
                        if (j.hayPieza(y, i)) {
                            caminoDespejado = false;
                            break;
                        } else {
                        	// Mirar si estaría en jaque al mover ahí
                        	
                        	mov = new Movimiento(y, x, new Tupla(y, i, false));
                        	mov.ejecutarMovimiento();
                        	
                        	if (super.enRangoDelRival()) {
                                caminoDespejado = false;
                                mov.deshacerMovimiento();
                                break;
                        	} else {
                                mov.deshacerMovimiento();	
                        	}
                        }
                    }
                    if (caminoDespejado) {
                        movimientosValidos.add(new Tupla(y, x + 2,false)); // Enroque corto
                    }
               }
                
            }

    	 
        return movimientosValidos;
    }

	public void procesarMovimiento(int f, int c) {

		// Se está aceptando el movimiento, así que se habrá movido
		
		this.seMovio = true;
	}

	public String toString() {
		return "k";
	}
}
