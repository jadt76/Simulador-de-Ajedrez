package model;

import util.Tablero;

public class Peon extends Pieza {
    public Peon(Tablero.Color color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean mover(String nuevaPosicion) {
        // Lógica de movimiento del Peón
        int filaActual = 8 - Integer.parseInt(getPosicion().substring(1));
        int columnaActual = getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';

        // El Peón se mueve hacia adelante
        if (getColor().equals("blanco")) {
            if (filaNueva == filaActual - 1 && columnaNueva == columnaActual) {
                setPosicion(nuevaPosicion);
                return true;
            }
        } else if (getColor().equals("negro")) {
            if (filaNueva == filaActual + 1 && columnaNueva == columnaActual) {
                setPosicion(nuevaPosicion);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean capturar(Pieza otraPieza) {
        return false;
    }

    @Override
    public Tablero.TipoPieza tipoPieza() {
        return Tablero.TipoPieza.Peon;
    }
}