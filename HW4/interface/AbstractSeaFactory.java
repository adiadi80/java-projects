
public interface AbstractSeaFactory {
	
	public abstract SeaCreature produceSeaCreature(String type);
	
	public abstract SeaCreature getClone(SeaCreature sea);
	
	
}
