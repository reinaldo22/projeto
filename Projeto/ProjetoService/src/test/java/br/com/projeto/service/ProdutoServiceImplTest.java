package br.com.projeto.service;

import java.math.BigDecimal;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.model.Produto;

public class ProdutoServiceImplTest {
	private final static EJBContainer ejbContainer = EJBContainer.createEJBContainer();

	@Test
	@Ignore
	public void contar() throws NamingException {
		Object object = ejbContainer.getContext().lookup(
				"java:global/ProjetoService/ProdutoServiceImpl");
		ProdutoService service = (ProdutoService) object;
		int total = service.contar();
		System.out.println(total);
	}

	@Test
	@Ignore
	public void inserir() throws NamingException{
		Produto produto = new Produto();
		produto.setDescricao("TESTESERGIO");
		produto.setPreco(new BigDecimal("100.00"));
		produto.setQuantidade(new Short("10000"));
		ProdutoService service = (ProdutoService) ejbContainer.getContext().lookup(
				"java:global/ProjetoService/ProdutoServiceImpl");
		service.inserir(produto);
	}
}
