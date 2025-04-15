package juegos;

public class Matriz<T> {
    public Object[][] datos;
    public int filas;
    public int columnas;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new Object[filas][columnas]; // ← NO hay cast aquí
    }

    public void trasladar(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        datos[filaDestino][colDestino] = datos[filaOrigen][colOrigen];
        datos[filaOrigen][colOrigen] = null;
    }
} //Fin de la clase Matriz