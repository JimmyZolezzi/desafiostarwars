package desafio.starwarsapi.app.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonAlias;

/**
 * Modelo do objeto Planeta
 * @author ledzo
 *
 */
@Entity
@Cacheable
public class Planeta implements Serializable {

	private static final long serialVersionUID = 6421854538388315186L;
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

	@JsonAlias({"name","nome"})
	private String nome;
	@JsonAlias({"climate","clima"})
	private String clima;
	@JsonAlias({"terrain","terreno"})
	private String terreno;
	private int aparicoesFilmes;
	@Transient
	private List<String> films;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getClima() {
		return clima;
	}

	public void setClima(String clima) {
		this.clima = clima;
	}

	public String getTerreno() {
		return terreno;
	}

	public void setTerreno(String terreno) {
		this.terreno = terreno;
	}

	public int getAparicoesFilmes() {
		return aparicoesFilmes;
	}

	public void setAparicoesFilmes(int aparicoesFilmes) {
		this.aparicoesFilmes = aparicoesFilmes;
	}

	public List<String> getFilms() {
		return films;
	}

	public void setFilms(List<String> films) {
		this.films = films;
		this.aparicoesFilmes = films != null ? films.size() : 0;
	}
}
