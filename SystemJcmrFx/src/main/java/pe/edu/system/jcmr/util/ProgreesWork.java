package pe.edu.system.jcmr.util;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ProgressIndicator;

public abstract class ProgreesWork {

	public void initProgrees(ProgressIndicator progressBarInit){

	System.out.println("hola mundo work");
		final ServiceExample serviceExample = new ServiceExample();
		progressBarInit.visibleProperty().bind(serviceExample.runningProperty());

		serviceExample.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent workerStateEvent) {

			
				progressSucceeded();
				
				

			}
		});

		serviceExample.setOnFailed(new EventHandler<WorkerStateEvent>() {
			@Override
			public void handle(WorkerStateEvent workerStateEvent) {

			}

		});

		serviceExample.restart();
	}

    
    
    public class ServiceExample  extends Service<String>{
    	protected Task<String> createTask() {
    	    return new Task<String>() {
    	        @Override
    	        protected String call() throws Exception {
    	        	methodProggres(null);
    	        	Thread.sleep(5000);
    	        
    	        	System.out.println("hola mundo");
    	            return "succefully";
    	        }
    	    };
    }
   
   }
   
    public abstract void methodProggres(String execute);
    
    public abstract  String progressSucceeded();
    
    public String progressOnFailed(){
    	return "falid";
    }
    

}
