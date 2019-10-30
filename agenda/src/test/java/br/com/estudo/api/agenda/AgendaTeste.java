package br.com.estudo.api.agenda;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.Test;

import br.com.estudo.api.agenda.controller.AgendaController;
import br.com.estudo.api.agenda.controller.dto.ContatoDto;
import br.com.estudo.api.agenda.modelo.Agenda;
import br.com.estudo.api.agenda.modelo.Contato;
import br.com.estudo.api.agenda.repository.ContatoRepository;



public class AgendaTeste {
	
	@Test
	public void deveRetornarUmContato() {
				
		ContatoRepository contatoRepository = mock(ContatoRepository.class);
		AgendaController agendaController = new AgendaController(contatoRepository) ;
		
		Agenda agenda = new Agenda("Agenda Teste");
		Contato contato = new Contato("nome", "nome@email.com", "123456789", agenda);
		when(contatoRepository.findAll()).thenReturn(Arrays.asList(contato));
						
	    assertEquals(1, agendaController.listaContatos(null).size());
        
		
	}

}
