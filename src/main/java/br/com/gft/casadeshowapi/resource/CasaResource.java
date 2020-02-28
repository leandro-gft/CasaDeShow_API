package br.com.gft.casadeshowapi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.casadeshowapi.domain.Casa;
import br.com.gft.casadeshowapi.service.CasaService;



@RestController
@RequestMapping("/casas")
public class CasaResource {
	
	@Autowired
	private CasaService casasService;
	
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<List<Casa>> listar() {
		List<Casa> casas = casasService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(casas);
		
	//	return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar (@Valid @RequestBody Casa casa) {
		casa = casasService.salvar(casa);
			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(casa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();					
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Casa> buscar(@PathVariable("id")Long id){
//		Casa casa = casasService.buscar(id); 
//		return ResponseEntity.status(HttpStatus.OK).body(casa);
		return ResponseEntity.status(HttpStatus.OK).body(casasService.buscar(id));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable("id") Long id) {
		/*
		 * Livro livro = livros.findById(id).orElse(null); if(livro == null) { return
		 * ResponseEntity.notFound().build(); //retorna um 404, senão retorna um 200 OK
		 * e o corpo da mensagem } livros.deleteById(id); return
		 * ResponseEntity.noContent().build(); }
		 */
		
		casasService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@RequestBody Casa casa, @PathVariable("id") Long id) {
		casa.setId(id);
		casasService.atualizar(casa);
		return ResponseEntity.noContent().build();
	}

	// http://tools.ietf.org/html/rfc7231 : documentação para consultar como tratar
	// respostas adequadamente
	

}
