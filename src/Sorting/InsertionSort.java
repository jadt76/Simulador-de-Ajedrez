package Sorting;

import model.Pieza;
import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;

import java.util.List;

public class InsertionSort implements AlgoritmoOrdenamiento {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();
        for (int i = 1; i < piezas.size(); i++) {
            Pieza clave = piezas.get(i);
            int j = i - 1;

            // Mover elementos de la lista que son mayores que la clave
            // a una posición adelante de su posición actual
            while (j >= 0 && compararPiezas(piezas.get(j), clave) > 0) {
                piezas.set(j + 1, piezas.get(j));
                j = j - 1;
            }
            piezas.set(j + 1, clave);

            actualizarTablero(piezas, tablero);
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
        tablero.getCasillas().clear();

        // Colocar las piezas en el tablero según su posición actual
        for (Pieza pieza : piezas) {
            String posicion = pieza.getPosicion();
            tablero.getCasillas().put(posicion, pieza);
        }
    }
}
