package pe.edu.system.jcmr.component;

import java.io.IOException;

import com.jfoenix.transitions.CachedTransition;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Transition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


public class DialogConfirmJCMR extends StackPane{

	@FXML public Pane idPaneCenter;
	@FXML public StackPane stackPaneCenter;
    @FXML public Region idRegion;
 	@FXML public StackPane stackMain ;
 	@FXML public Label lblmensaje;
 	@FXML public Button btnConfirm;
	@FXML public Text lbltitle;
	@FXML public Button btnCancel;
	Transition animation = null;
	
	private double x = 0;
	private double y = 0;


	public static enum ModalDialogAnimation{CENTER, TOP, RIGHT, BOTTOM, LEFT};
 	
	public DialogConfirmJCMR() {
		String fxmlFile = "/META-INF/component/dialog/fxml/DialogConfirm.fxml";
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
		fxmlLoader.setRoot(this);
		fxmlLoader.setController(this);

		try {
		 fxmlLoader.load();

		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}

		idRegion.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			public void handle(MouseEvent evet) {

				closeDialog();
			}
		});
		

		hidden();

	}
 	
	
	public void closeDialog() {

		animation.setRate(-1);
		animation.play();
		animation.setOnFinished((e) -> {
			hidden();

		});
	}
	
	@FXML private void btnOnCancel(ActionEvent event){
		closeDialog();
	}
	

	
	public Transition setPositionAnimation(ModalDialogAnimation transitionType){
		Transition animation = null;
		if (stackPaneCenter != null) {
			switch (transitionType) {
			case LEFT:
				animation = new LeftTransition();
				break;
			case RIGHT:
				animation = new RightTransition();
				break;
			case TOP:
				animation = new TopTransition();
				break;
			case BOTTOM:
				// contentHolder.setTranslateY(offsetY);
				animation = new BottomTransition();
				break;
			default:
				// contentHolder.setScaleX(0);
				// contentHolder.setScaleY(0);
				animation = new CenterTransition();
				break;
			}
		}

		return animation;
	}


	private class RightTransition extends CachedTransition {
		public RightTransition() {
			super(stackPaneCenter, new Timeline(
					new KeyFrame(Duration.millis(10), 
							new KeyValue(stackPaneCenter.translateXProperty(), x ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.visibleProperty(), false ,Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(10), 
							new KeyValue(stackPaneCenter.visibleProperty(), true ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 0, Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(1000), 
							new KeyValue(stackPaneCenter.translateXProperty(), 0,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 1, Interpolator.EASE_BOTH)))
					);


			setCycleDuration(Duration.seconds(0.4));
			setDelay(Duration.seconds(0));
		}
	}
	
	private class LeftTransition extends CachedTransition {
		public LeftTransition() {
			super(stackPaneCenter, new Timeline(
					new KeyFrame(Duration.millis(10), 
							new KeyValue(stackPaneCenter.translateXProperty(), -x ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.visibleProperty(), false ,Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(10), 
							new KeyValue(stackPaneCenter.visibleProperty(), true ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 0, Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(1000), 
							new KeyValue(stackPaneCenter.translateXProperty(), 0,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 1, Interpolator.EASE_BOTH)))
					);

			setCycleDuration(Duration.seconds(0.4));
			setDelay(Duration.seconds(0));
		}
	}
	
	private class CenterTransition extends CachedTransition {
		public CenterTransition() {
			super(stackPaneCenter, new Timeline(
					new KeyFrame(Duration.ZERO, 
							new KeyValue(stackPaneCenter.scaleXProperty(), 0 ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.scaleYProperty(), 0 ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.visibleProperty(), false ,Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(10), 
							new KeyValue(stackPaneCenter.visibleProperty(), true ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 0,Interpolator.EASE_BOTH)
							),							
					new KeyFrame(Duration.millis(1000), 							
							new KeyValue(stackPaneCenter.scaleXProperty(), 1 ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.scaleYProperty(), 1 ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 1, Interpolator.EASE_BOTH)
							))
					);
			setCycleDuration(Duration.seconds(0.4));
			setDelay(Duration.seconds(0));
		}
	}
	
	private class BottomTransition extends CachedTransition {
		public BottomTransition() {
			super(stackPaneCenter, new Timeline(
					new KeyFrame(Duration.ZERO, 
							new KeyValue(stackPaneCenter.translateYProperty(), y ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.visibleProperty(), false ,Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(10), 
							new KeyValue(stackPaneCenter.visibleProperty(), true ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 0, Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(1000), 
							new KeyValue(stackPaneCenter.translateYProperty(), 0,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 1, Interpolator.EASE_BOTH)))
					);
			setCycleDuration(Duration.seconds(0.4));
			setDelay(Duration.seconds(0));
		}
	}
	
	private class TopTransition extends CachedTransition {
		public TopTransition() {
			super(stackPaneCenter, new Timeline(
					new KeyFrame(Duration.ZERO, 
							new KeyValue(stackPaneCenter.translateYProperty(), -y ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.visibleProperty(), false ,Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(10), 
							new KeyValue(stackPaneCenter.visibleProperty(), true ,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 0, Interpolator.EASE_BOTH)
							),
					new KeyFrame(Duration.millis(1000),
							new KeyValue(stackPaneCenter.translateYProperty(), 0,Interpolator.EASE_BOTH),
							new KeyValue(stackPaneCenter.opacityProperty(), 1, Interpolator.EASE_BOTH)))
					);
			setCycleDuration(Duration.seconds(0.4));
			setDelay(Duration.seconds(0));
		}
	}

	@FXML public void btnOnConfirn(ActionEvent event){
           closeDialog();
		System.out.println("dialog");
	}
	
	public void hidden(){
    
		stackMain.setVisible(false);
	    idRegion.setVisible(false);
	    stackPaneCenter.setVisible(false);

	}

	public  void show(ModalDialogAnimation modalAnimation){

		stackMain.setVisible(true);
		idRegion.setVisible(true);
		stackPaneCenter.setVisible(true);

		x = this.getParent().getBoundsInLocal().getWidth();
		y = this.getParent().getBoundsInLocal().getHeight();

		animation = setPositionAnimation(modalAnimation);

		animation.play();
         
	}

	 
	public void message(String mensaje){
		 lblmensaje.setText(mensaje);;
	}
	public void titleMessage(String titleMessage){
		 lbltitle.setText(titleMessage);
	}



	
}
