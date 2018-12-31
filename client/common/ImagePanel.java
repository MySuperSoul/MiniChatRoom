package client.common;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

@SuppressWarnings("serial")
public class ImagePanel extends JPanel{
	public Image image = null;
	public ImagePanel(String url) throws IOException {
		setOpaque(false);
		String image_path = "src/client/icon/";
		image_path += url;
		File input = new File(image_path);
		
		ImageIcon icon = new ImageIcon(ImageIO.read(input));
		image = icon.getImage();
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
	}
}
