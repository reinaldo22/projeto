package br.com.projeto.bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.LazyDataModel;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.datamodel.ProdutoDataModel;
import br.com.projeto.excecao.ServiceException;
import br.com.projeto.model.Produto;
import br.com.projeto.util.ProjetoUtil;
import br.com.projeto.util.WebUtil;

@ManagedBean
@ViewScoped
public class ProdutoBean implements Serializable {
	private static final long serialVersionUID = 1L;
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

	public void novo() {
		produto = new Produto();
	}

	public void buscar() {
		produtosLDM = new ProdutoDataModel();
	}

	public void salvar() {
		try {
			ProdutoService service = (ProdutoService) WebUtil
					.getNamedObject(ProdutoService.NAME);
			service.inserir(produto);
			novo();
			WebUtil.adicionarMensagemSucesso(ProjetoUtil
					.getMessage("bean.produto.salvar"));
		} catch (ServiceException ex) {
			WebUtil.adicionarMensagemErro(ex.getMessage());
		}
	}

	public boolean isAdmin() {
		return true;
	}
}
