package DatabaseMethod;

import java.sql.*;


public class OutputRecord extends FunctionOfSql{
	public OutputLinuxStorage outputRecordFromLinuxRecordByIp(String ip){
		OutputLinuxStorage o = new OutputLinuxStorage();
		int i = 0;
		ResultSet rs = null;
		   String sql = "Select * from linux_record where ipaddress ='"+ip+"';";
			try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		    	do{
		    	 o.inputRecordId(rs.getString("Recordid"), i);
		    	 o.inputIpaddress(rs.getString("ipaddress"), i);
		    	 o.inputDevName(rs.getString("devicename"), i);
		    	 o.inputSysTime(rs.getString("systemuptime"), i);
		    	 o.inputTotalRam(rs.getString("total_ram"), i);
		    	 o.inputUsedRam(rs.getString("used_ram"), i);
		    	 o.inputRamUsage(rs.getString("ram_usage"),i);
		    	 o.inputCpuUsage(rs.getString("cpu_usage"), i);
		    	 o.inputTotalHddSpace(rs.getString("total_hdd_space"), i);
		    	 o.inputAvailHddSpace(rs.getString("avail_hdd_space"), i);
		    	 o.inputNetwork_income_traffic(rs.getString("network_in_traffic"), i);
		    	 o.inputNetwork_outcome_traffic(rs.getString("network_out_traffic"), i);
		    	 o.inputRecordTime(rs.getString("recordtime"), i);
		    	 i++;
		      	}while(rs.next());
		      rs.close();
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
		return o;

	}
	public OutputWindowsStorage outputRecordFromWindowsRecordByIp(String ip){
		OutputWindowsStorage o = new OutputWindowsStorage();
		int i = 0;
		ResultSet rs = null;
		   String sql = "Select * from windows_record where ipaddress ='"+ip+"';";
			try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		    	do{
		    	 o.inputRecordId(rs.getString("Recordid"), i);
		    	 o.inputIpaddress(rs.getString("ipaddress"), i);
		    	 o.inputDevName(rs.getString("devicename"), i);
		    	 o.inputSysTime(rs.getString("systemuptime"), i);
		    	 o.inputTotalRam(rs.getString("total_ram"), i);
		    	 o.inputUsedRam(rs.getString("used_ram"), i);
		    	 o.inputRamUsage(rs.getString("ram_usage"),i);
		    	 o.inputCpuUsage(rs.getString("cpu_usage"), i);
		    	 o.inputTotalHddSpace(rs.getString("total_hdd_space"), i);
		    	 o.inputUsedHddSpace(rs.getString("used_hdd_space"), i);
		    	 o.inputRecordTime(rs.getString("recordtime"), i);
		    	 i++;
		      	}while(rs.next());
		      rs.close();
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
		return o;

	}
	
	public OutputRouterStorage outputRecordFromRouterRecordByIp(String ip){
		OutputRouterStorage o = new OutputRouterStorage();
		int i = 0;
		ResultSet rs = null;
		   String sql = "Select * from windows_record where ipaddress ='"+ip+"';";
			try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		    	do{
		    	 o.inputRecordId(rs.getString("Recordid"), i);
		    	 o.inputIpaddress(rs.getString("ipaddress"), i);
		    	 o.inputDevName(rs.getString("devicename"), i);
		    	 o.inputTotal_interface(rs.getString("total_interface"), i);
		    	 o.inputIncome_traffic_mbps(rs.getString("income_traffic_mbps"), i);
		    	 o.inputOutcome_traffic_mbps(rs.getString("outcome_traffic_mbps"), i);
		    	 o.inputIncome_traffic_pps(rs.getString("income_traffic_pps"), i);
		    	 o.inputOutcome_traffic_pps(rs.getString("outcome_traffic_pps"), i);
		    	 o.inputCpuUsage(rs.getString("cpu_usage"), i);
		    	 o.inputRecordTime(rs.getString("recordtime"), i);
		    	 i++;
		      	}while(rs.next());
		      rs.close();
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
		return o;

	}
	
