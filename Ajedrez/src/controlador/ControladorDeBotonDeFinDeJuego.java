package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import modelo.Juego;

public class ControladorDeBotonDeFinDeJuego implements ActionListener {

	
	private int respuestaDelBoton; // 0 -> Salir | 1 -> Jugar de nuevo
	private JFrame panelResultados;
	
	public ControladorDeBotonDeFinDeJuego(int accionARealizar, JFrame pantallaResultados) {
		this.respuestaDelBoton = accionARealizar;
		this.panelResultados = pantallaResultados;
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		this.panelResultados.dispose();
		Juego.getJuego().setRespuestaTrasJuego(this.respuestaDelBoton);
		
	}

}
