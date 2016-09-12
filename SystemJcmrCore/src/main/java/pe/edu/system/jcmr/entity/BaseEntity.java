package pe.edu.system.jcmr.entity;

public class BaseEntity {

	private String codigo;
	private String descripcion;

	
	public BaseEntity(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public BaseEntity(Integer codigo, String descripcion) {

		this.codigo =String.valueOf(codigo);
		this.descripcion = descripcion;
	}
	
	public BaseEntity() {
	
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public String toString() {
		return descripcion;
	}



}
