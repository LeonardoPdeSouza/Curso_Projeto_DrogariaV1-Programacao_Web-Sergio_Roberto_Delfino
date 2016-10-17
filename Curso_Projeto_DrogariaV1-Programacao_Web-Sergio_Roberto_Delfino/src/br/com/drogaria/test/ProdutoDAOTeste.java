package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	@Test
	public void salvar() throws SQLException{
		Produto p = new Produto();
		p.setDescricao("PRODUTO 1");
		p.setPreco(2.45D);
		p.setQuantidade(13L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(16L);
		
		p.setFabricante(f);
		
		Produto p2 = new Produto();
		p2.setDescricao("PRODUTO 2");
		p2.setPreco(2.45D);
		p2.setQuantidade(13L);
		
		Fabricante f2 = new Fabricante();
		f2.setCodigo(16L);
		
		p2.setFabricante(f2);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.salvar(p);
		dao.salvar(p2);
	}
	@Test
	@Ignore
	public void listar() throws SQLException {
		ProdutoDAO dao = new ProdutoDAO();
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p : lista){
			System.out.println("Código do Produto: " + p.getCodigo());
			System.out.println("Descrição do Produto: " + p.getDescricao());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Código do Fabricante: " + p.getFabricante().getCodigo());
			System.out.println("Descrição do Fabricante: " + p.getFabricante().getDescricao());
			System.out.println("");
		}
	}
	@Test
	@Ignore
	public void excluir() throws SQLException {
		Produto p = new Produto();
		p.setCodigo(1L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
		
	}
	@Test
	@Ignore
	public void editar() throws SQLException {
		Produto p = new Produto();
		p.setCodigo(2L);
		p.setDescricao("Cataflan Pomada com 60 gramas");
		p.setPreco(15.30D);
		p.setQuantidade(7L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(14L);
		
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
	}
}
