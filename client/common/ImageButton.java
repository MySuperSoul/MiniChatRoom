package client.common;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

@SuppressWarnings({ "serial", "unused" })
public class ImageButton extends JButton{
	public ImageButton(String url) {
		setOpaque(false);
		String image_path = "src/client/icon/";
		image_path += url;
		File input = new File(image_path);
		
		try {
			ImageIcon icon = new ImageIcon(ImageIO.read(input));
			setIcon(icon);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
