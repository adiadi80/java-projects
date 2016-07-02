import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;


import javax.swing.JRadioButton;

public class ChooseDialog extends JDialog implements ActionListener {

		  
		int i=0;
		int Location = 50;
		JRadioButton[] optMonth = new JRadioButton[15];
		JButton bSubmit; 
		JButton bExit;
		HashSet<SeaCreature> seaL;
		
		SeaCreature sea;
		
    public ChooseDialog(HashSet<SeaCreature> seaL,int k) {
    	
    	this.seaL = new HashSet<SeaCreature>();
    	this.seaL = seaL;
    	
    	
    	Container contentPane = getContentPane();
    	contentPane.setLayout(new GridLayout(0, 1));
    	int i=0;
    	ButtonGroup bg=new ButtonGroup(); 
    	for(SeaCreature a: seaL){	
			 if(a instanceof Swimmable && k !=3 && k!=4){
				 
				 //"<html>Candidate 3:<font color=blue>R.I.P. McDaniels</font></html>"
				 
				 optMonth[i]=new JRadioButton(a.getOName() +",color:" + a.getColor()+ ",size:"+a.getSize() +
						 " y:"+a.getVerSpeed()+" x:"+a.getHorSpeed(),true);  
				 
				 
			     bg.add(optMonth[i]);
			     contentPane.add(optMonth[i]);
			     
			     i++;
			     Location+=50;
			 }else if(k ==3 || k ==4){
				 optMonth[i]=new JRadioButton(a.getOName() +",color:" + a.getColor()+ ",size:"+a.getSize() +
						 " y:"+a.getVerSpeed()+" x:"+a.getHorSpeed(),true);  
				 
				 
			     bg.add(optMonth[i]);
			     contentPane.add(optMonth[i]);
			     
			     i++;
			     Location+=50;
			 }
				 
		}
    	  if(k == 1){
    		  bSubmit=new JButton("Copy");  
    	  }else if(k==2){
    		 bSubmit=new JButton("Change Color");  
    	  }else if(k==3){
    		  bSubmit=new JButton("Save Object State");    
    	  }
    	  else if (k==4){
    		  bSubmit=new JButton("restore Object State");    
    	  }
    	  bExit=new JButton("Exit");
    	
    	
    	bSubmit.addActionListener(this);
    	bExit.addActionListener(this);  

    	contentPane.add(bSubmit);  
    	contentPane.add(bExit);
    	if(k == 1){
    		setTitle("Add a copy animal to aquarium");
    	}
    	else if(k==2){
    		setTitle("Change animal");
    	}else if (k==3){
    		setTitle("Save Object State");
    		
    	}else if (k==4){
    		setTitle("Restore Object State");
    	}
    	
    	setSize(270,300); 
    	setLocation(800, 5);
    	setDefaultCloseOperation(0);
        setModal(true);
    	
    	show();
    
    }  

    	public void actionPerformed(ActionEvent e){ 
    		
    		if(e.getSource() == bSubmit){
    			show(false);
    			
    			for (i = 0;i< seaL.size();i++){
    				if(optMonth[i].isSelected()){
        	    		break;
        	    	} 
    			}
    			
    	    	
    			int j=0;
    			
    			for(SeaCreature a: seaL){	
   	   			 if(a instanceof Swimmable){
   	   				 if(j == i){
   	   					 sea =  a;
   	   					
   	   					 
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