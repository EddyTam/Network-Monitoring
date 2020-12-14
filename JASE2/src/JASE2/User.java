package JASE2;

import java.util.List;

import javax.swing.JOptionPane;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import DatabaseMethod.InsertRecord;


public class User{
	
	public String ip;
	public String community;
	public int cpulimit = 100;
	public int ramlimit = 100;
	public SnmpUtility snmpget = new SnmpUtility();
	public String OS = "";
	
	public String getOS() {
		return OS;
	}

	public void setOS(String oS) {
		OS = oS;
	}

	public User(String ip, String community) {
		this.ip = ip;
		this.community = community;
	}
	
	public User(String ip, String community, String cpulimit, String ramlimit) {
		this.ip = ip;
		this.community = community;
		this.cpulimit = Integer.parseInt(cpulimit);
		this.ramlimit = Integer.parseInt(ramlimit);
		// TODO Auto-generated constructor stub
	}
	
	public String getsysdesc() {
		String r = null;
		try{
			r = snmpget.get(new OID(".1.3.6.1.2.1.1.1.0"),this.ip,this.community).toString();
		}catch(Exception e){
		
		}
		return r;
	
	}
	public String getsysuptime() {
		String r = null;
		try{
			r = snmpget.get(new OID(".1.3.6.1.2.1.1.3.0"),this.ip,this.community).toString();
		}catch(Exception e){
		
		}
		return r;
	
	}
	public String getsysname() {
		String r = null;
		try{
			r = snmpget.get(new OID(".1.3.6.1.2.1.1.5.0"),this.ip,this.community).toString();
		}catch(Exception e){
			
		}
		return r;
	}
	
	//Linux--------------------------------------------------------------------------------------------------------

	public String getcpuload_linux() {
		int r = 0;
		try{
		r = Integer.parseInt(snmpget.get(new OID(".1.3.6.1.4.1.2021.11.9.0"),this.ip,this.community).toString());
		}catch(Exception e){
			
		}
		if(r>=cpulimit){
			JOptionPane.showMessageDialog(null, this.ip+"'s CPU is over "+cpulimit+"%");
			String alertInfo[] = {this.ip,this.getsysname(),"CPU"};
			InsertRecord alert = new InsertRecord();
			alert.inputRecordToAlretLoggingRecord(alertInfo);
			
		}
		return String.valueOf(r);
		
	}
	public String gettotalram_linux() {
		String r = null;
		double q = 0;
		try{
		r = snmpget.get(new OID(".1.3.6.1.4.1.2021.4.5.0"),this.ip,this.community).toString();
		}catch(Exception e){
			
		}
		q = Double.parseDouble(r)/(1024*1024);
		Double d = Double.valueOf(String.format("%.02f", q));
		r = String.valueOf(d);
		
		return r;
		
	}
	public String getramused_linux() {
		String r = null;
		double q = 0;
		try{
		r = snmpget.get(new OID(".1.3.6.1.4.1.2021.4.6.0"),this.ip,this.community).toString();
		}catch(Exception e){
			
		}
		q = Double.parseDouble(r)/(1024*1024);
		Double d = Double.valueOf(String.format("%.02f", q));
		r = String.valueOf(d);
		return r;
		
	}
	public String getramload_linux(){
		String total = null;
		String used = null;
		double dramload;
		double iramload;
		String sramload;
		try{
		total = gettotalram_linux();
		used = getramused_linux();
		}catch(Exception e){
			
		}
		dramload = (Double.parseDouble(used)/Double.parseDouble(total))*100;
		iramload = (int)dramload;
		if(iramload>=ramlimit){
			JOptionPane.showMessageDialog(null, this.ip+"'s RAM is over "+ramlimit+"%");
			String alertInfo[] = {this.ip,this.getsysname(),"RAM"};
			InsertRecord alert = new InsertRecord();
			alert.inputRecordToAlretLoggingRecord(alertInfo);
			
		}
		return  String.valueOf(iramload);	
		
	}
	
	public String gettotalhd_linux() {
		String r = null;
		double q;
		try{
		r = snmpget.get(new OID(".1.3.6.1.4.1.2021.9.1.6.1"),this.ip,this.community).toString();
		}catch(Exception e){
			
		}
		q = Double.parseDouble(r)/(1024*1024);
		Double d = Double.valueOf(String.format("%.02f", q));
		r = String.valueOf(d);
		return r;
		
	}
	public String getavaildhd_linux() {
		String r = null;
		double q;
		try{
		r = snmpget.get(new OID(".1.3.6.1.4.1.2021.9.1.7.1"),this.ip,this.community).toString();
		}catch(Exception e){
			
		}
		q = Double.parseDouble(r)/(1024*1024);
		Double d = Double.valueOf(String.format("%.02f", q));
		r = String.valueOf(d);
		return r;
		
	}
	
