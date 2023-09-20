package modelo;

import controlador.*;
import vista.Pieza;
import vista.ReproductorDeAudio;

public class Main {

	
	
	public static void main (String[] args) {
		
		
		/*
		 	TODO LIST:
		 	
		 	- Computar si se puede hacer castling (En la clase del REY)
		 	- Filtrar los movimientos "legales" que dejan en jaque al rey tras ejecutarlos (en clase Pieza)
		 	- Programar WinCon / DrawCon del juego (En la clase Juego)
		 	- Programar check para promocion de peones (La clase esta por decidir donde poner)
		 	- Programar el movimiento internamente del programa (En alguna clase modelo)
		 	- Programar el movimiento en la interfaz (En alguna clase vista)
		 	
		 	
		 	
		 	
		 	
		 	Para programar movimientos en la interfaz:
		 		    Pieza p = this.tablero[7][1].quitarPieza();
	                this.tablero[5][3].ponerPieza(p);
	                
	                Mover de (7,1) -> (5,3)
		 	
		 	
		 	Para programar movimientos internamente:
		 	
		 	Crear clase Movimiento:
		 	
		 	Atributos:
		 	int f1
		 	int c1
		 	int f2
		 	int c2
		 	Pieza piezaComida = null
		 	
		 	Metodos:
		 	
		 	Movimiento(f1, c1, f2, c2)
		 	
		 	ejecutarMovimiento(Pieza[][] matriz, Pieza piezaAMover, Tupla infoDeMove) 
		 	// Pre: piezaComida == null
		 	// Post: Actualizar el movimiento, pero no definitivo y se puede deshacer 
		 	  
		    
		    # Eliminar la ficha comida (Si hubiera)
		    
		    if infoDeMove.comio:
		        if matriz[f2][c2] != null # Eliminacion por poner tu ficha en la de el/ella
		            piezaComida <-- matriz[f2][c2]
		            matriz[f2][c2] = null
		        
		        else: # Eliminacion enPassant
		           
		            if piezaAMover.esBlanco():
		                piezaComida <-- matriz[f2+1][c2] # cuando blanco, el negro eliminado una fila abajo
		                matriz[f2+1][c2] = null
		            else:
		                piezaComida <-- matriz[f2-1][c2]
		                matriz[f2+1][c2] = null
		        
		    
		    # Mover la pieza
		    
		    matriz[piezaAMover.f][piezaAMover.c] <-- null		    
		    matriz[f2][c2] <-- piezaAMover
		    piezaAMover.setFila(f2)
		    piezaAMover.setCol(c2)
		    
		    
		    deshacerMovimiento(Pieza[][] matriz)
		    // Pre: Este ha sido el ultimo movimiento ejecutado
		   
		    # Mover atras de nuevo la pieza
		    
		    matriz[f1][c1] <-- matriz[f2][c2]	    
		    matriz[f2][c2] <-- null
		    matriz[f1][c1].setFila(f1)
		    matriz[f1][c1].setCol(c1)
    
		    
		    
		    # Respawnear la ficha comida (Si hubiera)
		    
		    if piezaComida != null:
		        if not enPassant # Eliminacion por poner tu ficha en la de el/ella
		            matriz[f2][c2] <-- piezaComida
		        
		        else: # Eliminacion enPassant
                    matriz[piezaComida.f][piezaComida.c] <-- piezaComida		           




            
            
	
		        
		    		    
		    
		    
		    
		    
		    
		    
		  
		 */
		
		Dibujador dib = Dibujador.getDibujador();
		Juego j = Juego.getJuego();
		dib.inicializarPantalla();
		j.jugar();
		

		
		
		
	}
}
