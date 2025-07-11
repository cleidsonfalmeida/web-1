package br.ufscar.dc.dsw.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import br.ufscar.dc.dsw.dao.IDisciplinaDAO;
import br.ufscar.dc.dsw.domain.Disciplina;
import br.ufscar.dc.dsw.service.spec.IDisciplinaService;

@Service
@Transactional(readOnly = false)
public class DisciplinaService implements IDisciplinaService {

	@Autowired
	IDisciplinaDAO dao;
	
	public void salvar(Disciplina disciplina) {
		dao.save(disciplina);
	}
	
	public void excluir(Long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Disciplina buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Disciplina> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public boolean disciplinaTemAlunos(Long id) {
		return !dao.findById(id.longValue()).getAlunos().isEmpty(); 
	}

	@Override
    public Disciplina buscarPorNome(String nome) {
        return dao.findByNome(nome);
    }
}