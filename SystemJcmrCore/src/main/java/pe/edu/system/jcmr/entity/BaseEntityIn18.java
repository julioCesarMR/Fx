package pe.edu.system.jcmr.entity;

public class BaseEntityIn18 {

	private String codigo;
	private String descripcion;

	
	public BaseEntityIn18(String codigo, String descripcion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
	}
	public BaseEntityIn18(Integer codigo, String descripcion) {

		this.codigo =String.valueOf(codigo);
		this.descripcion = descripcion;
	}
	
	public BaseEntityIn18() {
	
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
