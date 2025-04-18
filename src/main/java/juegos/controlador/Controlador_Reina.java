package juegos.controlador;

import juegos.Main;
import juegos.modelos.Modelo_Reina;
import juegos.vistas.Vista_Reina;

import javax.swing.*;

public class Controlador_Reina {
    private Vista_Reina vista;
    private Modelo_Reina modelo;
    private boolean solucionesCalculadas = false; // Indica si ya se calcularon soluciones para el valor actual
    private int ultimoTamaño = -1; // Guarda la dimensión del último tablero resuelto

    // Constructor del controlador que recibe como parámetros la vista y el modelo
    public Controlador_Reina(Vista_Reina vista, Modelo_Reina modelo) {
        this.vista = vista;
        this.modelo = modelo;
        inicializarEventos(); // Asocia los eventos a los botones
    }

    // Método que inicializa todos los eventos de la vista
    private void inicializarEventos() {
        // Acción que se ejecuta al pulsar el botón "Resolver"
        vista.getBotonResolver().addActionListener(e -> {
            try {
                int n = Integer.parseInt(vista.getCampoDimension().getText()); // Obtiene la dimensión ingresada por el usuario
                if (n <= 0) throw new NumberFormatException(); // Valida que sea un número positivo

                // Si no se han calculado soluciones o se cambió el tamaño del tablero, se vuelve a resolver
                if (!solucionesCalculadas || n != ultimoTamaño) {
                    boolean exito = modelo.resolver(n); // Se intenta resolver el problema de las N Reinas

                    if (exito) {
                        // Si se encontró al menos una solución, se muestra el total y una solución aleatoria
                        vista.getEtiquetaResultado().setText("Total de soluciones posibles: " + modelo.numeroDeCombinaciones);
                        vista.mostrarTablero(modelo.obtenerSolucionAleatoria(), modelo.getDimension());

                        // Se actualiza el estado
                        solucionesCalculadas = true;
                        ultimoTamaño = n;
                    } else {
                        // Si no se encuentra solución (caso imposible), se informa al usuario
                        JOptionPane.showMessageDialog(vista, "No se encontró solución.");
                        solucionesCalculadas = false;
                    }
                }

            } catch (NumberFormatException ex) {
                // Si el usuario ingresa un valor inválido (no entero o <= 0)
                JOptionPane.showMessageDialog(vista, "Ingrese un número entero válido y mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Acción que se ejecuta al pulsar el botón "Mostrar otra solución"
        vista.getBotonMostrarOtra().addActionListener(e -> {
            // Solo se puede mostrar otra solución si ya se resolvió el problema
            if (solucionesCalculadas) {
                vista.mostrarTablero(modelo.obtenerSolucionAleatoria(), modelo.getDimension());
            }
        });

        // Acción que se ejecuta al pulsar el botón "Volver al menú principal"
        vista.getBotonVolver().addActionListener(e -> {
            vista.dispose();      // Cierra la vista actual
            Main.main(null);      // Llama al menú principal
        });

    }

} //Fin de la clase Controlador_Reina