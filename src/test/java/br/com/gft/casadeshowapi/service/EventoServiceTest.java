package br.com.gft.casadeshowapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gft.casadeshowapi.domain.Casa;
import br.com.gft.casadeshowapi.domain.Evento;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EventoServiceTest {
	
	@Autowired
	private EventoService service;
	
	@Test
	public void deveMostrarOTamanhoDaListaDeEventos() {
		List<Evento> eventos = service.listar();
		assertThat(eventos.size()).isEqualTo(3);
		
	}
	
	@Test
	public void deveBuscarCasaDeShowPorId() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		Evento eventos = service.buscar((long) 1);
		assertEquals(eventos.getNomeEvento(), "Show da Anitta");
		assertEquals(eventos.getCapacidade(), 400);
		assertEquals(eventos.getValor().longValue(), 100);
		
	}
	
	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemCrescenteNome() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Evento> eventos = service.listarAsc();
		assertEquals(eventos.get(0).getNomeEvento(), "Show da Anitta");
	}

	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemDecrescenteNome() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Evento> eventos = service.listarDesc();
		assertEquals(eventos.get(0).getNomeEvento(), "Show do Queen");
	}
	
	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemCrescenteCapacidade() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Evento> eventos = service.capacidadeAsc();
		assertEquals(eventos.get(0).getNomeEvento(), "Show da Anitta");
	}

	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemDecrescenteCapacidade() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Evento> eventos = service.capacidadeDesc();
		assertEquals(eventos.get(0).getNomeEvento(), "Show do Queen");
		
	}
	
	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemCrescenteData() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Evento> eventos = service.dataAsc();
		assertEquals(eventos.get(0).getNomeEvento(), "Show do Queen");
	}

	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemDecrescenteData() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Evento> eventos = service.dataDesc();
		assertEquals(eventos.get(0).getNomeEvento(), "Show da Anitta");
	}
	
	
}
