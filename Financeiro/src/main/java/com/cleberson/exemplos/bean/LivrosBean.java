package com.cleberson.exemplos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.cleberson.exemplos.model.Livro;

@ManagedBean
@ViewScoped
public class LivrosBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Livro> livros;
	private Livro novoLivro;
	
	public LivrosBean() {
		this.livros = new ArrayList<>();
		this.novoLivro = new Livro();
	}
	
	public void adicionar() {
		this.livros.add(this.novoLivro);
		Collections.sort(this.livros);
		this.novoLivro = new Livro();
	}

	public List<Livro> getLivros() {
		return livros;
	}

	public Livro getNovoLivro() {
		return novoLivro;
	}
}
