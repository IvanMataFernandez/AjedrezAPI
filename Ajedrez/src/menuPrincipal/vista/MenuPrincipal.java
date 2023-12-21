package menuPrincipal.vista;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import juegoBase.modelo.MainLoop;
import menuPrincipal.controlador.Accionador;

public class MenuPrincipal extends JFrame {
	
	
	private static MenuPrincipal menu;
	private JPanel panel;
	
	
	private MenuPrincipal() {}
	
	public static MenuPrincipal getMenu() {
		if (MenuPrincipal.menu == null) {
			MenuPrincipal.menu = new MenuPrincipal(); 
		}
		return MenuPrincipal.menu;
	}
	
	
	public void crearPantalla() {
		this.panel = new JPanel();
		this.panel.setLayout(null);

		super.setContentPane(this.panel);
	
		// Crear botones
		
		JLabel opt = new JLabel();
		opt.setIcon(new ImageIcon("."+File.separator+"assets"+File.separator+"menuAssets"+File.separator+"local.png"));
		opt.setBounds(266, 120, 354, 94);
		opt.addMouseListener(new Accionador("local", opt));
	    this.panel.add(opt);
	    
		opt = new JLabel();
		opt.setIcon(new ImageIcon("."+File.separator+"assets"+File.separator+"menuAssets"+File.separator+"online.png"));
		opt.setBounds(266, 220, 354, 94);
		opt.addMouseListener(new Accionador("online", opt));
	    this.panel.add(opt);
	    
		opt = new JLabel();
		opt.setIcon(new ImageIcon("."+File.separator+"assets"+File.separator+"menuAssets"+File.separator+"AI.png"));
		opt.setBounds(266, 320, 354, 94);
		opt.addMouseListener(new Accionador("AI", opt));
	    this.panel.add(opt);
	
		opt = new JLabel();
		opt.setIcon(new ImageIcon("."+File.separator+"assets"+File.separator+"menuAssets"+File.separator+"door.png"));
		opt.setBounds(266, 420, 354, 94);
		opt.addMouseListener(new Accionador("door", opt));
	    this.panel.add(opt);
	    
	    // Crear fondo
	    
	    JLabel fondo = new JLabel();
		fondo.setIcon(new ImageIcon("."+File.separator+"assets"+File.separator+"menuAssets"+File.separator+"mainMenu.png"));
		fondo.setBounds(0,0, 900, 600);
		this.panel.add(fondo);

		super.setResizable(false);
		super.setBounds(50, 50, 900, 600);
		super.setVisible(true);


		


	}

	

	
	public void cerrarVentana() {super.dispose();}
	
	public void jugarVsJugador() {MainLoop.getMainLoop().iniciarPrograma();}
	
	
	

}
