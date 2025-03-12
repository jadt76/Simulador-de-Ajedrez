package Sorting;

import model.Pieza;
import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements AlgoritmoOrdenamiento {

    public void ordenar(List<Pieza> piezas, Tablero tablero) {
        long startTime = System.nanoTime();

        // Convertir la lista a un array para facilitar el manejo de sublistas
        Pieza[] arrayPiezas = piezas.toArray(new Pieza[0]);

        // Llamar al método recursivo de ordenamiento
        mergeSort(arrayPiezas, tablero, 0, arrayPiezas.length - 1);

        // Actualizar la lista original con el array ordenado
        for (int i = 0; i < arrayPiezas.length; i++) {
            piezas.set(i, arrayPiezas[i]);
        }

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        System.out.println("Tiempo de ordenamiento: " + duration + " nanosegundos");
    }

    private static void mergeSort(Pieza[] arrayPiezas, Tablero tablero, int izquierda, int derecha) {
        if (izquierda < derecha) {
            int medio = (izquierda + derecha) / 2;

            // Ordenar recursivamente las dos mitades
            mergeSort(arrayPiezas, tablero, izquierda, medio);
            mergeSort(arrayPiezas, tablero, medio + 1, derecha);

            // Combinar las dos mitades
            merge(arrayPiezas, tablero, izquierda, medio, derecha);
        }
    }

    private static void merge(Pieza[] arrayPiezas, Tablero tablero, int izquierda, int medio, int derecha) {
        // Crear listas temporales para las dos mitades
        List<Pieza> izquierdaLista = new ArrayList<>();
        List<Pieza> derechaLista = new ArrayList<>();

        for (int i = izquierda; i <= medio; i++) {
            izquierdaLista.add(arrayPiezas[i]);
        }
        for (int i = medio + 1; i <= derecha; i++) {
            derechaLista.add(arrayPiezas[i]);
        }

        int i = 0, j = 0, k = izquierda;
        while (i < izquierdaLista.size() && j < derechaLista.size()) {
            if (compararPiezas(izquierdaLista.get(i), derechaLista.get(j)) <= 0) {
                arrayPiezas[k++] = izquierdaLista.get(i++);
            } else {
                arrayPiezas[k++] = derechaLista.get(j++);
            }
        }

        // Copiar los elementos restantes de izquierdaLista, si los hay
        while (i < izquierdaLista.size()) {
            arrayPiezas[k++] = izquierdaLista.get(i++);
        }

        // Copiar los elementos restantes de derechaLista, si los hay
        while (j < derechaLista.size()) {
            arrayPiezas[k++] = derechaLista.get(j++);
        }

        // Actualizar el tablero con las nuevas posiciones de las piezas
        actualizarTablero(arrayPiezas, tablero);

        // Visualizar el tablero después de cada combinación
        tablero.visualizarTablero();
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
