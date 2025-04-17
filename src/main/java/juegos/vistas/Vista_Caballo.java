package juegos.vistas;

import javax.swing.*;
import java.awt.*;

public class Vista_Caballo extends JFrame {

    // Componentes de entrada de datos del usuario:
    private JTextField campoTamaño;   // Campo para ingresar el tamaño del tablero
    private JTextField campoFila;     // Campo para ingresar la fila inicial
    private JTextField campoColumna;  // Campo para ingresar la columna inicial

    // Botones:
    private JButton botonResolver;    // Botón para iniciar el recorrido del caballo
    private JButton botonVolver;      // Botón para volver al menú principal

    // Variables para manejar los datos y el tablero:
    private Integer[][] datos;        // Matriz que contiene los pasos del caballo
    private int dimension;            // Tamaño del tablero
    private JPanel panelTablero;      // Panel donde se dibuja visualmente el tablero

    // Constructor que inicializa la ventana
    public Vista_Caballo() {
        setTitle("Problema del Caballo");         // Título de la ventana
        setSize(600, 700);                        // Dimensiones de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);  // Finaliza la aplicación al cerrar
        setLocationRelativeTo(null);              // Centra la ventana en la pantalla
        inicializarComponentes();                 // Método que arma la interfaz
    }

    // Método para crear y organizar todos los componentes gráficos
    private void inicializarComponentes() {
        setLayout(new BorderLayout()); // Se establece el diseño principal de la ventana

        // Panel superior con los campos de entrada y botones
        JPanel panelSuperior = new JPanel(new GridLayout(5, 2, 10, 10));
        panelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Etiqueta y campo para el tamaño del tablero
        panelSuperior.add(new JLabel("Tamaño del tablero:"));
        campoTamaño = new JTextField();
        panelSuperior.add(campoTamaño);

        // Etiqueta y campo para la fila inicial
        panelSuperior.add(new JLabel("Fila inicial:"));
        campoFila = new JTextField();
        panelSuperior.add(campoFila);

        // Etiqueta y campo para la columna inicial
        panelSuperior.add(new JLabel("Columna inicial:"));
        campoColumna = new JTextField();
        panelSuperior.add(campoColumna);

        // Botón para resolver el problema
        botonResolver = new JButton("Resolver");
        panelSuperior.add(botonResolver);

        // Botón para volver al menú principal
        botonVolver = new JButton("← Volver al menú");
        panelSuperior.add(botonVolver);

        // Se agrega el panel superior a la parte superior de la ventana
        add(panelSuperior, BorderLayout.NORTH);

        // Panel donde se dibuja el tablero y el recorrido del caballo
        panelTablero = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Llama a la versión original del método para mantener compatibilidad

                if (datos == null || dimension == 0){
                    return;  // Si no hay datos para mostrar o dimensión cero, no se dibuja el tablero
                }

                // Calcula el tamaño de cada celda del tablero:
                int ancho = getWidth() / dimension;
                int alto = getHeight() / dimension;

                // Dibuja las casillas del tablero (blancas y grises en patrón de ajedrez)
                for (int i = 0; i < dimension; i++) {
                    for (int j = 0; j < dimension; j++) {
                        if ((i + j) % 2 == 0) {
                            g.setColor(Color.WHITE);
                        } else {
                            g.setColor(Color.LIGHT_GRAY);
                        }
                        g.fillRect(j * ancho, i * alto, ancho, alto);

                        // Si hay un número (paso del caballo), lo dibuja en azul en el centro de la casilla
                        if (datos[i][j] != null) {
                            g.setColor(Color.BLUE);
                            g.setFont(new Font("Monospaced", Font.BOLD, alto / 2)); // Tamaño dinámico del texto
                            String texto = datos[i][j].toString(); // Número a dibujar
                            FontMetrics fm = g.getFontMetrics(); // Para centrar el texto
                            int x = j * ancho + (ancho - fm.stringWidth(texto)) / 2;
                            int y = i * alto + (alto + fm.getAscent()) / 2 - 5;
                            g.drawString(texto, x, y); // Dibuja el número en la casilla
                        }
                    }
                }
            }
        };

        // Define el tamaño preferido del área del tablero
        panelTablero.setPreferredSize(new Dimension(600, 550));

        // Se agrega el panelTablero al centro de la ventana
        add(panelTablero, BorderLayout.CENTER);
    }

    // Métodos públicos para que el controlador acceda a los componentes:
    public JTextField getCampoTamaño() { return campoTamaño; }
    public JTextField getCampoFila() { return campoFila; }
    public JTextField getCampoColumna() { return campoColumna; }
    public JButton getBotonResolver() { return botonResolver; }
    public JButton getBotonVolver() { return botonVolver; }

    // Método que actualiza los datos a mostrar y redibuja el tablero:
    public void actualizarTablero(Integer[][] nuevosDatos, int dimension) {
        this.datos = nuevosDatos;       // Guarda los nuevos pasos del caballo
        this.dimension = dimension;     // Guarda el nuevo tamaño del tablero
        panelTablero.repaint();         // Llama a paintComponent() para redibujar
    }

} //Fin de la clase Vista_Caballo