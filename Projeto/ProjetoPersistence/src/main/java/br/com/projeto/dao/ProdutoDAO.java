package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.excecao.PSTException;
import br.com.projeto.model.Produto;

public interface ProdutoDAO {
	public void inserir(Produto produto) throws PSTException;

	public void editar(Produto produto) throws PSTException;

	public void excluir(Long codigo) throws PSTException;
	
	public List<Produto> listar(int primeiro, int tamanho) throws PSTException;

	public int contar() throws PSTException;
}
