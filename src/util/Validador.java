package util;

import model.Pieza;

public class Validador {

    public static boolean esPosicionValida(String posicion) {
        if (posicion.length() != 2) {
            return false;
        }
        char columna = posicion.charAt(0);
        int fila = Integer.parseInt(posicion.substring(1));

        return columna >= 'a' && columna <= 'h' && fila >= 1 && fila <= 8;
    }
    public static boolean esMovimientoValido(Pieza pieza, String nuevaPosicion) {

        if (!esPosicionValida(nuevaPosicion)) {
            return false;
        }
        // Lógica específica para cada tipo de pieza
        switch (pieza.tipoPieza()) {
            case "Rey":
                return validarMovimientoRey(pieza, nuevaPosicion);
            case "Reina":
                return validarMovimientoReina(pieza, nuevaPosicion);
            case "Torre":
                return validarMovimientoTorre(pieza, nuevaPosicion);
            case "Alfil":
                return validarMovimientoAlfil(pieza, nuevaPosicion);
            case "Caballo":
                return validarMovimientoCaballo(pieza, nuevaPosicion);
            case "Peón":
                return validarMovimientoPeon(pieza, nuevaPosicion);
            default:
                return false;
        }
    }

    private static boolean validarMovimientoRey(Pieza pieza, String nuevaPosicion) {
        int filaActual = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
        int columnaActual = pieza.getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';
        // Movimiento del Rey (una casilla en cualquier dirección)
        return Math.abs(filaNueva - filaActual) <= 1 && Math.abs(columnaNueva - columnaActual) <= 1;
    }
    private static boolean validarMovimientoReina(Pieza pieza, String nuevaPosicion) {
        int filaActual = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
        int columnaActual = pieza.getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';
        // Movimiento de la Reina (horizontal, vertical o diagonal)
        return filaActual == filaNueva || columnaActual == columnaNueva || Math.abs(filaNueva - filaActual) == Math.abs(columnaNueva - columnaActual);
    }
    private static boolean validarMovimientoTorre(Pieza pieza, String nuevaPosicion) {
        int filaActual = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
        int columnaActual = pieza.getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';
        // Movimiento de la Torre (horizontal o vertical)
        return filaActual == filaNueva || columnaActual == columnaNueva;
    }
    private static boolean validarMovimientoAlfil(Pieza pieza, String nuevaPosicion) {
        int filaActual = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
        int columnaActual = pieza.getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';
        //Movimiento del Alfil en diagonal
        return Math.abs(filaNueva - filaActual) == Math.abs(columnaNueva - columnaActual);
    }
    private static boolean validarMovimientoCaballo(Pieza pieza, String nuevaPosicion) {
        int filaActual = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
        int columnaActual = pieza.getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';
        //Movimiento del Caballo en forma de "L"
        return (Math.abs(filaNueva - filaActual) == 2 && Math.abs(columnaNueva - columnaActual) == 1) ||
                (Math.abs(filaNueva - filaActual) == 1 && Math.abs(columnaNueva - columnaActual) == 2);
    }
    private static boolean validarMovimientoPeon(Pieza pieza, String nuevaPosicion) {
        int filaActual = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
        int columnaActual = pieza.getPosicion().charAt(0) - 'a';
        int filaNueva = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columnaNueva = nuevaPosicion.charAt(0) - 'a';

        // Dirección del peón (1 para blancas, -1 para negras)
        int direccion = pieza.getColor().equals("blanco") ? -1 : 1;

        // Movimiento hacia adelante
        if (columnaActual == columnaNueva) {
            // Movimiento normal (una casilla)
            if (filaNueva == filaActual + direccion) {
                return true;
            }
            // Primer movimiento (dos casillas)
            if ((filaActual == 1 && pieza.getColor().equals("negro")) ||
                    (filaActual == 6 && pieza.getColor().equals("blanco"))) {
                return filaNueva == filaActual + 2 * direccion;
            }
        }
        // Captura en diagonal
        else if (Math.abs(columnaNueva - columnaActual) == 1 && filaNueva == filaActual + direccion) {
            return true;
        }
        return false;
    }
}
