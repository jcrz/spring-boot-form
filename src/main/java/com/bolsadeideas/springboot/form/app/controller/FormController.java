package com.bolsadeideas.springboot.form.app.controller;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.domain.Usuario;
import com.bolsadeideas.springboot.form.app.validation.UsuarioValidador;

@Controller
@SessionAttributes("usuario")
public class FormController {
	
	@Autowired
	private UsuarioValidador validador;
	
	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("Carlos");
		usuario.setApellido("mono");
		usuario.setIdentificador("123.456.789-k");
		model.addAttribute("titulo", "Formulario usuarios");
		model.addAttribute("usuario", usuario);
		return "form";
	}
	
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result,Model model, SessionStatus status) {
		
		validador.validate(usuario, result);
		
		model.addAttribute("titulo", "Resultado form");
		if(result.hasErrors()) {
			return "form";
		}
		model.addAttribute("usuario", usuario);
		//Completa el proceso y elimina el objeto usuario de la sesión
		status.setComplete();
		return "resultado";
	}
}
