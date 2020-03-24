package br.com.gft.casadeshowapi.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.gft.casadeshowapi.domain.Casa;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CasaServiceTest {

	private String uri = "http://casadeshowapi.herokuapp.com/api/casas";

	@Autowired
	public WebApplicationContext context;
	
	
	private MockMvc mvc;

	@Autowired
	private CasaService service;

	@Before
	public void setup () {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void naoDeveCadastrarCasaRepetida() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.post("/api/casas")
				.content("{\"nomeCasa\":\"Maracanã\",\"localCasa\":\"Rio de Janeiro\"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isConflict());		
	}
	
	@Test
	public void testeRequisicaoPostSucesso() throws Exception {
		this.mvc.perform(MockMvcRequestBuilders.post("/api/casas")
				.content("{\"nomeCasa\":\"Teste\",\"localCasa\":\"Rio de Janeiro\"}")
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());		
	}
	
	
	
	@Test
	public void deveMostrarOTamanhoDaListaDeCasasDeShow() {
		List<Casa> casas = service.listar();
		assertThat(casas.size()).isGreaterThan(7);
	}

	@Test
	public void deveProcurarPorNomeDaCasaDeShow() {
		List<Casa> casa = service.buscarPorNome("Teatro");
	
		assertEquals(casa.get(0).getNomeCasa(), "Teatro Municipal");
	}

	@Test
	public void deveBuscarCasaDeShowPorId() {
		// RequestEntity<Void> request =
		// RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization",
		// "Basic TGVhbmRybzoxMjM=").build();
		Casa casa = service.buscar((long) 1);
		assertEquals(casa.getNomeCasa(), "Teatro Municipal");
	}

	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemCrescente() {
		// RequestEntity<Void> request =
		// RequestEntity.get(URI.create("http://localhost:8080/api/casas/")).header("Authorization",
		// "Basic TGVhbmRybzoxMjM=").build();
		List<Casa> casa = service.listarasc();
		assertEquals(casa.get(0).getNomeCasa(), "abc");
	}

	@Test
	public void deveValidarOPrimeiroDaListaEmOrdemDecrescente() {
		List<Casa> casa = service.listardesc();
		assertEquals(casa.get(0).getNomeCasa(), "Teatro Municipal");
	}
	
	@Test
	public void naoDeveCadastrarSemNomeCasa() {
		Casa casa = new Casa("", "Barueri");
		service.salvar(casa);
		given().when().then().statusCode(400);
	}
	
	@Test
	public void naoDeveCadastrarSemLocalCasa() {
		Casa casa = new Casa("Teste1", "");
		service.salvar(casa);
		given().when().then().statusCode(400);
	}
	

	
//	@Test
//	public void deveValidarOPrimeiroDaListaEmOrdemDecrescente() {
//		given()
//			.header("Authorization", "Basic TGVhbmRybzoxMjM=").
//		when()		
//			.get(uri+"/desc").
//		then()
//			.statusCode(200) // O status http retornado foi 200
//			.contentType(ContentType.JSON)
//			.body(a);
//		
//	}

	
	//TESTES EM PRODUÇÃO
	@Test //método RestAssured
	public void metodoGetRetorno200() {
		given()
			.header("Authorization", "Basic TGVhbmRybzoxMjM=").
		when()		
			.get(uri+"/5").
		then()
			.statusCode(200) // O status http retornado foi 200
			.contentType(ContentType.JSON) // O response foi retornado no formato JSON
			.body("nomeCasa", equalTo("Teatro Municipal"))
			.body("localCasa", equalTo("Barueri"));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test //método RestAssured
	public void nãoDeveDeixarCadastrarCasaJaExistente() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("nomeCasa", "Teste3");
		requestParams.put("localCasa", "Teste3");
		
		given()
			.header("Authorization", "Basic TGVhbmRybzoxMjM=")
			.body(requestParams.toJSONString())
			.contentType(ContentType.JSON).
		when()
			.post(uri).			
		then()
			.statusCode(409); // O status http retornado foi 409 CONCLIFT
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void naoDeveAtualizarRegistroInexistente() {
		JSONObject requestParams = new JSONObject();
		requestParams.put("nomeCasa", "Teste4");
		requestParams.put("localCasa", "Teste4");
		
		given()
			.header("Authorization", "Basic TGVhbmRybzoxMjM=")
			.body(requestParams.toJSONString())
			.contentType(ContentType.JSON).
		when()
			.put(uri+"/9").			
		then()
			.statusCode(404); // O status http retornado foi 404 NOT FOUND
	}
	
	@Test
	public void nãoDeveDeletarRecursoInexistente() {
		given()
			.header("Authorization", "Basic TGVhbmRybzoxMjM=").
		when()
			.delete(uri+"/10").			
		then()
			.statusCode(404); // O status http retornado foi 404 NOT FOUND
	}
	
	

}
