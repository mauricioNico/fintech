package com.fintechbanco.controller;

import com.fintechbanco.dto.DepositoDTO;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import servicio.BancoService;

@Controller
@RequestMapping("/cuenta")
public class CuentaController {

    private final BancoService banco;

    public CuentaController(BancoService banco) {
        this.banco = banco;
    }

    @GetMapping
    public String ver(Authentication auth, Model model) {
        var cuenta = banco.obtenerCuentaPorUsername(auth.getName());
        model.addAttribute("cuenta", cuenta);
        return "cuenta"; // templates/cuenta.html
    }

    @PostMapping("/deposito")
    public String depositar(@ModelAttribute DepositoDTO dto, Authentication auth) {
        banco.depositar(auth.getName(), dto.getMonto());
        return "redirect:/cuenta";
    }
}

