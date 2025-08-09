// src/main/java/com/fintechbanco/FintechBancoApplication.java
package com.fintechbanco;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import servicio.BancoService;
import modelo.Cliente;

@SpringBootApplication
public class FintechBancoApplication {
    public static void main(String[] args) {
        SpringApplication.run(FintechBancoApplication.class, args);
    }

    @Bean
    public BancoService bancoService() {
        BancoService b = new BancoService();

        // Usuarios de prueba (mismos usernames que SecurityConfig)
        Cliente ana  = b.crearCliente("ana",  "111");
        Cliente juan = b.crearCliente("juan", "222");

        // Crear cuentas y vincular a username
        var cAna  = b.crearCuentaParaCliente(ana);
        var cJuan = b.crearCuentaParaCliente(juan);
        b.vincularUsuarioConCuenta("ana",  cAna);
        b.vincularUsuarioConCuenta("juan", cJuan);

        // Saldos iniciales
        b.depositar("ana", 1000);
        b.depositar("juan", 500);

        return b;
    }
}
