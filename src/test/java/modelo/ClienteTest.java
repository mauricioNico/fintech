package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClienteTest {

    @Test
    void constructorInicializaCampos() {
        Cliente c = new Cliente("Juan Pérez", "12345678");
        assertEquals("Juan Pérez", c.getNombre());
        assertEquals("12345678", c.getDni());
        assertEquals("Juan Pérez (DNI: 12345678)", c.toString());
    }
}

