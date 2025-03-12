import chess.ChessSimulator;

/**
 * Clase principal que ejecuta el simulador de ajedrez.
 * Esta clase es la encargada de crear una instancia del simulador y ejecutarlo.
 *
 */
public class Main {
    public static void main(String[] args) {
        ChessSimulator simulator = new ChessSimulator();
        simulator.ejecutar();
    }
}
