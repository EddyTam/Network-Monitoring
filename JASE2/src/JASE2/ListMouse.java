package JASE2;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JList;

public class ListMouse implements MouseListener {

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
            int idx = GUI.list.locationToIndex(e.getPoint());
            Object item = GUI.listModel.getElementAt(idx);
            String target = (String) item;
            
            
            
        }
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
