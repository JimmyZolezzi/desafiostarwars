package desafio.starwarsapi.app.util;

import java.util.List;

import desafio.starwarsapi.app.model.Planeta;

/**
 * Resposta de Consumo da Api do Starwars
 * @author ledzo
 *
 */
public class ResponseStarWarsAPI {

	/** total de registros */
	private Long count;
	/** proxima url  */
	private String next;
	/** url anterior */
	private String previous;
	/** lista de planetas */
	private List<Planeta> results;

	/**
	 * Retorna a quantidade total de registros de planetas na api do starwars
	 * @return
	 */
	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}

	/**
	 * Obtem a proxima url de planeta(proxima pagina)
	 * @return String
	 */
	public String getNext() {
		return next;
	}

	public void setNext(String next) {
		this.next = next;
	}

	/**
	 * Obtem url anterior de planeta(pagina anterior)
	 * @return
	 */
	public String getPrevious() {
		return previous;
	}

	public void setPrevious(String previous) {
		this.previous = previous;
	}

	/**
	 * Lista de Planetas
	 * @return List<Planeta>
	 */
	public List<Planeta> getResults() {
		return results;
	}

	public void setResults(List<Planeta> results) {
		this.results = results;
	}

}
