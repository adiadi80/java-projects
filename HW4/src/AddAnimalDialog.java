/**
 * @author adi elyiahu - 301705687
 * @author alex sirotin - 307459305
 *  
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

public class AddAnimalDialog extends JDialog implements ActionListener{
	
	//parameter for the animal
	int size;
	int horizontalSpeed;
	int verticalSpeed;
	int index;
	String animal = null;
	
	//JList for color
	JList list;
	// color names
	String[] listColorNames = { "orange", "blue", "pink", "yellow","red" };
	// color parameters
	Color[] listColorValues = { Color.orange, Color.BLUE, Color.pink,
			   Color.YELLOW, Color.RED };
	
	//JSlider
	JSlider horizontalspeedS = new JSlider(SwingConstants.HORIZONTAL, 1, 10, 1);
	JSlider verticalSpeedS = new JSlider(SwingConstants.HORIZONTAL, 1, 10, 1);
	JSlider sizeS = new JSlider(SwingConstants.HORIZONTAL, 20, 320, 20);
	
	//JButton
	public JButton FishB;
	public JButton jellyFishB;
	
	
	 private static final int JDISPOSE_ON_CLOSE = 0;
	 private String name[] = {"Size: ", "Horizontal speed: ", "Vertical Speed: "};
	 private Label labels[] = new Label[3];
	   
	    
	    /**
	     * constructor for the DIALOG
	     */
		public AddAnimalDialog(){
		
			
			list = new JList(listColorNames);
			list.setSelectedIndex(0);
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			JPanel Panel = new JPanel();
			Panel.setLayout(new GridLayout(0, 1));
			
			
			//fish button
			FishB = new JButton("Fish"); 
			Panel.add(FishB);
			
			//gelly fish button
			jellyFishB = new JButton("Geli Fish"); 
		    Panel.add(jellyFishB); 
			
			 for (int i = 0; i < name.length; i++) {
		            labels[i] = new Label(name[i]);
		            
			 }
			 //size slider
			 		Panel.add(labels[0]);
		            sizeS.setMinorTickSpacing(2);
					sizeS.setMajorTickSpacing(60);
					sizeS.setPaintTicks(true);
					sizeS.setPaintLabels(true);
					Panel.add(sizeS);
					
					Panel.add(labels[1]);
					//horizontal slider
					horizontalspeedS.setMinorTickSpacing(1);
					horizontalspeedS.setMajorTickSpacing(1);
					horizontalspeedS.setPaintTicks(true);
					horizontalspeedS.setPaintLabels(true);
					Panel.add(horizontalspeedS);
					
					Panel.add(labels[2]);
					//vertical slider
					verticalSpeedS.setMinorTickSpacing(1);
					verticalSpeedS.setMajorTickSpacing(1);
					verticalSpeedS.setPaintTicks(true);
					verticalSpeedS.setPaintLabels(true);
		            Panel.add(verticalSpeedS);
		        

		            FishB.addActionListener(this);
				    jellyFishB.addActionListener(this);
			
				    
				    Panel.add(new JScrollPane(list));
			 
		        setDefaultCloseOperation(JDISPOSE_ON_CLOSE);
		        setModal(true);
		        add(Panel, BorderLayout.CENTER);
		        
		        
		        setSize(200, 600);
		        setLocation(800, 5);
		        setTitle("Add an animal to aquarium");
		        setVisible(false);
			
		}

		public void actionPerformed(ActionEvent e) {
			
			
			if(e.getSource() == FishB){
				
				animal	= FishB.getActionCommand().toString();
				setVisible(false);
				
			}
			if(e.getSource() == jellyFishB){
				animal	= jellyFishB.getActionCommand().toString();
				setVisible(false);
			}
		
			size =  sizeS.getValue();
			horizontalSpeed = horizontalspeedS.getValue();
			verticalSpeed = verticalSpeedS.getValue();
			index = list.getSelectedIndex();
			
		
		}
		
		public Integer getSizeA(){
			return size;
		}
		
		public Integer getx(){
			return horizontalSpeed;
		}
		public Integer gety(){
			return verticalSpeed;
		}
		
		public Color getCol(){
			return listColorValues[index];
		}
		
		public String getanimalname(){
			return animal;
		}
		
	
		
}



