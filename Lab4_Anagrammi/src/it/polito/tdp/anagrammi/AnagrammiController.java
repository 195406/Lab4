package it.polito.tdp.anagrammi;

import java.net.URL;

import java.util.LinkedList;
import java.util.List;

import java.util.ResourceBundle;


import it.polito.tdp.anagrammiModel.Model;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AnagrammiController {
	
	private Model model;
	List<Text> testi=new LinkedList<Text>();
	List<String> stringhe=new LinkedList<String>();
	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;
    
    @FXML
    private TextArea resultArea;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextFlow txtOutput;

    @FXML
    private Button btnReset;
    
    @FXML
    private Label lblTime;
    
    @FXML
    private ProgressBar pBar;
    
    @FXML
    void doCalcola(ActionEvent event) {
    	
    	txtOutput.getChildren().clear();
    	model.clearPerm();
    	testi.clear();
    	String parola=txtInput.getText();
    	
    	
    	
    	Task<List<Text>> task=new Task<List<Text>>(){

			@Override
			protected List<Text> call() throws Exception {
				Model m=new Model();
				long t0=System.currentTimeMillis();
				updateProgress(-1,-1);
				updateMessage("cacolo permutazioni e controllo in dizionario, attendere");
				m.permuta("", parola);
				updateProgress(1,1);
		    	
		    	long t1=System.currentTimeMillis();
		    	double tot=(double)((t1-t0)*0.001);
		    	updateMessage("ricerca conclusa in "+tot+" secondi");
				return m.getTesti();
			}
    		
    	};
    	
    	task.setOnSucceeded(new EventHandler<WorkerStateEvent>(){

			@Override
			public void handle(WorkerStateEvent event) {
				
				
				long t0=System.currentTimeMillis();
				
				for(Text s: task.getValue()){
					Text t=new Text(task.getValue().indexOf(s)+")"+s);
				    txtOutput.getChildren().add(s);
				    System.out.println(t.toString());
				}
				long t1=System.currentTimeMillis();
				double tot=(double)(t1-t0)*0.001;
				System.out.println("caricamento in "+tot+" secondi");
				
				
			}
			
    		
    	});
    	
    	
    	pBar.progressProperty().bind(task.progressProperty());
    	lblTime.textProperty().bind(task.messageProperty());
    	
    	
    	Thread th=new Thread(task);
    	th.setDaemon(true);
    	th.start();
    	
   }

    @FXML
    void doReset(ActionEvent event) {
    	txtInput.clear();
    	txtOutput.getChildren().clear();
    	this.testi.clear();
    	pBar.getProperties().clear();
    	

    }
    
    public void setModel(Model model){
    	this.model=model;
    }
    
    

    @FXML
    void initialize() {
        assert txtInput != null : "fx:id=\"txtInput\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert txtOutput != null : "fx:id=\"txtOutput\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert btnReset != null : "fx:id=\"btnReset\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert lblTime != null : "fx:id=\"lblTime\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert pBar != null : "fx:id=\"pBar\" was not injected: check your FXML file 'Anagrammi.fxml'.";
        assert resultArea != null : "fx:id=\"resultArea\" was not injected: check your FXML file 'Anagrammi.fxml'.";


    }
}