package DatabaseMethod;
import java.sql.*;
public class DbCreation extends FunctionOfSql{
	public boolean DbCreate(String name){
		boolean check = true;
		try{
			 //driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      // create a SQL statement for insert data to database
		      stmt.executeUpdate("create database "+name+";");
		   }catch(SQLException se){
		      se.printStackTrace();
		      check = false;
		   }catch(Exception e){
		      e.printStackTrace();
		      check = false;
		   }finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		    	  check = false;
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		         check = false;
		      }
		   } 
		return check;
	}
	
}
