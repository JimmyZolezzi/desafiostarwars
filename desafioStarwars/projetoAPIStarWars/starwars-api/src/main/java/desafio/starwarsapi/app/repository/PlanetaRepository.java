package desafio.starwarsapi.app.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import desafio.starwarsapi.app.model.Planeta;

/**
 * Interface de acesso ao banco de dados para Planetas da API Starwars
 * @author ledzo
 *
 */
@Repository
public interface PlanetaRepository extends JpaRepository<Planeta, Long>{

	/**
	 * Busca os Planetas */
	public Page<Planeta> findAll(Pageable pageable);
	/** Busca planeta por ID */
	public Optional<Planeta> findById(Long id);
	/** Busca planeta por nome */
	@Query("select p from Planeta p where p.nome = ?1")
	public List<Planeta> findByNome(String nome);

}
