import java.io.*;
import java.util.Scanner;
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
import javax.swing.JTextArea;
import javax.swing.JLabel;


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
	
	JTextArea textArea = new JTextArea();
	
	JLabel statusBar = new JLabel();
	
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
		contentPane.add(textArea, BorderLayout.CENTER);
		contentPane.add(statusBar, BorderLayout.SOUTH);
	}
	
	
	private void openFile(String fileName) {
		try {
			File file = new File(fileName);
			int size = (int) file.length();
			FileReader in = new FileReader(file);
			char[] data = new char[size];
			
			int charsRead = 0;		
			while(in.ready()) { charsRead += in.read(data, charsRead, size - charsRead); }
			in.close();
			
			textArea.setText(new String(data, 0, charsRead));
			
			statusBar.setText("Opened file: " + fileName);
		} catch (FileNotFoundException e) {
			statusBar.setText("Could not find file: " + fileName);
		} catch (IOException e) {
			statusBar.setText("Error reading from file: " + fileName);			
		}
	}
	
	
	public void ExitActionPerformed(ActionEvent e) {
		System.exit(0);
	}
	
	
	public void OpenActionPerformed(ActionEvent e) {
		if(chooser.showOpenDialog(this) == chooser.APPROVE_OPTION) {
			openFile(chooser.getSelectedFile().getPath());
		}
		
		repaint();
	}
	
	
	public void SaveAsActionPerformed(ActionEvent e) {
		if(chooser.showSaveDialog(this) == chooser.APPROVE_OPTION) {
			System.err.println(chooser.getSelectedFile().getPath());
		}
		
		repaint();
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
