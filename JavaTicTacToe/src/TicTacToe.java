import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.classfile.instruction.ThrowInstruction;
import java.util.Random;

import javax.accessibility.AccessibleRelationSet;
import javax.imageio.plugins.tiff.ExifGPSTagSet;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.text.StyledEditorKit.ForegroundAction;
import javax.xml.stream.events.EndDocument;

public class TicTacToe implements ActionListener{

	Random ramdom = new Random();
	JFrame frame = new JFrame();
	JPanel titlePanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	JLabel textField = new JLabel();
	JOptionPane drawOption = new JOptionPane(); 
	JButton[] buttons = new JButton[9];
	boolean player1Turn;
	
	TicTacToe(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800,800);
		frame.getContentPane().setBackground(Color.black);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		
		textField.setBackground(new Color(25,25,25));
		textField.setForeground(new Color(25,255,0));
		textField.setFont(new Font("Ink Free", Font.BOLD, 75));
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText("Tic-Tac-Toe");
		textField.setOpaque(true);
		
		titlePanel.setLayout(new BorderLayout());
		titlePanel.setBounds(0,0,800,100);
		
		buttonPanel.setLayout(new GridLayout(3,3));
		buttonPanel.setBackground(new Color(150,150,150	));
		
		for(int i = 0; i < 9; i++) {
			buttons[i] = new JButton();
			buttonPanel.add(buttons[i]);
			buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
			buttons[i].setBackground(Color.white);
			buttons[i].setBorder(BorderFactory.createLineBorder(Color.black));
			buttons[i].setFocusable(false);
			buttons[i].addActionListener(this);
		}
		
		titlePanel.add(textField);
		frame.add(titlePanel, BorderLayout.NORTH);
		frame.add(buttonPanel);
		
		firstTurn();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		for(int i = 0; i <9; i++) {
			if(e.getSource() == buttons[i]) {
				if(player1Turn) {
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(Color.red);
						buttons[i].setText("X");
						player1Turn = false;
						textField.setText("O turn");
						checkWin();
					}
				}
				else
				{
					if(buttons[i].getText() == "") {
						buttons[i].setForeground(Color.blue);
						buttons[i].setText("O");
						player1Turn = true;
						textField.setText("X turn");
						checkWin();
					}
				}
			}
		}
		
	}
	
	public void firstTurn() {
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(ramdom.nextInt(2) == 0) {
			player1Turn = true;
			textField.setText("X turn");
		}
		else {
			player1Turn = false;
			textField.setText("O turn");
		}
		
	}
	
	public void checkWin() {
		if(checkWinCondition("X")) {
			xWin();
		}
		else if(checkWinCondition("O")) {
			oWin();
		}
		else if(isDraw()) {
			draw();
		}
	}
	
	public void setWinBoard(int a, int b, int c) {
		buttons[a].setBackground(Color.green);
		buttons[b].setBackground(Color.green);
		buttons[c].setBackground(Color.green);
	}
	
	public boolean checkWinCondition(String player) {
		if ((buttons[0].getText().equals(player)) && (buttons[1].getText().equals(player)) && (buttons[2].getText().equals(player))) {
			setWinBoard(0, 1, 2);
			return true;
		}
	    if ((buttons[3].getText().equals(player)) && (buttons[4].getText().equals(player)) && (buttons[5].getText().equals(player))){
			setWinBoard(3, 4, 5);
			return true;
		}
	    if ((buttons[6].getText().equals(player)) && (buttons[7].getText().equals(player)) && (buttons[8].getText().equals(player))){
			setWinBoard(6, 7, 8);
			return true;
		}
	    if ((buttons[0].getText().equals(player)) && (buttons[3].getText().equals(player)) && (buttons[6].getText().equals(player))){
			setWinBoard(0, 3, 6);
			return true;
		}
	    if ((buttons[1].getText().equals(player)) && (buttons[4].getText().equals(player)) && (buttons[7].getText().equals(player))){
			setWinBoard(1, 4, 7);
			return true;
		}
	    if ((buttons[2].getText().equals(player)) && (buttons[5].getText().equals(player)) && (buttons[8].getText().equals(player))){
			setWinBoard(2, 5, 8);
			return true;
		}
	    if ((buttons[0].getText().equals(player)) && (buttons[4].getText().equals(player)) && (buttons[8].getText().equals(player))){
			setWinBoard(0, 4, 8);
			return true;
		}
	    if ((buttons[2].getText().equals(player)) && (buttons[4].getText().equals(player)) && (buttons[6].getText().equals(player))){
			setWinBoard(2, 4, 6);
			return true;
		}
	    return false;
	}
	
	public boolean isDraw() {
		for(JButton button : buttons) {
			if(button.getText().equals("")) {
				return false;
			}
		}
		return true;
	}
	
	public void xWin() {
		textField.setText("X wins! Congrats!!!");
		end();
	}
	
	public void oWin() {
		textField.setText("O wins! Congrats!!!");
		end();
	}
	
	public void draw() {
        textField.setText("Draw");
        end();
    }
	
	public void end() {
		for(JButton button : buttons) {
			button.setEnabled(false);
		}
		
		int answer = JOptionPane.showConfirmDialog(null, "Do you want to restart?", "Game over", JOptionPane.YES_NO_OPTION);
		if(answer == JOptionPane.YES_OPTION) {
			gameReset();
			firstTurn();
		}
		else {
			System.exit(0);
		}
	}
	
	public void gameReset() {
		for(JButton button : buttons) {
			button.setText("");
			button.setEnabled(true);
			button.setBackground(Color.white);
		}
	}
}
