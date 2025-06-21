package br.ufscar.dc.dsw.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Disciplina;
import br.ufscar.dc.dsw.domain.Aluno;
import br.ufscar.dc.dsw.service.spec.IDisciplinaService;
import br.ufscar.dc.dsw.service.spec.IAlunoService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private IAlunoService alunoService;

	@Autowired
	private IDisciplinaService disciplinaService;

	@GetMapping("/cadastrar")
	public String cadastrar(Aluno aluno) {
		return "aluno/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("alunos", alunoService.buscarTodos());
		return "aluno/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "aluno/cadastro";
		}

		alunoService.salvar(aluno);
		attr.addFlashAttribute("sucess", "Aluno inserido com sucesso");
		return "redirect:/alunos/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("aluno", alunoService.buscarPorId(id));
		return "aluno/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Aluno aluno, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "aluno/cadastro";
		}

		alunoService.salvar(aluno);
		attr.addFlashAttribute("sucess", "Aluno editado com sucesso.");
		return "redirect:/alunos/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		alunoService.excluir(id);
		attr.addFlashAttribute("sucess", "Aluno excluído com sucesso.");
		return "redirect:/alunos/listar";
	}

	@ModelAttribute("disciplinas")
	public List<Disciplina> listaDisciplinas() {
		return disciplinaService.buscarTodos();
	}
}