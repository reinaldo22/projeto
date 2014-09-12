package br.com.projeto.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

public class PSTUtil {
	private static Logger logger = Logger.getLogger(PSTUtil.class.getName());

	public static void fechar(Connection conexao) {
		if (conexao != null) {
			try {
				conexao.close();
				logger.info(ProjetoUtil.getMessage("pstutil.conexao.fechar"));
			} catch (SQLException ex) {
				logger.warning(ProjetoUtil
						.getMessage("pstutil.conexao.fechar.erro"));
			}
		}
	}

	public static void fechar(PreparedStatement comando) {
		if (comando != null) {
			try {
				comando.close();
				logger.info(ProjetoUtil.getMessage("pstutil.declaracao.fechar"));
			} catch (SQLException ex) {
				logger.warning(ProjetoUtil
						.getMessage("pstutil.declaracao.fechar.erro"));
			}
		}
	}

	public static void fechar(ResultSet resultado) {
		if (resultado != null) {
			try {
				resultado.close();
				logger.info(ProjetoUtil.getMessage("pstutil.resultado.fechar"));
			} catch (SQLException ex) {
				logger.warning(ProjetoUtil
						.getMessage("pstutil.resultado.fechar.erro"));
			}
		}
	}
}
