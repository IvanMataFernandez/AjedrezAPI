package juegoOnline.cliente.vista;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import juegoOnline.cliente.controlador.ControladorDeBoton;

public class Lobby extends JFrame {


	
	private static Lobby s;
	private JPanel panel;
	private JLabel estadoPlayers[];
	private JLabel textoCountdown;
	private JLabel numeroCountdown;
	
	private Lobby () {}
	
	public static Lobby getLobby() {
		if (Lobby.s == null) {
			Lobby.s = new Lobby();
		}
		
		return Lobby.s;
	}
	
	public void inicializarPantalla (String ip, int playersReady) {
		
		// Crear marco
		
		super.setBounds(200, 200, 600, 300);
		this.panel = new JPanel();
		this.panel.setBounds(0, 0, 600, 400);
		this.panel.setLayout(null);

		super.setContentPane(this.panel);
		
		// Crear título
		
		JLabel lab = new JLabel("Lobby de "+ip);
		lab.setForeground(Color.white);
		Font f = new Font(Font.SANS_SERIF, Font.BOLD, 30);
	
		lab.setFont(f);

		lab.setBounds(140, 10, 600, 50);
		this.panel.add(lab);
		
		// Crear texto 
		
		lab = new JLabel("Servidor: ");
		lab.setForeground(Color.white);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		lab.setFont(f);

		lab.setBounds(180, 80, 600, 25);
		this.panel.add(lab);
		
		
		lab = new JLabel("Activo");
		lab.setForeground(Color.green);
		lab.setBounds(320, 80, 600, 25);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		lab.setFont(f);
		this.panel.add(lab);
		
		
		this.estadoPlayers = new JLabel[2];
		
		
		lab = new JLabel("Cliente 1: ");
		lab.setForeground(Color.white);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		lab.setFont(f);

		lab.setBounds(180, 120, 600, 25);
		this.panel.add(lab);
		
		
		this.estadoPlayers[0] = new JLabel("Activo");
		this.estadoPlayers[0].setForeground(Color.green);
		this.estadoPlayers[0].setBounds(320, 120, 600, 25);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
		this.estadoPlayers[0].setFont(f);
		this.panel.add(this.estadoPlayers[0]);
		
		
		lab = new JLabel("Cliente 2: ");
		lab.setForeground(Color.white);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		lab.setFont(f);

		lab.setBounds(180, 160, 600, 25);
		this.panel.add(lab);
		
		
		if (playersReady > 1) {
			
			this.estadoPlayers[1] = new JLabel("Activo");
			this.estadoPlayers[1].setForeground(Color.green);
			this.estadoPlayers[1].setBounds(320, 160, 600, 25);
			this.estadoPlayers[1].setFont(f);
			this.panel.add(this.estadoPlayers[1]);	
			
		} else {
			
			this.estadoPlayers[1] = new JLabel("En espera");
			this.estadoPlayers[1].setForeground(Color.gray);
			this.estadoPlayers[1].setBounds(320, 160, 600, 25);
			this.estadoPlayers[1].setFont(f);
			this.panel.add(this.estadoPlayers[1]);
		}
		

		

	    // Crear label texto countdown
		
		this.textoCountdown = new JLabel("Empezando en: ");
		this.textoCountdown.setForeground(Color.white);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		this.textoCountdown.setFont(f);

		this.textoCountdown.setBounds(120, 220, 600, 25);
		this.textoCountdown.setVisible(false);
		this.panel.add(this.textoCountdown);
		

	    // Crear label countdown valor
		
		this.numeroCountdown = new JLabel("X");
		this.numeroCountdown.setForeground(Color.white);
		f = new Font(Font.SANS_SERIF, Font.BOLD, 22);
	
		this.numeroCountdown.setFont(f);

		this.numeroCountdown.setBounds(300, 220, 600, 25);
		this.numeroCountdown.setVisible(false);
		this.panel.add(this.numeroCountdown);	
		
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
	
	public void marcarPlayer2ComoReady() {
		this.estadoPlayers[1].setForeground(Color.green);
		this.estadoPlayers[1].setText("Activo");
		
	}
	
	
	public void ponerCountDownEn(String valor) {
		this.numeroCountdown.setText(valor);
		this.numeroCountdown.setVisible(true);
		this.textoCountdown.setVisible(true);

	}

	public void cerrarPantalla () {super.dispose();}
	
	
	
}
