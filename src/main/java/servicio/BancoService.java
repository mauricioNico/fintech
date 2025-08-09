package servicio;

import modelo.Cliente;
import modelo.Cuenta;

import java.util.*;

/**
 * Servicio que administra clientes, cuentas y operaciones bancarias.
 */
public class BancoService {
    private List<Cliente> clientes = new ArrayList<>();
    private List<Cuenta> cuentas = new ArrayList<>();
    private Map<String, Cuenta> cuentasPorUsuario = new HashMap<>();

    public Cliente crearCliente(String nombre, String dni) {
        Cliente cliente = new Cliente(nombre, dni);
        clientes.add(cliente);
        return cliente;
    }

    public Cuenta crearCuentaParaCliente(Cliente cliente) {
        Cuenta cuenta = new Cuenta(cliente);
        cuentas.add(cuenta);
        return cuenta;
    }

    public Cuenta buscarCuenta(int numero) {
        for (Cuenta c : cuentas) {
            if (c.getNumero() == numero) return c;
        }
        return null;
    }

    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    // ===== Métodos extra para la aplicación web =====
    public void vincularUsuarioConCuenta(String username, Cuenta cuenta) {
        cuentasPorUsuario.put(username, cuenta);
    }

    public Cuenta obtenerCuentaPorUsername(String username) {
        return cuentasPorUsuario.get(username);
    }

    public void depositar(String username, double monto) {
        Cuenta cuenta = obtenerCuentaPorUsername(username);
        if (cuenta != null) {
            cuenta.depositar(monto);
        }
    }

    public boolean transferir(String origenUsername, String destinoUsername, double monto) {
        Cuenta origen = obtenerCuentaPorUsername(origenUsername);
        Cuenta destino = obtenerCuentaPorUsername(destinoUsername);

        if (origen != null && destino != null && origen.retirar(monto)) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }
}

