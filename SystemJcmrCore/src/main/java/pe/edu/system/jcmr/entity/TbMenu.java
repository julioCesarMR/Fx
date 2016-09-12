package pe.edu.system.jcmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_menu database table.
 * 
 */
@Entity
@Table(name="tb_menu")
@NamedQuery(name="TbMenu.findAll", query="SELECT t FROM TbMenu t")
public class TbMenu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_menu")
	private int idMenu;

	private String i18nMenu;

	private String imagenurl;

	//bi-directional many-to-one association to TbSubmenuRol
	@OneToMany(mappedBy="tbMenu")
	private List<TbSubmenuRol> tbSubmenuRols;

	public TbMenu() {
	}

	public int getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public String getI18nMenu() {
		return this.i18nMenu;
	}

	public void setI18nMenu(String i18nMenu) {
		this.i18nMenu = i18nMenu;
	}

	public String getImagenurl() {
		return this.imagenurl;
	}

	public void setImagenurl(String imagenurl) {
		this.imagenurl = imagenurl;
	}

	public List<TbSubmenuRol> getTbSubmenuRols() {
		return this.tbSubmenuRols;
	}

	public void setTbSubmenuRols(List<TbSubmenuRol> tbSubmenuRols) {
		this.tbSubmenuRols = tbSubmenuRols;
	}

	public TbSubmenuRol addTbSubmenuRol(TbSubmenuRol tbSubmenuRol) {
		getTbSubmenuRols().add(tbSubmenuRol);
		tbSubmenuRol.setTbMenu(this);

		return tbSubmenuRol;
	}

	public TbSubmenuRol removeTbSubmenuRol(TbSubmenuRol tbSubmenuRol) {
		getTbSubmenuRols().remove(tbSubmenuRol);
		tbSubmenuRol.setTbMenu(null);

		return tbSubmenuRol;
	}

}