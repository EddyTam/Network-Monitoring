package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

public class startautocapAction implements ActionListener {
	public static Timer timer;
	@Override
	public void actionPerformed(ActionEvent e) {
		
		DatabaseMethod.InsertRecord insert = new DatabaseMethod.InsertRecord();
		timer = new Timer();
		timer.schedule( new TimerTask() {
			public void run(){
				newAction refreshtab = new newAction(); 
				if(newAction.userarray[CloseableTabbedPane.tabindex-1].getOS()=="Linux"){
					String [] linuxresult = newAction.userarray[CloseableTabbedPane.tabindex-1].getallfordb_linux();
					insert.inputRecordToLinuxRecord(linuxresult);
					GUI.tab = refreshtab.createTab_linux(CloseableTabbedPane.tabindex-1);
					GUI.tabbedPane.setComponentAt(CloseableTabbedPane.tabindex, GUI.tab);
				}else if(newAction.userarray[CloseableTabbedPane.tabindex-1].getOS()=="Windows"){
					String [] windowsresult = newAction.userarray[CloseableTabbedPane.tabindex-1].getallfordb_windows();
					insert.inputRecordToWindowRecord(windowsresult);
					GUI.tab = refreshtab.createTab_windows(CloseableTabbedPane.tabindex-1);
					GUI.tabbedPane.setComponentAt(CloseableTabbedPane.tabindex, GUI.tab);
				}else if(newAction.userarray[CloseableTabbedPane.tabindex-1].getOS()=="Router"){
					try {
						GUI.tab = refreshtab.createTab_router(CloseableTabbedPane.tabindex-1);
						GUI.tabbedPane.setComponentAt(CloseableTabbedPane.tabindex, GUI.tab);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}else if(newAction.userarray[CloseableTabbedPane.tabindex-1].getOS()=="Switch"){
						try {
							GUI.tab = refreshtab.createTab_switch(CloseableTabbedPane.tabindex-1);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						GUI.tabbedPane.setComponentAt(CloseableTabbedPane.tabindex, GUI.tab);
					}
				}
		
			
		}, 0, 300*25);
	}

}
