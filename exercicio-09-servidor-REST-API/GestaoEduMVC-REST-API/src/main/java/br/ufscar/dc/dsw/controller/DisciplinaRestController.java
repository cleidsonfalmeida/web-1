package br.ufscar.dc.dsw.controller;

import br.ufscar.dc.dsw.domain.Disciplina;
import br.ufscar.dc.dsw.dto.DisciplinaDTO;
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
@RequestMapping("/api/disciplinas")
public class DisciplinaRestController {

    @Autowired
    private IDisciplinaService disciplinaService;


    @PostMapping
    public ResponseEntity<DisciplinaDTO> criar(@RequestBody @Valid DisciplinaDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setCodigo(dto.codigo());
        disciplina.setProfessorResponsavel(dto.professorResponsavel());

        Disciplina salva = disciplinaService.salvar(disciplina);

        DisciplinaDTO resposta = new DisciplinaDTO(
            salva.getId(),
            salva.getNome(),
            salva.getCodigo(),
            salva.getProfessorResponsavel()
        );

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(salva.getId()).toUri();
        return ResponseEntity.created(uri).body(resposta);
    }

  
    @GetMapping
    public List<DisciplinaDTO> listar() {
        List<Disciplina> disciplinas = disciplinaService.buscarTodos();
        List<DisciplinaDTO> dtos = new ArrayList<>();
        for (Disciplina d : disciplinas) {
            dtos.add(new DisciplinaDTO(
                d.getId(),
                d.getNome(),
                d.getCodigo(),
                d.getProfessorResponsavel()
            ));
        }
        return dtos;
    }

 
    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> buscar(@PathVariable Long id) {
        Disciplina disciplina = disciplinaService.buscarPorId(id);
        if (disciplina == null) {
            return ResponseEntity.notFound().build();
        }
        DisciplinaDTO dto = new DisciplinaDTO(
            disciplina.getId(),
            disciplina.getNome(),
            disciplina.getCodigo(),
            disciplina.getProfessorResponsavel()
        );
        return ResponseEntity.ok(dto);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaDTO> atualizar(@PathVariable Long id, @RequestBody @Valid DisciplinaDTO dto) {
        Disciplina existente = disciplinaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        existente.setNome(dto.nome());
        existente.setCodigo(dto.codigo());
        existente.setProfessorResponsavel(dto.professorResponsavel());

        Disciplina salva = disciplinaService.salvar(existente);

        DisciplinaDTO resposta = new DisciplinaDTO(
            salva.getId(),
            salva.getNome(),
            salva.getCodigo(),
            salva.getProfessorResponsavel()
        );
        return ResponseEntity.ok(resposta);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        Disciplina existente = disciplinaService.buscarPorId(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }
        disciplinaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}