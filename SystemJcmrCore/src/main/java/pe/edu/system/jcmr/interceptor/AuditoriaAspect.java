/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.interceptor;

import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


/**
 *
 * @author francisco
 */
@Aspect
@Component("auditoriaAspect")
public class AuditoriaAspect {
//
//  @Autowired
//    AuditoriaService service;


//    @After("execution(* pe.edu.cibertec.dao.FrecuenciaDao.*(pe.edu.cibertec.entity.Frecuencia)) && args(frecuencia)")
//    public void afterDeleteAutorInterceptor(JoinPoint joinPoint, Frecuencia frecuencia) {
//        Auditoria auditoria = new Auditoria();
//        auditoria.setFecha(new Date());
//        auditoria.setValor("Accion: " + joinPoint.getSignature().getName() + ", Valores: " + frecuencia.toString());
//        service.insert(auditoria);
//    }
}
