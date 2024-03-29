

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.*;


/* File: PacFrame.java
 * Start: 2010/06/07
 * Modification: 2010/06/25
 * Description: Main window.
 */
public class PacFrame extends JFrame implements Constants {
	public static JTextField msgField;
	public static GamePanel gamePanel;
	public static PlayerPanel[] playerPanel; // size 2
	public static ConnectPanel RSPanel;
	
	public PacFrame() {
		super("Pacman Online");
		initGUI();
	}
	
	public void initGameMap( Grid[][] map ) {
		gamePanel.initMap( map );
	}
	
	public void placePlayer( Player[] playerList ){
		gamePanel.placePlayer( playerList );
	}
	
	public void movePlayer( Player[] playerList ){
		for ( int i = 0 ; i < playerList.length ; i++ ){
			gamePanel.movePlayer( playerList[i] );
		}
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
			pack();
			setSize(FRAME_WIDTH, FRAME_HEIGHT);
			setResizable(false);
			createGUI();
			/* Focus on game panel when window focused */
			addWindowFocusListener(new WindowAdapter() {
			    public void windowGainedFocus(WindowEvent e) {
			        gamePanel.requestFocusInWindow();
			    }
			});
			// temp get focus on nickField
			ConnectPanel.nickField.requestFocusInWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void createGUI(){
		Container pane = this.getContentPane();
		pane.setLayout(new FlowLayout());
		/* Frame adds those big panels */
		JPanel LPanel = new JPanel(new BorderLayout()), 
			   RPanel = new JPanel(new BorderLayout());
		pane.add(LPanel);
		pane.add(new JSeparator(SwingConstants.VERTICAL));
		pane.add(RPanel);

		/*** New the 4 panel objects and add them to the big panels ***/
		gamePanel = new GamePanel();
		playerPanel = new PlayerPanel[2];
		playerPanel[0] = new PlayerPanel( (char)0, MAX_TEAM_PLAYERS );
		playerPanel[1] = new PlayerPanel( (char)1, MAX_TEAM_PLAYERS );
		RSPanel = new ConnectPanel();

		
		
		/* Adding MouseListener for game panel */
		gamePanel.addMouseListener(new MouseAdapter() { 
	          public void mousePressed(MouseEvent me) { 
	              gamePanel.requestFocusInWindow();
	          } 
	    });
		
		

		/* left part */
		LPanel.add(gamePanel, "North");
		LPanel.add(new JSeparator(SwingConstants.HORIZONTAL), "Center");		
		JPanel LSPanel = new JPanel(new BorderLayout());
		LPanel.add(LSPanel,"South");
		LSPanel.add(new JLabel("Message:"), "North");
		msgField = new JTextField();
		msgField.setEditable(false);
		msgField.setFocusable(false);
		msgField.setPreferredSize(new Dimension(PacFrame.GAME_WIDTH,40));
		msgField.setFont(new Font("Arial", Font.PLAIN, 16));
		LSPanel.add(msgField,"South");

		/* right part */

		RPanel.add(playerPanel[0], "North");
		RPanel.add(playerPanel[1], "Center");
		RPanel.add(RSPanel, "South");
	}
}
