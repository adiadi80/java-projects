import java.awt.Color;

abstract class MarineAnimalDecorator implements MarineAnimal{

	MarineAnimal tempMarineAnimal;
	
	MarineAnimalDecorator(MarineAnimal newMarineAnimal){
		
		tempMarineAnimal = newMarineAnimal;
	}
	
	
	@Override
	public Color PaintFish() {
		return tempMarineAnimal.PaintFish();
	}

}
