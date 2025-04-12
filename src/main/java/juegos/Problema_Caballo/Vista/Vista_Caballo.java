package juegos.Problema_Caballo.Vista;

import javax.swing.*;
import java.awt.*;

public class Vista_Caballo extends JFrame {
    private JTextField campoTamaño;
    private JTextField campoFila;
    private JTextField campoColumna;
    private JButton botonResolver;
    private JLabel etiquetaResultado;

    public CaballoVista() {
        setTitle("Problema del Caballo");
        setSize(400, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Tamaño del tablero (n):"));
        campoTamaño = new JTextField();
        panel.add(campoTamaño);

        panel.add(new JLabel("Fila inicial:"));
        campoFila = new JTextField();
        panel.add(campoFila);

        panel.add(new JLabel("Columna inicial:"));
        campoColumna = new JTextField();
        panel.add(campoColumna);

        botonResolver = new JButton("Resolver");
        panel.add(botonResolver);

        etiquetaResultado = new JLabel("");
        panel.add(etiquetaResultado);

        add(panel);
    }

    public JTextField getCampoTamaño() {
        return campoTamaño;
    }

    public JTextField getCampoFila() {
        return campoFila;
    }

    public JTextField getCampoColumna() {
        return campoColumna;
    }

    public JButton getBotonResolver() {
        return botonResolver;
    }

    public JLabel getEtiquetaResultado() {
        return etiquetaResultado;
    }

} //Fin de la clase Vista Caballo