package chess;

import model.*;
import Sorting.*;
import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;
import util.Validador;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Random;

public class ChessSimulator {
    // Constantes para las opciones del menú
    private static final int OPCION_CONFIGURAR_PIEZAS = 1;
    private static final int OPCION_SELECCIONAR_ALGORITMO = 2;
    private static final int OPCION_VISUALIZAR_TABLERO = 3;
    private static final int OPCION_MOVER_PIEZA = 4;
    private static final int OPCION_SALIR = 5;

    private final List<Pieza> piezas;
    private Tablero tablero;

    public ChessSimulator() {
        piezas = new ArrayList<>();
        tablero = new Tablero();
        // Inicializar el tablero con las piezas en sus posiciones iniciales
        inicializarTablero();
    }
    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al simulador de ajedrez en consola.");

        while (true) {
            try {
                System.out.println("\nSeleccione una opción:");
                System.out.println(OPCION_CONFIGURAR_PIEZAS + ". Configurar piezas en el tablero");
                System.out.println(OPCION_SELECCIONAR_ALGORITMO + ". Seleccionar algoritmo de ordenamiento");
                System.out.println(OPCION_VISUALIZAR_TABLERO + ". Visualizar tablero");
                System.out.println(OPCION_MOVER_PIEZA + ". Mover una pieza");
                System.out.println(OPCION_SALIR + ". Salir");

                int opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case OPCION_CONFIGURAR_PIEZAS:
                        configurarPiezas(scanner);
                        break;
                    case OPCION_SELECCIONAR_ALGORITMO:
                        seleccionarAlgoritmo(scanner);
                        break;
                    case OPCION_VISUALIZAR_TABLERO:
                        tablero.visualizarTablero();
                        break;
                    case OPCION_MOVER_PIEZA:
                        moverPieza(scanner);
                        break;
                    case OPCION_SALIR:
                        System.out.println("Hasta luego");
                        return;
                    default:
                        System.out.println("Opción no válida. Por favor, intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número entero.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Error insesperado: " + e.getMessage());
                scanner.nextLine();
            }
        }
    }
    // Método para mover una pieza en el tablero
    private void moverPieza(Scanner scanner) {
        System.out.println("Ingrese el movimiento:");
        String movimiento = scanner.nextLine();

        // Validar el formato del movimiento
        if (!movimiento.matches("[a-h][1-8]-[a-h][1-8]")) {
            System.out.println("Formato de movimiento no válido. Ingrese dos posiciones separadas por un guion.");
            return;
        }

        // Obtener las posiciones de origen y destino
        String[] partes = movimiento.split("-");
        if (partes.length != 2) {
            System.out.println("Formato de movimiento no válido. Ingrese dos posiciones separadas por un guion.");
            return;
        }
        String origen = partes[0];
        String destino = partes[1];

        // Buscar la pieza en la posición de origen
        Pieza pieza = obtenerPiezaEnPosicion(origen);
        if (pieza == null) {
            System.out.println("No hay ninguna pieza en la posición de origen.");
            return;
        }

        // Validar el movimiento usando el Validador
        if (!Validador.esMovimientoValido(pieza, destino)) {
            System.out.println("Movimiento no válido para la pieza seleccionada.");
            return;
        }

        // Mover la pieza
        if (pieza.mover(destino)) {
            // Actualizar el tablero
            int filaOrigen = 8 - Integer.parseInt(origen.substring(1));
            int columnaOrigen = origen.charAt(0) - 'a';
            int filaDestino = 8 - Integer.parseInt(destino.substring(1));
            int columnaDestino = destino.charAt(0) - 'a';

            tablero.getCasillas()[filaOrigen][columnaOrigen] = null;
            tablero.getCasillas()[filaDestino][columnaDestino] = pieza;

            System.out.println("Movimiento realizado con éxito.");
            tablero.visualizarTablero();
        } else {
            System.out.println("No se pudo mover la pieza.");
            System.out.println("Movimientos válidos para " + pieza.tipoPieza() + " en " + origen + ":");
            mostrarMovimientosValidos(pieza, origen);
        }
    }
    // Método para obtener la pieza en una posición específica
    private Pieza obtenerPiezaEnPosicion(String posicion) {
        int fila = 8 - Integer.parseInt(posicion.substring(1));
        int columna = posicion.charAt(0) - 'a';
        return tablero.getCasillas()[fila][columna];
    }
    // Método para mostrar movimientos válidos para una pieza
    private void mostrarMovimientosValidos(Pieza pieza, String posicionActual) {
        int filaActual = 8 - Integer.parseInt(posicionActual.substring(1));
        int columnaActual = posicionActual.charAt(0) - 'a';

        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                String nuevaPosicion = (char) ('a' + columna) + String.valueOf(8 - fila);
                if (Validador.esMovimientoValido(pieza, nuevaPosicion)) {
                    System.out.println("- " + nuevaPosicion);
                }
            }
        }
    }

    // Método para inicializar el tablero con las piezas en sus posiciones iniciales
    private void inicializarTablero() {
        // Limpiar el tablero y la lista de piezas
        piezas.clear();
        tablero = new Tablero();

        // Colocar piezas blancas
        colocarPiezasIniciales("blanco", 0, 1);
        // Colocar piezas negras
        colocarPiezasIniciales("negro", 7, 6);
    }
    // Método auxiliar para colocar piezas en sus posiciones iniciales
    private void colocarPiezasIniciales(String color, int filaPiezas, int filaPeones) {
        // Colocar torres
        piezas.add(new Torre(color, "a" + (8 - filaPiezas)));
        piezas.add(new Torre(color, "h" + (8 - filaPiezas)));
        // Colocar caballos
        piezas.add(new Caballo(color, "b" + (8 - filaPiezas)));
        piezas.add(new Caballo(color, "g" + (8 - filaPiezas)));
        // Colocar alfiles
        piezas.add(new Alfil(color, "c" + (8 - filaPiezas)));
        piezas.add(new Alfil(color, "f" + (8 - filaPiezas)));
        // Colocar reina
        piezas.add(new Reina(color, "d" + (8 - filaPiezas)));
        // Colocar rey
        piezas.add(new Rey(color, "e" + (8 - filaPiezas)));
        // Colocar peones
        for (char col = 'a'; col <= 'h'; col++) {
            piezas.add(new Peon(color, col + String.valueOf(8 - filaPeones)));
        }

        // Colocar las piezas en el tablero
        for (Pieza pieza : piezas) {
            int fila = 8 - Integer.parseInt(pieza.getPosicion().substring(1));
            int columna = pieza.getPosicion().charAt(0) - 'a';
            tablero.getCasillas()[fila][columna] = pieza;
        }
    }

    // Método para configurar piezas en el tablero
    private void configurarPiezas(Scanner scanner) {
        try {
            System.out.println("Seleccione el color de las piezas:");
            System.out.println("1. Blancas");
            System.out.println("2. Negras");

            int opcionColor = scanner.nextInt();
            scanner.nextLine();

            if (opcionColor < 1 || opcionColor > 2) {
                System.out.print("Error: Opcion no valida. Seleccione 1 para blancas o 2 para negras.");
                return;
            }
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
            scanner.nextLine();

            if (opcionPieza < 1 || opcionPieza > 7) {
                System.out.println("Error: Opcion no valida. Seleccione entre 1 y 7.");
                return;
            }
            limpiarTablero();
            Random random = new Random();

            switch (opcionPieza) {
                case 1:
                    agregarPieza(color, "Rey", 1, random);
                break;
                case 2:
                    agregarPieza(color, "Reina", 1, random);
                break;
                case 3:
                    agregarPieza(color, "Torre", 2, random);
                break;
                case 4:
                    agregarPieza(color, "Alfil", 2, random);
                break;
                case 5:
                    agregarPieza(color, "Caballo", 2, random);
                break;
                case 6:
                    agregarPieza(color, "Peón", 8, random);
                break;
                case 7:
                    inicializarPiezas(color, random);
                break;
            }

            // Visualizar el tablero inicial
            tablero.visualizarTablero();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un número entero.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            scanner.nextLine();
        }
    }
    //Método para limpiar el tablero
    private void limpiarTablero() {
        piezas.clear();
        tablero = new Tablero();
    }

    // Método auxiliar para agregar una pieza al tablero
    private void agregarPieza(String color, String tipo, int cantidad, Random random) {
        for (int i = 0; i < cantidad; i++) {
            Pieza pieza = crearPieza(color, tipo, generarPosicionAleatoria(random));
            colocarPiezaEnPosicionValida(pieza, random);
            piezas.add(pieza);
        }
    }

    // Método auxiliar para crear una pieza según el tipo
    private Pieza crearPieza(String color, String tipo, String posicion) {
        return switch (tipo) {
            case "Rey" -> new Rey(color, posicion);
            case "Reina" -> new Reina(color, posicion);
            case "Torre" -> new Torre(color, posicion);
            case "Alfil" -> new Alfil(color, posicion);
            case "Caballo" -> new Caballo(color, posicion);
            case "Peón" -> new Peon(color, posicion);
            default -> throw new IllegalArgumentException("Tipo de pieza no válido.");
        };
    }

    // Método para colocar una pieza en una posición válida
    private void colocarPiezaEnPosicionValida(Pieza pieza, Random random) {
        String posicion;
        do {
            posicion = generarPosicionAleatoria(random);
        } while (!Validador.esPosicionValida(posicion) || estaPosicionOcupada(posicion));

        pieza.setPosicion(posicion);
        int fila = 8 - Integer.parseInt(posicion.substring(1));
        int columna = posicion.charAt(0) - 'a';
        tablero.getCasillas()[fila][columna] = pieza;
    }

    // Método para verificar si una posición está ocupada
    private boolean estaPosicionOcupada(String posicion) {
        int fila = 8 - Integer.parseInt(posicion.substring(1));
        int columna = posicion.charAt(0) - 'a';
        return tablero.getCasillas()[fila][columna] != null;
    }

    // Método para generar una posición aleatoria
    private String generarPosicionAleatoria(Random random) {
        int fila = random.nextInt(8);
        int columna = random.nextInt(8);
        return (char) ('a' + columna) + String.valueOf(8 - fila);
    }

    // Método para inicializar todas las piezas
    private void inicializarPiezas(String color, Random random) {
        agregarPieza(color, "Rey", 1, random);
        agregarPieza(color, "Reina", 1, random);
        agregarPieza(color, "Torre", 2, random);
        agregarPieza(color, "Alfil", 2, random);
        agregarPieza(color, "Caballo", 2, random);
        agregarPieza(color, "Peón", 8, random);
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
            AlgoritmoOrdenamiento algoritmo = null;

            // Seleccionar algoritmo
            switch (opcionAlgoritmo) {
                case 1:
                    algoritmo = new BubbleSort();
                    break;
                case 2:
                    algoritmo = new SelectionSort();
                    break;
                case 3:
                    algoritmo = new InsertionSort();
                    break;
                case 4:
                    algoritmo = new MergeSort();
                    break;
                case 5:
                    algoritmo = new QuickSort();
                    break;
                case 6:
                    algoritmo = new ShellSort();
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
            //Ejecución del algoritmo
            algoritmo.ordenar(piezas, tablero);
            //Visualizar el tablero ordenado
            tablero.visualizarTablero();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un número entero.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            scanner.nextLine();
        }
    }
}
