package com.cleberson.bean.exemplos;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "nomes")
@ApplicationScoped
public class NomesBean {

	private String nome;
	private List<String> nomes = new ArrayList<>();

	public void adicionarNome() {
		this.nomes.add(this.nome);
		this.nome = "";
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<String> getNomes() {
		return nomes;
	}
}
