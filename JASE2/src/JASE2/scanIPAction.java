package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import NetScanning.NetScan;

import java.util.*;
public class scanIPAction implements ActionListener {
	public static ImageIcon IPicon;
	public static ImageIcon comIcon;
	@Override
	public void actionPerformed(ActionEvent e) {
		String ip = "ok";
		String com = "ok";
		
		IPicon = new ImageIcon("icons/IP.png");
		comIcon = new ImageIcon("icons/com.png");
		// TODO Auto-generated method stub
    	boolean checknull = true;
    	boolean checknull_2 = true;
		NetScan a = new NetScan();
		Stack iplist = new Stack();
		Stack iplist_2 = new Stack();
		snmptest testing = new snmptest();
		String temp = "";
		boolean testresult = false;
		Stack iptestresult = new Stack();
		do{	
    		try{	
				ip= (String) JOptionPane.showInputDialog(null,"Please enter the IP scanning boundary(e.g. 192.168.10.255/24","Input the IP address",JOptionPane.QUESTION_MESSAGE,IPicon,null,null);
				if(ip.isEmpty()){
					JOptionPane.showMessageDialog(null,"Input Empty is invaild.");
					throw new NullPointerException();
				}else{		
					checknull = false;
					com = (String) JOptionPane.showInputDialog(null,"Please enter your community","Community",JOptionPane.QUESTION_MESSAGE,comIcon,null,null );
					if(com.isEmpty()){
						JOptionPane.showMessageDialog(null, "InputEmpty is invaild.");
						throw new NullPointerException();
					}else{
						checknull_2 = false;
					}
				}			
    		}catch(NullPointerException t){
    			break;
    		}
		}while(checknull == true);
		
		iplist = a.NetScan(ip);
		iplist_2 = iplist;
		/*for(int j = 0; j<=iplist.capacity();j++){
			try{
				System.out.println(iplist.pop());
			}catch(EmptyStackException ee){
				
			}
		}*/
		
		for(int i =0; i<=iplist.capacity();i++){
			try {	
				try{
					ip = iplist.pop().toString();
					testresult = testing.testsnmp(ip,com);
					if(testresult == true){
						GUI.listModel.addElement(ip);
				        //create user object for snmp
				        newAction.userarray[newAction.usercount] = new User(ip,com);
				        String checkOS = newAction.userarray[newAction.usercount].getsysdesc();
				        
				        if(checkOS.contains("Linux")){ 
				        	newAction.userarray[newAction.usercount].setOS("Linux");
				        }else {
				     	   if(checkOS.contains("Intel")){
				     		  newAction.userarray[newAction.usercount].setOS("Windows");
				     	   }else{
				     		   if(checkOS.contains("2800")){
				     			  newAction.userarray[newAction.usercount].setOS("Router");
				     		   }else{
				     			   if(checkOS.contains("2950")){
				     				  newAction.userarray[newAction.usercount].setOS("Switch");
				     			   }
				     		   }
				     	   }
				     	   
				        }
				        System.out.println(newAction.userarray[newAction.usercount].getOS());
				        
				        if(newAction.userarray[newAction.usercount].getOS()=="Linux"){
				     	   String [] linuxresult = newAction.userarray[newAction.usercount].getallfordb_linux();
				             
				      // return the os parameter and use string.contains  to change the values it is linux or windows and call the db method;
				         
				     	   DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
				     	   insert.inputRecordToDeviceTable(newAction.userarray[newAction.usercount].getIp(), newAction.userarray[newAction.usercount].getsysname(),newAction.userarray[newAction.usercount].getOS());
				     	   insert.inputRecordToLinuxRecord(linuxresult);
				     	  newAction.usercount++;
				        }else{
				     	   if(newAction.userarray[newAction.usercount].getOS() == "Windows"){
				            String [] windowsresult = newAction.userarray[newAction.usercount].getallfordb_windows();
				            
				            
				            // return the os parameter and use string.contains  to change the values it is linux or windows and call the db method;
				               DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
				               insert.inputRecordToDeviceTable(newAction.userarray[newAction.usercount].getIp(), newAction.userarray[newAction.usercount].getsysname(),newAction.userarray[newAction.usercount].getOS());
				               insert.inputRecordToWindowRecord(windowsresult);
				               newAction.usercount++;
				            }else{
				         	   if(newAction.userarray[newAction.usercount].getOS() == "Router"){
				         		   
				         		   DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
				                    insert.inputRecordToDeviceTable(newAction.userarray[newAction.usercount].getIp(), newAction.userarray[newAction.usercount].getsysname(),newAction.userarray[newAction.usercount].getOS());
				                    newAction.usercount++;
				         	   }else{
				         		   if(newAction.userarray[newAction.usercount].getOS() == "Switch"){
				         			   DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
				                        insert.inputRecordToDeviceTable(newAction.userarray[newAction.usercount].getIp(), newAction.userarray[newAction.usercount].getsysname(),newAction.userarray[newAction.usercount].getOS());
				                        newAction.usercount++;
				         		   }
				         	   }
				        }
				     	   
				        }

					}
				}catch(EmptyStackException ed){
					
				}
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
}
