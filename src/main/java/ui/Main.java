package ui;

import modelo.Cliente;
import modelo.Cuenta;
import modelo.Movimiento;
import servicio.BancoService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BancoService banco = new BancoService();
        int opcion;
        

        do {
            System.out.println("\n=== BANCO FINTECH ===");
            System.out.println("1. Crear cliente y cuenta");
            System.out.println("2. Depositar");
            System.out.println("3. Retirar");
            System.out.println("4. Ver movimientos");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpia buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombre = scanner.nextLine();
                    System.out.print("DNI del cliente: ");
                    String dni = scanner.nextLine();
                    Cliente cliente = banco.crearCliente(nombre, dni);
                    Cuenta cuenta = banco.crearCuentaParaCliente(cliente);
                    System.out.println("Cuenta creada: " + cuenta);
                    break;

                case 2:
                    System.out.print("Número de cuenta: ");
                    int numCuentaDep = scanner.nextInt();
                    System.out.print("Monto a depositar: ");
                    double montoDep = scanner.nextDouble();
                    Cuenta cuentaDep = banco.buscarCuenta(numCuentaDep);
                    if (cuentaDep != null) {
                        cuentaDep.depositar(montoDep);
                        System.out.println("Depósito exitoso.");
                    } else {
                        System.out.println("Cuenta no encontrada.");
                    }
                    break;

                case 3:
                    System.out.print("Número de cuenta: ");
                    int numCuentaRet = scanner.nextInt();
                    System.out.print("Monto a retirar: ");
                    double montoRet = scanner.nextDouble();
                    Cuenta cuentaRet = banco.buscarCuenta(numCuentaRet);
                    if (cuentaRet != null && cuentaRet.retirar(montoRet)) {
                        System.out.println("Retiro exitoso.");
                    } else {
                        System.out.println("Error: saldo insuficiente o cuenta inválida.");
                    }
                    break;

                case 4:
                    System.out.print("Número de cuenta: ");
                    int numCuentaMov = scanner.nextInt();
                    Cuenta cuentaMov = banco.buscarCuenta(numCuentaMov);
                    if (cuentaMov != null) {
                        System.out.println("Movimientos de la cuenta:");
                        for (Movimiento m : cuentaMov.getMovimientos()) {
                            System.out.println(m);
                        }
                    } else {
                        System.out.println("Cuenta no encontrada.");
                    }
                    break;

                case 5:
                    System.out.println("Gracias por usar el sistema.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 5);

        scanner.close();
    }
}
