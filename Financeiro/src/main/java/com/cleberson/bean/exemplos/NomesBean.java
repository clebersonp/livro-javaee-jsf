package com.cleberson.bean.exemplos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlInputText;

@ManagedBean(name = "nomesBean")
@ViewScoped
public class NomesBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nome;
	private List<String> nomes = new ArrayList<>();
	
	
	/*
	 * Usando o conceito de backing bean, acessando os componentes da página xhtml.
	 * Para fazer a amarração dos componentes com o backing bean, é utilizado o atributo
	 * binding nas tags da página xhtml, precisa adicionar os getters and setters tbm
	 */
	private HtmlInputText inputNome;
	private HtmlCommandButton botaoAdicionar;

	public void adicionarNome() {
		this.nomes.add(this.nome);
		this.nome = "";
		
		// verifica se a lista possui mas que três, e desativa o campo de texto e o botão de adição
		if (this.nomes.size() > 3) {
			this.inputNome.setDisabled(true);
			this.inputNome.setValue("Muitos nomes na lista...");
			this.botaoAdicionar.setDisabled(true);
			this.botaoAdicionar.setValue("Operação não suportada!");
		}
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

	public HtmlInputText getInputNome() {
		return inputNome;
	}
	public void setInputNome(HtmlInputText inputNome) {
		this.inputNome = inputNome;
	}

	public HtmlCommandButton getBotaoAdicionar() {
		return botaoAdicionar;
	}
	public void setBotaoAdicionar(HtmlCommandButton botaoAdicionar) {
		this.botaoAdicionar = botaoAdicionar;
	}
}
