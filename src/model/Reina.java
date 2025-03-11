package model;

public class Reina extends Pieza {
    public Reina(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean mover(String nuevaPosicion) {
        // Lógica de movimiento de la Reina
        int filaActual = 8 - Integer.parseInt(getPosicion().substring(1));
        int columnaActual = getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';

        // La Reina se mueve en línea recta o diagonal
        if (filaActual == filaNueva || columnaActual == columnaNueva ||
                Math.abs(filaNueva - filaActual) == Math.abs(columnaNueva - columnaActual)) {
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
        return "Reina";
    }
}
