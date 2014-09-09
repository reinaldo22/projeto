package br.com.projeto.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.projeto.excecao.PSTException;
import br.com.projeto.factory.ConnectionFactory;
import br.com.projeto.model.Produto;
import br.com.projeto.util.PSTUtil;
import br.com.projeto.util.ProjetoUtil;

public class ProdutoDAOImpl implements ProdutoDAO {
	@Override
	public List<Produto> listar(int primeiro, int tamanho) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade ");
		sql.append("FROM produtos p ");
		sql.append("ORDER BY p.descricao ");

		if (primeiro > -1 && tamanho > -1) {
			sql.append("LIMIT ?, ? ");
		}

		Connection conexao = null;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		List<Produto> produtos = new ArrayList<Produto>();

		try {
			conexao = ConnectionFactory.getConnection();

			comando = conexao.prepareStatement(sql.toString());
			if (primeiro > -1 && tamanho > -1) {
				comando.setInt(1, primeiro);
				comando.setInt(2, tamanho);
			}

			resultado = comando.executeQuery();

			while (resultado.next()) {
				Produto produto = new Produto();
				produto.setCodigo(resultado.getLong("codigo"));
				produto.setDescricao(resultado.getString("descricao"));
				produto.setPreco(resultado.getBigDecimal("preco"));
				produto.setQuantidade(resultado.getShort("quantidade"));

				produtos.add(produto);
			}

		} catch (SQLException ex) {
			throw new PSTException(
					ProjetoUtil.getMessage("dao.erro.produto.listar"), ex);
		} finally {
			PSTUtil.fechar(resultado);
			PSTUtil.fechar(comando);
			PSTUtil.fechar(conexao);
		}

		return produtos;
	}

	@Override
	public int contar() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT COUNT(*) ");
		sql.append("FROM produtos p ");

		Connection conexao = null;
		PreparedStatement comando = null;
		ResultSet resultado = null;
		int total = 0;

		try {
			conexao = ConnectionFactory.getConnection();

			comando = conexao.prepareStatement(sql.toString());
			resultado = comando.executeQuery();

			if (resultado.next()) {
				total = resultado.getInt(1);
			}
		} catch (SQLException ex) {
			throw new PSTException(
					ProjetoUtil.getMessage("dao.erro.produto.contar"), ex);
		} finally {
			PSTUtil.fechar(resultado);
			PSTUtil.fechar(comando);
			PSTUtil.fechar(conexao);
		}

		return total;
	}

	@Override
	public void inserir(Produto produto) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao, preco, quantidade) ");
		sql.append("VALUES (?, ?, ?) ");

		Connection conexao = null;
		PreparedStatement comando = null;

		try {
			conexao = ConnectionFactory.getConnection();

			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, produto.getDescricao());
			comando.setBigDecimal(2, produto.getPreco());
			comando.setShort(3, produto.getQuantidade());

			comando.executeUpdate();
		} catch (SQLException ex) {
			throw new PSTException(
					ProjetoUtil.getMessage("dao.erro.produto.inserir"), ex);
		} finally {
			PSTUtil.fechar(comando);
			PSTUtil.fechar(conexao);
		}
	}

	@Override
	public void editar(Produto produto) {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = null;
		PreparedStatement comando = null;

		try {
			conexao = ConnectionFactory.getConnection();

			comando = conexao.prepareStatement(sql.toString());
			comando.setString(1, produto.getDescricao());
			comando.setBigDecimal(2, produto.getPreco());
			comando.setShort(3, produto.getQuantidade());
			comando.setLong(4, produto.getCodigo());

			comando.executeUpdate();
		} catch (SQLException ex) {
			throw new PSTException(
					ProjetoUtil.getMessage("dao.erro.produto.editar"), ex);
		} finally {
			PSTUtil.fechar(comando);
			PSTUtil.fechar(conexao);
		}
	}

	@Override
	public void excluir(Long codigo) {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = null;
		PreparedStatement comando = null;

		try {
			conexao = ConnectionFactory.getConnection();

			comando = conexao.prepareStatement(sql.toString());
			comando.setLong(1, codigo);

			comando.executeUpdate();
		} catch (SQLException ex) {
			throw new PSTException(
					ProjetoUtil.getMessage("dao.erro.produto.excluir"), ex);
		} finally {
			PSTUtil.fechar(comando);
			PSTUtil.fechar(conexao);
		}
	}
}
