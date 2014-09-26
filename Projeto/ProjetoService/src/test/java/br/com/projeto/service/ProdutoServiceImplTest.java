package br.com.projeto.service;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.NamingException;
import javax.xml.rpc.ServiceException;

import org.junit.Ignore;
import org.junit.Test;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.model.Produto;

public class ProdutoServiceImplTest {
	private final static EJBContainer ejbContainer = EJBContainer.createEJBContainer();

	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void contar() throws NamingException, ServiceException {
		ProdutoService service = (ProdutoService) ejbContainer.getContext().lookup(
				"java:global/ProjetoService/ProdutoServiceImpl");
		int total = service.contar();
	}
	
	@SuppressWarnings("unused")
	@Test
	@Ignore
	public void listar() throws NamingException, ServiceException {
		ProdutoService service = (ProdutoService) ejbContainer.getContext().lookup(
				"java:global/ProjetoService/ProdutoServiceImpl");
		List<Produto> lstProdutos = service.listar(-1, -1);
	}

	@Test
	@Ignore
	public void inserir() throws NamingException, ServiceException{
		Produto produto = new Produto();
		produto.setDescricao("TESTESERGIO");
		produto.setPreco(new BigDecimal("100.00"));
		produto.setQuantidade(new Short("10000"));
		ProdutoService service = (ProdutoService) ejbContainer.getContext().lookup(
				"java:global/ProjetoService/ProdutoServiceImpl");
		service.inserir(produto);
	}
}
