package desafio.starwarsapi.app.tests;

import java.time.Duration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import desafio.starwarsapi.app.model.Planeta;
import desafio.starwarsapi.app.repository.PlanetaRepository;
import reactor.core.publisher.Mono;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StarwarsApiApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@MockBean
	private PlanetaRepository repo;

	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() {
		webTestClient = webTestClient.mutate().responseTimeout(Duration.ofMillis(30000)).build();
	}

	
	/**
	 * Realiza teste de busca de planetas na api starwars
	 */
	@Test
	public void TesteBuscaPlanetasAPIStarWars() {

		webTestClient.get().uri("/starwars/apistarwars/planetas/1").accept(MediaType.APPLICATION_JSON).exchange()
				.expectStatus().isOk();

	}

	/**
	 * Realiza teste de inserção de planetas no banco de dados
	 */
	@Test
	public void TesteSalvarPlanetaBanco() {
		Planeta planeta = new Planeta();
		planeta.setNome("Terra");
		planeta.setClima("temperado");
		planeta.setAparicoesFilmes(3);
		planeta.setTerreno("Montanhoso");
		webTestClient.post().uri("/starwars/banco/planeta").accept(MediaType.APPLICATION_JSON).body(Mono.just(planeta),
				Planeta.class);
	}

	/**
	 * Realiza teste de busca planetas no banco de dados
	 */
	@Test
	public void TesteBuscaPlanetasBanco() {

		webTestClient.get().uri("/starwars/banco/planetas/1").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk();

	}
	
	/**
	 * realiza teste de remoção de planetas no banco de dados
	 */
	@Test
	public void removerPlanetasBanco() {

		webTestClient.delete().uri("/starwars/banco/planeta/1").accept(MediaType.APPLICATION_JSON).exchange().expectStatus()
				.isOk();

	}

}
