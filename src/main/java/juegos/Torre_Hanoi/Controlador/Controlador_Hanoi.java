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
        vista.getBotonIniciar().addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                // Obtener el texto del campo de discos
                String texto = vista.getCampoDiscos().getText();

                // Intentar convertir el texto a un número entero
                try {
                    int numDiscos = Integer.parseInt(texto);

                    // Verificar que el número sea mayor que 0
                    if (numDiscos > 0) {
                        modelo.solve(numDiscos); // Ejecutar el modelo
                        vista.getEtiquetaMovimientos().setText("Mínimo de movimientos: " + modelo.numeroDeMovimientos);
                    } else {
                        JOptionPane.showMessageDialog(vista, "Debe ingresar un número mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException error) {
                    JOptionPane.showMessageDialog(vista, "Ingrese un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
}
