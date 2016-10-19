package com.cleberson.financeiro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import com.cleberson.financeiro.model.Pessoa;
import com.cleberson.financeiro.repository.Pessoas;
import com.cleberson.financeiro.util.JpaUtil;

@FacesConverter(forClass = Pessoa.class)
public class PessaoConverter implements Converter {

	/**
	 * Converte um id de Pessoa em um Objeto Pessoa
	 */
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EntityManager manager = JpaUtil.getEntityManager();
		Pessoa retorno = null;
		try {
			if (value != null && !"".equals(value)) {
				Pessoas pessoas = new Pessoas(manager);
				retorno = pessoas.porId(new Long(value));
			}
		} finally {
			manager.close();
		}
		return retorno;
	}

	/**
	 * Converte um objeto do tipo Pessoa em um id
	 */
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Pessoa pessoa = (Pessoa) value;
			return pessoa.getId().toString();
		}
		return null;
	}

}
