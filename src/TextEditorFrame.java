import java.awt.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


@SuppressWarnings("serial")
public class TextEditorFrame extends JFrame {
	
	JPanel contentPane;
	BorderLayout borderLayout = new BorderLayout();
	JToolBar toolBar = new JToolBar();

	JMenuBar menuBar = new JMenuBar();
	JMenu menuFile = new JMenu("File");
			JMenuItem menuFileExit = new JMenuItem("Exit");
	JMenu menuHelp = new JMenu("Help");
			JMenuItem menuHelpAbout = new JMenuItem("About");
	
	JButton button1;
	JButton button2;
	JButton button3;
	
	ImageIcon image1;
	ImageIcon image2;
	ImageIcon image3;
	
	public TextEditorFrame() {
		this.setSize(new Dimension(400, 300));
		this.setTitle("Text Editor");
		
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout);
		
		menuFileExit.addActionListener(new ExitActionAdapter(this));
		menuFile.add(menuFileExit);
		menuBar.add(menuFile);
		
		menuHelpAbout.addActionListener(new AboutActionAdapter(this));
		menuHelp.add(menuHelpAbout);
		menuBar.add(menuHelp);
		
		this.setJMenuBar(menuBar);
		
		try {
			image1 = new ImageIcon(TextEditorFrame.class.getResource("Open24.gif"));
			image2 = new ImageIcon(TextEditorFrame.class.getResource("Save24.gif"));
			image3 = new ImageIcon(TextEditorFrame.class.getResource("Help24.gif"));
			
			button1 = new JButton("Open", image1);
			button2 = new JButton("Save", image2);
			button3 = new JButton("Help", image3);
		} catch (Exception e) {
			button1 = new JButton("Open");
			button2 = new JButton("Save");
			button3 = new JButton("Help");
		}
		
		toolBar.add(button1);
		toolBar.add(button2);
		toolBar.add(button3);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
	}
	
	
	public void ExitActionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
	
	public void AboutActionPerformed(ActionEvent e) {		
		AboutTextEditor dialog = new AboutTextEditor(this);
		dialog.setModal(true);
		dialog.setVisible(true);
	}
	
	
	class ExitActionAdapter implements ActionListener {
		TextEditorFrame adaptee;
		
		ExitActionAdapter(TextEditorFrame adaptee) {
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e) {
			adaptee.ExitActionPerformed(e);
		}
	}
	
	class AboutActionAdapter implements ActionListener {
		TextEditorFrame adaptee;
		
		AboutActionAdapter(TextEditorFrame adaptee) {
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e) {
			adaptee.AboutActionPerformed(e);
		}
	}
}
