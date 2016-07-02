import java.util.ArrayList;

public class TimeData implements Subject{
	
	private ArrayList<Observer> observers ;
	private double eatTime1;
	private double eatTime2;
	private double eatTime3;
	private double eatTime4;
	private double eatTime5;
	
	public TimeData(){
		observers = new ArrayList<Observer>();
	}
	
	
	
	@Override
	public void registerObserver(Observer newObserver) {
		observers.add(newObserver);
	}

	@Override
	public void unregisterObserver(Observer deleteObserver) {
		int observerIndex = observers.indexOf(deleteObserver);
		
		observers.remove(observerIndex);
				
	}

	@Override
	public void notifyObserver() {
		for(Observer observer:observers){
			observer.update(eatTime1, eatTime2, eatTime3, eatTime4, eatTime5);
		}
		
	}
	
	public void setTime1(double d){
		this.eatTime1 = d;
		notifyObserver();
	}
	public void setTime2(double eatTime2){
		this.eatTime2 = eatTime2;
		notifyObserver();
	}
	public void setTime3(double eatTime3){
		this.eatTime3 = eatTime3;
		notifyObserver();
	}
	public void setTime4(double eatTime4){
		this.eatTime4 = eatTime4;
		notifyObserver();
	}
	public void setTime5(double eatTime5){
		this.eatTime5 = eatTime5;
		notifyObserver();
	}
	
	
}
