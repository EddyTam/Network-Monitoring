package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class webAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent arg0){
		// TODO Auto-generated method stub
		try {
			java.awt.Desktop.getDesktop().browse(new URI("file:///C:/Users/Jacky/Desktop/JASE2/JASEWeb/index.html"));
		} catch (IOException | URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
