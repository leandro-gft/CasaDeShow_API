package br.com.gft.casadeshowapi.resource;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.gft.casadeshowapi.domain.Compra;
import br.com.gft.casadeshowapi.domain.Evento;
import br.com.gft.casadeshowapi.service.CompraService;
import br.com.gft.casadeshowapi.service.EventoService;



@RestController
@RequestMapping("/historico")
public class CompraResource {
	
	@Autowired
	private CompraService comprasService;
	
	@Autowired
	private EventoService eventosService;
	
	@RequestMapping(
			method = RequestMethod.GET,
			produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<List<Compra>> listar() {
		List<Compra> compras = comprasService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(compras);
		
	//	return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
	}
	
	@PostMapping
	public ResponseEntity<Void> salvar (@Valid @RequestBody Compra compra) {
		Evento evento = eventosService.buscar(compra.getEvento().getId());
		compra.setTotal(new BigDecimal(compra.getQtd()).multiply(evento.getValor()));
		evento.setCapacidade(evento.getCapacidade()-compra.getQtd());
		
		compra = comprasService.salvar(compra);
		
			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(compra.getId()).toUri();
		
		return ResponseEntity.created(uri).build();					
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Compra> buscar(@PathVariable("id")Long id){
//		Casa casa = casasService.buscar(id); 
//		return ResponseEntity.status(HttpStatus.OK).body(casa);
		return ResponseEntity.status(HttpStatus.OK).body(comprasService.buscar(id));
		
	}
	
}
