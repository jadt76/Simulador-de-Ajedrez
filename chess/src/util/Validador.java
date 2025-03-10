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
        // Implementar lógica específica para validar el movimiento de la pieza
        // Aquí se puede incluir la lógica de movimiento específica para cada tipo de pieza
        if (!esPosicionValida(nuevaPosicion)) {
            return false;
        }

        char color = pieza.getColor().charAt(0);
        char tipo = pieza.tipoPieza().charAt(0);
        int fila = 8 - Integer.parseInt(nuevaPosicion.substring(1));
        int columna = nuevaPosicion.charAt(0) - 'a';

        // Validar posiciones para piezas blancas
        if (color == 'b') {
            if (tipo == 'R') return fila == 0 && columna == 4; // Rey
            if (tipo == 'Q') return fila == 0 && columna == 3; // Reina
            if (tipo == 'T') return (fila == 0 && columna == 0) || (fila == 0 && columna == 7); // Torres
            if (tipo == 'A') return (fila == 0 && columna == 2) || (fila == 0 && columna == 5); // Alfiles
            if (tipo == 'C') return (fila == 0 && columna == 1) || (fila == 0 && columna == 6); // Caballos
            if (tipo == 'P') return fila == 1; // Peones
        }

        // Validar posiciones para piezas negras
        if (color == 'n') {
            if (tipo == 'R') return fila == 7 && columna == 4; // Rey
            if (tipo == 'Q') return fila == 7 && columna == 3; // Reina
            if (tipo == 'T') return (fila == 7 && columna == 0) || (fila == 7 && columna == 7); // Torres
            if (tipo == 'A') return (fila == 7 && columna == 2) || (fila == 7 && columna == 5); // Alfiles
            if (tipo == 'C') return (fila == 7 && columna == 1) || (fila == 7 && columna == 6); // Caballos
            if (tipo == 'P') return fila == 6; // Peones
        }
        return false;
    }
}
