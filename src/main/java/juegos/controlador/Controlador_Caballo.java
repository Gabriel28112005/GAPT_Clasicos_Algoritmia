package juegos.controlador;

import juegos.Main;
import juegos.modelos.Modelo_Caballo;
import juegos.vistas.Vista_Caballo;
import juegos.clases.Matriz;

import javax.swing.*;

public class Controlador_Caballo {
    private Vista_Caballo vista;
    private Modelo_Caballo modelo;

    // Constructor que recibe como parámetros la vista y el modelo
    public Controlador_Caballo(Vista_Caballo vista, Modelo_Caballo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        inicializarEventos(); // Se registran los eventos de la interfaz.
    }

    // Método privado que define los eventos de los botones de la vista.
    private void inicializarEventos() {

        // Acción que se ejecuta al pulsar el botón "Resolver":
        vista.getBotonResolver().addActionListener(e -> {
            try {
                // Se obtienen y convierten los valores ingresados por el usuario.
                int n = Integer.parseInt(vista.getCampoTamaño().getText());
                int fila = Integer.parseInt(vista.getCampoFila().getText()) - 1;
                int col = Integer.parseInt(vista.getCampoColumna().getText()) - 1;

                // Verifica si la posición ingresada está dentro del tablero.
                if (fila < 0 || col < 0 || fila >= n || col >= n) {
                    JOptionPane.showMessageDialog(vista,
                            "Ingrese posiciones válidas dentro del tablero.",
                            "Error de posición", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Verifica que el tamaño del tablero sea al menos 5x5.
                if (n < 5) {
                    JOptionPane.showMessageDialog(vista,
                            "El tamaño mínimo del tablero para encontrar solución es 5x5.",
                            "Tamaño insuficiente", JOptionPane.WARNING_MESSAGE);
                    vista.actualizarTablero(null, 0);
                    return;
                }

                // Se intenta resolver el recorrido del caballo desde la posición inicial.
                boolean exito = modelo.resolver(n, fila, col);

                // Si no se encontró una solución válida.
                if (!exito) {
                    JOptionPane.showMessageDialog(vista,
                            "No hay solución para la posición inicial que has ingresado.",
                            "Sin solución", JOptionPane.WARNING_MESSAGE);
                    vista.actualizarTablero(null, 0);
                    vista.getCampoFila().setText("");
                    vista.getCampoColumna().setText("");
                    return;
                }

                // Si hay solución, se lanza un nuevo hilo para animar el recorrido paso a paso.
                new Thread(() -> {
                    Matriz<Integer> tablero = modelo.getTablero();

                    // Se recorren los pasos del caballo desde 1 hasta n*n.
                    for (int paso = 1; paso <= n * n; paso++) {
                        Integer[][] visibles = new Integer[n][n];

                        // Se construye una matriz visible con los pasos hasta el actual.
                        for (int i = 0; i < n; i++) {
                            for (int j = 0; j < n; j++) {
                                Integer valor = (Integer) tablero.datos[i][j];
                                if (valor != null && valor <= paso) {
                                    visibles[i][j] = valor;
                                }
                            }
                        }

                        // Se actualiza el tablero de la vista con los pasos visibles.
                        vista.actualizarTablero(visibles, n);

                        try {
                            Thread.sleep(200); // Pausa para animar el movimiento.
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                }).start(); // Se inicia la animación.

            } catch (NumberFormatException ex) {
                // Manejo de error si los campos no contienen números válidos.
                JOptionPane.showMessageDialog(vista,
                        "Ingrese números válidos (enteros positivos).",
                        "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción que se ejecuta al pulsar el botón "Volver al menú principal"
        vista.getBotonVolver().addActionListener(e -> {
            vista.dispose(); // Se cierra la vista actual.
            Main.main(null); // Se invoca el menú principal.
        });

    }

} // Fin de la clase Controlador_Caballo