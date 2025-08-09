// src/main/java/com/fintechbanco/controller/TransferenciaController.java
package com.fintechbanco.controller;

import com.fintechbanco.dto.TransferenciaDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import servicio.BancoService;

@Controller
@RequestMapping("/transferencia")
public class TransferenciaController {

    private final BancoService banco;

    public TransferenciaController(BancoService banco) {
        this.banco = banco;
    }

@GetMapping
public String formTransferencia() {
    return "transferencia"; // Muestra transferencia.html
}

    @PostMapping
    public String transferir(@ModelAttribute TransferenciaDTO dto, Authentication auth, Model model) {
        boolean ok = banco.transferir(auth.getName(), dto.getDestinoUsername(), dto.getMonto());
        if (!ok) {
            model.addAttribute("error", "No se pudo realizar la transferencia (saldo insuficiente o usuario destino inválido).");
            return "transferencia";
        }
        return "redirect:/cuenta";
    }
}

