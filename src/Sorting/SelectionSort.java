package Sorting;

import model.Pieza;
import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;

import java.util.List;

public class SelectionSort implements AlgoritmoOrdenamiento {
    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();
        int n = piezas.size();
        Pieza[] arrayPiezas = piezas.toArray(new Pieza[0]);

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (compararPiezas(arrayPiezas[j], arrayPiezas[minIndex]) < 0) {
                    minIndex = j;
                }
            }
            intercambiar(arrayPiezas, i, minIndex);

            // Actualizar el tablero después de cada intercambio
            actualizarTablero(arrayPiezas, tablero);
            tablero.visualizarTablero();
        }

        // Actualizar la lista original con el array ordenado
        for (int i = 0; i < arrayPiezas.length; i++) {
            piezas.set(i, arrayPiezas[i]);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Tiempo de ordenamiento: " + duration + " nanosegundos");
    }

    private static void intercambiar(Pieza[] arrayPiezas, int i, int j) {
        Pieza temp = arrayPiezas[i];
        arrayPiezas[i] = arrayPiezas[j];
        arrayPiezas[j] = temp;
    }

    private static int compararPiezas(Pieza p1, Pieza p2) {
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

    private static void actualizarTablero(Pieza[] arrayPiezas, Tablero tablero) {
        // Limpiar el tablero
        tablero.getCasillas().clear();

        // Colocar las piezas en el tablero según su posición actual
        for (Pieza pieza : arrayPiezas) {
            String posicion = pieza.getPosicion();
            tablero.getCasillas().put(posicion, pieza);
        }
    }
}