package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import jchart.piechart;

public class RefreshAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
        /*String[] all = newAction.user1.getlinall();
        String[] desc = {"Ip address : ","System Name : ","System Uptime : ","Total Ram : ","Used Ram : ",
        				"CPU Load : ","Total Harddisk space : ","Availd Harddisk space"};        		

        for(int i=0;i<all.length;i++){
        	GUI.text.setText(desc[i]+all[i]+"\n");
        }*/
		/*if(user1 <> null){
		GUI.text.setText(newAction.user1.getall());
		}else{
			GUI.text.setText(newAction.user2.getall());
		}*/
		DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
		
		newAction refreshtab = new newAction(); 
		if(newAction.userarray[CloseableTabbedPane.tabindex-1].getOS()=="Linux"){
			String [] linuxresult = newAction.userarray[CloseableTabbedPane.tabindex-1].getallfordb_linux();
			 insert.inputRecordToLinuxRecord(linuxresult);
			GUI.tab = refreshtab.createTab_linux(CloseableTabbedPane.tabindex-1);
			GUI.tabbedPane.setComponentAt(CloseableTabbedPane.tabindex, GUI.tab);
		}else if(newAction.userarray[CloseableTabbedPane.tabindex-1].getOS()=="Windows"){
			String [] windowsresult = newAction.userarray[CloseableTabbedPane.tabindex-1].getallfordb_windows();
			insert.inputRecordToLinuxRecord(windowsresult);
			GUI.tab = refreshtab.createTab_windows(CloseableTabbedPane.tabindex-1);
			GUI.tabbedPane.setComponentAt(CloseableTabbedPane.tabindex, GUI.tab);
		}else if(newAction.userarray[CloseableTabbedPane.tabindex-1].getOS()=="Router"){
			try {
				GUI.tab = refreshtab.createTab_router(CloseableTabbedPane.tabindex-1);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			GUI.tabbedPane.setComponentAt(CloseableTabbedPane.tabindex, GUI.tab);
		}
		/*newAction.text.setText(newAction.userarray[GUI.reindex].getall());
        JPanel hdpie = piechart.createDemoPanel(newAction.userarray[GUI.reindex].gettotalhd(),newAction.userarray[GUI.reindex].getavaildhd(), "Harddisk Usage");
        Double ram = Double.parseDouble(newAction.userarray[GUI.reindex].gettotalram())-Double.parseDouble(newAction.userarray[GUI.reindex].getramused());
        String sram = Double.toString(ram);
        JPanel rampie = piechart.createDemoPanel(newAction.userarray[GUI.reindex].gettotalram(),sram, "Ram Usage");
        JPanel cpupie = piechart.createDemoPanelforcpu(newAction.userarray[GUI.reindex].getcpuused(), "Cpu Usage");
        
        hdpie.setSize(newAction.panelSize);
        rampie.setSize(newAction.panelSize);
        cpupie.setSize(newAction.panelSize);      
        newAction.subPanel1.add(hdpie);
        newAction.subPanel2.add(rampie);
        newAction.subPanel3.add(cpupie);*/
		
	}

}
