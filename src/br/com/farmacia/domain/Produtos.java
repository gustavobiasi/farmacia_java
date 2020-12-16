package br.com.farmacia.domain;

public class Produtos {

	private Long codigo;
	private String descricao;
	private Long quantidade;
	private Double preco;
	private Fornecedores fornecedores = new Fornecedores();

	public Produtos() {

	}

	public Produtos(Long codigo, String descricao, Long quantidade, Double preco, Fornecedores fornecedores) {
		this.codigo = codigo;
		this.descricao = descricao;
		this.quantidade = quantidade;
		this.preco = preco;
		this.fornecedores = fornecedores;
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

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	@Override
	public String toString() {
		return "Produtos [codigo=" + codigo + ", descricao=" + descricao + ", quantidade=" + quantidade + ", preco="
				+ preco + ", fornecedores=" + fornecedores + "]";
	}

}
