package JASE2;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import java.awt.*;
import java.awt.event.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import jchart.piechart;

public class GUI extends JFrame {
	public JTree tree;
	public static DefaultListModel listModel;
	public static JPanel DisplayBox;
	public static JTextArea text;
	public static boolean RIGHT_TO_LEFT = false;
	public static JPanel panel1;
	public static Border BD_0;
	public static Border BD_1;
	public static Border BD_2;
	public static Border blackline;
	public static TitledBorder title;
	public static JPanel leftpanel;
	public static JLabel realtime;
	public static JSeparator separator;
	public static JSeparator separator1;
	public static JLabel IPAddress;
	public static JButton refresh;

	public static JScrollPane scrollPane;
	public static JPanel mainPanel;
	public static JPanel subPanel1;
	public static JPanel subPanel2;
	public static JPanel subPanel3;
	public static Dimension panelSize;
	public static JList list;
	public static JPanel tab;
	// public static CloseableTabbedPane tabbedPane;
	public static JTextArea text2;
	public static JTextArea text3;
	public static JTextArea text4;
	public static JButton mainPage;
	public static JButton filter;
	public static JButton scanIP;
	public static CloseableTabbedPane tabbedPane;
	public static ImageIcon arrow;
	public static ImageIcon Newpicture;
	public static ImageIcon refreshpic;
	public static ImageIcon reportpicture;
	public static ImageIcon clearpicture;
	public DateFormat timeFormat;
	public Timer timer;
	public Date date;
	public static JLabel banner;

	// Right Panel
	public static JPanel rightpanel;
	public static JPanel rightTop;
	public static JButton New;
	public static Border raisedbevel;
	public static Border loweredbevel;
	public static Border rightTopBorder;
	public static JButton report;
	public static JButton clear;
	// Right Panel - Center
	public static JLabel welcome;
	public static Border rightcentertopBorder;
	public static ImageIcon bg;
	public static JLabel labelBG;
	public static JLabel copyright;

	public static JPanel ButtonPanel;
	public static ImageIcon startIcon;
	public static ImageIcon stopIcon;
	public static JButton startButton;
	public static JButton stopButton;
	public static JPanel centerPanel;

	public static ImageIcon newIcon;
	public static JButton newButton;

	public static ImageIcon clearIcon;
	public static JButton clearButton;

	public static ImageIcon refreshIcon;
	public static JButton refreshButton;

	public static ImageIcon reportIcon;
	public static JButton reportButton;
	
	public static ImageIcon loggingpicture;
	public static JButton loggingButton;
	
	public static ImageIcon systemLog;
	public static JButton systemButton;

