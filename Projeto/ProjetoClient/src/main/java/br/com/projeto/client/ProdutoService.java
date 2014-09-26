package br.com.projeto.client;

import java.util.List;

import javax.ejb.Remote;

import br.com.projeto.excecao.ServiceException;
import br.com.projeto.model.Produto;

@Remote
public interface ProdutoService {
	public static final String NAME = "global/ProjetoService/ProdutoServiceImpl!br.com.projeto.client.ProdutoService";
	
	public void inserir(Produto produto) throws ServiceException;

	public Integer contar() throws ServiceException;

	public List<Produto> listar(int primeiro, int tamanho) throws ServiceException;

	public void editar(Produto produto) throws ServiceException;

	public void excluir(Long codigo) throws ServiceException;
}
