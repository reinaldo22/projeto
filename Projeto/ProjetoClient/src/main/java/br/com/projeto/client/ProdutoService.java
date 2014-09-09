package br.com.projeto.client;

import java.util.List;

import javax.ejb.Remote;

import br.com.projeto.model.Produto;

@Remote
public interface ProdutoService {
	public void inserir(Produto produto);

	public Integer contar();

	public List<Produto> listar(int primeiro, int tamanho);

	public void editar(Produto produto);

	public void excluir(Long codigo);
}
