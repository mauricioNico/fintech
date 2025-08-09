package modelo;

import java.util.ArrayList;
import java.util.List;

public class Cuenta {
    private static int contadorCuentas = 1000;

    private int numero;
    private Cliente cliente;
    private double saldo;
    private List<Movimiento> movimientos;

    public Cuenta(Cliente cliente) {
        this.numero = contadorCuentas++;
        this.cliente = cliente;
        this.saldo = 0.0;
        this.movimientos = new ArrayList<>();
    }

    public int getNumero() { return numero; }
    public Cliente getCliente() { return cliente; }
    public double getSaldo() { return saldo; }

    public void depositar(double monto) {
        saldo += monto;
        movimientos.add(new Movimiento("Depósito", monto));
    }

    public boolean retirar(double monto) {
        if (monto <= saldo) {
            saldo -= monto;
            movimientos.add(new Movimiento("Retiro", monto));
            return true;
        }
        return false;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    @Override
    public String toString() {
        return "Cuenta Nº " + numero + " - Cliente: " + cliente.getNombre() + " - Saldo: $" + saldo;
    }
}
