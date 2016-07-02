/**
 * @author adi elyiahu - 301705687
 * @author alex sirotin - 307459305
 *  
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;



abstract public class Swimmable extends Thread implements SeaCreature ,MarineAnimal{
	
	
	
	
	protected int horSpeed;
	protected int verSpeed;
	public Swimmable() 
	{
		horSpeed = 0;
		verSpeed = 0; 
	}
	public Swimmable(int hor, int ver) {
		horSpeed = hor;
		verSpeed = ver;
	}
	
	
	public int getHorSpeed() { return horSpeed; }
	public int getVerSpeed() { return verSpeed; } 
	public void setHorSpeed(int hor) { horSpeed = hor; } 
	public void setVerSpeed(int ver) { verSpeed = ver;} 
	
	
	abstract public void setObserver(Subject td,int name);
	abstract public void set(int HorSpeed,int VerSpeed,int size,Color c,AquaPanel panel);
	//abstract public String getOName(); 
	abstract public void drawAnimal(Graphics g); 
	abstract public void setSuspend(); 
	abstract public void setResume(); 
	abstract public void setBarrier(CyclicBarrier b); 
	abstract public int getSize(); 
	abstract public void eatInc(); 
	abstract public int getEatCount(); 
	abstract public String getColor();

}
