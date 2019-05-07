import java.awt.*;
import javax.swing.*;

public class ListItem extends JPanel {
	JLabel label;
	ImageButton donebtn, deletebtn;

	ListItem(String text) {
		label = new JLabel(text);
		donebtn = new ImageButton("greentick.png");
		deletebtn = new ImageButton("redx.png");

		setLayout(new BorderLayout());
		add(label, BorderLayout.CENTER);
		JPanel panel = new JPanel();
		panel.add(donebtn);
		panel.add(deletebtn);
		add(panel, BorderLayout.EAST);
	}
}
