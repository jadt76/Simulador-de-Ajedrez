package chess;

import model.*;
import Sorting.*;
import util.Tablero;
import util.Tablero.AlgoritmoOrdenamiento;
import util.Validador;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Random;

/**
 * Clase principal que simula un juego de ajedrez en consola.
 * Esta clase es la encargada de mostrar un menú de opciones al usuario y ejecutar las acciones correspondientes.
 */
public class ChessSimulator {
    // Constantes para las opciones del menú
    private static final int OPCION_CONFIGURAR_PIEZAS = 1;
    private static final int OPCION_SELECCIONAR_ALGORITMO = 2;
    private static final int OPCION_VISUALIZAR_TABLERO = 3;
    private static final int OPCION_MOVER_PIEZA = 4;
    private static final int OPCION_SALIR = 5;

    private final Tablero tablero;

    /**
     * Constructor de la clase ChessSimulator.
     */
    public ChessSimulator() {
        tablero = new Tablero(); //Inicializar el tablero
        inicializarTablero();  //Colocar las piezas en sus posiciones iniciales
    }

    public void ejecutar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenido al simulador de ajedrez en consola.");

        while (true) {
            try {
                mostrarMenu();
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
    // Método para mostrar el menú
    private void mostrarMenu() {
        System.out.println("\nSeleccione una opción:");
        System.out.println(OPCION_CONFIGURAR_PIEZAS + ". Configurar piezas en el tablero");
        System.out.println(OPCION_SELECCIONAR_ALGORITMO + ". Seleccionar algoritmo de ordenamiento");
        System.out.println(OPCION_VISUALIZAR_TABLERO + ". Visualizar tablero");
        System.out.println(OPCION_MOVER_PIEZA + ". Mover una pieza");
        System.out.println(OPCION_SALIR + ". Salir");
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
                System.out.println("Error: Opción no válida. Seleccione 1 para blancas o 2 para negras.");
                return;
            }
            Tablero.Color color = (opcionColor == 1) ? Tablero.Color.BLANCO : Tablero.Color.NEGRO;

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
                System.out.println("Error: Opción no válida. Seleccione entre 1 y 7.");
                return;
            }

            tablero.limpiarTablero();
            Random random = new Random();

            switch (opcionPieza) {
                case 1:
                    agregarPieza(color, Tablero.TipoPieza.Rey, 1, random);
                    break;
                case 2:
                    agregarPieza(color, Tablero.TipoPieza.Reina, 1, random);
                    break;
                case 3:
                    agregarPieza(color, Tablero.TipoPieza.Torre, 2, random);
                    break;
                case 4:
                    agregarPieza(color, Tablero.TipoPieza.Alfil, 2, random);
                    break;
                case 5:
                    agregarPieza(color, Tablero.TipoPieza.Caballo, 2, random);
                    break;
                case 6:
                    agregarPieza(color, Tablero.TipoPieza.Peon, 8, random);
                    break;
                case 7:
                    inicializarPiezas(color, random);
                    break;
            }

            tablero.visualizarTablero();
        } catch (InputMismatchException e) {
            System.out.println("Error: Por favor, ingrese un número entero.");
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Error inesperado: " + e.getMessage());
            scanner.nextLine();
        }
    }

    // Método para mover una pieza
    private void moverPieza(Scanner scanner) {
        System.out.println("Ingrese el movimiento (ejemplo: a2-a4):");
        String movimiento = scanner.nextLine();

        if (!movimiento.matches("[a-h][1-8]-[a-h][1-8]")) {
            System.out.println("Formato de movimiento no válido. Ingrese dos posiciones separadas por un guion.");
            return;
        }

        String[] partes = movimiento.split("-");
        String origen = partes[0];
        String destino = partes[1];

        Pieza pieza = tablero.obtenerPiezaEnPosicion(origen);
        if (pieza == null) {
            System.out.println("No hay ninguna pieza en la posición de origen.");
            return;
        }

        if (!Validador.esMovimientoValido(pieza, destino)) {
            System.out.println("Movimiento no válido para la pieza seleccionada.");
            return;
        }
        pieza.mover(destino);
        tablero.colocarPieza(pieza, destino);
        System.out.println("Movimiento realizado con éxito.");
        tablero.visualizarTablero();
    }

    private void inicializarTablero() {
        // Colocar piezas blancas
        colocarPiezasIniciales(Tablero.Color.BLANCO, 0, 1);
        // Colocar piezas negras
        colocarPiezasIniciales(Tablero.Color.NEGRO, 7, 6);
    }
    private void colocarPiezasIniciales(Tablero.Color color, int filaPiezas, int filaPeones) {
        // Colocar torres
        tablero.colocarPieza(new Torre(color, "a" + (8 - filaPiezas)), "a" + (8 - filaPiezas));
        tablero.colocarPieza(new Torre(color, "h" + (8 - filaPiezas)), "h" + (8 - filaPiezas));
        // Colocar caballos
        tablero.colocarPieza(new Caballo(color, "b" + (8 - filaPiezas)), "b" + (8 - filaPiezas));
        tablero.colocarPieza(new Caballo(color, "g" + (8 - filaPiezas)), "g" + (8 - filaPiezas));
        // Colocar alfiles
        tablero.colocarPieza(new Alfil(color, "c" + (8 - filaPiezas)), "c" + (8 - filaPiezas));
        tablero.colocarPieza(new Alfil(color, "f" + (8 - filaPiezas)), "f" + (8 - filaPiezas));
        // Colocar reina
        tablero.colocarPieza(new Reina(color, "d" + (8 - filaPiezas)), "d" + (8 - filaPiezas));
        // Colocar rey
        tablero.colocarPieza(new Rey(color, "e" + (8 - filaPiezas)), "e" + (8 - filaPiezas));
        // Colocar peones
        for (char col = 'a'; col <= 'h'; col++) {
            String posicion = col + String.valueOf(8 - filaPeones);
            tablero.colocarPieza(new Peon(color, posicion), posicion);
        }
    }

    // Método para agregar una pieza al tablero
    private void agregarPieza(Tablero.Color color, Tablero.TipoPieza tipo, int cantidad, Random random) {
        for (int i = 0; i < cantidad; i++) {
            String posicion = generarPosicionAleatoria(random);
            Pieza pieza = crearPieza(color, tipo, posicion);
            tablero.colocarPieza(pieza, generarPosicionAleatoria(random));
            //agregar pieza adicional
            Tablero.TipoPieza tipoAleatorio = obtenerTipoPiezaAleatorio(random);
            String posicionAleatoria = generarPosicionAleatoria(random);
            Pieza piezaAleatoria = crearPieza(color, tipoAleatorio, posicionAleatoria);
            tablero.colocarPieza(piezaAleatoria, generarPosicionAleatoria(random));
        }
    }

    private Tablero.TipoPieza obtenerTipoPiezaAleatorio(Random random) {
        Tablero.TipoPieza[] tipos = Tablero.TipoPieza.values();
        return tipos[random.nextInt(tipos.length)];
    }

    private String generarPosicionAleatoria(Random random) {
        String posicion;
        do {
            int fila = random.nextInt(8);
            int columna = random.nextInt(8);
            posicion = (char) ('a' + columna) + String.valueOf(8 - fila);
        } while (tablero.obtenerPiezaEnPosicion(posicion) != null); // Verificar que la posición no esté ocupada
        return posicion;
    }

    // Método para crear una pieza según el tipo
    private Pieza crearPieza(Tablero.Color color, Tablero.TipoPieza tipo, String posicion) {
        return switch (tipo) {
            case Rey -> new Rey(color, posicion);
            case Reina -> new Reina(color, posicion);
            case Torre -> new Torre(color, posicion);
            case Alfil -> new Alfil(color, posicion);
            case Caballo -> new Caballo(color, posicion);
            case Peon -> new Peon(color, posicion);
        };
    }

    // Método para inicializar todas las piezas
    private void inicializarPiezas(Tablero.Color color, Random random) {
        agregarPieza(color, Tablero.TipoPieza.Rey, 1, random);
        agregarPieza(color, Tablero.TipoPieza.Reina, 1, random);
        agregarPieza(color, Tablero.TipoPieza.Torre, 2, random);
        agregarPieza(color, Tablero.TipoPieza.Alfil, 2, random);
        agregarPieza(color, Tablero.TipoPieza.Caballo, 2, random);
        agregarPieza(color, Tablero.TipoPieza.Peon, 8, random);
    }

    // Método para seleccionar un algoritmo de ordenamiento
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
            AlgoritmoOrdenamiento algoritmo = switch (opcionAlgoritmo) {
                case 1 -> new BubbleSort();
                case 2 -> new SelectionSort();
                case 3 -> new InsertionSort();
                case 4 -> new MergeSort();
                case 5 -> new QuickSort();
                case 6 -> new ShellSort();
                default -> throw new IllegalArgumentException("Opción no válida.");
            };

            algoritmo.ordenar(tablero.getPiezas(), tablero);
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
