import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public interface SeaCreature extends Cloneable{
	
	
	public int getTime();
	
	SeaCreature makeCopy();
	
	void drawCreature(Graphics g);
	
	//public void display();
	
	public void setSuspend();
	
	public void setResume();
	
	public void stop();
	
	public void setBarrier(CyclicBarrier c);
	
	public String getOName();
	
	public void setColor(Color col);
	public String getColor();
	
	public void setSize(int size);
	public int getSize();
	
	public void setHorSpeed(int x);
	public int getHorSpeed();
	
	public void setVerSpeed(int y);
	public int getVerSpeed();
	
	public int getEatCount();

	public Color PaintFish();
	
	public void setIndex(int i);
	public int getIndex();
	
	public void setState(HungerState s );
	
}
