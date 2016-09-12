package pe.edu.system.jcmr.util;

import java.util.List;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import pe.edu.system.jcmr.entity.BaseEntity;

public class BaseMatenimientoUtil extends UtilidadesFx{
	@SuppressWarnings("rawtypes")
	TableView tableView=null;
    Pagination pagination=null;
    List<?> list =null;
	private Integer rowPage;
	private ProgressIndicator progreesIndicator;


    
    protected void createPaginator(TableView<?> tableView,Pagination pagination,List<?> list,ProgressIndicator progreesIndicator) {
            this.tableView =tableView;
            this.pagination = pagination;
            this.list = list;
            this.progreesIndicator = progreesIndicator;
            pagination.setPageCount((this.list.size() /  getRowPage() + 1));
            pagination.setCurrentPageIndex(0);

            if(progreesIndicator==null){
              pagination.setPageFactory(this::createPage);
            }else{
            	this.progreesIndicator.setVisible(true);
                pagination.setPageFactory(this::createPageIndicator);
            }
         
      
     }

    @SuppressWarnings("unchecked")
	protected Node createPage(int pageIndex) {
        int fromIndex = pageIndex * getRowPage();
        int toIndex = Math.min(fromIndex +  getRowPage(), this.list.size());
        tableView.setItems(FXCollections.observableList(this.list.subList(fromIndex,toIndex)));
        return  new AnchorPane();
    }

	@SuppressWarnings("unchecked")
	protected Node createPageIndicator(int pageIndex) {
		Node oldPlaceHolder = tableView.getPlaceholder();
		tableView.setPlaceholder(progreesIndicator);
		Task<ObservableList<?>> task = new Task<ObservableList<?>>() {

			@Override
			protected ObservableList<?> call() throws Exception {

				Thread.sleep(20);
				int fromIndex = pageIndex * getRowPage();
				int toIndex = Math.min(fromIndex + getRowPage(), list.size());
				return (FXCollections.observableArrayList(list.subList(fromIndex, toIndex)));

			}
		};

		progreesIndicator.progressProperty().bind(task.progressProperty());
		task.setOnSucceeded(ev -> {
			tableView.setItems(task.getValue());
			tableView.setPlaceholder(oldPlaceHolder);
			progreesIndicator.setVisible(false);
		});

		new Thread(task).start();
		return new AnchorPane();

	}

	protected void createRowPage(ComboBox<BaseEntity> rowPage) {
		rowPage.getSelectionModel().select(0);
		setRowPage(10);

		rowPage.valueProperty().addListener(new ChangeListener<BaseEntity>() {
			@Override
			public void changed(ObservableValue<? extends BaseEntity> observable, BaseEntity oldValue,
					BaseEntity newValue) {

				setRowPage(Integer.parseInt(newValue.getDescripcion()));
				createPaginator(tableView, pagination, list, progreesIndicator);
			}

		});
	}
	
	
	
	public Integer getRowPage() {

		if (rowPage == null) {
			rowPage = 10;
		}
		return rowPage;
	}

	public void setRowPage(Integer rowPage) {
		this.rowPage = rowPage;
	}


	



	

	

	
}
