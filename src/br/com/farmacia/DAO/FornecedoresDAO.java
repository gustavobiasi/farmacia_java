package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {

	public void Salvar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("INSERT INTO fornecedores (descricao) ");
		sql.append("VALUES(?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();
	}

	public void Excluir(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}

	public void Editar(Fornecedores f) throws SQLException {
		StringBuilder sql = new StringBuilder();

		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());		
		comando.executeUpdate();
	}

	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {
			retorno = new Fornecedores(resultado.getLong("codigo"), resultado.getString("descricao"));
		}

		return retorno;
	}

	public ArrayList<Fornecedores> listarFornecedores() throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (resultado.next()) {
			Fornecedores fFornecedor = new Fornecedores(resultado.getLong("codigo"), resultado.getString("descricao"));
			lista.add(fFornecedor);
		}

		return lista;
	}

	public ArrayList<Fornecedores> listarPorDescricao(Fornecedores f) throws SQLException {

		StringBuilder sql = new StringBuilder();

		sql.append("select codigo, descricao ");
		sql.append("from fornecedores ");
		sql.append("where descricao LIKE ? ");

		Connection conexao = ConexaoFactory.conectar();
		PreparedStatement comando = conexao.prepareStatement(sql.toString());

		comando.setString(1, "%" + f.getDescricao() + "%");

		ResultSet resultado = comando.executeQuery();
		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (resultado.next()) {
			Fornecedores fFornecedor = new Fornecedores(resultado.getLong("codigo"), resultado.getString("descricao"));
			lista.add(fFornecedor);
		}

		return lista;

	}

	public static void main(String[] args) {

		FornecedoresDAO fdao = new FornecedoresDAO();

		Fornecedores f1 = new Fornecedores(null, "Computer");
		Fornecedores f2 = new Fornecedores(null, "Somzão");
		Fornecedores f3 = new Fornecedores(null, "Carteira");
		Fornecedores f4 = new Fornecedores(null, "Chave");		

		try {		

			fdao.Salvar(f1);
			fdao.Salvar(f2);
			fdao.Salvar(f3);
			fdao.Salvar(f4);

		} catch (Exception ex) {
			System.out.println("Erro ao salvar fornecedoresDAO. Erro:" + ex.getMessage());
		}

	}
}
