package vista;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.Reina;

public class PromocionPeonDialog extends JDialog {
    private int piezaElegida;
    
 // Tipo de pieza: 0 -> Peon, 1 -> Caballo, 2 -> Alfil, 3 -> Torre, 4 -> Reina, 5 -> Rey

    public PromocionPeonDialog(Frame parent, boolean modal) {
        super(parent, modal);
        setTitle("Elige la pieza de promoción");
        setSize(300, 150);

        JPanel panel = new JPanel();
        add(panel);

        // Crear botones para las opciones de promoción
        JButton reinaButton = new JButton("Reina");
        JButton torreButton = new JButton("Torre");
        JButton caballoButton = new JButton("Caballo");
        JButton alfilButton = new JButton("Alfil");

        // Agregar un ActionListener a cada botón para manejar la elección
        reinaButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                piezaElegida = 4;
                dispose(); // Cierra la ventana
            }
        });

        torreButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                piezaElegida = 3;
                dispose(); // Cierra la ventana
            }
        });

        caballoButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                piezaElegida =1;
                dispose(); // Cierra la ventana
            }
        });

        alfilButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                piezaElegida = 2;
                dispose(); // Cierra la ventana
            }
        });

        // Agregar los botones al panel
        panel.add(reinaButton);
        panel.add(torreButton);
        panel.add(caballoButton);
        panel.add(alfilButton);

        setLocationRelativeTo(parent);
    }

    public int getPiezaElegida() {
        return piezaElegida;
    }
}
