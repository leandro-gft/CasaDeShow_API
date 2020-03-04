package br.com.gft.casadeshowapi.resource;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.casadeshowapi.domain.Evento;
import br.com.gft.casadeshowapi.service.EventoService;

@RestController
@RequestMapping("/api/eventos")
public class EventoResource {

	@Autowired
	private EventoService eventosService;

	@GetMapping
	public ResponseEntity<List<Evento>> Listar() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listar());
	}
	
	@GetMapping("/nome/asc")
	public ResponseEntity<List<Evento>> ListarAsc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarAsc());
	}
	
	@GetMapping("/nome/desc")
	public ResponseEntity<List<Evento>> ListarDesc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.listarDesc());
	}
	
	@GetMapping("/capacidade/asc")
	public ResponseEntity<List<Evento>> capacidadeAsc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.capacidadeAsc());
	}
	
	@GetMapping("/capacidade/desc")
	public ResponseEntity<List<Evento>> capacidadeDesc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.capacidadeDesc());
	}
	
	@GetMapping("/data/asc")
	public ResponseEntity<List<Evento>> dataAsc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.dataAsc());
	}
	
	@GetMapping("/data/desc")
	public ResponseEntity<List<Evento>> dataDesc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.dataDesc());
	}
	
	@GetMapping("/preco/asc")
	public ResponseEntity<List<Evento>> precoAsc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.precoAsc());
	}
	
	@GetMapping("/preco/desc")
	public ResponseEntity<List<Evento>> precoDesc() { 
		return ResponseEntity.status(HttpStatus.OK).body(eventosService.precoDesc());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar(@Valid @RequestBody Evento evento) { // @RequestBody pega as informações da requisição e
																	// coloca no objeto livro
		evento = eventosService.salvar(evento);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(evento.getId()).toUri();
		// para o cliente saber como ele acessa o recurso criado

		return ResponseEntity.created(uri).build(); // cria a resposta da forma correta (201 created, que serve para, ao
													// salvar o recurso, informar o cliente onde ele pode obter mais
													// informações sobre esse recurso)
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) { // ResponseEntity é responsavel por encapsular o
																	// objeto de retorno (no caso livro) e manipular
																	// informações do HTTP. A '?' significa que pode
																	// manipular qualqeur tipo de objeto
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS); //VALIDADE DE 20 segundos
		Evento evento =  eventosService.buscar(id); 
		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(evento); // posso setar o status e o corpo da resposta
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		/*
		 * Livro livro = livros.findById(id).orElse(null); if(livro == null) { return
		 * ResponseEntity.notFound().build(); //retorna um 404, senão retorna um 200 OK
		 * e o corpo da mensagem } livros.deleteById(id); return
		 * ResponseEntity.noContent().build(); }
		 */
		
		eventosService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Evento evento, @PathVariable("id") Long id) {
		evento.setId(id);
		eventosService.atualizar(evento);
		return ResponseEntity.noContent().build();
	}

	// http://tools.ietf.org/html/rfc7231 : documentação para consultar como tratar
	// respostas adequadamente
	
}
