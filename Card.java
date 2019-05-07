
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.util.ArrayList;

public class Card extends JPanel {

	JLabel titlelabel;
	ImageButton addbtn;

	JPanel listpanel;
	GridBagConstraints lp_c;

	private int gy = 0;

	ArrayList<ListItem> tasks;

	private Color brighterColor(Color c) {
		int r=c.getRed(),g=c.getGreen(),b=c.getBlue();
		if(r < 205) r+=50;
		if(g < 205) g += 50;
		if(b < 205) b += 50;

		return new Color(r,g,b);
	}

	public Card(String title, Color color) {
		tasks = new ArrayList<ListItem>();

		setLayout(new GridBagLayout());
		setBackground(color);
		titlelabel = new JLabel(title);

		GridBagConstraints c = new GridBagConstraints();

		c.weightx = c.weighty = 1;
		c.weighty = 0;

		JPanel headPanel = new JPanel(new BorderLayout());
		headPanel.setBackground(brighterColor(color));
		//headPanel.setBackground(Color.GREEN);
		headPanel.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.GRAY));
		//headPanel.setOpaque(false);
		c.fill = GridBagConstraints.HORIZONTAL;
		c.anchor = GridBagConstraints.PAGE_START;
		c.gridx = 0;
		c.gridy = 0;
		add(headPanel, c);
		JScrollPane contentPanel = new JScrollPane();//JPanel(new FlowLayout(FlowLayout.LEFT));
		contentPanel.setOpaque(false);
//		contentPanel.setBackground(Color.black);
		c.fill = GridBagConstraints.BOTH;
		c.gridx = 0;
		c.weighty = 1;
		c.gridy = 1;
		add(contentPanel, c);

		/*try {
			//addbtn = new JButton(new ImageIcon(ImageIO.read(new File("plus.png"))));
		} catch(Exception e) {
			System.out.println("Couldn't load editbutton");
		}*/
		addbtn = new ImageButton("plus.png");
//		addbtn.setSize(50,50);
//		addbtn.setOpaque(false);
//		addbtn.setFocusPainted(false);
//		addbtn.setBorderPainted(false);
//		addbtn.setContentAreaFilled(false);
//		addbtn.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addItem();
			}
		});
		headPanel.add(titlelabel, BorderLayout.WEST);
		headPanel.add(addbtn, BorderLayout.EAST);

		listpanel = new JPanel(new GridBagLayout());
		lp_c = new GridBagConstraints();
		lp_c.gridx = 0;
		lp_c.gridy = gy++;
		lp_c.weighty = 1;
		lp_c.weighty = 0;
		lp_c.fill = GridBagConstraints.HORIZONTAL;
		lp_c.anchor = GridBagConstraints.FIRST_LINE_START;
		//listpanel.add(new JLabel("abc"),lp_c);
		for(int i=0;i<tasks.size();i++) {
			//additemfunction
			//JLabel task = new JLabel(tasks.get(i));
			listpanel.add(tasks.get(i), lp_c);
			lp_c.gridy=gy++;
		}

/*		c = new GridBagConstraints();
		c.gridx = c.gridy = 0;
		c.fill = GridBagConstraints.BOTH;
		c.weightx = c.weighty = 1;
		c.anchor = GridBagConstraints.FIRST_LINE_START;*/

		//JScrollPane scrollpane = new JScrollPane(listpanel);
		//scrollpane.setPreferredSize(new Dimension(500,500));
		contentPanel.setViewportView(listpanel);
	}

	public void addItem() {
		String str = (String)JOptionPane.showInputDialog(this, "Enter task: ", "New task", JOptionPane.PLAIN_MESSAGE);

		ListItem task = new ListItem(str);

		tasks.add(task);

		/*JLabel task = new JLabel(str);S*/
		//task.setBorder(BorderFactory.createLineBorder(Color.black));
		task.setOpaque(false);
		listpanel.add(task, lp_c);
		lp_c.gridy=gy++;
		listpanel.revalidate();

		/*for(int i=0;i<tasks.size();i++) {
			System.out.print(tasks.get(i)+",");
		}
		System.out.println();*/
	}
}
