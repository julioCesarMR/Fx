package pe.edu.system.jcmr.utilCommon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import pe.edu.system.jcmr.entityCommon.FTPBean;

public class FTPUtil {
	
	protected final Log log = LogFactory.getLog(getClass());

	FTPClient ftpClient=null;
	
	
	public boolean loguearFTP(FTPBean ftpBean) {
		if (log.isDebugEnabled())
			log.debug("Entering 'loguearFTPActivo' method");
		try {

			ftpClient = new FTPClient();

			ftpClient.connect(ftpBean.getServidorFtp(), ftpBean.getPuertoFtp());

			ftpClient.login(ftpBean.getUsuarioFtp(), ftpBean.getPasswordFtp());

		} catch (Exception e) {

			e.printStackTrace();
		}
		log.info("Succesfully logged to " + ftpBean.getServidorFtp());
		return ftpClient.isConnected();
	}
	
	/**
	 * Permite loguarte al FTP si estás detrás de un cortafuegos (firewall) 
	 * @param ftpBean
	 * @return conection Succesfully
	 */
	public boolean loguearFTPPasivo(FTPBean ftpBean) {
		if (log.isDebugEnabled())
			log.debug("Entering 'loguearFTPPasivo' method");
		try {

			ftpClient = new FTPClient();

			ftpClient.connect(ftpBean.getServidorFtp(), ftpBean.getPuertoFtp());

			ftpClient.login(ftpBean.getUsuarioFtp(), ftpBean.getPasswordFtp());
			
			ftpClient.enterLocalPassiveMode();

		} catch (Exception e) {

			e.printStackTrace();
		}
		log.info("Succesfully logged to " + ftpBean.getServidorFtp());
		return ftpClient.isConnected();
	}
	/**
	 * Elimina el  Archivo guardado en el FTP
	 * @param directory  Directorio en el FTP donde se encuentra unbicado el archivo
	 * @param file   nombre del Archivo a eliminar
	 */
	public void deleteFile(String directoryFTP, String nameFileFTP){
		if (log.isDebugEnabled())
			log.debug("Entering 'deleteFile FTP' method");
		try {

			if (!ftpClient.isConnected()) {
				log.error("Error en la conexion FTP ");
                return;
			}

			if (!ftpClient.deleteFile(nameFileFTP)) {
				
				throw new RuntimeException();
			}

			// Valida que se haya podido eliminar el archivo
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				throw new RuntimeException();
			}
			log.info("Succesfully delete to " + nameFileFTP);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public void uploadFileFTP(String directoryLocal){
		
	        try {

	        	if(!ftpClient.isConnected()){
	        		
	        	}
	 
	            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

	            
	            File localFile = new File(directoryLocal);
	 
	            String firstRemoteFile = "Projects.zip";
	          
	            InputStream inputStream = new FileInputStream(localFile);
	 
	            log.info("Start uploading");
	           
	            ftpClient.storeFile(firstRemoteFile, inputStream);
	            inputStream.close();

	            
	            log.info("The  file is uploaded successfully.");
	 
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }

	}


   public void closeConexFTP(){
	   try {
           if (ftpClient.isConnected()) {
               ftpClient.logout();
               ftpClient.disconnect();
           }
       } catch (IOException ex) {
           ex.printStackTrace();
       }
   }
	
}
