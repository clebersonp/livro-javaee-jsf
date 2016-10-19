package com.cleberson.financeiro.service;

import java.io.Serializable;
import java.util.Date;

import com.cleberson.financeiro.model.Lancamento;
import com.cleberson.financeiro.repository.Lancamentos;

/**
 * Classe de negócio responsável por realizar operações de Lançamentos, como cadastro, excluir, etc.
 * @see Lancamentos
 * @see Lancamento
 * @see NegocioException
 * @author Cleberson
 *
 */
public class CadastroLancamentos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String ERRO_DATA_FUTURA = "Data de pagamento não pode ser uma data futura.";
	
	private Lancamentos lancamentos;
	
	public CadastroLancamentos(Lancamentos lancamentos) {
		this.lancamentos = lancamentos;
	}
	
	/**
	 * Salvar o lançamento se passar pela regra de negócio.
	 * @param lancamento
	 * @throws NegocioException Não deve permitir salvar o lançamento se a data de pagamento for uma data futura.
	 */
	public void salvar(Lancamento lancamento) throws NegocioException {
		if (lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException(ERRO_DATA_FUTURA);
		}
		
		this.lancamentos.adicionar(lancamento);
	}
	
	

}
