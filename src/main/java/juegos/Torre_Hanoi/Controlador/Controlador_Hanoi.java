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
                if (numDiscos <= 0) throw new NumberFormatException();

                modelo.solve(numDiscos);
                vista.getEtiquetaMovimientos().setText("Mínimo de movimientos: " + modelo.numeroDeMovimientos);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(vista, "Por favor, ingrese un número entero mayor que 0.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
