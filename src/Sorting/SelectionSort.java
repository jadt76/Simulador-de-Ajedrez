package Sorting;

import model.Pieza;
import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;

import java.util.List;

public class SelectionSort implements AlgoritmoOrdenamiento {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();
        int n = piezas.size();

        for (int i = 0; i < n - 1; i++) {
            // Encontrar el índice del elemento mínimo en la lista no ordenada
            int indiceMinimo = i;
            for (int j = i + 1; j < n; j++) {
                if (compararPiezas(piezas.get(j), piezas.get(indiceMinimo)) < 0) {
                    indiceMinimo = j;
                }
            }

            // Intercambiar el elemento mínimo encontrado con el primer elemento no ordenado
            Pieza temp = piezas.get(indiceMinimo);
            piezas.set(indiceMinimo, piezas.get(i));
            piezas.set(i, temp);

            // Actualizar el tablero con las nuevas posiciones de las piezas
            tablero.actualizarPosicionesEnTablero(piezas, tablero);

            // Visualizar el tablero después de cada intercambio
            tablero.visualizarTablero();
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Tiempo de ordenamiento: " + duration + " nanosegundos");
    }

    private static int compararPiezas(Pieza p1, Pieza p2) {
        // Definir el orden de las piezas según las reglas del ajedrez
        String[] ordenPiezas = {"Rey", "Reina", "Torre", "Alfil", "Caballo", "Peón"};

        int indiceP1 = -1;
        int indiceP2 = -1;

        for (int i = 0; i < ordenPiezas.length; i++) {
            if (ordenPiezas[i].equals(p1.tipoPieza())) {
                indiceP1 = i;
            }
            if (ordenPiezas[i].equals(p2.tipoPieza())) {
                indiceP2 = i;
            }
        }

        return Integer.compare(indiceP1, indiceP2);
    }

    private static void actualizarTablero(List<Pieza> piezas, Tablero tablero) {
        // Limpiar el tablero
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                tablero.getCasillas()[fila][columna] = null;
            }
        }

        // Colocar las piezas en el tablero según su posición actual
        for (Pieza pieza : piezas) {
            int fila = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
            int columna = pieza.getPosicion().charAt(0) - 'a';
            tablero.getCasillas()[fila][columna] = pieza;
        }
    }
}