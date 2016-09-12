package pe.edu.system.jcmr.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import pe.edu.system.jcmr.entity.TbEmpleado;
import pe.edu.system.jcmr.gui.Index;
import pe.edu.system.jcmr.util.ImgeUtilFx;
import pe.edu.system.jcmr.util.SessionJCMR;
import pe.edu.system.jcmr.util.UtilidadesFx;

/**
*
* @author Julio Cesar Meza Rios
*/
public class PoupPerfilController extends UtilidadesFx implements Initializable {

	
	@FXML public Label lblDatos;
	@FXML public Circle circleFoto;
	@FXML public Label lblRol;
    @FXML public Button btnSingOut;
	

    TbEmpleado employed =(TbEmpleado)SessionJCMR.getInstance().getContextObject("Usuario");
   
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		loadPoupSingOut();
	
		
	}

	@FXML public void btnOnSingOut(ActionEvent event){

		MainController.getInstance().closeMain();
		Stage stage = new Stage();
		Index log = new Index();
		try {
			log.start(stage);
		} catch (IOException e) {

			e.printStackTrace();
		}

		SessionJCMR.getInstance().clearContextObjects();
	}
	
	public void loadPoupSingOut(){
		  lblDatos.setText(employed.getNombre()+" "+employed.getApellido());
		  lblRol.setText(this.getResourceMessage(employed.getTbRol().getI18nRol()));
	
		if (employed.getFoto() == null && employed.getSexo().equals("M")) {

			circleFoto.setFill(new ImagePattern(new Image("/META-INF/negocio/main/img/perfil/man.png")));
		
		} else if (employed.getFoto() == null && employed.getSexo().equals("F")) {

			circleFoto.setFill(new ImagePattern(new Image("/META-INF/negocio/main/img/perfil/woman.png")));

		} else {
			circleFoto.setFill(new ImagePattern(ImgeUtilFx.convertByteToImage(employed.getFoto())));
		}
		

		
		  
	}




}
