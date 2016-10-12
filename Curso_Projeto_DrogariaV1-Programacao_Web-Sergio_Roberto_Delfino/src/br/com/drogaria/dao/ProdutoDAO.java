package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;
import br.com.drogaria.factory.ConexaoFactory;

public class ProdutoDAO {

	public void salvar(Produto p) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produto ");
		sql.append("(descricao, quantidade, preco, fabricante_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		
		comando.executeUpdate();
	}

	public void excluir(Produto p) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produto ");
		sql.append("WHERE produto.codigo = ?");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		
		comando.executeUpdate();
	}

	public void editar(Produto p) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produto ");
		sql.append("SET produto.descricao = ? ");
		sql.append("WHERE produto.codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setLong(2, p.getCodigo());
		
		comando.executeUpdate();
	}
	
	public Produto buscaPorCodigo(Produto p) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco, fabricante_codigo FROM produto ");
		sql.append("WHERE produto.codigo = ? ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		
		Produto retorno = null;
		
		if(resultado.next()){
			retorno = new Produto();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
			p.setQuantidade(resultado.getLong("quantidade"));
			p.setPreco(resultado.getDouble("preco"));
		}
		
		return retorno;
	}
	
	public ArrayList<Produto> listar() throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco, fabricante_codigo FROM produto ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		while(resultado.next()){
			Produto p = new Produto();
			p.setCodigo(resultado.getLong("codigo"));
			p.setDescricao(resultado.getString("descricao"));			
			p.setQuantidade(resultado.getLong("quantidade"));
			p.setPreco(resultado.getDouble("preco"));
			
			lista.add(p);
		}
		
		return lista;
	}
	
	public ArrayList<Produto> buscaPorDescricao(Produto p) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao, quantidade, preco, fabricante_codigo FROM produto ");
		sql.append("WHERE produto.descricao LIKE %?% ");
		sql.append("ORDER BY descricao ASC ");

		Connection conexao = ConexaoFactory.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produto> lista = new ArrayList<Produto>();
		
		while(resultado.next()){
			Produto item = new Produto();
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}
		
		return lista;
	}
	
}