package com.algaworks.manageBean;

import java.util.Date;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.algaworks.controller.Usuario;

@Named
@RequestScoped
public class LoginBean {

	@Inject
	private Usuario usuario;

	private String nomeUsuario;
	private String senha;

	public String login() {
		FacesContext context = FacesContext.getCurrentInstance();

		if ("admin".equals(this.nomeUsuario) && "123".equals(senha)) {
			this.usuario.setNome(nomeUsuario);
			this.usuario.setDataLogin(new Date());

			return "/ConsultaLancamentos?faces-redirect=true";
		} else {
			FacesMessage message = new FacesMessage("Usuário/Senha inválidos!");
			message.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, message);
		}
		return null;
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/Login?faces-redirect=true";
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
