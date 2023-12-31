package fruityfury.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.io.IOException;

import fruityfury.model.Field;
import fruityfury.model.GameObject;

public class GameView extends JPanel {
	private static final long serialVersionUID = -7837067837641826234L;

	private Field field;
	private BufferedImage bg;
	private ImageIcon[] images;

	/**
	 * Sets up the dimensions of the panel and loads the images to be used in
	 * the rendering process.
	 * 
	 * @param field The field used as game field.
	 */
	public GameView(Field field) {
		super();
		// Set dimensions for the view
		Dimension dim = new Dimension(500, 500);
		setMaximumSize(dim);
		setMinimumSize(dim);
		setPreferredSize(dim);
		setSize(dim);
		// Load field
		this.field = field;
		// Load background
		try {
			bg = ImageIO.read(getClass().getResource("/fruityfury/resources/BG Fruity Fury.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Load images for the GameObjects
		images = new ImageIcon[7];
		images[0] = createIcon("/fruityfury/resources/Apel (2).png", "apple");
		images[1] = createIcon("/fruityfury/resources/Jeruk (1).png", "orange");
		images[2] = createIcon("/fruityfury/resources/Melon (1).png", "melon");
		images[3] = createIcon("/fruityfury/resources/Pisang (1).png", "banana");
                                    images[4] = createIcon("/fruityfury/resources/Strawberry (1).png", "strawberry");
                                    images[5] = createIcon("/fruityfury/resources/Bom Panjang (1).png", "bomb");
                                    images[6] = createIcon("/fruityfury/resources/Bom Bulet (1).png", "bomb");
	}

	/**
	 * This method is used to create a new ImageIcon based on the path and
	 * desription in the parameters.
	 * 
	 * @param path
	 *            The path to the image as registered in the classpath (path
	 *            after /src/).
	 * @param description
	 *            Description for the image.
	 * @return A new ImageIcon with the image as defined in the path argument.
	 */
	private ImageIcon createIcon(String path, String description) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL, description);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/**
	 * This method is overridden from JPanel, it is used by swing to paint the
	 * view. It renders the GameObject on the game field.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		// Draw the background
		g.drawImage(bg, 0, 0, this);
		// Draw hte GameObject on screen
		GameObject obect = field.getObject();
		images[obect.getType()].paintIcon(this, g, obect.getX(), obect.getY());
	}

}
