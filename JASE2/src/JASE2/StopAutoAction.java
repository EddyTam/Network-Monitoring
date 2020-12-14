package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StopAutoAction implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		startautocapAction.timer.cancel();
	}

}
