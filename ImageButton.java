
import java.awt.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ImageButton extends JButton {
//	Color color;
	BufferedImage image;

	ImageButton() {
		super();
		initButton();
//		this.color = new Color(0,0,0,0);
		//enableInputMethods(true);
		//addMouseListener(this);
	}

	ImageButton(String imagestr) {
		super();
		initButton();
		try {
			BufferedImage image = ImageIO.read(new File(imagestr));
			setIcon(new ImageIcon(image));
		} catch(Exception e) {
			System.out.println("Couldn't load image for the button");
		}
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
}
