package desafio.starwarsapi.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import desafio.starwarsapi.app.model.Mensagem;
import desafio.starwarsapi.app.model.Planeta;
import desafio.starwarsapi.app.service.PlanetasService;
import desafio.starwarsapi.app.util.ResponseDefault;

/**
 * Controle para os endpoints de serviços da api do starwars
 * 
 * @author ledzo
 *
 */
@RestController
@RequestMapping("/starwars")
public class PlanetasController {
	
	@Autowired
	private PlanetasService planetasService;

	/**
	 * Busca planetas da api do Starwars
	 * @param pagina
	 * @return ResponseEntity<ResponseDefault<List<Planeta>>>
	 */
	@GetMapping()
	@RequestMapping("/apistarwars/planetas/{pagina}")
	public ResponseEntity<ResponseDefault<List<Planeta>>> getPlanetasAPIStarWars(@PathVariable Integer pagina) {
		
		ResponseDefault<List<Planeta>> response = planetasService.findPlanetasAPIStarWars(pagina);
		
		return new ResponseEntity<ResponseDefault<List<Planeta>>>(response, HttpStatus.OK);
		
	}
	
	
	/**
	 * Salva um novo planeta no banco de dados
	 * 
	 * @param planeta
	 * @return ResponseEntity<Mensagem>
	 */
	@PostMapping
	@RequestMapping("/banco/planeta")
	public ResponseEntity<Mensagem> postPlanetaBanco(@RequestBody Planeta planeta) {
		
		ResponseDefault<Mensagem> response = planetasService.addPlaneta(planeta);
		
		if(response.getResponse().equals(Mensagem.SUCESSO)) {
			return new ResponseEntity<Mensagem>((Mensagem) response.getResponse(), HttpStatus.OK);
		}
		
		return new ResponseEntity<Mensagem>((Mensagem) response.getResponse(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Busca planetas do banco de dados
	 * @param page
	 * @param size
	 * @return ResponseEntity<ResponseDefault<List<Planeta>>>
	 */
	@GetMapping
	@RequestMapping("/banco/planetas")
	public ResponseEntity<ResponseDefault<List<Planeta>>> getPlanetasBanco(@RequestParam Integer page, @RequestParam Integer size) {
	
		ResponseDefault<List<Planeta>> response = planetasService.findPlanetasAPIBanco(PageRequest.of(page - 1, size));
		if(response.getResponse() != null) {
					
			return new ResponseEntity<ResponseDefault<List<Planeta>>>(response, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	
	}
	
	/**
	 * Busca planeta do banco de dados por nome
	 * @param nome
	 * @return ResponseEntity<List<Planeta>>
	 */
	@GetMapping
	@RequestMapping("/banco/planetas/{nome}")
	public ResponseEntity<List<Planeta>> getPlanetasBanco(@PathVariable String nome) {
	
		ResponseDefault<List<Planeta>> response = planetasService.buscaPlaneta(nome);
		if(response.getMsg().equals(Mensagem.SUCESSO.name())) {
			
			return new ResponseEntity<List<Planeta>>((List<Planeta>) response.getResponse(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
	
	}
	
	/**
	 * Busca planeta do banco de dados por nome
	 * @param nome
	 * @return ResponseEntity<List<Planeta>>
	 */
	@GetMapping
	@RequestMapping("/banco/planeta/id/{id}")
	public ResponseEntity<Planeta> getPlanetaBancoPorId(@PathVariable Long id) {
	
		ResponseDefault<Planeta> response = planetasService.buscarPlaneta(id);
		if(response.getMsg().equals(Mensagem.SUCESSO.name())) {
			
			return new ResponseEntity<Planeta>((Planeta) response.getResponse(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
	
	}
	
	/**
	 * Remove planeta do banco de dados por ID
	 * @param id
	 * @return
	 */
	@DeleteMapping
	@RequestMapping("/banco/planeta/{id}")
	public ResponseEntity<Mensagem> getPlanetasBanco(@PathVariable Long id) {
	
		ResponseDefault<Mensagem> response = planetasService.removerPlaneta(id);
				
		if(response.getResponse().equals(Mensagem.SUCESSO)) {
			
			return new ResponseEntity<Mensagem>((Mensagem) response.getResponse(), HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		
		
	
	}
	
	
}
