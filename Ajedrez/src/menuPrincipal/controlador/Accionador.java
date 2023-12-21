package menuPrincipal.controlador;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import menuPrincipal.modelo.MainLoopMenuPrincipal;

public class Accionador implements MouseListener {

	
	private String opcion;
	private JLabel label;
	
	public Accionador (String opt, JLabel lab) {
		opcion = opt;
		label = lab;

	}
	
	

	
	@Override
	public void mouseClicked(MouseEvent e) {
		
		// Ejecutar la opción elegida
		
		switch (opcion) {
		case "local":
			// Jugar de forma local con otro player
			MainLoopMenuPrincipal.getMainLoop().setOpcion(1);



			break;
		case "online":
			// Jugar de forma online con otro player
			MainLoopMenuPrincipal.getMainLoop().setOpcion(2);

			break;
		case "AI":
			// Jugar contra bot
			MainLoopMenuPrincipal.getMainLoop().setOpcion(3);

			break;
			
		case "door":
			// Salir
			MainLoopMenuPrincipal.getMainLoop().setOpcion(4);

			
			
		
		}

		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// NOP
		

		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// NOP
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		// Marcar opcion del menú
		
		label.setIcon(new ImageIcon("."+File.separator+"assets"+File.separator+"menuAssets"+File.separator+opcion+"2.png"));
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
		// Desmarcar opcion del menú

		label.setIcon(new ImageIcon("."+File.separator+"assets"+File.separator+"menuAssets"+File.separator+opcion+".png"));

	}
	
}