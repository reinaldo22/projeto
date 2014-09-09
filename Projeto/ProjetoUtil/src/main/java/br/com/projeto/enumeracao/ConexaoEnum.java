package br.com.projeto.enumeracao;

public enum ConexaoEnum {
	MYSQL1("com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/drogaria",
			"root", "q1w2e3r4"), MYSQL2("com.mysql.jdbc.Driver",
			"jdbc:mysql://mysql-micromapteste.jelasticlw.com.br:3306/drogaria",
			"root", "QT0q7o5Cqc");

	private String driver;
	private String url;
	private String usuario;
	private String senha;

	private ConexaoEnum(String driver, String url, String usuario, String senha) {
		this.driver = driver;
		this.url = url;
		this.usuario = usuario;
		this.senha = senha;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
