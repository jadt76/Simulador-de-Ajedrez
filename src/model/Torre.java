package model;

import util.Tablero;

public class Torre extends Pieza {
    public Torre(Tablero.Color color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean mover(String nuevaPosicion) {
        // Lógica de movimiento de la Torre
        int filaActual = 8 - Integer.parseInt(getPosicion().substring(1));
        int columnaActual = getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';

        // La Torre se mueve en línea recta
        if (filaActual == filaNueva || columnaActual == columnaNueva) {
            setPosicion(nuevaPosicion);
            return true;
        }
        return false;
    }

    @Override
    public boolean capturar(Pieza otraPieza) {
        return false;
    }

    @Override
    public Tablero.TipoPieza tipoPieza() {
        return Tablero.TipoPieza.Torre;
    }
}