	public String getintraffic_linux(){
		String intime1 = null;
		String intime2 = null;
		//String outtime1 = null;
		//String outtime2 = null;
		double dintime1;
		double dintime2;
		//double douttime1;
		//double douttime2;
		try{
			intime1 = snmpget.get(new OID(".1.3.6.1.2.1.2.2.1.10.2"),this.ip,this.community).toString();
			//outtime1 = get(".1.3.6.1.2.1.2.2.1.16.2");
			Thread.sleep(2000);
			intime2 = snmpget.get(new OID(".1.3.6.1.2.1.2.2.1.10.2"),this.ip,this.community).toString();
			//outtime2 = get(".1.3.6.1.2.1.2.2.1.16.2");
		}catch(Exception e){
			
		}
		dintime1 = Double.parseDouble(intime1)/1024;
		dintime2 = Double.parseDouble(intime2)/1024;
		//douttime1 = Double.parseDouble(outtime1)/1024;
		//douttime2 = Double.parseDouble(outtime2)/1024;
		double dintraffic = dintime2 - dintime1;
		//double douttraffic = douttime2 - douttime1;
		dintraffic = Double.valueOf(String.format("%.02f", dintraffic));
		//douttraffic = Double.valueOf(String.format("%.02f", douttraffic));
		String intraffic = String.valueOf(dintraffic);
		//String outtraffic = String.valueOf(douttraffic);
		return intraffic;
	}
	
	public String getouttraffic_linux(){
		String outtime1 = null;
		String outtime2 = null;
		double douttime1;
		double douttime2;
		try{
			outtime1 = snmpget.get(new OID(".1.3.6.1.2.1.2.2.1.16.2"),this.ip,this.community).toString();
			Thread.sleep(2000);
			outtime2 = snmpget.get(new OID(".1.3.6.1.2.1.2.2.1.16.2"),this.ip,this.community).toString();
		}catch(Exception e){
			
		}
		douttime1 = Double.parseDouble(outtime1)/1024;
		douttime2 = Double.parseDouble(outtime2)/1024;
		double douttraffic = douttime2 - douttime1;
		douttraffic = Double.valueOf(String.format("%.02f", douttraffic));
		String outtraffic = String.valueOf(douttraffic);
		return outtraffic;
	}
	
	
	public String getall_linux(){
		/*String[] all = new String[7];
		all[0]  = getIp();
		all[1]  = getsysname();
		all[2]  = getsysuptime();
		all[3]  = gettotalram();
		all[4]  = getramused();
		all[5]  = getcpuused();
		all[6]  = gettotalhd();
		all[7]  = getavaildhd();*/
		
		String OS = "Device type : "+this.getOS();
		String a = "Ip address : "+this.getIp();
		String b = "System name : "+getsysname();
		String c = "System Uptime : "+getsysuptime();
		String d = "Total Ram : "+gettotalram_linux()+" GB";
		String e = "Used Ram : "+getramused_linux()+ " GB";
		String f = "Ram Load : "+getramload_linux()+"%";
		String g = "CPU Load : "+getcpuload_linux()+"%";
		String h = "Total Harddisk space : "+gettotalhd_linux() +" GB";
		String i = "Avail Harddisk space : "+getavaildhd_linux() +" GB";
		String j = "Incoming Traffic : "+getintraffic_linux()+" KB";
		String k = "Outgoing Traffic : "+getouttraffic_linux()+" KB";
		
		String all = OS+"\n"+a+"\n"+b+"\n"+c+"\n"+d+"\n"+e+"\n"+f+"\n"+g+"\n"+h+"\n"+i+"\n"+j+"\n"+k;

    	return all;
	}
	
	public int getCpulimit() {
		return cpulimit;
	}

	public void setCpulimit(int cpulimit) {
		this.cpulimit = cpulimit;
	}

	public int getRamlimit() {
		return ramlimit;
	}

	public void setRamlimit(int ramlimit) {
		this.ramlimit = ramlimit;
	}

	public String[] getallfordb_linux(){
		String[] all = new String[11];
		all[0]  = this.getIp();
		all[1]  = getsysname();
		all[2]  = getsysuptime();
		all[3]  = gettotalram_linux();
		all[4]  = getramused_linux();
		all[5]  = getramload_linux();
		all[6]  = getcpuload_linux();
		all[7]  = gettotalhd_linux();
		all[8]  = getavaildhd_linux();
		all[9]  = getintraffic_linux();
		all[10] = getouttraffic_linux();
		
		return all;
		
	}
	
