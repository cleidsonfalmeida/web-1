package br.ufscar.dc.dsw.dto;

public record DisciplinaDTO(
    Long id,
    String nome,
    String codigo,
    String professorResponsavel
) {}