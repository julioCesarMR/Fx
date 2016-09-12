package pe.edu.system.jcmr.service;

import java.util.List;

import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbMenu;
import pe.edu.system.jcmr.entity.TbRol;
import pe.edu.system.jcmr.entity.TbSubmenu;
import pe.edu.system.jcmr.entity.TbSubmenuRol;

public interface RolService  {

	List<TbRol> getRoles();
	
	List<BaseEntity> getBaseRoles();

	List<TbRol> userXrol(Integer rol);

	List<TbMenu> rolXmenu(Integer rol);

	List<TbSubmenuRol> getMenusItemRol(Integer rol, Integer idmenu);



	List<TbMenu> getMenus();
	
	List<TbSubmenu> getSubMenu();

	void insertRol(TbRol rol);

	void deleteRol(Integer id);

	void updateRol(TbRol rol);

	void insertMenu(TbMenu menu);

	void deleteMenu(Integer id);

	void updateMenu(TbMenu menu);

	void insertSubMenu(TbSubmenu subMenu);

	void deleteSubMenu(Integer id);

	void updateSubMenu(TbSubmenu subMenu);

}
