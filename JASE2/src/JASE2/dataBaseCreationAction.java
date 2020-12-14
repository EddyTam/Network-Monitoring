package JASE2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import DatabaseMethod.*;

public class dataBaseCreationAction implements ActionListener {
public static ImageIcon TickIcon;
public static ImageIcon CrossIcon;
	@Override
	public void actionPerformed(ActionEvent arg0) {
		TickIcon = new ImageIcon("icons/tick.png");
		CrossIcon = new ImageIcon("icons/cross2.png");
		FunctionOfSql modi = new FunctionOfSql();
		DatabaseConfigIdentifier iden = new DatabaseConfigIdentifier();
		boolean config_check = iden.getIdentifier();
		boolean confirm = false;
		String databaseName ="";
		DbCreation db_cre = new DbCreation();
		DbAutoCreation cre = new DbAutoCreation();
		boolean ck = false;
		if(config_check == true){
			do{
				try{
					databaseName = (String) JOptionPane.showInputDialog(null,"Please enter your database's name ","databaseName",JOptionPane.QUESTION_MESSAGE,null,null,null);
					if(databaseName.isEmpty()){
						JOptionPane.showMessageDialog(null,"Input Empty is invaild.");
						throw new NullPointerException();
					}else{
						confirm = true;
					}
				}catch(NullPointerException t){
					break;
				}
			}while(confirm == false);
			db_cre.DbCreate(databaseName);
			modi.setDB_Name(databaseName);
			ck =cre.autoCreation();
			if(ck == true){
				JOptionPane.showMessageDialog(null, "Create table sucess","Congratulation",JOptionPane.QUESTION_MESSAGE,TickIcon);
			}else{
				JOptionPane.showMessageDialog(null, "Create table failed","Error",JOptionPane.QUESTION_MESSAGE,CrossIcon);
			}
		}else{
			JOptionPane.showMessageDialog(null, "Please Configurate the database first");
		}
		// TODO Auto-generated method stub
		
	}

}
