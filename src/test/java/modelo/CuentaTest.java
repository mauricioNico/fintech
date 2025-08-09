package modelo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class CuentaTest {

    // Resetea contadorCuentas a 1000 antes de cada test para resultados deterministas
    @BeforeEach
    void resetContador() {
        try {
            Field f = Cuenta.class.getDeclaredField("contadorCuentas");
            f.setAccessible(true);
            f.setInt(null, 1000);
        } catch (Exception e) {
            fail("No se pudo resetear contadorCuentas: " + e.getMessage());
        }
    }

    @Test
    void constructorInicializaNumeroSaldoYCliente() {
        Cliente cli = new Cliente("Ana", "87654321");
        Cuenta c = new Cuenta(cli);

        assertNotNull(c);
        assertEquals(cli, c.getCliente());
        assertEquals(0.0, c.getSaldo());
        assertTrue(c.getNumero() >= 1000);
        assertTrue(c.getMovimientos().isEmpty());
    }

    @Test
    void numerosDeCuentaSonSecuenciales() {
        Cliente cli = new Cliente("Ana", "87654321");
        Cuenta c1 = new Cuenta(cli);
        Cuenta c2 = new Cuenta(cli);

        assertEquals(c1.getNumero() + 1, c2.getNumero());
    }

    @Test
    void depositarAumentaSaldoYRegistraMovimiento() {
        Cuenta c = new Cuenta(new Cliente("Pepe", "111"));
        c.depositar(250.0);

        assertEquals(250.0, c.getSaldo());
        assertEquals(1, c.getMovimientos().size());
        String movStr = c.getMovimientos().get(0).toString();
        assertTrue(movStr.contains("Depósito"));
        assertTrue(movStr.contains("250"));
    }

    @Test
    void retirarConSaldoSuficienteDisminuyeSaldoYRegistraMovimiento() {
        Cuenta c = new Cuenta(new Cliente("Pepe", "111"));
        c.depositar(300.0);

        boolean ok = c.retirar(100.0);

        assertTrue(ok);
        assertEquals(200.0, c.getSaldo());
        assertEquals(2, c.getMovimientos().size());
        assertTrue(c.getMovimientos().get(1).toString().contains("Retiro"));
    }

    @Test
    void retirarConSaldoInsuficienteNoCambiaSaldoNiMovimientos() {
        Cuenta c = new Cuenta(new Cliente("Pepe", "111"));
        c.depositar(50.0);

        boolean ok = c.retirar(100.0);

        assertFalse(ok);
        assertEquals(50.0, c.getSaldo());
        assertEquals(1, c.getMovimientos().size()); // solo el depósito
    }

    // Nota: actualmente tu implementación permite depósitos negativos (bajan el saldo).
    // Si luego agregás validaciones, este test debería ajustarse (o añadirse otro que espere excepción).
    @Test
    void depositarNegativoDisminuyeSaldo_SegunImplementacionActual() {
        Cuenta c = new Cuenta(new Cliente("Pepe", "111"));
        c.depositar(-20.0);
        assertEquals(-20.0, c.getSaldo());
    }
}
