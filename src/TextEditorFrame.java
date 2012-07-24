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
import javax.swing.JFileChooser;


@SuppressWarnings({ "static-access", "serial" })
public class TextEditorFrame extends JFrame {
	
	JPanel contentPane;
	BorderLayout borderLayout = new BorderLayout();
	JToolBar toolBar = new JToolBar();

	JMenuBar menuBar = new JMenuBar();
	JMenu menuFile = new JMenu("File");
		JMenuItem menuFileExit = new JMenuItem("Exit");
		JMenuItem menuFileSaveAs = new JMenuItem("Save As");
		JMenuItem menuFileSave = new JMenuItem("Save");
		JMenuItem menuFileOpen = new JMenuItem("Open");
			final JFileChooser chooser = new JFileChooser();
	JMenu menuHelp = new JMenu("Help");
			JMenuItem menuHelpAbout = new JMenuItem("About");
	
	JButton buttonOpen;
	JButton buttonSave;
	JButton buttonHelp;
	
	ImageIcon imageOpen;
	ImageIcon imageSave;
	ImageIcon imageHelp;
	
	public TextEditorFrame() {
		this.setSize(new Dimension(400, 300));
		this.setTitle("Text Editor");
		
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout);
		
		menuFileOpen.addActionListener(new OpenActionAdapter(this));
		menuFileSaveAs.addActionListener(new SaveAsActionAdapter(this));
		menuFileExit.addActionListener(new ExitActionAdapter(this));
		menuFile.add(menuFileOpen);
		menuFile.add(menuFileSave);
		menuFile.add(menuFileSaveAs);
		menuFile.add(menuFileExit);
		menuBar.add(menuFile);
		
		menuHelpAbout.addActionListener(new AboutActionAdapter(this));
		menuHelp.add(menuHelpAbout);
		menuBar.add(menuHelp);
		
		this.setJMenuBar(menuBar);
		
		try {
			imageOpen = new ImageIcon(TextEditorFrame.class.getResource("Open24.gif"));
			imageSave = new ImageIcon(TextEditorFrame.class.getResource("Save24.gif"));
			imageHelp = new ImageIcon(TextEditorFrame.class.getResource("Help24.gif"));
			
			buttonOpen = new JButton("Open", imageOpen);
			buttonSave = new JButton("Save", imageSave);
			buttonHelp = new JButton("Help", imageHelp);
		} catch (Exception e) {
			buttonOpen = new JButton("Open");
			buttonSave = new JButton("Save");
			buttonHelp = new JButton("Help");
		}
		
		buttonOpen.addActionListener(new OpenActionAdapter(this));
		toolBar.add(buttonOpen);
		toolBar.add(buttonSave);
		toolBar.add(buttonHelp);
		
		contentPane.add(toolBar, BorderLayout.NORTH);
	}
	
	
	public void ExitActionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
	
	public void OpenActionPerformed(ActionEvent e) {
		if(chooser.showOpenDialog(this) == chooser.APPROVE_OPTION) {
			System.err.println(chooser.getSelectedFile().getPath());
		}
	}
	
	
	public void SaveAsActionPerformed(ActionEvent e) {
		if(chooser.showSaveDialog(this) == chooser.APPROVE_OPTION) {
			System.err.println(chooser.getSelectedFile().getPath());
		}
	}
	
	
	public void AboutActionPerformed(ActionEvent e) {		
		AboutTextEditor dialog = new AboutTextEditor(this);
		dialog.setLocationRelativeTo(this);
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
	
	
	class OpenActionAdapter implements ActionListener {
		TextEditorFrame adaptee;
		
		OpenActionAdapter(TextEditorFrame adaptee) {
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e) {
			adaptee.OpenActionPerformed(e);
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
	
	class SaveAsActionAdapter implements ActionListener {
		TextEditorFrame adaptee;
		
		SaveAsActionAdapter(TextEditorFrame adaptee) {
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e) {
			adaptee.SaveAsActionPerformed(e);
		}
	}
}
