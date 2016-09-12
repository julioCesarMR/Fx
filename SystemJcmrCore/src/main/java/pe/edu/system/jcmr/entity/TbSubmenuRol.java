package pe.edu.system.jcmr.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tb_submenu_rol database table.
 * 
 */
@Entity
@Table(name="tb_submenu_rol")
@NamedQuery(name="TbSubmenuRol.findAll", query="SELECT t FROM TbSubmenuRol t")
public class TbSubmenuRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="idsubmenus_rol")
	private int idsubmenusRol;

	//bi-directional many-to-one association to TbRol
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idrol")
	private TbRol tbRol;

	//bi-directional many-to-one association to TbMenu
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idmenu")
	private TbMenu tbMenu;

	//bi-directional many-to-one association to TbSubmenu
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idsubmenu")
	private TbSubmenu tbSubmenu;

	public TbSubmenuRol() {
	}

	public int getIdsubmenusRol() {
		return this.idsubmenusRol;
	}

	public void setIdsubmenusRol(int idsubmenusRol) {
		this.idsubmenusRol = idsubmenusRol;
	}

	public TbRol getTbRol() {
		return this.tbRol;
	}

	public void setTbRol(TbRol tbRol) {
		this.tbRol = tbRol;
	}

	public TbMenu getTbMenu() {
		return this.tbMenu;
	}

	public void setTbMenu(TbMenu tbMenu) {
		this.tbMenu = tbMenu;
	}

	public TbSubmenu getTbSubmenu() {
		return this.tbSubmenu;
	}

	public void setTbSubmenu(TbSubmenu tbSubmenu) {
		this.tbSubmenu = tbSubmenu;
	}

}