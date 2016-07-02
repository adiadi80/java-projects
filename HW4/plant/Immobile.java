
public abstract class Immobile extends SeaPlant {
		String name;
		
		
		public static int max = 500;
		public static int xRan = 0; 
		public static int xBAF = 100; 
		public int x;
		public static int y= 500;
		
		public String getName(){return name;}
		public void setName(String name){this.name = name;}
		
		public int getX(){return x;}
		public void setX(int x){this.x = x;}
		
		public int getY(){return y;}
		public void setY(int y){this.y = y;}

		
		public void setXrand(int xR){
			xRan = xR;
		}
}
