package Sorting;

import util.Tablero;
import model.Pieza;
import util.Validador;

import java.util.List;

public class BubbleSort {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < piezas.size() - 1; i++) {
                if (Validador.esMovimientoValido(piezas.get(i + 1), piezas.get(i).getPosicion())) {
                    // Intercambiar piezas
                    Pieza temp = piezas.get(i);
                    piezas.set(i, piezas.get(i + 1));
                    piezas.set(i + 1, temp);

                    // Actualizar posiciones en el tablero con validación
                    int filaI = 8 - Integer.parseInt(piezas.get(i).getPosicion().substring(1));
                    int columnaI = piezas.get(i).getPosicion().charAt(0) - 'a';
                    int filaJ = 8 - Integer.parseInt(piezas.get(i + 1).getPosicion().substring(1));
                    int columnaJ = piezas.get(i + 1).getPosicion().charAt(0) - 'a';

                    tablero.colocarPieza(piezas.get(i), filaI, columnaI);
                    tablero.colocarPieza(piezas.get(i + 1), filaJ, columnaJ);

                    swapped = true;
                }
            }
            // Visualizar el tablero después de cada pasada
            tablero.visualizarTablero();
            try {
                Thread.sleep(500); // Pausa para visualización
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (swapped);
        long endTime = System.nanoTime();
        System.out.println("Bubble Sort tardó " + (endTime - startTime) / 1_000_000 + " ms");
    }

    private int caracterANumero(char c) {
        return c - 'a' + 1;
    }
}