	public String[] outputRecordFromDeviceForSelection(){ 
			FindTableInfo f = new FindTableInfo();
			String[] b = new String[f.countRecord("Device")];
			ResultSet rs = null;
			int j = 0;
			   String sql = "Select * from device ;";
				try{
			      Class.forName("com.mysql.jdbc.Driver");
			      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
			      stmt = conn.createStatement();
			      rs = stmt.executeQuery(sql);
			      rs.first();
			    	do{
			    			b[j] = rs.getString("IPAddress");
			    			j++;
			      	}while(rs.next());
			      rs.close();

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
			return b;
	}
	public String outputOSFromDevice(String ip){
		String ident = "";
		String result ="";
		ResultSet rs = null;
		int j = 0;
		   String sql = "select os from device where ipaddress ='"+ip+"';";
			try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		      ident = rs.getString("os");
		      rs.close();
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
		return ident;
	}
	public String ouputAvgFromRecord(String date , String ip , String type){
		String avg = Integer.toString(0);
		ResultSet rs = null;
		String sql = null;
		if(type.equals("Ram Load")){
			sql = "Select avg(ram_usage) from linux_record where recordtime like '"+date+"%' and ipaddress ='"+ip+"';";
		}else{
			if(type.equals("CPU Load")){
				sql = "select avg(cpu_usage) from linux_record where recordtime like '"+date+"%' and ipaddress ='"+ip+"';";
			}
		}
		try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		      do{
		    	  if(type.equals("Ram Load")){
		    		  avg = rs.getString("avg(ram_usage)");
		    	  }else{
		    		  avg = rs.getString("avg(cpu_usage)");
		    	  }
		      }while(rs.next());
		      rs.close();
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
		return avg;
	}
	public OutputAlertStorage outputRecordFromAlertLogging(String ip){
		OutputAlertStorage o = new OutputAlertStorage();
		int i = 0;
		ResultSet rs = null;
		   String sql = "Select * from alert_logging where deviceipaddress ='"+ip+"';";
			try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		    	do{
		    	 o.inputRecordId(rs.getString("Recordid"), i);
		    	 o.inputIpaddress(rs.getString("deviceipaddress"), i);
		    	 o.inputDevName(rs.getString("devicedevicename"), i);
		    	 o.inputAlertType(rs.getString("alert_type"), i);
		    	 o.inputRecordTime(rs.getString("recordtime"), i);
		    	 i++;
		      	}while(rs.next());
		      rs.close();
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
		return o;

	}
	public String[] outputRecordFromAlertLogForSelection(){ 
		FindTableInfo f = new FindTableInfo();
		String[] b = new String[f.countRecord("alert_logging")];
		ResultSet rs = null;
		int j = 0;
		   String sql = "Select * from device ;";
			try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		    	do{
		    			b[j] = rs.getString("DeviceIPAddress");
		    			j++;
		      	}while(rs.next());
		      rs.close();

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
		return b;
	}
	public OutputAlertStorage outputRecordFromAlertLoggingForAll(){
		OutputAlertStorage o = new OutputAlertStorage();
		int i = 0;
		ResultSet rs = null;
		   String sql = "Select * from alert_logging ;";
			try{
		      Class.forName("com.mysql.jdbc.Driver");
		      conn = DriverManager.getConnection(getDB_URL(), getUsername(), getPassword());
		      stmt = conn.createStatement();
		      rs = stmt.executeQuery(sql);
		      rs.first();
		    	do{
		    	 o.inputRecordId(rs.getString("Recordid"), i);
		    	 o.inputIpaddress(rs.getString("deviceipaddress"), i);
		    	 o.inputDevName(rs.getString("devicedevicename"), i);
		    	 o.inputAlertType(rs.getString("alert_type"), i);
		    	 o.inputRecordTime(rs.getString("recordtime"), i);
		    	 i++;
		      	}while(rs.next());
		      rs.close();
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
		return o;

	}
}

