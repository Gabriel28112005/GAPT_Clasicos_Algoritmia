package juegos.Torre_Hanoi.Modelo;

import juegos.Torre_Hanoi.Disco;
import juegos.Figura;
import juegos.Matriz;
import juegos.Torre_Hanoi.Vista.Vista_Hanoi;

import javax.swing.*;
import java.awt.Color;
import java.util.Random;
import java.util.Stack;

public class Modelo_Hanoi {
    private Stack<Figura>[] torres;
    private int numDiscos;
    public int numeroDeMovimientos;
    private Vista_Hanoi vista;

    public void setVista(Vista_Hanoi vista) {
        this.vista = vista;
    }


    public void solve(int numDiscos) {
        this.numDiscos = numDiscos;
        this.numeroDeMovimientos = (int) Math.pow(2, numDiscos) - 1;

        // Crear las 3 pilas
        torres = new Stack[3];
        for (int i = 0; i < 3; i++) {
            torres[i] = new Stack<>();
        }

        // Llenar la torre izquierda con los discos desde el más grande (abajo) al más pequeño (arriba)
        for (int i = numDiscos; i >= 1; i--) {
            Color color = generarColorAleatorio();
            Disco disco = new Disco(i, color);
            torres[0].push(disco);
        }

        // Mostrar estado inicial
        mostrarEstadoEnVista();

        // Pausa antes de empezar
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Resolver el problema recursivamente
        moverDiscos(numDiscos, 0, 2, 1);
    }

    private void moverDiscos(int n, int origen, int destino, int auxiliar) {
        if (n == 1) {
            moverUnDisco(origen, destino);
        } else {
            moverDiscos(n - 1, origen, auxiliar, destino);
            moverUnDisco(origen, destino);
            moverDiscos(n - 1, auxiliar, destino, origen);
        }
    }

    private void moverUnDisco(int origen, int destino) {
        if (torres[origen].isEmpty()) return;

        Figura disco = torres[origen].pop();
        torres[destino].push(disco);

        mostrarEstadoEnVista();

        try {
            Thread.sleep(500); // animación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Esta función adapta el contenido de las pilas a una Matriz<Figura> para que pueda ser visualizado
    private void mostrarEstadoEnVista() {
        Matriz<Figura> estado = new Matriz<>(numDiscos, 3);

        for (int col = 0; col < 3; col++) {
            Stack<Figura> torre = torres[col];
            int fila = numDiscos - 1;

            for (Figura figura : torre) {
                estado.datos[fila][col] = figura;
                fila--;
            }
        }

        SwingUtilities.invokeLater(() -> vista.mostrarEstado(estado));
    }

    private Color generarColorAleatorio() {
        Random r = new Random();
        return new Color(r.nextInt(156) + 100, r.nextInt(156) + 100, r.nextInt(156) + 100);
    }
}
