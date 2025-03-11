package model;

public class Caballo extends Pieza {
    public Caballo(String color, String posicion) {
        super(color, posicion);
    }

    @Override
    public boolean mover(String nuevaPosicion) {
        // Lógica de movimiento del Caballo
        int filaActual = 8 - Integer.parseInt(getPosicion().substring(1));
        int columnaActual = getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';

        // El Caballo se mueve en forma de "L"
        if ((Math.abs(filaNueva - filaActual) == 2 && Math.abs(columnaNueva - columnaActual) == 1) ||
                (Math.abs(filaNueva - filaActual) == 1 && Math.abs(columnaNueva - columnaActual) == 2)) {
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
        return "Caballo";
    }
}