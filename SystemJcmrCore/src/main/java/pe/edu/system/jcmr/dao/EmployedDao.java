package pe.edu.system.jcmr.dao;

import java.util.List;

import pe.edu.system.jcmr.entity.TbEmpleado;

public interface EmployedDao extends BaseDao<TbEmpleado> {

	TbEmpleado authenticateUser(String user,String password);
	
	List<TbEmpleado> getEmpleados();

}
