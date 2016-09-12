package pe.edu.system.jcmr.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import org.controlsfx.control.PopOver;
import org.controlsfx.control.PopOver.ArrowLocation;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import pe.edu.system.jcmr.entity.TbEmpleado;
import pe.edu.system.jcmr.entity.TbMenu;
import pe.edu.system.jcmr.entity.TbSubmenuRol;
import pe.edu.system.jcmr.service.RolService;
import pe.edu.system.jcmr.util.ImgeUtilFx;
import pe.edu.system.jcmr.util.SessionJCMR;
import pe.edu.system.jcmr.util.UtilidadesFx;



/**
*
* @author Julio Cesar Meza Rios
*/
public class MainController extends UtilidadesFx implements Initializable {

	

    @FXML Accordion idAcordionMenu;
    @FXML Label lblNombre;
    @FXML Circle circleFoto;
    @FXML Circle circleFotoBar;
    @FXML ScrollPane scrollPaneMenu;
    @FXML Label lblNombreBar;
    @FXML AnchorPane anchPaneBarPerfil;
    @FXML AnchorPane anchBody;
    @FXML ScrollPane scrollbarBody;
    @FXML Text lblTitle;
    @FXML private Label lblOpciones;
    TbEmpleado employed =(TbEmpleado)SessionJCMR.getInstance().getContextObject("Usuario");
    
	PopOver popover =null;
   	private String lenguaje;
	private static MainController instance;

	public MainController() {
		instance = this;

	}

