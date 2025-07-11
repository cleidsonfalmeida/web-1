package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Aluno;
import br.ufscar.dc.dsw.domain.Disciplina;
import br.ufscar.dc.dsw.dto.AlunoDTO;
import br.ufscar.dc.dsw.service.spec.IAlunoService;
import br.ufscar.dc.dsw.service.spec.IDisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/alunos")
public class AlunoRestController {

    @Autowired
    private IAlunoService alunoService;

    @Autowired
    private IDisciplinaService disciplinaService;

    @PostMapping
    public ResponseEntity<AlunoDTO> criar(@RequestBody @Valid AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setNome(dto.nome());
        aluno.setCpf(dto.cpf());
        aluno.setMatricula(dto.matricula());
        aluno.setEmail(dto.email());
        aluno.setDataNascimento(dto.dataNascimento());

        List<Disciplina> disciplinasAssociadas = new ArrayList<>();
        if (dto.disciplinas() != null) {
            for (String nomeDisciplina : dto.disciplinas()) {
                Disciplina disciplina = disciplinaService.buscarPorNome(nomeDisciplina);
                if (disciplina != null) {
                    disciplinasAssociadas.add(disciplina);
                }
            }
        }
        aluno.setDisciplinas(disciplinasAssociadas);

        Aluno salvo = alunoService.salvar(aluno);

    
        List<String> nomesDisciplinas = new ArrayList<>();
        if (salvo.getDisciplinas() != null) {
            for (Disciplina d : salvo.getDisciplinas()) {
                nomesDisciplinas.add(d.getNome());
            }
        }

        AlunoDTO resposta = new AlunoDTO(
            salvo.getId(),
            salvo.getNome(),
            salvo.getCpf(),
            salvo.getMatricula(),
            salvo.getEmail(),
            salvo.getDataNascimento(),
            nomesDisciplinas
        );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(salvo.getId()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

    @GetMapping
    public List<AlunoDTO> listar() {
        List<Aluno> alunos = alunoService.buscarTodos();
        List<AlunoDTO> dtos = new ArrayList<>();
        for (Aluno aluno : alunos) {
            List<String> nomesDisciplinas = new ArrayList<>();
            if (aluno.getDisciplinas() != null) {
                for (Disciplina d : aluno.getDisciplinas()) {
                    nomesDisciplinas.add(d.getNome());
                }
            }
            dtos.add(new AlunoDTO(
                aluno.getId(),
                aluno.getNome(),
                aluno.getCpf(),
                aluno.getMatricula(),
                aluno.getEmail(),
                aluno.getDataNascimento(),
                nomesDisciplinas
            ));
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> buscar(@PathVariable Long id) {
        Aluno aluno = alunoService.buscarPorId(id);
        if (aluno == null) {
            return ResponseEntity.notFound().build();
        }
        List<String> nomesDisciplinas = new ArrayList<>();
        if (aluno.getDisciplinas() != null) {
            for (Disciplina d : aluno.getDisciplinas()) {
                nomesDisciplinas.add(d.getNome());
            }
        }
        AlunoDTO dto = new AlunoDTO(
            aluno.getId(),
            aluno.getNome(),
            aluno.getCpf(),
            aluno.getMatricula(),
            aluno.getEmail(),
            aluno.getDataNascimento(),
            nomesDisciplinas
        );
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> atualizar(@PathVariable Long id, @RequestBody @Valid AlunoDTO dto) {
        Aluno existente = alunoService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setNome(dto.nome());
        existente.setCpf(dto.cpf());
        existente.setMatricula(dto.matricula());
        existente.setEmail(dto.email());
        existente.setDataNascimento(dto.dataNascimento());

        List<Disciplina> disciplinasAssociadas = new ArrayList<>();
        if (dto.disciplinas() != null) {
            for (String nomeDisciplina : dto.disciplinas()) {
                Disciplina disciplina = disciplinaService.buscarPorNome(nomeDisciplina);
                if (disciplina != null) {
                    disciplinasAssociadas.add(disciplina);
                }
            }
        }
        existente.setDisciplinas(disciplinasAssociadas);

        Aluno salvo = alunoService.salvar(existente);

        List<String> nomesDisciplinas = new ArrayList<>();
        if (salvo.getDisciplinas() != null) {
            for (Disciplina d : salvo.getDisciplinas()) {
                nomesDisciplinas.add(d.getNome());
            }
        }
        AlunoDTO resposta = new AlunoDTO(
            salvo.getId(),
            salvo.getNome(),
            salvo.getCpf(),
            salvo.getMatricula(),
            salvo.getEmail(),
            salvo.getDataNascimento(),
            nomesDisciplinas
        );
        return ResponseEntity.ok(resposta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Aluno existente = alunoService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        alunoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

}