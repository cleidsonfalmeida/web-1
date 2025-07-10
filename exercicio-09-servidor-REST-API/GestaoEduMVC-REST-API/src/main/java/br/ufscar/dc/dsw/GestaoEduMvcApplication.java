package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.ufscar.dc.dsw.dao.IDisciplinaDAO;
import br.ufscar.dc.dsw.dao.IAlunoDAO;
import br.ufscar.dc.dsw.domain.Disciplina;
import br.ufscar.dc.dsw.domain.Aluno;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@SpringBootApplication
public class GestaoEduMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestaoEduMvcApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(IDisciplinaDAO disciplinaDAO, IAlunoDAO alunoDAO) {
		return (args) -> {	
			Disciplina d1 = new Disciplina();
			d1.setNome("Matemática Básica");
			d1.setCodigo("D001");
			d1.setProfessorResponsavel("Prof. Ana Paula");
			disciplinaDAO.save(d1);
			
			Disciplina d2 = new Disciplina();
			d2.setNome("Física I");
			d2.setCodigo("D002");
			d2.setProfessorResponsavel("Prof. Carlos Alberto");
			disciplinaDAO.save(d2);
			
			Disciplina d3 = new Disciplina();
			d3.setNome("Química Geral");
			d3.setCodigo("D003");
			d3.setProfessorResponsavel("Prof. Fernanda Lima");
			disciplinaDAO.save(d3);
			
			Disciplina d4 = new Disciplina();
			d4.setNome("Programação I");
			d4.setCodigo("D016");
			d4.setProfessorResponsavel("Prof. Daniel Souza");
			disciplinaDAO.save(d4);
			
			Aluno a1 = new Aluno();
			a1.setNome("Alice Silva");
			a1.setCpf("123.456.789-00");
			a1.setMatricula("MAT001");
			a1.setEmail("alice@example.com");
			a1.setDataNascimento(Date.from(LocalDate.of(2005, 1, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			a1.getDisciplinas().add(d1);
			a1.getDisciplinas().add(d2);
			alunoDAO.save(a1);

			Aluno a2 = new Aluno();
			a2.setNome("Bruno Costa");
			a2.setCpf("234.567.890-11");
			a2.setMatricula("MAT002");
			a2.setEmail("bruno@example.com");
			a2.setDataNascimento(Date.from(LocalDate.of(2004, 3, 22).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			a2.getDisciplinas().add(d2);
			a2.getDisciplinas().add(d3);
			a2.getDisciplinas().add(d4);
			alunoDAO.save(a2);

			Aluno a3 = new Aluno();
			a3.setNome("Carla Mendes");
			a3.setCpf("345.678.901-22");
			a3.setMatricula("MAT003");
			a3.setEmail("carla@example.com");
			a3.setDataNascimento(Date.from(LocalDate.of(2005, 7, 11).atStartOfDay(ZoneId.systemDefault()).toInstant()));
			a3.getDisciplinas().add(d1);
			a3.getDisciplinas().add(d3);
			a3.getDisciplinas().add(d4);
			alunoDAO.save(a3);
		};
	}
}