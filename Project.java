import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;

public class Project extends JFrame {

	private JPanel Blank1 = new JPanel();
	private JPanel Blank2 = new JPanel();
	private JPanel Blank3 = new JPanel();
	private JPanel Blank4 = new JPanel();
	private JPanel Blank5 = new JPanel();
	private JPanel Blank6 = new JPanel();
	private JPanel Blank7 = new JPanel();
	private JPanel Blank8 = new JPanel();
	public Component frame;

	static int usersAmount = 0;

	int hash = 0;

	Hashtable<homePage, String> userList = new Hashtable();
	ArrayList<String> Users = new ArrayList<String>();

	static JTextField username = new JTextField("Type username here...", 30);
	static int SIZE = 50;
	static JButton[] usersArray = new JButton[SIZE];
	final JTextField status = new JTextField("What's on your mind?", 30);
	final JTextArea statusBox = new JTextArea(4, 30);
	final String newLine = "\n";

	Project() {

		setBackground(Color.BLUE);
		JButton login = new JButton("Login");
		login.addActionListener(new LoginHandler());
		JButton NEWUser = new JButton("New User");
		JLabel socialNetwork = new JLabel("Our Social Network");
		socialNetwork.setFont(new java.awt.Font("SanSerif", Font.BOLD, 30));

		// auto-highlights the text box
		username.setHorizontalAlignment(JTextField.CENTER);
		username.addFocusListener(new FocusListener() {
			public void focusLost(final FocusEvent pE) {
			}

			public void focusGained(final FocusEvent pE) {
				username.selectAll();
			}
		});

		NEWUser.addActionListener(new CreateHandler());

		Blank1.setBackground(Color.BLUE);
		Blank1.add(socialNetwork);

		Blank2.setBackground(Color.BLUE);
		Blank3.setBackground(Color.BLUE);
		Blank4.setBackground(Color.BLUE);
		Blank5.setBackground(Color.BLUE);
		Blank6.setBackground(Color.BLUE);
		Blank7.setBackground(Color.BLUE);
		Blank8.setBackground(Color.BLUE);

		Blank3.add(username);

		setLayout(new GridLayout(10, 1));
		add(Blank1);
		add(Blank2);
		add(Blank3);
		add(Blank4);
		add(login);
		add(NEWUser);
		add(Blank5);
		add(Blank6);
		add(Blank7);
		add(Blank8);

		setSize(800, 400); // set size of frame

		setLocationRelativeTo(null); // open in the center of the screen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // close the program
														// when "X" is pressed
		setVisible(true);

	}

	// added homePage class to replace interfaces in the action listener classes
	public class homePage extends JFrame {
		String input;

