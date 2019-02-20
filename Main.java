package tictactoe;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class Main extends JFrame {
	
	JButton[][] but;
	String[][] SignCircleCross = new String[3][3];
	boolean switchSign = true;
	boolean Win = false;
	int WygraneX = 0;
	int WygraneO = 0;
	JLabel pktX, pktO, WhoRound;
	
	Color color = UIManager.getColor ( "Panel.background" );
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		this.setSize(300,430);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Tic-Tac-Toe");
		tictactoe();
		this.setVisible(true);
	}
	
	void tictactoe() {
		JPanel thePanel = new JPanel();
		ListenForButton lForBut = new ListenForButton();		
		thePanel.setPreferredSize(new Dimension(100, 100));
		
		WhoRound = new JLabel("Runda:   X");
		WhoRound.setFont(WhoRound.getFont().deriveFont(40.0f));
		thePanel.add(WhoRound);
			
		but = new JButton[3][3];
		for(int i=0; i<but.length; i++)
			for(int j=0; j<but.length; j++) {
				but[i][j] = new JButton(SignCircleCross[i][j]);
				but[i][j].setPreferredSize(new Dimension(80, 80));
				but[i][j].addActionListener(lForBut);
				but[i][j].setFont(but[i][j].getFont().deriveFont(30.0f));
				thePanel.add(but[i][j]);
			}
		
		pktX = new JLabel("Punkty X:   " + WygraneX);
		pktO = new JLabel("Punkty O:   " + WygraneO);	
		pktX.setFont(pktX.getFont().deriveFont(26.0f));
		pktO.setFont(pktO.getFont().deriveFont(26.0f));
		
		thePanel.add(pktX);
		thePanel.add(pktO);
		
		this.add(thePanel);
	}
	
	public class ListenForButton implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			for(int i=0; i<3; i++)
			for(int j=0; j<3; j++)
				if(e.getSource() == but[i][j]) {
					
					but[i][j].setEnabled(false);
					
					
					if(switchSign == true) { 
						switchSign = false;
						but[i][j].setText("X");
						SignCircleCross[i][j] = "X";
						but[i][j].setBackground(new Color(179, 0, 0));
						WhoRound.setText("Runda:   O");
					}
					else {
						switchSign = true;
						but[i][j].setText("O");
						SignCircleCross[i][j] = "O";
						but[i][j].setBackground(new Color(0, 0, 179));
						WhoRound.setText("Runda:   X");
					}
				}
			
			checkWin();
			checkRemis();
		}
		
		private void checkRemis() {
			int check = 0;
			for(int i=0; i<3; i++)
				for(int j=0; j<3; j++)
					if(SignCircleCross[i][j] != null) check++;
			
			if(check >= 9) {
				ResetLevel();
				System.out.println("Remis");
			}
		}

		private void checkWin() {
			Win = false;
			if(SignCircleCross[0][0] == SignCircleCross[1][0] && SignCircleCross[1][0] == SignCircleCross[2][0] && SignCircleCross[0][0] != null) Win = true;
			if(SignCircleCross[0][1] == SignCircleCross[1][1] && SignCircleCross[1][1] == SignCircleCross[2][1] && SignCircleCross[0][1] != null) Win = true;
			if(SignCircleCross[0][2] == SignCircleCross[1][2] && SignCircleCross[1][2] == SignCircleCross[2][2] && SignCircleCross[0][2] != null) Win = true;
			
			if(SignCircleCross[0][0] == SignCircleCross[0][1] && SignCircleCross[0][1] == SignCircleCross[0][2] && SignCircleCross[0][0] != null) Win = true;
			if(SignCircleCross[1][0] == SignCircleCross[1][1] && SignCircleCross[1][1] == SignCircleCross[1][2] && SignCircleCross[1][0] != null) Win = true;
			if(SignCircleCross[2][0] == SignCircleCross[2][1] && SignCircleCross[2][1] == SignCircleCross[2][2] && SignCircleCross[2][0] != null) Win = true;
			
			if(SignCircleCross[0][0] == SignCircleCross[1][1] && SignCircleCross[1][1] == SignCircleCross[2][2] && SignCircleCross[0][0] != null) Win = true;
			if(SignCircleCross[2][0] == SignCircleCross[1][1] && SignCircleCross[1][1] == SignCircleCross[0][2] && SignCircleCross[2][0] != null) Win = true;
			
			
			
			if(Win == true) {
				if(switchSign == true) {
					WygraneO++;
					System.out.println("Win O");
					pktO.setText("Punkty O:   " + WygraneO);
				}
				else 
				{
					WygraneX++;
					System.out.println("Win X");
					pktX.setText("Punkty X:   " + WygraneX);
				}
				
				System.out.println("0: " + WygraneO + ", X: " + WygraneX);
				ResetLevel();
			}
		}

		private void ResetLevel() {
			for(int i=0; i<3; i++)
				for(int j=0; j<3; j++) {
					but[i][j].setText("");
					SignCircleCross[i][j] = null; 
					but[i][j].setEnabled(true);
					but[i][j].setBackground(color);
				}
			Win = false;		
		}
		
	}
}
