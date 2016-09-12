package pe.edu.system.jcmr.dao;

import java.util.List;

import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbUbigeo;

public interface UbigeoDao extends BaseDao<TbUbigeo>{

	List<BaseEntity> getDepartamentos();
	
	List<BaseEntity> getProvincias(String departamento);
	
	List<BaseEntity> getDistritos(String departamento , String provincincia);
	
	Integer getIdUbigeo(String departamento,String provincia,String distrito);
	
}
