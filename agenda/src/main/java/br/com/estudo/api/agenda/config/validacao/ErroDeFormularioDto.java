package br.com.estudo.api.agenda.config.validacao;

public class ErroDeFormularioDto {
	
	private String campo;
	private String erro;

	ErroDeFormularioDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}

	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}
	
	
	

}
