/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.system.jcmr.controlador;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import jfxtras.scene.control.CalendarTextField;
import pe.edu.system.jcmr.component.DialogConfirmJCMR;
import pe.edu.system.jcmr.component.DialogConfirmJCMR.ModalDialogAnimation;
import pe.edu.system.jcmr.entity.BaseEntity;
import pe.edu.system.jcmr.entity.TbEmpleado;
import pe.edu.system.jcmr.entity.TbRol;
import pe.edu.system.jcmr.entity.TbUbigeo;
import pe.edu.system.jcmr.gui.MailMail;
import pe.edu.system.jcmr.service.EmployedService;
import pe.edu.system.jcmr.service.RolService;
import pe.edu.system.jcmr.service.UbigeoService;
import pe.edu.system.jcmr.util.BaseMatenimientoUtil;
import pe.edu.system.jcmr.util.FormattedDateValueFactory;
import pe.edu.system.jcmr.util.ImgeUtilFx;
import pe.edu.system.jcmr.util.SessionJCMR;
import pe.edu.system.jcmr.utilCommon.ConstansRegx;
import pe.edu.system.jcmr.validation.ValidationJcmr;



/**
*
* @author Julio Cesar Meza Rios
*/
@SuppressWarnings({ "unchecked", "rawtypes" })

public  class MantenimientoFXMLController extends BaseMatenimientoUtil implements Initializable  {



	@FXML	private TableColumn<TbEmpleado, String> columNombre;
	@FXML	private TableColumn<TbEmpleado, String>  columTelefono;
	@FXML	private TableColumn<TbEmpleado, String>  columSexo;
	@FXML	private TableColumn<TbEmpleado, String> columApellido;
	@FXML	private TableColumn<TbEmpleado, String> columIdUbigeo;
	@FXML	private TableColumn<TbEmpleado, String> columfechaNac;
	@FXML	private TableColumn<TbEmpleado, String>  columEmail;
	@FXML	private TableView<TbEmpleado> tbViewEmployee;

	@FXML	private JFXTextField txtDocuments;
	@FXML	private JFXDatePicker txtBirthdate;

	@FXML	private JFXTextField txtName;
	@FXML	private RadioButton rbMale;
	@FXML	private JFXTextField txtSalary;
	@FXML	private RadioButton rboFemale;
	@FXML	private JFXComboBox<BaseEntity> cboDistrito;
	@FXML	private JFXComboBox<BaseEntity> cboDepartamento;
	@FXML	private JFXComboBox<BaseEntity> cboCargo;
	@FXML	private JFXComboBox<BaseEntity> cboProvincia;
	@FXML	private JFXComboBox<BaseEntity> cbostatusCivil;
	@FXML	private JFXTextArea txtAddress;
	@FXML	private JFXTextField fecStartContract;
	@FXML	private JFXTextField txtPassword;
	@FXML	private JFXTextField txtPhone;
	@FXML	private JFXTextField txtEmail;
	@FXML	private JFXTextField txtCell;
	@FXML	private JFXTextField txtNumDocument;
	@FXML	private JFXTextField txtLastName;
	
	@FXML	private AnchorPane anchCRUD;
	@FXML	private AnchorPane anchSearch;
	
	@FXML	private ToolBar toolBarAction;
	@FXML	private Button btnSearch;
	@FXML	private Button btnRefresh;
	@FXML	private Button btnDelete;
	@FXML	private Button btnSave;
	@FXML	private Button btnSalir;
	
	@FXML	private ToolBar toolBarNav;
	@FXML	private Button btnNavNew;
	@FXML	private Button btnNavEdit;
	
	@FXML	private ComboBox<BaseEntity> rowPageEmployee;
	@FXML	private ProgressIndicator progreesIndicatorTbView;
	@FXML	private Pagination paginationEmployee;
	
	@FXML	private Region backRegion;
	@FXML	private ProgressIndicator progressBarMain;
	
	@FXML	private Button SubirFoto;
	@FXML   CalendarTextField calentarTextField;
	
	@FXML	private ImageView imgPhotoEmployee;
	
