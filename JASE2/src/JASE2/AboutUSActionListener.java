package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class AboutUSActionListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String title ="About Us";
		String message ="         Network Monitoring System \n \n please contact us If you have any problems \n Contact Number: 99998888 \n "
				+ "Email address: NMS@gmail.com";
		int type = JOptionPane.INFORMATION_MESSAGE;
		
		JOptionPane.showMessageDialog(null, message, title, type);;
	}


}
