package JASE2;

import javax.swing.JOptionPane;

import org.snmp4j.CommunityTarget;
import org.snmp4j.PDU;
import org.snmp4j.Snmp;
import org.snmp4j.TransportMapping;
import org.snmp4j.event.ResponseEvent;
import org.snmp4j.mp.SnmpConstants;
import org.snmp4j.smi.Integer32;
import org.snmp4j.smi.OID;
import org.snmp4j.smi.OctetString;
import org.snmp4j.smi.UdpAddress;
import org.snmp4j.smi.VariableBinding;
import org.snmp4j.transport.DefaultUdpTransportMapping;

public class snmptest {

	public boolean testsnmp(String ip, String community) throws Exception{
		boolean testresult = false;
	    // Create TransportMapping and Listen
	    TransportMapping transport = new DefaultUdpTransportMapping();
	    transport.listen();

	    // Create Target Address object
	    CommunityTarget comtarget = new CommunityTarget();
	    comtarget.setCommunity(new OctetString(community));
	    comtarget.setVersion(SnmpConstants.version2c);
	    comtarget.setAddress(new UdpAddress(ip + "/"+161));
	    comtarget.setRetries(0);
	    comtarget.setTimeout(500);

	    // Create the PDU object
	    PDU pdu = new PDU();
	    pdu.add(new VariableBinding(new OID(".1.3.6.1.2.1.1.1.0")));
	    pdu.setType(PDU.GET);
	    pdu.setRequestID(new Integer32(1));
	    
	    // Create Snmp object for sending data to Agent
	    Snmp snmp = new Snmp(transport);

	    ResponseEvent response = snmp.get(pdu, comtarget);
	    // Process Agent Response
	    String result = null;
	    if (response != null){
	      PDU responsePDU = response.getResponse();
	      testresult = true;

	      if (responsePDU != null){
	        int errorStatus = responsePDU.getErrorStatus();
	        int errorIndex = responsePDU.getErrorIndex();
	        String errorStatusText = responsePDU.getErrorStatusText();

	        if (errorStatus == PDU.noError){
	            result = responsePDU.get(0).getVariable().toString();
	        }
	        else{
	        testresult = false;
	         return testresult;
	        }
	      }
	      else{
	    	  testresult = false;
	    	  return testresult;
	      }
	    }
	    else{
	    	testresult = false;
	    	return testresult;
	    }
	    snmp.close();
	    return testresult;
	  }
	
}
