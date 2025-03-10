package Sorting;

import model.Pieza;
import util.Tablero;
import util.Validador;

import java.util.List;

public class QuickSort {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();
        quickSort(piezas, 0, piezas.size() - 1, tablero);

        long endTime = System.nanoTime();
        System.out.println("Quick Sort tardó " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private void quickSort(List<Pieza> piezas, int bajo, int alto, Tablero tablero) {
        if (bajo < alto) {
            int pi = particion(piezas, bajo, alto, tablero);

            quickSort(piezas, bajo, pi - 1, tablero);
            quickSort(piezas, pi + 1, alto, tablero);
        }
    }

    private int particion(List<Pieza> piezas, int bajo, int alto, Tablero tablero) {
        Pieza pivot = piezas.get(alto);
        int i = (bajo - 1);

        for (int j = bajo; j < alto; j++) {
            if (Validador.esMovimientoValido(piezas.get(j), pivot.getPosicion())) {
                i++;

                // Intercambiar piezas
                Pieza temp = piezas.get(i);
                piezas.set(i, piezas.get(j));
                piezas.set(j, temp);

                // Actualizar posiciones en el tablero
                int filaI = 8 - Integer.parseInt(piezas.get(i).getPosicion().substring(1));
                int columnaI = piezas.get(i).getPosicion().charAt(0) - 'a';
                int filaJ = 8 - Integer.parseInt(piezas.get(j).getPosicion().substring(1));
                int columnaJ = piezas.get(j).getPosicion().charAt(0) - 'a';

                tablero.colocarPieza(piezas.get(i), filaI, columnaI);
                tablero.colocarPieza(piezas.get(j), filaJ, columnaJ);
            }
        }

        // Intercambiar piezas
        Pieza temp = piezas.get(i + 1);
        piezas.set(i + 1, piezas.get(alto));
        piezas.set(alto, temp);

        // Actualizar posiciones en el tablero
        int filaI = 8 - Integer.parseInt(piezas.get(i + 1).getPosicion().substring(1));
        int columnaI = piezas.get(i + 1).getPosicion().charAt(0) - 'a';
        int filaJ = 8 - Integer.parseInt(piezas.get(alto).getPosicion().substring(1));
        int columnaJ = piezas.get(alto).getPosicion().charAt(0) - 'a';

        tablero.colocarPieza(piezas.get(i + 1), filaI, columnaI);
        tablero.colocarPieza(piezas.get(alto), filaJ, columnaJ);
        // Visualizar el tablero después de cada pasada
        tablero.visualizarTablero();
        try {
            Thread.sleep(500); // Pausa para visualización
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return i + 1;
    }
}
