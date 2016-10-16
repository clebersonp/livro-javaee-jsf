package com.cleberson.exemplos.model;

import java.io.Serializable;

public class Livro implements Serializable, Comparable<Livro> {
	
	private static final long serialVersionUID = 1L;
	
	private String autor;
	private String titulo;
	
	public Livro() {
	}

	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	@Override
	public int compareTo(Livro o) {
		return this.titulo.compareToIgnoreCase(o.titulo);
	}
}
