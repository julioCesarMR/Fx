package pe.edu.system.jcmr.gui;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import pe.edu.system.jcmr.utilCommon.DownloadFile;

public class main {


	public static void main(String[] args) {
		

		System.out.println("error");
		         
		    	ApplicationContext context =  new ClassPathXmlApplicationContext("classpath:/META-INF/spring-context.xml");
		    	 
		    	MailMail mm = (MailMail) context.getBean("mailMail");
		    	DownloadFile x = new DownloadFile();
		    	Map<String, Object> model = new HashMap<>();
//		    	model.put("To", "juliocesarmezarios@gmail.com");
//		    	model.put("From","g3org3.18.94@gmail.com");
//		    	model.put("Cc","g3org3.18.94@gmail.com");
//		    	model.put("template","suggestPodcastNotificationMessage.vm");
		  
	    		  ArrayList list = new ArrayList();
	    	        Map map = new HashMap();
	    	        map.put("name", "Miguel");
	    	        map.put("price", "$100.00");
	    	        list.add( map );
	    	 
	    	        map = new HashMap();
	    	        map.put("name", "Arturo");
	    	        map.put("price", "$59.99");
	    	        list.add( map );

	    	        map = new HashMap();
	    	        map.put("name", "Kevin");
	    	        map.put("price", "$3.99");
	    	        list.add( map );
	    	        map.put("petList", list);
	  //  	        x.downloadFile("http://java.ciberaula.com/articulo/diseno_patrones_j2ee","html","foto","C:/Users/cmeza/Desktop");
		    	    model.put("model",map);
		 //   	    model.put("fileAttachment",x.getDirectoryFile()+"/"+x.getNameFile()+"."+x.getTypeFile());
		    //	    model.put("fileAttachment","C:/Users/cmeza/Desktop/button/camera.png");
					mm.sendMail(model);
			
		    
					
//		    	SimpleMailMessage message = new SimpleMailMessage();
//		    		
//		    		 message.setFrom("juliocesarmezarios@gmail.com");
//		    		 message.setTo("juliocesarmezarios@gmail.com");
//		    		 message.setSubject("TEST");
////		    	 Map map = new HashMap();
//		    		    ArrayList list = new ArrayList();
//		    	        Map map = new HashMap();
//		    	        map.put("name", "Cow");
//		    	        map.put("price", "$100.00");
//		    	        list.add( map );
//		    	 
//		    	        map = new HashMap();
//		    	        map.put("name", "Eagle");
//		    	        map.put("price", "$59.99");
//		    	        list.add( map );
//
//		    	        map = new HashMap();
//		    	        map.put("name", "Shark");
//		    	        map.put("price", "$3.99");
//		    	        list.add( map );
//		    	        model.put("petList", list);
////		    	//	 message.setText(msg);
//////		    		 mailSender.send(message);
//		    	mm.sendMessage(message, "suggestPodcastNotificationMessage.vm", model);
//		        mm.sendMail("juliocesarmezarios@gmail.com",
//		    		   "juliocesarmezarios@gmail.com",
//		    		   "Testing123", 
//		    		   "Testing only \n\n Hello Spring Email Sender");
////		        
		    }
		
//		DownloadFile x = new DownloadFile();
////
//		x.downloadFile("http://www2.sunat.gob.pe/padron_reducido_ruc.zip","html","foto","C:/Users/cmeza/Desktop");
//		
//		System.out.println("Probando"+x.getNameFile());
//
//		 FTPUtil x = new FTPUtil();
//		 FTPBean	b = new FTPBean();
//		 x.loguearFTP(b);
		 
	
}
