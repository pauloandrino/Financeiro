package com.algaworks.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.model.Lancamento;
import com.algaworks.repository.Lancamentos;
import com.algaworks.util.JpaUtil;

@FacesConverter(forClass = Lancamento.class)
public class LancamentoConverter implements Converter {

	private Lancamentos lancamentos = new Lancamentos(JpaUtil.getEntityManager());
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Lancamento retorno = null;
		
		if (value != null && !"".equals(value)) {
			retorno = lancamentos.porId(new Long(value));
		}
		
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {

		if (value != null) {
			Lancamento lancamento = (Lancamento) value;
			return lancamento.getId() == null ? null : lancamento.getId().toString();
		}
		
		return null;
	
	}

}
