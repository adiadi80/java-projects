/**
 * @author adi elyiahu - 301705687
 * @author alex sirotin - 307459305
 *  
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;
import java.util.concurrent.CyclicBarrier;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class AquaPanel extends JPanel implements ActionListener {
	
	
	int index =0;
	

	public TimeData  timeData;
	public TimeObserver observer;
	
	
	public Singleton singleton;
	
	public int b =0;
	
	public int sum = 0;
	
	public JFrame fr;
	
	public JTable tab ;
	
	public String[] sTab = { "Animal", "Color", "Size", "Hor.speed",
	   "Ver.speed","Eat counter"};
	
	
	public String[][] matrixTabl ;

	
	private Boolean tfInfo = true;
	private Boolean pushFood = false;
	private int flag = 0;
	private ImageIcon imgI;
	
	private int maxSwimm = 0 ;
	private int maxPlant = 0 ;
	
	private HashSet<SeaCreature> seaL;
	
	//memento
	private HashSet<SeaCreature> mementoSeaL;
	int saveSize = 0;
	
	final int NUM_OF_OBJ = 5;

	public CyclicBarrier theSwimManager ;
	
	
	//jbutton
	public JButton decoratorB;
	public JButton duplicateAnimalB;
	public JButton AddAnimal;
	public JButton AddPlant;
	public JButton Sleep;
	public JButton Wakeup; 
	public JButton Reset;
	public JButton Food;
	public JButton Info;
	public JButton Exit;

	
	//JPanel
	static public JPanel panel = new JPanel();
	
	
	PlantFactory plantFactory;
	AnimalFactory animalFactory = new AnimalFactory(this);
	
	
	
	Caretaker caretaker;
	Originator originator;
	//////////////////////////////////////////////////////////////////////
	public AquaPanel(JFrame f){
		
	//////////////////////////////////////////
		timeData = new TimeData();
		observer = new TimeObserver(timeData);
		
		
	/////////////////////////////////////////
		
		
		/////////////
		caretaker = new Caretaker();
		originator = new Originator();
		/////////////
		
		plantFactory = new PlantFactory();
		animalFactory = new AnimalFactory(this);
		
		singleton = Singleton.getInstance();
		
		matrixTabl = new String[NUM_OF_OBJ ][NUM_OF_OBJ +2];
		
		fr = f;
		fr.add(this);
		
		//set the panel in south side and pick the color
		fr.add(panel,BorderLayout.SOUTH);
		panel.setBackground(Color.DARK_GRAY);
		
		
		
		
		
		imgI = new ImageIcon(getClass().getResource("aquarium_background.jpg"));
		//flag =1;
		
		setPreferredSize(new Dimension(900,503));
		
		//hashset of swimmable objects
		seaL = new HashSet<SeaCreature>();
		
		mementoSeaL = new HashSet<SeaCreature>();
		
		
		setBackground(Color.white);
		
		
		//add name to button
		AddAnimal = new JButton("Add Animal");
		AddPlant = new JButton("Add Plant");
		decoratorB = new JButton("decorator");
		duplicateAnimalB  = new JButton("Duplicate Animal");
		Sleep = new JButton("Sleep");
		Wakeup = new JButton("Wake up"); 
		Reset = new JButton("Reset");
		Food = new JButton("Food");
		Info = new JButton("Info");
		Exit = new JButton("Exit");
	
		
		
		
		
		// add options to the Down buttons
		panel.add(AddAnimal);
		panel.add(AddPlant);
		panel.add(decoratorB);
		panel.add(duplicateAnimalB);
		panel.add(Sleep);
		panel.add(Wakeup);
		panel.add(Reset);
		panel.add(Food);
		panel.add(Info);
		panel.add(Exit);

		
		//add listener to buttons
		decoratorB.addActionListener(this);
		duplicateAnimalB.addActionListener(this);
		AddPlant.addActionListener(this);
		AddAnimal.addActionListener(this);
		Sleep.addActionListener(this);
		Wakeup.addActionListener(this);
		Reset.addActionListener(this);
		Food.addActionListener(this);
		Info.addActionListener(this);
		Exit.addActionListener(this);
		
		
		
		
		
	}

	
	////////////////////////////////////////////////////////////////////////////////////////////////
	/**
	 * check the press button 
	 * @param ActionEvent 
	 */
	@Override
	public void actionPerformed(ActionEvent e){
		
		
		
		
		
		if(e.getSource() == decoratorB){
			if(maxSwimm !=0){
				Color color = null;
				ChooseDialog cd  = new ChooseDialog(seaL,2);
				color = JColorChooser.showDialog(null,"Pick your color", color);
					 if(color !=null)
						 cd.getChoose().setColor(color);
			}
			else{
				JOptionPane.showMessageDialog(this,"Nothing to chinge color..");
			}
		}
		
		
		
		
		
		if(e.getSource() == duplicateAnimalB){
			
			if(maxSwimm !=5 && maxSwimm !=0){
				ChooseDialog chooseDialog  = new ChooseDialog(seaL,1);
				if(chooseDialog.getChoose() != null){
					Swimmable tempsea = (Swimmable) animalFactory.getClone(chooseDialog.getChoose());
					tempsea.setIndex(index);
					tempsea.setObserver(timeData,maxSwimm+1);
					index++;
					seaL.add(tempsea);
					UpdateDialog up = new UpdateDialog(tempsea);
					up.setVisible(true);
					
					maxSwimm++;
				}
					
				
				
		        
			}else if(maxSwimm ==5) {
				JOptionPane.showMessageDialog(this,"Maximum animals in aquarium is 5");
			}else{
				JOptionPane.showMessageDialog(this,"Nothing to copy");
			}

		}
		
		if(e.getSource() ==  AddPlant){
			if(maxPlant !=5){
				maxPlant++;
			
				//create AddAnimalDialog and get back the parameters
				AddPlantlDialog pd = new AddPlantlDialog();
				pd.setVisible(true);
				
				
		
				
				Immobile im = plantFactory.produceSeaCreature(pd.getName());
				
				if(maxPlant == 1)
					im.setXrand(20);
				
				im.setSize(pd.getPSize());
				im.setIndex(index);
				index++;
				seaL.add(im);
				repaint();
			}
			else{
				JOptionPane.showMessageDialog(this,"Maximum plants in aquarium is 5");
			}
		}
	
		
		if(e.getSource() == AddAnimal){
			
			if(maxSwimm != 5){
				timeData.setTime1(0.5);
				//create AddAnimalDialog and get back the parameters
				AddAnimalDialog ad = new AddAnimalDialog();
				ad.setVisible(true);
				//create Swimmable object
				
				Swimmable s = animalFactory.produceSeaCreature(ad.getanimalname());
				
				//set 
				s.set(ad.getx(),ad.gety(),ad.getSizeA(),ad.getCol(),this);
				s.setIndex(index);
				s.setObserver(timeData,maxSwimm+1);
				
				
				///////////////
				seaL.add(s);
				
				index++;
				repaint();
				maxSwimm++;
			}else{
				JOptionPane.showMessageDialog(this,"Maximum animals in aquarium is 5");
			}

		}
		
		
		if(e.getSource() == Sleep){
			for(SeaCreature a: seaL){
				if(a instanceof Swimmable)
					a.setSuspend();	
			}
			
		}
		
		
		if(e.getSource() == Wakeup){
			for(SeaCreature a: seaL){
				if(a instanceof Swimmable)
					a.setResume();
		}
			 
			
		}
		
		if(e.getSource() == Reset){
			
			for(int i = 0 ;i<maxSwimm;i++){
				for(int j = 0 ;j<NUM_OF_OBJ+1;j++){
					matrixTabl[i][j] = "";
				}
			
			}
			pushFood = false;
			maxSwimm = 0;
			maxPlant = 0;
			for(SeaCreature a: seaL){
				if(a instanceof Swimmable)
					a.stop();
			}
			 
			mementoSeaL.removeAll(mementoSeaL);
			saveSize = 0;
		
			seaL.removeAll(seaL);
	
		}
		
		if(e.getSource() == Food){
			pushFood = true;
			try {
				theSwimManager = new CyclicBarrier(maxSwimm);
			} catch (IllegalArgumentException e2) {
				System.out.println(e2.getMessage());
			}
			
			 for(SeaCreature a: seaL){	
				 if(a instanceof Swimmable)
					 a.setBarrier(theSwimManager);
			}
			 
			
		}
		
		if(e.getSource() == Info){
			int i=0;
			sum = 0;
			try {//enter the info of the swim object 
				for(SeaCreature a: seaL){
					if(a instanceof Swimmable){
						matrixTabl[i][0] = a.getOName();
						matrixTabl[i][1] = a.getColor();
						matrixTabl[i][2] = Integer.toString(a.getSize());
						matrixTabl[i][3] = Integer.toString(a.getHorSpeed());
						matrixTabl[i][4] = Integer.toString(a.getVerSpeed());
						matrixTabl[i][5] = Integer.toString(a.getEatCount());
						sum += a.getEatCount();
						i++;
					}
				}
				
				matrixTabl[maxSwimm][0] = "Total";
				matrixTabl[maxSwimm][5] = Integer.toString(sum);
				
			} catch (Exception e2) {
				System.out.println(e2.getMessage());
			}
			
			//create jTable and enter the inf
			tab = new JTable(matrixTabl,sTab);
			tab.setPreferredScrollableViewportSize(new Dimension(880,480));
			JScrollPane jps = new JScrollPane(tab);		
			fr.add(jps);			
			tab.setFillsViewportHeight(tfInfo);
			tfInfo = !tfInfo;
			setVisible(tfInfo);
			fr.pack();

		}
		
		if(e.getSource() == Exit){
			//tab.setFillsViewportHeight(false);
			System.exit(0);
		}
		
	}
	
	
	

	
	public JPanel getPanel(){
		return panel;
	}
	
	
	public void setBackgr(int i){
		if(i == 2){//set Background to blue
			flag = 0;
			this.setBackground(new Color(20, 200, 238));
			setSize(getWidth(),getHeight());
			fr.pack();
			
		}	//set Background to white
		else if(i == 1){
			flag=0;
			this.setBackground(Color.white);
			setSize(getWidth(),getHeight());
			fr.pack();
			
		}
			
		else{//set image
			flag = 1;
		}
	}
	
	
	@Override
    public void paintComponent(Graphics g) {
		
		
		super.paintComponent(g);
		

		
		Image im = imgI.getImage(); 

		if(flag ==1){//set image
			g.drawImage(im, 0, 0,this); 
			setSize(getWidth(),getHeight());
			fr.pack();
			
		}

		for(SeaCreature a: seaL){
			a.drawCreature(g);
			repaint();
		}
		
		
		if(pushFood)
		{//print warm
			
			singleton.getWorm(g, this);
			
		 }
		
		
		
    }
	
	
	//set eatfood
	public void eatFood(Boolean a){
		pushFood =a;
	}
	
	//get eat food
	public Boolean getEatFood() {
		return pushFood;
		
	}
	/////////////////////
	public void saveObjectState(){
		
		ChooseDialog cd = new ChooseDialog(seaL,3);
		if(cd.getChoose() != null){
			Boolean c = true;
			for(SeaCreature seaRestore: mementoSeaL){
				if(cd.getChoose().getIndex() == seaRestore.getIndex()){	
					c = false;
				}
			}
				if(c){
					SeaCreature tempsea = animalFactory.getClone(cd.getChoose());
					tempsea.setIndex(cd.getChoose().getIndex());
					originator.set(tempsea);
					caretaker.addMemento(originator.storeInMemento());
					saveSize++;
				}
				
			
		
		}
	}
	
	public void restoreObjectState(){
		for(int i =0;i< saveSize ;i++){
			SeaCreature s =  originator.restoreFromMemento(caretaker.getMemento(i));
			mementoSeaL.add(s);
		}
		
		if(saveSize !=0){
			ChooseDialog cd = new ChooseDialog(mementoSeaL,4);
		
			for(SeaCreature seaRestore: seaL){
				if(cd.getChoose().getIndex() == seaRestore.getIndex()){
					seaRestore.setSize(cd.getChoose().getSize());
					seaRestore.setColor(cd.getChoose().PaintFish());
					seaRestore.setHorSpeed(cd.getChoose().getHorSpeed());
					seaRestore.setVerSpeed(cd.getChoose().getVerSpeed());
				}
		
			}
			
			
		}
		
		
		
	}
	

	
}

