import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

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
	
	/*public static ImageIcon getStarIcon(int numStar) {
		
	}*/
}
