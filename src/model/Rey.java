package model;

public class Rey extends Pieza {
    public Rey(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean mover(String nuevaPosicion) {
        // Lógica de movimiento del Rey
        int filaActual = 8 - Integer.parseInt(getPosicion().substring(1));
        int columnaActual = getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';

        // El Rey se mueve una casilla en cualquier dirección
        if (Math.abs(filaNueva - filaActual) <= 1 && Math.abs(columnaNueva - columnaActual) <= 1) {
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
    public String tipoPieza() {
        return "Rey";
    }
}
