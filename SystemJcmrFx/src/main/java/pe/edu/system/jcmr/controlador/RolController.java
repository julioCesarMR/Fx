package pe.edu.system.jcmr.controlador;

import java.net.URL;
import java.util.ResourceBundle;

import org.apache.commons.lang.StringUtils;

import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ListView.EditEvent;
import javafx.scene.control.cell.TextFieldListCell;
import javafx.util.Callback;
import javafx.util.StringConverter;
import pe.edu.system.jcmr.component.DialogConfirmJCMR;
import pe.edu.system.jcmr.component.DialogConfirmJCMR.ModalDialogAnimation;
import pe.edu.system.jcmr.entity.TbMenu;
import pe.edu.system.jcmr.entity.TbRol;
import pe.edu.system.jcmr.entity.TbSubmenu;
import pe.edu.system.jcmr.service.RolService;
import pe.edu.system.jcmr.util.BaseMatenimientoUtil;
import pe.edu.system.jcmr.util.UtilidadesFx;
import pe.edu.system.jcmr.utilCommon.ResourcesI18n;

public class RolController extends BaseMatenimientoUtil implements Initializable {



	@FXML
	public ListView<TbMenu> listViewMenu;
	@FXML
	public ListView<TbRol> listViewRol;
	@FXML
	public ListView<TbSubmenu> listViewSubMenu;
	@FXML
	public DialogConfirmJCMR dialogConfimUpdate;
	@FXML
	public DialogConfirmJCMR dialogConfimDelete;
	@FXML
	public JFXTextField txtRol;
	@FXML
	public Button btnSaveRol;
	@FXML
	public Button btnDeleteRol;
	@FXML
	public Button btnFoundRol;
	@FXML
	public Button btnRefreshMenu;
	@FXML
	public Button btnFoundMenu;
	@FXML
	public Button btnSaveMenu;
	@FXML
	public Button btnFoundSubMenu;
	@FXML
	public Button btnDeleteSubMenu;
	@FXML
	public Button btnSaveSubMenu;
	@FXML
	public Button btnUpdateSubMenu;
	@FXML
	public Button btnClearSubMenu;

	@FXML
	public Button btnDeleteMenu;
	@FXML
	public Button btnUpdateMenu;
	@FXML
	public JFXTextField txtMenu;
	@FXML
	public JFXTextField txtImgUrl;
	@FXML
	public JFXTextField txtSubMenu;
	@FXML
	public JFXTextField txtfxmlSubMenu;
	@FXML
	public Label lblCodigoMenu;
	@FXML
	public Label lblCodigoSubMenu;

	RolService rolService = (RolService) getBean("rolService");
	ObservableList<TbRol> roles;
	ObservableList<TbMenu> menus;
    ObservableList<TbSubmenu> subMenus;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadMenus();
		loadRoles();
		saveRol();
		deleteRol();
		updateRol();
		foundRol();
		

	    saveMenu();
	    deleteMenu();
	    freshButton();
	    updateMenu();
	    foundMenu();
	    
