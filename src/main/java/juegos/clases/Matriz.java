package juegos.clases;

public class Matriz<T> {
    public Object[][] datos;
    public int filas;
    public int columnas;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.datos = new Object[filas][columnas]; // ← NO hay cast aquí
    }

} //Fin de la clase Matriz