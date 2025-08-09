package servicio;

import modelo.Cliente;
import modelo.Cuenta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BancoServiceTest {

    private BancoService banco;

    @BeforeEach
    void setUp() {
        banco = new BancoService();
    }

    @Test
    void crearClienteLoAgregaYDevuelveInstancia() {
        Cliente c = banco.crearCliente("Juan", "123");
        assertNotNull(c);
        assertEquals("Juan", c.getNombre());
        assertEquals("123", c.getDni());
    }

    @Test
    void crearCuentaParaClienteAgregaYDevuelveCuenta() {
        Cliente cli = banco.crearCliente("Ana", "456");
        Cuenta cuenta = banco.crearCuentaParaCliente(cli);

        assertNotNull(cuenta);
        assertEquals(cli, cuenta.getCliente());
        assertTrue(banco.getCuentas().contains(cuenta));
    }

    @Test
    void buscarCuentaExistenteLaDevuelve() {
        Cliente cli = banco.crearCliente("Ana", "456");
        Cuenta c = banco.crearCuentaParaCliente(cli);

        Cuenta encontrada = banco.buscarCuenta(c.getNumero());
        assertNotNull(encontrada);
        assertEquals(c, encontrada);
    }

    @Test
    void buscarCuentaInexistenteDevuelveNull() {
        assertNull(banco.buscarCuenta(999999));
    }
}

