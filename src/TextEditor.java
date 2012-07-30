import java.awt.*;
import java.awt.Toolkit;
import java.awt.event.*;
import javax.swing.UIManager;


public class TextEditor {

	public TextEditor() {
		TextEditorFrame frame = new TextEditorFrame();
		
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frameSize = frame.getSize();
		if(frameSize.height > screenSize.height) { frameSize.height = screenSize.height; }
		if(frameSize.width > screenSize.width) { frameSize.width = screenSize.width; }
		frame.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		
		frame.validate();
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		new TextEditor();
	}

}
