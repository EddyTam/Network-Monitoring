package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import DatabaseMethod.InsertRecord;

public class reportAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		InsertRecord report = new InsertRecord();
		//if(report.outputRecordFromDb(newAction.user1.getIp())){
			//report.outputRecordFromDb(newAction.user1.getIp());
			JOptionPane.showMessageDialog(null, "Success");
		//}else{
			JOptionPane.showMessageDialog(null, "Fail");
		//}
		
	}

}
