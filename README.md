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

## Validación del formulario usando anotaciones (@Valid & BindingResult)

En la clase Usuario añadimos la anotación @NotEmpty en cada atributo para asegurarnos 
de no recibir un valor vacío. 

```java
public class Usuario {
	
	@NotEmpty
	private String username;
	
	@NotEmpty
	private String password;
	
	@NotEmpty
	private String email;
}
```

El objeto **usuario** llega como parámetro del método 'procesar' con los datos del formulario. 
Para validar el objeto antes de pasarlo al método hay que anotar el parámetro con @Valid.  


```java
@PostMapping("/form")
public String procesar(@Valid Usuario usuario, BindingResult result,Model model) {
		
	model.addAttribute("titulo", "Resultado form");
		
	if(result.hasErrors()) {
		Map<String, String> errores = new HashMap<>();
		//BindingResult representa el resultado de la validación, osea contiene los mensajes de error en caso de que ocurran
		result.getFieldErrors().forEach(err -> {
			errores.put(err.getField(), "El campo ".concat(err.getField()).concat(err.getDefaultMessage()));
		});
		// Añadimos los mensajes de error a la vista
		model.addAttribute("error", errores);
		return "form";
	}
		
	model.addAttribute("usuario", usuario);
	return "resultado";
}
```

## Añadiendo mensajes de errores a la vista del formulario

```html
<div>
	<label for="username">Nombre de usuario</label>
	<div>
		<input type="text" name="username" id="username" th:value="${usuario.username}"></input>
		</div>
	<div th:if="${error != null && error.containsKey('username')}" th:text="${error.username}"></div>
</div>
``

## La anotación @SessionAttributes para mantener los datos durante el ciclo del form

```java
@Controller
@SessionAttributes("usuario")
public class FormController {
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult result,Model model, SessionStatus status) {
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
```
# Validaciones

## Anotaciones validadoras @Size y @Email

```java
public class Usuario {

	@Size(min = 3, max = 8)
	private String username;
	
	@Email
	private String email;
}
```

## Mensajes de errores personalizados usando properties

Creamos un archivo dentro de **src/main/resources** > **new** > **File** > 'messages.properties'.
Y configuramos lo siguiente:

- Comenzando con el nombre de la etiqueta de modo general
- Si se requiere de un atributo en especifico, se añade el objeto y atributo
```
NotEmpty=El campo es requerido
NotEmpty.usuario.nombre=El campo nombre es requerido
NotEmpty.usuario.apellido=El campo apellido es requerido
Email.usuario.email=El formato del correo es incorrecto
```

## Validación personalizada usando @Pattern para expresiones regulares

En este caso se requiere validar solo un formato de identificador, para lo cual usando @Pattern 
definiremos los posibles rangos y la cantidad digitos.Por ejemplo: 12.345.678-k

```java
public class Usuario {
	@Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.][\\d]{3}[-][a-zA-Z]{1}")
	private String identificador;
}
```

