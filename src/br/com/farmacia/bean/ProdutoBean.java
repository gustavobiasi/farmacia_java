package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBProduto")
@ViewScoped
public class ProdutoBean {

	private Produtos produtos;
	private ArrayList<Produtos> itens;
	private ArrayList<Produtos> itensFiltrados;
	private ArrayList<Fornecedores> comboFornecedores;

	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}

	public void setComboFornecedores(ArrayList<Fornecedores> comboFornecedores) {
		this.comboFornecedores = comboFornecedores;
	}

	public Produtos getProdutos() {
		return produtos;
	}

	public void setprodutos(Produtos produtos) {
		this.produtos = produtos;
	}

	public ArrayList<Produtos> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}

	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			ProdutosDAO pdao = new ProdutosDAO();
			itens = pdao.listarProdutos();

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}
	
	public void prepararNovo() {
	
		try {
			produtos = new Produtos();
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			comboFornecedores = fdao.listarFornecedores();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	public void novo() {

		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.salvar(produtos);

			// atualizar a lista automaticamente
			itens = fdao.listarProdutos();			

			JSFUtil.adicionarMensagemSucesso("Produto salvo com sucesso");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void excluir() {
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.excluir(produtos);

			// atualizar a lista automaticamente
			itens = fdao.listarProdutos();
			

			JSFUtil.adicionarMensagemSucesso("Produto excluído com sucesso");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void editar() {
		try {
			ProdutosDAO fdao = new ProdutosDAO();
			fdao.editar(produtos);

			// atualizar a lista automaticamente
			itens = fdao.listarProdutos();			

			JSFUtil.adicionarMensagemSucesso("Produto editado com sucesso");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public void prepararEditar() {
		
		try {
			produtos = new Produtos();
			
			FornecedoresDAO fdao = new FornecedoresDAO();
			comboFornecedores = fdao.listarFornecedores();
		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
		
	}


}
