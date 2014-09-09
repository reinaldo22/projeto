package br.com.projeto.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.projeto.excecao.PSTException;

public class PSTUtil {
	public static void fechar(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
			} catch (SQLException ex) {
				throw new PSTException(
						ProjetoUtil.getMessage("pstutil.erro.fecharConexao"),
						ex);
			}
		}
	}

	public static void fechar(PreparedStatement comando) {
		if (comando != null) {
			try {
				comando.close();
			} catch (SQLException ex) {
				throw new PSTException(
						ProjetoUtil.getMessage("pstutil.erro.fecharPreparedStatement"),
						ex);
			}
		}
	}

	public static void fechar(ResultSet resultado) {
		if (resultado != null) {
			try {
				resultado.close();
			} catch (SQLException ex) {
				throw new PSTException(
						ProjetoUtil.getMessage("pstutil.erro.fecharResultSet"),
						ex);
			}
		}
	}
}
