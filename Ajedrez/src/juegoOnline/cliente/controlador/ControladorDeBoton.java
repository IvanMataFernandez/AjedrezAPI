package juegoOnline.cliente.controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import juegoOnline.cliente.modelo.Cliente;
import juegoOnline.cliente.vista.SelectorDeServidor;

public class ControladorDeBoton implements ActionListener {

	
	private int id; // 0 -> Boton de confirmar IP, 1 -> Boton de volver, 2 -> Confirmar error de conexión
	
	public ControladorDeBoton (int id) {
		this.id = id;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// Se acciona el botón
		
		switch (this.id) {
		case 0:
			// Se busca el servidor
			Cliente.getCliente().setIP(SelectorDeServidor.getSelector().obtenerIPIntroducida());

			break;
			// Se vuelve atrás

		case 1:
			Cliente.getCliente().setIP(null);

			break;
		
		case 2:
			// Confirmar error de conexión
			
	
			Cliente.getCliente().setIP("0.0.0.0");


			break;
			

			
			
		}
	

	}

}
