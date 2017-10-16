package minimax;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Main {
	
	Computer player1 = new Computer(1);
	JFrame mainFrame = new JFrame("TicTac");
	JPanel mainPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	
	JButton restartButton = new JButton("Exit");
	JButton buttons[][]= new JButton[3][3];
	Grid mainGrid = new Grid();
	
	buttonhandler handler = new buttonhandler();
	
	private class buttonhandler implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			
			
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					if( e.getSource() == buttons[i][j] ) {
						
						mainGrid.putIntoGrid(i, j);
						rePaint(mainGrid);
						
						if(mainGrid.findIfWon() == 2) {
							JOptionPane.showMessageDialog(null, "You won, Impossible");
							System.exit(0);
						}
						else if(mainGrid.findIfWon() == 0) {
							JOptionPane.showMessageDialog(null, "Its a Draw");
							System.exit(0);
						}
						
						computerTurn();
					}
				}
			}
			
			if(e.getSource() == restartButton) {
				System.exit(0); 
			}
		}
	}
	
	public static void main(String[] args) {
		
		new Main();

	}
	
	public Main() {
		
		startGame();
	}
	
	private void startGame() {
		
		showGui();
		
		mainGrid.initialize();
		
		rePaint(mainGrid);
//		mainGrid.putValue(0, 2);
//		player1.giveTurn(mainGrid).printValues();
//		
//		mainGrid.putValue(6, 2);
//		player1.giveTurn(mainGrid).printValues();
		
		
	}
	
	public void computerTurn() {
		player1.giveTurn(mainGrid);
		rePaint(mainGrid);
		
		if(mainGrid.findIfWon() == 1) {
			JOptionPane.showMessageDialog(null, "Computer Wins");
			System.exit(0);
		}
		else if(mainGrid.findIfWon() == 0) {
			JOptionPane.showMessageDialog(null, "Its a Draw");
			System.exit(0);
		}
	}
	
	
	public void showGui() {
		
		
		mainPanel.setLayout(new GridLayout(1, 2));
		mainPanel.setPreferredSize(new Dimension(750, 500));
		gamePanel = new JPanel(new GridLayout(3, 3));
		gamePanel.setPreferredSize(new Dimension(500, 500));
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {

				buttons[i][j] = new JButton();
				buttons[i][j].addActionListener(handler);
				gamePanel.add(buttons[i][j]);
			
			}
		}
		
		mainPanel.add(gamePanel);
		
		//buttonPanel.setLayout(new GridLayout(2, 1));
		//buttonPanel.add( getCircleButton());
		buttonPanel.setPreferredSize(new Dimension(250, 500));
		try {
			BufferedImage myPicture = ImageIO.read(new File("banner.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			buttonPanel.add(picLabel);
		}
		catch( Exception e) {
			
		}
		
		restartButton.addActionListener(handler);
		//buttonPanel.add(restartButton);
		buttonPanel.setBackground(Color.BLACK);
		mainPanel.add(buttonPanel);
		
		mainPanel.setBackground(Color.black);
		
		mainFrame.add(mainPanel);
		mainFrame.setLayout(new GridLayout(1, 2));
		mainFrame.setSize(760, 510);
		mainFrame.getContentPane();
		mainFrame.setVisible(true);
	}
	
	public void rePaint(Grid myGrid) {
		
		gamePanel.removeAll();
		gamePanel.revalidate();
		gamePanel.repaint();
		gamePanel.setPreferredSize(new Dimension(500, 500));
		
		for(int i=0; i<3; i++) {
			for(int j=0; j<3; j++) {
				
				buttons[i][j].addActionListener(handler);
				if(myGrid.value[i][j] != 0 ) {
					
					if(myGrid.value[i][j] == 1) buttons[i][j] = getCircleButton();
					else buttons[i][j] = getCrossButton();
					//buttons[i][j].setEnabled(false);
				}
				gamePanel.add(buttons[i][j]);
			}
		}
		
		mainPanel.add(gamePanel);
	}
	
	private JButton getCircleButton() {
		JButton button = new JButton();
		
		try {
		    //Image img = ImageIO.read(getClass().getResource("circle.png"));
		    button.setIcon(new ImageIcon("circle.png"));
		  } catch (Exception ex) {
			  System.out.println("Working Directory = " +
		              System.getProperty("user.dir"));
		  }
		button.setSize(50, 50);
		
		return button;

	}
	
	private JButton getCrossButton() {
		JButton button = new JButton();
		
		try {
		    //Image img = ImageIO.read(getClass().getResource("circle.png"));
		    button.setIcon(new ImageIcon("cross.png"));
		  } catch (Exception ex) {
		  }
		button.setSize(50, 50);
		
		return button;
	}
	
	private void clearJFrame() {
		mainFrame.dispose();
	}
}
