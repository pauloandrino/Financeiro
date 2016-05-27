package com.algaworks.manageBean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.model.Lancamento;
import com.algaworks.model.Pessoa;
import com.algaworks.model.TipoLancamento;
import com.algaworks.repository.Lancamentos;
import com.algaworks.repository.Pessoas;
import com.algaworks.service.CadastroLancamento;
import com.algaworks.service.NegocioException;

@Named
@SessionScoped
public class CadastroLancamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;

	@Inject
	private CadastroLancamento cadastro;

	@Inject
	private Pessoas pessoas;
	
	@Inject
	private Lancamentos lancamentos;

	public void prepararCadastro() {
		this.todasPessoas = pessoas.todas();
		
		if (this.lancamento == null) {
			this.lancamento = new Lancamento();
		}
	}

	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();

		try {
			cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage("Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}

	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println("Valor antigo: " + event.getOldValue());
		System.out.println("Novo valor: " + event.getNewValue());
		// FacesContext.getCurrentInstance().renderResponse();

	}

	public void dataVencimentoAlterada(AjaxBehaviorEvent event) {
		if (this.lancamento.getDataPagamento() == null) {
			this.lancamento.setDataPagamento(this.lancamento.getDataVencimento());
		}
	}
	
	public List<String> pesquisarDescricoes(String descricao) {
		return this.lancamentos.descricoesQueContem(descricao);
	}

	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}

	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
}
