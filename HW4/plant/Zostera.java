import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Zostera extends Immobile  {

	int index;
	
	int size;

	public Random ran = new Random();
	
	
	Zostera(){
		setName("Zostera");
		size = 50;
		x = xRan + ran.nextInt(xBAF);

		xBAF = max;
		xRan = max - xRan;
		max -=100;
	}
	
	Zostera(int size){
		this.size = size;
		
	}
	

	public void draw(Graphics g) {
		g.setColor(Color.green);
		
		g.fillArc(x-size/20, y-size, size/10, size*4/5, 0, 360);
		g.fillArc(x-size*3/20,y- size*13/15, size/10, size*2/3, 0, 360);
		g.fillArc(x+size/20,y- size*13/15, size/10, size*2/3, 0, 360);
		g.drawLine(x, y, x, y-size/5);
		g.drawLine(x, y, x-size/10, y-size/5);
		g.drawLine(x, y, x+size/10, y-size/5);
	
	}

	public void setSize(int size){
		this.size = size;
	}
	
	@Override
	public void drawCreature(Graphics g) {
		draw(g);
		
	}

	
	public String getOName(){
		return name;
	}


	
	public String getColor(){
		return "Green";
	}
	
	public int getSize(){
		return size;
	}

	@Override
	public void setIndex(int i) {
		index = i;		
	}

	@Override
	public int getIndex() {
		return index;
	}
	


	
	public Zostera clone(){
		return new Zostera(size); 
	}

	@Override
	public SeaCreature makeCopy() {
		Zostera zosteraObj = null;
		
		zosteraObj = clone();
		

		return zosteraObj;
	}



}
