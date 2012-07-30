import java.io.*;
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
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;


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
	
	String fileName = null;
	boolean modified = false;
	
	public TextEditorFrame() {
		this.setSize(new Dimension(400, 300));
		this.setTitle("Text Editor");
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				if(textArea.getText().trim().equals("")) {
					System.exit(0);
				} else if(modified == true) {
					int choice = JOptionPane.showConfirmDialog(null, "You have made changes, do you want to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
					if(choice == JOptionPane.YES_OPTION) { System.exit(0); }
				}
			}
		});
		
		contentPane = (JPanel) this.getContentPane();
		contentPane.setLayout(borderLayout);
		
		menuFileOpen.addActionListener(new OpenActionAdapter(this));
		menuFileSave.addActionListener(new SaveActionAdapter(this));
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
		buttonSave.addActionListener(new SaveActionAdapter(this));
		toolBar.add(buttonOpen);
		toolBar.add(buttonSave);
		toolBar.add(buttonHelp);
		
		toolBar.setFloatable(false);
		
		
		class dListener implements DocumentListener {
			public void insertUpdate(DocumentEvent arg0) {
				modified = true;
			}

			public void removeUpdate(DocumentEvent arg0) {
				modified = true;
			}

			public void changedUpdate(DocumentEvent e) {
				modified = true;
			}
		}
		textArea.getDocument().addDocumentListener(new dListener());
				
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
			
			this.fileName = fileName;
		} catch (FileNotFoundException e) {
			statusBar.setText("Could not find file: " + fileName);
		} catch (IOException e) {
			statusBar.setText("Error reading from file: " + fileName);			
		}
	}
	
	
	private void saveFile(String fileName) {
		boolean exists = false;
		
		if(textArea.getText().compareToIgnoreCase("") == 0) {
			statusBar.setText("There's nothing to Save!");
		} else if(fileName == null) {
			if(chooser.showSaveDialog(this) == chooser.APPROVE_OPTION) {
				fileName = chooser.getSelectedFile().getPath();
				
				exists = chooser.getSelectedFile().exists();
				System.err.println(exists);
				if(exists == true) {
					JOptionPane optionPane = new JOptionPane();
					int answer = optionPane.showConfirmDialog(chooser, "This file already exists, would you like to overwrite?", fileName, optionPane.WARNING_MESSAGE, optionPane.YES_NO_CANCEL_OPTION);
					System.err.println(answer);
				}
			}
		} else if(this.fileName.compareToIgnoreCase(fileName) == 0) { 
			fileName = this.fileName;
		} else {
			try {
				File file = new File(fileName);
				FileWriter out = new FileWriter(file);
				String text = textArea.getText();
				out.write(text);
				out.close();
				
				statusBar.setText("Saved file to: " + fileName);
			} catch (IOException e) {
				statusBar.setText("Error writing to file: " + fileName);
			}
		}
	}
	
	
	public void ExitActionPerformed(ActionEvent e) {
		if(textArea.getText().trim().equals("")) {
			System.exit(0);
		} else if(modified == true) {
			int choice = JOptionPane.showConfirmDialog(null, "You have made changes, do you want to Exit?", "Confirm Exit", JOptionPane.YES_NO_OPTION);
			if(choice == JOptionPane.YES_OPTION) { System.exit(0); }
		}
	}
	
	
	public void OpenActionPerformed(ActionEvent e) {
		if(chooser.showOpenDialog(this) == chooser.APPROVE_OPTION) {
			openFile(chooser.getSelectedFile().getPath());
		}
		
		repaint();
	}
	
	
	public void SaveAsActionPerformed(ActionEvent e) {
		if(chooser.showSaveDialog(this) == chooser.APPROVE_OPTION) {
			saveFile(chooser.getSelectedFile().getPath());
		}
		
		repaint();
	}
	
	
	public void SaveActionPerformed(ActionEvent e) {
		saveFile(this.fileName);
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
	
	
	class SaveActionAdapter implements ActionListener {
		TextEditorFrame adaptee;
		
		SaveActionAdapter(TextEditorFrame adaptee) {
			this.adaptee = adaptee;
		}
		
		public void actionPerformed(ActionEvent e) {
			adaptee.SaveActionPerformed(e);
		}
	}
}
