import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingConstants;

public class AddPlantlDialog extends JDialog implements ActionListener{

	
	
	int size = 1;
	String plant = null;
	
	

	//JButton
	public JButton zosteraB;
	public JButton laminariaB;
	JSlider sizeS = new JSlider(SwingConstants.HORIZONTAL, 50, 200, 50);
	private Label labels = new Label("size");
	
	AddPlantlDialog(){
		
		JPanel Panel = new JPanel();
		Panel.setLayout(new GridLayout(0, 1));
		
		//fish button
		laminariaB = new JButton("Laminaria"); 
		Panel.add(laminariaB);
		
		//gelly fish button
		zosteraB = new JButton("Zostera"); 
	    Panel.add(zosteraB); 
		
		
		
		 //size slider
		 		Panel.add(labels);
		 		sizeS.setMinorTickSpacing(2);
				sizeS.setMajorTickSpacing(30);
				sizeS.setPaintTicks(true);
				sizeS.setPaintLabels(true);
				Panel.add(sizeS);
		
				zosteraB.addActionListener(this);
				laminariaB.addActionListener(this);
				
				setDefaultCloseOperation(0);
		        setModal(true);
		        add(Panel, BorderLayout.CENTER);
		        
		        
		        setSize(200, 300);
		        setLocation(800, 5);
		        setTitle("Add a plant to aquarium");
		        setVisible(false);
				
	}
	 
	
	public String getName(){
		return plant;
	}
	
	
	public int getPSize(){
		return size;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == zosteraB){
			plant = zosteraB.getActionCommand().toString();
			setVisible(false);
		}
		if(e.getSource() == laminariaB){
			plant	= laminariaB.getActionCommand().toString();
			setVisible(false);
			
		}
		
		size =  sizeS.getValue();
		
	}

}
