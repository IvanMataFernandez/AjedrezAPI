package juegoBase.modelo.IA;


import juegoBase.modelo.Matriz;
import juegoBase.modelo.Peon;
import juegoBase.modelo.Pieza;
import juegoBase.modelo.Tupla;

public class MovimientoIA {
	
	
	
	private int f1;
 	private int c1;
 	private int f2;
 	private int c2;
 	private boolean seVaAComer;
 	private boolean enPassant;
 	private Pieza piezaComida;
 	private Peon peonAscendido;

 	public MovimientoIA(int f1, int c1, Tupla tupla) {
		super();
		this.f1 = f1;
		this.c1 = c1;
		this.f2 = tupla.getF();
		this.c2 = tupla.getC();
		this.seVaAComer = tupla.come();
		this.peonAscendido = null;
		
	}
 	
 
 	

    public void realizarMovimiento() {
    	
    	// Ejecutar y confirmar el move por simulación de IA. Este proceso es revesible

       
    	Pieza[][] matriz = Matriz.getMatriz().getTablero();
    	
    	Pieza piezaAMover = matriz[this.f1][this.c1];
    	
        // Eliminar la ficha comida (Si hubiera)
    	
        if (this.seVaAComer) {
        	
            if (matriz[this.f2][this.c2] != null) { // Eliminacion por poner tu ficha en la de el/ella
                this.piezaComida = matriz[this.f2][this.c2];
                matriz[this.f2][this.c2] = null;
         
            } else { // Checkeo eliminacion enPassant
            	
            	this.enPassant = true;
                if (piezaAMover.pBando()) {
                    this.piezaComida = matriz[this.f2 + 1][this.c2]; // cuando blanco, el negro eliminado una fila abajo
                    matriz[this.f2 + 1][this.c2] = null;
         
                } else {
                    this.piezaComida = matriz[this.f2 - 1][this.c2];
                    matriz[this.f2 - 1][this.c2] = null;
                    
                 
                }
            }
        }

        // Mover la pieza

        matriz[piezaAMover.getPosY()][piezaAMover.getPosX()] = null;
        matriz[this.f2][this.c2] = piezaAMover;
        piezaAMover.setPosY(this.f2);
        piezaAMover.setPosX(this.c2);
        
 		
 		
        
        
        // Si el movimiento ha sido un enroque, eso implica que el
        // rey se movio al menos dos casillas horizontalmente y debemos mover
        // su torre respectiva, podemos deducir la posición de la torre
        // a mover basado en la posición donde colocaremos el rey
        // Si no te imaginas la imagen en la cabeza, consulta la primera
        // imagen de este articulo https://es.wikipedia.org/wiki/Enroque
        
        if (piezaAMover.tipo() == 5 && Math.abs(this.c1-this.c2) > 1) {
        	
        	
        	
        	int fTorre;
        	int cTorre;
        	int cTorreFin;
        	
        	// Las torre a mover está en una esquina, deducir basado en el tipo de enrroque
        	
        	if (this.c2 == 6) { // Es enroque corto, torre en la derecha del tablero
        		cTorre = 7;
        		cTorreFin = 5; // La torre se movería de col 7 a 5
        		
        	} else { // Enroque largo, torre en la izquierda del tablero
        		cTorre = 0;
        		cTorreFin = 3; // La torre se movería de col 0 a 3
        	}
        	
 
        	fTorre = this.f2; // La torre se mueve a la misma fila a donde estará el rey
        	                  // es un movimiento horizontal
        	
        	Pieza torre = matriz[fTorre][cTorre];
            matriz[fTorre][cTorre] = null;
            matriz[fTorre][cTorreFin] = torre;
            
            torre.setPosY(fTorre); // Paso redundante ya que no se mueve de fila pero lo dejo por claridad
            torre.setPosX(cTorreFin);
            
            
            

        }
     		

       	// Comprobar si hay ascensos de peón, si los hay, guardar el peón para cuando se vaya a deshacer esto
        // y poner una reina temporal en esa posición en su lugar
        
        if ((this.f2 == 0 || this.f2 == 7) && matriz[this.f2][this.c2].tipo() == 0) { 
        	this.peonAscendido = (Peon) matriz[this.f1][this.c1];
        	Matriz.getMatriz().eliminarPieza(f2, c2);
        	Matriz.getMatriz().añadirPieza(f2, c2, this.peonAscendido.pBando(), 4);

       }
        
        // Actualizar flags de rey/torre/peon
        
        Matriz.getMatriz().procesarMovimientoEnPieza(f1, c1, f2, c2);
        
        
        // Eliminar la pieza comida
        
       	if (this.piezaComida != null) {
    		this.piezaComida.eliminarseDeListaDeJugador();
    	}
       
        
    }
    public void deshacerMovimiento(Pieza[][] matriz) {


    	// Revertir el move por simulación de IA.

    	// Actualizar flags de rey/torre/peon
    	
        Matriz.getMatriz().antiProcesarMovimientoEnPieza(f2, c2, f1, c1);

    	
        // Mover atrás de nuevo la pieza, si dio la casualidad de que fue una promoción,
    	// cambiar la reina por el peón que se había almacenado anteriormente
    	
    	if (this.peonAscendido != null) {
    		
        	Matriz.getMatriz().eliminarPieza(f2, c2);
        	Matriz.getMatriz().añadirPieza(f2, c2, this.peonAscendido);
    	}
    	
    	// Mover la pieza hacia atrás
    	
        matriz[this.f1][this.c1] = matriz[this.f2][this.c2];    		    	
        matriz[this.f2][this.c2] = null;
        matriz[this.f1][this.c1].setPosY(this.f1);
        matriz[this.f1][this.c1].setPosX(this.c1);

        // Respawnear la ficha comida (Si hubiera) 
        
        if (this.piezaComida != null) {
            if (!this.enPassant) { // Eliminación por poner tu ficha en la de él/ella
                matriz[this.f2][this.c2] = this.piezaComida;
            } else { // Eliminación enPassant
                matriz[this.piezaComida.getPosY()][this.piezaComida.getPosX()] = this.piezaComida;
            }
            
         // Si no se comio es posible que haya que deshacer un enrroque, en cuyo caso
         // habrá que mover la torre de nuevo
            
        } else if ( matriz[this.f1][this.c1].tipo() == 5 && Math.abs(this.c1-this.c2) > 1) {
        	
        	int cTorre;
        	int fTorre;
        	int cTorreFin;
        	
        	// Las torre a esta en: (0,3) (0,5) (7,0) (7,5)
        	
        	if (this.c2 == 6) { // Era enroque corto, torre en la derecha del tablero
        		cTorre = 5;
        		cTorreFin = 7; // La torre se movío de col 7 a 5
        		
        	} else { // Era enroque largo, torre en la izquierda del tablero
        		cTorre = 3;
        		cTorreFin = 0; // La torre se movió de col 0 a 3
        	}
        	fTorre = this.f2; // La torre se movió a la misma fila a donde estaba el rey
                              // es un movimiento horizontal

        	Pieza torre = matriz[fTorre][cTorre];
            matriz[fTorre][cTorre] = null;
            matriz[fTorre][cTorreFin] = torre;
            
            torre.setPosY(fTorre); // Paso redundante ya que no se mueve de fila pero lo dejo por claridad
            torre.setPosX(cTorreFin);
        }
        

        
		//matriz[this.f2][this.c2].procesarMovimiento(this.f1, this.c1);
		
       	if (this.piezaComida != null) {
    		this.piezaComida.ponerseEnListaDeJugador();
    	}
		
    }
    

    
    public String toString() {
    	return "("+f1+","+c1+") --> ("+f2+","+c2+")";
    }
    
    
 /*   
    private Pieza abrirVentanaSeleccionPromocion(int fila, int columna,boolean esBlanco) {
    	
    	 PromocionPeonDialog dialog = new PromocionPeonDialog(null, true);
    	    dialog.setVisible(true);
    	    // Obtiene la elección del jugador desde la ventana de selección de promoción
    	    int piezaElegida = dialog.getPiezaElegida();
    	    // Crea la nueva pieza según la elección del jugador
    	    Pieza nuevaPieza = null;
    	    switch (piezaElegida) {
    	        case 0: // Peón (valor que representa un peón en tu lógica)
    	            nuevaPieza = new Peon(fila,columna,esBlanco);
    	            break;
    	        case 1: // Caballo
    	            nuevaPieza = new Caballo(fila,columna,esBlanco);
    	            break;
    	        case 2: // Alfil
    	            nuevaPieza = new Alfil(fila,columna,esBlanco);
    	            break;
    	        case 3: // Torre
    	            nuevaPieza = new Torre(fila,columna,esBlanco);
    	            break;
    	        case 4: // Reina
    	            nuevaPieza = new Reina(fila,columna,esBlanco,4);
    	            break;
    	        case 5: // Rey
    	            nuevaPieza = new Rey(fila,columna,esBlanco);
    	            break;
    	        default:
    	            // El jugador cerró la ventana de selección de promoción o hizo clic fuera de las opciones
    	            break;
    	    }

    	    return nuevaPieza;
    	} */
    


}