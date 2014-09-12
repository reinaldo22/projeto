package br.com.projeto.service;

import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.dao.ProdutoDAOImpl;
import br.com.projeto.excecao.PSTException;
import br.com.projeto.excecao.ServiceException;
import br.com.projeto.model.Produto;
import br.com.projeto.util.ProjetoUtil;

@Stateless
public class ProdutoServiceImpl implements ProdutoService {
	private static Logger logger = Logger.getLogger(ProdutoServiceImpl.class
			.getName());

	@Override
	public Integer contar() {
		Integer total = 0;
		try {
			total = new ProdutoDAOImpl().contar();
			logger.info(ProjetoUtil.getMessage("service.produto.contar"));
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
			logger.info(ProjetoUtil.getMessage("service.produto.listar"));
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
		return produtos;
	}

	@Override
	public void inserir(Produto produto) {
		try {
			validar(produto);
			new ProdutoDAOImpl().inserir(produto);
			logger.info(ProjetoUtil.getMessage("service.produto.inserir"));
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void editar(Produto produto) {
		try {
			validar(produto);
			new ProdutoDAOImpl().editar(produto);
			logger.info(ProjetoUtil.getMessage("service.produto.editar"));
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void excluir(Long codigo) {
		try {
			new ProdutoDAOImpl().excluir(codigo);
			logger.info(ProjetoUtil.getMessage("service.produto.excluir"));
		} catch (PSTException ex) {
			throw new ServiceException(ex);
		}
	}

	private void validar(Produto produto) throws PSTException {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Produto>> constraintViolations = validator
				.validate(produto);

		if (constraintViolations.size() > 0) {
			StringBuilder mensagem = new StringBuilder();
			for (ConstraintViolation<Produto> constraintViolation : constraintViolations) {
				mensagem.append(constraintViolation.getMessage() + "\n");
			}
			throw new PSTException(mensagem.toString());
		}
	}
}
