package pe.edu.system.jcmr.utilCommon;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
/**
 * 
 * @author Julio Cesar Meza Rios
 *
 */

public class DownloadFile {

	
	private String urlFile;
	private String typeFile;
	private String nameFile;
	private String directoryFile;
	private URL url=null;
	private URLConnection urlCon=null;
	protected final Log log = LogFactory.getLog(getClass());

     /**
     *  Metodo que permite descargar archivos desde una Url
     * @param urlFile : Url de la pagina .
     * @param typeFile : Extension del Archivo. No usar el "."
     * @param nameFile : nombre del Archivo
     * @param directoryFile : directory donde se guardara el archivo
     */
	public void downloadFile(String urlFile, String typeFile, String nameFile, String directoryFile){
		
		  log.info("Entering 'downloadFile' method");
		  this.urlFile=urlFile;
		  this.typeFile=typeFile;
		  this.nameFile=nameFile;
		  this.directoryFile=directoryFile;
	
		try {
			
			 url = new URL(urlFile);
			
			urlCon = url.openConnection();

			InputStream file = urlCon.getInputStream();
			log.info(urlCon.getContentLength()/1024);
			
			FileOutputStream directory = new FileOutputStream(directoryFile+"/"+nameFile+"."+typeFile);

			byte[] temp = new byte[1024];
			int leido = file.read(temp);
			while (leido > 0){
				
				directory.write(temp, 0, leido);
				leido = file.read(temp);
			}

			file.close();
			directory.close();
			log.info("Download Succesfully "+nameFile+"."+typeFile);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			
		}
	}


	public String getUrlFile() {
		return urlFile;
	}


	public void setUrlFile(String urlFile) {
		this.urlFile = urlFile;
	}


	public String getTypeFile() {
		return typeFile;
	}


	public void setTypeFile(String typeFile) {
		this.typeFile = typeFile;
	}


	public String getNameFile() {
		return nameFile;
	}


	public void setNameFile(String nameFile) {
		this.nameFile = nameFile;
	}


	public String getDirectoryFile() {
		return directoryFile;
	}


	public void setDirectoryFile(String directoryFile) {
		this.directoryFile = directoryFile;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(URL url) {
		this.url = url;
	}
	public URLConnection getUrlCon() {
		return urlCon;
	}
	public void setUrlCon(URLConnection urlCon) {
		this.urlCon = urlCon;
	}
	
	
}
