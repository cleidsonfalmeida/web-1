package br.ufscar.dc.dsw.service.spec;

import java.util.List;
import br.ufscar.dc.dsw.domain.Aluno;

public interface IAlunoService {
	Aluno buscarPorId(Long id);
	List<Aluno> buscarTodos();
	void salvar(Aluno livro);
	void excluir(Long id);
}