package Sorting;

import model.Pieza;
import util.Tablero;
import util.Validador;

import java.util.List;

public class SelectionSort {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();

        for (int i = 0; i < piezas.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < piezas.size(); j++) {
                if (Validador.esMovimientoValido(piezas.get(j), piezas.get(minIndex).getPosicion())) {
                    minIndex = j;
                }
                // Intercambiar piezas
                Pieza temp = piezas.get(i);
                piezas.set(i, piezas.get(minIndex));
                piezas.set(minIndex, temp);

                // Actualizar posiciones en el tablero con validación
                int filaI = 8 - Integer.parseInt(piezas.get(i).getPosicion().substring(1));
                int columnaI = piezas.get(i).getPosicion().charAt(0) - 'a';
                int filaJ = 8 - Integer.parseInt(piezas.get(i + 1).getPosicion().substring(1));
                int columnaJ = piezas.get(i + 1).getPosicion().charAt(0) - 'a';

                tablero.colocarPieza(piezas.get(i), filaI, columnaI);
                tablero.colocarPieza(piezas.get(i + 1), filaJ, columnaJ);
                // Visualizar el tablero después de cada pasada
                tablero.visualizarTablero();
                try {
                    Thread.sleep(500); // Pausa para visualización
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            long endTime = System.nanoTime();
            System.out.println("Selection Sort tardó " + (endTime - startTime) / 1_000_000 + " ms");
        }
    }
}
