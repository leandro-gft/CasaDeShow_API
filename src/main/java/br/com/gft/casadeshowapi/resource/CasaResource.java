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
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;



@RestController
@RequestMapping("/api/casas")
public class CasaResource {
	
	@Autowired
	private CasaService casasService;
	
	@ApiOperation("Lista as casas de show cadastradas.")
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
	
	@ApiOperation("Lista as casas de show cadastradas em ordem crescente.")
	@RequestMapping(
			value="/asc", method = RequestMethod.GET)
	public ResponseEntity<List<Casa>> listarasc() {
		List<Casa> casas = casasService.listarasc();
		return ResponseEntity.status(HttpStatus.OK).body(casas);		
	}
	
	@ApiOperation("Lista as casas de show cadastradas em ordem decrescente.")
	@RequestMapping(value="/desc",
			method = RequestMethod.GET)
	public ResponseEntity<List<Casa>> listardesc() {
		List<Casa> casas = casasService.listardesc();
		return ResponseEntity.status(HttpStatus.OK).body(casas);
	}
	
	@ApiOperation("Cadastra uma nova casa de show.")
	@PostMapping
	public ResponseEntity<Void> salvar (@ApiParam(name="corpo", value="Representação de uma nova casa de show.") @Valid @RequestBody Casa casa) {
		casa = casasService.salvar(casa);
			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(casa.getId()).toUri();
		
		return ResponseEntity.created(uri).build();					
	
	}
	
	@ApiOperation("Busca uma casa de show de acordo com seu id.")
	@GetMapping("/{id}")
	public ResponseEntity<Casa> buscar(@ApiParam(value="ID de uma casa de show", example="1") @PathVariable("id")Long id){
//		Casa casa = casasService.buscar(id); 
//		return ResponseEntity.status(HttpStatus.OK).body(casa);
		return ResponseEntity.status(HttpStatus.OK).body(casasService.buscar(id));
		
	}
	
	@ApiOperation("Busca uma casa de show de acordo com seu nome.")
	@GetMapping("/nome/{nomeCasa}")
	public ResponseEntity<Casa> buscarPorNome(@ApiParam(value="Nome de uma casa de show", example="Teatro") @PathVariable("nomeCasa")String nomeCasa){
//		Casa casa = casasService.buscar(id); 
//		return ResponseEntity.status(HttpStatus.OK).body(casa);
		return ResponseEntity.status(HttpStatus.OK).body(casasService.buscarPorNome(nomeCasa));
		
	}
	
	@ApiOperation("Deleta uma casa de show de acordo com seu id.")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@ApiParam(value="ID de uma casa de show", example="1") @PathVariable("id") Long id) {
		/*
		 * Livro livro = livros.findById(id).orElse(null); if(livro == null) { return
		 * ResponseEntity.notFound().build(); //retorna um 404, senão retorna um 200 OK
		 * e o corpo da mensagem } livros.deleteById(id); return
		 * ResponseEntity.noContent().build(); }
		 */
		
		casasService.deletar(id);
		return ResponseEntity.noContent().build();
	}

	@ApiOperation("Atualiza as informações sobre uma casa de show já cadastrada.")
	@PutMapping("/{id}")
	public ResponseEntity<Void> atualizar(@ApiParam(value="ID de uma casa de show", example="1") @RequestBody Casa casa, @PathVariable("id") Long id) {
		casa.setId(id);
		casasService.atualizar(casa);
		return ResponseEntity.noContent().build();
	}

	// http://tools.ietf.org/html/rfc7231 : documentação para consultar como tratar
	// respostas adequadamente
	

}
