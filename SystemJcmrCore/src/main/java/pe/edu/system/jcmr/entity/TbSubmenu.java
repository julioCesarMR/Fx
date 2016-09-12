package pe.edu.system.jcmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_submenu database table.
 * 
 */
@Entity
@Table(name="tb_submenu")
@NamedQuery(name="TbSubmenu.findAll", query="SELECT t FROM TbSubmenu t")
public class TbSubmenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idsubmenu;

	@Column(name="fxml_name")
	private String fxmlName;

	private String i18n;

	//bi-directional many-to-one association to TbSubmenuRol
	@OneToMany(mappedBy="tbSubmenu")
	private List<TbSubmenuRol> tbSubmenuRols;

	public TbSubmenu() {
	}

	public int getIdsubmenu() {
		return this.idsubmenu;
	}

	public void setIdsubmenu(int idsubmenu) {
		this.idsubmenu = idsubmenu;
	}

	public String getFxmlName() {
		return this.fxmlName;
	}

	public void setFxmlName(String fxmlName) {
		this.fxmlName = fxmlName;
	}

	public String getI18n() {
		return this.i18n;
	}

	public void setI18n(String i18n) {
		this.i18n = i18n;
	}

	public List<TbSubmenuRol> getTbSubmenuRols() {
		return this.tbSubmenuRols;
	}

	public void setTbSubmenuRols(List<TbSubmenuRol> tbSubmenuRols) {
		this.tbSubmenuRols = tbSubmenuRols;
	}

	public TbSubmenuRol addTbSubmenuRol(TbSubmenuRol tbSubmenuRol) {
		getTbSubmenuRols().add(tbSubmenuRol);
		tbSubmenuRol.setTbSubmenu(this);

		return tbSubmenuRol;
	}

	public TbSubmenuRol removeTbSubmenuRol(TbSubmenuRol tbSubmenuRol) {
		getTbSubmenuRols().remove(tbSubmenuRol);
		tbSubmenuRol.setTbSubmenu(null);

		return tbSubmenuRol;
	}

}