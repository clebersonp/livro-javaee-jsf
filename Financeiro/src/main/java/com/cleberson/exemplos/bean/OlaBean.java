package com.cleberson.exemplos.bean;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "ola")
public class OlaBean {

	private String nome;
	private String sobrenome;
	private String nomeCompleto;
	
	public void dizerOla() {
		this.nomeCompleto = this.nome.toUpperCase()
				+ " " + this.sobrenome.toUpperCase();
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}
}
