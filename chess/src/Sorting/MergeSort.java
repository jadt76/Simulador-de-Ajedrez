package Sorting;

import model.Pieza;
import util.Tablero;
import util.Validador;

import java.util.List;

public class MergeSort {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        if (piezas.size() < 2) {
            return;
        }

        int medio = piezas.size() / 2;
        List<Pieza> izquierda = piezas.subList(0, medio);
        List<Pieza> derecha = piezas.subList(medio, piezas.size());

        ordenar(izquierda, tablero);
        ordenar(derecha, tablero);

        merge(piezas, izquierda, derecha, tablero);
    }

    private void merge(List<Pieza> piezas, List<Pieza> izquierda, List<Pieza> derecha, Tablero tablero) {
        long startTime = System.nanoTime();

        int i = 0, j = 0, k = 0;

        while (i < izquierda.size() && j < derecha.size()) {
            if (Validador.esMovimientoValido(izquierda.get(i), derecha.get(j).getPosicion())) {
                piezas.set(k++, izquierda.get(i++));
            } else {
                piezas.set(k++, derecha.get(j++));
            }
        }

        while (i < izquierda.size()) {
            piezas.set(k++, izquierda.get(i++));
        }

        while (j < derecha.size()) {
            piezas.set(k++, derecha.get(j++));
        }
        // Actualizar posiciones en el tablero
        for (Pieza pieza : piezas) {
            int fila = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
            int columna = pieza.getPosicion().charAt(0) - 'a';
            tablero.colocarPieza(pieza, fila, columna);
        }

        // Visualizar el tablero después de cada pasada
        tablero.visualizarTablero();
        try {
            Thread.sleep(500); // Pausa para visualización
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.nanoTime();
        System.out.println("Merge Sort tardó " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
