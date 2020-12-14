package DatabaseMethod;
import java.sql.*;
public class FindTableInfo extends FunctionOfSql{
	public String[] findFieldInfo(){
		String[] x = null;
		int i = 0;
		try{
			 Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      String sql = "describe OID;";
		      ResultSet rs = stmt.executeQuery(sql);
		      x = new String[countFieldInfo("MIB","OID")];
		      while(rs.next()){
		    	  x[i]= rs.getString("Field");
		    	  i++;
		      }
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
		return x;
	}
	public int countFieldInfo(String database,String table){
		int num = 0;
		try{
			 Class.forName("com.mysql.jdbc.Driver");
		      System.out.println("Connecting to a selected database...");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      String sql = "select count(*) from INFORMATION_SCHEMA.COLUMNS where table_schema ='"+database+"' AND table_name ='"+table+"';";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  num = Integer.parseInt(rs.getString("Count(*)"));
		      }
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
		return num;
	}
	public int countRecord(String tablename){
		int num = 0;
		String table = tablename;
		try{
			 Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      String sql = "select count(*) from "+table+";";
		      ResultSet rs = stmt.executeQuery(sql);
		      while(rs.next()){
		    	  num = Integer.parseInt(rs.getString("Count(*)"));
		      }
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
		return num;
	}

}