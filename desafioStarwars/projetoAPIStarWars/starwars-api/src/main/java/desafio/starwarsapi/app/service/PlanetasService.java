package desafio.starwarsapi.app.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import desafio.starwarsapi.app.model.Mensagem;
import desafio.starwarsapi.app.model.Planeta;
import desafio.starwarsapi.app.util.ResponseDefault;

/**
 * Interface de serviços de busca de planetas da API Starwars
 * @author ledzo
 *
 */
public interface PlanetasService {

	/**
	 * Busca planetas do banco de dados
	 * @param pagina
	 * @return ResponseDefault<List<Planeta>>
	 */
	public ResponseDefault<List<Planeta>> findPlanetasAPIBanco(Pageable pagina);
	/**
	 * Busca planetas da api do starwars
	 * @param pagina
	 * @return ResponseDefault<List<Planeta>> 
	 */
	public ResponseDefault<List<Planeta>> findPlanetasAPIStarWars(Integer pagina);
	/**
	 * Adiciona um novo planeta ao banco de dados
	 * @param planeta
	 * @return ResponseDefault<Mensagem>
	 */
	public ResponseDefault<Mensagem> addPlaneta(Planeta planeta);
	/**
	 * Busca planeta pelo nome
	 * @param nome
	 * @return ResponseDefault<List<Planeta>>
	 */
	public ResponseDefault<List<Planeta>> buscaPlaneta(String nome);
	/**
	 * Busca planeta pelo ID do planeta
	 * @param id
	 * @return ResponseDefault<Planeta>
	 */
	public ResponseDefault<Planeta> buscarPlaneta(Long id);
	/**
	 * Remove um planeta por ID
	 * @param id
	 * @return ResponseDefault<Mensagem>
	 */
	public ResponseDefault<Mensagem> removerPlaneta(Long id);
}
