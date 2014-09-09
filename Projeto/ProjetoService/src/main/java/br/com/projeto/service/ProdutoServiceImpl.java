package br.com.projeto.service;

import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.dao.ProdutoDAOImpl;
import br.com.projeto.excecao.ServiceException;
import br.com.projeto.model.Produto;

@Stateless
public class ProdutoServiceImpl implements ProdutoService {

	@Override
	public Integer contar() {
		Integer total = 0;
		try {
			total = new ProdutoDAOImpl().contar();
		} catch (RuntimeException ex) {
			throw new ServiceException(ex);
		}
		return total;
	}

	@Override
	public List<Produto> listar(int primeiro, int tamanho) {
		List<Produto> produtos = null;
		try {
			produtos = new ProdutoDAOImpl().listar(primeiro, tamanho);
		} catch (RuntimeException ex) {
			throw new ServiceException(ex);
		}
		return produtos;
	}

	@Override
	public void inserir(Produto produto) {
		try {
			validar(produto);
			new ProdutoDAOImpl().inserir(produto);
		} catch (RuntimeException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void editar(Produto produto) {
		try {
			new ProdutoDAOImpl().editar(produto);
		} catch (RuntimeException ex) {
			throw new ServiceException(ex);
		}
	}

	@Override
	public void excluir(Long codigo) {
		try {
			new ProdutoDAOImpl().excluir(codigo);
		} catch (RuntimeException ex) {
			throw new ServiceException(ex);
		}
	}

	private void validar(Produto produto) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Produto>> constraintViolations = validator
				.validate(produto);

		if (constraintViolations.size() > 0) {
			StringBuilder mensagem = new StringBuilder();
			for (ConstraintViolation<Produto> constraintViolation : constraintViolations) {
				mensagem.append(constraintViolation.getMessage() + "\n");
			}
			throw new RuntimeException(mensagem.toString());
		}
	}
}
