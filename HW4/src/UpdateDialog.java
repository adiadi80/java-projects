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

public class UpdateDialog extends JDialog implements ActionListener{
	
	//parameter for the animal
	int size;
	int horizontalSpeed;
	int verticalSpeed;
	int index;
	String animal = null;
	
	//JList for color
	JList<String> list;
	//color names
	String[] listColorNames = { "orange", "blue", "pink", "yellow","red" };
	//color parameters
	Color[] listColorValues = { Color.orange, Color.BLUE, Color.pink,Color.YELLOW,Color.RED };
	
	//JSlider
	JSlider horizontalspeedS;
	JSlider verticalSpeedS;
	JSlider sizeS ;
	
	//JButton
	public JButton updateB;
	public JButton exitB;
	
	
	 private static final int JDISPOSE_ON_CLOSE = 0;
	 private String name[] = {"Size: ", "Horizontal speed: ", "Vertical Speed: "};
	 private Label labels[] = new Label[3];
	 SeaCreature sea;
	    
	    /**
	     * constructor for the DIALOG
	     */
		public UpdateDialog(SeaCreature sea){
			
			
			
			
			
			this.sea = sea;
			
			
			
			
			
			horizontalspeedS = new JSlider(SwingConstants.HORIZONTAL, 1, 10, sea.getHorSpeed());
			verticalSpeedS = new JSlider(SwingConstants.HORIZONTAL, 1, 10, sea.getVerSpeed());
			sizeS = new JSlider(SwingConstants.HORIZONTAL, 20, 320, sea.getSize());
			
			list = new JList<String>(listColorNames);
		
			list.setSelectedIndex(0);
			
			
			list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			
			
			JPanel Panel = new JPanel();
			Panel.setLayout(new GridLayout(0, 1));
			
			
			//fish button
			updateB = new JButton("Update"); 
			Panel.add(updateB);
			
			//gelly fish button
			exitB = new JButton("Exit"); 
		    Panel.add(exitB); 
			
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
		        

		            updateB.addActionListener(this);
				    exitB.addActionListener(this);
			
				    
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
			index = list.getSelectedIndex();
			
			if(e.getSource() == updateB){
				
				sea.setColor(getCol());
				sea.setSize(sizeS.getValue());
				sea.setHorSpeed(horizontalspeedS.getValue());
				sea.setVerSpeed(verticalSpeedS.getValue());
				setVisible(false);
				
			}
			if(e.getSource() == exitB){
				
				setVisible(false);
			}
			size =  sizeS.getValue();
			horizontalSpeed = horizontalspeedS.getValue();
			verticalSpeed = verticalSpeedS.getValue();
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



