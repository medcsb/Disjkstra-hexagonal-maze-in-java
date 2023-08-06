package GUI;

import java.awt.Dimension;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Manuel extends JFrame{

	
	public Manuel() {
		super("Instruction");
		System.out.print("A");
		String textContent = null;
		 Scanner u = new Scanner(System.in);
		 try {
			textContent = new Scanner(new File("Instructions.txt")).useDelimiter("\\A").next();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		setPreferredSize(new Dimension(300, 400));
		setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
		JTextArea text = new JTextArea();
		text.setText(textContent);
		text.setLineWrap(true);
		text.setEditable(false);
		JScrollPane scroll = new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		add(scroll);
		setVisible(true);
		pack();
	}
}
