package br.com.projeto.datamodel;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.excecao.ServiceException;
import br.com.projeto.excecao.WebException;
import br.com.projeto.model.Produto;

@SuppressWarnings("serial")
public class ProdutoDataModel extends LazyDataModel<Produto>{
	private List<Produto> produtos;

	@Override
	public Object getRowKey(Produto object) {
		return object.getCodigo();
	}

	@Override
	public Produto getRowData(String rowKey) {
		Long codigo = Long.parseLong(rowKey);
		for (Produto produto : produtos) {
			if (produto.getCodigo() == codigo) {
				return produto;
			}
		}
		return null;
	}

	@Override
	public List<Produto> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		try {	
			Properties prop = new Properties();
			prop.put("java.naming.factory.initial", "org.apache.openejb.client.RemoteInitialContextFactory");
			prop.put("java.naming.provider.url", "http://localhost:8080/tomee/ejb");
			InitialContext ctx = new InitialContext(prop);
			Object objeto = ctx.lookup("global/ProjetoService/ProdutoServiceImpl!br.com.projeto.client.ProdutoService");
			ProdutoService facade = (ProdutoService)objeto;
			
			produtos = facade.listar(first, pageSize);
			this.setRowCount(facade.contar());
		} catch (ServiceException | NamingException ex) {
			throw new WebException(ex);
		}
		return produtos;
	}
}
