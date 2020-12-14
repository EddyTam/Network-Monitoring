package DatabaseMethod;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DeleteRecord extends FunctionOfSql{
	public boolean deleteRecordFromLinuxRecord(String ip){
		boolean check = true;
		try{
			//driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		   // create a SQL statement for insert data to database
		      String sql_1 ="delete from linux_record where ipaddress =\'"+ip+"\';";
		      stmt.executeUpdate(sql_1);
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
	public boolean deleteRecordFromWindowRecord(String ip){
		boolean check = true;
		try{
			//driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		   // create a SQL statement for insert data to database
		      String sql_1 ="delete from windows_record where ipaddress =\'"+ip+"\';";
		      stmt.executeUpdate(sql_1);
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
	public boolean deleteRecordFromRouterRecord(String ip){
		boolean check = true;
		try{
			//driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		   // create a SQL statement for insert data to database
		      String sql_1 ="delete from router_record where ipaddress =\'"+ip+"\';";
		      stmt.executeUpdate(sql_1);
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
	public boolean deleteRecordFromAlertLogging(String ip){
		boolean check = true;
		try{
			//driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		   // create a SQL statement for insert data to database
		      String sql_1 ="delete from alert_logging where ipaddress =\'"+ip+"\';";
		      stmt.executeUpdate(sql_1);
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
