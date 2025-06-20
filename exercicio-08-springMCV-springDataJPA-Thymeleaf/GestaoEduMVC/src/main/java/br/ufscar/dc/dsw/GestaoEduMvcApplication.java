package br.ufscar.dc.dsw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import br.ufscar.dc.dsw.dao.IDisciplinaDAO;
import br.ufscar.dc.dsw.domain.Disciplina;

@SpringBootApplication
public class GestaoEduMvcApplication {
	public static void main(String[] args) {
		SpringApplication.run(GestaoEduMvcApplication.class, args);
	}
	@Bean
	public CommandLineRunner demo(IDisciplinaDAO disciplinaDAO) {
		return (args) -> {	
			Disciplina d1 = new Disciplina();
			d1.setNome("Cálculo II");
			d1.setCodigo("D021");
			d1.setProfessorResponsavel("Prof. Roberto Augusto");
			disciplinaDAO.save(d1);			
		};
	}
}