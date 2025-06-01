package com.exercicio.conversao.controller;

import com.exercicio.conversao.model.Conversao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@Controller
public class ConversaoController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/")
    public String mostrarFormulario(Model model) {
        model.addAttribute("conversao", new Conversao());
        return "formulario";
    }

    @PostMapping("/")
    public String processarConversao(@ModelAttribute Conversao conversao, Model model, Locale locale) {
        try {
            String resultado = conversao.calcularResultado(messageSource, locale);
            model.addAttribute("resultado", resultado);
            return "resultado";
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", messageSource.getMessage("mensagem.erro", null, locale));
            return "erro";
        }
    }
}
