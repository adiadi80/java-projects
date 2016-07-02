import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JRadioButton;


public class JPanelDecorator extends JDialog implements ActionListener  {
	int Location = 50;
	JRadioButton[] optMonth = new JRadioButton[5];
	JButton changeColorB; 
	JButton bExit;
	HashSet<SeaCreature> seaL;
	
	Color color;
	
	SeaCreature sea;
	
public JPanelDecorator(HashSet<SeaCreature> seaL) {
	
	this.seaL = new HashSet<SeaCreature>();
	this.seaL = seaL;
	
	
	Container contentPane = getContentPane();
	contentPane.setLayout(new GridLayout(0, 1));
	int i=0;
	ButtonGroup bg=new ButtonGroup(); 
	for(SeaCreature a: seaL){	
		 if(a instanceof Swimmable){
			 
			 
			 optMonth[i]=new JRadioButton(a.getOName() +",color:" + a.getColor()+ ",size:"+a.getSize() +
					 " y:"+a.getVerSpeed()+" x:"+a.getHorSpeed(),true);  
			 
			 
		     bg.add(optMonth[i]);
		     contentPane.add(optMonth[i]);
		     
		     i++;
		     Location+=50;
		 }
			 
	}
	  
	changeColorB=new JButton("Change Color");  
	bExit=new JButton("Exit");
	
	
	changeColorB.addActionListener(this);
	bExit.addActionListener(this);  

	contentPane.add(changeColorB);  
	contentPane.add(bExit);
	
	setTitle("Change animal");
	setSize(270,300); 
	setLocation(800, 5);
	setDefaultCloseOperation(0);
    setModal(true);
	
	show();

}  

	public void actionPerformed(ActionEvent e){ 
		int i=0;
		if(e.getSource() == changeColorB){
			show(false);
			if(optMonth[0].isSelected()){  
	    		i = 0;
	    	} 
	    	else if(optMonth[1].isSelected()){  
	    		i=1;
	    	}  
	    	else if(optMonth[2].isSelected()){  
	    		i=2;
	    	}  
	    	else if(optMonth[3].isSelected()){  
	    		i=3;
	    	} 
			int j=0;
			
			
			
			for(SeaCreature a: seaL){	
	   			 if(a instanceof Swimmable){
	   				 if(j == i){
	   					 color = JColorChooser.showDialog(null,"Pick your color", color);
	   					 if(color !=null)
	   						 a.setColor(color);

	   				 }
	   			j++;	
	   			}
	   			
	   		}
			
			
		}
    	
    	else{
    		show(false);	
    	}
    	
    	
    	
    	
    	  
	 
}	 
	
	public SeaCreature getChoose(){
		return sea;
	}
}
