package JASE2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import org.snmp4j.smi.OID;

import jchart.*;

public class newAction extends JFrame implements ActionListener {
	static JTextField txtInput ;
	public String ip = "ok";
	public String com = null;
	public String cpulimit = "";
	public String ramlimit = "";
	public String type;
	public static String getall;
	public static User [] userarray = new User[10];
	public static int usercount = 0;
	public static JPanel hdpie;
	public static JPanel rampie;
	public static JPanel cpupie;
	public static ImageIcon IpPic;
	public static ImageIcon comPic;

	///////////////////
	    public static JPanel DisplayBox;
	    public static JTextArea text;
		public static Border BD_0;
		public static Border BD_1;
		public static Border BD_2;
		public static Border blackline;
		public static TitledBorder title;
	    public static JLabel IP;
	    public static JPanel mainPanel;
	    public static JPanel subPanel1;
	    public static JPanel subPanel2;
	    public static JPanel subPanel3;
	    public static Dimension panelSize;
	    
	    public static String Router;
	    public static String Pc;
	    public static String ChoicedType;
	    public static SnmpUtility snmpget = new SnmpUtility();
	    public static String OS;
	    public static String checkOS="";
	    
	    
	/////Windows////////////////////
	    public static JPanel DisplayBox1;
	    public static JPanel DisplayBox2;
	    public static JPanel DisplayBox3;
	    
	    public static JTextArea text1;
	    public static JTextArea text2;
	    public static JTextArea text3;	    
	    
	    
	///////////////////
	    //////////New Windows/////////////
	    public static JPanel WindowsDisplayTextPanel1;
	    public static JPanel WindowsDisplayTextPanel2;
	    public static JPanel WindowsDisplayPicture3;
	    public static JPanel WindowsDisplayPicture4;
	    
	    public static JTextArea WindowsText1;
	    public static JTextArea WindowsText2;
	    
	    public static TitledBorder title1;
	    public static TitledBorder title2;
	    public static TitledBorder title3;
	    public static TitledBorder title4;
	    
	    //////////////////////////////////
	    
	    
	    
	    

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	//String title = "please input";
    	boolean checknull = true;
    	boolean checknull_2 = true;
    	boolean checkcpu = false;
    	boolean checkram = false;
    	
    	IpPic = new ImageIcon("icons/IP.png");
    	comPic = new ImageIcon("icons/com.png");
		do{	
    		try{	
				ip= (String) JOptionPane.showInputDialog(null,"Please enter your device IP address","IP address",JOptionPane.QUESTION_MESSAGE,IpPic,null,null);
				if(ip.isEmpty()){
					JOptionPane.showMessageDialog(null,"Input Empty is invaild.");
					throw new NullPointerException();
				}else{
					checknull = true;
					com = (String) JOptionPane.showInputDialog(null,"Please enter your community","Community",JOptionPane.QUESTION_MESSAGE, comPic,null,null);
					if(com.isEmpty()){
						JOptionPane.showMessageDialog(null, "Input Empty is invaild.");
						throw new NullPointerException();
					}else{
				
					}
					checknull_2 = true;
				}
	
				cpulimit = (String)JOptionPane.showInputDialog(null,"Please enter the CPU limit(%) : ","CPU limit",JOptionPane.QUESTION_MESSAGE,IpPic,null,null);	
				/*if(cpulimit.isEmpty()||cpucheck>100||cpucheck<=1){
					JOptionPane.showMessageDialog(null, "Inncorrect input");
					throw new NullPointerException();
				}else{
					cpulimit = String.valueOf(cpucheck);
					checkcpu = true;
				}
				}while(checkcpu == false);*/
				
			
				ramlimit = (String) JOptionPane.showInputDialog(null,"Please enter the RAM limit(%) : ","RAM limit",JOptionPane.QUESTION_MESSAGE,IpPic,null,null);
				/*if(ramlimit.isEmpty()||ramcheck>100||ramcheck<=1){
					JOptionPane.showMessageDialog(null, "Inncorrect input");
					throw new NullPointerException();
				}else{
					ramlimit = String.valueOf(ramcheck);
					checkram = true;
				}
				
				}while(checkram == false);*/
    		}catch(NullPointerException t){
    			break;
    		}
		}while(checknull == false || checknull_2 == false);
		/*do{
			try{
				com = JOptionPane.showInputDialog(null,"Please enter your community");
				if(com.isEmpty()){
					JOptionPane.showMessageDialog(null, "InputEmpty is invaild.");
				}else{
					checknull = false;
				}
    		}catch(NullPointerException w){	
    		}
		}while(checknull == true); */
		
