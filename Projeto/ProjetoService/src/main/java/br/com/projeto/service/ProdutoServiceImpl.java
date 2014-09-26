package br.com.projeto.service;

import java.util.List;
import java.util.logging.Logger;

import javax.ejb.Stateless;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.dao.ProdutoDAOImpl;
import br.com.projeto.excecao.PSTException;
import br.com.projeto.excecao.ServiceException;
import br.com.projeto.model.Produto;

@Stateless
public class ProdutoServiceImpl implements ProdutoService {
	private static Logger logger = Logger.getLogger(ProdutoServiceImpl.class
			.getName());

	@Override
	public Integer contar() {
		Integer total = 0;
		try {
			total = new ProdutoDAOImpl().contar();
			logger.info("Total de produtos obtido com sucesso");
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
		return total;
	}

	@Override
	public List<Produto> listar(int primeiro, int tamanho) {
		List<Produto> produtos = null;
		try {
			produtos = new ProdutoDAOImpl().listar(primeiro, tamanho);
			logger.info("Listagem dos produtos realizada com sucesso");
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
		return produtos;
	}

	@Override
	public void inserir(Produto produto) {
		try {
			new ProdutoDAOImpl().inserir(produto);
			logger.info("Produto inserido com sucesso");
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void editar(Produto produto) {
		try {
			new ProdutoDAOImpl().editar(produto);
			logger.info("Produto editado com sucesso");
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void excluir(Long codigo) {
		try {
			new ProdutoDAOImpl().excluir(codigo);
			logger.info("Produto excluído com sucesso");
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
	}
}
