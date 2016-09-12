package pe.edu.system.jcmr.util;





import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.eclipse.persistence.exceptions.ValidationException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.TransactionSystemException;
import org.apache.log4j.Logger;

@Component
@Aspect
public class SecurityAspect {
	  	

	
	final  Logger log = Logger.getLogger(getClass());
//	@Before("execution(* com.journaldev.spring.service.*.get*())")
//	public void getAllAdvice(){
//		System.out.println("Service method getter called");
//	}

	//Ejecuta antes de cualquier metodo que inicia con insert	
	@Before("execution(* pe.edu.system.jcmr.service.*.insert*(..)) &&  args(objecto)")
	public void logBeforeInsertAuditAdvice(JoinPoint joinPoint,Object objecto) {
		log.debug("***Ocurrio un excepcion al insertar el objeto***");
		log.debug("Method : "+joinPoint.getSignature().getName());
		log.debug("Method : "+objecto);
		
	}


	
	 @AfterThrowing(pointcut = "execution(* *.insert*(..))", throwing= "error")
	 public void logAfterThrowingInsert(JoinPoint joinPoint, Throwable error) {			 
		 log.debug("***Insert Error***");
		 log.debug("Error : "+error.getMessage());
	 }

	 @AfterThrowing(pointcut = "execution(* *.update*(..))",
			 throwing= "error")
	 public void logAfterThrowingUpdate(JoinPoint joinPoint, Throwable error) {			 
		 log.debug("***Ocurrio un excepcion al actualizar el objeto***");
		 log.debug("Error : "+error.getMessage());
	 }
	 
	 @AfterThrowing(pointcut = "execution(* *.delete*(..))",
			 throwing= "error")
	 public void logAfterThrowingDelete(JoinPoint joinPoint, Throwable error) {			 
		 log.debug("***Ocurrio un excepcion al eliminar el objeto***");
		 log.debug("Error : "+error.getMessage());
	 }
	 
	 
	 @AfterThrowing(pointcut = "execution(* *.get*(..))",
			 throwing= "error")
	 public void logAfterThrowingGet(JoinPoint joinPoint, Throwable error) {			 
		log.debug("***Ocurrio un excepcion al obtener el objeto***");
		log.debug("Method : " + joinPoint.getSignature().getName());
		log.debug("Error : " + error.getMessage());
	 }
	 

}

