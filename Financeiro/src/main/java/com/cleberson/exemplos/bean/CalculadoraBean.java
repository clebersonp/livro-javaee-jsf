package com.cleberson.exemplos.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CalculadoraBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Double valorA;
	private Double valorB;
	private Double resultado;
	
	public void somar() {
		this.resultado = valorA + valorB;
	}

	public Double getValorA() {
		return valorA;
	}
	public void setValorA(Double valorA) {
		this.valorA = valorA;
	}

	public Double getValorB() {
		return valorB;
	}
	public void setValorB(Double valorB) {
		this.valorB = valorB;
	}

	public Double getResultado() {
		return resultado;
	}
}
