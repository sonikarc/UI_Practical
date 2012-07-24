import java.awt.*;
import java.awt.event.*;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class AboutTextEditor extends JDialog implements ActionListener {

	JPanel contentPane;	
	BorderLayout contentLayout = new BorderLayout();

	JButton buttonOK = new JButton("Ok");
	
	public AboutTextEditor(Frame owner) {
		super(owner);
		
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(contentLayout);
		
		buttonOK.addActionListener(this);
		
		contentPane.add(buttonOK, BorderLayout.SOUTH);
		
		this.pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
