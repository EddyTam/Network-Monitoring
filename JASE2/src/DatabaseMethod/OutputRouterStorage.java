package DatabaseMethod;

public class OutputRouterStorage {
	private  static String[] recordID = new String[findRecord()];
	private  static String[] ipaddress = new String[findRecord()];
	private  static String[] devName = new String[findRecord()];
	private  static String[] total_interface = new String[findRecord()];
	private static String[] income_traffic_mbps =new String[findRecord()];
	private static String[] outcome_traffic_mbps = new String[findRecord()];
	private  static String[] income_traffic_pps= new String[findRecord()];
	private static String[] outcome_traffic_pps = new String[findRecord()];
	private  static String[] cpu_usage = new String[findRecord()];
	private  static String[] recordtime = new String[findRecord()];	
	public static int findRecord(){
		FindTableInfo f = new FindTableInfo();
		int number = f.countRecord("record");
		return number;
	}

	public void inputRecordTime(String time,int i){
		recordtime[i] = time;
	}
	public String[] outputRecordTime(){
		return recordtime;
	}
	public void inputCpuUsage(String cpuUsage,int i){
		cpu_usage[i] = cpuUsage;
	}
	public String[] outputCpuUsage(){
		return cpu_usage;
	}
	public void inputOutcome_traffic_pps(String out, int i){
		outcome_traffic_pps[i] = out;
	}
	public String[] outputOutcome_traffic_pps(){
		return outcome_traffic_pps;
	}
	public void inputIncome_traffic_pps(String in,int i){
		income_traffic_pps[i] = in;
	}
	public String[] outputIncome_traffic_pps(){
		return income_traffic_pps;
	}
	public void inputOutcome_traffic_mbps(String out,int i){
		outcome_traffic_mbps[i]=out;
	}
	public String[] outputOutcome_traffic_mbps(){
		return outcome_traffic_mbps;
	}
	public void inputIncome_traffic_mbps(String in,int i){
		income_traffic_mbps[i] = in;
	}
	public String[] outputIncome_traffic_mbps(){
		return income_traffic_mbps;
	}
	public void inputRecordId(String rec,int i){
		recordID[i] = rec;
	}
	public String[] outputRecordId(){
		return recordID;
	}
	public void inputDevName(String deviceName,int i){
		devName[i] = deviceName; 
	}
	public String[] outputDevName(){
		return devName;
	}
	public void inputIpaddress(String ip,int i){
		ipaddress[i] = ip;
	}
	public String[] outputIpaddress(){
		return ipaddress;
	}
	public void inputTotal_interface(String total_interfaces,int i){
		total_interface[i] = total_interfaces;
	}
	public String[] outputTotal_interface(){
		return total_interface;
	}

}