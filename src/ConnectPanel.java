/* File: ConnectPanel.java
 * Start: 2010/06/07
 * Modification: 2010/06/25
 * Description: The bottom-right JPanel for connection establishment.
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.*;
import java.net.*;

public class ConnectPanel extends JPanel implements Constants, ActionListener {
	JPanel nickPanel, clientPanel, serverPanel, csPanel, finalPanel;
	public static JTextField nickField;
	public static JTextField addrField;
	public static JButton clientButton, serverButton, lockButton, finalButton;


	ConnectPanel() {
		/* The GUI */
		GUI();
		addListeners();
	}	

	private void GUI() {
		this.setLayout(new BorderLayout());
		/* Nickname */
		nickPanel = new JPanel();
		add(nickPanel, "North");
		nickPanel.add(new JLabel("Nickname", SwingConstants.RIGHT));
		nickField = new JTextField(10);
		nickPanel.add(nickField);

		/* Client-Server */
		csPanel = new JPanel(new BorderLayout());
		add(csPanel, "Center");
		
		/* Client */
		clientPanel = new JPanel(new GridBagLayout());
		clientPanel.setBorder(BorderFactory.createTitledBorder("Client"));
		csPanel.add(clientPanel, "North");
		GridBagConstraints gbc1 = new GridBagConstraints(); // for (width,height) = (1,1)
		gbc1.gridwidth = 1;
		gbc1.gridheight = 1;
		GridBagConstraints gbc2 = new GridBagConstraints(); // for (width,height) = (2,1)
		gbc2.gridwidth = 2;
		gbc2.gridheight = 1;

		gbc1.gridx = 0;
		gbc1.gridy = 0;
		clientPanel.add(new JLabel("Address", SwingConstants.RIGHT), gbc1);
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		addrField = new JTextField(10);
		clientPanel.add(addrField, gbc2);
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		clientButton = new JButton("Connect");
		clientPanel.add(clientButton, gbc1);
		
		/* Server */
		serverPanel = new JPanel(new GridBagLayout());
		serverPanel.setBorder(BorderFactory.createTitledBorder("Server"));
		csPanel.add(serverPanel, "South");
		
		gbc1.gridx = 1;
		gbc1.gridy = 0;
		serverButton = new JButton("  Create  ");
		serverPanel.add(serverButton, gbc1);
		gbc1.gridx = 1;
		gbc1.gridy = 1;
		lockButton = new JButton(" Lock Room ");
		lockButton.setEnabled(false);
		serverPanel.add(lockButton, gbc1);
		
		/* Ready / Start Game */
		finalPanel = new JPanel();
		add(finalPanel, "South");		
		finalButton = new JButton("Ready");
		finalButton.setEnabled(false);
		finalButton.setFont(new Font("Arial Black", Font.PLAIN, 24));
		finalButton.setForeground(Color.blue);
		finalPanel.add(finalButton);
	}
	
	private void addListeners() {
		clientButton.addActionListener(this);
		serverButton.addActionListener(this);
		finalButton.addActionListener(this);
	}
	
	public void actionPerformed(ActionEvent evt) {
		/* Check for empty nickname */
		String nickname = nickField.getText();
		if (nickname.equals("")) {
			JOptionPane.showMessageDialog(this.getParent(), "You must type a nickname!", 
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}
		/* RULE: Nicknames should have length <= MAX_NICKNAME_LENGTH */
		else if (nickname.length() > MAX_NICKNAME_LENGTH) {
			JOptionPane.showMessageDialog(this.getParent(), 
					"Nicknames should have lengths <= " + MAX_NICKNAME_LENGTH, 
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;			
		}
		
		JButton src = (JButton)evt.getSource();
		/* Server: Create a game */
		if (src == serverButton) {			
			try {
				/* new a ServerThread to do the I/O blocking jobs */
				new ServerThread(this);

			} catch (Exception e) {
				Utility.error(e);
			}
			PacmanOnline.isServer=true;
			// ryanlei: comment this
			//finalButton.setEnabled(true);
		}
		/* Client: Connect to a server */
		else if (src == clientButton) {
			String addr = addrField.getText();
			/* default address is "localhost" */
			if (addr.equals("")) {
				addr = "localhost";
			}
			
			try {
				new ClientThread(this, addr);
			}
			catch (Exception e) {
				Utility.error(e);
			}
			PacmanOnline.isServer=false;
			// ryanlei: comment this
			//finalButton.setEnabled(true);
			
		}
		/*** ryanlei: I suggest this branch be migrated to both clientThread and serverThread ***/
		else if (src == finalButton) {
			if(PacmanOnline.isServer){
				PacmanOnline.inst.gamePanel.addKeyListener(new DirectionListener());
				
				PacmanOnline.movingTimer = new Timer( MOVING_TIMER_PERIOD, new MovingListener() );
				PacmanOnline.movingTimer.start();
				// ryanlei: comment this
				//finalButton.setEnabled(false);
				PacmanOnline.isReady=true;
			}else{
				//////////////////////////////////////////
				/* Client will not need this, all the moving depend on server. */
				PacmanOnline.movingTimer = new Timer( MOVING_TIMER_PERIOD, new MovingListener() );
				PacmanOnline.movingTimer.start();
				//////////////////////////////////////////
				// ryanlei: comment this
				//finalButton.setEnabled(false);
				PacmanOnline.isReady=true;
			}
		}
	}
}

