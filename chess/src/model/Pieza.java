package model;

// Clase base abstracta para todas las piezas de ajedrez
public abstract class Pieza {
    protected String color;
    protected String posicion;

    public Pieza(String color, String posicion) {
        this.color = color;
        this.posicion = posicion;
    }

    // Método abstracto para mover la pieza
    public abstract boolean mover(String nuevaPosicion);

    // Método abstracto para capturar otra pieza
    public abstract boolean capturar(Pieza otraPieza);

    //Método polimorfico para obtener el tipo de pieza
    public abstract String tipoPieza();

    // Getters y Setters
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
}
