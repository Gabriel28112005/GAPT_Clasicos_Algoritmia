package juegos.modelos;

import juegos.clases.Matriz;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Modelo_Caballo {
    private Matriz<Integer> tablero; // Representa el tablero como una matriz de enteros
    private int dimension;           // Tamaño del tablero (n x n)

    // Arrays que definen los posibles movimientos del caballo en el eje X e Y
    private final int[] dx = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int[] dy = {1, 2, 2, 1, -1, -2, -2, -1};

    // Método público que inicia la resolución del recorrido del caballo
    public boolean resolver(int n, int filaInicio, int colInicio) {
        this.dimension = n; // Dimensión del tablero
        this.tablero = new Matriz<>(n, n);       // Crea una nueva matriz vacía de tamaño n x n
        return moverCaballo(filaInicio, colInicio, 1); // Inicia el recorrido desde la posición inicial con el paso 1
    }

    //Método que desplaza el caballo por el tablero
    private boolean moverCaballo(int fila, int col, int paso) {
        // Verifica si la posición está fuera de los límites del tablero
        if (fila < 0 || fila >= dimension || col < 0 || col >= dimension) {
            return false;
        }

        // Verifica si la celda ya fue visitada
        if (tablero.datos[fila][col] != null) {
            return false;
        }

        // Marca la celda con el número del paso actual
        tablero.datos[fila][col] = paso;

        // Si se llegó al último paso, se ha recorrido todo el tablero
        if (paso == dimension * dimension) {
            return true;
        }

        // Obtiene la lista de movimientos ordenados por accesibilidad
        List<int[]> movimientos = obtenerMovimientosOrdenados(fila, col);

        // Intenta moverse a cada una de las posiciones válidas
        for (int[] mov : movimientos) {
            int nuevaFila = mov[0];
            int nuevaCol = mov[1];
            if (moverCaballo(nuevaFila, nuevaCol, paso + 1)) {
                return true; // Si se encuentra una solución, se termina la búsqueda
            }
        }

        // Si no se pudo avanzar, retrocede (backtracking)
        tablero.datos[fila][col] = null;

        return false; // Cuando no hay solución desde esta ruta
    }

    // Método que calcula todos los movimientos posibles desde una posición y los ordena por accesibilidad
    private List<int[]> obtenerMovimientosOrdenados(int fila, int col) {
        List<int[]> movimientos = new ArrayList<>(); // Lista para guardar los movimientos posibles

        // Genera todos los posibles movimientos del caballo
        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + dx[i];
            int nuevaCol = col + dy[i];

            // Si el movimiento es válido, se calcula su accesibilidad (número de opciones futuras)
            if (esValido(nuevaFila, nuevaCol)) {
                int accesibilidad = contarMovimientosDisponibles(nuevaFila, nuevaCol);
                movimientos.add(new int[]{nuevaFila, nuevaCol, accesibilidad}); // Guarda el movimiento con su accesibilidad
            }
        }

        // Ordena los movimientos por accesibilidad ascendente (menos accesos primero)
        movimientos.sort(Comparator.comparingInt(a -> a[2]));

        // Devuelve la lista de movimientos sin el dato de accesibilidad (solo fila y columna)
        List<int[]> resultado = new ArrayList<>();
        for (int[] mov : movimientos) {
            resultado.add(new int[]{mov[0], mov[1]});
        }
        return resultado;
    }

    // Método para verificar si una posición está dentro del tablero y no ha sido visitada
    private boolean esValido(int fila, int col) {
        return fila >= 0 && fila < dimension &&
                col >= 0 && col < dimension &&
                tablero.datos[fila][col] == null;
    }

    // Método que cuenta cuántos movimientos válidos tiene una celda (usado para la accesibilidad)
    private int contarMovimientosDisponibles(int fila, int col) {
        int contador = 0;
        for (int i = 0; i < 8; i++) {
            int nuevaFila = fila + dx[i];
            int nuevaCol = col + dy[i];
            if (esValido(nuevaFila, nuevaCol)) {
                contador++; // Incrementa si el movimiento es válido
            }
        }
        return contador;
    }

    // Método que permite acceder al tablero actual desde otras clases
    public Matriz<Integer> getTablero() {
        return tablero;
    }

} //Fin de la clase Modelo_Caballo