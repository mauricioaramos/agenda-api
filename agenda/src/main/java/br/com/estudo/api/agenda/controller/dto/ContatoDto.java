package br.com.estudo.api.agenda.controller.dto;

import br.com.estudo.api.agenda.modelo.Contato;
import io.swagger.annotations.ApiModelProperty;

public class ContatoDto {
	
	@ApiModelProperty(example = "123", value = "")
	private Long id;
	@ApiModelProperty(example = "mauricio", value = "")
	private String nome;
	@ApiModelProperty(example = "mauricio@email.com", value = "")
	private String email;
	@ApiModelProperty(example = "99876543", value = "")
	private String telefone;
	@ApiModelProperty(example = "Agenda 1", value = "")
	private String nomeAgenda;
	
	
	public ContatoDto(Contato contato) {
		this.id = contato.getId();
		this.nome = contato.getNome();
		this.email = contato.getEmail();
		this.telefone = contato.getTelefone();
		this.nomeAgenda = contato.getAgenda().getNome();
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


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
	
	

}
