package br.ufscar.dc.dsw.dao;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import br.ufscar.dc.dsw.domain.Aluno;

@SuppressWarnings("unchecked")
public interface IAlunoDAO extends CrudRepository<Aluno, Long>{
	Aluno findById(long id);
	List<Aluno> findAll();
	Aluno save(Aluno disciplina);
	void deleteById(Long id);
}