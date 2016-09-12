package pe.edu.system.jcmr.service;

import java.util.List;

import pe.edu.system.jcmr.entity.TbEmpleado;

public interface EmployedService {
	TbEmpleado authenticateUser(String user,String password);
	List<TbEmpleado> getEmpleados();
	boolean insert(TbEmpleado employee);
}
