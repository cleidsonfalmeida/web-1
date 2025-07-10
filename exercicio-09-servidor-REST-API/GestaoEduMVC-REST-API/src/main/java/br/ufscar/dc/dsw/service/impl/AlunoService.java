package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IAlunoDAO;
import br.ufscar.dc.dsw.domain.Aluno;
import br.ufscar.dc.dsw.service.spec.IAlunoService;

@Service
@Transactional(readOnly = false)
public class AlunoService implements IAlunoService {

	@Autowired
	IAlunoDAO dao;
	
	public void salvar(Aluno aluno) {
		dao.save(aluno);
	}
	
	public void excluir(Long id) {
		dao.deleteById(id);
	}
	
	@Transactional(readOnly = true)
	public Aluno buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}
	
	@Transactional(readOnly = true)
	public List<Aluno> buscarTodos() {
		return dao.findAll();
	}
}