	public static MainController getInstance() {
		return instance;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
     	 
	   
		scrollPaneMenu.setHbarPolicy(ScrollBarPolicy.NEVER);
    	createPerfil();
		createMenus();
		singOutPerfil();
		
		 Tooltip tooltip = new Tooltip();
		lblTitle.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> ov, String t, String t1) {
            
            
            	         tooltip.setText("dawd");
            
            }
        }); 

		lblOpciones.setText(getResourceMessage("key.main.menu.opciones"));
		
	}

	
	
	public void createPerfil(){
    	lblNombre.setText(employed.getNombre() +" "+ employed.getApellido());
    	lblNombreBar.setText(employed.getNombre() +" "+ employed.getApellido());
		
		if (employed.getFoto() == null && employed.getSexo().equals("M")) {
			circleFoto.setFill(new ImagePattern(new Image("/META-INF/negocio/main/img/perfil/man.png")));
			circleFotoBar.setFill(new ImagePattern(new Image("/META-INF/negocio/main/img/perfil/man.png")));
		}
		else if (employed.getFoto() == null && employed.getSexo().equals("F")) {
			circleFoto.setFill(new ImagePattern(new Image("/META-INF/negocio/main/img/perfil/woman.png")));
			circleFotoBar.setFill(new ImagePattern(new Image("/META-INF/negocio/main/img/perfil/woman.png")));
		}else{
			circleFoto.setFill(new ImagePattern(ImgeUtilFx.convertByteToImage(employed.getFoto())));
			circleFotoBar.setFill(new ImagePattern(ImgeUtilFx.convertByteToImage(employed.getFoto())));
		}
        	
   

	}
	
    public void singOutPerfil(){
	
    	anchPaneBarPerfil.setOnMouseExited(new EventHandler<MouseEvent>() {

		@Override
			public void handle(MouseEvent event) {
			anchPaneBarPerfil.setStyle("-fx-background-color:  #3C8DBC;");
				
			}
		});
		
    	anchPaneBarPerfil.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
			
				anchPaneBarPerfil.setStyle("-fx-background-color: #367FAA;");

			}
		});

    	anchPaneBarPerfil.setOnMouseClicked(new EventHandler<MouseEvent>() {

    		
			@Override
			public void handle(MouseEvent event) {
			        
				        popover= new PopOver();
				        popover.getOnCloseRequest();
					    popover.setDetachable(false);
					    popover.setAutoHide(true);
					    popover.setHideOnEscape(false);
					    popover.setPrefSize(180, 180);
				        popover.setArrowLocation(ArrowLocation.TOP_CENTER);
				        try {
							popover.setContentNode(loadPoupPerfil());
						} catch (IOException e) {
							e.printStackTrace();
						}
					    popover.show(anchPaneBarPerfil);
		         
			    
		               
			}
	
    	});
		
	}
 
    int posicionSubmenus=0;
	public void createMenus(){
		    RolService rolService =(RolService) getBean("rolService");

	     	List<TbMenu> menus = rolService.rolXmenu(employed.getTbRol().getIdrol());
	     	
	    	List<AnchorPane> anchorPanes = new ArrayList<>();
	     	ObservableList<TitledPane> titledPanes = idAcordionMenu.getPanes();

	     	TbMenu tbmenu=null;
	    	TitledPane titlePane = null;
	    	AnchorPane anchorPane = null;

	   
	       	for (int i = 0; i < menus.size(); i++) {
			
				tbmenu = new TbMenu();
	            tbmenu = menus.get(i);
	            
	            titlePane = new TitledPane();
	            titlePane.setId(""+tbmenu.getIdMenu());
				titlePane.setText(getResourceMessage(tbmenu.getI18nMenu()));
		        titlePane.setGraphic(imageMenu(tbmenu.getImagenurl()));
			
				titlePane.setStyle("-fx-background-color: #243740;");
				anchorPane = new AnchorPane();
				anchorPane.setStyle("-fx-background-color: #243740;");
				anchorPanes.add(anchorPane);
			
				idAcordionMenu.getPanes().add(titlePane);
				titledPanes.get(i).setContent(anchorPane);

				double menuItmX = -1.0;
				double menuItmIncrement = 33;
				double menuItmY = -33;
				Button button = null;
				for (TbSubmenuRol b : rolService.getMenusItemRol(employed.getTbRol().getIdrol(),tbmenu.getIdMenu())) {
					menuItmY = menuItmIncrement + menuItmY;
					button = new Button();
					button.setText(b.getTbSubmenu().getI18n());
					button.setLayoutX(menuItmX);
					button.setLayoutY(menuItmY);
					button.setPrefHeight(33.0);
					button.setPrefWidth(300.0);
					button.setGraphic(imageSubMenu());
					idAcordionMenu.setPrefSize(0, 0);
		
					button.setOnAction((even)->{
					System.out.println(b.getTbSubmenu().getFxmlName());
					 loadAnchorPane(anchBody, b.getTbSubmenu().getFxmlName());
					});
					anchorPanes.get(getIndexMenu(titledPanes,titlePane)).getChildren().add(button);
				}

		}

	       	
	}

	public int getIndexMenu(List<TitledPane> panes, TitledPane title) {

		int index = -1;
		for (int i = 0; i < panes.size(); i++) {
			if (panes.get(i).getId().equals(title.getId())) {
				index = i;
				break;
			}
		}
		return index;
	}
	private ImageView imageMenu(String url){

		ImageView imgView = new ImageView();
		Image img = new Image(url);
		imgView.setImage(img);
		imgView.setFitWidth(23.0);
		imgView.setFitHeight(23.0);
		
		return imgView;
	}
	
	private ImageView imageSubMenu(){

		ImageView imgView = new ImageView();
		Image img = new Image("/META-INF/negocio/main/img/subMenu/circle.png");
		imgView.setImage(img);
		imgView.setFitWidth(10.0);
		imgView.setFitHeight(10.0);
		
		return imgView;
	}

	public void loadAnchorPane(AnchorPane ap, String url) {
		try {

			ap.getChildren().clear();
			String lenguage =(String)SessionJCMR.getInstance().getContextObject("Lenguage");
			Locale currentLocale = new Locale(lenguage);
			ResourceBundle rb = ResourceBundle.getBundle("in18/systemJCMR", currentLocale);

			StackPane pane = FXMLLoader.load(getClass().getResource(url), rb);
			scrollbarBody.setHbarPolicy(ScrollBarPolicy.NEVER);
			ap.getChildren().setAll(pane);

		} catch (IOException e) {

		}

	}
	
	
	public void closeMain(){
		closeWindow(anchBody);
	}
	

	  public void loadButton(AnchorPane ap){
	        try {
	        
	        	Locale currentLocale = new Locale("en_US");
	  			ResourceBundle rb = ResourceBundle.getBundle("in18/systemJCMR", currentLocale);
	            Button p = FXMLLoader.load(getClass().getResource("/META-INF/component/buttonBar/fxml/btnSave.fxml"),rb);
	            ap.getChildren().setAll(p);
	       } catch (IOException e) {
	       
	       }   
	  
	  }
	  public AnchorPane loadPoupPerfil() throws IOException{
		   AnchorPane p = FXMLLoader.load(getClass().getResource("/META-INF/negocio/main/fxml/poupPerfil.fxml"));
           return  p;
	  }

	public String getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(String lenguaje) {
		this.lenguaje = lenguaje;
	}

	
	


  


}
