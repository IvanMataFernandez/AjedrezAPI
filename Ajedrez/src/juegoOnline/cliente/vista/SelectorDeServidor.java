package juegoOnline.cliente.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import juegoOnline.cliente.controlador.ControladorDeBoton;


public class SelectorDeServidor extends JFrame {
	
	
	private static SelectorDeServidor s;
	private JPanel panel;
	private JTextArea area;
	
	private SelectorDeServidor () {}
	
	public static SelectorDeServidor getSelector() {
		if (SelectorDeServidor.s == null) {
			SelectorDeServidor.s = new SelectorDeServidor();
		}
		
		return SelectorDeServidor.s;
	}
	
	public void inicializarPantalla () {
		
		// Crear marco
		
		super.setBounds(200, 200, 600, 400);
		this.panel = new JPanel();
		this.panel.setBounds(0, 0, 600, 400);
		this.panel.setLayout(null);

		super.setContentPane(this.panel);
		
		// Crear título
		
		JLabel lab = new JLabel("Introduce la dirección IP del servidor para conectar");
		lab.setForeground(Color.white);
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		lab.setFont(f);

		lab.setBounds(30, 30, 600, 20);
		this.panel.add(lab);
		
		// Crear texto al lado del cuadro de texto
		
		lab = new JLabel("IP del servidor: ");
		lab.setForeground(Color.white);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		lab.setFont(f);

		lab.setBounds(100, 100, 600, 20);
		this.panel.add(lab);
		
		// Crear cuadro de texto
		
		this.area = new JTextArea();
		this.area.setBounds(320, 100, 110, 25);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 15);
		this.area.setFont(f);
		this.panel.add(this.area);
		
		
		
		// Crear botones
		
		JButton bot = new JButton("Conectar");
		bot.setBounds(200, 200, 200, 50);
		bot.addActionListener(new ControladorDeBoton(0));
	    this.panel.add(bot);
	    
	    bot = new JButton("Atrás");
		bot.setBounds(200, 270, 200, 50);
		bot.addActionListener(new ControladorDeBoton(1));
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
	
	public String obtenerIPIntroducida () {
		return this.area.getText();	
	}
	
	public void cerrarPantalla () {super.dispose();}
	
	
	

}
