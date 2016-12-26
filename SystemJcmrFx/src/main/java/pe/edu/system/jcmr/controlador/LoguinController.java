/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.controlador;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.jfoenix.controls.JFXButton;

import herudi.animations.FadeInLeftTransition;
import herudi.animations.FadeInLeftTransition1;
import herudi.animations.FadeInRightTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbEmpleado;
import pe.edu.system.jcmr.service.EmployedService;
import pe.edu.system.jcmr.service.RolService;
import pe.edu.system.jcmr.util.SessionJCMR;
import pe.edu.system.jcmr.util.UtilidadesFx;
import pe.edu.system.jcmr.validation.ValidationJcmr;




/**
*
* @author Julio Cesar Meza Rios
*/

public class LoguinController extends UtilidadesFx implements Initializable,EventHandler<Event>{

	

	@FXML
	private JFXButton btnSingIn;
	@FXML
	private ComboBox<BaseEntity> cboIdioma;
	@FXML
	private Label lblClose;
	@FXML
	private TextField txtUsuario;
	@FXML
	private PasswordField txtPassword;
    @FXML
    private Label lblMensaje;
    @FXML
    private Text lblWelcome;
    @FXML 
    private Region rgBack;
    @FXML
    private ImageView viewLoader;


	EmployedService employedService =(EmployedService) getBean("employedService");
	RolService rolService =(RolService) getBean("rolService");



    private final  String MAIN_FXML ="/META-INF/negocio/main/fxml/main.fxml";
	@Override
	public void initialize(URL location, ResourceBundle resources) {

//        dialogFx.show(DialogTransition.RIGHT);
 
  
//	 	txtUsuario.textProperty().addListener(new ChangeListener<String>() {
//
//			@Override
//			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//				
//				if(newValue.contains(ConstansDomios.COM+" ") || newValue.contains(ConstansDomios.NET+" ") 
//						|| newValue.contains(ConstansDomios.BIZ+" ")|| newValue.contains(ConstansDomios.ORG+" ")
//						|| newValue.contains(ConstansDomios.EDU+" ") || newValue.contains(ConstansDomios.INFO+" ")
//						|| newValue.contains(ConstansDomios.MIL+" ") || newValue.contains(ConstansDomios.GOV+" ")){
//					txtUsuario.setText(oldValue+";");
//		
//				}
//				
//			}
//    		
//    	});


		closeLoguin();
		loadIdiomas();
		blockTextField();
		clearMessage();
		   Platform.runLater(() -> {
//	            new FadeInRightTransition(lblUserLogin).play();
	            new FadeInLeftTransition(lblWelcome).play();
//	            new FadeInLeftTransition1(lblPassword).play();
	            new FadeInLeftTransition1(cboIdioma).play();
	            new FadeInLeftTransition1(txtUsuario).play();
	            new FadeInLeftTransition1(txtPassword).play();
	            new FadeInRightTransition(btnSingIn).play();

	        });
	}

	public void blockTextField(){
		ValidationJcmr validacion = new ValidationJcmr();

		validacion.lengthCaracteres(txtUsuario, 8);
		validacion.lengthCaracteres(txtPassword, 10);
	}

	public void loadIdiomas(){
	   ObservableList<BaseEntity> list = FXCollections.observableArrayList(
			 new BaseEntity("0", "Select Lenguage"), 
             new BaseEntity("en_US", "Ingles"), 
             new BaseEntity("es_ES", "Español")
     );
  
     cboIdioma.getItems().addAll(list);
     cboIdioma.getSelectionModel().select(0);  
    
}
	public void closeLoguin(){
		
		lblClose.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {

				Platform.exit();
			}

		});
		
	}
	public void clearMessage(){
		cboIdioma.valueProperty().addListener( new ChangeListener<BaseEntity>() {

			@Override
			public void changed(ObservableValue<? extends BaseEntity> observable,BaseEntity oldValue, BaseEntity newValue) {
	                if(!newValue.getCodigo().equals("0")){
			            lblMensaje.setText("");      
		             }
				
			
			}
		});
		txtUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
		        if(newValue.length()>=0){
		        	lblMensaje.setText("");
		        }
		});
		txtPassword.textProperty().addListener((observable, oldValue, newValue) -> {
	        if(newValue.length()>=0){
	        	lblMensaje.setText("");
	        }
	});

	}
	
	
    @FXML public void btnOnSingIn(ActionEvent event) throws IOException {
    	
	    if(StringUtils.isBlank(txtUsuario.getText())){
			lblMensaje.setText(getResourceMessage("required.campo.message",new Object[]{txtUsuario.getPromptText()}));
          return ;
		}
	    if(StringUtils.isBlank(txtPassword.getText())){
			lblMensaje.setText(getResourceMessage("required.campo.message",new Object[]{txtPassword.getPromptText()}));
			return;
	    }
	    if(cboIdioma.getValue().getCodigo().equals("0")){
			lblMensaje.setText(getResourceMessage("required.campo.message",new Object[]{cboIdioma.getPromptText()}));
			return;
	    }
		Service<TbEmpleado> service = new Service<TbEmpleado>() {
		@Override
		protected Task<TbEmpleado> createTask() {
			return new Task<TbEmpleado>() {
				@Override
				protected TbEmpleado call() throws Exception {

                    TbEmpleado employed = employedService.authenticateUser(txtUsuario.getText(),txtPassword.getText());
						
                     return employed;
					}
			};
		}
		
			@Override
			protected void succeeded() {
					SessionJCMR.getInstance().addContextObject("Usuario", getValue());
					SessionJCMR.getInstance().addContextObject("Lenguage", cboIdioma.getValue().getCodigo());
					closeWindow(btnSingIn);
					openWindow(MAIN_FXML);
			}

			@Override
			protected void failed() {
				lblMensaje.setText(getResourceMessage("errors.password.mismatch"));
			}

	};
	viewLoader.visibleProperty().bind(service.runningProperty());
	rgBack.visibleProperty().bind(service.runningProperty());
	service.start();
	
    	
}

	@Override
	public void handle(Event event) {
		
		System.out.println("xvx"+event);
		
	}



	

}









    
 

	
	


