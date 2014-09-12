package br.com.projeto.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import org.apache.commons.dbcp2.BasicDataSource;

import br.com.projeto.util.ProjetoUtil;

public class ConnectionFactory {
	private static Logger logger = Logger.getLogger(ConnectionFactory.class
			.getName());
	private static BasicDataSource dataSource = setUp();

	private static BasicDataSource setUp() {
		ResourceBundle bundle = ResourceBundle.getBundle(
				"br.com.projeto.configuracoes.conexao", new Locale("pt", "BR"));

		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl(bundle.getString("url"));
		dataSource.setUsername(bundle.getString("usuario"));
		dataSource.setPassword(bundle.getString("senha"));
		dataSource.setDriverClassName(bundle.getString("driver"));

		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxIdle(10);
		dataSource.setMaxTotal(10);

		logger.info(ProjetoUtil.getMessage("factory.pool"));

		return dataSource;
	}

	public static Connection getConnection() {
		Connection conexao = null;
		try {
			conexao = dataSource.getConnection();
			logger.info(ProjetoUtil.getMessage("factory.conexao"));
		} catch (SQLException ex) {
			logger.warning(ProjetoUtil.getMessage("factory.erro.conexao"));
		}
		return conexao;
	}
}
