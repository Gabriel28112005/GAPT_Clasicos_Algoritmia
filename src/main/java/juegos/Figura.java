package juegos;

public abstract class Figura {
    protected String nombre;

    public Figura(String nombre) {
        this.nombre = nombre;
    }

    public String toString() {
        return nombre;
    }
} // Fin de la clase juegos.Figura