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
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Point3D;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;
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

public class SplashController extends UtilidadesFx implements Initializable{

	


    @FXML
    private ImageView imgLogo;
    @FXML
    private ImageView imgLogo2;
    @FXML
    private StackPane stackMain;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		start();
	 start2();
	}

	public void start() {

		FadeTransition tt = 
				    new FadeTransition(Duration.seconds(16), stackMain);
//         tt.setFromAngle(0);
//         tt.setToAngle(360);
//         tt.setAutoReverse(true);
//         tt.setCycleCount(4);
//         tt.setInterpolator(Interpolator.);
//         tt.setAxis( Rotate.X_AXIS );
		  
	    tt.setAutoReverse(true);
		tt.setCycleCount(3);
		tt.setFromValue(1.0);
        tt.setToValue(0.0);		
        
        RotateTransition rr = new RotateTransition(Duration.seconds(30), imgLogo);
        rr.setCycleCount(3);
        rr.setByAngle(360);
        
//		  rotator.setInterpolator(Interpolator.LINEAR);
//				    tt.setFromX( -(imgLogo.getFitWidth()) );
//				    tt.setToX( stackMain.getPrefWidth() );
//				    tt.setCycleCount( Timeline.INDEFINITE );
//				    tt.play();
				    rr.play();
	}

	public void start2() {

		FadeTransition tt = 
				    new FadeTransition(Duration.seconds(16), stackMain);
//         tt.setFromAngle(0);
//         tt.setToAngle(360);
//         tt.setAutoReverse(true);
//         tt.setCycleCount(4);
//         tt.setInterpolator(Interpolator.);
//         tt.setAxis( Rotate.X_AXIS );
		  
	    tt.setAutoReverse(true);
		tt.setCycleCount(3);
		tt.setFromValue(1.0);
        tt.setToValue(0.0);		
        
        RotateTransition rr = new RotateTransition(Duration.seconds(30), imgLogo2);
        rr.setCycleCount(3);
        rr.setByAngle(360);
        
//		  rotator.setInterpolator(Interpolator.LINEAR);
//				    tt.setFromX( -(imgLogo.getFitWidth()) );
//				    tt.setToX( stackMain.getPrefWidth() );
//				    tt.setCycleCount( Timeline.INDEFINITE );
//				    tt.play();
				    rr.play();
	}


}









    
 

	
	


