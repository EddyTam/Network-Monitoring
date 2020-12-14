package DatabaseMethod;
import java.sql.*;
public class FunctionOfSql {
	public static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";   // JAVA DATABASE Connector
	private static String DB_URL = "jdbc:mysql://"; // link of Database
	private static String username = "";
	private static String pass = "";
	public static Connection conn = null;
	public static Statement stmt = null;
	private final String device_sql ="CREATE TABLE `Device`("+
			"`IPaddress` varchar(20) NOT NULL, "+
			"`DeviceName` varchar(255) NOT NULL, "+
			"`DeviceType` varchar(20) DEFAULT NULL, "+
			"`DeviceDescription` varchar(255) DEFAULT NULL, "+
			"`OS` varchar(255) NOT NULL, "+
			"PRIMARY KEY (`IPaddress`,`DeviceName`), "+
			"UNIQUE KEY `IPaddress` (`IPaddress`))"+
			" ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	private final String linux_record_sql ="CREATE TABLE `Linux_Record`("+
			"`RecordID` int(255) NOT NULL AUTO_INCREMENT, "+
			"`ipaddress` varchar(20) NOT NULL, "+
			"`DeviceName` varchar(255) NOT NULL, "+
			"`systemuptime` varchar(255) NOT NULL, "+
			"`total_ram` varchar(255) NOT NULL, "+
			"`used_ram` varchar(255) NOT NULL, "+
			"`ram_usage` varchar(255) NOT NULL, "+
			"`cpu_usage` varchar(255) NOT NULL, "+
			"`total_hdd_space` varchar(255) NOT NULL, "+
			"`network_in_traffic` varchar(255) NOT NULL,"+
			"`network_out_traffic` varchar(255) NOT NULL,"+
			"`avail_hdd_space` varchar(255) NOT NULL, "+
			"`recordtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, "+
			"PRIMARY KEY (`RecordID`), "+
			"KEY `FKLinux_Reco891163` (`ipaddress`,`DeviceName`), "+
			"CONSTRAINT `FKLinux_Reco891163` FOREIGN KEY (`ipaddress`, `DeviceName`) REFERENCES `Device` (`IPaddress`, `DeviceName`))"+
			"ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	private final String windows_record_sql ="CREATE TABLE `Windows_Record` ("+
			"`RecordID` varchar(255) NOT NULL,"+
			"`IPaddress` varchar(20) NOT NULL,"+
			"`DeviceName` varchar(255) NOT NULL,"+
			"`systemuptime` varchar(255) NOT NULL,"+
			"`total_ram` varchar(255) NOT NULL,"+
			"`used_ram` varchar(255) NOT NULL,"+
			"`ram_usage` varchar(255) NOT NULL,"+
			"`cpu_usage` int(10) NOT NULL,"+
			"`total_hdd_space` int(10) NOT NULL,"+
			"`used_hdd_space` varchar(255) NOT NULL,"+
			"`recordtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"+
			"PRIMARY KEY (`RecordID`),"+
			"KEY `FKWindows_Re127457` (`IPaddress`,`DeviceName`),"+
			"CONSTRAINT `FKWindows_Re127457` FOREIGN KEY (`IPaddress`, `DeviceName`) REFERENCES `Device` (`IPaddress`, `DeviceName`)"+
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	public String getWindows_record_sql() {
		return windows_record_sql;
	}
	private final String router_record_sql="CREATE TABLE `Router_record` ("+
			"`RecordID` int(255) NOT NULL AUTO_INCREMENT,"+
			"`IPaddress` varchar(20) NOT NULL,"+
			"`DeviceName` varchar(255) NOT NULL,"+
			"`Total_Interface` varchar(255) NOT NULL,"+
			"`Income_Traffic_Mbps` varchar(255) NOT NULL,"+
			"`Outcome_Traffic_Mbps` varchar(255) NOT NULL,"+
			"`Income_Traffic_pps` varchar(255) NOT NULL,"+
			"`Outcome_Traffic_pps` varchar(255) NOT NULL,"+
			"`cpu_usage` varchar(255) NOT NULL,"+
			"`recordtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"+
			"PRIMARY KEY (`RecordID`),"+
			"KEY `FKRouter_rec233861` (`IPaddress`,`DeviceName`),"+
			"CONSTRAINT `FKRouter_rec233861` FOREIGN KEY (`IPaddress`, `DeviceName`) REFERENCES `Device` (`IPaddress`, `DeviceName`)"+
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	public String getRouter_record_sql() {
		return router_record_sql;
	}
	
	private final String alert_logging_sql="CREATE TABLE `Alert_logging` ("+
			"`recordID` int(255) NOT NULL AUTO_INCREMENT,"+
			"`IPaddress` varchar(20) NOT NULL,"+
			"`DeviceName` varchar(255) NOT NULL,"+
			"`alert_type` varchar(255) NOT NULL,"+
			"`recordtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,"+
			"PRIMARY KEY (`recordID`),"+
			"KEY `FKAlert_logg500589` (`DeviceIPaddress`,`DeviceDeviceName`),"+
			"CONSTRAINT `FKAlert_logg500589` FOREIGN KEY (`DeviceIPaddress`, `DeviceDeviceName`) REFERENCES `Device` (`IPaddress`, `DeviceName`)"+
			") ENGINE=InnoDB DEFAULT CHARSET=latin1;";
	
	public String getAlert_logging_sql() {
		return alert_logging_sql;
	}
	
	private String drop_record ="DROP TABLE IF EXISTS `Device`;";
	
	private String drop_device ="DROP TABLE IF EXISTS `NetworkDevice`;";
	
	private String drop_record_fk ="alter table Record drop foreign key FKRecord20988";
	
	public String getDrop_record_fk() {
		return drop_record_fk;
	}
	public String getDrop_record() {
		return drop_record;
	}
	public String getDrop_device() {
		return drop_device;
	}
	public String getDevice_sql() {
		return device_sql;
	}
	public String getRecord_sql() {
		return linux_record_sql;
	}
	public static void setUser(String user,String password){
		username = user;
		pass = password;
	}
	public void setUsername(String username) {
		FunctionOfSql.username = username;
	}
	public void setPass(String pass) {
		FunctionOfSql.pass = pass;
	}
	public static String getDB_URL(){
		return DB_URL;
	}
	public static String getUsername(){
		return username;
	}
	public static String getPassword(){
		return pass;
	}
	public static String getPass() {
		return pass;
	}
	public static void setDB_URL(String dB_URL) {
		DB_URL = "jdbc:mysql://"+dB_URL;
	}
	public static void setDB_Name(String name){
		DB_URL = DB_URL + "/"+name;
	}

}
