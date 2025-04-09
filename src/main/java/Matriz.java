public class Matriz<T> {
    public T[][] datos;
    public int filas;
    public int columnas;

    public Matriz(int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        datos = (T[][]) new Object[filas][columnas];
    }

    public void imprimir() {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (datos[i][j] != null) {
                    System.out.print(datos[i][j] + "\t");
                } else {
                    System.out.print(".\t");
                }
            }
            System.out.println();
        }
    }

    public void trasladar(int filaOrigen, int colOrigen, int filaDestino, int colDestino) {
        datos[filaDestino][colDestino] = datos[filaOrigen][colOrigen];
        datos[filaOrigen][colOrigen] = null;
    }
} //Fin de la clase Matriz