	//Windows-----------------------------------------------------------------------------------------------------------
	
	public String getcpuload_windows(){
		List<VariableBinding> cpuload = snmpget.walk(new OID(".1.3.6.1.2.1.25.3.3.1.2"),this.ip,this.community);
		String[]Scpudesc = new String[cpuload.size()];
		String[]Scpuload = new String[cpuload.size()];
		String cpuloadforgui = "";
		for (int j = 0;j<cpuload.size();j++){
			Scpudesc[j] = "Processor "+(j+1)+" : ";
		}
		for (int i = 0;i<cpuload.size();i++){
			Scpuload[i] = cpuload.get(i).getVariable().toString();
			cpuloadforgui += Scpudesc[i]+Scpuload[i]+"%"+"\n";
		}
		return cpuloadforgui;
	}
	
	public String getaveragecpuload_windows(){
		List<VariableBinding> cpuload = snmpget.walk(new OID(".1.3.6.1.2.1.25.3.3.1.2"),this.ip,this.community);
		
		int intcpuload = 0;
		for(int i = 0;i<cpuload.size();i++){
			intcpuload += Integer.parseInt(cpuload.get(i).getVariable().toString());
		}
		intcpuload = intcpuload/cpuload.size();
		if(intcpuload>=cpulimit){
			JOptionPane.showMessageDialog(null, this.ip+"'s CPU is over "+cpulimit+"%");
			String alertInfo[] = {this.ip,this.getsysname(),"CPU"};
			InsertRecord alert = new InsertRecord();
			alert.inputRecordToAlretLoggingRecord(alertInfo);
			
		}
		return String.valueOf(intcpuload);
	}
	
	public String gettotalram_windows(){
		double result = Integer.parseInt(snmpget.get(new OID(".1.3.6.1.2.1.25.2.2.0"), this.ip, this.community).toString());
		result = Math.round(result/1024/1024);
		String sresult = String.valueOf(result);
		return sresult;
	}
	
	
	public String getusedram_windows(){
		List<VariableBinding> usedram = snmpget.walk(new OID(".1.3.6.1.2.1.25.5.1.1.2"), this.ip, this.community);
		double result = 0;
		for (VariableBinding varBinding : usedram) {
			result += Integer.parseInt(varBinding.getVariable().toString());
	    }
		return String.format("%.02f", result/1024/1024);
	
	}
	
	public String getramload_windows(){
		String totalram = gettotalram_windows();
		String usedram = getusedram_windows();
		double ramload = Math.round((Double.parseDouble(usedram)/Double.parseDouble(totalram))*100);
		if(ramload>=ramlimit){
			JOptionPane.showMessageDialog(null, this.ip+"'s RAM is over "+ramlimit+"%");
			String alertInfo[] = {this.ip,this.getsysname(),"RAM"};
			InsertRecord alert = new InsertRecord();
			alert.inputRecordToAlretLoggingRecord(alertInfo);
			
		}
		return String.valueOf(ramload);

	}
	
	public String gettotalhd_windows(){
		List<VariableBinding> storagetype = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.3"),this.ip,this.community);
		List<VariableBinding> storageallocation = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.4"),this.ip,this.community);
		List<VariableBinding> storagesize = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.5"),this.ip,this.community);
	
		String[]Sstoragetype = new String[storagetype.size()];
		Double[]Sstorageallocation = new Double[storagetype.size()];
		Double[]Sstoragesize = new Double[storagetype.size()];
		Double[]totalharddisk = new Double[storagetype.size()];
		String totalharddiskforgui = "";
	
	
		for (int i = 0;i<storagetype.size()-2;i++){
			Sstoragetype[i] = storagetype.get(i).getVariable().toString().substring(0, 3);
			Sstorageallocation[i] = Double.parseDouble(storageallocation.get(i).getVariable().toString());
			Sstoragesize[i] = Double.parseDouble(storagesize.get(i).getVariable().toString());
			totalharddisk[i] = (double) Math.round((Sstorageallocation[i]*Sstoragesize[i])/1024/1024/1024);
			totalharddiskforgui += Sstoragetype[i]+" : "+totalharddisk[i]+" GB\n";

		}

		return totalharddiskforgui;
	}
	
