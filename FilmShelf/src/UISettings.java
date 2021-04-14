import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public final class UISettings {
	private static Color fontColor = Color.WHITE;
    private static Color buttonColor = new Color(247,216,91); //light orange
    private static Image backgroundImage = Toolkit.getDefaultToolkit().getImage("Images\\dLB5ai0-blue-gradient-wallpaper.jpg");
    private static Image starImage = Toolkit.getDefaultToolkit().getImage("Images\\846480.png");
    
	public static Color getFontColor() {
		return fontColor;
	}
	
	public static Color getButtonColor() {
		return buttonColor;
	}
	
	public static Image getBackgroundImage() {
		return backgroundImage;
	}
	
	public static Image getStarImage() {
		return starImage;
	}
	
	public static ImageIcon getStarsIcon(int numStars, int size, int offset) {
		Image star = starImage.getScaledInstance(size, size, Image.SCALE_SMOOTH);
		ImageIcon starIcon = new ImageIcon(star);

		BufferedImage bi = new BufferedImage(size * numStars+(numStars-1)*offset, size * numStars,
				BufferedImage.TYPE_INT_ARGB);
		Graphics bg = bi.getGraphics();
		for (int i = 0; i < numStars; i++)
		{
			bg.drawImage(star,i*size+(i)*offset,0,null);
		}
		bg.dispose();

		return new ImageIcon(bi);
	}
}
