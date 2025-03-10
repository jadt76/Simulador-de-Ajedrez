package model;

public class Alfil extends Pieza {

        public Alfil(String color, String posicion) {
            super(color, posicion);
        }

        @Override
        public boolean mover(String nuevaPosicion) {
            // Lógica para mover el Alfil
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
            return "Alfil";
        }
    }
