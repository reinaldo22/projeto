package br.com.projeto.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class ProjetoUtil {
	public static String getMessage(String baseName, Locale locale, String chave) {
		String mensagem = null;
		ResourceBundle bundle = ResourceBundle.getBundle(baseName, locale);
		mensagem = bundle.getString(chave);
		return mensagem;
	}

	public static String getMessage(String chave) {
		String mensagem = null;
		ResourceBundle bundle = ResourceBundle.getBundle(
				"br.com.projeto.mensagens.mensagens", new Locale("pt", "BR"));
		mensagem = bundle.getString(chave);
		return mensagem;
	}
}
