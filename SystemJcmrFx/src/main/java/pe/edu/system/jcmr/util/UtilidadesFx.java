/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.util;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.utilCommon.ResourcesI18n;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;


/**
 *
 * @author Julio Cesar Meza Rios
 */

public  class UtilidadesFx extends ResourcesI18n  {

	protected transient final Log log = LogFactory.getLog(getClass());
	
	public void initializable(){
		
	}
	
	public ApplicationContext context;

	public UtilidadesFx() {
		
	   	 String CONFIG_PATH = "classpath:/META-INF/spring-context.xml";
         context = new ClassPathXmlApplicationContext(CONFIG_PATH);
	}
    //Spring Core
    public Object getBean(String service){
         return context.getBean(service);
    }
    
    
    /**
     * 
     * @param pane
     * @param tabPane
     */
    public void OpenPane(Tab pane, TabPane tabPane) {
        if (!tabPane.getTabs().contains(pane)) {
            tabPane.getTabs().add(pane);
        }
    }
	/**
	 * 
	 * @param pane
	 * @param tabPane
	 */
    public void ClosePane(Tab pane, TabPane tabPane) {

        if (tabPane.getTabs().contains(pane)) {
            tabPane.getTabs().remove(pane);
        }
    }
	



	public static LocalDate converterDateToLocalDate(Date date) {

		Instant instant = Instant.ofEpochMilli(date.getTime());
		LocalDate localDate = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
		return localDate;
	}

    public BaseEntity getValueBaseEntity(String value){
		BaseEntity baseEntity = new BaseEntity();
		baseEntity.setDescripcion(value);
		return baseEntity;
	}


    @Override
    public String getResourceMessage(String key) {
		String lenguage =(String)SessionJCMR.getInstance().getContextObject("Lenguage");
    	setLenguage(lenguage);
    	return super.getResourceMessage(key);
    }
	

	public void menssajeValidation(String title,String content){
		TrayNotification tray = new TrayNotification("Mensaje Alerta", content, NotificationType.ERROR);
		tray.setAnimationType(AnimationType.POPUP);
		tray.showAndDismiss(javafx.util.Duration.seconds(1));
        tray.showAndWait();
	}
	public void messageError(String message){
		TrayNotification tray = new TrayNotification(getResourceMessage("mensaje.notification.tile.default"), message, NotificationType.ERROR);
		tray.setAnimationType(AnimationType.POPUP);
		tray.setRectangleFill(Paint.valueOf("#FF0000"));
		tray.setImage(new Image("/META-INF/component/notificationIcon/error.png"));
		tray.showAndDismiss(javafx.util.Duration.seconds(1));
        tray.showAndWait();
	}
	
	public void messageSuccess(String message){
		TrayNotification tray = new TrayNotification(getResourceMessage("mensaje.notification.tile"), message, NotificationType.SUCCESS);
		tray.setAnimationType(AnimationType.POPUP);
		tray.setRectangleFill(Paint.valueOf("#A6D785"));
		tray.setImage(new Image("/META-INF/component/notificationIcon/success.png"));
		tray.showAndDismiss(javafx.util.Duration.seconds(1));
        tray.showAndWait();
	}
	public void messageWarning(String message){
		TrayNotification tray = new TrayNotification(getResourceMessage("mensaje.notification.tile.default"), message, NotificationType.WARNING);
		tray.setAnimationType(AnimationType.POPUP);
		tray.showAndDismiss(javafx.util.Duration.seconds(1));
        tray.showAndWait();
	}
	
	
	public void closeWindow(Node node) {

		Stage stage = (Stage) node.getScene().getWindow();
		stage.close();
	}

	public void closeWindow(ActionEvent event) {

		Node source = (Node) event.getSource();
		Stage stage = (Stage) source.getScene().getWindow();
		stage.close();
		
		
	}

//	public void openWindow(String fxml) {
//		try {
//			Stage st = new Stage();
//			Locale currentLocale = new Locale("en_US");
//			ResourceBundle rb = ResourceBundle.getBundle("in18/systemJCMR", currentLocale);
//			AnchorPane root;
//			root = FXMLLoader.load(getClass().getResource(fxml), rb);
//			root.setPrefHeight(615);
//			root.setPrefWidth(1031);
//			Scene scena = new Scene(root);
//			st.setScene(scena);
//			st.show();
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//	}
//	

//	public void openWindow1(String fxml) {
//		try {
//			Stage st = new Stage();
//			Locale currentLocale = new Locale("en_US");
//			ResourceBundle rb = ResourceBundle.getBundle("in18/systemJCMR", currentLocale);
//            StackPane root;
//			root = FXMLLoader.load(getClass().getResource(fxml), rb);
//			root.setPrefHeight(615);
//			root.setPrefWidth(1031);
//			Scene scena = new Scene(root);
//			st.setScene(scena);
//			st.show();
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//	}
//	public void openWindow2(String fxml,Stage stage) {
//		try {
//	        StackPane secene= (StackPane)stage.getScene().getRoot();
//			Locale currentLocale = new Locale("en_US");
//			ResourceBundle rb = ResourceBundle.getBundle("in18/systemJCMR", currentLocale);
//			StackPane root;
//            root = FXMLLoader.load(getClass().getResource(fxml), rb);
//
//            secene.getChildren().addAll(root);
//
//
//		} catch (IOException e) {
//
//			e.printStackTrace();
//		}
//	}
	

	public void openWindow(String fxml) {
		try {
			Stage st = new Stage();
		    
			String lenguage =(String)SessionJCMR.getInstance().getContextObject("Lenguage");
			log.debug("Open window , idioma :"+lenguage);
		    Locale currentLocale = new Locale(lenguage);
		    
			ResourceBundle rb = ResourceBundle.getBundle("in18/systemJCMR", currentLocale);
			Parent root;
			root = FXMLLoader.load(getClass().getResource(fxml), rb);
			Scene scena = new Scene(root);
			st.setScene(scena);
			st.show();
		} catch (IOException e) {

			e.printStackTrace();
		}
	
	}

	
	
	
//	public void openWindow(String fxml, Double height, Double width) {
//		try {
//			Stage st = new Stage();
//			Locale currentLocale = new Locale("en_US");
//			ResourceBundle rb = ResourceBundle.getBundle("in18/systemJCMR", currentLocale);
//			AnchorPane root;
//			root = FXMLLoader.load(getClass().getResource(fxml), rb);
//			root.setPrefHeight(height);
//			root.setPrefWidth(width);
//			Scene scena = new Scene(root);
//			st.setScene(scena);
//			st.show();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}	
	
	

	
	

	

}
