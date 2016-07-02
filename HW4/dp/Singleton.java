import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Singleton {
	
	private static  Worm worm;
	private static Singleton instance = null;
	static Boolean wb = true;
	
	/* A private Constructor prevents any other * class from instantiating.*/
	Singleton(){ }
	/* Static 'instance' method */ 
	
	
	public static  Singleton getInstance( ){
		if(instance == null){
			if(wb){
				worm = new Worm();
				wb = false;
			}	
			instance = new Singleton();
		}
		return instance;
	}
	
	public static void setInstance(Singleton s ){
		instance = s;
	}
	public void getWorm(Graphics g,AquaPanel panel) {
		worm.drawAnimal(g, panel);
	}
	
	
	
	
}

