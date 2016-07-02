import java.awt.Color;
import java.awt.Graphics;
import java.util.concurrent.CyclicBarrier;

public abstract class SeaPlant implements SeaCreature {

	public abstract void drawCreature(Graphics g);

	
	public void setSuspend(){}
	
	public void setResume() {}
	
	public void setSize(int size) {}
	
	public void stop(){}
	
	public void setBarrier(CyclicBarrier c){}
	
	public int getHorSpeed(){return 0;}
	
	public int getVerSpeed(){return 0;}

	public int getEatCount(){return 0;}
	

	
	public void setColor(Color col) {}
	
	@Override
	public void setHorSpeed(int x) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setVerSpeed(int y) {
		// TODO Auto-generated method stub
		
	}
	public int getTime(){
		return 0;
	}
	
	public Color PaintFish(){
		return Color.green;
	}
	
	public void setState(HungerState s){}
	
}
