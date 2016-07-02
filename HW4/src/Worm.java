import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Worm {
	
	
	Worm(){};
	public void drawAnimal(Graphics g,AquaPanel panel) {
		Graphics2D g2 = (Graphics2D) g; 
		g2.setStroke(new BasicStroke(3));
	    g2.setColor(Color.red);
	    g2.drawArc(panel.getWidth()/2, panel.getHeight()/2-5, 10, 10, 30, 210);	   
	    g2.drawArc(panel.getWidth()/2,panel.getHeight()/2+5, 10, 10, 180, 270);	
	    g2.setStroke(new BasicStroke(1)); 
	}
	
}
