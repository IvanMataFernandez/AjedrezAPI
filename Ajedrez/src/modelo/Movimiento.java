package modelo;
import java.util.ArrayList;

import controlador.ComandoAInterfazAñadirPieza;
import controlador.ComandoAInterfazBorrarPieza;
import controlador.ComandoAInterfazMoverPieza;
import controlador.ControladorDePromociones;

// import vista.PromocionPeonDialog;
public class Movimiento {
	
	private int f1;
 	private int c1;
 	private int f2;
 	private int c2;
 	private boolean seVaAComer;
 	private boolean enPassant;
 	private Pieza piezaComida;
 	private ArrayList<ComandoAInterfazBorrarPieza> comandos;
		// El AL es de tipo borrar porque la funcion borrar hereda a las demas, el AL 
		// soporta cualquier tipo de Comando A Interfaz

 	public Movimiento(int f1, int c1, Tupla tupla) {
		super();
		this.f1 = f1;
		this.c1 = c1;
		this.f2 = tupla.getF();
		this.c2 = tupla.getC();
		this.seVaAComer = tupla.come();
		this.comandos = new ArrayList<ComandoAInterfazBorrarPieza>();
		
		
	}
 	
 	public ArrayList<ComandoAInterfazBorrarPieza> informarPantalla() { 		
 		// Post: Le devuelve a la pantalla las acciones que debe realizar
 		
 		return this.comandos;

 	}
 	

    public void ejecutarMovimiento() {
        // Pre: piezaComida == null
        // Post: Actualizar el movimiento, pero no definitivo y se puede deshacer
       
    	Pieza[][] matriz = Juego.getJuego().getTablero();
    	
    	
    	Pieza piezaAMover = matriz[this.f1][this.c1];
    	
        // Eliminar la ficha comida (Si hubiera)
        if (this.seVaAComer) {
        	
            if (matriz[this.f2][this.c2] != null) { // Eliminacion por poner tu ficha en la de el/ella
                this.piezaComida = matriz[this.f2][this.c2];
                matriz[this.f2][this.c2] = null;
         
            } else { // Eliminacion enPassant
            	
            	this.enPassant = true;
                if (piezaAMover.pBando()) {
                    this.piezaComida = matriz[this.f2 + 1][this.c2]; // cuando blanco, el negro eliminado una fila abajo
                    matriz[this.f2 + 1][this.c2] = null;
                    
                    // Guardar el hecho que debemos borrar ese peon sin remplazar pieza en la lista de comandos
                    // para la interfaz
                    
             		this.comandos.add(new ComandoAInterfazBorrarPieza(this.f2 + 1, this.c2));
                } else {
                    this.piezaComida = matriz[this.f2 - 1][this.c2];
                    matriz[this.f2 - 1][this.c2] = null;
                    
                    // Guardar el hecho que debemos borrar ese peon sin remplazar pieza en la lista de comandos
                    // para la interfaz
                    
             		this.comandos.add(new ComandoAInterfazBorrarPieza(this.f2 - 1, this.c2));
                    
                }
            }
        }

        // Mover la pieza
        System.out.println(this.f1+","+this.c1+" --> "+this.f2+","+this.c2);

        matriz[piezaAMover.getPosY()][piezaAMover.getPosX()] = null;
        matriz[this.f2][this.c2] = piezaAMover;
        piezaAMover.setPosY(this.f2);
        piezaAMover.setPosX(this.c2);
        
 		
 		// Almacenar que casillas de deberían editar por el movimiento en la interfaz para uso futuro
 		
        
 		this.comandos.add(new ComandoAInterfazMoverPieza(this.f1, this.c1, this.f2, this.c2));
        
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
            
            
            // También añadir a la lista de comandos para la interfaz la de mover la torre de sitio
            
     		this.comandos.add(new ComandoAInterfazMoverPieza(fTorre, cTorre, fTorre, cTorreFin));

        }
     		// Por ultimo, comprobar si se deberia realizar una promocion de peon
     		

       	 
        if ((this.f2 == 0 || this.f2 == 7) && matriz[this.f2][this.c2].tipo() == 0) { // si hay algun peon en su ultima fila, se debe bufferear la instruccion de ascenderlo para despues
       		// Añadir la instruccion de ascender para después, si el peon está en la fila 0 es blanco, si no, negro
        	this.comandos.add(new ComandoAInterfazAñadirPieza(this.f2, this.c2, this.f2==0));
       		 
       		 

       }
        
        
        
    }
    public void deshacerMovimiento() {
        // Pre: Este ha sido el último movimiento ejecutado

    	Pieza[][] matriz = Juego.getJuego().getTablero();

        System.out.println(this.f1+","+this.c1+" <-- "+this.f2+","+this.c2);

    	
    	
        // Mover atrás de nuevo la pieza
    	
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
        
        
        
    }
    
    public void confirmarMovimiento() {
    	// Pre: Movimiento ejecutado y no confirmado o revertido
    	// Post: Se ha confirmado el movimiento y ya no se puede deshacer, se han
    	//       eliminado las fichas comidas de la lista de su jugador
    	
    	Juego j = Juego.getJuego();
    	// Actualizar flags de pieza
    	j.procesarMovimientoEnPieza(this.f1, this.c1, this.f2, this.c2);
    	
    	// Eliminar de la lista de piezas del jugador la pieza comida
    	if (this.piezaComida != null) {
    		this.piezaComida.eliminarseDeListaDeJugador();
    	}
    	
    	// Manejar promociones de peon
    	
    	for (ComandoAInterfazBorrarPieza comando : this.comandos) {
    		if (comando instanceof ComandoAInterfazAñadirPieza) {
    			
    			// Crear nueva UI y esperar al input del usuario
    			
    			ControladorDePromociones con = new ControladorDePromociones();
    			int tipo = con.elegirPromocion();
    			
    			// Actualizar la instrucción para mostrar en interfaz la pieza correcta
    			ComandoAInterfazAñadirPieza comandoAñadir = (ComandoAInterfazAñadirPieza) comando;
    			comandoAñadir.setTipo(tipo);
    			
    			// Actualizar la pieza
    			
    			j.eliminarPieza(comandoAñadir.getF(), comandoAñadir.getC());
    			j.añadirPieza(comandoAñadir.getF(), comandoAñadir.getC(), comandoAñadir.esBlanco(), tipo);
    			
    		}

       }
    	/*
    	 
    	 
    	
    	 
    	  
    	  
    	 
    	
    	 Pieza[][] matriz = Juego.getJuego().getTablero();

    	 boolean esBlanco = false; // Variable para determinar el color del peón
    	 
    	 if ((this.f2 == 0 || this.f2 == 7) && matriz[this.f2][this.c2].tipo() == 0) { // si hay algun peon en algunos de los extremos...
    		 esBlanco = (this.f2 == 0); // Si f2 es 0, el peón es blanco; de lo contrario, es negro
    		
    		 
    		 
    		 
    		 Pieza nuevaPiezaElegida = abrirVentanaSeleccionPromocion(this.f2, this.c2,esBlanco);
    	        // Asigna la nueva pieza elegida como piezaMovida
    	        if (nuevaPiezaElegida != null) {
    	        	 Juego.getJuego().getTablero()[this.f2][this.c2]=nuevaPiezaElegida;
    	        } 
    	 } */
    	
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