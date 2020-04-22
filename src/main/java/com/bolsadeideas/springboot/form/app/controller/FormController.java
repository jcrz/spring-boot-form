package com.bolsadeideas.springboot.form.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;

@Controller
public class FormController {
	
	// Handler para mostrar el formulario
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		return "form";
	}
	
	// Handler para recibir los datos utilizando @RequestParam
	/*
	@PostMapping("/form")
	public String procesar(Model model, 
			@RequestParam String username,
			@RequestParam String password,
			@RequestParam String email) {
		
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(password);
		usuario.setEmail(email);
		
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("usuario", usuario);
		
		return "resultado";
	}
	*/
	
	// Handler automatizado para recibir los datos
	@PostMapping("/form")
	public String procesar(Usuario usuario, Model model) {
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("usuario", usuario);
		return "resultado";
	}
}
