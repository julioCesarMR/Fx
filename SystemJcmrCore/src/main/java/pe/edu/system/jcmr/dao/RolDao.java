package pe.edu.system.jcmr.dao;

import java.util.List;

import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbMenu;
import pe.edu.system.jcmr.entity.TbRol;
import pe.edu.system.jcmr.entity.TbSubmenu;
import pe.edu.system.jcmr.entity.TbSubmenuRol;



public interface RolDao   {


	List<TbRol> userXrol(Integer rol);

    List<TbMenu> rolXmenu(Integer rol);

    List<TbSubmenuRol> getMenusItemRol(Integer rol,Integer idmenu);

    List<BaseEntity> getBaseRoles();
    
    List< TbMenu> getMenus();

    List<TbRol> getRoles();

    void insertRol(TbRol rol);
    
    void deleteRol(Integer id);
    
    void updateRol(TbRol rol);
    
    void insertMenu(TbMenu menu);
    
    void deleteMenu(Integer id);
    
    void updateMenu(TbMenu menu);
    
    void insertSubMenu(TbSubmenu subMenu);
    
    void deleteSubMenu(Integer id);
    
    void updateSubMenu(TbSubmenu subMenu);
    
	List<TbSubmenu> getSubMenu();


}
