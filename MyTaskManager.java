
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

		GridBagConstraints c = new GridBagConstraints();

		/*JPanel aPanel = new JPanel();
		JPanel bPanel = new JPanel();
		JPanel cPanel = new JPanel();
		JPanel dPanel = new JPanel();

		aPanel.setBackground(Color.RED);
		bPanel.setBackground(Color.YELLOW);
		cPanel.setBackground(Color.BLUE);
		dPanel.setBackground(Color.GREEN);*/
		aCard = new Card(this, "Urgent and important",Color.RED);
		bCard = new Card(this, "Important not urgent",Color.YELLOW);
		cCard = new Card(this, "Not urgent not Important",Color.BLUE);
		dCard = new Card(this, "Urgent not important",Color.GREEN);

		try {
			readFromFile(filename);
		} catch(Exception e) {
			JOptionPane.showMessageDialog(this, "Exception occured while reading from file", "Problem while reading the tasks", JOptionPane.ERROR_MESSAGE);
		}

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
