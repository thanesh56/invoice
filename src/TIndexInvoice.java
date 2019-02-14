//package invoice_pack;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

 class TIndexInvoice extends JFrame  implements ActionListener {
	 TIndexInvoice() { showGUI(); }
   	 JMenu menus[];
   	 JMenuItem menuitm[];
	 JDesktopPane jdpan;
	 static int openFrameCount = 1; 
	 public void showGUI() {
	 this.setLayout(null);
         this.setDefaultCloseOperation(3);

	
	 // Make the main window positioned as 50 pixels from each edge of the
	 // screen.
	 int inset = 50;
	 Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	 setBounds(inset, inset, screenSize.width - inset * 2,
			screenSize.height - inset * 2);
         // Add a Window Exit Listener
	 addWindowListener(new WindowAdapter() {
		 public void windowClosig(WindowEvent e) {
		    	 System.exit(0);
		 }
	 });
	 // Create and Set up the GUI.
	 jdpan = new JDesktopPane();
	 //A specialized layered pane to be used with JInternalFrames 
		 JInternalFrame frame = new Users().showGUI(); 
		 frame.setVisible(true);
	         //frame.setBackground(Color.green);
		 jdpan.add(frame);  
        	 try {                           
        		frame.setSelected(true); 					
        	 } catch (java.beans.PropertyVetoException err) {               
		 } 	
		 setContentPane(jdpan);
	
		 // Make dragging faster by setting drag mode to Outline
		 jdpan.putClientProperty("JDesktopPane.dragMode", "outline");
  		 //////////////JMenu defination /////////////  
	  	 JMenuBar jb = new JMenuBar();
	  	 menuitm = new JMenuItem[8];
	  	 String mtm[] = new String[]{"Admins","Users","Clients","Contacts","Events","Vendors","Quote","Invoice"};
	  	 for(int i = 0;i<8;i++) {
	        	  menuitm[i] = new JMenuItem(mtm[i]);
	        	  menuitm[i].addActionListener(this); 
	  	 } 
	 
	  	 menus = new JMenu[6];
	  	 String mnu[] = new String[]{"File","Edit","View","Bookmarks","Setting","Help"};
	  	 for(int i = 0;i<6;i++) {
			   menus[i] = new JMenu(mnu[i]);
		  	   if(i==0){
			  	 for(int j = 0;j<8;j++)
	 				 menus[i].add(menuitm[j]);	   
		  	   } 
	          	  jb.add(menus[i]);
	  	 }	
	  	 menus[0].setMnemonic(KeyEvent.VK_F);
	         this.setJMenuBar(jb);
		 
	 	 this.setVisible(true); 
	}
		static final int xPosition = 30, yPosition = 30;

	        public void actionPerformed(ActionEvent actionEvent) {
	   ////////////////////////////////Admin///////////////////////////////////////////
	        if(actionEvent.getActionCommand().equals("Admins")) {
	        	
			JInternalFrame frame = new Admins().showGUI();                           	
			 frame.setVisible(true);                                                	
			// Set the window's location.                                           	
			frame.setLocation(xPosition * openFrameCount, yPosition                 	
					* openFrameCount);                                      	
			 openFrameCount++;                                                      	
			 jdpan.add(frame);                                                      	
		         try {                                                                  	
		        	frame.setSelected(true); 						
		         } catch (java.beans.PropertyVetoException err) {                       	
			 } 	                                                             	
						                         	
	    	}
		if(actionEvent.getActionCommand().equals("Users")) {
			JInternalFrame frame = new Users().showGUI();                           	
	                 frame.setVisible(true);
	                // Set the window's location.
	                frame.setLocation(xPosition * openFrameCount, yPosition
                		* openFrameCount);
	                 openFrameCount++;
	                 jdpan.add(frame);  
	                 try {                           
	                	frame.setSelected(true); 					
	                 } catch (java.beans.PropertyVetoException err) {               
	                 } 	
	        }
	        if(actionEvent.getActionCommand().equals("Clients")) {
			JInternalFrame frame = new Clients().showGUI();                           	
	                 frame.setVisible(true);
	                // Set the window's location.
	                frame.setLocation(xPosition * openFrameCount, yPosition
                		* openFrameCount);
	                 openFrameCount++;
	                 jdpan.add(frame);                                                              	       
	                 try {                                                                          	       
	                	frame.setSelected(true); 					       	       
	                 } catch (java.beans.PropertyVetoException err) {                               	       
	                 } 	                                                                     	       
	        }
	        if(actionEvent.getActionCommand().equals("Contacts")) {
			JInternalFrame frame = new Contacts().showGUI();                           			
	                 frame.setVisible(true);                                                        	
	                // Set the window's location.
	                frame.setLocation(xPosition * openFrameCount, yPosition
                		* openFrameCount);
	                 openFrameCount++;
	                 jdpan.add(frame);                                                              		
	                 try {                                                                          		
	                	frame.setSelected(true); 					       		
	                 } catch (java.beans.PropertyVetoException err) {                               		
	                 } 	                             			 
	        }
	        if (actionEvent.getActionCommand().equals("Vendors")) {
		
		
			JInternalFrame frame = new Vendors().showGUI();                           			
	                 frame.setVisible(true);
	                // Set the window's location.
	                frame.setLocation(xPosition * openFrameCount, yPosition
                		* openFrameCount);
	                 openFrameCount++;                                                              		
	                 jdpan.add(frame);                                                              		
	                 try {                                                                          		
	                	frame.setSelected(true); 					       		
	                 } catch (java.beans.PropertyVetoException err) {                               		
	                 } 
		}
	        if(actionEvent.getActionCommand().equals("Events")) {
	        
			JInternalFrame frame = new Events().showGUI();                           			
	                 frame.setVisible(true);                                                        		
	                // Set the window's location.
	                frame.setLocation(xPosition * openFrameCount, yPosition
                		* openFrameCount);
	                 openFrameCount++;                                                              		
	                 jdpan.add(frame);                                                              		
	                 try {                                                                          		
	                	frame.setSelected(true); 					       		
	                 } catch (java.beans.PropertyVetoException err) {                               		
	                 } 	  
		}
	        if(actionEvent.getActionCommand().equals("Invoice")) {
	        
			JInternalFrame frame = new Invoice().showGUI();                           			
	                 frame.setVisible(true);                                                        		
	                // Set the window's location.                                                   		
	                frame.setLocation(xPosition * openFrameCount, yPosition
                		* openFrameCount);
	                 openFrameCount++;
	                 jdpan.add(frame);  
	                 try {                                                                          		
	                	frame.setSelected(true); 					       		
	                 } catch (java.beans.PropertyVetoException err) {                               		
	                 } 	                                                                     		
	        }
	        if(actionEvent.getActionCommand().equals("Quote")) {
			JInternalFrame frame = new Quote().showGUI();                           	
	                 frame.setVisible(true);
	                // Set the window's location.
	                frame.setLocation(xPosition * openFrameCount, yPosition
                		* openFrameCount);
	                 openFrameCount++;
	                 jdpan.add(frame);  
	                 try {                           
	                	frame.setSelected(true); 					
	                 } catch (java.beans.PropertyVetoException err) {               
			 }
		}	
	  }
	  public static void main(String args[]) {
		new TIndexInvoice().showGUI();
	  }
	}
