package DatabaseMethod;

public class OutputLinuxStorage {
	private  static String[] recordID = new String[findRecord()];
	private  static String[] ipaddress = new String[findRecord()];
	private  static String[] devName = new String[findRecord()];
	private  static String[] systemUptime = new String[findRecord()];
	private  static String[] total_ram = new String[findRecord()];
	private  static String[] used_ram = new String[findRecord()];
	private  static String[] ram_usage = new String[findRecord()];
	private  static String[] cpu_usage = new String[findRecord()];
	private  static String[] total_hdd_space = new String[findRecord()];
	private  static String[] avail_hdd_space = new String[findRecord()];
	private static String[] network_income_traffic =new String[findRecord()];
	private static String[] network_outcome_traffic = new String[findRecord()];
	private  static String[] recordtime = new String[findRecord()];	
	public static int findRecord(){
		FindTableInfo f = new FindTableInfo();
		int number = f.countRecord("linux_record");
		return number;
	}
	public String[] outputNetwork_income_traffic(){
		return network_income_traffic;
	}
	public void inputNetwork_income_traffic(String netInTraf,int i){
		network_income_traffic[i] = netInTraf;
	}
	public String[] outputNetwork_outcome_traffic(){
		return network_outcome_traffic;
	}
	public void inputNetwork_outcome_traffic(String netOutTraf,int i){
		network_outcome_traffic[i] =netOutTraf;
	}
	public String[] outputRamUsage() {
		return ram_usage;
	}
	public void inputRamUsage(String ram_use , int i) {
		ram_usage[i] = ram_use;
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
	public void inputSysTime(String sysTime,int i){
		systemUptime[i] = sysTime;
	}
	public String[] outputSysTime(){
		return systemUptime;
	}
	public void inputTotalRam(String totalRam,int i){
		total_ram[i] = totalRam;
	}
	public String[] outputTotalRam(){
		return total_ram;
	}
	public void inputUsedRam(String usedRam,int i){
		used_ram[i] = usedRam;
	}
	public String[] outputUsedRam(){
		return used_ram;
	}
	public void inputCpuUsage(String cpuUsage,int i){
		cpu_usage[i] = cpuUsage;
	}
	public String[] outputCpuUsage(){
		return cpu_usage;
	}
	public void inputTotalHddSpace(String totalHddSpace , int i){
		total_hdd_space[i] = totalHddSpace;
	}
	public String[] outputTotalHddSpace(){
		return total_hdd_space;
	}
	public void inputAvailHddSpace(String availHddSpace,int i){
		avail_hdd_space[i] = availHddSpace;
	}
	public String[] outputAvailHdd(){
		return avail_hdd_space;
	}
	public void inputRecordTime(String time,int i){
		recordtime[i] = time;
	}
	public String[] outputRecordTime(){
		return recordtime;
	}
}
