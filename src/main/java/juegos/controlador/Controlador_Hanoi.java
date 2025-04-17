package juegos.controlador;

import juegos.Main;
import juegos.vistas.Vista_Hanoi;
import juegos.modelos.Modelo_Hanoi;

import javax.swing.*;

public class Controlador_Hanoi {
    private Vista_Hanoi vista;
    private Modelo_Hanoi modelo;

    // Constructor del controlador que recibe la vista y el modelo y registra los eventos
    public Controlador_Hanoi(Vista_Hanoi vista, Modelo_Hanoi modelo) {
        this.vista = vista;
        this.modelo = modelo;
        iniciarEventos(); // Se inicializan los eventos de la interfaz
    }

    // Método privado que asocia los eventos a los botones de la vista
    private void iniciarEventos() {
        // Evento para el botón "Iniciar"
        vista.getBotonIniciar().addActionListener(e -> {
            String texto = vista.getCampoDiscos().getText(); // Se obtiene el texto del campo de número de discos

            try {
                int numDiscos = Integer.parseInt(texto); // Se convierte el texto a entero

                // Verifica que se haya ingresado un número positivo de discos
                if (numDiscos > 0) {
                    modelo.setVista(vista); // Se asocia la vista al modelo para permitirle actualizarla

                    // ✅ Se inicia el algoritmo en un hilo separado para evitar que se congele la interfaz
                    new Thread(() -> {
                        modelo.solve(numDiscos); // Se ejecuta el algoritmo de resolución

                        // Luego de terminar, se actualiza la etiqueta con el número de movimientos usando el hilo de Swing
                        SwingUtilities.invokeLater(() -> {
                            vista.getEtiquetaMovimientos().setText(
                                    "Mínimo de movimientos: " + modelo.numeroDeMovimientos
                            );
                        });
                    }).start();

                } else {
                    // Muestra un mensaje si se ingresó un número inválido (cero o negativo)
                    JOptionPane.showMessageDialog(vista,
                            "Debe ingresar un número mayor que 0.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException error) {
                // Captura errores si el texto no es un número válido
                JOptionPane.showMessageDialog(vista,
                        "Ingrese un número válido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Evento para el botón "Volver al menú principal"
        vista.getBotonVolver().addActionListener(e -> {
            vista.dispose();     // Cierra la vista actual
            Main.main(null);     // Llama al método principal para volver al menú
        });

    }

} //Fin de la clase Controlador_Hanoi