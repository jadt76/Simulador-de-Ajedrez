package Sorting;

import model.Pieza;
import util.Tablero;
import util.Validador;

import java.util.List;

public class ShellSort {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();

        int n = piezas.size();

        for (int gap = n / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < n; i++) {
                Pieza temp = piezas.get(i);
                int j;
                for (j = i; j >= gap && Validador.esMovimientoValido(piezas.get(j - gap), temp.getPosicion()); j -= gap) {
                    piezas.set(j, piezas.get(j - gap));
                }
                piezas.set(j, temp);
            }

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
        System.out.println("Shell Sort tardó " + (endTime - startTime) / 1_000_000 + " ms");
    }
}
