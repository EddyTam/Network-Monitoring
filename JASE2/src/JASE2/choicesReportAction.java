package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DatabaseMethod.*;

public class choicesReportAction implements ActionListener {
	public static String Excel;
	public static String txt;
	public static ImageIcon formatIcon;
	public static ImageIcon HistoryIP;
	public static String choicedFormat;
	public static String choicedIplist;
	@Override
	public void actionPerformed(ActionEvent e){
		OutputRecord record = new OutputRecord();
 		OutputToXlsx newXlsx = new OutputToXlsx();
 		OutputToText newText = new OutputToText();
 		DatabaseConfigIdentifier iden = new DatabaseConfigIdentifier();
 		boolean config_check = iden.getIdentifier();
		boolean check = false;
		boolean checkNull = false;
		String os ="";
		final String [] iplist;
		//Initialize the variable 
		
 		if(config_check == true){  // check the user has configured database or not
 			iplist = record.outputRecordFromDeviceForSelection();
 			// Get existing IP from database
 			Excel = "Microsoft Excel Format";
 			txt = "NotePad Format";
 			formatIcon = new ImageIcon("icons/formatIcon.png");
 			HistoryIP = new ImageIcon("icons/IP.png");
 			final String[] format ={ Excel,txt};
 			do{
 				choicedFormat = (String)JOptionPane.showInputDialog(null,"Please choose a format for the files.", "Save Files",
				JOptionPane.QUESTION_MESSAGE,formatIcon,format,format[0]);
 				if(choicedFormat.isEmpty()){
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
 			do{
 				choicedIplist = (String)JOptionPane.showInputDialog(null,"Please choose the IP","IP List",JOptionPane.QUESTION_MESSAGE,HistoryIP,iplist,iplist[0]);
 				if(choicedIplist.isEmpty()){
 					break;
 				}
 				os = record.outputOSFromDevice(choicedIplist); //Get every ip's OS from database
 				try{
 					do{
 						if(os.equals("Linux")){
 							if(choicedFormat=="Microsoft Excel Format"){
 								newXlsx.createLinuxXlsxFile(record.outputRecordFromLinuxRecordByIp(choicedIplist));
 								check = true;
 							}else{
 								if(choicedFormat=="NotePad Format"){ 					 
 									newText.createLinuxTextFile(record.outputRecordFromLinuxRecordByIp(choicedIplist));
 									check = true;
 								}
 							}
 						}else{
 							if(os.equals("Windows")){
 								if(choicedFormat=="Microsoft Excel Format"){
 	 								newXlsx.createWindowsXlsxFile(record.outputRecordFromWindowsRecordByIp(choicedIplist));
 	 								check = true;
 	 							}else{
 	 								if(choicedFormat=="NotePad Format"){ 					 
 	 									newText.createWindowsTextFile(record.outputRecordFromWindowsRecordByIp(choicedIplist));
 	 									check = true;
 	 								}
 	 							}
 							}
 						}
 					}while(check==false);
 				}catch(Exception ie){
 			
 				}
 			}while(checkNull == true);
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
 		 ///////////////////////////////////////////////
	}
}
