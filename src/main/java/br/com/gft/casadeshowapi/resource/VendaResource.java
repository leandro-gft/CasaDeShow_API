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

import br.com.gft.casadeshowapi.domain.Venda;
import br.com.gft.casadeshowapi.domain.Evento;
import br.com.gft.casadeshowapi.service.VendaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import br.com.gft.casadeshowapi.service.EventoService;



@RestController
@RequestMapping("/api/vendas")
public class VendaResource {
	
	@Autowired
	private VendaService vendasService;
	
	@Autowired
	private EventoService eventosService;

	@ApiOperation("Lista as vendas registradas de acordo com o usuário que realizou a compra.")
	@RequestMapping(
			method = RequestMethod.GET,
			produces = {
			MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE
			})
	public ResponseEntity<List<Venda>> listar() {
		List<Venda> vendas = vendasService.listar();
		return ResponseEntity.status(HttpStatus.OK).body(vendas);
		
	//	return ResponseEntity.status(HttpStatus.OK).body(autoresService.listar());
	}
	
	@ApiOperation("Registra uma nova venda e subtrai o número de ingresso vendidos da capacidade do evento.")
	@PostMapping
	public ResponseEntity<Void> salvar (@ApiParam(name="corpo", value="Representação de uma nova venda registrada.") @Valid @RequestBody Venda venda) {
		Evento evento = eventosService.buscar(venda.getEvento().getId());
		venda.setTotal(new BigDecimal(venda.getQtd()).multiply(evento.getValor()));
		evento.setCapacidade(evento.getCapacidade()-venda.getQtd());
		
		venda = vendasService.salvar(venda);
		
			
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.getId()).toUri();
		
		return ResponseEntity.created(uri).build();					
	
	}
	@ApiOperation("Busca uma venda de acordo com seu id.")
	@GetMapping("/{id}")
	public ResponseEntity<Venda> buscar(@ApiParam(value="ID de um evento", example="1") @PathVariable("id")Long id){
		return ResponseEntity.status(HttpStatus.OK).body(vendasService.buscar(id));
		
	}
	
}
