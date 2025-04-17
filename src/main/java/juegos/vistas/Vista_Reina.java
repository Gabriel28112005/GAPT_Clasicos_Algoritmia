package juegos.vistas;

import juegos.clases.Matriz;

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
        setTitle("Problema de las 'n' Reinas");     // Título de la ventana
        setSize(500, 600);             // Tamaño de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);    // Cierre automático al salir
        setLocationRelativeTo(null);                // Centrar ventana en pantalla
        inicializarComponentes();                   // Llamada al método que configura los componentes
    }

    // Método que configura todos los componentes visuales
    private void inicializarComponentes() {
        // Panel superior con diseño de rejilla (6 filas, 1 columna)
        JPanel superior = new JPanel(new GridLayout(6, 1, 5, 5));
        superior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaciado interno

        // Etiqueta + campo de entrada para el tamaño del tablero
        superior.add(new JLabel("Tamaño del tablero (n):"));
        campoDimension = new JTextField();
        superior.add(campoDimension);

        // Botón para resolver el problema
        botonResolver = new JButton("Resolver");
        superior.add(botonResolver);

        // Botón para mostrar otra solución
        botonMostrarOtra = new JButton("Mostrar otra solución");
        superior.add(botonMostrarOtra);

        // Etiqueta donde se mostrará el número total de soluciones encontradas
        etiquetaResultado = new JLabel("", JLabel.CENTER);
        superior.add(etiquetaResultado);

        // Botón para volver al menú
        botonVolver = new JButton("← Volver al menú");
        superior.add(botonVolver);

        // Agrega el panel superior a la parte superior de la ventana
        add(superior, BorderLayout.NORTH);

        // Panel central donde se dibuja el tablero
        panelTablero = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Llamada al comportamiento por defecto
                // El dibujo del tablero se realiza desde el método mostrarTablero
            }
        };
        panelTablero.setPreferredSize(new Dimension(500, 500)); // Tamaño preferido
        add(panelTablero, BorderLayout.CENTER); // Se ubica al centro del frame

    }

    // Métodos públicos que permiten acceder a los componentes desde el controlador
    public JTextField getCampoDimension() {
        return campoDimension;
    }

    public JButton getBotonResolver() {
        return botonResolver;
    }

    public JButton getBotonMostrarOtra() {
        return botonMostrarOtra;
    }

    public JButton getBotonVolver() {
        return botonVolver;
    }

    public JLabel getEtiquetaResultado() {
        return etiquetaResultado;
    }

    // Método para dibujar gráficamente el tablero con las reinas
    public void mostrarTablero(Matriz<?> datos, int dimension) {
        Graphics g = panelTablero.getGraphics(); // Obtiene el contexto gráfico del panel
        int ancho = panelTablero.getWidth() / dimension; // Calcula el ancho de cada celda
        int alto = panelTablero.getHeight() / dimension; // Calcula el alto de cada celda

        // Recorre todas las celdas del tablero
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                // Colorea las casillas de blanco y gris como en un tablero de ajedrez
                g.setColor((i + j) % 2 == 0 ? Color.WHITE : Color.LIGHT_GRAY);
                g.fillRect(j * ancho, i * alto, ancho, alto);

                // Si hay una reina en esa celda, se dibuja el número u otro marcador
                if (datos.datos[i][j] != null) {
                    g.setColor(Color.BLACK); // Color del texto
                    g.setFont(new Font("SansSerif", Font.BOLD, alto / 2)); // Tamaño proporcional al alto
                    String txt = datos.datos[i][j].toString(); // Valor textual a mostrar
                    FontMetrics fm = g.getFontMetrics(); // Métricas para centrar el texto
                    int x = j * ancho + (ancho - fm.stringWidth(txt)) / 2; // Centrado horizontal
                    int y = i * alto + (alto + fm.getAscent()) / 2 - 4;    // Centrado vertical
                    g.drawString(txt, x, y); // Dibuja el texto en la celda
                }
            }
        }
    }

} //Fin de la clase Vista_Reina