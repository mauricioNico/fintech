package modelo;

import java.time.LocalDateTime;

public class Movimiento {
    private String tipo;
    private double monto;
    private LocalDateTime fecha;

    public Movimiento(String tipo, double monto) {
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "[" + fecha + "] " + tipo + ": $" + monto;
    }
}
