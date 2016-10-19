package com.cleberson.financeiro.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cleberson.financeiro.model.Pessoa;

/**
 * Classe responsável por fazer acesso ao banco de dados para objetos do tipo Pessoa.
 * @author Cleberson
 * @see Pessoa
 */
public class Pessoas implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private EntityManager manager;
	
	public Pessoas(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * Permiter consultar todas as Pessoas cadastradas no banco de dados.
	 * @return Lista de Pessoa
	 */
	public List<Pessoa> todas() {
		TypedQuery<Pessoa> query = this.manager.createQuery("from Pessoa", Pessoa.class);
		return query.getResultList();
	}
	
	/**
	 * Consulta uma pessoa especifica pela sua PK
	 * @param id PK da tabela de Pessoa
	 * @return Pessoa consultada com a PK especifica
	 */
	public Pessoa porId(Long id) {
		return this.manager.find(Pessoa.class, id);
	}
	
}
