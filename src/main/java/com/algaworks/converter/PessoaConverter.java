package com.algaworks.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.algaworks.model.Pessoa;
import com.algaworks.repository.Pessoas;
import com.algaworks.util.JpaUtil;


@FacesConverter(forClass = Pessoa.class)
public class PessoaConverter implements Converter {
	
	// Aqui deveria ser @Inject - Mas só é possível com o omnifaces - no qual não consegui fazer funcionar com o Tomcat e Weld CDI
	Pessoas pessoas = new Pessoas(JpaUtil.getEntityManager());

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		Pessoa retorno = null;

		
		if (value != null && !"".equals(value)) {
			retorno = pessoas.porId(new Long(value));
		}
		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		
		if (value != null) {
			return ((Pessoa) value).getId().toString();
		}
		
		return null;
	}

}
