package DatabaseMethod;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;
public class OutputToText extends OutputLinuxStorage{

	public boolean createLinuxTextFile(OutputLinuxStorage p){
		boolean check = false;
		//get System day time
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		//create file with specific path
		File file = new File(timeStamp+".txt");
		//explode the object to each record array
		OutputLinuxStorage ols = p;
		String[] recordID = ols.outputRecordId();
		String[] ipaddress = ols.outputIpaddress();
		String[] devName = ols.outputDevName();
		String[] systemUptime = ols.outputSysTime();
		String[] total_ram = ols.outputTotalRam();
		String[] used_ram = ols.outputUsedRam();
		String[] ram_usage = ols.outputRamUsage();
		String[] cpu_usage = ols.outputCpuUsage();
		String[] total_hdd_space = ols.outputTotalHddSpace();
		String[] avail_hdd_space = ols.outputAvailHdd();
		String[] network_in_traffic = ols.outputNetwork_income_traffic();
		String[] network_out_traffic = ols.outputNetwork_outcome_traffic();
		String[] recordtime = ols.outputRecordTime();
		int i = 0;
		// write every record array to text file
		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("RecordID\tIP address\tDeviceName\tSystemUptime\tTotal Ram\tUsed Ram\tram_usage\tCPU Usage\tTotalHarddiskSpace\tAvail Harddisk Space\tNetwork Income Traffic\tNetwork Outcome Traffice\tRecord Time\n");
			while(i<p.outputAvailHdd().length){
				
				bw.write(recordID[i]+"\t"+ipaddress[i]+"\t"+devName[i]+"\t"+systemUptime[i]+"\t"+ 
						total_ram[i]+"\t"+used_ram[i]+"\t"+ram_usage[i]+"\t"+cpu_usage[i]+"\t"+ total_hdd_space[i] +"\t"+
						avail_hdd_space[i]+"\t"+network_in_traffic[i]+"\t"+network_out_traffic[i]+"\t"+recordtime[i]+"\n");
			i++;
			}
			bw.close();
			check = true;
		}catch(Exception e){
			System.out.println("Error");
		}
		return check;
	}
	
	public boolean createWindowsTextFile(OutputWindowsStorage p){
		boolean check = false;
		//get System day time
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		//create file with specific path
		File file = new File(timeStamp+".txt");
		//explode the object to each record array
		OutputWindowsStorage ows = p;
		String[] recordID = ows.outputRecordId();
		String[] ipaddress = ows.outputIpaddress();
		String[] devName = ows.outputDevName();
		String[] systemUptime = ows.outputSysTime();
		String[] total_ram = ows.outputTotalRam();
		String[] used_ram = ows.outputUsedRam();
		String[] ram_usage = ows.outputRamUsage();
		String[] cpu_usage = ows.outputCpuUsage();
		String[] total_hdd_space = ows.outputTotalHddSpace();
		String[] avail_hdd_space = ows.outputUsedHdd();
		String[] network_in_traffic = ows.outputNetwork_income_traffic();
		String[] network_out_traffic = ows.outputNetwork_outcome_traffic();
		String[] recordtime = ows.outputRecordTime();
		int i = 0;
		// write every record array to text file
		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("RecordID\tIP address\tDeviceName\tSystemUptime\tTotal Ram\tUsed Ram\tram_usage\tCPU Usage\tTotalHarddiskSpace\tAvail Harddisk Space\tNetwork Income Traffic\tNetwork Outcome Traffice\tRecord Time\n");
			while(i<p.outputUsedHdd().length){
				
				bw.write(recordID[i]+"\t"+ipaddress[i]+"\t"+devName[i]+"\t"+systemUptime[i]+"\t"+ 
						total_ram[i]+"\t"+used_ram[i]+"\t"+ram_usage[i]+"\t"+cpu_usage[i]+"\t"+ total_hdd_space[i] +"\t"+
						avail_hdd_space[i]+"\t"+network_in_traffic[i]+"\t"+network_out_traffic[i]+"\t"+recordtime[i]+"\n");
				i++;
			}
			bw.close();
			check = true;
		}catch(Exception e){
			System.out.println("Error");
		}
		return check;

	}
	public boolean createRouterTextFile(OutputRouterStorage p){
		boolean check = false;
		//get System day time
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		//create file with specific path
		File file = new File(timeStamp+".txt");
		//explode the object to each record array
		OutputRouterStorage ors = p;
		String[] recordID = ors.outputRecordId();
		String[] ipaddress = ors.outputIpaddress();
		String[] devName = ors.outputDevName();
		String[] total_interface = ors.outputTotal_interface();
		String[] income_traffic_mbps = ors.outputIncome_traffic_mbps();
		String[] outcome_traffic_mbps = ors.outputOutcome_traffic_mbps();
		String[] income_traffic_pps = ors.outputIncome_traffic_pps();
		String[] outcome_traffic_pps = ors.outputOutcome_traffic_pps();
		String[] cpu_usage = ors.outputCpuUsage();
		String[] recordtime = ors.outputRecordTime();
		int i = 0;
		// write every record array to text file
		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("RecordID\tIP address\tDeviceName\tSystemUptime\tTotal Ram\tUsed Ram\tram_usage\tCPU Usage\tTotalHarddiskSpace\tAvail Harddisk Space\tNetwork Income Traffic\tNetwork Outcome Traffice\tRecord Time\n");
			while(i<p.outputRecordId().length){
				
				bw.write(recordID[i]+"\t"+ipaddress[i]+"\t"+devName[i]+"\t"+total_interface[i]+"\t"+ 
						income_traffic_mbps[i]+"\t"+outcome_traffic_mbps[i]+"\t"+income_traffic_pps[i]+"\t"+outcome_traffic_pps[i]+"\t"+ cpu_usage[i] +"\t"+
						recordtime[i]+"\n");
				i++;
			}
			bw.close();
			check = true;
		}catch(Exception e){
			System.out.println("Error");
		}
		return check;

	}
	
	public boolean createAlertLogTextFile(OutputAlertStorage p){
		boolean check = false;
		//get System day time
		String timeStamp = new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime());
		//create file with specific path
		File file = new File(timeStamp+".txt");
		//explode the object to each record array
		OutputAlertStorage oas = p;
		String[] recordID = oas.outputRecordId();
		String[] ipaddress = oas.outputIpaddress();
		String[] devName = oas.outputDevName();	
		String[] alert_type = oas.outputAlertType();
		String[] recordtime = oas.outputRecordTime();
		int i = 0;
		// write every record array to text file
		try{
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("RecordID\tIP address\tDeviceName\tAlert Type\tRecord Time\n");
			while(i<p.outputRecordId().length){			
				bw.write(recordID[i]+"\t"+ipaddress[i]+"\t"+devName[i]+"\t"+alert_type[i]+"\t"+  
						recordtime[i]+"\n");
				i++;
			}
			bw.close();
			check = true;
		}catch(Exception e){
			System.out.println("Error");
		}
		return check;

	}
}
