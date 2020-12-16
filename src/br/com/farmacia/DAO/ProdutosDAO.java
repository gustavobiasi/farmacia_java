package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.xml.xpath.XPathEvaluationResult.XPathResultType;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutosDAO {

	public void salvar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO produtos (descricao, quantidade, preco, fornecedores_codigo) ");
		sql.append("VALUES(?,?,?,?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setLong(2, p.getQuantidade());
		comando.setDouble(3, p.getPreco());
		comando.setLong(4, p.getFornecedores().getCodigo());
		
		comando.executeUpdate();
	}
	
	public ArrayList<Produtos> listarProdutos() throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("select a.codigo, a.descricao, a.quantidade, a.preco, b.codigo, b.descricao ");
		sql.append("from produtos a ");
		sql.append("INNER JOIN fornecedores b on (b.codigo = a.fornecedores_codigo) ");		
		

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		ArrayList<Produtos> lista = new ArrayList<Produtos>();

		while (resultado.next()) {
			Fornecedores f1 = new Fornecedores(resultado.getLong("b.codigo"), resultado.getString("b.descricao"));
			
			Produtos lProdutos = new Produtos(resultado.getLong("a.codigo"), 
											  resultado.getString("a.descricao"),
											  resultado.getLong("a.quantidade"),
											  resultado.getDouble("a.preco"),
											  f1);
			lista.add(lProdutos);
		}

		return lista;
	}
	
	public void excluir(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Produtos p) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco = ?, quantidade = ?, fornecedores_codigo = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.setLong(5, p.getCodigo());
		comando.executeUpdate();
	}

}
