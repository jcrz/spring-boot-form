# Apuntes del proyecto


## Clase model del formulario

Añadir link al archivo Usuario.java

## Método handler para recibir y procesar los datos enviados

```java
	// Handler para mostrar el formulario
	@GetMapping("/form")
	public String form(Model model) {
		model.addAttribute("titulo", "Formulario usuarios");
		return "form";
	}
	
	// Handler para recibir los datos utilizando @RequestParam
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
```

## Método handler automatizado

```java
	// Handler automatizado para recibir los datos
	@PostMapping("/form")
	public String procesar(Usuario usuario, Model model) {
		model.addAttribute("titulo", "Resultado form");
		model.addAttribute("usuario", usuario);
		return "resultado";
	}
```
