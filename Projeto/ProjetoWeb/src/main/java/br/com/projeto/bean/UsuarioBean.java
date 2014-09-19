package br.com.projeto.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;

import br.com.projeto.model.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean implements Serializable {
	private static final long serialVersionUID = 1L;
	private Usuario usuario;

	public Usuario getUsuario() {
		if (usuario == null) {
			usuario = new Usuario();
		}
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void login() {

	}

	public void logout() {

	}
}
