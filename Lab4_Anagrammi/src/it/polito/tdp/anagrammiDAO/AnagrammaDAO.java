package it.polito.tdp.anagrammiDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AnagrammaDAO {
	
	private Connection conn=DBConnect.getConnection();
	
	public boolean findWord(String s){
		
		boolean trovata=false;
		int contatore=0;
		final String sql="select * from parola where nome=?";
		
		//Connection conn=DBConnect.getConnection();
		try {
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, s);
			ResultSet res=st.executeQuery();
			while(res.next()){
				contatore++;
			}
			if(contatore==0){
				trovata=false;
			}else{
				trovata=true;
			}
			st.close();
			//conn.close();
			return trovata;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new RuntimeException(e);
		}		
		
		
	}
	
	public void closeConn(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConn(){
		return this.conn;
	}

}
