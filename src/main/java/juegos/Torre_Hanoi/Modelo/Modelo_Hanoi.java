package juegos.Torre_Hanoi.Modelo;

import juegos.Torre_Hanoi.Disco;
import juegos.Figura;
import juegos.Matriz;
import juegos.Torre_Hanoi.Vista.Vista_Hanoi;

import java.awt.Color;
import java.util.Random;

public class Modelo_Hanoi {
    private Matriz<Figura> matriz;
    private int numDiscos;
    public int numeroDeMovimientos;
    private Vista_Hanoi vista;

    public void setVista(Vista_Hanoi vista) {
        this.vista = vista;
    }

    public void solve(int numDiscos) {
        this.numDiscos = numDiscos;
        this.numeroDeMovimientos = (int) Math.pow(2, numDiscos) - 1;
        matriz = new Matriz<>(numDiscos, 3);

        for (int i = 0; i < numDiscos; i++) {
            int tamaño = numDiscos - i;
            Color color = generarColorAleatorio();
            Disco disco = new Disco(tamaño, color);
            matriz.datos[i][0] = disco;
        }

        vista.mostrarEstado(matriz);
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
        int filaOrigen = buscarDiscoSuperior(origen);
        int filaDestino = buscarEspacioInferior(destino);
        if (filaOrigen == -1 || filaDestino == -1) return;

        matriz.trasladar(filaOrigen, origen, filaDestino, destino);

        vista.mostrarEstado(matriz);
        try {
            Thread.sleep(500); // animación entre pasos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int buscarDiscoSuperior(int columna) {
        for (int i = 0; i < matriz.filas; i++) {
            if (matriz.datos[i][columna] != null) return i;
        }
        return -1;
    }

    private int buscarEspacioInferior(int columna) {
        for (int i = matriz.filas - 1; i >= 0; i--) {
            if (matriz.datos[i][columna] == null) return i;
        }
        return -1;
    }

    private Color generarColorAleatorio() {
        Random r = new Random();
        return new Color(r.nextInt(156) + 100, r.nextInt(156) + 100, r.nextInt(156) + 100);
    }
} //Fin de la clase Modelo_Hanoi