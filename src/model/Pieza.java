package model;

import util.Tablero;

/**
 * Clase base abstracta para todas las piezas de ajedrez
 * Esta clase define los atributos y métodos comunes a todas las piezas de ajedrez.
 * Cada pieza de ajedrez hereda de esta clase y define su propio comportamiento.
 */

public abstract class Pieza {
    protected Tablero.Color color;
    protected String posicion;

    public Pieza(Tablero.Color color, String posicion) {
        this.color = color;
        this.posicion = posicion;
    }

    // Método para mover una pieza a una nueva posición
    public  boolean mover(String nuevaPosicion) {
        this.posicion = nuevaPosicion;
        return true;
    }

    //Método polimórfico para obtener el tipo de pieza
    public abstract Tablero.TipoPieza tipoPieza();

    // Getters y Setters
    public Tablero.Color getColor() {
        return color;
    }

    public void setColor(Tablero.Color color) {
        this.color = color;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    // Método abstracto para capturar otra pieza
    public abstract boolean capturar(Pieza otraPieza);

}
