package br.com.projeto.dao;

import java.util.List;

import br.com.projeto.model.Produto;

public interface ProdutoDAO {
	public void inserir(Produto produto);

	public void editar(Produto produto);

	public void excluir(Long codigo);
	
	public List<Produto> listar(int primeiro, int tamanho);

	public int contar();
}
