import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class SMTP_Client extends JFrame implements ActionListener{
	JLabel from = new JLabel("from", JLabel.CENTER);
	JLabel passwd = new JLabel("password", JLabel.CENTER);
	JLabel SMTP_Server = new JLabel("SMTP_Server", JLabel.CENTER);
	JLabel SMTP_Port = new JLabel("SMTP_Port", JLabel.CENTER);
	JLabel to = new JLabel("to", JLabel.CENTER);
	JLabel subject = new JLabel("subject", JLabel.CENTER);
	JTextField fromJTextField = new JTextField(10);
	JTextField passwdJTextField = new JTextField(10);
	JTextField smtp_serverJTextField = new JTextField(10);
	JTextField smtp_porJTextField = new JTextField(10);
	JTextField toJTextField = new JTextField(10);
	JTextField subJTextField = new JTextField(10);
	JButton sendMail = new JButton("Send");
	public SMTP_Client() {
		super("SMTP Client");
		setSize(400, 300);
		setLocationRelativeTo(null);
		setVisible(true);
		setLayout(new GridLayout(10, 2));
		add(from);add(fromJTextField);
		add(passwd);add(passwdJTextField);
		add(SMTP_Server);add(smtp_serverJTextField);
		add(SMTP_Port);add(smtp_porJTextField);
		add(to);add(toJTextField);
		add(subject);add(subJTextField);
		
		sendMail.addActionListener(this);
		add(sendMail);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			Process process = Runtime.getRuntime().exec("./python_smtp.py " + fromJTextField.getText() + " "
					+ passwdJTextField.getText() + " " + smtp_serverJTextField.getText() + " " 
					+ Integer.parseInt(smtp_porJTextField.getText()) + " " + toJTextField.getText() + " " + subJTextField.getText());
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new SMTP_Client();
	}

}
