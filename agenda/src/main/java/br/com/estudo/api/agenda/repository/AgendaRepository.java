package br.com.estudo.api.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.estudo.api.agenda.modelo.Agenda;

public interface AgendaRepository extends JpaRepository<Agenda, Long>{

	Agenda findByNome( String nome);

}
