package blackJack_Game;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
/**
 * You may need to run and close the program a few times before it loads correctly for some reason
 */
public class CreateDeckRunner {
	
	public static void main(String[] args) throws IOException {
		System.out.println("Program is runnning...");
		
		//Creates the graphical user interface
		GUI gui = new GUI();
		
		//Refreshes the GUI until program is done
		while (true) {
			gui.repaint();
		}
		
	
	}


}
