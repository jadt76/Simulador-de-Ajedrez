package model;

public class Rey extends Pieza {

        public Rey(String color, String posicion) {
            super(color, posicion);
        }

        @Override
        public boolean mover(String nuevaPosicion) {
            // Lógica para mover el Rey
            // Aquí se debería validar si el movimiento es válido según las reglas del ajedrez
            setPosicion(nuevaPosicion);
            return true;
        }

        @Override
        public boolean capturar(Pieza otraPieza) {
            // Lógica para capturar otra pieza
            return false;
        }

        @Override
        public String tipoPieza() {
            return "Rey";
        }
    }
