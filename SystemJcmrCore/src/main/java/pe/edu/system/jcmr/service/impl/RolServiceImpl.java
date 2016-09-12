package pe.edu.system.jcmr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.system.jcmr.dao.RolDao;
import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbMenu;
import pe.edu.system.jcmr.entity.TbRol;
import pe.edu.system.jcmr.entity.TbSubmenu;
import pe.edu.system.jcmr.entity.TbSubmenuRol;
import pe.edu.system.jcmr.service.RolService;

@Service("rolService")
public class RolServiceImpl implements RolService {

	@Autowired
	private RolDao rolDao;

	public List<TbRol> getRoles() {
		return rolDao.getRoles();
	}

	public RolDao getRolDao() {
		return rolDao;
	}

	public void setRolDao(RolDao rolDao) {
		this.rolDao = rolDao;
	}

	public List<TbRol> userXrol(Integer rol) {

		return rolDao.userXrol(rol);
	}

	public List<TbMenu> rolXmenu(Integer rol) {
		return rolDao.rolXmenu(rol);
	}

	@Override
	public List<TbSubmenuRol> getMenusItemRol(Integer rol, Integer idmenu) {

		return rolDao.getMenusItemRol(rol, idmenu);
	}

	@Override
	public List<TbMenu> getMenus() {

		return rolDao.getMenus();
	}

	@Override
	public void insertRol(TbRol rol) {
		rolDao.insertRol(rol);

	}

	@Override
	public void deleteRol(Integer id) {
		rolDao.deleteRol(id);
	}

	@Override
	public void updateRol(TbRol rol) {
		rolDao.updateRol(rol);

	}

	@Override
	public void insertMenu(TbMenu menu) {
		rolDao.insertMenu(menu);

	}

	@Override
	public void deleteMenu(Integer id) {
		rolDao.deleteMenu(id);

	}

	@Override
	public void updateMenu(TbMenu menu) {
		rolDao.updateMenu(menu);

	}

	@Override
	public void insertSubMenu(TbSubmenu subMenu) {
		rolDao.insertSubMenu(subMenu);

	}

	@Override
	public void deleteSubMenu(Integer id) {
		rolDao.deleteSubMenu(id);

	}

	@Override
	public void updateSubMenu(TbSubmenu subMenu) {
		rolDao.updateSubMenu(subMenu);

	}

	@Override
	public List<BaseEntity> getBaseRoles() {

		return rolDao.getBaseRoles();
	}

	@Override
	public List<TbSubmenu> getSubMenu() {
		return rolDao.getSubMenu();
	}

}