		txtInput = new JTextField(ip);
		
		if (txtInput.getText().equals(""))
            return;
        addItem(txtInput.getText().trim());
	}
	
	public void addItem(String ip) {
        for (int i = 0; i < GUI.listModel.size(); i++) {
            if (GUI.listModel.get(i).equals(ip)){
                return;
            }
        }
        //add ip to list
        GUI.listModel.addElement(ip);
        txtInput.setText("");
        //create user object for snmp
        
        userarray[usercount] = new User(ip,this.com,cpulimit,ramlimit);
       checkOS = userarray[usercount].getsysdesc();
   
       if(checkOS.contains("Linux")){ 
    	   userarray[usercount].setOS("Linux");
       }else {
    	   if(checkOS.contains("Intel")){
    		   userarray[usercount].setOS("Windows");
    	   }else{
    		   if(checkOS.contains("2800")){
    			   userarray[usercount].setOS("Router");
    		   }else{
    			   if(checkOS.contains("2950")){
    				   userarray[usercount].setOS("Switch");
    			   }
    		   }
    	   }
    	   
       }
       System.out.println(userarray[usercount].getOS());
 
        //get info for database and import to it
       if(userarray[usercount].getOS()=="Linux"){
    	   String [] linuxresult = userarray[usercount].getallfordb_linux();
            
     // return the os parameter and use string.contains  to change the values it is linux or windows and call the db method;
        
    	   DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
    	   insert.inputRecordToDeviceTable(userarray[usercount].getIp(), userarray[usercount].getsysname(),userarray[usercount].getOS());
    	   insert.inputRecordToLinuxRecord(linuxresult);
    	   usercount++;
       }else{
    	   if(userarray[usercount].getOS() == "Windows"){
           String [] windowsresult = userarray[usercount].getallfordb_windows();
           
           
           // return the os parameter and use string.contains  to change the values it is linux or windows and call the db method;
              DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
              insert.inputRecordToDeviceTable(userarray[usercount].getIp(), userarray[usercount].getsysname(),userarray[usercount].getOS());
              insert.inputRecordToWindowRecord(windowsresult);
              usercount++;
           }else{
        	   if(userarray[usercount].getOS() == "Router"){
        		   
        		   DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
                   insert.inputRecordToDeviceTable(userarray[usercount].getIp(), userarray[usercount].getsysname(),userarray[usercount].getOS());
                   usercount++;
        	   }else{
        		   if(userarray[usercount].getOS() == "Switch"){
        			   DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
                       insert.inputRecordToDeviceTable(userarray[usercount].getIp(), userarray[usercount].getsysname(),userarray[usercount].getOS());
                       usercount++;
        		   }
        	   }
       }
    	   
       }
       
     
    }
	
	//Create Linux tab
	public  JPanel createTab_linux(int index){ // Create Tab
	
		getall = userarray[index].getall_linux();
        //create chart
        piechart piechart = new piechart("pie");
        hdpie = piechart.createDemoPanel(userarray[index].gettotalhd_linux(),userarray[index].getavaildhd_linux(), "Harddisk Usage");
        double ram =Double.parseDouble(userarray[index].gettotalram_linux())-Double.parseDouble(userarray[index].getramused_linux());
        String sram = Double.toString(ram);
        rampie = piechart.createDemoPanel(userarray[index].gettotalram_linux(),sram, "Ram Usage");
        cpupie = piechart.createDemoPanelforcpu(userarray[index].getcpuload_linux(), "Cpu Usage");
      //create tab layout
		mainPanel = new JPanel(new GridLayout(2,4));
        //DisplayBox = new JPanel();
        subPanel1 = new JPanel(new BorderLayout());
        panelSize = subPanel1.getSize();
        subPanel2 = new JPanel(new BorderLayout());
        subPanel3 = new JPanel(new BorderLayout());
        BD_1 = BorderFactory.createBevelBorder(0);
        BD_2 = BorderFactory.createBevelBorder(1);
        blackline = BorderFactory.createLineBorder(Color.black);
        title = BorderFactory.createTitledBorder(
                blackline, "Message Information");
        title.setTitleJustification(TitledBorder.CENTER);
        //DisplayBox.setBorder(BD_1);
        subPanel1.setBorder(BD_2);
        subPanel2.setBorder(BD_2);
        subPanel3.setBorder(BD_1);
        text = new JTextArea();
        text.setText(null);
        text.setEnabled(false);
        JScrollPane textScroll = new JScrollPane(text);
        textScroll.setBorder(title);
        //tabbedPane.addTab("Display Box",new ImageIcon("icons/filter.png"),DisplayBox.add(textScroll));
        mainPanel.add(textScroll);
        mainPanel.add(subPanel1);
        mainPanel.add(subPanel2);
        mainPanel.add(subPanel3);
        hdpie.setSize(panelSize);
        rampie.setSize(panelSize);
        cpupie.setSize(panelSize);  
        //import info and chart into tab
        text.setText(getall);
        subPanel1.add(hdpie);
        subPanel2.add(rampie);
        subPanel3.add(cpupie);
		return mainPanel;
	
	}
	
	//Create Router tab
	public JPanel createTab_router(int index) throws InterruptedException{
		
		String Info = "Device type : "+userarray[index].getOS()+"\n"+"\n"+
				   	  "Ip address : "+userarray[index].getIp()+"\n"+"\n"+
				      "System name : "+userarray[index].getsysname()+"\n"+"\n"+
				      "System Up time : "+userarray[index].getsysuptime()+"\n\n"+
				      "CPU Load : "+userarray[index].getCpuload_router();

		String PortStatus = userarray[index].getIfstatus_router();
		
		String Pktinout = "Port Packet in traffic : \n\n"+userarray[index].getIfpktintraffic_router()+"\nPort Packet out traffic : \n\n"+userarray[index].getIfpktouttraffic_router();
		
		String Mbinout = "Port Network in traffic : \n\n"+userarray[index].getIfmbintraffic_router()+"\nPort Network out traffic : \n\n"+userarray[index].getIfmbouttraffic_router();
		
		mainPanel = new JPanel(new GridLayout(2,4));
						
		BD_1 = BorderFactory.createBevelBorder(0);
        BD_2 = BorderFactory.createBevelBorder(1);
        blackline = BorderFactory.createLineBorder(Color.black);
        title1 = BorderFactory.createTitledBorder(
                blackline, "Router Information");
        title2 = BorderFactory.createTitledBorder(
                blackline, "Port Status");
        title3 = BorderFactory.createTitledBorder(
                blackline, "Port Packet traffic");
        title4 = BorderFactory.createTitledBorder(
                blackline, "Port Network traffic");
        title1.setTitleJustification(TitledBorder.CENTER);
        title2.setTitleJustification(TitledBorder.CENTER);
        title3.setTitleJustification(TitledBorder.CENTER);
        title4.setTitleJustification(TitledBorder.CENTER);
        
        DisplayBox = new JPanel(new BorderLayout());
        DisplayBox1 = new JPanel(new BorderLayout());
        DisplayBox2 = new JPanel(new BorderLayout());
        DisplayBox3 = new JPanel(new BorderLayout());


        text = new JTextArea();
        text.setText(Info);
        text.setEnabled(false);
        
        text1 = new JTextArea();
        text1.setText(PortStatus);
        text1.setEnabled(false);
        
        text2 = new JTextArea();
        text2.setText(Pktinout);
        text2.setEnabled(false);

        text3 = new JTextArea();
        text3.setText(Mbinout);
        text3.setEnabled(false);
        
		JScrollPane textScroll = new JScrollPane(text);
		JScrollPane textScroll1 = new JScrollPane(text1);
		JScrollPane textScroll2 = new JScrollPane(text2);
		JScrollPane textScroll3 = new JScrollPane(text3);

      //  panelSize = DisplayBox1.getSize();

	
		textScroll.setBorder(title1);
		textScroll1.setBorder(title2);
		textScroll2.setBorder(title3);
		textScroll3.setBorder(title4);
		
		DisplayBox.add(textScroll);
		DisplayBox1.add(textScroll1);
		DisplayBox2.add(textScroll2);
		DisplayBox3.add(textScroll3);

		mainPanel.add(DisplayBox);
		mainPanel.add(DisplayBox1);
		mainPanel.add(DisplayBox2);
		mainPanel.add(DisplayBox3);
	
		return mainPanel;
	}
	
