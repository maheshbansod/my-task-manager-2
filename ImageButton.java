
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ImageButton extends JButton {
	Color color;
	BufferedImage image;

	ImageButton() {
		super();
		initButton();
		this.color = new Color(0,0,0,0);
		//enableInputMethods(true);
		//addMouseListener(this);
	}

	ImageButton(String imagestr, Color color) {
		super();
		//enableInputMethods(true);
		//addMouseListener(this);
		initButton();
		try {
			BufferedImage image = ImageIO.read(new File("editbutton.png"));
		} catch(Exception e) {
			System.out.println("Couldn't load image edit button");
		}

		this.color = color;
	}

	private void initButton() {
		setOpaque(false);
		setFocusPainted(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
	}

	public Dimension getPreferredSize() {
		return new Dimension(50,50);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;

		g2.setPaint(color);

		g2.fill(new Ellipse2D.Double(0,0,getSize().width, getSize().width));
		g2.drawImage(image, 0,0,getSize().width, getSize().width, null);
	}

}
