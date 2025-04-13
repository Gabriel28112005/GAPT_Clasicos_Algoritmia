package juegos.N_Reinas.Modelo;

import juegos.Matriz;
import juegos.Figura;
import juegos.N_Reinas.Reina;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Modelo_Reina {
    private int dimension;
    public int numeroDeCombinaciones;
    private List<Matriz<Figura>> soluciones;

    public boolean resolver(int n) {
        this.dimension = n;
        this.numeroDeCombinaciones = 0;
        this.soluciones = new ArrayList<>();
        Matriz<Figura> tablero = new Matriz<>(n, n);
        buscarSoluciones(0, tablero);
        return !soluciones.isEmpty();
    }

    private void buscarSoluciones(int fila, Matriz<Figura> tablero) {
        if (fila == dimension) {
            Matriz<Figura> copia = copiarTablero(tablero);
            soluciones.add(copia);
            numeroDeCombinaciones++;
            return;
        }

        for (int col = 0; col < dimension; col++) {
            if (esSeguro(fila, col, tablero)) {
                tablero.datos[fila][col] = new Reina();
                buscarSoluciones(fila + 1, tablero);
                tablero.datos[fila][col] = null;
            }
        }
    }

    private boolean esSeguro(int fila, int col, Matriz<Figura> tablero) {
        for (int i = 0; i < fila; i++) {
            if (tablero.datos[i][col] != null) return false;
        }
        for (int i = fila - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (tablero.datos[i][j] != null) return false;
        }
        for (int i = fila - 1, j = col + 1; i >= 0 && j < dimension; i--, j++) {
            if (tablero.datos[i][j] != null) return false;
        }
        return true;
    }

    private Matriz<Figura> copiarTablero(Matriz<Figura> original) {
        Matriz<Figura> copia = new Matriz<>(dimension, dimension);
        for (int i = 0; i < dimension; i++) {
            for (int j = 0; j < dimension; j++) {
                if (original.datos[i][j] != null) {
                    copia.datos[i][j] = new Reina();
                }
            }
        }
        return copia;
    }

    public Matriz<Figura> obtenerSolucionAleatoria() {
        if (soluciones.isEmpty()) return null;
        Random r = new Random();
        return soluciones.get(r.nextInt(soluciones.size()));
    }

    public int getDimension() {
        return dimension;
    }
}