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

import br.com.gft.casadeshowapi.domain.Role;
import br.com.gft.casadeshowapi.service.RoleService;
import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping("/roles")
public class RoleResource {

	@Autowired
	private RoleService rolesService;
	
	@ApiOperation("Lista os tipos de permissões cadastradas.")
	@GetMapping
	public ResponseEntity<List<Role>> Listar() {
		return ResponseEntity.status(HttpStatus.OK).body(rolesService.listar());
	}
	@ApiOperation("Busca um tipo de permissão de acordo com seu id.")
	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) { // ResponseEntity é responsavel por encapsular o
																	// objeto de retorno (no caso livro) e manipular
																	// informações do HTTP. A '?' significa que pode
																	// manipular qualqeur tipo de objeto
		
		Role role =  rolesService.buscar(id); 
		return ResponseEntity.status(HttpStatus.OK).body(role); // posso setar o status e o corpo da resposta
	}
	
	
	@ApiOperation("Cadastra um novo tipo de permissão.")
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Role role) { // @RequestBody pega as informações da requisição e
																	// coloca no objeto livro
		role = rolesService.salvar(role);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(role.getId()).toUri();
		// para o cliente saber como ele acessa o recurso criado

		return ResponseEntity.created(uri).build(); // cria a resposta da forma correta (201 created, que serve para, ao
													// salvar o recurso, informar o cliente onde ele pode obter mais
													// informações sobre esse recurso)
	}

}
