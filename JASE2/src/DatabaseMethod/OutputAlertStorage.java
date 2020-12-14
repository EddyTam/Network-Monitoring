package DatabaseMethod;

public class OutputAlertStorage {
 	private  static String[] recordID= new String[findRecord()];
	private  static String[] ipaddress = new String[findRecord()];
	private  static String[] devName = new String[findRecord()];
	private static String[] alert_type = new String[findRecord()];
	private  static String[] recordtime = new String[findRecord()];	
	public static int findRecord(){
		FindTableInfo f = new FindTableInfo();
		int number = f.countRecord("alert_logging");
		return number;
	}
	public void inputRecordId(String rec,int i){
		recordID[i] = rec;
	}
	public String[] outputRecordId(){
		return recordID;
	}
	public void inputIpaddress(String ip,int i){
		ipaddress[i] = ip;
	}
	public String[] outputIpaddress(){
		return ipaddress;
	}
	public void inputDevName(String deviceName,int i){
		devName[i] = deviceName; 
	}
	public String[] outputDevName(){
		return devName;
	}
	public void inputRecordTime(String time,int i){
		recordtime[i] = time;
	}
	public String[] outputRecordTime(){
		return recordtime;
	}
	public void inputAlertType(String alert,int i){
		alert_type[i]=alert;
	}
	public String[] outputAlertType(){
		return alert_type;
	}
}
