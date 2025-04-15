package juegos.Torre_Hanoi.Vista;

import javax.swing.*;
import java.awt.*;
import juegos.Matriz;
import juegos.Figura;
import juegos.Torre_Hanoi.Disco;

public class Vista_Hanoi extends JFrame {
    private JTextField campoDiscos;
    private JButton botonIniciar;
    private JButton botonVolver;
    private JLabel etiquetaMovimientos;
    private JPanel panelVisual;

    public Vista_Hanoi() {
        setTitle("Torre de Hanoi");
        setSize(500, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarComponentes();
    }

    private void inicializarComponentes() {
        JPanel panel = new JPanel(new GridLayout(5, 1, 5, 5));

        JLabel etiqueta = new JLabel("Cantidad de discos:");
        campoDiscos = new JTextField();
        botonIniciar = new JButton("Iniciar Juego");
        etiquetaMovimientos = new JLabel(" ");
        botonVolver = new JButton("← Volver al menú");

        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.add(etiqueta);
        panel.add(campoDiscos);
        panel.add(botonIniciar);
        panel.add(etiquetaMovimientos);
        panel.add(botonVolver);

        add(panel, BorderLayout.NORTH);

        panelVisual = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        panelVisual.setPreferredSize(new Dimension(500, 350));
        add(panelVisual, BorderLayout.CENTER);
    }

    public JTextField getCampoDiscos() {
        return campoDiscos;
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public JLabel getEtiquetaMovimientos() {
        return etiquetaMovimientos;
    }

    public void mostrarEstado(Matriz<Figura> matriz) {
        Graphics g = panelVisual.getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        int ancho = panelVisual.getWidth();
        int alto = panelVisual.getHeight();
        g.clearRect(0, 0, ancho, alto);

        int columnas = 3;
        int filas = matriz.filas;
        int torreAncho = ancho / columnas;
        int discoAltura = alto / (filas + 3);

        // Dibujar torres
        g.setColor(new Color(70, 70, 70));
        for (int c = 0; c < columnas; c++) {
            int paloX = c * torreAncho + torreAncho / 2 - 5;
            g.fillRect(paloX, alto / 6, 10, alto - alto / 4);
        }

        // Dibujar base
        for (int c = 0; c < columnas; c++) {
            int baseX = c * torreAncho + 10;
            int baseY = alto - 40;
            g.setColor(new Color(90, 90, 90));
            g.fillRoundRect(baseX, baseY, torreAncho - 20, 15, 10, 10);
        }

        // Dibujar discos desde la base hacia arriba según su nivel real
        for (int c = 0; c < columnas; c++) {
            int nivel = 0;
            for (int f = filas - 1; f >= 0; f--) {
                Figura figura = (Figura) matriz.datos[f][c];
                if (figura instanceof Disco disco) {
                    int tamaño = disco.getTamaño();
                    int discoAncho = tamaño * (torreAncho / filas);

                    int x = c * torreAncho + (torreAncho - discoAncho) / 2;
                    int y = alto - 40 - (nivel + 1) * discoAltura;

                    g2d.setColor(disco.getColor());
                    g2d.fillRoundRect(x, y, discoAncho, discoAltura - 4, 20, 20);
                    g2d.setColor(Color.DARK_GRAY);
                    g2d.setStroke(new BasicStroke(2));
                    g2d.drawRoundRect(x, y, discoAncho, discoAltura - 4, 20, 20);

                    nivel++;
                }
            }
        }
    }
}