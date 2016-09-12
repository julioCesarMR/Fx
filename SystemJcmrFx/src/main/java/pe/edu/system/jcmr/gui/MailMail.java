package pe.edu.system.jcmr.gui;

import java.io.StringWriter;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component("mailMail")
public  class MailMail {

	@Autowired
	private JavaMailSender mailSender;
	@Autowired
	private VelocityEngine velocityEngine;
	protected final Log log = LogFactory.getLog(getClass());


	public void sendMail(Map<String, Object> mailParams)  {
		try {
		 
			MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper;
			
			helper = new MimeMessageHelper(message, true, "UTF-8");
			
			log.info("Iniciando procesamiento de Envio de Correo");
	    	
			helper.setTo((String)mailParams.get("To").toString());
	    	helper.setFrom((String)mailParams.get("From").toString());
	    	helper.setCc((String)mailParams.get("Cc").toString());

	    	  
	        VelocityContext velocityContext = new VelocityContext((Map<?, ?>) mailParams.get("model"));
	    
	        StringWriter resultWriter = new StringWriter();
	     
	        velocityEngine.mergeTemplate((String)mailParams.get("template").toString(), "UTF-8", velocityContext, resultWriter);  
	    	

	        helper.setText(resultWriter.toString(),true);
	    	
	        mailSender.send(message);
	    	log.info("Finalizando el procesamiento de Envio de Correo");
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	

	public JavaMailSender getMailSender() {
		return mailSender;
	}

	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	public VelocityEngine getVelocityEngine() {
		return velocityEngine;
	}

	public void setVelocityEngine(VelocityEngine velocityEngine) {
		this.velocityEngine = velocityEngine;
	}

}