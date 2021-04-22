package stopwatch;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
/*GW*/
public class StopWatch implements ActionListener{
	JFrame frame = new JFrame("StopWatch/½ºÅé¿öÄ¡");
	JLabel label = new JLabel();
	JLabel warningLabel = new JLabel("Stopwatch is stop!");
	JButton startBtn = new JButton("START");
	JButton resetBtn = new JButton("RESET");
	Font font = new Font("gothic", Font.BOLD, 55);
	Font warningFont = new Font("gothic", Font.BOLD, 20);
	
	static boolean IsStart = true; 
	
	static int hours = 0, minutes = 0, seconds = 0;
	String h = String.format("%02d", hours);
	String m = String.format("%02d", minutes);
	String s = String.format("%02d", seconds);
	

	StopWatch() throws InterruptedException{
		
		setGUI();
		
		while(true) {
			start();
			resetBtn();
			if(IsStart==false) {
				
				warningLabel.setVisible(false);
				removeLabel();
				seconds++;
				if(seconds > 59) {
					seconds = 0;
					minutes++;
				}
				if(minutes > 59) {
					minutes = 0;
					hours ++;
				}
				h = String.format("%02d", hours);
				m = String.format("%02d", minutes);
				s = String.format("%02d", seconds);
				
				createLabel(h, m, s);
				
				Thread.sleep(1000);//set, repeat create and remove label function per 1 seconds
				removeLabel();
			}else {
				showIsStop();
				warningLabel.setVisible(true);
			}
			
		}
	}
	
	public void setGUI() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setLayout(null);
		Toolkit kit = Toolkit.getDefaultToolkit();
		Image icon = kit.getImage("stopwatch.png");
		frame.setIconImage(icon);
		createButton();
		createLabel(h, m, s);
		frame.setBounds(500, 200, 420, 420);
		frame.setResizable(false);
	}
	
	public void showIsStop() {
		frame.add(warningLabel);
		warningLabel.setFont(warningFont);
		warningLabel.setForeground(Color.red);
		warningLabel.setBounds(110, 0, 200, 100);
	}
	public void createLabel(String h, String m, String s) {
		label = new JLabel(h+":"+m+":"+s, JLabel.CENTER);
		frame.add(label);
		label.setFont(font);
		label.setBounds(50, 100, 300, 100);
	}
	
	public void start() {
		startBtn.addActionListener(this);
	}
	
	public void reset() {
		hours=0;
		minutes=0;
		seconds=0;
	}
	
	public void resetBtn() {
		resetBtn.addActionListener(this);
	}

	
	public void removeLabel() {
		frame.remove(label);
	}
	public void createButton() {
		startBtn.setBounds(50, 300, 150, 100);
		resetBtn.setBounds(200,300,150,100);
		frame.add(startBtn);
		frame.add(resetBtn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(startBtn)) {
			if(startBtn.getText().equals("START")) {
				IsStart = false;
				startBtn.setText("STOP");
			}else if(startBtn.getText().equals("STOP")) {
				IsStart = true;
				startBtn.setText("START");
				frame.remove(warningLabel);	
			}
		}
		
		if(e.getSource().equals(resetBtn)) {
			reset();
		}
	}

}

