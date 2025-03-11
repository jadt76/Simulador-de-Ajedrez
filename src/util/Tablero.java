package util;

import model.Pieza;

import java.util.List;

public class Tablero {
    private final Pieza[][] casillas;

    public Tablero() {
        casillas = new Pieza[8][8];
    }

    public void visualizarTablero() {
        // Imprimir la fila superior con las columnas numeradas
        System.out.print("    +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        for (int fila = 0; fila < 8; fila++) {
            // Imprimir el número de fila
            System.out.print((8 - fila) + "   |");
            for (int columna = 0; columna < 8; columna++) {
                if (casillas[fila][columna] != null) {
                    // Imprimir la inicial de la pieza
                    System.out.print(" " + obtenerSimboloPieza(casillas[fila][columna]) + "   |");
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
    public interface AlgoritmoOrdenamiento {
        void ordenar(List<Pieza> piezas, Tablero tablero);
    }

    // Método para obtener el símbolo Unicode de una pieza
    private String obtenerSimboloPieza(Pieza pieza) {
        String color = pieza.getColor();
        String tipo = pieza.tipoPieza();

        // Asignar símbolos Unicode según el tipo de pieza
        return switch (tipo) {
            case "Rey" -> color.equals("blanco") ? "♔" : "♚";
            case "Reina" -> color.equals("blanco") ? "♕" : "♛";
            case "Torre" -> color.equals("blanco") ? "♖" : "♜";
            case "Alfil" -> color.equals("blanco") ? "♗" : "♝";
            case "Caballo" -> color.equals("blanco") ? "♘" : "♞";
            case "Peón" -> color.equals("blanco") ? "♙" : "♟";
            default -> " ";
        };
    }
    public void actualizarPosicionesEnTablero(List<Pieza> piezas, Tablero tablero) {
        // Limpiar el tablero
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                tablero.getCasillas()[fila][columna] = null;
            }
        }

        // Colocar las piezas en el tablero según su posición en la lista ordenada
        int index = 0;
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                if (index < piezas.size()) {
                    Pieza pieza = piezas.get(index);
                    pieza.setPosicion(String.valueOf((char) ('a' + columna)) + (8 - fila));
                    tablero.getCasillas()[fila][columna] = pieza;
                    index++;
                }
            }
        }
    }

    // Método para obtener el arreglo de casillas (necesario para colocar piezas)
    public Pieza[][] getCasillas() {
        return casillas;
    }
}
