package br.ufscar.dc.dsw.dto;

import java.util.Date;
import java.util.List;

public record AlunoDTO(
    Long id,
    String nome,
    String cpf,
    String matricula,
    String email,
    Date dataNascimento,
    List<String> disciplinas
) {}