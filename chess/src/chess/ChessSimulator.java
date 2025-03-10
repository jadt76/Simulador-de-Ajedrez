package chess;

import model.*;
import Sorting.*;
import util.Tablero;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ChessSimulator {

    private List<Pieza> piezas;
    private Tablero tablero;

    public ChessSimulator() {
        piezas = new ArrayList<>();
        tablero = new Tablero();
        inicializarPiezas(generarPosicionAleatoria(new Random()), new Random());
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al simulador de ajedrez en consola.");

        while (true) {
            try {
                System.out.println("\nSeleccione una opción:");
                System.out.println("1. Configurar piezas en el tablero");
                System.out.println("2. Seleccionar algoritmo de ordenamiento");
                System.out.println("3. Visualizar tablero");
                System.out.println("4. Salir");

                int opcion = scanner.nextInt();

                switch (opcion) {
                    case 1:
                        configurarPiezas(scanner);
                        break;
                    case 2:
                        seleccionarAlgoritmo(scanner);
                    break;
                    case 3:
                        tablero.visualizarTablero();
                    break;
                    case 4:
                        System.out.println("¡Hasta luego!");
                    return;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Por favor, ingrese un número entero.");
                scanner.nextLine();
            }
        }
    }
    private void configurarPiezas(Scanner scanner) {
        System.out.println("Seleccione el color de las piezas:");
        System.out.println("1. Blancas");
        System.out.println("2. Negras");

        int opcionColor = scanner.nextInt();
        String color = (opcionColor == 1) ? "blanco" : "negro";

        System.out.println("Seleccione las piezas a colocar en el tablero:");
        System.out.println("1. Rey");
        System.out.println("2. Reina");
        System.out.println("3. Torre");
        System.out.println("4. Alfil");
        System.out.println("5. Caballo");
        System.out.println("6. Peón");
        System.out.println("7. Colocar todas las piezas");

        int opcionPieza = scanner.nextInt();
        Random random = new Random();

        switch (opcionPieza) {
            case 1:
                piezas.add(new Rey(color, generarPosicionAleatoria(random)));
                break;
            case 2:
                piezas.add(new Reina(color, generarPosicionAleatoria(random)));
                break;
            case 3:
                piezas.add(new Torre(color, generarPosicionAleatoria(random)));
                piezas.add(new Torre(color, generarPosicionAleatoria(random)));
                break;
            case 4:
                piezas.add(new Alfil(color, generarPosicionAleatoria(random)));
                piezas.add(new Alfil(color, generarPosicionAleatoria(random)));
                break;
            case 5:
                piezas.add(new Caballo(color, generarPosicionAleatoria(random)));
                piezas.add(new Caballo(color, generarPosicionAleatoria(random)));
                break;
            case 6:
                for (int i = 0; i < 8; i++) {
                    piezas.add(new Peon(color, generarPosicionAleatoria(random)));
                }
                break;
            case 7:
                inicializarPiezas(color, random);
                break;
            default:
                System.out.println("Opción no válida.");
        }

        // Colocar las piezas seleccionadas en el tablero en posición aleatoria
        for (Pieza pieza : piezas) {
            int fila = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
            int columna = pieza.getPosicion().charAt(0) - 'a';
            tablero.colocarPieza(pieza, columna, fila);
        }
        //Visualizar el tablero inicial
        tablero.visualizarTablero();
    }

    private void inicializarPiezas(String color, Random random) {
        // Inicializa las piezas con posiciones aleatorias o predefinidas
        piezas.add(new Rey(color, generarPosicionAleatoria(random)));
        piezas.add(new Reina(color, generarPosicionAleatoria(random)));
        piezas.add(new Torre(color, generarPosicionAleatoria(random)));
        piezas.add(new Torre(color, generarPosicionAleatoria(random)));
        piezas.add(new Alfil(color, generarPosicionAleatoria(random)));
        piezas.add(new Alfil(color, generarPosicionAleatoria(random)));
        piezas.add(new Caballo(color, generarPosicionAleatoria(random)));
        piezas.add(new Caballo(color, generarPosicionAleatoria(random)));

        for (int i=0; i<8; i++) {
            piezas.add(new Peon(color, generarPosicionAleatoria(random)));
        }
    }

    private String generarPosicionAleatoria(Random random) {
        int fila = random.nextInt(8);
        int columna = random.nextInt(8);
        return (char)('a' + columna) + String.valueOf(8 - fila);
    }

    private void seleccionarAlgoritmo(Scanner scanner) {
        try {
            System.out.println("Seleccione el algoritmo de ordenamiento:");
            System.out.println("1. Bubble Sort");
            System.out.println("2. Selection Sort");
            System.out.println("3. Insertion Sort");
            System.out.println("4. Merge Sort");
            System.out.println("5. Quick Sort");
            System.out.println("6. Shell Sort");

            int opcionAlgoritmo = scanner.nextInt();

            System.out.println("Seleccione el criterio de ordenamiento:");
            System.out.println("1. Por valor de posición");
            System.out.println("2. Por tipo de pieza");

            int opcionOrdenamiento = scanner.nextInt();

            // Seleccionar algoritmo
            switch (opcionAlgoritmo) {
                case 1:
                    new BubbleSort().ordenar(piezas, tablero);
                    break;
                case 2:
                    new SelectionSort().ordenar(piezas, tablero);
                    break;
                case 3:
                    new InsertionSort().ordenar(piezas, tablero);
                    break;
                case 4:
                    new MergeSort().ordenar(piezas, tablero);
                    break;
                case 5:
                    new QuickSort().ordenar(piezas, tablero);
                    break;
                case 6:
                    new ShellSort().ordenar(piezas, tablero);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }

            // Ordenar por criterio seleccionado
            if (opcionOrdenamiento == 2) {
                piezas.sort((p1, p2) -> p1.tipoPieza().compareTo(p2.tipoPieza()));
            }
            // Visualizar el tablero final
            tablero.visualizarTablero();
        } catch (InputMismatchException e) {
            System.out.println("Por favor, ingrese un número entero.");
            scanner.nextLine();
        }
    }
}