	public static void addComponentsToPane(Container contentPane) {
		// Use BorderLayout. Default empty constructor with no horizontal and
		// vertical
		// gaps
		contentPane.setLayout(new BorderLayout(5, 5));
		// contentPane.setBackground(Color.white);

		if (!(contentPane.getLayout() instanceof BorderLayout)) {
			contentPane.add(new JLabel("Container doesn't use BorderLayout!"));
			return;
		}

		if (RIGHT_TO_LEFT) {
			contentPane
					.setComponentOrientation(java.awt.ComponentOrientation.RIGHT_TO_LEFT);
		}

		banner = new JLabel(new ImageIcon("icons/banner.png"));
		contentPane.add(banner, BorderLayout.PAGE_START);
		// ////////////////////////////////////////////////////////
		centerPanel = new JPanel(new BorderLayout());
		ButtonPanel = new JPanel(new GridBagLayout());
		startIcon = new ImageIcon("icons/start.png");
		stopIcon = new ImageIcon("icons/stop.png");
		newIcon = new ImageIcon("icons/document3.png");
		clearIcon = new ImageIcon("icons/clear3.png");
		reportIcon = new ImageIcon("icons/report3.png");
		refreshIcon = new ImageIcon("icons/refresh3.png");
		loggingpicture = new ImageIcon("icons/logging.png");
		systemLog = new ImageIcon("icons/system2.png");

		startButton = new JButton(startIcon);
		startButton.setBorderPainted(false);
		// startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		// set the button transparent
		startButton.addActionListener(new startautocapAction());
		startButton.setToolTipText("Start Auto capture");

		stopButton = new JButton(stopIcon);
		stopButton.setBorderPainted(false);
		// set Border
		// stopButton.setOpaque(false);
		stopButton.setContentAreaFilled(false);
		stopButton.addActionListener(new StopAutoAction());
		stopButton.setToolTipText("Stop Auto Capture");

		newButton = new JButton(newIcon);
		newButton.setBorderPainted(false);
		// stopButton.setOpaque(false);
		newButton.setContentAreaFilled(false);
		newButton.setToolTipText("New");
		newButton.addActionListener(new newAction());

		clearButton = new JButton(clearIcon);
		clearButton.setBorderPainted(false);
		// stopButton.setOpaque(false);
		clearButton.setContentAreaFilled(false);
		clearButton.setToolTipText("Clear");
		clearButton.addActionListener(new clearlistAction());

		reportButton = new JButton(reportIcon);
		reportButton.setBorderPainted(false);
		// stopButton.setOpaque(false);
		reportButton.setContentAreaFilled(false);
		reportButton.setToolTipText("History");
		reportButton.addActionListener(new choicesReportAction());

		refreshButton = new JButton(refreshIcon);
		refreshButton.setBorderPainted(false);
		// stopButton.setOpaque(false);
		refreshButton.setContentAreaFilled(false);
		refreshButton.setToolTipText("Refresh");
		refreshButton.addActionListener(new RefreshAction());
		
		loggingButton = new JButton(loggingpicture);
		loggingButton.setBorderPainted(false);
		// startButton.setOpaque(false);
		loggingButton.setContentAreaFilled(false);
		// set the button transparent
		loggingButton.addActionListener(new LoggingRecordsAction());
		loggingButton.setToolTipText("Logging records");
		
		systemButton = new JButton(systemLog);
		systemButton.setBorderPainted(false);
		// startButton.setOpaque(false);
		systemButton.setContentAreaFilled(false);
		// set the button transparent
		systemButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
			
				JFrame linux = new JFrame(newAction.userarray[tabbedPane.tabindex-1].getIp());
				JPanel linuxPanel = new JPanel();
				JLabel cputext = new JLabel();
				System.out.println('3');
				int num1 = newAction.userarray[tabbedPane.tabindex-1].getCpulimit();
				String num2 = Integer.toString(num1);
				cputext.setText(num2 +"%");
				System.out.println('4');
				JTextField newNumber = new JTextField();
				newNumber.setText(null);
				newNumber.setEditable(true);
				
				JLabel newValue = new JLabel("New Value");
				
				
			
				JLabel currentValue = new JLabel("The current CPU limit value is : ");
				JTabbedPane tab = new JTabbedPane();
				JPanel cpuTab = new JPanel(new GridLayout(6,1));
				cpuTab.setName("CPU Limit Value");
				cpuTab.add(currentValue);
				cpuTab.add(cputext );
				cpuTab.add(newValue);
				cpuTab.add(newNumber);
				System.out.println('4');
				JButton reset = new JButton("Reset value");
				reset.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String number1 = newNumber.getText();
						int number2 = Integer.parseInt(number1);
						
						if (number2 >= 0 && number2 <=100){
							
							newAction.userarray[tabbedPane.tabindex-1].setCpulimit(number2);
							String number3 = Integer.toString(number2);
							cputext.setText(number3+"%");
							newNumber.setText(null);
							
						}
							
						
					}
					
				});
				cpuTab.add(reset);
				tab.add(cpuTab);
				///////
				JLabel ramtext = new JLabel();
				int num10 = newAction.userarray[tabbedPane.tabindex-1].getRamlimit();
				String num20 = Integer.toString(num10);
				ramtext.setText(num20 +"%");
				JTextField newNumber1 = new JTextField();
				newNumber1.setText(null);
				newNumber1.setEditable(true);
				
				JLabel newValue1 = new JLabel("New Value");
				
				
			
				JLabel currentValue1 = new JLabel("The current RAM limit value is : ");
				
				JPanel ramTab = new JPanel(new GridLayout(6,1));
				ramTab.setName("RAM Limit Value");
				ramTab.add(currentValue1);
				ramTab.add(ramtext );
				ramTab.add(newValue1);
				ramTab.add(newNumber1);
				System.out.println('4');
				JButton reset1 = new JButton("Reset value");
				reset1.addActionListener(new ActionListener(){
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String number10 = newNumber1.getText();
						int number20 = Integer.parseInt(number10);
						
						if (number20 >= 0 && number20 <=100){
							
							newAction.userarray[tabbedPane.tabindex-1].setRamlimit(number20);
							String number30 = Integer.toString(number20);
							ramtext.setText(number30+"%");
							newNumber1.setText(null);
							
						}
							
						
					}
					
				});
				
				ramTab.add(reset1);
				tab.add(ramTab);
				
				linuxPanel.add(tab);
				linux.add(linuxPanel);
				linux.setSize(new Dimension(400,300));
				linux.setLocation(500, 400);
				linux.setResizable(false);
				linux.pack();
				linux.show();
				linux.setVisible(true);
			}
			
			
		});
		systemButton.setToolTipText("Alert setting");

		GridBagConstraints c100 = new GridBagConstraints();
		c100.gridx = 0;
		c100.gridy = 0;
		c100.gridwidth = 1;
		c100.gridheight = 1;
		c100.weightx = 0.1;
		c100.weighty = 0.05;
		c100.fill = GridBagConstraints.WEST;
		c100.anchor = GridBagConstraints.WEST;

		GridBagConstraints c101 = new GridBagConstraints();
		c101.gridx = 1;
		c101.gridy = 0;
		c101.gridwidth = 1;
		c101.gridheight = 1;
		c101.weightx = 15;
		c101.weighty = 0.05;
		c101.fill = GridBagConstraints.WEST;
		c101.anchor = GridBagConstraints.WEST;

		GridBagConstraints c102 = new GridBagConstraints();
		c102.gridx = 2;
		c102.gridy = 0;
		c102.gridwidth = 1;
		c102.gridheight = 1;
		c102.weightx = 70;
		c102.weighty = 0.05;
		c102.fill = GridBagConstraints.WEST;
		c102.anchor = GridBagConstraints.WEST;

		GridBagConstraints c103 = new GridBagConstraints();
		c103.gridx = 3;
		c103.gridy = 0;
		c103.gridwidth = 1;
		c103.gridheight = 1;
		c103.weightx = 1400;
		c103.weighty = 0.1;
		c103.fill = GridBagConstraints.WEST;
		c103.anchor = GridBagConstraints.WEST;

		GridBagConstraints c104 = new GridBagConstraints();
		c104.gridx = 4;
		c104.gridy = 0;
		c104.gridwidth = 1;
		c104.gridheight = 1;
		c104.weightx = 5000;
		c104.weighty = 0.05;
		c104.fill = GridBagConstraints.WEST;
		c104.anchor = GridBagConstraints.WEST;

		GridBagConstraints c105 = new GridBagConstraints();
		c105.gridx = 5;
		c105.gridy = 0;
		c105.gridwidth = 1;
		c105.gridheight = 1;
		c105.weightx = 200000;
		c105.weighty = 0.05;
		c105.fill = GridBagConstraints.WEST;
		c105.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints c106 = new GridBagConstraints();
		c106.gridx = 6;
		c106.gridy = 0;
		c106.gridwidth = 1;
		c106.gridheight = 1;
		c106.weightx = 8000000;
		c106.weighty = 0.05;
		c106.fill = GridBagConstraints.WEST;
		c106.anchor = GridBagConstraints.WEST;
		
		GridBagConstraints c107 = new GridBagConstraints();
		c107.gridx = 7;
		c107.gridy = 0;
		c107.gridwidth = 1;
		c107.gridheight = 1;
		c107.weightx = 999999999;
		c107.weighty = 0.05;
		c107.fill = GridBagConstraints.WEST;
		c107.anchor = GridBagConstraints.WEST;


		ButtonPanel.add(startButton, c100);
		ButtonPanel.add(stopButton, c101);
		ButtonPanel.add(newButton, c102);
		ButtonPanel.add(clearButton, c103);
		ButtonPanel.add(refreshButton, c104);
		ButtonPanel.add(reportButton, c105);
		ButtonPanel.add(loggingButton, c106);
		ButtonPanel.add(systemButton, c107);
		centerPanel.add(ButtonPanel, BorderLayout.PAGE_START);
		contentPane.add(centerPanel, BorderLayout.CENTER);
		// ////////////////////////////////////////////////////////

		leftpanel = new JPanel(new GridBagLayout());
		arrow = new ImageIcon("icons/arrow.png");
		mainPage = new JButton("Main Page       ", arrow);
		mainPage.setFont(new Font("Serif", Font.PLAIN, 18)); // set font
																// style,size
		mainPage.setHorizontalTextPosition(SwingConstants.LEFT); // align text
																	// left
		mainPage.setSize(50, 50);
		mainPage.setCursor(new Cursor(Cursor.HAND_CURSOR));// mouse style on the
															// button area
		mainPage.addActionListener(new mainpage());
		GridBagConstraints c0 = new GridBagConstraints();
		c0.gridx = 0;
		c0.gridy = 0;
		c0.gridwidth = 1;
		c0.gridheight = 1;
		c0.weightx = 0;
		c0.weighty = 0.05;
		c0.fill = GridBagConstraints.BOTH;
		c0.anchor = GridBagConstraints.NORTH;
		mainPage.setOpaque(true);
		// mainPage.setBackground(Color.cyan);
		leftpanel.add(mainPage, c0);

		filter = new JButton("  Usage Trend    ", arrow);
		filter.setFont(new Font("Serif", Font.PLAIN, 18));
		filter.setHorizontalTextPosition(SwingConstants.LEFT);
		filter.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints c1 = new GridBagConstraints();
		c1.gridx = 0;
		c1.gridy = 1;
		c1.gridwidth = 1;
		c1.gridheight = 1;
		c1.weightx = 0;
		c1.weighty = 0.05;
		c1.fill = GridBagConstraints.BOTH;// Full the space
		c1.anchor = GridBagConstraints.NORTH;
		filter.setOpaque(true);// set the
		// filter.setBackground(Color.cyan);
		leftpanel.add(filter, c1);
		filter.addActionListener(new filterAction());

		scanIP = new JButton("Scan IP Address  ", arrow);
		scanIP.setFont(new Font("Serif", Font.PLAIN, 18));
		scanIP.setHorizontalTextPosition(SwingConstants.LEFT);
		scanIP.setCursor(new Cursor(Cursor.HAND_CURSOR));

		GridBagConstraints c80 = new GridBagConstraints();
		c80.gridx = 0;
		c80.gridy = 3;
		c80.gridwidth = 1;
		c80.gridheight = 1;
		c80.weightx = 0;
		c80.weighty = 0.05;
		c80.fill = GridBagConstraints.BOTH;
		c80.anchor = GridBagConstraints.NORTH;
		scanIP.setOpaque(true);
		// autoCapture.setBackground(Color.cyan);
		leftpanel.add(scanIP, c80);
		scanIP.addActionListener(new scanIPAction());

		separator = new JSeparator(SwingConstants.HORIZONTAL);
		GridBagConstraints c3 = new GridBagConstraints();
		c3.gridx = 0;
		c3.gridy = 3;
		c3.gridwidth = 1;
		c3.gridheight = 1;
		c3.weightx = 0;
		c3.weighty = 0;
		c3.anchor = GridBagConstraints.CENTER;
		leftpanel.add(separator, c3);

		IPAddress = new JLabel("IP Addresses");
		GridBagConstraints c4 = new GridBagConstraints();
		c4.gridx = 0;
		c4.gridy = 4;
		c4.gridwidth = 1;
		c4.gridheight = 1;
		c4.weightx = 1;
		c4.weighty = 0.01;
		c4.fill = GridBagConstraints.NONE;
		c4.anchor = GridBagConstraints.CENTER;
		leftpanel.add(IPAddress, c4);

		listModel = new DefaultListModel();
		list = new JList(listModel);
		list.addMouseListener(new ListMouse());
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		scrollPane = new JScrollPane(list);
		// scrollPane.setHorizontalScrollBarPolicy(
		// JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		// scrollPane.setVerticalScrollBarPolicy(
		// JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		GridBagConstraints c5 = new GridBagConstraints();
		c5.gridx = 0;
		c5.gridy = 5;
		c5.gridwidth = 1;
		c5.gridheight = 1;
		c5.weightx = 1;
		c5.weighty = 0.005;
		c5.fill = GridBagConstraints.NONE;
		c5.anchor = GridBagConstraints.CENTER;
		leftpanel.add(scrollPane, c5);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent evt) {

				if (evt.getClickCount() == 2) {
					String tabName = tabbedPane.getTabTitleAt(tabbedPane
							.getSelectedIndex());
					int index = list.locationToIndex(evt.getPoint());
					if (tabName.equals(newAction.userarray[index].getIp())) {
						int num = tabbedPane.getTabPlacement();
						tabbedPane.setSelectedIndex(num);
					} else {
						newAction a = new newAction();
						if(newAction.userarray[index].getOS()=="Linux"){
						tab = a.createTab_linux(index);
						tabbedPane.addTab(newAction.userarray[index].getIp(),
								tab);
						// newAction.usercount++;
						int num = tabbedPane.getTabPlacement();
						tabbedPane.setSelectedIndex(num);
						}else{
							if(newAction.userarray[index].getOS()=="Windows"){
							//get the IP in the tab label
								tab = a.createTab_windows(index);
							tabbedPane.addTab(newAction.userarray[index].getIp(),
									tab);
							// newAction.usercount++;
							int num = tabbedPane.getTabPlacement();
							tabbedPane.setSelectedIndex(num);
						}else{
							if(newAction.userarray[index].getOS()=="Router"){
								try {
									tab = a.createTab_router(index);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								tabbedPane.addTab(newAction.userarray[index].getIp(),tab);
								int num = tabbedPane.getTabPlacement();
								tabbedPane.setSelectedIndex(num);
							}else{
								if(newAction.userarray[index].getOS()=="Switch"){
									try {
										tab = a.createTab_switch(index);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									tabbedPane.addTab(newAction.userarray[index].getIp(),
											tab);
									// newAction.usercount++;
									int num = tabbedPane.getTabPlacement();
									tabbedPane.setSelectedIndex(num);
								}
							}
						}
						}
					}

				} else {
					if (evt.getClickCount() == 1) {
						CloseableTabbedPane.tabindex = list.locationToIndex(evt
								.getPoint()) + 1;
						tabbedPane
								.setSelectedIndex(CloseableTabbedPane.tabindex);
						// when click the IP address from left panel, then the tab will be correspondence
					}
				}
			}
		});

		realtime = new JLabel();
		GridBagConstraints c6 = new GridBagConstraints();
		c6.gridx = 0;
		c6.gridy = 6;
		c6.gridwidth = 10;
		c6.gridheight = 5;
		c6.weightx = 1;
		c6.weighty = 0.01;
		c6.fill = GridBagConstraints.VERTICAL;
		c6.anchor = GridBagConstraints.CENTER;
		DateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");//set the time
		ActionListener timerListener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date date = new Date();
				String time = timeFormat.format(date);
				realtime.setText("Time: " + time);
			}
		};
		Timer timer = new Timer(1000, timerListener);
		// to make sure it doesn't wait one second at the start
		timer.setInitialDelay(0);
		timer.start();
		leftpanel.add(realtime, c6);
		// leftpanel.setBackground(Color.pink);
		raisedbevel = BorderFactory.createRaisedBevelBorder();
		leftpanel.setBorder(raisedbevel);
		centerPanel.add(leftpanel, BorderLayout.LINE_START);

		// //////////////////////////////////////////////////////
		// Right Panel
		rightpanel = new JPanel(new BorderLayout());
		// rightpanel.setBackground(Color.white);
		blackline = BorderFactory.createLineBorder(Color.black);
		rightpanel.setBorder(blackline);
		// Right Panel - Top position

		// raisedbevel = BorderFactory.createRaisedBevelBorder(); //å¤–åœ�ç·š
		// functions
		// loweredbevel = BorderFactory.createLoweredBevelBorder();
		// rightTopBorder = BorderFactory.createCompoundBorder( // combine two
		// Boarder line functions
		// raisedbevel, loweredbevel);
		// rightTop.setBorder(rightTopBorder); // set the boarder in specific
		// position
		// rightpanel.add(rightTop,BorderLayout.PAGE_START); // show it on frame

		// Right Panel - Center
		tabbedPane = new CloseableTabbedPane(); // create Tab
		// tabbedPane.setBackground(Color.cyan);
		mainPanel = new JPanel(new BorderLayout());
		// mainPanel.setBackground(Color.white);
		rightcentertopBorder = BorderFactory.createCompoundBorder(raisedbevel,
				loweredbevel);
		mainPanel.setBorder(rightcentertopBorder);
		welcome = new JLabel(
				
				 	
				
				
				
				
				"<html><font color='blue' size='6'>What is Network Monitoring?<br></font>"
						+ "<font size='3'>In network management terms, network monitoring is the phrase used to describe a system that continuously monitors a netowrk and notifies a network<br>"
					
						+ "administartor though messaging system(usually e-mail) when a device fails or an outage occurs."
						+ "Netowrk monitoring is usually performed through the use of software application and tools.</font> </html>");
						
				
				
		bg = new ImageIcon("icons/europe.png");
		labelBG = new JLabel(bg, labelBG.CENTER);
		mainPanel.add(labelBG, BorderLayout.CENTER);
		copyright = new JLabel(
				"Copyright © 2015 JASE co.  All rights reserved",
				copyright.RIGHT);
		mainPanel.add(copyright, BorderLayout.PAGE_END);

		mainPanel.add(welcome, BorderLayout.PAGE_START);
		tabbedPane.addTab("Main Page", mainPanel);
		rightpanel.add(tabbedPane, BorderLayout.CENTER);
		centerPanel.add(rightpanel, BorderLayout.CENTER);
	}

	public GUI() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame mainFrame = new JFrame("JASE - Network Monitoring System"); // Topics
																			// Name
																			// (Network
																			// Monitoring
																			// System)

		// SetMenuBar Functions--start
		JMenuBar MBar = new JMenuBar();
		// MBar.setBackground(Color.cyan);
		MBar.setOpaque(true);
		JMenu MFile = MenuBarFile();
		// MFile.setBackground(Color.red);
		MBar.add(MFile);
		JMenu MEdit = MenuBarEdit();
		// MEdit.setBackground(Color.yellow);
		MBar.add(MEdit);
		JMenu MView = MenuBarView();
		// MView.setBackground(Color.green);
		MBar.add(MView);
		JMenu MHelp = MenuBarHelp();
		// MHelp.setBackground(Color.blue);
		MBar.add(MHelp);
		mainFrame.setJMenuBar(MBar);
		// end

		// mainFrame.addWindowListener(new WindowAdapter() {
		// public void windowClosing(WindowEvent e){
		// System.exit(0);
		// }
		// });

		// Set up the content pane and add swing components to it
		addComponentsToPane(mainFrame.getContentPane());
		mainFrame.setLocation(450, 180); // position
		mainFrame.setPreferredSize(new Dimension(800, 650)); // start size
		mainFrame.pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		mainFrame.setVisible(true); // show it Frame

	}

	public JMenu MenuBarHelp() { // create Help Menu Bar items
		JMenu Help = new JMenu("Help");
		JMenuItem aboutUs = new JMenuItem("About Us", new ImageIcon(
				"icons/AboutUs.png"));
		Help.add(aboutUs);
		aboutUs.addActionListener(new AboutUSActionListener());
		JMenuItem web = new JMenuItem("WebPage", new ImageIcon("icons/web.png"));
		Help.add(web);
		web.addActionListener(new webAction());
		return Help;
	}

	public JMenu MenuBarView() { // create View Menu Bar items
		JMenu View = new JMenu("View");

		JMenu AutoCap = new JMenu("Auto Capture");
		View.add(AutoCap);
		JMenuItem startMenu = new JMenuItem("Start Capture", new ImageIcon(
				"icons/start.png"));
		AutoCap.add(startMenu);
		startMenu.addActionListener(new startautocapAction());
		JMenuItem stopMenu = new JMenuItem("Stop Capture", new ImageIcon(
				"icons/stop.png"));
		stopMenu.addActionListener(new StopAutoAction());
		AutoCap.add(stopMenu);
		return View;
	}

	public JMenu MenuBarEdit() { // create Edit Menu Bar items
		JMenu Edit = new JMenu("Edit");

		JMenuItem dataBaseMenu = new JMenuItem("DataBase " + "\n"
				+ " Configuration", new ImageIcon("icons/spanner.png"));
		Edit.add(dataBaseMenu);
		dataBaseMenu.addActionListener(new DataBaseMenuAction());

		JMenuItem dataBaseCreation = new JMenuItem("DataBase Auto Creation",
				new ImageIcon("icons/copy.png"));
		Edit.add(dataBaseCreation);
		dataBaseCreation.addActionListener(new dataBaseCreationAction());
		JMenuItem DeleteRecordAction = new JMenuItem("Delete Specific record", new ImageIcon(
				"icons/cross.png"));
		Edit.add(DeleteRecordAction);
		DeleteRecordAction.addActionListener(new DeleteRecordAction());
		JMenuItem clearMenu = new JMenuItem("Clear", new ImageIcon(
				"icons/clear.png"));
		Edit.add(clearMenu);
		clearMenu.addActionListener(new clearlistAction());

		return Edit;
	}

	public JMenu MenuBarFile() { // // create File Menu Bar items
		JMenu File = new JMenu("File");
		JMenuItem New = new JMenuItem("New",
				new ImageIcon("icons/document.png"));
		New.addActionListener(new newAction()); // doing action on (class :
												// newAction)
		File.add(New);

		JMenuItem reportMenu = new JMenuItem("History", new ImageIcon(
				"icons/report1.png"));
		File.add(reportMenu);
		reportMenu.addActionListener(new choicesReportAction());
		File.addSeparator(); // 橫線
		JMenuItem Exit = new JMenuItem("Exit", new ImageIcon("icons/exit1.png"));
		File.add(Exit);
		Exit.addActionListener(new ExitActionListener()); // doing action on (
															// Class:
															// ExitActionListener
															// )
		return File;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager
							.setLookAndFeel("com.jtattoo.plaf.noire.NoireLookAndFeel");
				} catch (Exception ex) {

				}
				new GUI();

			}
		});
	}
}