	public String gettotalhdfordb_windows(){
		List<VariableBinding> storageallocation = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.4"),this.ip,this.community);
		List<VariableBinding> storagesize = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.5"),this.ip,this.community);
		double totalhdfordb = 0;
		for (int i = 0;i<storagesize.size()-2;i++){
			totalhdfordb +=  Math.round((Double.parseDouble(storageallocation.get(i).getVariable().toString())*
							Double.parseDouble(storagesize.get(i).getVariable().toString()))/1024/1024/1024);
		}
		return String.valueOf(totalhdfordb);
	}
	
	
	
	public String getusedhd_windows(){
		List<VariableBinding> storagetype = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.3"),this.ip,this.community);
		List<VariableBinding> storageallocation = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.4"),this.ip,this.community);
		List<VariableBinding> storageused = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.6"),this.ip,this.community);
		String[]Sstoragetype = new String[storagetype.size()];
		Double[]Sstorageallocation = new Double[storagetype.size()];
		Double[]Sstorageused= new Double[storagetype.size()];
		Double[]usedharddisk = new Double[storagetype.size()];
		String usedharddiskforgui = "";
		
		for (int i = 0;i<storagetype.size()-2;i++){
			Sstoragetype[i] = storagetype.get(i).getVariable().toString().substring(0, 3);
			Sstorageallocation[i] = Double.parseDouble(storageallocation.get(i).getVariable().toString());
			Sstorageused[i] = Double.parseDouble(storageused.get(i).getVariable().toString());
			usedharddisk[i] = (double) Math.round((Sstorageused[i]*Sstorageallocation[i])/1024/1024/1024);
			usedharddiskforgui += Sstoragetype[i]+" : "+usedharddisk[i]+" GB\n";
		}
		return usedharddiskforgui;
	}
	
	public String getusedhdfordb_windows(){
		List<VariableBinding> storageallocation = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.4"),this.ip,this.community);
		List<VariableBinding> storagesize = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.6"),this.ip,this.community);
		double usedhdfordb = 0;
		for (int i = 0;i<storagesize.size()-2;i++){
			usedhdfordb += Math.round((Double.parseDouble(storageallocation.get(i).getVariable().toString())*
							Double.parseDouble(storagesize.get(i).getVariable().toString()))/1024/1024/1024);
		}
		return String.valueOf(usedhdfordb);
	}
	
	public String[] getallfordb_windows(){
		String[] all= new String[9];
		all[0] = getIp();
		all[1] = getsysname();
		all[2] = getsysuptime();
		all[3] = gettotalram_windows();
		all[4] = getusedram_windows();
		all[5] = getramload_windows();
		all[6] = getaveragecpuload_windows();
		all[7] = gettotalhdfordb_windows();
		all[8] = getusedhdfordb_windows();
		
		return all;
	}
	
	/*public String getall_windows(){
		String[] all = new String[7];
		all[0]  = getIp();
		all[1]  = getsysname();
		all[2]  = getsysuptime();
		all[3]  = gettotalram();
		all[4]  = getramused();
		all[5]  = getcpuused();
		all[6]  = gettotalhd();
		all[7]  = getavaildhd();
		
		String OS = "Device type : "+this.getOS()+"\n";
		String a = "Ip address : "+this.getIp()+"\n";
		String b = "System name : "+getsysname()+"\n";
		String c = "System Uptime : "+getsysuptime()+"\n";
		String d = "CPU load : \n"+getcpuload_windows();
		String e = "Total Harddisk space : \n"+gettotalhd_windows();
		String f = "Used Harddisk space : \n"+getusedhd_windows();
		String g = "Total Ram : "+gettotalram_windows();
		String h = "Used Ram : "+getusedram_windows();
		
		String all = OS+a+b+c+"\n"+d+"\n"+e+"\n"+f+"\n"+g+"\n"+h;

    	return all;
	}*/
	
	//Router------------------------------------------------------------------------------------------------------------
	
	
	public String getIfstatus_router(){
		List<VariableBinding> ifDescr = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.2"),this.ip,this.community);
		List<VariableBinding> ifOperStatus =snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.8"),this.ip,this.community);
		List<VariableBinding> ifAdminStatus =snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.7"),this.ip,this.community);
		String[]ifOperON = new String[ifDescr.size()];
		String[]ifAdminON = new String[ifDescr.size()];
		String Ifstatus = "";
		
		for(int j = 0;j<ifDescr.size();j++){
			if(ifOperStatus.get(j).getVariable().toString().equals("1")){
				ifOperON[j] = "On";
			}else{
				ifOperON[j] = "Off";					
			}

			if(ifAdminStatus.get(j).getVariable().toString().equals("1")){
				ifAdminON[j] = "On";
			}else{
				ifAdminON[j] = "Off";
			}
		
		}	
		for(int i = 0;i<ifDescr.size()-2;i++){
			Ifstatus += ifDescr.get(i).getVariable().toString()+" : \nStatus : ["+ifOperON[i]+"]  Protocol : ["+ifAdminON[i]+"]\n";			
		}
		return Ifstatus;
	}
	
