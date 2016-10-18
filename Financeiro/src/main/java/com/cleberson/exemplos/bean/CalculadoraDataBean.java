package com.cleberson.exemplos.bean;

import java.util.Calendar;
import java.util.Date;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CalculadoraDataBean {

	private String nome;
	private Date dataBase;
	private Integer dias;
	private Date resultado;
	
	public void adicionar() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(this.dataBase);
		calendar.add(Calendar.DAY_OF_MONTH, this.dias);
		
		this.resultado = calendar.getTime();
	}

	public String getNome() {
		return this.nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Integer getDias() {
		return dias;
	}
	public void setDias(Integer dias) {
		this.dias = dias;
	}
	
	public Date getDataBase() {
		return dataBase;
	}
	public void setDataBase(Date dataBase) {
		this.dataBase = dataBase;
	}

	public Date getResultado() {
		return resultado;
	}
}
