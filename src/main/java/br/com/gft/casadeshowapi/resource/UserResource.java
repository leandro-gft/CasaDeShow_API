package br.com.gft.casadeshowapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.casadeshowapi.domain.User;
import br.com.gft.casadeshowapi.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/users")
public class UserResource {

	@Autowired
	private UserService usersService;
	 
	@ApiOperation("Lista os usuários cadastrados.")
	@GetMapping
	public ResponseEntity<List<User>> Listar() {
		return ResponseEntity.status(HttpStatus.OK).body(usersService.listar());
	}
	
	@ApiOperation("Cadastra um novo usuário.")
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody User user) { // @RequestBody pega as informações da requisição e
																	// coloca no objeto livro
		user = usersService.salvar(user);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
		// para o cliente saber como ele acessa o recurso criado

		return ResponseEntity.created(uri).build(); // cria a resposta da forma correta (201 created, que serve para, ao
													// salvar o recurso, informar o cliente onde ele pode obter mais
													// informações sobre esse recurso)
	}
	
	@ApiOperation("Busca um usuário de acordo com seu id.")
	@GetMapping("/{id}")
	public ResponseEntity<User> buscar(@PathVariable("id")Long id){
		return ResponseEntity.status(HttpStatus.OK).body(usersService.buscar(id));
		
	}

}
