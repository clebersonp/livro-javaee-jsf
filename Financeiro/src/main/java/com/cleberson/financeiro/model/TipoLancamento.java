package com.cleberson.financeiro.model;

public enum TipoLancamento {

	RECEITA("Receita"), 
	DESPESA("Despesa");
	
	private String tipo;
	
	private TipoLancamento(String tipo) {
		this.tipo = tipo;
	}
	
	public String getTipo() {
		return this.tipo;
	}
}
