package br.com.apex.api.colaboradores.model;

public class RespostaModel {
	
	private String mensagem;
	
	public RespostaModel() {
		
		
	}

	public RespostaModel(String mensagem) {
		this.mensagem = mensagem;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	
}
