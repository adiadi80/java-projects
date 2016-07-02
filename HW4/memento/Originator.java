

public class Originator {
	private SeaCreature sea;
	
	public void set (SeaCreature newSea){
		sea = newSea;
	}
	
	public Memento storeInMemento(){
		return new Memento(sea);
	}
	
	public SeaCreature restoreFromMemento(Memento memento){
		sea = memento.getSave();
		return sea;
	}

}
