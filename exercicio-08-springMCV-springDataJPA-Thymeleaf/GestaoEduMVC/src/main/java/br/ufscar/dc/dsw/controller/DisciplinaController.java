package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import br.ufscar.dc.dsw.domain.Disciplina;
import br.ufscar.dc.dsw.service.spec.IDisciplinaService;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinaController {
	
	@Autowired
	private IDisciplinaService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Disciplina disciplina) {
		return "disciplina/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("disciplinas",service.buscarTodos());
		return "disciplina/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Disciplina disciplina, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "disciplina/cadastro";
		}
		
		service.salvar(disciplina);
		attr.addFlashAttribute("sucess", "Disciplina inserida com sucesso.");
		return "redirect:/disciplinas/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("disciplinas", service.buscarPorId(id));
		return "disciplina/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Disciplina disciplina, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "disciplina/cadastro";
		}

		service.salvar(disciplina);
		attr.addFlashAttribute("sucess", "Disciplina editada com sucesso.");
		return "redirect:/disciplinas/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.disciplinaTemAlunos(id)) {
			model.addAttribute("fail", "Disciplina não excluída. Possui alunos(s) vinculado(s).");
		} else {
			service.excluir(id);
			model.addAttribute("sucess", "Disciplina excluída com sucesso.");
		}
		return listar(model);
	}
}