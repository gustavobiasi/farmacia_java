package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name = "MBFornecedores")
@ViewScoped
public class FornecedoresBean {

	private Fornecedores fornecedores;
	private ArrayList<Fornecedores> itens;
	private ArrayList<Fornecedores> itensFiltrados;

	public Fornecedores getFornecedores() {
		return fornecedores;
	}

	public void setFornecedores(Fornecedores fornecedores) {
		this.fornecedores = fornecedores;
	}

	public ArrayList<Fornecedores> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fornecedores> itens) {
		this.itens = itens;
	}

	public ArrayList<Fornecedores> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fornecedores> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	@PostConstruct
	public void prepararPesquisa() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			itens = fdao.listarFornecedores();
			

		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}

	public void prepararNovo() {
		fornecedores = new Fornecedores();
	}

	public void novo() {

		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.Salvar(fornecedores);

			// atualizar a lista automaticamente
			itens = fdao.listarFornecedores();			

			JSFUtil.adicionarMensagemSucesso("Fornecedor salvo com sucesso");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("ex.getMessage()");
			e.printStackTrace();
		}
	}


	public void excluir() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.Excluir(fornecedores);

			// atualizar a lista automaticamente
			itens = fdao.listarFornecedores();
			

			JSFUtil.adicionarMensagemSucesso("Fornecedor excluído com sucesso");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro("Não é possivel excluir um fornecedor que tenha um produto associado");
			e.printStackTrace();
		}
	}


	public void editar() {
		try {
			FornecedoresDAO fdao = new FornecedoresDAO();
			fdao.Editar(fornecedores);

			// atualizar a lista automaticamente
			itens = fdao.listarFornecedores();			

			JSFUtil.adicionarMensagemSucesso("Fornecedor editado com sucesso");

		} catch (SQLException e) {
			JSFUtil.adicionarMensagemErro(e.getMessage());
			e.printStackTrace();
		}
	}

}