    @FXML   private DialogConfirmJCMR dialogConfimDelete;
	
    @FXML   private TextField  txtSearchEmployed;

	private boolean newRecord;
	

	
	ToggleGroup groupRadioBu = new ToggleGroup();

    ObservableList<TbEmpleado>  employees;
	List<BaseEntity> provincias =null;
	List<BaseEntity> distritos = null; 
	ObservableList<BaseEntity> roles;
	
	RolService  rolService =(RolService) getBean("rolService");
	EmployedService employedService = (EmployedService) getBean("employedService");
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		
		rbMale.setUserData("M");
		rboFemale.setUserData("F");
		
//		progressBarMain.setVisible(false);
//		backRegion.setVisible(false);
//		backRegion.setPrefHeight(652);
//		backRegion.setPrefHeight(1025);
		cboLoadUbigeo();
		esCorrecto();
		loadRol();;
		groupRbtSex();
		loadPageRow();
	    blockTextField();


	     txtBirthdate.setDefaultColor(Color.valueOf("#9A9C9E"));
	     
	     
	     searchEmployed();
     
	}
	

	
	public void loadPageRow(){
		List<BaseEntity> numberRows = new ArrayList<BaseEntity>();
		
		numberRows.add(new BaseEntity("1", "10"));
		numberRows.add(new BaseEntity("2", "20"));
		numberRows.add(new BaseEntity("3", "30"));
		numberRows.add(new BaseEntity("4", "50"));
		numberRows.add(new BaseEntity("5", "100"));
		rowPageEmployee.getItems().addAll(numberRows);
		rowPageEmployee.getSelectionModel().select(0);
	}
	
	private void groupRbtSex() {
		rboFemale.setToggleGroup(groupRadioBu);
		rbMale.setToggleGroup(groupRadioBu);
	}
	public void openProgrees(){
		progressBarMain.setVisible(true);
	    backRegion.setVisible(true);
	}
	public void closeProgrees(){
		progressBarMain.setVisible(false);
	    backRegion.setVisible(false);
	}

	
	@FXML 
	public void btnOnSave(ActionEvent event) throws InterruptedException, IOException{
	
  


//		openProgrees();
//		Service<Void> service = new Service<Void>() {
//			@Override
//			protected Task<Void> createTask() {
//				return new Task<Void>() {
//					@Override
//					protected Void call() throws Exception {
//
////                            
//
//						return null;
//					}
//
//				};
//			}
//
//			@Override
//			protected void succeeded() {
//				closeProgrees();
//			}
//			@Override
//			protected void failed() {
//			      closeProgrees();
//			}
//
//		};
//		service.start();
//		
	
	//	openWindow1("/META-INF/component/webCam/fxml/WebCamPreview.fxml");
//		if(esCorrecto()){
//			System.out.println("CORRECTO");
//		
//		}else{
//			System.out.println("incorrecto");
//		}
//		
//		
	
//		System.out.println(txtBirthdate.getConverter().fromString(txtBirthdate.getEditor().getText()));


	}
	
	private void onSaveEmployed() {
		TbEmpleado empleado = new TbEmpleado();
		empleado.setEmpleadoId(145);
		empleado.setNombre(txtName.getText());
		empleado.setApellido(txtLastName.getText());
		empleado.setDni(Integer.parseInt(txtNumDocument.getText()));
		empleado.setClave(txtPassword.getText());
		empleado.setDireccion("zzzz");
		empleado.setEmail("xxc@hotmail.com");
		empleado.setCelular("526110944");
		empleado.setSexo("M");
		empleado.setTelefono("526110944");
		empleado.setFechaNac(new Date());
        empleado.setSexo(groupRadioBu.getSelectedToggle().getUserData().toString());
        empleado.setTelefono(txtPhone.getText());
        empleado.setCelular(txtCell.getText());
        empleado.setEmail(txtEmail.getText());
        
		TbUbigeo u = new TbUbigeo();
		u.setUbigeoId(3631);

		empleado.setTbUbigeo(u);
		TbRol r = new TbRol();
		r.setIdrol(1);

		empleado.setTbRol(r);
	
	//	employedService.insert(empleado);
	}
	
	private void onSearch(){
		loadGridEmployed();
	}

	
	@FXML
	void btnOnNavEdit(ActionEvent event) {
		try {
			TbEmpleado employee = tbViewEmployee.getSelectionModel().getSelectedItem();
			if (employee != null) {
				anchCRUD.setVisible(true);
				anchSearch.setVisible(false);
				newRecord = false;
				txtName.setText(employee.getNombre());
				txtLastName.setText(employee.getApellido());
				txtEmail.setText(employee.getEmail());
				txtNumDocument.setText(String.valueOf(employee.getDni()));
				txtPassword.setText(employee.getClave());
				txtBirthdate.setValue(converterDateToLocalDate(employee.getFechaNac()));

				if (employee.getSexo().equals("M")) {
					rbMale.setText("M");
				} else {
					rboFemale.setText("F");
				}
				txtCell.setText(employee.getCelular());
				txtPhone.setText(employee.getTelefono());
				txtEmail.setText(employee.getEmail());
				cboDepartamento.setValue(getValueBaseEntity(employee.getTbUbigeo().getDepartamento()));
				cboProvincia.setValue(getValueBaseEntity(employee.getTbUbigeo().getProvincia()));
				cboDistrito.setValue(getValueBaseEntity(employee.getTbUbigeo().getDistrito()));
				txtAddress.setText(employee.getDireccion());
			} else {

				menssajeValidation("", "Seleccione registro");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

	@FXML
	void btnOnNavNew(ActionEvent event) {
		anchCRUD.setVisible(true);
		anchSearch.setVisible(false);
		newRecord = true;

	}

	
	public void onDeleteEmployed(){
	    TbEmpleado employee = tbViewEmployee.getSelectionModel().getSelectedItem();
	    if(employee!=null){
			dialogConfimDelete.titleMessage(this.getResourceMessage("confirm.title.information"));
			dialogConfimDelete.message(this.getResourceMessage("confirm.delete.message"));
			dialogConfimDelete.show(ModalDialogAnimation.RIGHT);

			dialogConfimDelete.btnConfirm.setOnAction((event) -> {

				System.out.println("OK");
				dialogConfimDelete.closeDialog();
			});

	        dialogConfimDelete.btnCancel.setOnAction((event)->{
	        	System.out.println("CANCELAR");
	        	tbViewEmployee.getSelectionModel().clearSelection();
	    		dialogConfimDelete.closeDialog();
	        });
			
	    }else{
	    	menssajeValidation("", this.getResourceMessage("select.info.message"));
	    }
	}

	public void onRefresh() {
		for (Node node : anchCRUD.getChildren()) {
			if (node instanceof TextField) {
				((TextField) node).setText("");
			}

		}
	}


	@FXML void btnChooseImage(ActionEvent event) throws IOException{
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Seleccione Imagen");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
				new FileChooser.ExtensionFilter("JPG", "*.jpg"), new FileChooser.ExtensionFilter("PNG", "*.png"));

		File file = fileChooser.showOpenDialog(new Stage());
		if (file != null) {
			if (file.getName().endsWith(".png") || file.getName().endsWith(".jpg")) {
				
				imgPhotoEmployee.setImage(ImgeUtilFx.fileToImage(file));
				
			} else {
				menssajeValidation("", "Error al seleccionar imagen");
			}
		}

	}
	@FXML void btnOnClearPhoto(ActionEvent event){

		imgPhotoEmployee.setImage(new Image("/META-INF/negocio/mantenimento/crudEmployee/img/webcam.png"));
	}

	
    void onSalir() {
		anchCRUD.setVisible(false);
		anchSearch.setVisible(true);
	}
	
	private void loadRol() {

		roles = FXCollections.observableArrayList(rolService.getBaseRoles());
		cboCargo.getItems().addAll(roles);

	}

	private void cboLoadUbigeo() {

		UbigeoService ubigeoService=(UbigeoService)getBean("ubigeoService");
		List<BaseEntity> departamentos = ubigeoService.getDepartamentos();
			
		cboDepartamento.getItems().addAll(departamentos);

		cboDepartamento.valueProperty().addListener( new ChangeListener<BaseEntity>() {

			@Override
			public void changed(ObservableValue<? extends BaseEntity> observable,BaseEntity oldValue, BaseEntity newValue) {
	        
				cboProvincia.getItems().clear();
				cboDistrito.getItems().clear();
				provincias = ubigeoService.getProvincias(newValue.getDescripcion());
				cboProvincia.getItems().addAll(provincias);
			}
		});
		
	
		cboProvincia.valueProperty().addListener( new ChangeListener<BaseEntity>() {

			@Override
			public void changed(ObservableValue<? extends BaseEntity> observable,BaseEntity oldValue, BaseEntity newValue) {

				if (newValue != null) {
					distritos = ubigeoService.getDistritos(cboDepartamento.getValue().getDescripcion(),newValue.getDescripcion());
					cboDistrito.getItems().addAll(distritos);
				}
			}
		});
	

	}


	public void blockTextField(){
		ValidationJcmr validacion = new ValidationJcmr();
		validacion.lettersAndSpaces(txtName, 30);
		validacion.lettersAndSpaces(txtLastName, 30);
	    validacion.onlyNumber(txtNumDocument, 8);
	    validacion.datePickerFormt(txtBirthdate, ConstansRegx.DATEFORM_DAY_MONTH_YEAR);
	}

	public boolean esCorrecto() {
	
		boolean es = false;

	
		if (txtName.getText().trim().matches("")) {
			menssajeValidation(null, "Nombre es un campo requerido");
			return es;
		}
		if (!txtName.getText().trim().matches(ConstansRegx.NOMBRES)) {
			menssajeValidation(null, "Formato de nombre error");
			return es;
		}
		if (txtLastName.getText().trim().matches("")) {
			menssajeValidation(null, "Apellido  es un campo requerido");
			return es;

		}
		if (!txtLastName.getText().trim().matches(ConstansRegx.APELLIDO)) {
			menssajeValidation(null, "Formato de apellido error");
			return es;
		}
		if (txtEmail.getText().trim().matches("")) {
			menssajeValidation(null,"email es un campo requerido");
			return es;

		}

		if (txtNumDocument.getText().trim().matches("")) {
			menssajeValidation(null, "Dni  es un campo requerido");
			return es;
		}
		if (!txtNumDocument.getText().trim().matches(ConstansRegx.DNI_OCHO_DIGITOS)) {
			menssajeValidation(null, "Minimo 8 caracteres");
			return es;

		}
		if(groupRadioBu.selectedToggleProperty().getValue()==null){
			menssajeValidation(null, "Seleccione Sexo");
			return es;
		}
		
		
		if (!txtEmail.getText().trim().matches(ConstansRegx.EMAIL_PATTERN)) {
			menssajeValidation(null,"formato error");
			return es;
		}
	

		return es;
	}
//	     	DownloadFile x = new DownloadFile();
//    	    x.downloadFile("http://www2.sunat.gob.pe/padron_reducido_ruc.zip","html","foto","C:/Users/cmeza/Desktop");
//	        x.downloadFile("http://java.ciberaula.com/articulo/diseno_patrones_j2ee","html","foto","C:/Users/cmeza/Desktop");
		
	public void sendMail(){
		
		MailMail mm = (MailMail) getBean("mailMail");
    	
    	Map<String, Object> model = new HashMap<>();
    	model.put("To", "juliocesarmezarios@gmail.com");
    	model.put("From","juliocesarmezarios@gmail.com");
    	model.put("Cc","juliocesarmezarios@hotmail.com");
    	model.put("template","suggestPodcastNotificationMessage.vm");
  
		  ArrayList list = new ArrayList();
	        Map map = new HashMap();
	        map.put("name", "Cow");
	        map.put("price", "$100.00");
	        list.add( map );
	 
	        map = new HashMap();
	        map.put("name", "Eagle");
	        map.put("price", "$59.99");
	        list.add( map );

	        map = new HashMap();
	        map.put("name", "Shark");
	        map.put("price", "$3.99");
	        list.add( map );
	        map.put("petList", list);
    	  
	        model.put("model",map);
			mm.sendMail(model);
		
	}
	
	
	
	

	private void loadGridEmployed() {

		EmployedService employedService = (EmployedService) getBean("employedService");

		employees = FXCollections.observableList(employedService.getEmpleados());

		columNombre.setCellValueFactory(new PropertyValueFactory("nombre"));
		columApellido.setCellValueFactory(new PropertyValueFactory("apellido"));
		columTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
		columfechaNac.setCellValueFactory(new FormattedDateValueFactory<TbEmpleado>("fechaNac", ConstansRegx.DATEFORM_DAY_MONTH_YEAR));
		columEmail.setCellValueFactory(new PropertyValueFactory("email"));
		columSexo.setCellValueFactory(new PropertyValueFactory("sexo"));
		columIdUbigeo.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTbUbigeo().getDepartamento()));

		tbViewEmployee.setItems(employees);

		createPaginator(tbViewEmployee, paginationEmployee, employees, progreesIndicatorTbView);
		createRowPage(rowPageEmployee);
    
	}


	public void searchEmployed(){
		txtSearchEmployed.textProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				if(getEmployees()!=null && getEmployees().size()>0)
				if (txtSearchEmployed.textProperty().get().isEmpty()) {
					tbViewEmployee.setItems(getEmployees());
					return;
				} else{
				    ObservableList  emp=  	filterPlantList(txtSearchEmployed.textProperty().get(), getEmployees());
					System.out.println("xzc:"+emp.size());
				    tbViewEmployee.setItems(filterPlantList(txtSearchEmployed.textProperty().get(), getEmployees()));
					createPaginator(tbViewEmployee, paginationEmployee, emp, progreesIndicatorTbView);
					createRowPage(rowPageEmployee);
			  
				}
				
			
				
//				 ObservableList<TbEmpleado> tableItems = FXCollections.observableArrayList();
//	                ObservableList<TableColumn<TbEmpleado, ?>> cols = tbViewEmployee.getColumns();
//	                for(int i=0; i<getEmployees().size(); i++) {
//	                    
//	                    for(int j=0; j<cols.size(); j++) {
//	                        TableColumn col = cols.get(j);
//	                        String cellValue = col.getCellData(getEmployees().get(i)).toString();
//	                        cellValue = cellValue.toLowerCase();
//	                        if(cellValue.contains(txtSearchEmployed.textProperty().get().toLowerCase())) {
//	                            tableItems.add(getEmployees().get(i));
//	                            break;
//	                        }                        
//	                    }
//
//	                }
//	                tbViewEmployee.setItems(tableItems);
			}
			
		});
		
	}

	public Integer ubigeoId() {
		UbigeoService ubigeoService =(UbigeoService)getBean("ubigeoService");
		Integer codigo = ubigeoService.getIdUbigeo(cboDepartamento.getValue()
				.toString(),cboProvincia.getValue().toString(),cboProvincia
				.getValue().toString());
		return codigo;
	}


	public ObservableList<TbEmpleado> getEmployees() {
		return employees;
	}
	public void setEmployees(ObservableList<TbEmpleado> employees) {
		this.employees = employees;
	}
	public boolean isNewRecord() {
		return newRecord;
	}
	public void setNewRecord(boolean newRecord) {
		this.newRecord = newRecord;
	}

	public ObservableList<BaseEntity> getRoles() {
		return roles;
	}

	public void setRoles(ObservableList<BaseEntity> roles) {
		this.roles = roles;
	}

	public void handleButtonAction(ActionEvent event) {

		Platform.runLater(new Runnable() {

			@Override
			public void run() {

				Button button = (Button) event.getSource();

				switch (button.getId()) {
				case "btnDelete":
					onDeleteEmployed();
					break;
				case "btnSave":
					break;
				case "btnSearch":
					onSearch();
					break;
				case "btnSalir":
					onSalir();
					break;
				case "btnRefresh":
					onRefresh();
					break;

				}

			}
		});
	}
}




	


	


	
	


