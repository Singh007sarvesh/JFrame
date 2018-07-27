import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class LoginFrame extends JFrame implements ActionListener{
	Container c;
	JLabel userLable=new JLabel("UserName");
	JLabel passLable=new JLabel("Passowrd");
	JTextField userfield=new JTextField();
	JPasswordField pass=new JPasswordField();
	JButton button=new JButton("Submit");
	public LoginFrame()
	{
		c=this.getContentPane();
		c.setLayout(null);
		c.setBackground(Color.RED);
		userLable.setBounds(200,50,100,40);
		passLable.setBounds(200,150,100,40);
		Font f=new Font("Arial",Font.BOLD,20);
		userLable.setFont(f);
		passLable.setFont(f);
		userfield.setBounds(350,50,100,40);
		pass.setBounds(350,150,100,40);
		button.setBounds(300, 250, 200, 50);
		button.addActionListener(this);
		c.add(userLable);
		c.add(passLable);
		c.add(userfield);
		c.add(pass);
		c.add(button);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button)
		{
			String userName=userfield.getText();
			String userPass=pass.getText();
			if(userName.equals("ABC") && userPass.equals("123"))
			{
				System.out.println("Good");
				c.setBackground(Color.YELLOW);
			}
			else {
				System.out.println("wrong");
			c.setBackground(Color.GREEN);
			}
		}
	}

}
class LoginForm
{
	public static void main(String[] args)
	{
		LoginFrame loginframe=new LoginFrame();
		
		loginframe.setVisible(true);
		loginframe.setResizable(true);
		loginframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginframe.setBounds(200,50,250,300);
	}
}