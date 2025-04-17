package juegos.modelos;

import juegos.clases.Matriz;
import juegos.clases.Figura;
import juegos.clases.Reina;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Modelo_Reina {
    private int dimension;
    public int numeroDeCombinaciones;
    private List<Matriz<Figura>> soluciones;

    // Método principal para iniciar la resolución del problema
    public boolean resolver(int n) {
        this.dimension = n;                      // Se guarda el tamaño del tablero
        this.numeroDeCombinaciones = 0;          // Se inicializa el contador de soluciones
        this.soluciones = new ArrayList<>();     // Se inicializa la lista de soluciones
        Matriz<Figura> tablero = new Matriz<>(n, n); // Se crea un tablero vacío de n x n
        buscarSoluciones(0, tablero);            // Se empieza la búsqueda desde la fila 0
        return !soluciones.isEmpty();            // Devuelve true si se encontró al menos una solución
    }

    // Método recursivo que busca todas las soluciones válidas
    private void buscarSoluciones(int fila, Matriz<Figura> tablero) {
        // Si ya se colocaron todas las reinas (una por fila), se encontró una solución
        if (fila == dimension) {
            Matriz<Figura> copia = copiarTablero(tablero); // Se hace una copia de la solución
            soluciones.add(copia);                          // Se guarda la copia en la lista
            numeroDeCombinaciones++;                        // Se incrementa el contador
            return;
        }

        // Se intenta colocar una reina en cada columna de la fila actual
        for (int col = 0; col < dimension; col++) {
            if (esSeguro(fila, col, tablero)) {             // Verifica si es seguro colocar una reina aquí
                tablero.datos[fila][col] = new Reina();     // Coloca la reina
                buscarSoluciones(fila + 1, tablero);        // Avanza a la siguiente fila
                tablero.datos[fila][col] = null;            // Backtracking: quita la reina para probar otra posición
            }
        }
    }

    // Verifica si se puede colocar una reina en una posición sin ser atacada por otras
    private boolean esSeguro(int fila, int col, Matriz<Figura> tablero) {
        // Verifica la columna vertical hacia arriba
        for (int i = 0; i < fila; i++) {
            if (tablero.datos[i][col] != null) return false;
        }

        // Verifica la diagonal superior izquierda
        for (int i = fila - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (tablero.datos[i][j] != null) return false;
        }

        // Verifica la diagonal superior derecha
        for (int i = fila - 1, j = col + 1; i >= 0 && j < dimension; i--, j++) {
            if (tablero.datos[i][j] != null) return false;
        }

        return true; // Si pasa todas las condiciones, es una posición segura
    }

    // Crea una copia del tablero actual con las posiciones de las reinas
    private Matriz<Figura> copiarTablero(Matriz<Figura> original) {
        Matriz<Figura> copia = new Matriz<>(dimension, dimension); // Crea un tablero nuevo

        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (original.datos[i][j] != null) {
                    copia.datos[i][j] = new Reina(); // Copia la reina a la misma posición
                }
            }
        }

        return copia; // Devuelve el tablero clonado
    }

    // Devuelve una solución aleatoria entre todas las posibles
    public Matriz<Figura> obtenerSolucionAleatoria() {
        if (soluciones.isEmpty()) return null; // Si no hay soluciones, retorna null
        Random r = new Random(); // Generador de números aleatorios
        return soluciones.get(r.nextInt(soluciones.size())); // Devuelve una solución aleatoria
    }

    // Método para obtener el tamaño del tablero
    public int getDimension() {
        return dimension;
    }

} //Fin de la clase Modelo_Reina