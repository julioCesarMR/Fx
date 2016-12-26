package pe.edu.system.jcmr.entity;

import pe.edu.system.jcmr.utilCommon.ResourcesI18n;

public class BaseEntity extends ResourcesI18n {

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
		if (getResourceMessage(descripcion)!= null && getResourceMessage(descripcion).length()>0) {
			return getResourceMessage(descripcion);
		
		} else {
			System.out.println("roles"+getResourceMessage(descripcion));
			return descripcion;
			
		}

	}



}
