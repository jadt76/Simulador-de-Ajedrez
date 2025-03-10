package model;

public class Caballo extends Pieza {

        public Caballo(String color, String posicion) {
            super(color, posicion);
        }

        @Override
        public boolean mover(String nuevaPosicion) {
            // Lógica para mover el Caballo
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
            return "Caballo";
        }
    }
