package br.com.projeto.factory;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

public class ConnectionFactoryTest {
	@Test
	@Ignore
	public void conectar() throws SQLException{
		ConnectionFactory.getConnection();
	}
}
