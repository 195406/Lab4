package it.polito.tdp.anagrammiModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.anagrammiDAO.AnagrammaDAO;

public class Model {

	private List<String> permutazioni=new ArrayList<String>();
	public Map<String,Boolean> controllo=new HashMap<String,Boolean>();
	private AnagrammaDAO d=new AnagrammaDAO();
	
    public void permuta(String inizio,String fine){
		
		if(fine.length()<=1){
			permutazioni.add(inizio+fine);
		    controllo.put(inizio+fine, d.findWord(inizio+fine));
		}
		else{
			
			for(int i=0; i<fine.length();i++){
				String nuova=fine.substring(0, i)+fine.substring(i+1);
				this.permuta(inizio+fine.charAt(i), nuova);
			}
			
		}
		
	}
    public void clearPerm(){
    	this.permutazioni.clear();
    }
    public List<String> getPerm(){
    	return permutazioni;
    }
	
	public Map<String,Boolean> getControllo(){
		return controllo;
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
