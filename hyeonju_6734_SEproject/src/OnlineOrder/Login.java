package OnlineOrder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame {
	private MainApp main;

	private JButton btnLogin;
	private JButton btnInit;
	private JPasswordField passText;
	private JTextField userText;
	private boolean bLoginCheck;

	public Login() {
		// setting
		setTitle("login");
		setSize(280, 150);
		setResizable(false);
		setLocation(800, 450);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// panel
		JPanel panel = new JPanel();
		placeLoginPanel(panel);

		// add
		add(panel);

		// visiible
		setVisible(true);
	}

	public void placeLoginPanel(JPanel panel) {
		panel.setLayout(null);
		JLabel userLabel = new JLabel("User");
		userLabel.setBounds(10, 10, 80, 25);
		panel.add(userLabel);

		JLabel passLabel = new JLabel("Pass");
		passLabel.setBounds(10, 40, 80, 25);
		panel.add(passLabel);

		userText = new JTextField(20);
		userText.setBounds(100, 10, 160, 25);
		panel.add(userText);

		passText = new JPasswordField(20);
		passText.setBounds(100, 40, 160, 25);
		panel.add(passText);
		passText.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});

		btnInit = new JButton("Reset");
		btnInit.setBounds(10, 80, 100, 25);
		panel.add(btnInit);
		btnInit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				userText.setText("");
				passText.setText("");
			}
		});

		btnLogin = new JButton("Login");
		btnLogin.setBounds(160, 80, 100, 25);
		panel.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isLoginCheck();
			}
		});
	}

	public void isLoginCheck() {
		// this method should check ID&PW by comparing DB,this step should be done later.
		// here, to check the process, sample ID&PW are used.
		if (userText.getText().equals("customer123") && new String(passText.getPassword()).equals("1234")) {
			Customer myCustomer = new Customer(userText.getText(), new String(passText.getPassword()));
			JOptionPane.showMessageDialog(null, " Welcome " + myCustomer.getUserInfo());
			bLoginCheck = true;

			// if login success
			if (isLogin()) {
				main.showFrameTest(); // show a new frame
			}
		} else if (userText.getText().equals("admin0") && new String(passText.getPassword()).equals("1234")) {
			Employee myEmployee = new Employee("0admin", "Admin", 0);
			JOptionPane.showMessageDialog(null, "Welcome " + myEmployee.getEmpInfo());
			bLoginCheck = true;

			if (isLogin()) {
				main.showFrameTest();
			}

		} else if (userText.getText().equals("admin1") && new String(passText.getPassword()).equals("1234")) {
			Employee myEmployee = new Employee("1admin", "Admin", 1);
			JOptionPane.showMessageDialog(null, " Welcome " + myEmployee.getEmpInfo());
			bLoginCheck = true;

			if (isLogin()) {
				main.showFrameTest();
			}
		} else {
			JOptionPane.showMessageDialog(null, "Failed");
		}
	}

	// linked to mainProcess
	public void setMain(MainApp main) {
		this.main = main;
	}

	public boolean isLogin() {
		return bLoginCheck;
	}

}