	    loadSubMenus();
	    saveSubMenu();
	    updateSubMenu();
	    deleteSubMenu();
	    foundSubMenu();
	}
	private void foundSubMenu() {
		btnFoundSubMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				 FilteredList<TbSubmenu> filteredSubMenu = new FilteredList<>(getSubMenus(), s -> true);
				  
				    String filterSubMenu = txtSubMenu.getText();
				    
				    txtSubMenu.textProperty().addListener(obs->{
				    	if(txtSubMenu.getText()==null|| txtSubMenu.getText().length()==0){
				    		filteredSubMenu.setPredicate(rol -> true);
				    		listViewSubMenu.setItems(FXCollections.observableArrayList(getSubMenus()));
				    	}
				    });
				 
				 
				    filteredSubMenu.setPredicate(rol -> rol.getI18n().toLowerCase().contains(filterSubMenu.toLowerCase()));

		    		listViewSubMenu.setItems(FXCollections.observableArrayList(filteredSubMenu));
		       
			}
		});
		
	}
	private void deleteSubMenu() {
		
       btnDeleteSubMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				TbSubmenu subMenu = listViewSubMenu.getSelectionModel().getSelectedItem();
				if (subMenu != null) {
					dialogConfimDelete.titleMessage(getResourceMessage("confirm.title.information"));
					dialogConfimDelete.message(getResourceMessage("confirm.delete.message"));
					dialogConfimDelete.show(ModalDialogAnimation.RIGHT);
				} else {
					messageError(getResourceMessage("select.info.message"));
				}
		
				
			}
		});
		dialogConfimDelete.btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				TbSubmenu subMenu =listViewSubMenu.getSelectionModel().getSelectedItem();
				rolService.deleteSubMenu(subMenu.getIdsubmenu());
				messageSuccess(getResourceMessage("datos.delete.ok"));
				loadSubMenus();
				dialogConfimDelete.closeDialog();
			}
		});
		dialogConfimDelete.btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				dialogConfimDelete.closeDialog();
				listViewSubMenu.getSelectionModel().clearSelection();
			} 
		});
	}
	private void updateSubMenu() {
	lblCodigoSubMenu.setVisible(true);
		
		listViewSubMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TbSubmenu>() {

		    @Override
		    public void changed(ObservableValue<? extends TbSubmenu> observable, TbSubmenu oldValue, TbSubmenu newValue) {
		        if(newValue!=null){
		    	txtSubMenu.setText(newValue.getI18n());
		    	txtfxmlSubMenu.setText(newValue.getFxmlName());
		    	lblCodigoSubMenu.setText(""+newValue.getIdsubmenu());
		        }
		    }
		});
		
		btnUpdateSubMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(StringUtils.isBlank(txtSubMenu.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtSubMenu.getPromptText()}));
				}else if( StringUtils.isBlank(txtfxmlSubMenu.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtfxmlSubMenu.getPromptText()}));
				}else{
					
					dialogConfimUpdate.titleMessage(getResourceMessage("confirm.title.information"));
					dialogConfimUpdate.message(getResourceMessage("confirm.update.message"));
					dialogConfimUpdate.show(ModalDialogAnimation.RIGHT);
				}
				
			}
		});
		
		dialogConfimUpdate.btnConfirm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
         
				TbSubmenu subMenu = new TbSubmenu();
				subMenu.setI18n(txtSubMenu.getText());
				subMenu.setFxmlName(txtfxmlSubMenu.getText());
				subMenu.setIdsubmenu(Integer.parseInt(lblCodigoSubMenu.getText()));
				rolService.updateSubMenu(subMenu);
				messageSuccess(getResourceMessage("datos.update.ok"));
				loadSubMenus();
				clearFieldSubMenu();
				dialogConfimUpdate.closeDialog();
			}
		});
		
	
		dialogConfimUpdate.btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
			        dialogConfimUpdate.closeDialog();	
			}
		});
		
		
	}
	private void loadSubMenus() {
		listViewSubMenu.getItems().clear();
		subMenus =FXCollections.observableArrayList(rolService.getSubMenu());
		listViewSubMenu.getItems().addAll(subMenus);
		listViewSubMenu.setCellFactory(new Callback<ListView<TbSubmenu>, ListCell<TbSubmenu>>() {

			@Override
			public ListCell<TbSubmenu> call(ListView<TbSubmenu> param) {
				ListCell<TbSubmenu> cell = new ListCell<TbSubmenu>() {

					@Override
					protected void updateItem(TbSubmenu item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							String Submenu = getResourceMessage(item.getI18n());
							if(StringUtils.isBlank(Submenu)){
								Submenu=item.getI18n();
							}
							setText(Submenu);
						}else{
							setText("");
						}

					}
				};
				return cell;
			}
		});
		
	}
	private void saveSubMenu() {
		btnSaveSubMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(StringUtils.isBlank(txtSubMenu.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtSubMenu.getPromptText()}));
				}else if( StringUtils.isBlank(txtfxmlSubMenu.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtfxmlSubMenu.getPromptText()}));
				}else{
					TbSubmenu subMenu = new TbSubmenu();
					subMenu.setI18n(txtSubMenu.getText());
					subMenu.setFxmlName(txtfxmlSubMenu.getText());
					rolService.insertSubMenu(subMenu);
					messageSuccess(getResourceMessage("datos.insert.ok"));
					loadSubMenus();
					clearFieldSubMenu();
				}
			}
		} );
		
	}
	public void foundMenu(){
		btnFoundMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				 FilteredList<TbMenu> filteredMenu = new FilteredList<>(getMenus(), s -> true);
				  
				    String filterRol = txtMenu.getText();
				    
				    txtMenu.textProperty().addListener(obs->{
				    	if(txtMenu.getText()==null|| txtMenu.getText().length()==0){
				    		filteredMenu.setPredicate(rol -> true);
				    		   listViewMenu.setItems(FXCollections.observableArrayList(getMenus()));
				    	}
				    });
				 
				 
				    filteredMenu.setPredicate(rol -> rol.getI18nMenu().toLowerCase().contains(filterRol.toLowerCase()));

		    		listViewMenu.setItems(FXCollections.observableArrayList(filteredMenu));
		       
			}
		});
	}
	public void saveMenu(){
		btnSaveMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				if(StringUtils.isBlank(txtMenu.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtMenu.getPromptText()}));
				}else if( StringUtils.isBlank(txtImgUrl.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtImgUrl.getPromptText()}));
				}else{
					TbMenu menu = new TbMenu();
					menu.setI18nMenu(txtMenu.getText());
					menu.setImagenurl(txtImgUrl.getText());
					rolService.insertMenu(menu);
					messageSuccess(getResourceMessage("datos.insert.ok"));
					loadMenus();
					clearFieldMenu();
				}
			}
		} );
	}
	public void freshButton(){
		btnRefreshMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {

				clearFieldMenu();
			}

		});
		
		btnClearSubMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
		
		          clearFieldSubMenu();		
			}
		});
	}
	public void updateMenu(){
		lblCodigoMenu.setVisible(true);
		
		listViewMenu.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TbMenu>() {

		    @Override
		    public void changed(ObservableValue<? extends TbMenu> observable, TbMenu oldValue, TbMenu newValue) {
		        if(newValue!=null){
		    	txtMenu.setText(newValue.getI18nMenu());
		    	txtImgUrl.setText(newValue.getImagenurl());
		    	lblCodigoMenu.setText(""+newValue.getIdMenu());
		        }
		    }
		});
		
		btnUpdateMenu.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(StringUtils.isBlank(txtMenu.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtMenu.getPromptText()}));
				}else if( StringUtils.isBlank(txtImgUrl.getText())){
					messageError(getResourceMessage("required.campo.message",new Object[]{txtImgUrl.getPromptText()}));
				}else{
					
					dialogConfimUpdate.titleMessage(getResourceMessage("confirm.title.information"));
					dialogConfimUpdate.message(getResourceMessage("confirm.update.message"));
					dialogConfimUpdate.show(ModalDialogAnimation.RIGHT);
				}
				
			}
		});
		
		dialogConfimUpdate.btnConfirm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
               System.out.println("update");
				TbMenu menu = new TbMenu();
				menu.setI18nMenu(txtMenu.getText());
				menu.setImagenurl(txtImgUrl.getText());
				menu.setIdMenu(Integer.parseInt(lblCodigoMenu.getText()));
				rolService.updateMenu(menu);
				messageSuccess(getResourceMessage("datos.update.ok"));
				loadMenus();
				clearFieldMenu();
				dialogConfimUpdate.closeDialog();
			}
		});
		
	
		dialogConfimUpdate.btnCancel.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
			
			        dialogConfimUpdate.closeDialog();	
			}
		});
		
		
	}
	
	public void deleteMenu(){

		btnDeleteMenu.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				TbMenu menu = listViewMenu.getSelectionModel().getSelectedItem();
				if (menu != null) {
					dialogConfimDelete.titleMessage(getResourceMessage("confirm.title.information"));
					dialogConfimDelete.message(getResourceMessage("confirm.delete.message"));
					dialogConfimDelete.show(ModalDialogAnimation.RIGHT);
				} else {
					messageError(getResourceMessage("select.info.message"));
				}
		
				
			}
		});
		dialogConfimDelete.btnConfirm.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				TbMenu menu = listViewMenu.getSelectionModel().getSelectedItem();
				rolService.deleteMenu(menu.getIdMenu());
				messageSuccess(getResourceMessage("datos.delete.ok"));
				loadMenus();
				dialogConfimDelete.closeDialog();
			}
		});
		dialogConfimDelete.btnCancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				dialogConfimDelete.closeDialog();
				listViewMenu.getSelectionModel().clearSelection();
			} 
		});
	}
	
	public void clearFieldMenu(){
		txtMenu.clear();
		txtImgUrl.clear();
	}
	public void clearFieldSubMenu(){
		txtSubMenu.clear();
		txtfxmlSubMenu.clear();
	}

	public void foundRol() {

		btnFoundRol.setOnAction((event) -> {
		
		    FilteredList<TbRol> filteredRol = new FilteredList<>(getRoles(), s -> true);
		  
		    String filterRol = txtRol.getText();
		    
		    txtRol.textProperty().addListener(obs->{
		    	if(txtRol.getText()==null|| txtRol.getText().length()==0){
		    		   filteredRol.setPredicate(rol -> true);
		    		   listViewRol.setItems(FXCollections.observableArrayList(getRoles()));
		    	}
		    });
		 
		 
    		filteredRol.setPredicate(rol -> rol.getI18nRol().toLowerCase().contains(filterRol.toLowerCase()));

			listViewRol.setItems(FXCollections.observableArrayList(filteredRol));
       
			
		});
	

	}

	public void saveRol() {
		btnSaveRol.setOnAction((event) -> {

			if (!txtRol.getText().equals("") || txtRol.getText().length() != 0) {
				TbRol rol = new TbRol();
				rol.setI18nRol(txtRol.getText());
							
				rolService.insertRol(rol);

				loadRoles();
				messageSuccess(this.getResourceMessage("datos.insert.ok"));
			} else {

				messageError(this.getResourceMessage("required.campo.message", new Object[] { txtRol.getPromptText() }));
			}

		});

	}

	public void deleteRol() {
		
		btnDeleteRol.setOnAction((event) -> {
			TbRol rol = listViewRol.getSelectionModel().getSelectedItem();
			if (rol != null) {
				dialogConfimDelete.titleMessage(this.getResourceMessage("confirm.title.information"));
				dialogConfimDelete.message(this.getResourceMessage("confirm.delete.message"));
				dialogConfimDelete.show(ModalDialogAnimation.RIGHT);
			} else {
				messageError(this.getResourceMessage("select.info.message"));
			}
		});
  
		dialogConfimDelete.btnConfirm.setOnAction((event) -> {
			TbRol rol = listViewRol.getSelectionModel().getSelectedItem();
			rolService.deleteRol(rol.getIdrol());
			loadRoles();
		    roles.remove(rol);
		     
			dialogConfimDelete.closeDialog();
		});

		dialogConfimDelete.btnCancel.setOnAction((event) -> {

			dialogConfimDelete.closeDialog();

		});

	}

	public void updateRol() {
	

		listViewRol.setOnEditCommit(new EventHandler<EditEvent<TbRol>>() {
			@Override
			public void handle(EditEvent<TbRol> t) {
				dialogConfimUpdate.titleMessage(getResourceMessage("confirm.title.information"));
				dialogConfimUpdate.message(getResourceMessage("confirm.update.message"));
				dialogConfimUpdate.show(ModalDialogAnimation.RIGHT);

				dialogConfimUpdate.btnConfirm.setOnAction((value) -> {

					listViewRol.getItems().set(t.getIndex(), t.getNewValue());
					rolService.updateRol(t.getNewValue());
					dialogConfimUpdate.closeDialog();

				});
				dialogConfimUpdate.btnCancel.setOnAction((value) -> {
					dialogConfimUpdate.closeDialog();
					loadRoles();

				});

			}

		});
	}

	public void loadRoles() {
	
		listViewRol.getItems().clear();
		roles =FXCollections.observableArrayList(rolService.getRoles());
		listViewRol.getItems().addAll(roles);
		listViewRol.setCellFactory(new Callback<ListView<TbRol>, ListCell<TbRol>>() {

			@Override
			public ListCell<TbRol> call(ListView<TbRol> param) {
				ListCell<TbRol> cell = new ListCell<TbRol>() {

					@Override
					protected void updateItem(TbRol item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							setText(getResourceMessage(item.getI18nRol()));
						}else{
							setText("");
						}

					}
				};
				return cell;
			}
		});
		listViewRol.setCellFactory(lv -> {
			TextFieldListCell<TbRol> cell = new TextFieldListCell<>();
			cell.setConverter(new ClientConverter(cell));
			return cell;
		});
		listViewRol.setEditable(true);

}

	public void loadMenus() {
		listViewMenu.getItems().clear();
		menus =FXCollections.observableArrayList(rolService.getMenus());
		listViewMenu.getItems().addAll(menus);
		listViewMenu.setCellFactory(new Callback<ListView<TbMenu>, ListCell<TbMenu>>() {

			@Override
			public ListCell<TbMenu> call(ListView<TbMenu> param) {
				ListCell<TbMenu> cell = new ListCell<TbMenu>() {

					@Override
					protected void updateItem(TbMenu item, boolean empty) {
						super.updateItem(item, empty);
						if (item != null) {
							String menu = getResourceMessage(item.getI18nMenu());
							if(StringUtils.isBlank(menu)){
								menu=item.getI18nMenu();
							}
							setText(menu);
						}else{
							setText("");
						}

					}
				};
				return cell;
			}
		});


	}

	public static class ClientConverter extends StringConverter<TbRol> {
		private final ListCell<TbRol> cell;

		public ClientConverter(ListCell<TbRol> cell) {
			this.cell = cell;
		}

		@Override
		public String toString(TbRol rol) {
			UtilidadesFx i18n = new UtilidadesFx();
			return i18n.getResourceMessage(rol.getI18nRol());
		}

		@Override
		public TbRol fromString(String descripcion) {
			TbRol client = cell.getItem();
			client.setI18nRol(descripcion);
			return client;
		}

	}
	

	

	public ObservableList<TbRol> getRoles() {
		return roles;
	}

	public void setRoles(ObservableList<TbRol> roles) {
		this.roles = roles;
	}

	public ObservableList<TbMenu> getMenus() {
		return menus;
	}

	public void setMenus(ObservableList<TbMenu> menus) {
		this.menus = menus;
	}
	public ObservableList<TbSubmenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(ObservableList<TbSubmenu> subMenus) {
		this.subMenus = subMenus;
	}

}
