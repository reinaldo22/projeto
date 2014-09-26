package br.com.projeto.datamodel;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.projeto.client.ProdutoService;
import br.com.projeto.excecao.ServiceException;
import br.com.projeto.model.Produto;
import br.com.projeto.util.WebUtil;

@SuppressWarnings("serial")
public class ProdutoDataModel extends LazyDataModel<Produto> {
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
			ProdutoService service = (ProdutoService) WebUtil
					.getNamedObject(ProdutoService.NAME);
			
			produtos = service.listar(first, pageSize);
			
			this.setRowCount(service.contar());
		} catch (ServiceException ex) {
			WebUtil.adicionarMensagemErro(ex.getMessage());
		}
		return produtos;
	}
}
