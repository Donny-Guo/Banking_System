package gui.Teller;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import gui.Teller.TellerMainPage;
import main.TellerGUIClient;
import java.util.Random;
import java.io.*;
import java.util.*;
import java.net.*;
import message.*;
public class TellerCreateAccount {
	public static TellerGUIClient client;
	public static int id;
	
	public TellerCreateAccount (TellerGUIClient client, int id) {
		this.client = client;
		this.id = id;
	}
	
	public static TellerGUIClient getClient() {
		return client;
	}
	
	public static void createWindow() {
	    JFrame frame = new JFrame("Create a Bank User");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    TellerCreateAccountUI(frame);
	    frame.setSize(450,300);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(true);
	}
		
	private static void TellerCreateAccountUI(final JFrame frame){
		// creates a new panel
		JPanel panel = new JPanel();
		LayoutManager layout = new FlowLayout();
		frame.add(panel);
		
		JButton savingButton = new JButton("SAVINGS");
		savingButton.setSize(200,200);
		JButton checkingButton = new JButton("CHECKINGS");
		checkingButton.setSize(200,200);
		JButton exitButton = new JButton("Cancel");
		exitButton.setSize(100,100);
		JTextField textField = new JTextField(25);
		JLabel label1 = new JLabel("Account Pin: ");
		
		savingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pin = textField.getText();
				if (pin.equals("")) {
					// Generate a random 4-digit pin
					Random random = new Random();
			        int randomPin = 1000 + random.nextInt(9000);
			        pin = String.valueOf(randomPin);
				}
				String status = getClient().checkPin(pin);
				if(status.equalsIgnoreCase("VALID")){
					String userConfirm = JOptionPane.showInputDialog("You are creating a savings account with pin " + pin
							+ "\nPlease enter yes to confirm.");
					if (userConfirm.equalsIgnoreCase("YES")) {
						int intPin = Integer.parseInt(pin);
						AccountType saving = AccountType.SAVING;
						String stat = getClient().createAccount(id, intPin, saving);
						if(stat.equalsIgnoreCase("SUCCESS")) {
							JOptionPane.showMessageDialog(frame,"Updated user id ["
						+ id + "] accounts: " + getClient().getAccounts());
						}
					}else {
						JOptionPane.showMessageDialog(frame, "Fail to create a new SAVINGS.\n");
					}
				}
				else{
					JOptionPane.showMessageDialog(frame, "Invalid pin. Try again.");
				}
			}
		} );
		
		
		checkingButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String pin = textField.getText();
				if (pin.equals("")) {
					// Generate a random 4-digit pin
					Random random = new Random();
			        int randomPin = 1000 + random.nextInt(9000);
			        pin = String.valueOf(randomPin);
				}
				String status = getClient().checkPin(pin);
				if(status.equalsIgnoreCase("VALID")){
					String userConfirm = JOptionPane.showInputDialog("You are creating a checking account with pin " + pin
							+ "\nPlease enter yes to confirm.");
					if (userConfirm.equalsIgnoreCase("YES")) {
						int intPin = Integer.parseInt(pin);
						AccountType saving = AccountType.CHECKING;
						String stat = getClient().createAccount(id, intPin, saving);
						if(stat.equalsIgnoreCase("SUCCESS")) {
							JOptionPane.showMessageDialog(frame,"Updated user id ["
						+ id + "] accounts: " + getClient().getAccounts());
						}
					}else {
						JOptionPane.showMessageDialog(frame, "Fail to create a new CHECKINGS.\n");
					}
				}
				else{
					JOptionPane.showMessageDialog(frame, "Invalid pin. Try again.");
				}
			}
		} );
		exitButton.addActionListener(new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             JOptionPane.showMessageDialog(frame, "Back to user page.");
	             //closes TellerCreateUser
	             frame.setVisible(false);
	             // go back to Teller User page
	          }
		});
		
		
		panel.add(savingButton);
		panel.add(checkingButton);
		panel.add(label1);
		panel.add(textField);
		panel.add(exitButton);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		
	}
}