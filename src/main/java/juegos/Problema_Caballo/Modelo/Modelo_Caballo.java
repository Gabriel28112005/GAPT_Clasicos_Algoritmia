package juegos.Problema_Caballo.Modelo;

import juegos.Figura;
import juegos.Problema_Caballo.Caballo;
import juegos.Matriz;

public class Modelo_Caballo {
    public Matriz<Figura> tablero;
    public int numeroDeMovimientos;
    private int dimension;

    private final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public void resolver(int n, int filaInicio, int colInicio) {
        this.dimension = n;
        this.tablero = new Matriz<>(n, n);
        this.numeroDeMovimientos = 0;

        if (moverCaballo(filaInicio, colInicio, 1)) {
            System.out.println("Recorrido completado:");
        } else {
            System.out.println("No se pudo completar el recorrido.");
        }

        tablero.imprimir();
        System.out.println("Movimientos realizados: " + numeroDeMovimientos);
    }

    private boolean moverCaballo(int fila, int col, int paso) {
        if (fila < 0 || fila >= dimension || col < 0 || col >= dimension) return false;
        if (tablero.datos[fila][col] != null) return false;

        tablero.datos[fila][col] = new Caballo() {
            @Override
            public String toString() {
                return String.valueOf(paso);
            }
        };

        numeroDeMovimientos++;

        if (paso == dimension * dimension) return true;

        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + dx[i];
            int nuevaCol = col + dy[i];
            if (moverCaballo(nuevaFila, nuevaCol, paso + 1)) return true;
        }

        tablero.datos[fila][col] = null; // backtrack
        numeroDeMovimientos--;
        return false;
    }

} //Fin de la clase Modelo Caballo