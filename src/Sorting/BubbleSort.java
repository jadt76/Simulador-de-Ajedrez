package Sorting;

import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;
import model.Pieza;

import java.util.List;

public class BubbleSort implements AlgoritmoOrdenamiento {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        int n = piezas.size();
        long startTime = System.nanoTime();
        boolean swapped;
        do {
            swapped = false;
            for (int i = 1; i < n; i++) {
                if (compararPiezas(piezas.get(i - 1), piezas.get(i)) > 0) {
                    // Intercambiar piezas
                    Pieza temp = piezas.get(i - 1);
                    piezas.set(i - 1, piezas.get(i));
                    piezas.set(i, temp);
                    swapped = true;

                    // Actualizar el tablero después de cada intercambio
                    actualizarTablero(piezas, tablero);
                    tablero.visualizarTablero();
                }
            }
            // Reducir el rango de la lista a ordenar
            n--;
        } while (swapped);

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Tiempo de ordenamiento: " + duration + " nanosegundos");
    }

    private static int compararPiezas(Pieza p1, Pieza p2) {
        // Definir el orden de las piezas según las reglas del ajedrez
        // Aquí se puede definir un orden arbitrario, por ejemplo: Rey, Reina, Torre, Alfil, Caballo, Peón
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
