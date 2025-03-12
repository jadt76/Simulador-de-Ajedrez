package util;

import model.Pieza;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * Clase para representar un tablero de ajedrez
 * Esta clase contiene un mapa de casillas y una lista de piezas en el tablero.
 * También contiene métodos para colocar piezas en el tablero y visualizarlo.
 */
public class Tablero {
    //Mapa para almacenar las piezas en el tablero
    private Map<String, Pieza> casillas;
    // Lista para almacenar las piezas en orden
    private  List<Pieza> piezas;
    //Enums para colores y tipos de piezas
    public enum Color {
        BLANCO, NEGRO
    }
    public enum TipoPieza {
        Peon, Torre, Caballo, Alfil, Reina, Rey
    }
    //Constructor
    public Tablero() {
        casillas = new HashMap<>();
        piezas = new ArrayList<>();
    }
    // Método para colocar una pieza en el tablero
    public void colocarPieza(Pieza pieza, String posicion) {
        if (posicionValida(posicion)) {
            casillas.put(posicion, pieza);
            piezas.add(pieza);
            pieza.setPosicion(posicion);
        } else {
            throw new IllegalArgumentException("Posición inválida en el tablero: " + posicion);
        }
    }
    // Método para verificar si una posición es válida
    private boolean posicionValida(String posicion) {
        return posicion.matches("[a-h][1-8]");
    }
    public Pieza obtenerPiezaEnPosicion(String posicion) {
        return casillas.get(posicion);
    }

    public void visualizarTablero() {
        // Imprimir la fila superior con las columnas numeradas
        System.out.print("    +-----+-----+-----+-----+-----+-----+-----+-----+\n");
        for (int fila = 8; fila >= 1; fila--) {
            // Imprimir el número de fila
            System.out.print((8 - fila) + "   |");
            for (char columna = 'a'; columna <= 'h'; columna++) {
                String posicion = String.valueOf(columna) + fila;
                Pieza pieza = casillas.get(posicion);
                if (pieza != null) {
                    // Imprimir la inicial de la pieza
                    System.out.print(" " + obtenerSimboloPieza(pieza) + "   |");
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
        Color color = pieza.getColor();
        TipoPieza tipo = pieza.tipoPieza();

        // Asignar símbolos Unicode según el tipo de pieza
        return switch (tipo) {
            case Rey -> color == Color.BLANCO ? "♔" : "♚";
            case Reina -> color == Color.BLANCO ? "♕" : "♛";
            case Torre -> color == Color.BLANCO ? "♖" : "♜";
            case Alfil -> color == Color.BLANCO ? "♗" : "♝";
            case Caballo -> color == Color.BLANCO ? "♘" : "♞";
            case Peon -> color == Color.BLANCO ? "♙" : "♟";
        };
    }

    public void limpiarTablero() {
        casillas.clear(); // Limpia el mapa de casillas
        piezas.clear(); // Limpia la lista de piezas
    }

    public void actualizarPosicionesEnTablero(List<Pieza> piezas, Tablero tablero) {
        // Limpiar el tablero
        tablero.getCasillas().clear();
        int index = 0;
        for (Pieza pieza : piezas) {
            String posicion = String.valueOf((char) ('a' + (index % 8))) + (8 - (index / 8));
            pieza.setPosicion(posicion);
            tablero.getCasillas().put(posicion, pieza);
            index++;
        }

        // Colocar las piezas en el tablero según su posición en la lista ordenada
        index = 0;
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                if (index < piezas.size()) {
                    Pieza pieza = piezas.get(index);
                    pieza.setPosicion(String.valueOf((char) ('a' + columna)) + (8 - fila));
                    tablero.getCasillas().put(pieza.getPosicion(), pieza);
                    index++;
                }
            }
        }
    }

    // Método para obtener todas las piezas
    public List<Pieza> getPiezas() {
        return piezas;
    }

    public Map<String, Pieza> getCasillas() {
        return casillas;
    }
}
