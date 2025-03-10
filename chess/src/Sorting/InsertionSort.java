package Sorting;

import model.Pieza;
import util.Tablero;

import java.util.List;

public class InsertionSort {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();



        for (int i = 1; i < piezas.size(); i++) {
            Pieza key = piezas.get(i);
            int j = i - 1;

            while (j >= 0 && piezas.get(j).getPosicion().compareTo(key.getPosicion()) > 0) {
                piezas.set(j + 1, piezas.get(j));
                j = j - 1;
            }
            piezas.set(j + 1, key);

            // Actualizar posiciones en el tablero
            for (int k = 0; k < piezas.size(); k++) {
                int fila = 8 - Integer.parseInt(piezas.get(k).getPosicion().substring(1));
                int columna = piezas.get(k).getPosicion().charAt(0) - 'a';
                tablero.colocarPieza(piezas.get(k), fila, columna);
            }
            // Visualizar el tablero después de cada pasada
            tablero.visualizarTablero();
            try {
                Thread.sleep(500); // Pausa para visualización
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long endTime = System.nanoTime();
        System.out.println("Insertion Sort tardó " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
