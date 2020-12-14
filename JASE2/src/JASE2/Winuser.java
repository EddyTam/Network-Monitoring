package JASE2;

import java.util.List;

import org.snmp4j.smi.OID;
import org.snmp4j.smi.VariableBinding;

import JASE2.*;
public class Winuser {

	public String ip;
	public String community;
	public SnmpUtility snmpget;
	public Winuser(String ip, String community) {
		this.ip = ip;
		this.community = community;
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
	
	public String gettotalhd(){
		List<VariableBinding> storagetype = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.3"),this.ip,this.community);
		List<VariableBinding> storageallocation = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.4"),this.ip,this.community);
		List<VariableBinding> storagesize = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.5"),this.ip,this.community);
	
		String[]Sstoragetype = new String[storagetype.size()];
		Double[]Sstorageallocation = new Double[storagetype.size()];
		Double[]Sstoragesize = new Double[storagetype.size()];
		Double[]totalharddisk = new Double[storagetype.size()];
		String totalharddiskforgui = "";
	
	
		for (int i = 0;i<storagetype.size();i++){
			Sstoragetype[i] = storagetype.get(i).getVariable().toString();
			Sstorageallocation[i] = Double.parseDouble(storageallocation.get(i).getVariable().toString());
			Sstoragesize[i] = Double.parseDouble(storagesize.get(i).getVariable().toString());
			totalharddisk[i] = (double) Math.round((Sstorageallocation[i]*Sstoragesize[i])/1024/1024/1024);
			totalharddiskforgui = Sstoragetype[i]+" : "+totalharddisk[i]+"\n";
		}
		return totalharddiskforgui;

	}
	
	public String getavaildhd(){
		List<VariableBinding> storagetype = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.3"),this.ip,this.community);
		List<VariableBinding> storageallocation = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.4"),this.ip,this.community);
		List<VariableBinding> storageused = snmpget.walk(new OID(".1.3.6.1.2.1.25.2.3.1.6"),this.ip,this.community);
		String[]Sstoragetype = new String[storagetype.size()];
		Double[]Sstorageallocation = new Double[storagetype.size()];
		Double[]Sstorageused= new Double[storagetype.size()];
		Double[]usedharddisk = new Double[storagetype.size()];
		String usedharddiskforgui = "";
		for (int i = 0;i<storagetype.size();i++){
			Sstoragetype[i] = storagetype.get(i).getVariable().toString();
			Sstorageallocation[i] = Double.parseDouble(storageallocation.get(i).getVariable().toString());
			Sstorageused[i] = Double.parseDouble(storageused.get(i).getVariable().toString());
			usedharddisk[i] = (double) Math.round((Sstorageused[i]*Sstorageallocation[i])/1024/1024/1024);
			usedharddiskforgui = Sstoragetype[i]+" : "+usedharddisk[i]+"\n";
		}
		return usedharddiskforgui;
	}
	
	public String getall(){
		/*String[] all = new String[7];
		all[0]  = getIp();
		all[1]  = getsysname();
		all[2]  = getsysuptime();
		all[3]  = gettotalram();
		all[4]  = getramused();
		all[5]  = getcpuused();
		all[6]  = gettotalhd();
		all[7]  = getavaildhd();*/
		
		String a = "Ip address : "+this.getIp();
		String b = "System name : "+getsysname();
		String c = "System Uptime : "+getsysuptime();
		String h = "Total Harddisk space : "+gettotalhd() +" GB";
		String i = "Avail Harddisk space : "+getavaildhd() +" GB";
		
		String all = a+"\n"+b+"\n"+c+"\n"+"\n"+h+"\n"+i;

    	return all;
	}

	public String getIp() {
		return ip;
	}

	public String getCommunity() {
		return community;
	}
	
}
