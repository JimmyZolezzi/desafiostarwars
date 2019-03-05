package desafio.starwarsapi.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import desafio.starwarsapi.app.model.Mensagem;
import desafio.starwarsapi.app.model.Planeta;
import desafio.starwarsapi.app.repository.PlanetaRepository;
import desafio.starwarsapi.app.service.PlanetaAPIStarWarsConsumesService;
import desafio.starwarsapi.app.service.PlanetasService;
import desafio.starwarsapi.app.util.ResponseDefault;
import desafio.starwarsapi.app.util.ResponseStarWarsAPI;

/**
 * Classe de implementção do seviço de planetas da API do Star Wars
 * @author ledzo
 *
 */
/**
 * @author ledzo
 *
 */
/**
 * @author ledzo
 *
 */
@Service
public class PlanetasServiceImpl implements PlanetasService{
	
	@Autowired
	private PlanetaAPIStarWarsConsumesService planetaAPIStarWarsConsumesService;
	@Autowired
	private PlanetaRepository planetaRepository;
	
	/**
	 * Busca planetas do banco de dados
	 * @param pagina
	 * @return ResponseDefault<List<Planeta>>
	 */
	@Override
	public ResponseDefault<List<Planeta>> findPlanetasAPIBanco(Pageable pagina) {
		ResponseDefault<List<Planeta>> response = null;
		try {
			
			Page<Planeta> planetasPage = planetaRepository.findAll(pagina);
			response = new ResponseDefault<List<Planeta>>(planetasPage.getContent(), planetasPage.getTotalElements(), Mensagem.SUCESSO.name());
			
		}catch(Exception e) {
			response = new ResponseDefault<List<Planeta>>(null, 0L, e.getMessage());
			
		}
		
		return response;
	}
	/**
	 * Busca planetas da api do starwars
	 * @param pagina
	 * @return ResponseDefault<List<Planeta>> 
	 */
	@Override
	public ResponseDefault<List<Planeta>> findPlanetasAPIStarWars(Integer pagina) {
		
		StringBuilder sBuilderUrl = new StringBuilder();
		sBuilderUrl.append("https://swapi.co/api/planets/?format=json");
		if(pagina != null) {
			sBuilderUrl.append("&page=").append(pagina);
		}
		String url = sBuilderUrl.toString();
		
		ResponseDefault<List<Planeta>> response = null;
		try {
			ResponseStarWarsAPI responseAPI  = planetaAPIStarWarsConsumesService.findPlanetasAPIStarWars(url);
			List<Planeta> planetas = responseAPI.getResults();
			response = new ResponseDefault<List<Planeta>>(planetas, responseAPI.getCount(), "OK");
		
		}catch(Exception e) {
			response = new ResponseDefault<List<Planeta>>(null, 0L, e.getMessage());
		}
	
	
		return response;
	}
	
	/**
	 * Adiciona um novo planeta ao banco de dados
	 * @param planeta
	 * @return ResponseDefault<Mensagem>
	 */
	@Override
	public ResponseDefault<Mensagem> addPlaneta(Planeta planeta) {

		ResponseDefault<Mensagem> response = null;
		try {

			planetaRepository.save(planeta);
			response = new ResponseDefault<Mensagem>(Mensagem.SUCESSO);
			
		}catch(Exception e) {
			
			response = new ResponseDefault<Mensagem>(Mensagem.ERRO,e.getMessage());
		}

		return response;
	}
	
	/**
	 * Busca planeta pelo nome
	 * @param nome
	 * @return ResponseDefault<List<Planeta>>
	 */
	@Override
	public ResponseDefault<List<Planeta>> buscaPlaneta(String nome) {

		ResponseDefault<List<Planeta>> responseDefault = null;
		try {
			List<Planeta> planetas = planetaRepository.findByNome(nome);
			Long total = planetas !=null ? planetas.size() : 0L;
			responseDefault = new ResponseDefault<List<Planeta>>(planetas, total, Mensagem.SUCESSO.name());
			

		}catch(Exception e) {
			responseDefault = new ResponseDefault<List<Planeta>>(null, 0L, e.getMessage());
		}
		
		return responseDefault;

	}

	/**
	 * Busca planeta pelo ID do planeta
	 * @param id
	 * @return ResponseDefault<Planeta>
	 */
	@Override
	public ResponseDefault<Planeta> buscarPlaneta(Long id) {

		ResponseDefault<Planeta> responseDefault = null;
		try {
			Optional<Planeta> optionalPlaneta = planetaRepository.findById(id);
			optionalPlaneta.get();
			
			responseDefault = new ResponseDefault<Planeta>(optionalPlaneta.get(), 1L, Mensagem.SUCESSO.name());
			

		}catch(Exception e) {
			responseDefault = new ResponseDefault<Planeta>(null, 0L, e.getMessage());
		}
		
		return responseDefault;
	}
	/**
	 * Remove um planeta por ID
	 * @param id
	 * @return ResponseDefault<Mensagem>
	 */
	@Override
	public ResponseDefault<Mensagem> removerPlaneta(Long id) {

		ResponseDefault<Mensagem> response = null;
		try {
			
			planetaRepository.deleteById(id);
			response = new ResponseDefault<Mensagem>(Mensagem.SUCESSO,Mensagem.SUCESSO.name());
			
		}catch(Exception e) {
			response = new ResponseDefault<Mensagem>(Mensagem.ERRO,e.getMessage());
		}
		
		return response;
	}

}
