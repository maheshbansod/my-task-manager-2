
import java.awt.*;
import java.awt.event.*;
import java.beans.*; //for property change listener
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Card extends JPanel {

	JLabel titlelabel;
	ImageButton addbtn;

	JPanel listpanel;
	GridBagConstraints lp_c;

	JFrame frame;

	private int gy = 0;

	ArrayList<ListItem> tasks;

	private Color brighterColor(Color c) {
		int r=c.getRed(),g=c.getGreen(),b=c.getBlue();
		if(r < 205) r+=50;
		if(g < 205) g += 50;
		if(b < 205) b += 50;

		return new Color(r,g,b);
	}

	public Card(JFrame frame, String title, Color color) {
		this.frame = frame;
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
		contentPanel.setBackground(color);
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
		addbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				addItem();
			}
		});
		headPanel.add(titlelabel, BorderLayout.WEST);
		headPanel.add(addbtn, BorderLayout.EAST);

		listpanel = new JPanel(new GridBagLayout());
		listpanel.setOpaque(false);
		lp_c = new GridBagConstraints();
		lp_c.gridx = 0;
		lp_c.gridy = gy++;
		lp_c.weighty = 1;
		lp_c.weighty = 0;
		lp_c.fill = GridBagConstraints.HORIZONTAL;
		lp_c.anchor = GridBagConstraints.FIRST_LINE_START;
		for(int i=0;i<tasks.size();i++) {
			//additemfunction
			listpanel.add(tasks.get(i), lp_c);
			lp_c.gridy=gy++;
		}

		contentPanel.setViewportView(listpanel);
	}

	public void addItem() {
		String str = (String)JOptionPane.showInputDialog(this, "Enter task: ", "New task", JOptionPane.PLAIN_MESSAGE);
		
		if(str == null) return;

		ListItem task = new ListItem(this, str);
//		task.addPropertyChangeListener(this);

		tasks.add(task);

		task.setOpaque(false);
/*		listpanel.add(task, lp_c);
		lp_c.gridy=gy++;
		listpanel.revalidate();

		/*for(int i=0;i<tasks.size();i++) {
			System.out.print(tasks.get(i)+",");
		}
		System.out.println();*/
		reAddAll();
	}

	private void reAddAll() {
		listpanel.removeAll();
		GridBagConstraints c = new GridBagConstraints();

		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.weightx = 1.0;
		c.gridy = 0;

		for(ListItem li : tasks) {
			listpanel.add(li, c);
			c.gridy+=1;
		}
		listpanel.revalidate();
		frame.revalidate();
	}

	public void removeItem(ListItem item) {
		tasks.remove(item);

		reAddAll();
	}

/*	public void propertyChange(PropertyChangeEvent evt) {
		String propName = evt.getPropertyName();
		if(propName.equals("delete")) {
			System.out.println("delete action fired");
		}
	}*/
	
	public void writeToFile(ObjectOutputStream out) throws Exception {
		out.write(getListSize());
		for(ListItem l : tasks) {
			out.writeObject(l.getText());
		}
	}

	public int getListSize() {
		return tasks.size();
	}

	public ArrayList<String> getList() {
		ArrayList<String> list = new ArrayList<String>();

		for(ListItem l: tasks) {
			list.add(l.getText());
		}
		return list;
	}
}
