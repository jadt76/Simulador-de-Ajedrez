package model;

public class Torre extends Pieza {

        public Torre(String color, String posicion) {
            super(color, posicion);
        }

        @Override
        public boolean mover(String nuevaPosicion) {
            // Lógica para mover la Torre
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
            return "Torre";
        }
    }
