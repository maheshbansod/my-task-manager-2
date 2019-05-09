
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;
import java.io.*;

public class MyTaskManager extends JFrame {

	Card aCard, bCard, cCard, dCard;

	final String filename = "taskfile.dat";

	MyTaskManager() {

		setLayout(new GridLayout(2,2));
		setTitle("My Task Manager 2");
		setIconImage((new ImageIcon("icon.png")).getImage());

		GridBagConstraints c = new GridBagConstraints();

		aCard = new Card(this, "Urgent and important",Color.RED);
		bCard = new Card(this, "Important not urgent",Color.GREEN);
		cCard = new Card(this, "Not urgent not Important", new Color(100,100,255));
		dCard = new Card(this, "Urgent not important",new Color(0,100,0));

		

		add(aCard);
		add(bCard);
		add(cCard);
		add(dCard);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				try {
					writeToFile(filename);
;
				} catch(Exception e) {
					JOptionPane.showMessageDialog(MyTaskManager.this, "Exception occured while writing to file.","Problem while saving the tasks", JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				}
				MyTaskManager.this.dispose();
			}
		});

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);
		try {
			readFromFile(filename);
		} catch(FileNotFoundException e) {
			System.out.println("error while opening file");
			JOptionPane.showMessageDialog(this, "File not found. New file will be created");
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Exception occured while reading from file", "Problem while reading the tasks", JOptionPane.ERROR_MESSAGE);
		}
	}

	void writeToFile(String filename) throws Exception {
		ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
		out.writeObject("A");
		aCard.writeToFile(out);

		out.writeObject("B");
		bCard.writeToFile(out);

		out.writeObject("C");
		cCard.writeToFile(out);

		out.writeObject("D");
		dCard.writeToFile(out);
		
		out.close();
	}

	void readFromFile(String filename) throws Exception {
		ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename));


		try {
			String c;
			boolean close = false;
			while(!close) {
				c = (String)in.readObject();
				if(c.equals("A"))
					aCard.readFromFile(in);
				else if(c.equals("B"))
					bCard.readFromFile(in);
				else if(c.equals("C"))
					cCard.readFromFile(in);
				else if(c.equals("D"))
					dCard.readFromFile(in);
				else {
					System.out.println("some weird error");
					break;
				}
			}
		} catch(EOFException e) {
		}
			in.close();
	}

	public static void main(String args[]) {
		new MyTaskManager();
	}
}
