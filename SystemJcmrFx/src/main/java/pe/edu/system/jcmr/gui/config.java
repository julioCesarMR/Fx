package pe.edu.system.jcmr.gui;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class config {
    private ApplicationContext applicationContext;
    private static config provider;

    public config() throws ExceptionInInitializerError {
        try {
            this.applicationContext = new ClassPathXmlApplicationContext("classpath:/META-INF/spring-context.xml");
        } catch (BeansException ex) {
            System.err.print("error " + ex);
        }
    }

    public synchronized static config getInstance() throws ExceptionInInitializerError {
        config tempProvider;
        if (provider == null) {
            provider = new config();
            tempProvider = provider;
        }else if(provider.getApplicationContext()==null){
            provider=new config();
            tempProvider=provider;
        }else{
            tempProvider=provider;
        }

        return tempProvider;
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}