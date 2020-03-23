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
	private CasaRepository casasRepository;
	
	@Test
	public void deveMostrarOTamanhoDaListaDeCasasDeShow() {
		List<Casa> casas = casasRepository.findAll();
		assertThat(casas.size()).isEqualTo(7);
	}
	
	@Test
	public void deveProcurarPorNomeDaCasaDeShow() {
		List<Casa> casa = casasRepository.findByNomeCasaContaining("Teatro");
		assertEquals(casa.get(0).getNomeCasa(), "Teatro Municipal");
	}
	
	@Test
	public void deveBuscarCasaDeShowPorId() {
		//RequestEntity<Void> request = RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization", "Basic TGVhbmRybzoxMjM=").build();
		Optional<Casa> casa = casasRepository.findById((long) 1);
		assertEquals(casa.get().getNomeCasa(), "Teatro Municipal");
	}
	
	


}
