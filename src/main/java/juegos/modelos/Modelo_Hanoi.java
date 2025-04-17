package juegos.modelos;

import juegos.clases.Disco;
import juegos.clases.Figura;
import juegos.clases.Matriz;
import juegos.vistas.Vista_Hanoi;

import javax.swing.*;
import java.awt.Color;
import java.util.Random;
import java.util.Stack;

public class Modelo_Hanoi {
    private Stack<Figura>[] torres;
    private int numDiscos;
    public int numeroDeMovimientos;
    private Vista_Hanoi vista;

    // Método para establecer la vista desde el controlador
    public void setVista(Vista_Hanoi vista) {
        this.vista = vista;
    }

    // Método principal que resuelve el juego de la Torre de Hanoi
    public void solve(int numDiscos) {
        this.numDiscos = numDiscos; // Guarda el número de discos
        this.numeroDeMovimientos = (int) Math.pow(2, numDiscos) - 1; // Calcula el mínimo de movimientos (2^n - 1)

        // Inicializa las 3 torres como pilas vacías
        torres = new Stack[3];
        for (int i = 0; i < 3; i++) {
            torres[i] = new Stack<>();
        }

        // Agrega los discos a la torre 0 (izquierda), del más grande al más pequeño
        for (int i = numDiscos; i >= 1; i--) {
            Color color = generarColorAleatorio(); // Asigna un color aleatorio al disco
            Disco disco = new Disco(i, color);     // Crea el disco con su tamaño y color
            torres[0].push(disco);                 // Coloca el disco en la pila (base abajo)
        }

        // Muestra el estado inicial en la interfaz gráfica
        mostrarEstadoEnVista();

        // Espera un momento antes de iniciar el movimiento
        try {
            Thread.sleep(500); // pausa de 500 milisegundos
        } catch (InterruptedException e) {
            e.printStackTrace(); // muestra error si ocurre una interrupción
        }

        // Comienza a mover los discos recursivamente
        moverDiscos(numDiscos, 0, 2, 1); // origen = 0, destino = 2, auxiliar = 1
    }

    // Método recursivo para resolver la Torre de Hanoi
    private void moverDiscos(int n, int origen, int destino, int auxiliar) {
        if (n == 1) {
            moverUnDisco(origen, destino); // Caso base: mover un solo disco
        } else {
            moverDiscos(n - 1, origen, auxiliar, destino); // Mueve n-1 discos al auxiliar
            moverUnDisco(origen, destino);                 // Mueve el disco más grande al destino
            moverDiscos(n - 1, auxiliar, destino, origen); // Mueve los n-1 discos sobre el mayor
        }
    }

    // Método para mover un disco entre dos torres (de una pila a otra)
    private void moverUnDisco(int origen, int destino) {
        if (torres[origen].isEmpty()) return; // Verifica que haya discos en la torre origen

        Figura disco = torres[origen].pop();  // Toma el disco del tope de la torre origen
        torres[destino].push(disco);          // Coloca el disco en la torre destino

        mostrarEstadoEnVista();               // Actualiza la vista con el nuevo estado

        try {
            Thread.sleep(500); // Espera para animar el movimiento
        } catch (InterruptedException e) {
            e.printStackTrace(); // Muestra error si algo interrumpe el hilo
        }
    }

    // Método que convierte las pilas (torres) en una matriz para mostrar en pantalla
    private void mostrarEstadoEnVista() {
        Matriz<Figura> estado = new Matriz<>(numDiscos, 3); // Crea una matriz de nx3 para representar el tablero

        for (int col = 0; col < 3; col++) { // Recorre cada torre (columna)
            Stack<Figura> torre = torres[col];     // Obtiene la pila correspondiente
            int fila = numDiscos - 1;              // Empieza desde la base visual (última fila)

            for (Figura figura : torre) {          // Recorre los discos apilados
                estado.datos[fila][col] = figura;  // Coloca cada disco en la matriz
                fila--;                            // Sube una fila visualmente
            }
        }

        // Actualiza la vista de forma segura desde el hilo de interfaz
        SwingUtilities.invokeLater(() -> vista.mostrarEstado(estado));
    }

    // Genera un color aleatorio para asignar a cada disco
    private Color generarColorAleatorio() {
        Random r = new Random(); // Objeto para generar números aleatorios
        return new Color(r.nextInt(156) + 100, r.nextInt(156) + 100, r.nextInt(156) + 100);
        // Cada componente (RGB) estará entre 100 y 255 para colores más claros
    }

} //Fin de la clase Modelo_Hanoi
