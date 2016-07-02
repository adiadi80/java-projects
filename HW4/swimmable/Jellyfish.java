/**
 * @author adi elyiahu - 301705687
 * @author alex sirotin - 307459305
 *  
 */
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

import javax.swing.JOptionPane;

public class Jellyfish extends Swimmable implements MarineAnimal{

	
	

	HungerState state;
	
	int index;
	//Observer
	/////////////
	double time;
	Subject td;
	int name;
	////////////
	
	//Barrier of the Jelly fish
	static CyclicBarrier ts;
	
	Random ran = new Random();
	
	
	AquaPanel panel;
	
	//the value for the new speed
	double k;
	double v_old;
	double v_new;
	double newHorSpeed;
	double newVerSpeed;
	
	
	//the value for the size count and the location
	Color col;
	int x_front;
	int y_front;
	int size;
	int x_dir = 1;
	int y_dir = 1;
	int eatCount;
	
	
	static int  flag = 0;
	
	
	/**
	 * 
	 * @param HorSpeed //the horizontal speed of the jelly fish
	 * @param VerSpeed // the vertical speed of the jelly fish
	 * @param size //the size of the jelly  fish
	 * @param c //color of the jelly fish
	 * @param panel // the panel  
	 */
	public Jellyfish(){
		state = null;
	}
	
	
	
	
	public Jellyfish(int x,int y,int size,Color c,AquaPanel panel){
		super(x,y);
		
		this.panel = panel;
		state = null;
		x_front = 50 + ran.nextInt(600);
		y_front = 20 + ran.nextInt(400);
		this.size = size;
		col = c;
		eatCount = 0;
		
		start();	
	}
	
	@Override
	public void set(int HorSpeed,int VerSpeed,int size,Color c,AquaPanel panel){
		state = null;
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
	
	//Observer
	public void setObserver(Subject td,int name){
		this.td = td;
		this.name = name;
		this.time = 8000 - ((size*5) + ran.nextInt(10*size));
		setState(new Satiated());
	}
	/**
	 * if the jellyfish is catch the food 
	 * 
	 */
	@Override
	public void eatInc() {
		eatCount++;
		this.time = 8000 - ((size*5) + ran.nextInt(10*size));
		setState(new Satiated());
	}
	
	/**
	 * the run meted is to move the jelly fish Thread 
	 */
	
	public void run(){
		while(true){

			//this function calculates and changes the direction of the jelly fish to the food location

			if(ts !=null){
				/*
				 * Barrier //wait for all jelly fish and start the race to the food 
				 */
				startFood();
				try {
					ts.await();
				} catch (InterruptedException | BrokenBarrierException e) {
					e.printStackTrace();
				}
				ts = null;
			}
			

			if(time == 1 ){
				JOptionPane.showMessageDialog(null, "Your Jellyfish number "+ name +" is hungry hungry..!!");
				setState(new Hungry());
				time =0;
				
			}
			
			
			if(panel.getEatFood() && getHungerState() instanceof Hungry){
				
			
				
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
					if((Math.abs(x_front- panel.getWidth()/2) <= 5) && (Math.abs(y_front- panel.getHeight()/2) <= 5))
					{
					   eatInc();
					   setState(new Satiated());
					   panel.eatFood(false);
					}
				}
			}else{
				
				if(time>=-1){

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
				
				/*
                 * check if the animal is on the board of the frame then change the direction
                 */
                if(x_front > panel.getWidth()) // if it arrive to the end of the screen
                    x_dir = -1;
                if(y_front > panel.getHeight())  
                    y_dir = -1; //change fish swimming to other side
                if(x_front <0)
                    x_dir = 1;
                if(y_front <0)
                    y_dir = 1;
                /*
                 * move the animal
                 */
                x_front += x_dir*getHorSpeed();
                y_front += y_dir*getVerSpeed();
                
			}
			
			try {
				Thread.sleep(30);
				
			} catch (InterruptedException e) {
				
				e.printStackTrace();
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
	 * the jelly fish printer
	 */
	@Override
	public void drawAnimal(Graphics g) {
		int numLegs;
		if(size<40)
			numLegs = 5;
		else if(size<80)
			numLegs = 9;
		else
			numLegs = 12;
		
		g.setColor(col);
		g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);
		
		for(int i=0; i<numLegs; i++)
			g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1),
					y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1),
					y_front+size/3);
	}
	
	/**
	 * Suspend the jelly fish
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
	 * @return the color of the jelly fish
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



////////////////////////////////
	@Override
	public void drawCreature(Graphics g) {
		int numLegs;
		if(size<40)
			numLegs = 5;
		else if(size<80)
			numLegs = 9;
		else
			numLegs = 12;
		
		g.setColor(col);
		g.fillArc(x_front - size/2, y_front - size/4, size, size/2, 0, 180);
		
		for(int i=0; i<numLegs; i++)
			g.drawLine(x_front - size/2 + size/numLegs + size*i/(numLegs+1),
					y_front, x_front - size/2 + size/numLegs + size*i/(numLegs+1),
					y_front+size/3);		
	}

	
	
	@Override
	public void setSize(int size) {
		this.size = size;
		
	}




	@Override
	public SeaCreature makeCopy() {
		Jellyfish jellyfishObj = null;
		
		jellyfishObj  = clone();
		
		/*
		 * try {
			jellyfishObj = (Jellyfish)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 */
		
		
		return jellyfishObj;
	}
	
	public Jellyfish clone() {
		return new Jellyfish(getHorSpeed(),getVerSpeed(),size,col,panel);	
	}
	
	@Override
	public void setColor(Color col) {
		this.col = col;
	}

	public int getTime(){
		return (int) time;
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
