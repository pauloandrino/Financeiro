package com.algaworks.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.algaworks.model.Lancamento;

public class Lancamentos implements Serializable {

	private static final long serialVersionUID = 1L;

	private EntityManager manager;

	@Inject
	public Lancamentos(EntityManager manager) {
		this.manager = manager;
	}

	public Lancamento salvar(Lancamento lancamento) {
		return this.manager.merge(lancamento);
	}

	public Lancamento porId(Long id) {
		return manager.find(Lancamento.class, id);
	}

	public void remover(Lancamento lancamento) {
		this.manager.remove(lancamento);
	}

	public List<Lancamento> todos() {
		TypedQuery<Lancamento> query = manager.createQuery("from Lancamento", Lancamento.class);
		return query.getResultList();
	}

	public List<String> descricoesQueContem(String descricao) {
		TypedQuery<String> query = manager.createQuery(
				"select distinct descricao from Lancamento " + "where upper(descricao) like upper(:descricao)",
				String.class);

		query.setParameter("descricao", "%" + descricao + "%");
		return query.getResultList();
	}

}
