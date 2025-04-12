package juegos.Problema_Caballo.Vista;

import javax.swing.*;
import java.awt.*;

public class Vista_Caballo extends JFrame {
    private JTextField campoTamaño;
    private JTextField campoFila;
    private JTextField campoColumna;
    private JButton botonResolver;

    private Integer[][] datos;
    private int dimension;
    private JPanel panelTablero;

    public Vista_Caballo() {
        setTitle("Problema del Caballo");
        setSize(600, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        setLayout(new BorderLayout());

        JPanel panelSuperior = new JPanel(new GridLayout(4, 2, 10, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panelSuperior.add(new JLabel("Tamaño del tablero:"));
        campoTamaño = new JTextField();
        panelSuperior.add(campoTamaño);

        panelSuperior.add(new JLabel("Fila inicial:"));
        campoFila = new JTextField();
        panelSuperior.add(campoFila);

        panelSuperior.add(new JLabel("Columna inicial:"));
        campoColumna = new JTextField();
        panelSuperior.add(campoColumna);

        botonResolver = new JButton("Resolver");
        panelSuperior.add(botonResolver);
        panelSuperior.add(new JLabel("")); // espacio vacío

        add(panelSuperior, BorderLayout.NORTH);

        panelTablero = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                if (datos == null || dimension == 0) return;

                int ancho = getWidth() / dimension;
                int alto = getHeight() / dimension;

                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        if ((i + j) % 2 == 0) {
                            g.setColor(Color.WHITE);
                        } else {
                            g.setColor(Color.LIGHT_GRAY);
                        }
                        g.fillRect(j * ancho, i * alto, ancho, alto);

                        if (datos[i][j] != null) {
                            g.setColor(Color.BLUE);
                            g.setFont(new Font("Monospaced", Font.BOLD, alto / 2));
                            String texto = datos[i][j].toString();
                            FontMetrics fm = g.getFontMetrics();
                            int x = j * ancho + (ancho - fm.stringWidth(texto)) / 2;
                            int y = i * alto + (alto + fm.getAscent()) / 2 - 5;
                            g.drawString(texto, x, y);
                        }
                    }
                }
            }
        };

        panelTablero.setPreferredSize(new Dimension(600, 550));
        add(panelTablero, BorderLayout.CENTER);
    }

    public JTextField getCampoTamaño() { return campoTamaño; }
    public JTextField getCampoFila() { return campoFila; }
    public JTextField getCampoColumna() { return campoColumna; }
    public JButton getBotonResolver() { return botonResolver; }

    public void actualizarTablero(Integer[][] nuevosDatos, int dimension) {
        this.datos = nuevosDatos;
        this.dimension = dimension;
        panelTablero.repaint();
    }
}