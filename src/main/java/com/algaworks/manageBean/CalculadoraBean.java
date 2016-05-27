package com.algaworks.manageBean;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class CalculadoraBean {

	private String nome;
	private Double valorA;
	private Double valorB;
	private Double resultado;

	public void somar() {
		this.resultado = this.valorA + this.valorB;
	}
	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
