

package pe.edu.system.jcmr.validation;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import pe.edu.system.jcmr.entity.BaseEntity;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class TestTable extends Pane   {

	
	@FXML private Region idRegionTabla;
	@FXML private ComboBox<BaseEntity> idRowPage;
	@FXML private Pagination   idPaginationTable ;
	@FXML private TableView idTableSimple;
	@FXML private ProgressIndicator idProgressIndTable;
//	@FXML private Button btnTxt;
//	@FXML private Button btnCsv;
//	@FXML private Button btnPdf;
//	@FXML private Button btnExcel;
	
	
	
	private BooleanProperty showPaginator = new SimpleBooleanProperty(this,"showPaginator");
	private BooleanProperty showIndicator = new SimpleBooleanProperty(this,"showIndicator");
	private BooleanProperty showRowPage = new SimpleBooleanProperty(this,"showRowPage");
    private IntegerProperty rowPage = new SimpleIntegerProperty(this,"rowPage");
    private StringProperty  namesColumns = new SimpleStringProperty(this,"namesColumns");
    private StringProperty  valuesColumns = new SimpleStringProperty(this,"valuesColumns");
   
    private BooleanProperty  showPdf = new SimpleBooleanProperty(this,"showPdf");
    private BooleanProperty  showExcel = new SimpleBooleanProperty(this,"showExcel");
    private BooleanProperty  showCsv = new SimpleBooleanProperty(this,"showCsv");
    private BooleanProperty  showTxt = new SimpleBooleanProperty(this,"showTxt");
   
    
    private static ResourceBundle rb = ResourceBundle.getBundle("in1888/Aplication");
  //  private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    
	private List listData=null;
    private String imgColumUrl;
	
//    public ImageView btnExcel(){
//    	return paginatorExportFileController.btnCsv;
//    }
    
	public TestTable() { 
	      
	   String fxmlFile ="/META-INF/component/gridSimple/fxml/gridSimple.fxml";
	   FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
	   fxmlLoader.setRoot(this);
       fxmlLoader.setController(this);
 
       try {
    	  Parent parent=  fxmlLoader.load();  
    	  parent.getStylesheets().add("/META-INF/component/gridSimple/css/gridSimple.css");
       } catch (IOException exception) {
           throw new RuntimeException(exception);
       }

   } 


   public  TableView.TableViewSelectionModel getSelectionModel(){
	   
	 return  idTableSimple.getSelectionModel();
	 
   }



	public void loadColum(List listData){

		this.listData = listData;
		
    	List<TableColumn>  columns = new ArrayList<TableColumn>();
		TableColumn colum ;
		   
		for(int i=0;i<getvalueProperties(namesColumns.get()).length;i++){
	         
			 colum = new TableColumn(getvalueProperties(namesColumns.get())[i]);
			 colum.setGraphic(imageColumn());
			 colum.setId(getvalueProperties(namesColumns.get())[i]);
			 colum.setCellValueFactory(new PropertyValueFactory(getvalueProperties(valuesColumns.get())[i]));
			 columns.add(colum);
		
		}
		idTableSimple.getColumns().addAll(columns);
		idTableSimple.setItems(FXCollections.observableArrayList(listData));
		
		showRowPage(isShowRowPage());
		createPaginator(isShowPaginator());
//	    showButtonExport();
	    
	}

	
//	private void showButtonExport(){
//		paginatorExportFileController.btnCsv.setVisible(isShowCsv());
//		paginatorExportFileController.btnPdf.setVisible(isShowPdf());
//		paginatorExportFileController.btnTxt.setVisible(isShowTxt());
//		paginatorExportFileController.btnExcel.setVisible(isShowExcel());
//
//	}

	
	
	private ImageView imageColumn(){
		
		if(getImgColumUrl()==null){
			setImgColumUrl("img/appbar.arrow.up.down.png");
		}
		
		ImageView imgView = new ImageView();
		Image img = new Image(getImgColumUrl());
		imgView.setImage(img);
		imgView.setFitWidth(28.0);
		imgView.setFitHeight(30.0);
		
		return imgView;
	}
	


	private void createPaginator(boolean showPaginator) {
		if (isShowPaginator()) {
			System.out.println(rowPage.get());
			idPaginationTable.setPageCount((this.listData.size() / this.rowPage.get() + 1));
			idPaginationTable.setCurrentPageIndex(0);
			idPaginationTable.setPageFactory(this::createPage);
		}
		idPaginationTable.setVisible(isShowPaginator());
		idProgressIndTable.setVisible(isShowIndicator());
//		idRegionTabla.setVisible(isShowIndicator());
	}

	
	private Node createPage(int pageIndex) {

     	if (isShowIndicator()) {
     		idProgressIndTable.setVisible(true);
			Node oldPlaceHolder = idTableSimple.getPlaceholder();
			idProgressIndTable.setMaxSize(90, 90);
			idTableSimple.setPlaceholder(idProgressIndTable);
			Task<ObservableList<?>> task = new Task<ObservableList<?>>() {

				@Override
				protected ObservableList<?> call() throws Exception {
					for (int i = 0; i < 101; i++) {
						Thread.sleep(20);
						updateProgress(i, 100);
					
					}
					int fromIndex = pageIndex * rowPage.get();
					int toIndex = Math.min(fromIndex + rowPage.get(), listData.size());
					return (FXCollections.observableArrayList(listData.subList(fromIndex, toIndex)));
				
				}
			};

			idProgressIndTable.progressProperty().bind(task.progressProperty());
			task.setOnSucceeded(ev -> {
				idTableSimple.setItems(task.getValue());
				idTableSimple.setPlaceholder(oldPlaceHolder);
				idProgressIndTable.setVisible(false);
				
			});
			idRegionTabla.setStyle("-fx-background-color: rgba(0, 0, 0, 0.4)");
			new Thread(task).start();
			idRegionTabla.visibleProperty().bind(task.runningProperty());
			

		}
		if (!isShowIndicator()) {
			int fromIndex = pageIndex * rowPage.get();
			int toIndex = Math.min(fromIndex + rowPage.get(), listData.size());
			idTableSimple.setItems(FXCollections.observableArrayList(listData.subList(fromIndex, toIndex)));
			idRegionTabla.setVisible(isShowIndicator());
		}

		return new AnchorPane();

}

  private void showRowPage(boolean showRowpage){

		idRowPage.setVisible(showRowpage);
		
		setRowPage(10);
		
		if (isShowRowPage()) {
			
			List<BaseEntity> numberRows =new ArrayList<BaseEntity>();

			numberRows.add(new BaseEntity("1", "10"));
			numberRows.add(new BaseEntity("2", "20"));
			numberRows.add(new BaseEntity("3", "30"));
			numberRows.add(new BaseEntity("4", "50"));
			numberRows.add(new BaseEntity("5", "100"));

			idRowPage.getItems().addAll(numberRows);
			
			idRowPage.valueProperty().addListener( new ChangeListener<BaseEntity>() {
			@Override
			public void changed(ObservableValue<? extends BaseEntity> observable,BaseEntity oldValue, BaseEntity newValue) {
			
			    setRowPage(Integer.parseInt(newValue.getDescripcion()));
				createPaginator(isShowPaginator());
			}
			
		  });
			
			
		}

}


  
	protected String[] getvalueProperties(String properties) {
		
		String value[] = rb.getString(properties).toString().split(",");
		return value;
	}

	public final BooleanProperty showRowPageProperty() {
		return this.showRowPage;
	}
	
	public final boolean isShowRowPage() {
		return this.showRowPageProperty().get();
	}
	
	public final void setShowRowPage(final boolean showRowPage) {
		this.showRowPageProperty().set(showRowPage);
	}

	public final IntegerProperty rowPageProperty() {
		return this.rowPage;
	}

	public final int getRowPage() {
		return this.rowPageProperty().get();
	}

	public final void setRowPage(final int rowPage) {
		this.rowPageProperty().set(rowPage);
	}

	public final StringProperty namesColumnsProperty() {
		return this.namesColumns;
	}

	public final String getNamesColumns() {
		return this.namesColumnsProperty().get();
	}
	
	public final void setNamesColumns(final String namesColumns) {
		this.namesColumnsProperty().set(namesColumns);
	}
	
	public final StringProperty valuesColumnsProperty() {
		return this.valuesColumns;
	}
	
	public final String getValuesColumns() {
		return this.valuesColumnsProperty().get();
	}
	
	public final void setValuesColumns(final String valuesColumns) {
		this.valuesColumnsProperty().set(valuesColumns);
	}

	public List getListData() {
		return listData;
	}

	public void setListData(List listData) {
		this.listData = listData;
	}

	public final BooleanProperty showIndicatorProperty() {
		return this.showIndicator;
	}
	
	public final boolean isShowIndicator() {
		return this.showIndicatorProperty().get();
	}
	
	public final void setShowIndicator(final boolean showIndicator) {
		this.showIndicatorProperty().set(showIndicator);
	}

	public final BooleanProperty showPaginatorProperty() {
		return this.showPaginator;
	}
	
	public final boolean isShowPaginator() {
		return this.showPaginatorProperty().get();
	}
	
	public final void setShowPaginator(final boolean showPaginator) {
		this.showPaginatorProperty().set(showPaginator);
	}

	public String getImgColumUrl() {
		return imgColumUrl;
	}

	public void setImgColumUrl(String imgColumUrl) {
		this.imgColumUrl = imgColumUrl;
	}

	public final BooleanProperty showPdfProperty() {
		return this.showPdf;
	}
	

	public final boolean isShowPdf() {
		return this.showPdfProperty().get();
	}
	

	public final void setShowPdf(final boolean showPdf) {
		this.showPdfProperty().set(showPdf);
	}
	

	public final BooleanProperty showExcelProperty() {
		return this.showExcel;
	}
	

	public final boolean isShowExcel() {
		return this.showExcelProperty().get();
	}
	

	public final void setShowExcel(final boolean showExcel) {
		this.showExcelProperty().set(showExcel);
	}
	

	public final BooleanProperty showCsvProperty() {
		return this.showCsv;
	}
	

	public final boolean isShowCsv() {
		return this.showCsvProperty().get();
	}
	

	public final void setShowCsv(final boolean showCsv) {
		this.showCsvProperty().set(showCsv);
	}
	

	public final BooleanProperty showTxtProperty() {
		return this.showTxt;
	}
	

	public final boolean isShowTxt() {
		return this.showTxtProperty().get();
	}
	

	public final void setShowTxt(final boolean showTxt) {
		this.showTxtProperty().set(showTxt);
	}
	
	

}