		homePage() {
			input = username.getText();
			JFrame profile = new JFrame(input + "'s profile");
			JLabel displayName = new JLabel(input);
			statusBox.setEditable(false);
			statusBox.setRows(3);
			statusBox.setLineWrap(true);
			JScrollPane statusPane = new JScrollPane(statusBox);
			JButton updateStatus = new JButton("Update");
			updateStatus.addActionListener(new StatusHandler());
			JButton clearStatus = new JButton("Clear Status");
			clearStatus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					statusBox.setText(null);
				}
			});
			JButton friends = new JButton("Friends");
			friends.addActionListener(new Friends());
			JTextField search = new JTextField("Type a name to search...", 30);
			JButton startSearch = new JButton("Search");
			startSearch.addActionListener(new Search());
			JButton closeAccount = new JButton("Close Account");
			closeAccount.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					profile.dispose();
				}
			});
			final JPanel one = new JPanel();
			final JPanel two = new JPanel();
			final JPanel three = new JPanel();
			final JPanel four = new JPanel();
			final JPanel five = new JPanel();
			final JPanel six = new JPanel();

			// auto-highlights the text boxes when selected
			status.addFocusListener(new FocusListener() {
				public void focusLost(final FocusEvent pE) {
				}

				public void focusGained(final FocusEvent pE) {
					status.selectAll();
				}
			});

			search.addFocusListener(new FocusListener() {
				public void focusLost(final FocusEvent pE) {
				}

				public void focusGained(final FocusEvent pE) {
					search.selectAll();
				}
			});

			one.setBackground(Color.BLUE);
			displayName.setFont(new java.awt.Font("SanSerif", Font.BOLD, 30));
			two.setBackground(Color.BLUE);
			three.setBackground(Color.BLUE);
			four.setBackground(Color.BLUE);
			five.setBackground(Color.BLUE);
			six.setBackground(Color.BLUE);

			one.add(displayName);
			two.add(status);
			two.add(updateStatus);
			two.add(clearStatus);
			three.add(statusPane);
			four.add(friends);
			five.add(search);
			five.add(startSearch);
			six.add(closeAccount);

			profile.setSize(400, 600);
			profile.setBackground(Color.BLUE);
			profile.setLayout(new GridLayout(6, 1));
			profile.setVisible(true);
			profile.setLocationRelativeTo(null);
			profile.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			profile.add(one);
			profile.add(two);
			profile.add(three);
			profile.add(four);
			profile.add(five);
			profile.add(six);
		}

	}

	/*
	 * LOGIN HANDLER Checks if the user name exists. If user name exists, logs
	 * into home page. if user name doesn't exist, shows error message dialog..
	 */

	public class LoginHandler implements ActionListener {
		String input;

		public void actionPerformed(ActionEvent e) {
			input = username.getText();

			if (userList.containsValue(input)) {
				new homePage();
			} else {
				JOptionPane.showMessageDialog(frame, "Invalid Username");
			}

			System.out.println(userList.values());
			Collections.sort(Users);
			System.out.println(Users);

		}
	}

	/*
	 * NEW USER CREATION HANDLER Checks if the user name is already taken. If
	 * user name isn't taken, creates new user profile. if user name is taken,
	 * shows error message dialog.
	 */

	public class CreateHandler implements ActionListener { // actionlistener for
															// the new user
															// button on main
															// menu
		String input;

		public void actionPerformed(ActionEvent e) {
			input = username.getText(); // takes text from the textbox and sets
										// it to the name

			if (userList.containsValue(input) == true) {
				JOptionPane.showMessageDialog(frame, "That user name is taken.");
			} else {

				userList.put((new homePage()), input);
				Users.add(input);
			}
		}
	}

	public class StatusHandler implements ActionListener {
		String input;

		public void actionPerformed(ActionEvent e) {
			input = status.getText();
			statusBox.append(input + newLine);
			status.setText("What's on your mind?");
		}
	}

	public class Search implements ActionListener // actionlistener for the
													// search button within
													// profiles
	{
		public void actionPerformed(ActionEvent e) {
			JFrame users = new JFrame();
			JLabel search = new JLabel("Search");

			final JPanel one = new JPanel();
			final JPanel two = new JPanel();

			users.setSize(400, 600);
			users.setBackground(Color.BLUE);
			users.setLayout(new GridLayout(2, 1));
			users.setVisible(true);
			users.setLocationRelativeTo(null);
			users.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			one.setBackground(Color.BLUE);
			one.setLayout(new GridLayout(6, 1));
			search.setFont(new java.awt.Font("SanSerif", Font.BOLD, 30));
			two.setBackground(Color.BLUE);
			two.setLayout(new GridLayout(6, 1));

			one.add(search);
			DefaultListModel USERLIST = new DefaultListModel();
			Object[] userARRAY = Users.toArray();

			for (int i = 0; i > Users.size(); i++) {
				USERLIST.addElement(userARRAY[i]);
			}

			JList list = new JList(USERLIST);
			users.add(one);
			users.add(list);
		}
	}

	public class Friends implements ActionListener // actionlistener for the
													// friends button within
													// profiles
	{
		public void actionPerformed(ActionEvent e) {
			JFrame friends = new JFrame();
			JLabel label = new JLabel("Friends");

			final JPanel one = new JPanel();
			final JPanel two = new JPanel();

			one.setBackground(Color.BLUE);
			one.setLayout(new GridLayout(6, 1));
			label.setFont(new java.awt.Font("SanSerif", Font.BOLD, 30));
			two.setBackground(Color.BLUE);
			two.setLayout(new GridLayout(6, 1));

			one.add(label);

			friends.setSize(400, 600);
			friends.setBackground(Color.BLUE);
			friends.setLayout(new GridLayout(2, 1));
			friends.setVisible(true);
			friends.setLocationRelativeTo(null);
			friends.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

			JButton button1 = new JButton();
			JButton button2 = new JButton();
			JButton button3 = new JButton();
			JButton button4 = new JButton();
			JButton button5 = new JButton();
			JButton button6 = new JButton();

			two.add(button1);
			two.add(button2);
			two.add(button3);
			two.add(button4);
			two.add(button5);
			two.add(button6);

			friends.add(one);
			friends.add(two);

		}
	}

	public static void main(String[] args) {

		Project test = new Project();

	}

}
