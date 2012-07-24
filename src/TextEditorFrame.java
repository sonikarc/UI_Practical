import java.awt.*;
// import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.ImageIcon;


@SuppressWarnings("serial")
public class TextEditorFrame extends JFrame {
	
	JPanel contentPane;
	BorderLayout borderLayout = new BorderLayout();
	JToolBar toolBar = new JToolBar();
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
}
