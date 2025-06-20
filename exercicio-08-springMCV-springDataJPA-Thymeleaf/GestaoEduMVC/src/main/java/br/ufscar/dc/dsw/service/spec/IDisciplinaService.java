package br.ufscar.dc.dsw.service.spec;
import java.util.List;
import br.ufscar.dc.dsw.domain.Disciplina;
public interface IDisciplinaService {
	Disciplina buscarPorId(Long id);
	List<Disciplina> buscarTodos();
	void salvar(Disciplina disciplina);
	void excluir(Long id);
	boolean disciplinaTemAlunos(Long id);
}