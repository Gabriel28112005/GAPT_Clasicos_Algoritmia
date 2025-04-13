package juegos.N_Reinas.Vista;

import javax.swing.*;
import java.awt.*;

public class Vista_Reina extends JFrame {
    private JTextField campoDimension;
    private JButton botonResolver;
    private JButton botonMostrarOtra;
    private JButton botonVolver;
    private JLabel etiquetaResultado;
    private JPanel panelTablero;

    public Vista_Reina() {
        setTitle("Problema de las N Reinas");
        setSize(500, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel superior = new JPanel(new GridLayout(6, 1, 5, 5));
        superior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        superior.add(new JLabel("Tamaño del tablero (n):"));
        campoDimension = new JTextField();
        superior.add(campoDimension);

        botonResolver = new JButton("Resolver");
        superior.add(botonResolver);

        botonMostrarOtra = new JButton("Mostrar otra solución");
        superior.add(botonMostrarOtra);

        etiquetaResultado = new JLabel("", JLabel.CENTER);
        superior.add(etiquetaResultado);

        botonVolver = new JButton("← Volver al menú");
        superior.add(botonVolver);

        add(superior, BorderLayout.NORTH);

        panelTablero = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        panelTablero.setPreferredSize(new Dimension(500, 500));
        add(panelTablero, BorderLayout.CENTER);
    }

    public JTextField getCampoDimension() { return campoDimension; }
    public JButton getBotonResolver() { return botonResolver; }
    public JButton getBotonMostrarOtra() { return botonMostrarOtra; }
    public JButton getBotonVolver() { return botonVolver; }
    public JLabel getEtiquetaResultado() { return etiquetaResultado; }

    public void mostrarTablero(juegos.Matriz<?> datos, int dimension) {
        Graphics g = panelTablero.getGraphics();
        int ancho = panelTablero.getWidth() / dimension;
        int alto = panelTablero.getHeight() / dimension;

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                g.setColor((i + j) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                g.fillRect(j * ancho, i * alto, ancho, alto);

                if (datos.datos[i][j] != null) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("SansSerif", Font.BOLD, alto / 2));
                    String txt = datos.datos[i][j].toString();
                    FontMetrics fm = g.getFontMetrics();
                    int x = j * ancho + (ancho - fm.stringWidth(txt)) / 2;
                    int y = i * alto + (alto + fm.getAscent()) / 2 - 4;
                    g.drawString(txt, x, y);
                }
            }
        }
    }
}