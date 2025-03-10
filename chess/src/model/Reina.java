package model;

public class Reina extends Pieza {

        public Reina(String color, String posicion) {
            super(color, posicion);
        }

        @Override
        public boolean mover(String nuevaPosicion) {
            // Lógica para mover la Reina
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
            return "Reina";
        }
    }
