package pe.edu.system.jcmr.utilCommon;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ResourcesI18n {
	protected transient final Log log = LogFactory.getLog(getClass());
	/* METODOS PARA OBTENER DATOS DEL ARCHIVO DE RECURSOS */
	private  static final String ARCHIVO_APPLICATION = "in18.systemJCMR";
	private  static final String ARCHIVO_RESOURCE = "in18.resources";
	private  static final String ARCHIVO_MENU_RESOURCE = "in18.menu";
	private  String lenguage;
	
	public String getResourceMessage(String key) {
		if (log.isDebugEnabled()) {
			log.debug("Entering 'getResourceMessage' method , idioma :"+ getLenguage() );
		}
		if (StringUtils.isBlank(getLenguage())) {
		     lenguage="en_US";
		}
		Locale currentLocale = new Locale(getLenguage());
		
	

		try {
			ResourceBundle aplication = ResourceBundle.getBundle(ARCHIVO_APPLICATION, currentLocale);
			String mensaje = (String) aplication.getObject(key);

			return mensaje;

		} catch (Exception e) {

			try {
				ResourceBundle resource = ResourceBundle.getBundle(ARCHIVO_RESOURCE, currentLocale);
				String mensajeEx = (String) resource.getObject(key);
				return mensajeEx;
			} catch (Exception e2) {
				try {
					ResourceBundle menu = ResourceBundle.getBundle(ARCHIVO_MENU_RESOURCE, currentLocale);
					String mensajeMenu = (String) menu.getObject(key);
					return mensajeMenu;	
					
				} catch (Exception e3) {
					log.info(e3.getMessage());
					return "";
				
				}
				
			}

		}

	
	}
	public String getResourceMessage(String key, Object[] args) {
		String iString = null;
		String value = getResourceMessage(key);

		try {
			Object nonNullArgs[] = args;
	        for (int i=0; i<args.length; i++) {
	        	if (args[i] == null) {
	        		if (nonNullArgs==args) nonNullArgs=(Object[])args.clone();
	        			nonNullArgs[i] = "null";
				}
		    }
	        
	        iString = MessageFormat.format(value, nonNullArgs);
		} 
		catch (IllegalArgumentException iae) {
		    StringBuffer buf = new StringBuffer();
		    buf.append(value);
		    
		    for (int i = 0; i < args.length; i++) {
		    	buf.append(" arg[" + i + "]=" + args[i]);
		    }
		    
		    iString = buf.toString();
		}
		return iString;
	}
	public String getLenguage() {
		return lenguage;
	}
	public void setLenguage(String lenguage) {
		this.lenguage = lenguage;
	}
}
