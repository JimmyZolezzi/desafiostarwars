package desafio.starwarsapi.app.service;

import desafio.starwarsapi.app.util.ResponseStarWarsAPI;

/**
 * Interface de consumo para busca de planetas da API do Starwars
 * @author ledzo
 *
 */
public interface PlanetaAPIStarWarsConsumesService {

	/**
	 * Busca Planetas da API do Starwars
	 * @param url
	 * @return
	 */
	public ResponseStarWarsAPI findPlanetasAPIStarWars(String url);
}
