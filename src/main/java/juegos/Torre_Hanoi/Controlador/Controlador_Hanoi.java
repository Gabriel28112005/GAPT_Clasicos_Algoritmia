package juegos.Torre_Hanoi.Controlador;

import juegos.Torre_Hanoi.Vista.Vista_Hanoi;
import juegos.Torre_Hanoi.Modelo.Modelo_Hanoi;

import javax.swing.*;

public class Controlador_Hanoi {
    private Vista_Hanoi vista;
    private Modelo_Hanoi modelo;

    public Controlador_Hanoi(Vista_Hanoi vista, Modelo_Hanoi modelo) {
        this.vista = vista;
        this.modelo = modelo;
        iniciarEventos();
    }

    private void iniciarEventos() {
        vista.getBotonIniciar().addActionListener(e -> {
            String texto = vista.getCampoDiscos().getText();

            try {
                int numDiscos = Integer.parseInt(texto);
                if (numDiscos > 0) {
                    modelo.setVista(vista);

                    // ✅ Iniciar la resolución en un hilo separado para que no se congele la interfaz
                    new Thread(() -> {
                        modelo.solve(numDiscos);
                        SwingUtilities.invokeLater(() -> {
                            vista.getEtiquetaMovimientos().setText(
                                    "Mínimo de movimientos: " + modelo.numeroDeMovimientos
                            );
                        });
                    }).start();

                } else {
                    JOptionPane.showMessageDialog(vista,
                            "Debe ingresar un número mayor que 0.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException error) {
                JOptionPane.showMessageDialog(vista,
                        "Ingrese un número válido.",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        vista.getBotonVolver().addActionListener(e -> {
            vista.dispose();
            juegos.Main_Juegos.main(null);
        });
    }
} //Fin de la clase Controlador_Hanoi