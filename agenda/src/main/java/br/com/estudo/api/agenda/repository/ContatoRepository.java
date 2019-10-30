package br.com.estudo.api.agenda.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudo.api.agenda.modelo.Contato;

public interface ContatoRepository extends JpaRepository<Contato, Long>{
	
	List<Contato> findByNome(String nome);

}
