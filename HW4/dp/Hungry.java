
public class Hungry implements HungerState {

	@Override
	public void doAction(Swimmable obj) {
		obj.setState(new Hungry());
	}

}
