package Sorting;

import model.Pieza;
import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;

import java.util.List;

public class QuickSort implements AlgoritmoOrdenamiento {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();
        // Convertir la lista a un array para facilitar el manejo de sublistas
        Pieza[] arrayPiezas = piezas.toArray(new Pieza[0]);

        // Llamar al método recursivo de ordenamiento
        quickSort(arrayPiezas, tablero, 0, arrayPiezas.length - 1);

        // Actualizar la lista original con el array ordenado
        for (int i = 0; i < arrayPiezas.length; i++) {
            piezas.set(i, arrayPiezas[i]);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Tiempo de ordenamiento: " + duration + " nanosegundos");
    }

    private static void quickSort(Pieza[] arrayPiezas, Tablero tablero, int bajo, int alto) {
        if (bajo < alto) {
            int indiceParticion = particion(arrayPiezas, tablero, bajo, alto);

            // Ordenar recursivamente las dos sublistas
            quickSort(arrayPiezas, tablero, bajo, indiceParticion - 1);
            quickSort(arrayPiezas, tablero, indiceParticion + 1, alto);
        }
    }

    private static int particion(Pieza[] arrayPiezas, Tablero tablero, int bajo, int alto) {
        Pieza pivote = arrayPiezas[alto];
        int i = (bajo - 1);

        for (int j = bajo; j <= alto - 1; j++) {
            if (compararPiezas(arrayPiezas[j], pivote) <= 0) {
                i++;
                intercambiar(arrayPiezas, i, j);

                // Actualizar el tablero con las nuevas posiciones de las piezas
                tablero.actualizarPosicionesEnTablero(List.of(arrayPiezas), tablero);

                // Visualizar el tablero después de cada intercambio
                tablero.visualizarTablero();
            }
        }
        intercambiar(arrayPiezas, i + 1, alto);

        // Actualizar el tablero con las nuevas posiciones de las piezas
        actualizarTablero(arrayPiezas, tablero);

        // Visualizar el tablero después de la partición
        tablero.visualizarTablero();

        return (i + 1);
    }

    private static void intercambiar(Pieza[] arrayPiezas, int i, int j) {
        Pieza temp = arrayPiezas[i];
        arrayPiezas[i] = arrayPiezas[j];
        arrayPiezas[j] = temp;
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
