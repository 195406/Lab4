package it.polito.tdp.anagrammi;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.anagrammiDAO.AnagrammaDAO;
import it.polito.tdp.anagrammiModel.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class AnagrammiController {
	
	private Model model;
	List<Text> testi=new LinkedList<Text>();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtInput;

    @FXML
    private Button btnCalcola;

    @FXML
    private TextFlow txtOutput;

    @FXML
    private Button btnReset;
    
    @FXML
    private Label lblTime;

    @FXML
    void doCalcola(ActionEvent event) {
    	
    	long t0=System.currentTimeMillis();
    	
    	txtOutput.getChildren().clear();
    	model.clearPerm();
    	testi.clear();
    	
    	
    	
    	AnagrammaDAO d=new AnagrammaDAO();
    	String parola=txtInput.getText();
    	
    	if(d.findWord(parola)==false){
    		Text t=new Text("PAROLA INESISTENTE!");
    		txtOutput.getChildren().add(t);
    		return;
    	}
    	
    	model.permuta("", parola);
    	
    	List<String> permutazioni=new ArrayList<String>(model.getPerm());
    	for(String s : permutazioni){
    		System.out.println(s);
    	}
    	
    	for(String s:permutazioni){
    		if(d.findWord(s)==false){
    		
    		Text t=new Text(permutazioni.indexOf(s)+1+") "+s+"\n");
    		t.setFill(Color.RED);
    		
    		
    		testi.add(t);
    		}else{
    			
    			Text t=new Text(permutazioni.indexOf(s)+1+") "+s+"\n");
    			t.setFill(Color.GREEN);
    			
    			testi.add(t);
    		}
    	}
    	d.closeConn(d.getConn());
    
    	long t1=System.currentTimeMillis();
    	double tot=(double)((t1-t0)*0.001);
    	lblTime.setText(""+tot+" secondi");
    	txtOutput.getChildren().addAll(testi);
    	
    	

    }

    @FXML
    void doReset(ActionEvent event) {
    	txtInput.clear();
    	txtOutput.getChildren().clear();
    	this.testi.clear();

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

    }
}