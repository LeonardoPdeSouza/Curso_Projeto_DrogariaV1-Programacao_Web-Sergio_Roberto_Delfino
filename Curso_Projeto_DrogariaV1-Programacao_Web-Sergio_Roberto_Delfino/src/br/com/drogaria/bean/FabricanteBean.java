package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.ListDataModel;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;

@ManagedBean(name = "MBFabricante")
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricante;
	private ListDataModel<Fabricante> itens;

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	public ListDataModel<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ListDataModel<Fabricante> itens) {
		this.itens = itens;
	}
	
	public void prepararNovo() {
		fabricante = new Fabricante();
	} 
	
	@PostConstruct
	public void preparaPesquisa() {
		try {

			FabricanteDAO dao = new FabricanteDAO();

			ArrayList<Fabricante> lista = dao.listar();

			itens = new ListDataModel<Fabricante>(lista);

		} catch (SQLException ex) {
			ex.printStackTrace();
			//JSFUtil.adicionarMensagemErro("Não foi possivel listar os fabricantes"
			//		+ ex.getMessage() + "\n" + ex.getErrorCode());
		}
	}
	
	public void novo() {

		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabricante);

			ArrayList<Fabricante> lista = dao.listar();
			itens = new ListDataModel<Fabricante>(lista);

			//JSFUtil.adicionarMensagemSucesso("Fabricante cadastrado com sucesso !");

		} catch (SQLException ex) {
			ex.printStackTrace();
			//JSFUtil.adicionarMensagemErro("Não foi Gravar o fabricante"
			//		+ ex.getMessage() + "\n" + ex.getErrorCode());
		}
}
}	
