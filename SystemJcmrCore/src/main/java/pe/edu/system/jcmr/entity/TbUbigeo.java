package pe.edu.system.jcmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tb_ubigeo database table.
 * 
 */
@Entity
@Table(name="tb_ubigeo")
@NamedQuery(name="TbUbigeo.findAll", query="SELECT t FROM TbUbigeo t")
public class TbUbigeo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int ubigeoId;

	private String departamento;

	private String distrito;

	private String provincia;

	//bi-directional many-to-one association to TbEmpleado
	@OneToMany(mappedBy="tbUbigeo")
	private List<TbEmpleado> tbEmpleados;

	public TbUbigeo() {
	}

	public int getUbigeoId() {
		return this.ubigeoId;
	}

	public void setUbigeoId(int ubigeoId) {
		this.ubigeoId = ubigeoId;
	}

	public String getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getDistrito() {
		return this.distrito;
	}

	public void setDistrito(String distrito) {
		this.distrito = distrito;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public List<TbEmpleado> getTbEmpleados() {
		return this.tbEmpleados;
	}

	public void setTbEmpleados(List<TbEmpleado> tbEmpleados) {
		this.tbEmpleados = tbEmpleados;
	}

	public TbEmpleado addTbEmpleado(TbEmpleado tbEmpleado) {
		getTbEmpleados().add(tbEmpleado);
		tbEmpleado.setTbUbigeo(this);

		return tbEmpleado;
	}

	public TbEmpleado removeTbEmpleado(TbEmpleado tbEmpleado) {
		getTbEmpleados().remove(tbEmpleado);
		tbEmpleado.setTbUbigeo(null);

		return tbEmpleado;
	}

}