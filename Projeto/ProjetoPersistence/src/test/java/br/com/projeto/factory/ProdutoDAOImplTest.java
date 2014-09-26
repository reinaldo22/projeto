package br.com.projeto.factory;

import java.util.List;

import org.junit.Test;

import br.com.projeto.dao.ProdutoDAO;
import br.com.projeto.dao.ProdutoDAOImpl;
import br.com.projeto.excecao.PSTException;
import br.com.projeto.model.Produto;

public class ProdutoDAOImplTest {
	@SuppressWarnings("unused")
	@Test
	public void listar() throws PSTException {
		ProdutoDAO produtoDAO = new ProdutoDAOImpl();
		List<Produto> lstProdutos = produtoDAO.listar(-1, -1);
	}
}
