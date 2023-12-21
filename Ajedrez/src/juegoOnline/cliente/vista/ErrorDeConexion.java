package juegoOnline.cliente.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import juegoOnline.cliente.controlador.ControladorDeBoton;

public class ErrorDeConexion extends JFrame {

	private static ErrorDeConexion s;
	private JPanel panel;

	
	private ErrorDeConexion () {}
	
	public static ErrorDeConexion getErrorPantalla() {
		if (ErrorDeConexion.s == null) {
			ErrorDeConexion.s = new ErrorDeConexion();
		}
		
		return ErrorDeConexion.s;
	}
	
	public void inicializarPantalla () {
		
		// Crear marco
		
		super.setBounds(200, 200, 500, 300);
		this.panel = new JPanel();
		this.panel.setBounds(0, 0, 300, 200);
		this.panel.setLayout(null);

		super.setContentPane(this.panel);
		
		// Crear título
		
		JLabel lab = new JLabel("Error de conexión al servidor");
		lab.setForeground(Color.white);
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		lab.setFont(f);

		lab.setBounds(80, 30, 600, 40);
		this.panel.add(lab);
		
	
		// Crear botones
		
		JButton bot = new JButton("Volver");
		bot.setBounds(150, 150, 200, 50);
		bot.addActionListener(new ControladorDeBoton(2));
	    this.panel.add(bot);

		
		// Crear fondo
		
		lab = new JLabel();
		lab.setBounds(0, 0, 600, 400);
		lab.setBackground(new Color(70, 10, 10)); 
		lab.setOpaque(true);
		
		this.panel.add(lab);
		
		super.repaint();
		
		
		super.setResizable(false);
		super.setVisible(true);
		
	}

	public void cerrarPantalla () {super.dispose();}
	
	
}