public JPanel createTab_switch(int index) throws InterruptedException{
		
		String Info = "Device type : "+userarray[index].getOS()+"\n"+"\n"+
				   	  "Ip address : "+userarray[index].getIp()+"\n"+"\n"+
				      "System name : "+userarray[index].getsysname()+"\n"+"\n"+
				      "System Up time : "+userarray[index].getsysuptime()+"\n\n";
				     

		String PortStatus = userarray[index].getIfstatus_router();
		
		String Pktinout = "Port Packet in traffic : \n\n"+userarray[index].getIfpktintraffic_router()+"\nPort Packet out traffic : \n\n"+userarray[index].getIfpktouttraffic_router();
		
		String Mbinout = "Port Network in traffic : \n\n"+userarray[index].getIfmbintraffic_router()+"\nPort Network out traffic : \n\n"+userarray[index].getIfmbouttraffic_router();
		
		mainPanel = new JPanel(new GridLayout(2,4));
						
		BD_1 = BorderFactory.createBevelBorder(0);
        BD_2 = BorderFactory.createBevelBorder(1);
        blackline = BorderFactory.createLineBorder(Color.black);
        title1 = BorderFactory.createTitledBorder(
                blackline, "Router Information");
        title2 = BorderFactory.createTitledBorder(
                blackline, "Port Status");
        title3 = BorderFactory.createTitledBorder(
                blackline, "Port Packet traffic");
        title4 = BorderFactory.createTitledBorder(
                blackline, "Port Network traffic");
        title1.setTitleJustification(TitledBorder.CENTER);
        title2.setTitleJustification(TitledBorder.CENTER);
        title3.setTitleJustification(TitledBorder.CENTER);
        title4.setTitleJustification(TitledBorder.CENTER);
        
        DisplayBox = new JPanel(new BorderLayout());
        DisplayBox1 = new JPanel(new BorderLayout());
        DisplayBox2 = new JPanel(new BorderLayout());
        DisplayBox3 = new JPanel(new BorderLayout());


        text = new JTextArea();
        text.setText(Info);
        text.setEnabled(false);
        
        text1 = new JTextArea();
        text1.setText(PortStatus);
        text1.setEnabled(false);
        
        text2 = new JTextArea();
        text2.setText(Pktinout);
        text2.setEnabled(false);

        text3 = new JTextArea();
        text3.setText(Mbinout);
        text3.setEnabled(false);
        
		JScrollPane textScroll = new JScrollPane(text);
		JScrollPane textScroll1 = new JScrollPane(text1);
		JScrollPane textScroll2 = new JScrollPane(text2);
		JScrollPane textScroll3 = new JScrollPane(text3);

      //  panelSize = DisplayBox1.getSize();

	
		textScroll.setBorder(title1);
		textScroll1.setBorder(title2);
		textScroll2.setBorder(title3);
		textScroll3.setBorder(title4);
		
		DisplayBox.add(textScroll);
		DisplayBox1.add(textScroll1);
		DisplayBox2.add(textScroll2);
		DisplayBox3.add(textScroll3);

		mainPanel.add(DisplayBox);
		mainPanel.add(DisplayBox1);
		mainPanel.add(DisplayBox2);
		mainPanel.add(DisplayBox3);
	
		return mainPanel;
	}
	
	
	///Create Windows Tab
	public JPanel createTab_windows(int index){
		
		 piechart piechart = new piechart("pie");
		 
	     double ram =Double.parseDouble(userarray[index].gettotalram_windows())-Double.parseDouble(userarray[index].getusedram_windows());
	     String sram = Double.toString(ram);
	     rampie = piechart.createDemoPanel(userarray[index].gettotalram_windows(),sram, "Ram Usage");
	     cpupie = piechart.createDemoPanelforcpu(userarray[index].getaveragecpuload_windows(), "Cpu Usage");
		
		String Info = "Device type : "+userarray[index].getOS()+"\n"+"\n"+
					   "Ip address : "+userarray[index].getIp()+"\n"+"\n"+
					   "System name : "+userarray[index].getsysname()+"\n"+"\n"+
					   "System Up time : "+userarray[index].getsysuptime()+"\n";

		String harddiskInfo = "Total Harddisk space : \n\n"+userarray[index].gettotalhd_windows()+"\n"+
                              "Used Harddisk space : \n\n"+userarray[index].getusedhd_windows();       			
		
		mainPanel = new JPanel(new GridLayout(2,4));
							
		BD_1 = BorderFactory.createBevelBorder(0);
        BD_2 = BorderFactory.createBevelBorder(1);
        
        //Title//
        blackline = BorderFactory.createLineBorder(Color.black);
        
        title1 = (BorderFactory.createTitledBorder(
                blackline, "System Info"));
   
        title2 = (BorderFactory.createTitledBorder(
                blackline, "Harddisk Info"));

        
        title1.setTitleJustification(TitledBorder.CENTER);
        title2.setTitleJustification(TitledBorder.CENTER);
        
        ///////////
        
        
        
        ///Create Panel////
        WindowsDisplayTextPanel1 = new JPanel(new BorderLayout());
        WindowsDisplayTextPanel2 = new JPanel(new BorderLayout());
        WindowsDisplayPicture3 = new JPanel(new BorderLayout());
        WindowsDisplayPicture4 = new JPanel(new BorderLayout());
        


        WindowsText1 = new JTextArea();
        WindowsText1.setText(Info);
        WindowsText1.setEnabled(false);
        
        WindowsText2 = new JTextArea();
        WindowsText2.setText(harddiskInfo);
        WindowsText2.setEnabled(false);
        
		JScrollPane WindowsTextScroll1 = new JScrollPane(WindowsText1);
		WindowsTextScroll1.setBorder(title1);
		JScrollPane WindowsTextScroll2 = new JScrollPane(WindowsText2);
		WindowsTextScroll2.setBorder(title2);

		WindowsDisplayPicture3.setBorder(BD_1);
		WindowsDisplayPicture4.setBorder(BD_2);
		
        panelSize = WindowsDisplayPicture3.getSize();

        //Panel add functions or add  text, chart
        WindowsDisplayTextPanel1.add(WindowsTextScroll1);
        WindowsDisplayTextPanel2.add(WindowsTextScroll2);
        WindowsDisplayPicture3.add(rampie);
        WindowsDisplayPicture4.add(cpupie);
  
        // Main Panel add Panel
		mainPanel.add(WindowsDisplayTextPanel1);
		mainPanel.add(WindowsDisplayTextPanel2);
		mainPanel.add(WindowsDisplayPicture3);
		mainPanel.add(WindowsDisplayPicture4);
	
		return mainPanel;
	}
	
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getCom() {
		return com;
	}
	public void setCom(String com) {
		this.com = com;
	}


	public void setType(String type) {
		this.type = type;
	}

	public void setUsername(String type) {
		this.type = type;
	}
	
}
