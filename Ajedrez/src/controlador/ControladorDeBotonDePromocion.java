package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import vista.PromocionPeonDialog;

public class ControladorDeBotonDePromocion implements ActionListener {

	
	private int pieza;
	private PromocionPeonDialog pantalla;
	
	public ControladorDeBotonDePromocion(int piezaElegida, PromocionPeonDialog pPantalla) {
		this.pieza = piezaElegida;
		this.pantalla = pPantalla;
	}
	
	
	public void actionPerformed(ActionEvent e) {
		this.pantalla.procesarClick(this.pieza);
	}

}
