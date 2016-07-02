import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

public class Laminaria extends Immobile {

	

	public int index;

	public int size;
	public Random ran = new Random();
	
	Laminaria(){
		setName("Laminaria");
		size = 50;
		x= xRan + ran.nextInt(xBAF);

		xBAF = max;
		xRan = max - xRan;
		max -=100;
		
		
	}
	Laminaria(int s){
		
		size = s;
		
	}
	
	
	public void setSize(int size){
		this.size = size;
	}
	
	public void draw(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.setStroke(new BasicStroke(3));
		g2.setColor(Color.green);
		
		
		
		g.drawLine(x, y, x, y-size);
		g.drawLine(x-2, y, x-10, y-size*9/10);
		g.drawLine(x+2, y, x+10, y-size*9/10);
		g.drawLine(x-4, y, x-20, y-size*4/5);
		g.drawLine(x+4, y, x+20, y-size*4/5);
		g.drawLine(x-6, y, x-30, y-size*7/10);
		g.drawLine(x+6, y, x+30, y-size*7/10);
		g.drawLine(x-8, y, x-40, y-size*4/7);
		g.drawLine(x+8, y, x+40, y-size*4/7);
		g2.setStroke(new BasicStroke(1));
		
		
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
	
	public Laminaria clone(){
		return new Laminaria(size); 
	}
	@Override
	public SeaCreature makeCopy() {
		Laminaria laminariaObj = null;
		
		laminariaObj = clone();
		

		return laminariaObj;
	}



	
}
