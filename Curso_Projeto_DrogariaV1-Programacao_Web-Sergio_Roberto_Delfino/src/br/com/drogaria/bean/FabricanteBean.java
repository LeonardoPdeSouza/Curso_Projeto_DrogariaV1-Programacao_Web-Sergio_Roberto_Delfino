package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricante;
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}
	
	public void prepararNovo() {
		fabricante = new Fabricante();
	} 
	
	@PostConstruct
	public void preparaPesquisa() {
		try {

			FabricanteDAO dao = new FabricanteDAO();
			itens = dao.listar();

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Não foi possivel listar os fabricantes"
					+ ex.getMessage() + "\n" + ex.getErrorCode());
		}
	}
	
	public void novo() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabricante);
			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Fabricante cadastrado com sucesso !");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Não foi Gravar o fabricante"
					+ ex.getMessage() + "\n" + ex.getErrorCode());
		}
}

	public void excluir() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);
			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Fabricante Excluido com sucesso !");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Não foi excluir o fabricante"
					+ ex.getMessage() + "\n" + ex.getErrorCode());
		}

	}

	public void editar() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricante);
			itens = dao.listar();

			JSFUtil.adicionarMensagemSucesso("Fabricante Editado com sucesso !");

		} catch (SQLException ex) {
			ex.printStackTrace();
			JSFUtil.adicionarMensagemErro("Não foi editar o fabricante"
					+ ex.getMessage() + "\n" + ex.getErrorCode());
		}

	}
}	
