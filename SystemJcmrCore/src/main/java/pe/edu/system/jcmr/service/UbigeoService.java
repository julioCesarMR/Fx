package pe.edu.system.jcmr.service;

import java.util.List;

import pe.edu.system.jcmr.entity.BaseEntity;

public interface UbigeoService {

	List<BaseEntity> getDepartamentos();
	
	List<BaseEntity> getProvincias(String departamento);
	
	List<BaseEntity> getDistritos(String departamento , String provincincia);
	
	Integer getIdUbigeo(String departamento, String provincia, String distrito);
}
