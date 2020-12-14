package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DatabaseMethod.*;


public class DataBaseMenuAction implements ActionListener {
public static ImageIcon nameIcon;
public static ImageIcon passIcon;
public static ImageIcon URLicon;
public static ImageIcon tickIcon;
public static ImageIcon cross2Icon;

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		nameIcon = new ImageIcon("icons/name.png");
		passIcon = new ImageIcon("icons/security.png");
		URLicon = new ImageIcon("icons/URL.png");
		tickIcon = new ImageIcon("icons/tick.png");
		cross2Icon = new ImageIcon("icons/cross2.png");
		DatabaseConfigIdentifier iden = new DatabaseConfigIdentifier();
		String username = null;
		String password = null;
		boolean testident = false;
		String url = null;
		FunctionOfSql modi = new FunctionOfSql();
		ConnectionTesting test = new ConnectionTesting();
		boolean confirm = false;
		do{
			try{
				username= (String) JOptionPane.showInputDialog(null,"Please enter the username of the database","Username",JOptionPane.QUESTION_MESSAGE,nameIcon,null,null);
				if(username.isEmpty()){
					JOptionPane.showMessageDialog(null,"Input Empty is invaild.");
					throw new NullPointerException();
				}else{		
					password = (String) JOptionPane.showInputDialog(null,"Please enter the password of your database","Password Requirement",JOptionPane.QUESTION_MESSAGE,passIcon,null,null);
					if(password.isEmpty()){
						JOptionPane.showMessageDialog(null, "InputEmpty is invaild.");
						throw new NullPointerException();
					}else{
						url = (String) JOptionPane.showInputDialog(null,"Please enter your database's URL (e.g 127.0.0.1/databasename)","URL",JOptionPane.QUESTION_MESSAGE,URLicon,null,null);
						if(url.isEmpty()){
							JOptionPane.showMessageDialog(null, "Input Empty is invaild");
							throw new NullPointerException();
						}else{
							confirm = true;
						}
					}
				}
				
			}catch(NullPointerException t){
				break;
			}
		}while(confirm == false);
		modi.setUsername(username);
		modi.setPass(password);
		modi.setDB_URL(url);
		testident = test.ConnectionTest();
		if(testident == true){
			JOptionPane.showMessageDialog(null, "This is connected.","Congratulation",JOptionPane.QUESTION_MESSAGE,tickIcon);
			iden.setIdentifier(true);
		}else{
			JOptionPane.showMessageDialog(null,"This is not connected","Error",JOptionPane.QUESTION_MESSAGE,cross2Icon);
		}
		
	}
	

}
