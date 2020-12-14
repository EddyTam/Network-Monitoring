package NetScanning;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
public class NetScan{
	public Stack NetScan(String input_ip){ // 172.18.39.214/24
	    int[] bounds = NetScan.rangeFromCidr(input_ip);
	    Stack stack = new Stack();
	    Process p1 = null;
	    int returnVal = 3;
	    boolean reachable = true;
	    try{
	    	for (int i = bounds[0]; i <= bounds[1]; i++) {
	    		String address = InetRange.intToIp(i);
	    		InetAddress ip = InetAddress.getByName(address);
	    		String temp = ip.toString().substring(1);
	    		try {
	    			p1 = java.lang.Runtime.getRuntime().exec("ping -n 1 -w 100 " +temp);
	    		} catch (IOException e) {
	    			// TODO Auto-generated catch block
	    			e.printStackTrace();
	    		}
	    		returnVal = p1.waitFor();
	    		reachable = (returnVal==0);
	    		if(returnVal == 0){ // if the ip host is living, the ip will push into the stack
	    			//System.out.printf("%s is connected\n",temp);
	    			stack.push(temp);
	    		}else{
	    			//System.out.printf("%s is not conneted\n",temp);
	    		}
	    	}
	    }catch(Exception e){
	    	
	    }
	
	    return stack;
	}

	public static int[] rangeFromCidr(String cidrIp) {
	    int maskStub = 1 << 31;
	    String[] atoms = cidrIp.split("/");
	    int mask = Integer.parseInt(atoms[1]);
	    
	    int[] result = new int[2];
	    result[0] = InetRange.ipToInt(atoms[0]) & (maskStub >> (mask - 1)); // lower bound
	    result[1] = InetRange.ipToInt(atoms[0]); // upper bound
	    return result;
	}

	static class InetRange {
	    public static int ipToInt(String ipAddress) {
	        try {
	            byte[] bytes = InetAddress.getByName(ipAddress).getAddress();
	            int octet1 = (bytes[0] & 0xFF) << 24;
	            int octet2 = (bytes[1] & 0xFF) << 16;
	            int octet3 = (bytes[2] & 0xFF) << 8;
	            int octet4 = bytes[3] & 0xFF;
	            int address = octet1 | octet2 | octet3 | octet4;
	            //System.out.println(address);
	            return address;
	        } catch (Exception e) {
	            e.printStackTrace();

	            return 0;
	        }
	    }

	    public static String intToIp(int ipAddress) {
	        int octet1 = (ipAddress & 0xFF000000) >>> 24;
	        int octet2 = (ipAddress & 0xFF0000) >>> 16;
	        int octet3 = (ipAddress & 0xFF00) >>> 8;
	        int octet4 = ipAddress & 0xFF;

	        return new StringBuffer().append(octet1).append('.').append(octet2)
	                                 .append('.').append(octet3).append('.')
	                                 .append(octet4).toString();
	    }
		}
	}


