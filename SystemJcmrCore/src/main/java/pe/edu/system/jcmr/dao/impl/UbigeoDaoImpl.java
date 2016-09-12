package pe.edu.system.jcmr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import pe.edu.system.jcmr.dao.UbigeoDao;
import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbUbigeo;

@SuppressWarnings("unchecked")
@Repository("UbigeoDao")
public class UbigeoDaoImpl  implements UbigeoDao{

	 @PersistenceContext
     private EntityManager em;


		public TbUbigeo getById(Integer id) {
			// TODO Auto-generated method stub
			return null;
		}

	
		public List<TbUbigeo> list() {
			// TODO Auto-generated method stub
			return null;
		}


		public void insert(TbUbigeo object) {
			// TODO Auto-generated method stub
			
		}

	
		public void delete(TbUbigeo object) {
			// TODO Auto-generated method stub
			
		}

	
		public void update(TbUbigeo object) {
			// TODO Auto-generated method stub
			
		}
	 
	
		public Integer getIdUbigeo(String departamento, String provincia, String distrito) {
			Query q = em.createQuery("select u.ubigeoId from TbUbigeo u where u.departamento =:departamento and u.provincia=:provincia and u.distrito =:distrito",Integer.class);
		    q.setParameter("departamento", departamento); 
			q.setParameter("provincia", provincia); 
			q.setParameter("distrito", distrito); 
			return  (Integer) q.getSingleResult();
		}
		
		
		public List<BaseEntity> getDepartamentos() {
			  Query q = em.createQuery("select DISTINCT new pe.edu.system.jcmr.entity.BaseEntity(u.departamento, u.departamento)  from TbUbigeo u",BaseEntity.class);
		      return q.getResultList();
		}



		public List<BaseEntity> getProvincias(String departamento) {
			 Query q = em.createQuery("select DISTINCT new pe.edu.system.jcmr.entity.BaseEntity(u.departamento, u.provincia)  from TbUbigeo u where u.departamento=:departamento",BaseEntity.class);
		     q.setParameter("departamento", departamento); 
			 return q.getResultList();
		}

		
		
		public List<BaseEntity> getDistritos(String departamento, String provincia) {
			Query q = em.createQuery("select DISTINCT new pe.edu.system.jcmr.entity.BaseEntity(u.provincia, u.distrito)  from TbUbigeo u where u.departamento =:departamento and u.provincia=:provincia",BaseEntity.class);
		    q.setParameter("departamento", departamento); 
			q.setParameter("provincia", provincia); 
			return q.getResultList();
		}

		public EntityManager getEm() {
			return em;
		}

		public void setEm(EntityManager em) {
			this.em = em;
		}

	

}
