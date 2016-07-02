public class TimeObserver implements Observer{
	private double eatTime1;
	private double eatTime2;
	private double eatTime3;
	private double eatTime4;
	private double eatTime5;
	private Subject timeData;
	
	public TimeObserver(Subject timeData){
		this.timeData = timeData;
		timeData.registerObserver(this);
		
	}
	
	
	@Override
	public void update(double eatTime1, double eatTime2, double eatTime3, double eatTime4, double eatTime5) {
		this.eatTime1 = eatTime1;
		this.eatTime2 = eatTime2;
		this.eatTime3 = eatTime3;
		this.eatTime4 = eatTime4;
		this.eatTime5 = eatTime5;
		
	}

}
