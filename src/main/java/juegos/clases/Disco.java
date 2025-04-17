package juegos.clases;

import java.awt.Color;

public class Disco extends Figura {
    private int tamaño;
    private Color color;

    public Disco(int tamaño, Color color) {
        super("D" + tamaño);
        this.tamaño = tamaño;
        this.color = color;
    }

    public int getTamaño() {
        return tamaño;
    }

    public Color getColor() {
        return color;
    }

} //Fin de la clase Disco