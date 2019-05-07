import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ListItem extends JPanel {
	JLabel label;
	ImageButton donebtn, deletebtn;
	Card parent;

	ListItem(Card c, String text) {
		parent = c;

		label = new JLabel(text);
		donebtn = new ImageButton("greentick.png");
		deletebtn = new ImageButton("redx.png");

		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.add(donebtn);
		panel.add(deletebtn);
		add(panel, BorderLayout.EAST);

		donebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				JOptionPane.showMessageDialog(null, "Great!");
//				firePropertyChange("delete", null, this);
				parent.removeItem(ListItem.this);
			}
		});
	}

	public String getText() {
		return label.getText();
	}
}
