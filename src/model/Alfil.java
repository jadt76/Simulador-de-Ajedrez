package model;

import util.Tablero;

public class Alfil extends Pieza {
    public Alfil(Tablero.Color color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean mover(String nuevaPosicion) {
        // Lógica de movimiento del Alfil
        int filaActual = 8 - Integer.parseInt(getPosicion().substring(1));
        int columnaActual = getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';

        // El Alfil se mueve en diagonal
        if (Math.abs(filaNueva - filaActual) == Math.abs(columnaNueva - columnaActual)) {
            setPosicion(nuevaPosicion);
            return true;
        }
        return false;
    }

    @Override
    public boolean capturar(Pieza otraPieza) {
        // Lógica de captura del Alfil
        return mover(otraPieza.getPosicion());
    }

    @Override
    public Tablero.TipoPieza tipoPieza() {
        return Tablero.TipoPieza.Alfil;
    }
}
