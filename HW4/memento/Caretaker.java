import java.util.ArrayList;

public class Caretaker {

	ArrayList<Memento> saveSea = new ArrayList<Memento>();
	
	public void addMemento(Memento m){
		saveSea.add(m);
	}
	public Memento getMemento(int index){
		return saveSea.get(index);
	}
	
	public ArrayList<Memento> getSaveSea(){
		return saveSea;
	}
}
