package juegos;

import juegos.controlador.Controlador_Hanoi;
import juegos.modelos.Modelo_Hanoi;
import juegos.vistas.Vista_Hanoi;

import juegos.modelos.Modelo_Reina;
import juegos.vistas.Vista_Reina;
import juegos.controlador.Controlador_Reina;

import juegos.modelos.Modelo_Caballo;
import juegos.vistas.Vista_Caballo;
import juegos.controlador.Controlador_Caballo;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame ventana = new JFrame("Selección de juegos"); // Se crea la ventana donde se selecciona uno de los tres juegos. El nombre de dicha ventana es "Selección de juegos"

            ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Cuando el usuario haga click en la "x" de la ventana, se detendrá la ejecución del programa.

            ventana.setSize(400, 200); //Se define la anchura (width) y la altura (height) de la ventana.

            ventana.setLocationRelativeTo(null); //Sirve para colocar la ventana en el centro de la pantalla de la computadora.

            ventana.setLayout(new GridLayout(4, 1, 10, 10)); //Se administra el diseño de los elementos dentro de la ventana: 4 filas (rows), 1 columna (cols), 10 píxeles de separación entre filas y 10 píxeles de separación entre columnas.


            JLabel etiqueta = new JLabel("Por favor, elige de los siguientes juegos:", JLabel.CENTER);

            JButton botonHanoi = new JButton("Torre de Hanoi");

            JButton botonReinas = new JButton("Problema de las 'n' Reinas");

            JButton botonCaballo = new JButton("Problema del Caballo");

            ventana.add(etiqueta);
            ventana.add(botonHanoi);
            ventana.add(botonReinas);
            ventana.add(botonCaballo);

            botonHanoi.addActionListener(e -> {

                ventana.dispose(); //Se cierra la ventana en donde se selecciona el juego

                Vista_Hanoi vista = new Vista_Hanoi();

                Modelo_Hanoi modelo = new Modelo_Hanoi();

                new Controlador_Hanoi(vista, modelo);

                vista.setVisible(true); //Se abre la ventana del juego de la Torre de Hanoi

            }); //Botón Hanoi usa una función lambda para ejecutar el código dentro del botón cuando el usuario haga click en él.

            botonReinas.addActionListener(e -> {

                ventana.dispose(); //Se cierra la ventana en donde se selecciona el juego

                Vista_Reina vista = new Vista_Reina();

                Modelo_Reina modelo = new Modelo_Reina();

                new Controlador_Reina(vista, modelo);

                vista.setVisible(true); //Se abre la ventana del juego de la reina

            }); //Botón Reinas que usa una función lambda para ejecutar el código dentro del botón cuando el usuario haga click en él.

            botonCaballo.addActionListener(e -> {

                ventana.dispose(); //Se cierra la ventana en donde se selecciona el juego

                Vista_Caballo vista = new Vista_Caballo();

                Modelo_Caballo modelo = new Modelo_Caballo();

                new Controlador_Caballo(vista, modelo);

                vista.setVisible(true); //Se abre la ventana del juego del caballo

            }); //Botón Caballo que usa una función lambda para ejecutar el código dentro del botón cuando el usuario haga click en él.

            ventana.setVisible(true);
        });
    }
} //Fin de la clase Main