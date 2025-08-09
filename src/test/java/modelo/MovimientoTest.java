package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MovimientoTest {

    @Test
    void constructorSeteaTipoMontoYFecha() {
        Movimiento m = new Movimiento("Depósito", 500.0);
        String s = m.toString();

        // Sin getters, validamos vía toString
        assertTrue(s.contains("Depósito"));
        assertTrue(s.contains("500.0"));
        assertTrue(s.startsWith("[")); // incluye fecha al comienzo
        assertTrue(s.contains("]"));   // cierre del bloque de fecha
    }
}

