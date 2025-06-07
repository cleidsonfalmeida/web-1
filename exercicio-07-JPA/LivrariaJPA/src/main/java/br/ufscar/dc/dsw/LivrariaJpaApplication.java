package br.ufscar.dc.dsw;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.ufscar.dc.dsw.dao.ILivroDAO;
import br.ufscar.dc.dsw.dao.IEditoraDAO;
import br.ufscar.dc.dsw.domain.Editora;
import br.ufscar.dc.dsw.domain.Livro;

@SpringBootApplication
public class LivrariaJpaApplication {

	private static final Logger log = LoggerFactory.getLogger(LivrariaJpaApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaJpaApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner demo(ILivroDAO livroDAO, IEditoraDAO editoraDAO) {
		return (args) -> {
	
			// Inserindo nova Editora
			Editora novaEditora = new Editora();
			novaEditora.setCNPJ("87.557.922/0001-82");
			novaEditora.setNome("Seguinte");
			editoraDAO.save(novaEditora);

			// Inserindo novos Livros
			Livro livro1 = new Livro();
			livro1.setTitulo("O Dia do Curinga");
			livro1.setAutor("Jostein Gaarder");
			livro1.setAno(1996);
			livro1.setPreco(new BigDecimal("29.90"));
			livro1.setEditora(novaEditora);
			livroDAO.save(livro1);

			Livro livro2 = new Livro();
			livro2.setTitulo("A Revolução dos Bichos");
			livro2.setAutor("George Orwell");
			livro2.setAno(2007);
			livro2.setPreco(new BigDecimal("23.90"));
			livro2.setEditora(novaEditora);
			livroDAO.save(livro2);

			// Todos Livros ordenados por preço
			log.info("Livros ordenados por Preço -- findAllByPrecosAsc():");
			log.info("---------------------------------------------------");
			for (Livro l : livroDAO.findAllByOrderByPrecoAsc()) {
				log.info(l.toString());
			}

			// // Recupere todos livros
			
			// log.info("Livros recuperados -- findAll():");
			// log.info("--------------------------------");
			// for (Livro livro : livroDAO.findAll()) {
			// 	log.info(livro.toString());
			// }
			// log.info("");
	
			// // Recupere um livro por seu ID
			
			// Livro livro = livroDAO.findById(1L);
			// log.info("Livro recuperado -- findById(1L):");
			// log.info("---------------------------------");
			// log.info(livro.toString());
			// log.info("");
		};
	}
}