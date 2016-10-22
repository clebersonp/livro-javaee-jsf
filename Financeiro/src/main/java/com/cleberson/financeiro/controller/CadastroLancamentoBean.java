package com.cleberson.financeiro.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.apache.log4j.Logger;

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
	private Logger log = Logger.getLogger(this.getClass().getName());
	
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
	
	/*
	 * Esse metodo é executado sempre que houver mudança na pagina e que a pagina
	 * sera renderizada pulando todas as outras fases do ciclo de vida do jsf por causa do renderResponse().
	 * Os métodos de mudança de valor são chamados no final da fase Processar validações, 
	 * antes de atribuir os valores convertidos e validados ao modelo, por isso que não consigo salvar(finalizar) o processo.
	 * só consigo salvar o lancamento se eu preencher os campos e submeter duas vezes, ele salvara na segunda vez
	 * pois não chama esse evento de mudança de valor
	 * 
	 * immediate, que quando atribuída para true, faz com que as validações, conversões
	 * e eventos desse componente sejam executados durante a fase Aplicar valores de
	 * requisição.
	 */
	public void descricaoModificada(ValueChangeEvent event) {
		log.info("Metodo descricaoModificada; Valor antigo: " + event.getOldValue());
		log.info("Metodo descricaoModificada; Valor nome: " + event.getNewValue());
		FacesContext.getCurrentInstance().renderResponse();
	}
	
	/*
	 * Listener de ação são executados antes da lógica real do botão
	 * Se eu registrar esse listener para o botão de cadastro, esse método
	 * será executado antes do método salvar()
	 */
	public void registrarLogCadastro(ActionEvent event) {
		log.info("Registro de log do cadastro de usuário. Componente: "
							+ event.getComponent().getId());
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
			
			limparCampos();
			
			trx.commit();
			
		} catch (NegocioException e) {
			trx.rollback();
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), e.getMessage()));
		} finally {
			manager.close();
		}
	}

	private void limparCampos() {
		this.lancamento = new Lancamento();
		this.prepararCadastro();
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
