package pe.edu.system.jcmr.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tb_empleado database table.
 * 
 */
@Entity
@Table(name="tb_empleado")
@NamedQuery(name="TbEmpleado.findAll", query="SELECT t FROM TbEmpleado t")
public class TbEmpleado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int empleadoId;

	private String apellido;

	private String celular;

	private String clave;

	private String direccion;

	private int dni;

	private String email;

	@Temporal(TemporalType.DATE)
	private Date fechaNac;

	@Lob
	private byte[] foto;

	private String nombre;

	private String sexo;

	private String telefono;

	//bi-directional many-to-one association to TbRol
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idrol")
	private TbRol tbRol;

	//bi-directional many-to-one association to TbUbigeo
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ubigeoId")
	private TbUbigeo tbUbigeo;

	public TbEmpleado() {
	}

	public int getEmpleadoId() {
		return this.empleadoId;
	}

	public void setEmpleadoId(int empleadoId) {
		this.empleadoId = empleadoId;
	}

	public String getApellido() {
		return this.apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public int getDni() {
		return this.dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public byte[] getFoto() {
		return this.foto;
	}

	public void setFoto(byte[] foto) {
		this.foto = foto;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSexo() {
		return this.sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public TbRol getTbRol() {
		return this.tbRol;
	}

	public void setTbRol(TbRol tbRol) {
		this.tbRol = tbRol;
	}

	public TbUbigeo getTbUbigeo() {
		return this.tbUbigeo;
	}

	public void setTbUbigeo(TbUbigeo tbUbigeo) {
		this.tbUbigeo = tbUbigeo;
	}

}