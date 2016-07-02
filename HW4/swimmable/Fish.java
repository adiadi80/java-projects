/**
 * @author adi elyiahu - 301705687
 * @author alex sirotin - 307459305
 *  
 */
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Fish  extends Swimmable implements MarineAnimal{

	
	HungerState state ;
	int index;
	
	MarineAnimal marineAnimal;
	MarineAnimalDecorator marineAnimalDecorator;
	
	////////////
	Subject td;
	double time;
	int name;
	//////////
	
	//the max swimmable object that need to be in the panel
	public AquaPanel panel;
	
	
	//Barrier of the fish
	public CyclicBarrier ts = null;
	public int y_dir=1;
	public static int  flag = 0;
	
	public Random ran = new Random();
	public Color col;
	
	//the value for the new speed
	private double k;
	private double v_old;
	private double v_new;
	private double newHorSpeed;
	private double newVerSpeed;
	
	//the value for the size count and the location
	public int x_front;
	public int y_front;
	public int size= 200;
	private int x_dir =1;
	public int eatCount;
	

	
	public Fish(){
		state = null;		
	}
	
	/**
	 * 
	 * @param HorSpeed //the horizontal speed of the fish
	 * @param VerSpeed // the vertical speed of the fish
	 * @param size //the size of the fish
	 * @param c //color of the fish
	 * @param panel // the panel  
	 */
	
	public Fish(int HorSpeed,int VerSpeed,int size,Color c,AquaPanel panel){

		super(HorSpeed,VerSpeed);
		this.panel = panel;
		x_front = 50 + ran.nextInt(500);
		y_front = 20 + ran.nextInt(400);
		this.size = size;	
		col = c;
		eatCount = 0;
		this.time = 8000 - ((size*5) + ran.nextInt(10*size));
		state = null;
		start();	
	}
	
	
	//Observer
		public void setObserver(Subject td,int name){
			this.td = td;
			this.name = name;
			this.time = 8000 - ((size*5) + ran.nextInt(10*size));
			setState(new Satiated());
		}
		/**
		 * if the fish is catch the food 
		 * 
		 */
		@Override
		public void eatInc() {
			eatCount++;
			this.time = 8000 - ((size*5) + ran.nextInt(10*size));
			setState(new Satiated());
		}
	
	
	
	/**
	 * the run meted is to move the fish Thread 
	 */
	public  void run(){
		
		
		while(true){
		if(ts !=null){
			//this function calculates and changes the direction of the fish to the food location
			startFood();
			
				try {
					/*
					 * Barrier //wait for all fish and start the race to the food 
					 */
					ts.await();
				} catch (InterruptedException | BrokenBarrierException e) {
				
					e.printStackTrace();
				}
				ts = null;
				
			}
	
		if(time == 1 ){
			JOptionPane.showMessageDialog(null, "Your Fish number "+ name +" is hungry ..!!");
			setState(new Hungry());
			time =0;
		}
		
				if(panel.getEatFood() &&  getHungerState() instanceof Hungry){
					
					
						
						
					//Only if the food button press, this new speed for the fish is located
					startFood();
					
					if(x_front> panel.getWidth() /2)
						   x_dir = -1;
						else
						   x_dir=1;
						if(y_front>panel.getHeight()/2)
						   y_dir=-1;
						else
						   y_dir=1;
				
						
					
						x_front+=(int)newHorSpeed*x_dir;
						y_front+=(int)newVerSpeed*y_dir;
						
					synchronized (this) {
						if((Math.abs(x_front- panel.getWidth()/2) <= 5) && (Math.abs(y_front- panel.getHeight()/2) <= 5)){
						   eatInc();
						   panel.eatFood(false);
						}
					}
						
				}else{
					if(time>=0){

						if(name == 1)
							((TimeData)td).setTime1(time--);
						if(name == 2)
							((TimeData)td).setTime2(time--);
						if(name == 3)
							((TimeData)td).setTime3(time--);
						if(name == 4)
							((TimeData)td).setTime4(time--);
						if(name == 5)
							((TimeData)td).setTime5(time--);
						//System.out.println(time);
						
					}
				//the movement of the fish
			          if(x_front > panel.getWidth()) 
		                    x_dir = -1;
		                if(y_front > panel.getHeight()) 
		                    y_dir = -1; 
		                if(x_front <0)
		                    x_dir = 1;
		                if(y_front <0)
		                    y_dir = 1;
		                /*
		                 * move the animal
		                 */
		                
		                this.x_front +=x_dir*getHorSpeed();
						this.y_front +=y_dir*getVerSpeed();
					
				}
		
			
				
			
			
			//if the sleep button is press 
			if(flag == 1){
			synchronized (this) {
				try {
					wait();
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}
				
			}
			
			
	
	}
			
			try {
				
				sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		
			
			
			

			
			
		}
			
	}
	
	/**
	 * 
	 * @return string //the name class 
	 */
	
	public String getOName() {
			return getClass().getName();
	}
	/**
	 * @param
	 * the fish printer
	 */
	@Override
	public void drawAnimal(Graphics g) {
		
		g.setColor(col);
	
		
		if (x_dir == 1) // fish swims to right side
		{
			
			
			
			// Body of fish
			g.fillOval(x_front - size, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front - size - size / 4, x_front - size - size / 4, x_front - size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue()));
			g2.fillOval(x_front - size / 5, y_front - size / 10, size / 10, size / 10);

			// Mouth of fish
			if (size > 70)
				g2.setStroke(new BasicStroke(3));
			else if (size > 30)
				g2.setStroke(new BasicStroke(2));
			else

				g2.setStroke(new BasicStroke(1));
			g2.drawLine(x_front, y_front, x_front - size / 10, y_front + size / 10);
			g2.setStroke(new BasicStroke(1));
		} else // fish swims to left side
		{
			
			
			// Body of fish
			g.fillOval(x_front, y_front - size / 4, size, size / 2);

			// Tail of fish
			int[] x_t = { x_front + size + size / 4, x_front + size + size / 4, x_front + size };
			int[] y_t = { y_front - size / 4, y_front + size / 4, y_front };
			Polygon t = new Polygon(x_t, y_t, 3);
			g.fillPolygon(t);

			// Eye of fish
						Graphics2D g2 = (Graphics2D) g;
						g2.setColor(new Color(255-col.getRed(),255-col.getGreen(),255-
								col.getBlue()));
						g2.fillOval(x_front+size/10, y_front-size/10, size/10, size/10);
						
						// Mouth of fish
						if(size>70)	
							g2.setStroke(new BasicStroke(3));
						else if(size>30)
							g2.setStroke(new BasicStroke(2));	
						else
							g2.setStroke(new BasicStroke(1));
						g2.drawLine(x_front, y_front, x_front+size/10, y_front+size/10);
						g2.setStroke(new BasicStroke(1));
						
	
		}
		
	}
	
	/**
	 * Suspend the fish
	 */
	@Override
	public void setSuspend() {
		flag = 1;
	}
	/**
	 * Resume the fish
	 */
	@Override
	public void setResume() {
		synchronized (this) {
			notify();
			}
		flag =0;
	}

	/**
	 * set the Barrier if the food button is Pressed
	 */
	@Override
	public void setBarrier(CyclicBarrier b) {
		
		ts = b;

		
	}
	
	/**
	 * return the size of the fish
	 * @return size
	 */
	@Override
	public int getSize() {
		return size;
	}

	
	/**
	 * @return the eat count 
	 */
	@Override
	public int getEatCount() {
		
		return eatCount;
	}

	/**
	 * @return the color of the fish
	 */
	@Override
	public String getColor() {
		return "("+col.getRed()+","+col.getGreen()+","+col.getBlue()+")";
		/*
		 * if(col.getRGB()==-14336){
			return "orange";
		}else if(col.getRGB()==-16776961){
			return "BLUE";
		}else if(col.getRGB()==-20561){
			return "PINK";
		}else if(col.getRGB()==-256){
			return "YELLOW";
		}else{
			return "RED";
		}
		 */
	}

	
	
	
	
	
	
	/**
	 * Calculates the new vector direction of food
	 */
	public  void startFood(){
		
		// (1)v_old = sqrt(v_hor*v_hor + v_ver*v_ver)
		v_old = Math.sqrt(verSpeed*verSpeed + horSpeed*horSpeed); 
		
	
		try {
			k = Math.abs((y_front - panel.getHeight()/2) / (x_front - panel.getWidth()/2));
		} catch (ArithmeticException e) {
			System.out.println(e.getMessage());;
		}
		 //set the new horSpeed
		 newHorSpeed = (v_old / (double)Math.sqrt(k*k+1));
		//set the new VerSpeed
		 newVerSpeed = newHorSpeed * k;

		
	}

	@Override
	public void set(int HorSpeed,int VerSpeed,int size,Color c,AquaPanel panel){
		this.td = td;
		setHorSpeed(HorSpeed);
		setVerSpeed(VerSpeed);
		this.panel = panel;
		x_front = 50 + ran.nextInt(500);
		y_front = 20 + ran.nextInt(400);
		this.size = size;	
		col = c;
		eatCount = 0;
		start();
	}
	
	
	
	
	@Override
	public void drawCreature(Graphics g) {

		drawAnimal(g);
	}

	@Override
	public void setSize(int size) {
		this.size = size;
		
	}

	@Override
	public SeaCreature makeCopy() {
		
		Fish fishObj = null;
		
		fishObj = clone();
		
		/*
		 * try {
			fishObj = (Fish) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		 */
		return fishObj;
	}
	public Fish clone(){
		return new Fish(getHorSpeed(),getVerSpeed(),size,col,panel); 
	}

	@Override
	public void setColor(Color col){
		this.col = col;
	}
	
	public int getTime(){
		return (int) time;
	}

	
	public void setDecorator(MarineAnimal newMarineAnimal){
		
		
	}
	
	@Override
	public Color PaintFish() {
		
		return col;
	}

	@Override
	public void setIndex(int i) {
		index = i;
		
	}
	
	@Override
	public int getIndex() {
		return index;
	}

	@Override
	public void setState(HungerState s) {
		state = s;
	}
	public HungerState getHungerState() {
        return state;
    }

	
}
