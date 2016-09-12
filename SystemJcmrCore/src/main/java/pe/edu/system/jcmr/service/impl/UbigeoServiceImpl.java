package pe.edu.system.jcmr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.system.jcmr.dao.UbigeoDao;
import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.service.UbigeoService;

@Service("ubigeoService")
public class UbigeoServiceImpl implements UbigeoService{

	@Autowired
	UbigeoDao ubigeoDao;
	
	
	

	public List<BaseEntity> getDepartamentos() {
		return ubigeoDao.getDepartamentos();
	}


	public List<BaseEntity> getProvincias(String departamento) {
		return ubigeoDao.getProvincias(departamento);
	}


	public List<BaseEntity> getDistritos(String departamento, String provincincia) {
		return ubigeoDao.getDistritos(departamento, provincincia);
	}


	public Integer getIdUbigeo(String departamento, String provincia, String distrito) {
		
		return ubigeoDao.getIdUbigeo(departamento, provincia, distrito);
	}

	
	
	public UbigeoDao getUbigeoDao() {
		return ubigeoDao;
	}

	public void setUbigeoDao(UbigeoDao ubigeoDao) {
		this.ubigeoDao = ubigeoDao;
	}

	

	
}
