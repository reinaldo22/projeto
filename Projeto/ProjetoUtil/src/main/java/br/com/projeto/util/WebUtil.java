package br.com.projeto.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


public class WebUtil {
	public static void adicionarMensagemErro(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, mensagem, null));
	}
	
	public static void adicionarMensagemSucesso(String mensagem) {
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, mensagem, null));
	}
}
