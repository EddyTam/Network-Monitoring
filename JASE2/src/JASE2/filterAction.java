package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

import DatabaseMethod.OutputRecord;
import DatabaseMethod.OutputToText;
import DatabaseMethod.OutputToXlsx;

import java.util.*;
import java.text.*;

import jchart.linechart;

public class filterAction implements ActionListener {
	public static String choicedIplist;
	public static String choicedInfolist;
	public static String infolist;
	public static ImageIcon icon;
	public static ImageIcon day;
	public static ImageIcon deviceType;
	public static String[] dateArray; 
	@Override
	public void actionPerformed(ActionEvent e) {
		// define the Date Format
		String str = null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		dateArray = new String[7];
		// TODO Auto-generated method stub
		OutputRecord a = new OutputRecord();
 		String [] b = a.outputRecordFromDeviceForSelection();
 		/*String [] c = new String[b.length];
 		  for(int i=0;i<b.length;i++){
 			c[i]= b[i][0]+"/"+b[i][1];
 		}*/
 		final String [] iplist = b;
 		String [] resultlist = new String[7];
 		final String [] infolist = {"CPU Load","Ram Load"};
 		icon = new ImageIcon("icons/IP.png");
 		day = new ImageIcon("icons/day.png");
 		deviceType = new ImageIcon("icons/hardware.png"); 
 		DatabaseConfigIdentifier iden = new DatabaseConfigIdentifier();
 		boolean config_check = iden.getIdentifier();
 		if(config_check == true){
 		OutputRecord record = new OutputRecord();
 		choicedIplist = (String)JOptionPane.showInputDialog(null,"Please choose the IP","IP List",JOptionPane.QUESTION_MESSAGE,icon,iplist,iplist[0]);
 		choicedInfolist = (String)JOptionPane.showInputDialog(null,"Please choose the type","Info List",JOptionPane.QUESTION_MESSAGE,deviceType,infolist,infolist[0]);

 			str =(String) JOptionPane.showInputDialog(null,"Please input the day by (yyyy-MM-dd)","Day",JOptionPane.QUESTION_MESSAGE,day,null,null);
 		
 			try{
 				if(str.charAt(4)!= '-' || str.charAt(7)!='-'){
 					throw new Exception();
 				}else{
				
 				}
 			}catch(Exception ex){
 				JOptionPane.showMessageDialog(null,"Please input the correct date format");
 			}
 			try{
 				for (int i=6; i>=0;i--){
 					dateArray[i] = str;
 					resultlist[i] =record.ouputAvgFromRecord(str, choicedIplist, choicedInfolist);
 					Date dt = sdf.parse(str);
 					Calendar rightNow = Calendar.getInstance();
 					rightNow.setTime(dt);
 					rightNow.add(Calendar.DAY_OF_YEAR,-1);// date + 1;
 					Date dt1=rightNow.getTime();
 					str = sdf.format(dt1);
 				}
 			}catch(Exception eo){
 			
 			}

 			final linechart crline = new linechart(choicedInfolist,resultlist,dateArray);
		
 			RefineryUtilities.centerFrameOnScreen(crline);
 			crline.setVisible(true);
 			crline.pack();
	}else{
			JOptionPane.showMessageDialog(null,"Please configurate the database first.");
		}
	}
}
