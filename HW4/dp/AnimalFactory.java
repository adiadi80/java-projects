
public class AnimalFactory implements AbstractSeaFactory {
	AquaPanel ap;
	AnimalFactory (AquaPanel ap){
		this.ap = ap;
	}
		public Swimmable produceSeaCreature(String type){
			Swimmable swimmable =null;
			
			if(type.equals("Fish"))
				swimmable = new Fish();
			else
				swimmable = new Jellyfish();

		return swimmable;
		}
		public SeaCreature getClone(SeaCreature sea) {
		
			if(sea instanceof Fish)
				return (Fish)sea.makeCopy();
			else if( sea instanceof Jellyfish)
				return sea.makeCopy();
			else return null;

			
		}
}
