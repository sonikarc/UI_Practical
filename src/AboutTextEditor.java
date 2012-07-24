import java.awt.*;
import java.awt.event.*;

import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.BorderFactory;


@SuppressWarnings("serial")
public class AboutTextEditor extends JDialog implements ActionListener {

	JPanel contentPane;	
	BorderLayout contentLayout = new BorderLayout();

	JPanel buttonPane = new JPanel();
	FlowLayout buttonLayout = new FlowLayout();
	
	ImageIcon aboutIcon;
	JLabel graphicLabel = new JLabel();
	
	JPanel infoPane = new JPanel();
	
	JButton buttonOK = new JButton("Ok");
	
	public AboutTextEditor(Frame owner) {
		super(owner);
		
		this.setTitle("About");
		this.setResizable(false);
		
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(contentLayout);
		
		try {
			aboutIcon = new ImageIcon(AboutTextEditor.class.getResource("about.gif"));
			graphicLabel.setIcon(aboutIcon);
		} catch (Exception e) {
			graphicLabel.setText("TextEditor");
		}
		graphicLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		infoPane.setLayout(new BoxLayout(infoPane, BoxLayout.Y_AXIS));
		infoPane.add(new JLabel("TextEditor"));
		infoPane.add(new JLabel("A simple Text Editor using Swing"));
		infoPane.add(new JLabel("Version 1.0"));
		infoPane.add(new JLabel("Author: Stewart Malik"));
		infoPane.add(new JLabel("Copyright(c) 2012 WTFPL"));
		infoPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		
		buttonPane.setLayout(buttonLayout);
		buttonPane.add(buttonOK);
		
		buttonOK.addActionListener(this);
		
		contentPane.add(graphicLabel, BorderLayout.WEST);
		contentPane.add(infoPane, BorderLayout.EAST);
		contentPane.add(buttonPane, BorderLayout.SOUTH);
		
		this.pack();
	}
	
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
