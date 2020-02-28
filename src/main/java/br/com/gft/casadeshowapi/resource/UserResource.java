package br.com.gft.casadeshowapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.casadeshowapi.domain.User;
import br.com.gft.casadeshowapi.service.UserService;

@RestController
@RequestMapping("/users")
public class UserResource {

	@Autowired
	private UserService usersService;
	 
	@GetMapping
	public ResponseEntity<List<User>> Listar() {
		return ResponseEntity.status(HttpStatus.OK).body(usersService.listar());
	}
	
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
	
	@GetMapping("/{id}")
	public ResponseEntity<User> buscar(@PathVariable("id")Long id){
//		Casa casa = casasService.buscar(id); 
//		return ResponseEntity.status(HttpStatus.OK).body(casa);
		return ResponseEntity.status(HttpStatus.OK).body(usersService.buscar(id));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		/*
		 * Livro livro = livros.findById(id).orElse(null); if(livro == null) { return
		 * ResponseEntity.notFound().build(); //retorna um 404, senão retorna um 200 OK
		 * e o corpo da mensagem } livros.deleteById(id); return
		 * ResponseEntity.noContent().build(); }
		 */
		
		usersService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody User user, @PathVariable("id") Long id) {
		user.setId(id);
		usersService.atualizar(user);
		return ResponseEntity.noContent().build();
	}

}
