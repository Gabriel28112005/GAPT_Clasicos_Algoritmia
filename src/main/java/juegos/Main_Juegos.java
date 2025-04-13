package juegos;

import juegos.Torre_Hanoi.Modelo.Modelo_Hanoi;
import juegos.Torre_Hanoi.Vista.Vista_Hanoi;
import juegos.Torre_Hanoi.Controlador.Controlador_Hanoi;

import juegos.N_Reinas.Modelo.Modelo_Reina;
import juegos.N_Reinas.Vista.Vista_Reina;
import juegos.N_Reinas.Controlador.Controlador_Reina;

import juegos.Problema_Caballo.Modelo.Modelo_Caballo;
import juegos.Problema_Caballo.Vista.Vista_Caballo;
import juegos.Problema_Caballo.Controlador.Controlador_Caballo;

import javax.swing.*;
import java.awt.*;

public class Main_Juegos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame ventana = new JFrame("Selecciona un juego");
            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            ventana.setSize(400, 200);
            ventana.setLocationRelativeTo(null);
            ventana.setLayout(new GridLayout(4, 1, 10, 10));

            JLabel etiqueta = new JLabel("Elige un juego:", JLabel.CENTER);
            JButton botonHanoi = new JButton("Torre de Hanoi");
            JButton botonReinas = new JButton("Problema de las N Reinas");
            JButton botonCaballo = new JButton("Problema del Caballo");

            ventana.add(etiqueta);
            ventana.add(botonHanoi);
            ventana.add(botonReinas);
            ventana.add(botonCaballo);

            botonHanoi.addActionListener(e -> {
                ventana.dispose();
                Vista_Hanoi vista = new Vista_Hanoi();
                Modelo_Hanoi modelo = new Modelo_Hanoi();
                new Controlador_Hanoi(vista, modelo);
                vista.setVisible(true);
            });

            botonReinas.addActionListener(e -> {
                ventana.dispose();
                Vista_Reina vista = new Vista_Reina();
                Modelo_Reina modelo = new Modelo_Reina();
                new Controlador_Reina(vista, modelo);
                vista.setVisible(true);
            });

            botonCaballo.addActionListener(e -> {
                ventana.dispose();
                Vista_Caballo vista = new Vista_Caballo();
                Modelo_Caballo modelo = new Modelo_Caballo();
                new Controlador_Caballo(vista, modelo);
                vista.setVisible(true);
            });

            ventana.setVisible(true);
        });
    }
}