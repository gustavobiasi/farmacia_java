package br.com.farmacia.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutosDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;


public class ProdutoDAOTeste {
	
	@Test
	@Ignore
	public void salvar() throws SQLException {

		Fornecedores f1 = new Fornecedores(5L, null);
		Produtos p1 = new Produtos(null, "Dipirona", 12L, 12.99, f1);
		
		
		ProdutosDAO fdao = new ProdutosDAO();
		
		fdao.salvar(p1);
	}
	
	@Test
	@Ignore
	public void listarProdutos() throws SQLException {

		ProdutosDAO pdao = new ProdutosDAO();
		ArrayList<Produtos> lista = pdao.listarProdutos();
		
		for (Produtos p : lista) {
			System.out.println("Produto é: " + p);
			
		}
	}
	
	@Test
	@Ignore
	public void excluirProdutos() throws SQLException {
		Produtos p = new Produtos(10L, null, null, null, null);
		
		ProdutosDAO pdao = new ProdutosDAO();
		
		pdao.excluir(p);		
	}
	
	@Test
	public void editar() throws SQLException {
		Fornecedores f1 = new Fornecedores(1L, null);
		
		Produtos p1 = new Produtos(2L, "Pao de Queijoo", 5L, 5.99, f1);
		
		ProdutosDAO pdao = new ProdutosDAO();
		
		pdao.editar(p1);		
	}
	
	
}
