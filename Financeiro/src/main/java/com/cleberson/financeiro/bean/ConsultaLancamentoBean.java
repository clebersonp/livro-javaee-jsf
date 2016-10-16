package com.cleberson.financeiro.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.cleberson.financeiro.model.Lancamento;
import com.cleberson.financeiro.util.JpaUtil;

@ManagedBean
@ViewScoped
public class ConsultaLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<Lancamento> lancamentos;
	
	public void consultar() {
		EntityManager manager = JpaUtil.getEntityManager();
		
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento", Lancamento.class);
		this.lancamentos = query.getResultList();
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
}
