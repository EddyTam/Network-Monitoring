package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class clearlistAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		newAction.usercount = 0;
		newAction.userarray = new User[10];
		GUI.listModel.removeAllElements();
		int num = GUI.tabbedPane.getTabPlacement();
		GUI.tabbedPane.remove(num);
		
	}
	
}
