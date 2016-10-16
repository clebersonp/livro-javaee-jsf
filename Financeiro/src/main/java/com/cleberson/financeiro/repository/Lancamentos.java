package com.cleberson.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cleberson.financeiro.model.Lancamento;

/**
 * 
 * @author Cleberson
 * Repositorio para realizar operações de banco de dados para os lançamentos
 */
public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;
	
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}
	
	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = this.manager.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}
	
}
