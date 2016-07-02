
public class Satiated implements HungerState{

	@Override
	public void doAction(Swimmable obj) {
		obj.setState(new Satiated());
	}

}
