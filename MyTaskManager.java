
import java.awt.*;
import javax.swing.*;

public class MyTaskManager extends JFrame {
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
		Card aCard = new Card("Urgent",Color.RED);
		Card bCard = new Card("Important not urgent",Color.YELLOW);
		Card cCard = new Card("Not urgent not Important",Color.BLUE);
		Card dCard = new Card("Urgent not important",Color.GREEN);

	/*	c.fill = GridBagConstraints.BOTH;
		//c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = c.weighty = 1;

		c.gridx = 0;
		c.gridy = 0;
		add(aCard, c);
		c.gridx = 1;
		c.gridy = 0;
		add(bCard, c);
		c.gridx = 0;
		c.gridy = 1;
		add(cCard, c);
		c.gridx = 1;
		c.gridy = 1;
		add(dCard, c);*/
		add(aCard);
		add(bCard);
		add(cCard);
		add(dCard);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);
		setVisible(true);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String args[]) {
		new MyTaskManager();
	}
}
