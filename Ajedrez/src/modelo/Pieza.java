package modelo;

import java.util.ArrayList;


public abstract class Pieza {
    // Campos  para todas las piezas
    private int posX;
    private int posY;
    private boolean blancoYNoNegro;
    private int id; // Tipo de pieza: 0 -> Peon, 1 -> Caballo, 2 -> Alfil, 3 -> Torre, 4 -> Reina, 5 -> Rey
    private ArrayList<Tupla> movimientosLegales;
     
    // Constructor
    public Pieza(int posY, int posX, boolean pBando, int pTipo) {
        this.posX = posX;
        this.posY = posY;
        this.blancoYNoNegro = pBando;
        this.id = pTipo;
        ListaJugadores.getListaJugadores().obtenerJugador(this.blancoYNoNegro).añadirPieza(this);

        
    }

 
    public ArrayList<Tupla> obtenerMovimientosLegales() {
    	return this.movimientosLegales;
    }
    
    
    public int tipo() {
    	return this.id;
    }
    
    
    public boolean recalcularMovimientosLegales() {
    	
    	// Post: Tiene al menos un movimiento legal, se han calculado y almacenado los movimientos
    	
    	this.movimientosLegales = this.movimientosValidos();
    	
    	for (Tupla t: this.movimientosLegales) {
    		
    		// SUSTITUIR LA CONDICION DEL IF POR LA COMPROBACION DE TRAS HACER EL MOVIMIENTO
    		// SI EL REY DEL JUGADOR ESTA EN JAQUE
    		
    		if (false) {
    			this.movimientosLegales.remove(t);
    		}
    		
    		
    	}
    	
    	return this.movimientosLegales.size() != 0;
    }
    
    // Métodos abstractos que las demas piezas deben implementar
    public abstract ArrayList<Tupla> movimientosValidos();


    public boolean enRangoDelRival() {
    	
    	// Post: Si al rival le tocase mover, tiene un movimiento legal con el que eliminar esta pieza
    	// estadoDeCasilla: -1 (vacio) 0 (peon) 1 (caballo) 2 (alfil) 3 (torre) 4 (reina) 5 (rey)
    	
    	// Por ahora solo usar con el rey este metodo para comprobar jaques.
    	
    	Juego j = Juego.getJuego();
    	boolean bando = this.blancoYNoNegro;
    	
    	
    	// Comprobar si hay Rey en rango
    	
        int[][] rey = {
                {-1, -1}, {-1, 0}, {-1, 1}, {0, -1},
                {0, 1}, {1, -1}, {1, 0}, {1, 1}
            };

        for (int[] peligro : rey) {
            int y = this.posY + peligro[0];
            int x = this.posX + peligro[1];

            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
               if (j.estadoDeCasilla(y, x) == 5 && j.esBlanco(y, x) != bando) {
                    return true;
                }
            }
        } 
        
        // Comprobar si hay un caballo en rango
        
		
        int[][] caballo = {
                {-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},
                {1, -2}, {1, 2}, {2, -1}, {2, 1}
            };

        for (int[] peligro : caballo) {
            int y = this.posY + peligro[0];
            int x = this.posX + peligro[1];

            if (x >= 0 && x < 8 && y >= 0 && y < 8) {
               if (j.estadoDeCasilla(y, x) == 1 && j.esBlanco(y, x) != bando) {
                    return true;
                }
            }
        
        }   

