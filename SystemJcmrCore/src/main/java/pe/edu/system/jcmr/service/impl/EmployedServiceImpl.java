package pe.edu.system.jcmr.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.system.jcmr.dao.EmployedDao;
import pe.edu.system.jcmr.entity.TbEmpleado;
import pe.edu.system.jcmr.service.EmployedService;



@Service("employedService")
public class EmployedServiceImpl  implements EmployedService{

	@Autowired
    private EmployedDao employedDao;
	
	
	
	public List<TbEmpleado> getEmpleados() {

           return employedDao.getEmpleados();
	}

	public TbEmpleado authenticateUser(String user, String password) {
		return employedDao.authenticateUser(user, password);
	}

	public EmployedDao getEmployedDao() {
		return employedDao;
	}
	public void setEmployedDao(EmployedDao employedDao) {
		this.employedDao = employedDao;
	}


	public boolean insert(TbEmpleado employee) {
		System.out.println(employee.toString());
	   employedDao.insert(employee);
		return false;
	}
}
