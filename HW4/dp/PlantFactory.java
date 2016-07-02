
public class PlantFactory implements AbstractSeaFactory {


	PlantFactory(){}
	
	@Override
	public Immobile produceSeaCreature(String type) {
		if(type.equals("Laminaria"))
			return new Laminaria();
		else if(type.equals("Zostera"))
			return new Zostera();
		else 
			return null;
	}

	@Override
	public SeaCreature getClone(SeaCreature sea) {
		// TODO Auto-generated method stub
		return null;
	}


}
