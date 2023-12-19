package juegoBase.vista;
import javax.swing.*;

import juegoBase.controlador.ControladorDeBotonDePromocion;
import juegoBase.controlador.ControladorDePromociones;

import java.awt.*;

@SuppressWarnings("serial")
public class PromocionPeonDialog extends JDialog {
    private int piezaElegida;
    private ControladorDePromociones llamadoDesde;
    
    // Tipo de pieza: 0 -> Peon, 1 -> Caballo, 2 -> Alfil, 3 -> Torre, 4 -> Reina, 5 -> Rey

    public PromocionPeonDialog(Frame parent, boolean modal, ControladorDePromociones llamadoDe) {
        super(parent, modal);
        this.llamadoDesde = llamadoDe;
        setTitle("Elige la pieza de promoción");
        setSize(300, 150);

        JPanel panel = new JPanel();
        add(panel);

        // Crear botones para las opciones de promoción
        JButton reinaButton = new JButton("Reina");
        JButton torreButton = new JButton("Torre");
        JButton caballoButton = new JButton("Caballo");
        JButton alfilButton = new JButton("Alfil");
        
        // Darles los action listeners para que procesen el click cuando se les de
        
        reinaButton.addActionListener(new ControladorDeBotonDePromocion(4, this));
        torreButton.addActionListener(new ControladorDeBotonDePromocion(3, this));
        alfilButton.addActionListener(new ControladorDeBotonDePromocion(2, this));
        caballoButton.addActionListener(new ControladorDeBotonDePromocion(1, this));
        


        // Agregar los botones al panel
        panel.add(reinaButton);
        panel.add(torreButton);
        panel.add(caballoButton);
        panel.add(alfilButton);
        super.setVisible(true);
        setLocationRelativeTo(null); // Centra en la pantalla principal
    }

    public void procesarClick(int boton) {
    	super.dispose();
    	this.llamadoDesde.promocionElegida(boton);
    }
    
    public int getPiezaElegida() {
        return piezaElegida;
    }
}
