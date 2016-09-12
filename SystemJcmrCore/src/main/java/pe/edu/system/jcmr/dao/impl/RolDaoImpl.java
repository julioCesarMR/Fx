package pe.edu.system.jcmr.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.system.jcmr.dao.RolDao;
import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbMenu;
import pe.edu.system.jcmr.entity.TbRol;
import pe.edu.system.jcmr.entity.TbSubmenu;
import pe.edu.system.jcmr.entity.TbSubmenuRol;

@Repository("rolDao")

public class RolDaoImpl  implements RolDao{

	 @PersistenceContext
     private EntityManager em;


	public TbRol getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}





	public List<BaseEntity> getBaseRoles() {

		TypedQuery<BaseEntity> q = em.createQuery(
				"select new pe.edu.system.jcmr.entity.BaseEntity(r.idrol,r.i18nRol)  from TbRol r", BaseEntity.class);
		return q.getResultList();

	}



	public List<TbRol> userXrol(Integer rol) {
		TypedQuery<TbRol> q= em.createQuery("SELECT r FROM TbRol r WHERE r.idrol =:idrol",TbRol.class);
		q.setParameter("idrol", rol);
		return q.getResultList();
	}

	
	public List<TbMenu> rolXmenu(Integer rol) {
		TypedQuery<TbMenu>   q = em.createQuery("SELECT DISTINCT( men) FROM TbMenu men inner join men.tbSubmenuRols sub join sub.tbRol rol WHERE rol.idrol =:idrol order by men.idMenu asc",TbMenu.class);

//		TypedQuery<TbMenu>  q = em.createQuery("SELECT m FROM TbMenu m join m.tbMenuHasTbRols u join u.tbRol r WHERE r.idrol =:idrol",TbMenu.class);
		q.setParameter("idrol", rol);
		return q.getResultList();
	}



//	public List<TbMenuitem> getMenusItemRol(Integer rol,Integer idmenu) {
//	
//		TypedQuery<TbMenuitem> q = em.createQuery("SELECT i FROM TbMenuitem i join i.tbMenu m join m.tbMenuHasTbRols mr where mr.tbRol.idrol =:idrol and m.idMenu =:idMenu ",TbMenuitem.class);
//		q.setParameter("idrol", rol);
//		q.setParameter("idMenu", idmenu);
//		return q.getResultList();
//    
//	}



	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}



	@Override
	public List<TbSubmenuRol> getMenusItemRol(Integer rol, Integer idmenu) {

		
		TypedQuery<TbSubmenuRol> q = em.createQuery(""
				+ "SELECT subMen FROM TbSubmenuRol subMen "
				+ " where  subMen.tbRol.idrol =:idrol and subMen.tbMenu.idMenu =:idMenu order by subMen.tbMenu.idMenu asc",TbSubmenuRol.class);

//		TypedQuery<TbSubmenuRol> q = em.createQuery("SELECT subMen FROM TbSubmenuRol subMen join subMen.tbMenu men join subMen.tbRol rol where rol.idrol =:idrol and men.idMenu =:idMenu ",TbSubmenuRol.class);
        q.setParameter("idrol", rol);
		q.setParameter("idMenu", idmenu);
		return q.getResultList();
	}

	@Transactional
	@Override
	public List<TbMenu> getMenus() {
		TypedQuery<TbMenu> q = em.createQuery("select menu from TbMenu menu", TbMenu.class);
		return q.getResultList();

	}



	@Override
	public List<TbRol> getRoles() {
		  TypedQuery<TbRol> q = em.createQuery("select rol from TbRol rol",TbRol.class);
	      return q.getResultList();
	}

	@Override
	public void insertRol(TbRol rol) {

		em.persist(rol);		
	}

	@Override
	public void deleteRol(Integer id) {
		TbRol rol = em.find(TbRol.class, id);

		em.remove(rol);

	}

	@Override
	public void updateRol(TbRol rol) {
	       
		em.merge(rol);
	}



	@Override
	public void insertMenu(TbMenu menu) {
		em.persist(menu);
		
	}



	@Override
	public void deleteMenu(Integer id) {
		TbMenu menu = em.find(TbMenu.class, id);
		em.remove(menu);
	}


	@Override
	public void updateMenu(TbMenu menu) {
		em.merge(menu);
	}

	@Override
	public void insertSubMenu(TbSubmenu subMenu) {
		em.persist(subMenu);
		
	}



	@Override
	public void deleteSubMenu(Integer id) {
         TbSubmenu subMenu = em.find(TbSubmenu.class, id);
         em.remove(subMenu);
	}


	@Override
	public void updateSubMenu(TbSubmenu subMenu) {
		em.merge(subMenu);
		
	}

	@Override
	public List<TbSubmenu> getSubMenu() {
		  TypedQuery<TbSubmenu> q = em.createQuery("select subMenu from TbSubmenu subMenu",TbSubmenu.class);
	      return q.getResultList();
	}











}
