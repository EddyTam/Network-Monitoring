package DatabaseMethod;
import java.sql.*;
public class InsertRecord extends FunctionOfSql{
	public void inputRecordToLinuxRecord(String[] array){
		// receive the array store to a array
		String [] a = array; 
		try{
			 //driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      // create a SQL statement for insert data to database
		      String sql = "INSERT INTO linux_record(ipaddress,devicename,systemuptime,total_ram,used_ram,ram_usage,cpu_usage,total_hdd_space,avail_hdd_space, network_in_traffic, network_out_traffic) " +
		                   "VALUES ('"+a[0]+"','"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"','"+a[8]+"','"+a[9]+"','"+a[10]+"')";
		      stmt.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		    	  
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   } 
	}
	public void inputRecordToDeviceTable(String ip , String devicename,String os){
		try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      String sql = "INSERT INTO device(ipaddress,devicename,os)values('"+ip+"','"+devicename+"','"+os+"');";
		      stmt.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		    	  
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   } 
	}
	public void inputRecordToWindowRecord(String[] array){
		String [] a = array; 
		try{
			 //driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      // create a SQL statement for insert data to database
		      String sql = "INSERT INTO windows_record(ipaddress,devicename,systemuptime,total_ram,used_ram,ram_usage,cpu_usage,total_hdd_space,used_hdd_space) " +
		                   "VALUES ('"+a[0]+"','"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"','"+a[8]+"')";
		      stmt.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   } 
	}
	public void inputRecordToRouterRecord(String[] array){
		String [] a = array; 
		try{
			 //driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      // create a SQL statement for insert data to database
		      String sql = "INSERT INTO router_record(ipaddress,devicename,total_interface,Income_Traffic_Mbps,Outcome_Traffic_Mbps,Income_Traffic_pps,Outcome_Traffic_pps,cpu_usage) " +
		                   "VALUES ('"+a[0]+"','"+a[1]+"','"+a[2]+"','"+a[3]+"','"+a[4]+"','"+a[5]+"','"+a[6]+"','"+a[7]+"')";
		      stmt.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   } 
	}
	public void inputRecordToAlretLoggingRecord(String[] array){
		String [] a = array; 
		try{
			 //driver for connect MySQL Server
		      Class.forName("com.mysql.jdbc.Driver");
		      // input the database address , username and password
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      // create a SQL statement for insert data to database
		      String sql = "INSERT INTO alert_logging(deviceipaddress,devicedevicename,alert_type) " +
		                   "VALUES ('"+a[0]+"','"+a[1]+"','"+a[2]+"')";
		      stmt.executeUpdate(sql);
		   }catch(SQLException se){
		      se.printStackTrace();
		   }catch(Exception e){
		      e.printStackTrace();
		   }finally{
		      try{
		         if(stmt!=null)
		            conn.close();
		      }catch(SQLException se){
		      }
		      try{
		         if(conn!=null)
		            conn.close();
		      }catch(SQLException se){
		         se.printStackTrace();
		      }
		   } 
	}
}

