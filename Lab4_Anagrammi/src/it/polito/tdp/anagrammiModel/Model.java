package it.polito.tdp.anagrammiModel;

import java.util.ArrayList;
import java.util.List;
import it.polito.tdp.anagrammiDAO.AnagrammaDAO;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class Model {

	
	
	
	private AnagrammaDAO d=new AnagrammaDAO();
	
	private List<Text> text=new ArrayList<Text>();
	
    public void permuta(String inizio,String fine){
		
		if(fine.length()<=1){
			
			
			String stringa = inizio+fine;
			Text t=new Text(stringa+"\n");
			boolean check=d.findWord(stringa);
			if(check==false)
				t.setFill(Color.RED);
			text.add(t);
			
		}
		else{
			
			for(int i=0; i<fine.length();i++){
				String nuova=fine.substring(0, i)+fine.substring(i+1);
				this.permuta(inizio+fine.charAt(i), nuova);
			}
			
		}
		
	}
    
   
    public void clearPerm(){
    	this.text.clear();
    }
    
    public List<Text> getTesti(){
    	return this.text;
    }
    
    
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
