package br.com.projeto.bean;

import java.io.Serializable;
import java.util.Properties;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.datamodel.ProdutoDataModel;
import br.com.projeto.excecao.ServiceException;
import br.com.projeto.model.Produto;
import br.com.projeto.util.WebUtil;

@SuppressWarnings("serial")
@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private Produto produto;
	private String pesquisa;
	private LazyDataModel<Produto> produtosLDM;

	public Produto getProduto() {
		if (produto == null) {
			produto = new Produto();
		}
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
	public String getPesquisa() {
		return pesquisa;
	}
	
	public void setPesquisa(String pesquisa) {
		this.pesquisa = pesquisa;
	}

	public LazyDataModel<Produto> getProdutosLDM() {
		return produtosLDM;
	}

	public void setProdutosLDM(LazyDataModel<Produto> produtosLDM) {
		this.produtosLDM = produtosLDM;
	}

	public void novo(ActionEvent evt){
		produto = new Produto();
	}
	
	public void buscar(ActionEvent evt){
		produtosLDM = new ProdutoDataModel();
	}
	
	public void salvar(ActionEvent evt){
		try {	
			Properties prop = new Properties();
			prop.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
			prop.put("java.naming.provider.url", "http://localhost:8080/tomee/ejb");
			InitialContext ctx = new InitialContext(prop);
			ProdutoService service = (ProdutoService) ctx.lookup("global/ProjetoService/ProdutoServiceImpl!br.com.projeto.client.ProdutoService");
			service.inserir(produto);
			novo(evt);
			WebUtil.adicionarMensagemSucesso("Produto salvo com sucesso.");
		} catch (ServiceException | NamingException ex) {
			WebUtil.adicionarMensagemErro(ex.getMessage());
		}
	}
}
