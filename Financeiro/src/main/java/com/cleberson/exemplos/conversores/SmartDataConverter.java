package com.cleberson.exemplos.conversores;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("smartData")
public class SmartDataConverter implements Converter {

	private static final DateFormat FORMATADOR = new SimpleDateFormat("dd/MM/yyyy");
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Date dataConvertida = null;
		if (value == null || value.isEmpty()) {
			throw new ConverterException(
					new FacesMessage(
							FacesMessage.SEVERITY_ERROR, "Data obrigatória", "A data não foi informada!"));
		}
		
		if ("hoje".equalsIgnoreCase(value)) {
			dataConvertida = getDataAtual().getTime();
		} else if ("amanha".equalsIgnoreCase(value) 
					|| "amanhã".equalsIgnoreCase(value)) {
			Calendar amanha = getDataAtual();
			amanha.add(Calendar.DAY_OF_MONTH, 1);
			dataConvertida = amanha.getTime();
		} else if ("ontem".equalsIgnoreCase(value)) {
			Calendar ontem = getDataAtual();
			ontem.add(Calendar.DAY_OF_MONTH, -1);
			dataConvertida = ontem.getTime();
		} else {
			try {
				dataConvertida = FORMATADOR.parse(value);
			} catch (ParseException e) {
				throw new ConverterException(
						new FacesMessage(
								FacesMessage.SEVERITY_ERROR, "Data incorreta.", "Informe uma data correta."));
			}
		}
		
		return dataConvertida;
	}

	private Calendar getDataAtual() {
		Calendar dataAtual = new GregorianCalendar();
		dataAtual.set(Calendar.HOUR, 0);
		dataAtual.set(Calendar.MINUTE, 0);
		dataAtual.set(Calendar.SECOND, 0);
		dataAtual.set(Calendar.MILLISECOND, 0);
		return dataAtual;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value  != null) {
			return FORMATADOR.format((Date) value);
		}
		return null;
	}

}
