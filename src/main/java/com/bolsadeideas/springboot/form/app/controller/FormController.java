package com.bolsadeideas.springboot.form.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FormController {
	
	// Handler para mostrar el formulario
	@GetMapping("/form")
	public String form(Model model) {
		return "form";
	}
	
	// Handler para recibir los datos
	@PostMapping("/form")
	public String procesar(Model model) {
		return "resultado";
	}
}
