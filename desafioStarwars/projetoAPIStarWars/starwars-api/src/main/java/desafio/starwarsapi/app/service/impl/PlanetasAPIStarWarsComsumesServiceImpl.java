package desafio.starwarsapi.app.service.impl;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.impl.client.HttpClients;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import desafio.starwarsapi.app.service.PlanetaAPIStarWarsConsumesService;
import desafio.starwarsapi.app.util.ResponseStarWarsAPI;

/**
 * Implementação do serviço de busca de planetas da API Starwars
 * @author ledzo
 *
 */
@Service
public class PlanetasAPIStarWarsComsumesServiceImpl implements PlanetaAPIStarWarsConsumesService {

	/**
	 * Busca Planetas da API do Starwars
	 * @param url
	 * @return
	 */
	@Override
	public ResponseStarWarsAPI findPlanetasAPIStarWars(String url) {

		HttpClient httpClient = HttpClients.custom().setSSLHostnameVerifier(new NoopHostnameVerifier())
				.build();
		HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);

		RestTemplate restTemplate = new RestTemplate(requestFactory);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		ResponseEntity<ResponseStarWarsAPI> responseJson =  restTemplate.getForEntity(url, ResponseStarWarsAPI.class);


		return responseJson.getBody();

	}

}
