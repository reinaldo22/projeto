package br.com.projeto.factory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Logger;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.apache.tomcat.jdbc.pool.PoolProperties;

public class ConnectionFactory {
	private static Logger logger = Logger.getLogger(ConnectionFactory.class
			.getName());
	private static DataSource dataSource;

	static {
		PoolProperties p = new PoolProperties();
		p.setUrl("jdbc:mysql://127.0.0.1:3306/drogaria");
		p.setDriverClassName("com.mysql.jdbc.Driver");
		p.setUsername("srdelfino");
		p.setPassword("q1w2e3r4");
		p.setJmxEnabled(true);
		p.setTestWhileIdle(false);
		p.setTestOnBorrow(true);
		p.setValidationQuery("SELECT 1");
		p.setTestOnReturn(false);
		p.setValidationInterval(30000);
		p.setTimeBetweenEvictionRunsMillis(30000);
		p.setMaxActive(100);
		p.setInitialSize(10);
		p.setMaxWait(10000);
		p.setRemoveAbandonedTimeout(60);
		p.setMinEvictableIdleTimeMillis(30000);
		p.setMinIdle(10);
		p.setLogAbandoned(true);
		p.setRemoveAbandoned(true);
		p.setJdbcInterceptors("org.apache.tomcat.jdbc.pool.interceptor.ConnectionState;"
				+ "org.apache.tomcat.jdbc.pool.interceptor.StatementFinalizer");

		dataSource = new DataSource();
		dataSource.setPoolProperties(p);

		logger.info("Pool de conexão configurado com sucesso");
	}

	public static Connection getConnection() throws SQLException {
		Connection conexao = null;

		conexao = dataSource.getConnection();
		logger.info("Conexão obtida com sucesso");

		return conexao;
	}
}
