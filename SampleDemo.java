import java.awt.Button;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
class Link extends JFrame implements ActionListener{
	Container c;
	JButton button=new JButton("Click Me");
	public Link()
	{
		c=this.getContentPane();
		c.setLayout(null);
		button.setBounds(500, 250, 200, 50);
		button.addActionListener(this);
		c.add(button);
	}
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==button)
		{
			try {
				//JFrame jframe=new JFrame();
			//Desktop desktop=Desktop.getDesktop();
		   //URL url=new URL("http://www.quodd.com/b4utrade/app/EquityPlusScrollingNewsHeadline.do?NEWS_SOURCE=EDGE,&UPCLOSETICKER=MSFT");
				
		   // c.add(url);
			
		  /* jframe.setVisible(true);
		   jframe.setResizable(true);
		   jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   jframe.setBounds(200,50,250,300);*/
				
				MiniBrowser mini=new MiniBrowser("My Browser");
			}
			catch(Exception ex)
			{
				System.out.println(ex);
			}
		}
			
	}

}
public class SampleDemo {
	
	
	public static void main(String[] args) 
	{
		Link link=new Link();
		link.setVisible(true);
		link.setResizable(true);
		link.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		link.setBounds(200,50,250,300);
	}

}
