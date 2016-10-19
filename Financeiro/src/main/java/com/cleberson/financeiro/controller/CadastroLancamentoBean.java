package com.cleberson.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.cleberson.financeiro.model.Lancamento;
import com.cleberson.financeiro.model.Pessoa;
import com.cleberson.financeiro.model.TipoLancamento;
import com.cleberson.financeiro.repository.Lancamentos;
import com.cleberson.financeiro.repository.Pessoas;
import com.cleberson.financeiro.service.CadastroLancamentos;
import com.cleberson.financeiro.service.NegocioException;
import com.cleberson.financeiro.util.JpaUtil;

/**
 * Classe bean na camada de controller para realizar operações com Lançamentos como, salvar, editar etc...
 * @author Cleberson
 * @see Lancamento
 * @see Pessoas
 * @see Pessoa
 * @see CadastroLancamentos
 * @see TipoLancamento
 */

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	private static final String SALVO_SUCESSO = "Lançamento salvo com sucesso!";
	
	/**
	 * Metodo responsável por preparar na tela a lista de Pessoa cadastradas no banco de dados.
	 * Quando o usuário for realizar um lançamento já estará disponível uma pessoa para seleção.
	 */
	public void prepararCadastro() {
		EntityManager manager = JpaUtil.getEntityManager();
		try {
			Pessoas pessoas = new Pessoas(manager);
			todasPessoas = pessoas.todas();
		} finally {
			manager.close();
		}
	}
	
	/**
	 * Metodo responsável em salvar o lançamento delegando para camada de negócio.
	 * 
	 */
	public void salvar() {
		EntityManager manager = JpaUtil.getEntityManager();
		EntityTransaction trx = manager.getTransaction();
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			trx.begin();
			
			CadastroLancamentos cadastro = new CadastroLancamentos(new Lancamentos(manager));
			cadastro.salvar(this.lancamento);
			
			context.addMessage(null, new FacesMessage(SALVO_SUCESSO));
			
			trx.commit();
			
		} catch (NegocioException e) {
			trx.rollback();
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		} finally {
			manager.close();
		}
	}

	public Lancamento getLancamento() {
		return lancamento;
	}
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Pessoa> getTodasPessoas() {
		return todasPessoas;
	}
	
	/**
	 * Metodo que recurepa todos os valores do enum para carregar na tela
	 * @return Os atributos do enum
	 */
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
}
