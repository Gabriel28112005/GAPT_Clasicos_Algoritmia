package juegos.Problema_Caballo.Modelo;

import juegos.Matriz;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Modelo_Caballo {
    private Matriz<Integer> tablero;
    private int dimension;

    private final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    public boolean resolver(int n, int filaInicio, int colInicio) {
        this.dimension = n;
        this.tablero = new Matriz<>(n, n);
        return moverCaballo(filaInicio, colInicio, 1);
    }

    private boolean moverCaballo(int fila, int col, int paso) {
        if (fila < 0 || fila >= dimension || col < 0 || col >= dimension) {
            return false;
        }

        if (tablero.datos[fila][col] != null) {
            return false;
        }

        tablero.datos[fila][col] = paso;

        if (paso == dimension * dimension) {
            return true;
        }

        List<int[]> movimientos = obtenerMovimientosOrdenados(fila, col);

        for (int[] mov : movimientos) {
            int nuevaFila = mov[0];
            int nuevaCol = mov[1];
            if (moverCaballo(nuevaFila, nuevaCol, paso + 1)) {
                return true;
            }
        }

        tablero.datos[fila][col] = null;
        return false;
    }

    private List<int[]> obtenerMovimientosOrdenados(int fila, int col) {
        List<int[]> movimientos = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + dx[i];
            int nuevaCol = col + dy[i];

            if (esValido(nuevaFila, nuevaCol)) {
                int accesibilidad = contarMovimientosDisponibles(nuevaFila, nuevaCol);
                movimientos.add(new int[]{nuevaFila, nuevaCol, accesibilidad});
            }
        }

        movimientos.sort(Comparator.comparingInt(a -> a[2]));

        List<int[]> resultado = new ArrayList<>();
        for (int[] mov : movimientos) {
            resultado.add(new int[]{mov[0], mov[1]});
        }
        return resultado;
    }

    private boolean esValido(int fila, int col) {
        return fila >= 0 && fila < dimension && col >= 0 && col < dimension && tablero.datos[fila][col] == null;
    }

    private int contarMovimientosDisponibles(int fila, int col) {
        int contador = 0;
        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + dx[i];
            int nuevaCol = col + dy[i];
            if (esValido(nuevaFila, nuevaCol)) {
                contador++;
            }
        }
        return contador;
    }

    public Matriz<Integer> getTablero() {
        return tablero;
    }

    public int getDimension() {
        return dimension;
    }
}