	public String getIfpktintraffic_router(){
		List<VariableBinding> ifDescr = null;
		List<VariableBinding> ifinpkt1= null;
		List<VariableBinding> ifinpkt2 = null;
		try{
			ifDescr = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.2"),this.ip,this.community);
			ifinpkt1 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.11"),this.ip,this.community);
		Thread.sleep(2000);
			ifinpkt2 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.11"),this.ip,this.community);
		}catch(Exception e){
			
		}
		String ifinpktresult = "";
		
		for (int i = 0;i<ifDescr.size()-2;i++){
			ifinpktresult += ifDescr.get(i).getVariable().toString()+" : "+
							 (ifinpkt2.get(i).getVariable().toLong()-ifinpkt1.get(i).getVariable().toLong())+"\n";
		}
		return ifinpktresult;		
	}
	
	public String getIfpktouttraffic_router() throws InterruptedException{
		List<VariableBinding> ifDescr = null;
		List<VariableBinding> ifoutpkt1 = null;
		List<VariableBinding> ifoutpkt2 = null;
		try{
			ifDescr = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.2"),this.ip,this.community);
			ifoutpkt1 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.17"),this.ip,this.community);
		Thread.sleep(2000);
			ifoutpkt2 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.17"),this.ip,this.community);
		}catch(Exception e){
			
		}
		String ifoutpktresult = "";
		for (int i = 0;i<ifDescr.size()-2;i++){
			ifoutpktresult += ifDescr.get(i).getVariable().toString()+" : "+
							 (ifoutpkt2.get(i).getVariable().toLong()-ifoutpkt1.get(i).getVariable().toLong())+"\n";
		}
		return ifoutpktresult;		
	}
	
	public String getIfmbintraffic_router() throws InterruptedException{
		List<VariableBinding> ifDescr = null;
		List<VariableBinding> ifinmb1 = null;
		List<VariableBinding> ifinmb2 = null;
		try{
			ifDescr = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.2"),this.ip,this.community);
			ifinmb1 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.10"),this.ip,this.community);
		Thread.sleep(2000);
			ifinmb2 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.10"),this.ip,this.community);
		}catch(Exception e){
			
		}
		String ifinmbresult = "";
		for (int i = 0;i<ifDescr.size()-2;i++){
			ifinmbresult += ifDescr.get(i).getVariable().toString()+" : "+
							 (ifinmb2.get(i).getVariable().toLong()-ifinmb1.get(i).getVariable().toLong())/1024+"KB/s \n";
		}
		return ifinmbresult;
	}
	
	public String getIfmbouttraffic_router() throws InterruptedException{
		List<VariableBinding> ifDescr = null;
		List<VariableBinding> ifoutmb1 = null;
		List<VariableBinding> ifoutmb2 = null;
		try{
			ifDescr = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.2"),this.ip,this.community);
			ifoutmb1 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.16"),this.ip,this.community);
		Thread.sleep(2000);
			ifoutmb2 = snmpget.walk(new OID(".1.3.6.1.2.1.2.2.1.16"),this.ip,this.community);
		}catch(Exception e){
			
		}
		String ifoutmbresult = "";
		for (int i = 0;i<ifDescr.size()-2;i++){
			ifoutmbresult += ifDescr.get(i).getVariable().toString()+" : "+
							 (ifoutmb2.get(i).getVariable().toLong()-ifoutmb1.get(i).getVariable().toLong())/1024+"KB/s \n";
		}
		return ifoutmbresult;
	}
	
	
	
	public String getCpuload_router(){
		int cpuload = Integer.parseInt(snmpget.get(new OID(".1.3.6.1.4.1.9.9.109.1.1.1.1.3.1"), this.ip, this.community).toString());
		if(cpuload>=cpulimit){
			JOptionPane.showMessageDialog(null, this.ip+"'s CPU is over "+cpulimit+"%");
			String alertInfo[] = {this.ip,this.getsysname(),"CPU"};
			InsertRecord alert = new InsertRecord();
			alert.inputRecordToAlretLoggingRecord(alertInfo);
			
		}
		return String.valueOf(cpuload);
	}
	
	public String getIp() {
		return ip;
	}

	public String getCommunity() {
		return community;
	}

}
	

