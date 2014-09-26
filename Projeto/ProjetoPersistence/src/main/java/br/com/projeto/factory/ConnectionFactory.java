package br.com.projeto.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.commons.dbcp2.BasicDataSource;

public class ConnectionFactory {
	private static Logger logger = Logger.getLogger(ConnectionFactory.class
			.getName());
	private static BasicDataSource dataSource = setUp();

	private static BasicDataSource setUp() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:mysql://192.168.0.72:3306/drogaria");
		dataSource.setUsername("srdelfino");
		dataSource.setPassword("q1w2e3r4");
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");

		dataSource.setInitialSize(1);
		dataSource.setMinIdle(1);
		dataSource.setMaxIdle(10);
		dataSource.setMaxTotal(10);

		logger.info("Pool de conexão configurado com sucesso");

		return dataSource;
	}

	public static Connection getConnection() {
		Connection conexao = null;
		try {
			conexao = dataSource.getConnection();
			logger.info("Conexão obtida com sucesso");
		} catch (SQLException ex) {
			logger.warning("Ocorreu um erro ao tentar obter uma conexão");
		}
		return conexao;
	}
}
