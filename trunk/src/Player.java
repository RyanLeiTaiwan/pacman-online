import java.awt.event.KeyEvent;

import javax.swing.JLabel;


public class Player {
	public static int PACMAN = 0,
					  MONSTER = 1;
	public final static int SIZE = 20;
	
	int x, y;
	int gridx, gridy;
	String iconSrc;
	int type;
	int direction;
	JLabel image;
	
	Player( int x, int y, int type ) {
		this.x = x;
		this.y = y;
		this.type = type;
		direction = KeyEvent.VK_LEFT;
		updateGrid();
	}
	
	public void updateGrid(){
		int offsetx	= x % SIZE,
			offsety = y % SIZE;
		gridx = x / SIZE;
		gridy = y / SIZE;
		if ( offsetx > SIZE * 0.5 )
			gridx++;
		if ( offsety > SIZE * 0.5 )
			gridy++;
	}
}
