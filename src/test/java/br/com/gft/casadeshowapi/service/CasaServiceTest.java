package br.com.gft.casadeshowapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URI;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.RequestEntity;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.gft.casadeshowapi.domain.Casa;
import br.com.gft.casadeshowapi.repository.CasaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasaServiceTest {
	
	@Autowired
	private CasaService service;
	
	@Test
	public void deveMostrarOTamanhoDaListaDeCasasDeShow() {
		List<Casa> casas = service.listar();
		assertThat(casas.size()).isEqualTo(7);
	}
	
	@Test
	public void deveProcurarPorNomeDaCasaDeShow() {
		List<Casa> casa = service.buscarPorNome("Teatro");
		assertEquals(casa.get(0).getNomeCasa(), "Teatro Municipal");
	}
	
	@Test
	public void deveBuscarCasaDeShowPorId() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		Casa casa = service.buscar((long) 1);
		assertEquals(casa.getNomeCasa(), "Teatro Municipal");
	}
	
	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemCrescente() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Casa> casa = service.listarasc();
		assertEquals(casa.get(0).getNomeCasa(), "abc");
	}

	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemDecrescente() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		List<Casa> casa = service.listardesc();
		assertEquals(casa.get(0).getNomeCasa(), "Teatro Municipal");
	}
		

}
