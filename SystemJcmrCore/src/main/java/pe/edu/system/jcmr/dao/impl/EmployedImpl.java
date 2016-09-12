package pe.edu.system.jcmr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.system.jcmr.dao.EmployedDao;
import pe.edu.system.jcmr.entity.TbEmpleado;

@Repository("employedDao")
public class EmployedImpl implements EmployedDao {

	@PersistenceContext
	private EntityManager em;



	


	public TbEmpleado getById(Integer id) {
		return null;
	}

   @Transactional
	public List<TbEmpleado> getEmpleados() {
	     Query q = em.createQuery("select e from TbEmpleado e");
	     return q.getResultList();
	}

	@Transactional
	public void insert(TbEmpleado object) {
		System.out.println("insertando");
		em.persist(object);
	

	}

	public void delete(TbEmpleado object) {
		// TODO Auto-generated method stub

	}

	public void update(TbEmpleado object) {
		// TODO Auto-generated method stub

	}


	public TbEmpleado authenticateUser(String user, String password) {
		TypedQuery<TbEmpleado> rs = em.createQuery("select e from TbEmpleado e where e.dni =:username and e.clave =:password",TbEmpleado.class);
		rs.setParameter("username", Integer.parseInt(user));
		rs.setParameter("password", password);
		return rs.getSingleResult();
	}

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}
}
