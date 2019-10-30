package br.com.estudo.api.agenda.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.estudo.api.agenda.modelo.Agenda;
import br.com.estudo.api.agenda.modelo.Contato;
import br.com.estudo.api.agenda.repository.AgendaRepository;
import br.com.estudo.api.agenda.repository.ContatoRepository;
import io.swagger.annotations.ApiModelProperty;

public class ContatoForm {
	@ApiModelProperty(example = "mauricio", value = "")
	@NotNull @NotEmpty
	private String nome;
	
	@ApiModelProperty(example = "mauricio@email.com", value = "")
	@NotNull @NotEmpty @Email
	private String email;
	
	@ApiModelProperty(example = "987654893", value = "")
	private String telefone;
	
	@ApiModelProperty(example = "Agenda 1", value = "")
	@NotNull @NotEmpty
	private String nomeAgenda;
	
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getNomeAgenda() {
		return nomeAgenda;
	}
	public void setNomeAgenda(String nomeAgenda) {
		this.nomeAgenda = nomeAgenda;
	}
	
	public Contato converter(AgendaRepository agendaRepository) {
		Agenda agenda = agendaRepository.findByNome(nomeAgenda);
		if(agenda == null) {
			agenda = new Agenda(nomeAgenda);
		}
		Contato contato = new Contato(nome, email, telefone, agenda);
		
		return contato;
	}
	public Contato atualizar(Long id, ContatoRepository contatoRepository, AgendaRepository agendaRepository) {
		Contato contato = contatoRepository.getOne(id);
		contato.setEmail(this.email);
		contato.setNome(this.nome);
		contato.setTelefone(this.telefone);
		Agenda agenda = agendaRepository.findByNome(nomeAgenda);
		if(agenda != null) {
			contato.setAgenda(agenda);		
		}
		return contato;
	}
	

}