    	// Comprobar si hay Peon en rango
          
        
        if (bando) {
        	
            int[][] peonNegro = {
            		{1, -1}, {1, 1}
                };        	
            
            for (int[] peligro : peonNegro) {
                int y = this.posY + peligro[0];
                int x = this.posX + peligro[1];

                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                   if (j.estadoDeCasilla(y, x) == 0 && j.esBlanco(y, x) != bando) {
                        return true;
                    }
                }
            }
            
            
        } else {
        	
            int[][] peonBlanco = {
            		{-1, -1}, {-1, 1}
                }; 
            
            for (int[] peligro : peonBlanco) {
                int y = this.posY + peligro[0];
                int x = this.posX + peligro[1];

                if (x >= 0 && x < 8 && y >= 0 && y < 8) {
                   if (j.estadoDeCasilla(y, x) == 5 && j.esBlanco(y, x) != bando) {
                        return true;
                    }
                }
            }
        }
        
        
        // Si es un peon que solo ha movido dos casilla arriba en un movimiento y su casilla de atras esta libre
        
        if (this.vulnerableAEnPassant() && ((bando && j.estadoDeCasilla(this.posY-1, this.posX) == 0) || (!bando && j.estadoDeCasilla(this.posY+1, this.posX) == 0))) {
        	if (this.posX != 0 && j.estadoDeCasilla(this.posY, this.posX-1) == 0 && j.esBlanco(this.posY, this.posX-1) != bando) {
        		return true;
        	}
        	if (this.posX != 7 && j.estadoDeCasilla(this.posY, this.posX+1) == 0 && j.esBlanco(this.posY, this.posX+1) != bando) {
        		return true;
        	}
        }
        
        
 	 
        
        // Comprobar si hay torre / reina ortogonalmente
        
	    // Rango hacia la derecha
   	 
	    for (int i = 1; this.posX + i < 8; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY, this.posX+i);
	    	
	    	if (est != -1) {
	    		if ((est == 3 || est == 4) && j.esBlanco(this.posY, this.posX+i) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }
   
	    // Rango hacia la izquierda
	   	 
	    for (int i = 1; this.posX - i >= 0; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY, this.posX-i);
	    	
	    	if (est != -1) {
	    		if ((est == 3 || est == 4) && j.esBlanco(this.posY, this.posX-i) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }
    	
	    // Rango hacia abajo
	   	 
	    for (int i = 1; this.posY + i < 8; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY+i, this.posX);
	    	
	    	if (est != -1) {
	    		if ((est == 3 || est == 4) && j.esBlanco(this.posY+i, this.posX) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }
    	

	    // Rango hacia abajo
	   	 
	    for (int i = 1; this.posY - i >= 0; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY-i, this.posX);
	    	
	    	if (est != -1) {
	    		if ((est == 3 || est == 4) && j.esBlanco(this.posY-i, this.posX) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }
    	
    	
       // Comprobar si hay alfil / reina diagonalmente
	    
	    // Rango en diagonal superior izquierda
	    
	    for (int i = 1; this.posX - i >= 0 && this.posY - i >= 0; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY-i, this.posX-i);
	    	
	    	if (est != -1) {
	    		if ((est == 2 || est == 4) && j.esBlanco(this.posY-i, this.posX-i) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }
	    
	    
	    // Rango en diagonal superior derecha
	    
	    for (int i = 1; this.posX + i < 8 && this.posY - i >= 0; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY-i, this.posX+i);
	    	
	    	if (est != -1) {
	    		if ((est == 2 || est == 4) && j.esBlanco(this.posY-i, this.posX+i) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }

	    
	    // Rango en diagonal inferior izquierda
	    
	    for (int i = 1; this.posX - i >= 0 && this.posY + i < 8; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY+i, this.posX-i);
	    	
	    	if (est != -1) {
	    		if ((est == 2 || est == 4) && j.esBlanco(this.posY+i, this.posX-i) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }
	    
	    // Rango en diagonal inferior derecha
	    
	    for (int i = 1; this.posX + i < 8 && this.posY + i < 8; i++) {
	    	
	    	int est = j.estadoDeCasilla(this.posY+i, this.posX+i);
	    	
	    	if (est != -1) {
	    		if ((est == 2 || est == 4) && j.esBlanco(this.posY+i, this.posX+i) != bando) {
	    			return true;
	    		} else {
	    			break;
	    		}
	    	}
	    }
    	
	    // Si a pesar de todo esto no esta en peligro, entonces no lo esta
    	
    	return false; 
    	
    }
    
    
    
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
    
    public boolean vulnerableAEnPassant() {
    	return false;
    }    
    /*
    public void setColor(String color) {
        this.color = color;
    } 
    Las piezas no cambian de bando tras ser creadas, no deberia ser necesario este
    
    */
    

}




/*		
 * 
 * 
 * 	Codigo antiguo de checkear rey en rango

	if (this.posY != 0) {
		if (this.posX != 0) {
			if (j.estadoDeCasilla(this.posY-1, this.posX-1) == 5 && j.esBlanco(this.posY-1, this.posX-1) != bando) {
				return true;
			}
		}
		
		if (j.estadoDeCasilla(this.posY-1, this.posX) == 5 && j.esBlanco(this.posY-1, this.posX)) {
			return true;
		}
		
		if (this.posX != 7) {
			if (j.estadoDeCasilla(this.posY-1, this.posX+1) == 5 && j.esBlanco(this.posY-1, this.posX+1) != bando) {
				return true;
			}
		}
		
	}
	
	if (this.posX != 0) {
		if (j.estadoDeCasilla(this.posY, this.posX-1) == 5 && j.esBlanco(this.posY, this.posX-1) != bando) {
			return true;
		}
	}
	
	if (j.estadoDeCasilla(this.posY, this.posX) == 5 && j.esBlanco(this.posY, this.posX)) {
		return true;
	}
	
	if (this.posX != 7) {
		if (j.estadoDeCasilla(this.posY, this.posX+1) == 5 && j.esBlanco(this.posY, this.posX+1) != bando) {
			return true;
		}
	}
    	
	if (this.posY != 7) {
		if (this.posX != 0) {
			if (j.estadoDeCasilla(this.posY+1, this.posX-1) == 5 && j.esBlanco(this.posY+1, this.posX-1) != bando) {
				return true;
			}
		}
		
		if (j.estadoDeCasilla(this.posY+1, this.posX) == 5 && j.esBlanco(this.posY+1, this.posX)) {
			return true;
		}
		
		if (this.posX != 7) {
			if (j.estadoDeCasilla(this.posY+1, this.posX+1) == 5 && j.esBlanco(this.posY+1, this.posX+1) != bando) {
				return true;
			}
		}
		
	} 
	
	
	
	*/
	
