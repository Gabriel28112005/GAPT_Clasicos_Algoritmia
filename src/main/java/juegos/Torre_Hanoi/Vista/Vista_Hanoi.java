package juegos.Torre_Hanoi.Vista;

import javax.swing.*;
import java.awt.*;


public class Vista_Hanoi  extends JFrame{
    private JTextField campoDiscos;
    private JButton botonIniciar;
    private JLabel etiquetaMovimientos;

    public Vista_Hanoi() {
        setTitle("Torre de Hanoi");
        setSize(350, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(4, 1, 5, 5));

        JLabel etiqueta = new JLabel("Cantidad de discos:");
        campoDiscos = new JTextField();
        botonIniciar = new JButton("Iniciar Juego");
        etiquetaMovimientos = new JLabel(" ");

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(etiqueta);
        panel.add(campoDiscos);
        panel.add(botonIniciar);
        panel.add(etiquetaMovimientos);

        add(panel);
    }

    public JTextField getCampoDiscos() {
        return campoDiscos;
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JLabel getEtiquetaMovimientos() {
        return etiquetaMovimientos;
    }

}