package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DatabaseMethod.*;

public class LoggingRecordsAction implements ActionListener {
	public static String IP;
	public static String all;
	public static ImageIcon formatIcon;
	public static ImageIcon HistoryIP;
	public static String choicedCriteria;
	public static String choicedIplist;
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
 		DatabaseConfigIdentifier iden = new DatabaseConfigIdentifier();
 		OutputRecord record = new OutputRecord();
		boolean config_check = iden.getIdentifier();
		OutputToXlsx newXlsx = new OutputToXlsx();
		final String [] iplist;
		boolean checkNull = false;
		boolean check = false;
		if(config_check == true){
 			iplist = record.outputRecordFromDeviceForSelection();
 			// TODO Auto-generated method stub
 			IP = "IP";
 			all = "ALL";
 			formatIcon = new ImageIcon("icons/formatIcon.png");
 			HistoryIP = new ImageIcon("icons/IP.png");
 			final String[] format ={ IP,all};
 			do{
 				choicedCriteria = (String)JOptionPane.showInputDialog(null,"Please choose a criteria for the files.", "Save Files",
				JOptionPane.QUESTION_MESSAGE,formatIcon,format,format[0]);
 				if(choicedCriteria.isEmpty()){
 					break;
 				}
 			}while(checkNull == true );
 			//String [][] b = null; // [i][0] = ipaddress [i][1]= deviceName
 			// String[] a = b[0][1]+b[0][2];
 			///////////////////////////////////////////////

 		/*String [] c = new String[b.length];
 		  for(int i=0;i<b.length;i++){
 			c[i]= b[i][0]+"/"+b[i][1];
 		}*/
 			if(choicedCriteria=="ALL"){
 				try {
					newXlsx.createAlertLogXlsxFile(record.outputRecordFromAlertLoggingForAll());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
 			}else{
 				if(choicedCriteria=="IP"){
 					do{
 						choicedIplist = (String)JOptionPane.showInputDialog(null,"Please choose the IP","IP List",JOptionPane.QUESTION_MESSAGE,HistoryIP,iplist,iplist[0]);
 						if(choicedIplist.isEmpty()){
 							break;
 						}
 						try{
 							do{
 								newXlsx.createAlertLogXlsxFile(record.outputRecordFromAlertLogging(choicedIplist));
 								check = true;					 									
 							}while(check==false);
 						}catch(Exception ie){
 			
 						}
 					}while(checkNull == true);
 				}
 			}
 		 /*do{
 			 if(choicedIplist==c[j]){
 				 if(choicedFormat=="Microsoft Excel Format")
 					 check = newfile.createXlsxFile(record.outputRecordFromRecordByIp(b[j][0]));
 			 }
 			 
 		 }while(check==true);
 		 */ 
 		}else{
 			JOptionPane.showMessageDialog(null,"Please configurate the database first.");
 		}
	}
}
 
