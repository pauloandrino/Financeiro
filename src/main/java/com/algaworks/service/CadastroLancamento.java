package com.algaworks.service;

import java.io.Serializable;
import java.util.Date;

import javax.inject.Inject;

import com.algaworks.model.Lancamento;
import com.algaworks.repository.Lancamentos;
import com.algaworks.util.Transacional;

public class CadastroLancamento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Lancamentos lancamentos;
	
	@Transacional
	public void salvar(Lancamento lancamento) throws NegocioException {
		if(lancamento.getDataPagamento() != null && lancamento.getDataPagamento().after(new Date())) {
			throw new NegocioException("A data de pagamento não pode ser uma data futura");
		}
		this.lancamentos.salvar(lancamento);
	}
	
	@Transacional
	public void excluir(Lancamento lancamento) throws NegocioException {
		lancamento = this.lancamentos.porId(lancamento.getId());
		
		if (lancamento.getDataPagamento() != null) {
			throw new NegocioException("Não é possível excluir um pagamento pago!");
		}
		
		this.lancamentos.remover(lancamento);
	}

}
