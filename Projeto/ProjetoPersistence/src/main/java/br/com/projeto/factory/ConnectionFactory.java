package br.com.projeto.factory;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import br.com.projeto.enumeracao.ConexaoEnum;
import br.com.projeto.excecao.PSTException;
import br.com.projeto.util.ProjetoUtil;

public class ConnectionFactory {
	private static BasicDataSource dataSource = setUp();

	private static BasicDataSource setUp() {
		BasicDataSource dataSource = null;
		try {
			dataSource = new BasicDataSource();
			dataSource.setUrl(ConexaoEnum.MYSQL1.getUrl());
			dataSource.setUsername(ConexaoEnum.MYSQL1.getUsuario());
			dataSource.setPassword(ConexaoEnum.MYSQL1.getSenha());
			dataSource.setDriverClassName(ConexaoEnum.MYSQL1.getDriver());

			dataSource.setInitialSize(1);
			dataSource.setMinIdle(1);
			dataSource.setMaxIdle(10);
			dataSource.setMaxTotal(10);
		} catch (RuntimeException ex) {
			throw new PSTException(ProjetoUtil.getMessage("factory.erro.pool"), ex);
		}
		return dataSource;
	}

	public static Connection getConnection() {
		Connection conexao = null;
		try {
			conexao = dataSource.getConnection();
		} catch (SQLException ex) {
			throw new PSTException(ProjetoUtil.getMessage("factory.erro.conexao"),
					ex);
		}
		return conexao;
	}
}
