package br.com.projeto.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@SuppressWarnings("serial")
public class Produto implements Serializable {
	@Min(value = 0, message = "O valor minímo para o campo código é 0.")
	private Long codigo;
	
	@NotNull(message = "O campo descrição é obrigatório.")
	@Size(min = 5, max = 50, message = "Informe um valor válido para o campo descrição (5 - 50 caracteres).")
	private String descricao;
	
	@NotNull(message = "O campo preço é obrigatório")
	@DecimalMin(value = "0.00", message = "Informe um valor válido para o campo preço (0,00 - 10000,00).")
	@DecimalMax(value = "10000.00", message = "Informe um valor válido para o campo preço (0,00 - 10000,00).")
	private BigDecimal preco;
	
	@NotNull(message = "O campo quantidade é obrigatório")
	@Min(value = 0, message = "Informe um valor válido para o campo quantidade (0 - 1000)")
	@Max(value = 1000, message = "Informe um valor válido para o campo quantidade (0 - 1000)")
	private Short quantidade;
	
	public Produto() {
		super();
	}
	
	public Produto(Long codigo){
		super();
		this.codigo = codigo;
	}

	public Produto(String descricao, BigDecimal preco, Short quantidade) {
		super();
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public Produto(Long codigo, String descricao, BigDecimal preco,
			Short quantidade) {
		super();
		this.codigo = codigo;
		this.descricao = descricao;
		this.preco = preco;
		this.quantidade = quantidade;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Short getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Short quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", descricao=" + descricao
				+ ", preco=" + preco + ", quantidade=" + quantidade + "]";
	}
}
