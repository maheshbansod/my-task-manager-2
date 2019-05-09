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

		label.setBorder(BorderFactory.createEmptyBorder(0,20,0,0));
		//setOpaque(false);
		//Color color = new Color(100,100,0);
		setBackground(new Color(255,255,255,127));
		setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.black));
		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		//panel.setOpaque(false);
		panel.setBackground(new Color(100,100,0));
		panel.add(donebtn);
		panel.add(deletebtn);
		add(panel, BorderLayout.EAST);

		donebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int option = JOptionPane.showConfirmDialog(null, "Have you completed this task and want to remove it from here?","Nice!",JOptionPane.YES_NO_OPTION );
				if(option == JOptionPane.YES_OPTION) {
					parent.removeItem(ListItem.this);
				}
			}
		});	

		deletebtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				int option = JOptionPane.showConfirmDialog(null, "Are you sure you want to remove it from here?","Confirm this action",JOptionPane.YES_NO_OPTION );
				if(option == JOptionPane.YES_OPTION) {
					parent.removeItem(ListItem.this);
				}
			}
		});
	}

	public String getText() {
		return label.getText();
	}
}
