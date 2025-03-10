package util;

import model.Pieza;

public class Tablero {
    private Pieza[][] casillas;

    public Tablero() {
        casillas = new Pieza[8][8];
    }

    public void colocarPieza(Pieza pieza, int fila, int columna) {
        if (fila >= 0 && fila < 8 && columna >= 0 && columna < 8) {
            casillas[fila][columna] = pieza;
            pieza.setPosicion(String.valueOf(columnaToLetter(columna) + (8 - fila)));
        } else {
            throw new IllegalArgumentException("Posición inválida en el tablero.");
        }
    }

    public void visualizarTablero() {
        // Imprimir la fila superior con las columnas numeradas
        System.out.print("    +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        for (int fila = 0; fila < 8; fila++) {
            // Imprimir el número de fila
            System.out.print((8 - fila) + "    |");
            for (int columna = 0; columna < 8; columna++) {
                if (casillas[fila][columna] != null) {
                    // Imprimir la inicial de la pieza
                    System.out.print(casillas[fila][columna].tipoPieza().substring(0, 1).toUpperCase() + "    |");
                } else {
                    // Imprimir espacio vacío
                    System.out.print("     |");
                }
            }
            System.out.print("\n    +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        }
        // Imprimir la fila inferior con las letras de las columnas
        System.out.print("       a     b     c     d     e     f     g    h\n");
    }

    private char columnaToLetter(int columna) {
        return (char) ('a' + columna);
    }
}
