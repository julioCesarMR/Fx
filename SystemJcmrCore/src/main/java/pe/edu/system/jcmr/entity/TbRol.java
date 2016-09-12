package pe.edu.system.jcmr.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the tb_rol database table.
 * 
 */
@Entity
@Table(name="tb_rol")
@NamedQuery(name="TbRol.findAll", query="SELECT t FROM TbRol t")
public class TbRol implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int idrol;

	private String i18nRol;

	//bi-directional many-to-one association to TbEmpleado
	@OneToMany(mappedBy="tbRol")
	private List<TbEmpleado> tbEmpleados;

	//bi-directional many-to-one association to TbSubmenuRol
	@OneToMany(mappedBy="tbRol")
	private List<TbSubmenuRol> tbSubmenuRols;

	public TbRol() {
	}

	public int getIdrol() {
		return this.idrol;
	}

	public void setIdrol(int idrol) {
		this.idrol = idrol;
	}

	public String getI18nRol() {
		return this.i18nRol;
	}

	public void setI18nRol(String i18nRol) {
		this.i18nRol = i18nRol;
	}

	public List<TbEmpleado> getTbEmpleados() {
		return this.tbEmpleados;
	}

	public void setTbEmpleados(List<TbEmpleado> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}

	public TbEmpleado addTbEmpleado(TbEmpleado tbEmpleado) {
		getTbEmpleados().add(tbEmpleado);
		tbEmpleado.setTbRol(this);

		return tbEmpleado;
	}

	public TbEmpleado removeTbEmpleado(TbEmpleado tbEmpleado) {
		getTbEmpleados().remove(tbEmpleado);
		tbEmpleado.setTbRol(null);

		return tbEmpleado;
	}

	public List<TbSubmenuRol> getTbSubmenuRols() {
		return this.tbSubmenuRols;
	}

	public void setTbSubmenuRols(List<TbSubmenuRol> tbSubmenuRols) {
		this.tbSubmenuRols = tbSubmenuRols;
	}

	public TbSubmenuRol addTbSubmenuRol(TbSubmenuRol tbSubmenuRol) {
		getTbSubmenuRols().add(tbSubmenuRol);
		tbSubmenuRol.setTbRol(this);

		return tbSubmenuRol;
	}

	public TbSubmenuRol removeTbSubmenuRol(TbSubmenuRol tbSubmenuRol) {
		getTbSubmenuRols().remove(tbSubmenuRol);
		tbSubmenuRol.setTbRol(null);

		return tbSubmenuRol;
	}

//	@Override
//	public String toString() {
//		UtilidadesFx i18n = new UtilidadesFx();
//
//		return i18n.getResourceMessage(i18nRol);
//	}

	
}