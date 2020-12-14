package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DatabaseMethod.*;

public class DeleteRecordAction implements ActionListener {
	public String choicedIplist;
	public static ImageIcon IPpicture;
	@Override
	public void actionPerformed(ActionEvent e) {
		IPpicture = new ImageIcon("icons/Ip.png");
		boolean ck = true;
		DatabaseConfigIdentifier iden = new DatabaseConfigIdentifier();
		boolean config_check = iden.getIdentifier();
		int confirm = 0;
		String os = "";
		if(config_check == true){
		// TODO Auto-generated method stub
			OutputRecord record = new OutputRecord();
			DeleteRecord deleterecord = new DeleteRecord();
			final String [] iplist = record.outputRecordFromDeviceForSelection();
			choicedIplist = (String)JOptionPane.showInputDialog(null,"Please choose the IP","IP List",JOptionPane.QUESTION_MESSAGE,IPpicture,iplist,iplist[0]);
			confirm = JOptionPane.showConfirmDialog(null, "Do you want to delete "+choicedIplist,"Confirmation",JOptionPane.YES_NO_OPTION);
			
			if (confirm == 0){
				os = record.outputOSFromDevice(choicedIplist);
				if(os=="Linux"){
					ck = deleterecord.deleteRecordFromLinuxRecord(choicedIplist);
				}else{
					if(os=="Windows"){
						ck = deleterecord.deleteRecordFromWindowRecord(choicedIplist);
					}
				}
					if(ck==true){
						JOptionPane.showMessageDialog(null, "Delete success");
					}else{
						JOptionPane.showMessageDialog(null, "Delete failed");
					}
				}else{
				
			}
		}else{
			JOptionPane.showMessageDialog(null,"Please config the database first");
		}
	}

}
