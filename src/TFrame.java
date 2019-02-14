import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.sql.*;
class TFrame implements ActionListener {
		JLabel[] jlabels;
	       	JTextField[] jtfields;
		JButton[] jbuttons; 
		JPasswordField[] jpasswords;	
		String[] labels;
		String tablename; 	
		int passval = 0;
		int tfval = 0;
		DBLib dblib= new DBLib();
		public JInternalFrame showTFrame(String tablename,String[] btns,String... args) {
			labels = args;
			this.tablename = tablename;
			int yframe = 0;
			//A specialized layered pane to be used with JInternalFrames
			 JInternalFrame jiframe = new JInternalFrame("JInternalFrame",true,true,true,true);
			 jiframe.setLayout(null);
			 ////////////initialization////////
			 jlabels = new JLabel[args.length];
		     	 jtfields = new JTextField[args.length-1];
			 jpasswords = new JPasswordField[2];
			 //////tittel defination///////////
			 jlabels[0] = new JLabel(args[0]); 
		 	 jlabels[0].setFont(new Font("serif",1,21));	
			 jlabels[0].setBounds(100,5,300,25);
			 jiframe.add(jlabels[0]);
			 for(int i = 1;i< args.length;i++) {
				//jlabel defination	
				jlabels[i] = new JLabel(args[i]);
					yframe = 45+(i-1)*25;
					jlabels[i].setBounds(80,yframe,100,20);
					if(args[i]=="Password"||args[i]=="Conform Password") {                   
						//jpasswordfield defination		
						jpasswords[passval] = new JPasswordField();	
                                		jpasswords[passval].setBounds(165,45+(i-1)*25,200,20);
                                		jiframe.add(jpasswords[passval]);
						passval++;
					}
                               
					else {
						//jtextfield defination
						jtfields[tfval] = new JTextField();
						jtfields[tfval].setBounds(165,45+(i-1)*25,200,20);
						jiframe.add(jtfields[tfval]);
						tfval++;
					}
				jiframe.add(jlabels[i]);
			}
		        //jbutton initialization
			jbuttons = new JButton[btns.length];	
			for(int i = 0;i< btns.length;i++){
				//jbuttons defination
				jbuttons[i] = new JButton(btns[i]);
				if(i<3) {
					yframe = 45+(args.length-1)*30;
					jbuttons[i].setBounds(80+i*95,yframe,90,25);
				}
				else if(i<6) {	
					yframe = 45+(i/3+args.length-1)*30; 
					jbuttons[i].setBounds(80+(i-3)*95,yframe,90,25);
				}
				else {
					yframe = 45+(i/3+args.length-1)*30;
					jbuttons[i].setBounds(80+(i-6)*95,yframe,90,25);
				}
				jbuttons[i].addActionListener(this);
				jiframe.add(jbuttons[i]);
			}
			jiframe.setSize(470,yframe+80);
			jiframe.setVisible(true);
			return jiframe;
		}	
		static int openFrameCount = 1;
		static final int xPosition = 30, yPosition = 30;

	/////////////////////Action method//////////////
	public void actionPerformed(ActionEvent e) {
		ArrayList<Object> colval = new ArrayList<Object>();
	    	//////////////////////////////////////////insert statement////////////////////////////////////////////// 
	        if(e.getActionCommand().equals("Insert")) {
			//System.out.println(jpasswords.length+"\n"+jtfields.length+"\n"+jlabels.length);
			int datacount = 0;
			int p = 0;
			int q = 0;
			do {	
				if((jlabels[datacount+1].getText()=="Password")||(jlabels[datacount+1].getText()=="Conform Password")){	
				
					if(!jpasswords[p].getText().trim().isEmpty()) {
						if(p>0){
							if(!((jpasswords[p-1].getText()).equals(jpasswords[p].getText()))) {	
								JOptionPane.showMessageDialog(jpasswords[p],jlabels[datacount+1].getText()+" not matched");
								break;

							}
						}
						datacount++;
						p++;	
					} 
					else {	
					       JOptionPane.showMessageDialog(jbuttons[0],jlabels[datacount+1].getText()+" Missing");			     
					       break;
					}
				}
				else {
			        	if(!jtfields[q].getText().trim().isEmpty()) {
						datacount++;
						q++;
					}
					else{
					       JOptionPane.showMessageDialog(jbuttons[0],jlabels[datacount+1].getText()+" Missing");				
				               break;	       
   					      
					}
				}
			} while((tfval+passval)!=datacount);
			int p2 = 0;
			int q2 = 0;
		        if((tfval+passval)==datacount) {
				for(int i = 0;i<(tfval+passval);i++) {
				    	if(jlabels[i+1].getText()=="Conform Password")
				    		continue;	    
				    	else if(jlabels[i+1].getText()=="Password"){
					        //System.out.println(jpasswords[p2].getText());
						colval.add(jpasswords[p2].getText().trim());
						p2++;
						
					}
					else 	{
				
						 //System.out.println(jtfields[q2].getText());
					 	 colval.add(jtfields[q2].getText().trim());	 
				     		 q2++;
					}	 
				}
				////////////////////////SQL Operation////////////////
			
				dblib.getStatement("jdbc:mysql://localhost/invoice","root","thaneshd");	
				//////////////////getColumnName//////////////
				ArrayList<String> colname = new ArrayList<String>();
				ArrayList<Integer> coltype = new ArrayList<Integer>();
				try {
					ResultSet rs = dblib.selectData("select * from "+tablename);
					ResultSetMetaData rsmd = rs.getMetaData();
		                        int ccount = rsmd.getColumnCount();
					for(int i = 2;i<=ccount;i++) {
						colname.add(rsmd.getColumnName(i));
						coltype.add(rsmd.getColumnType(i));
					}
				}
				catch(SQLException se) {JOptionPane.showMessageDialog(jbuttons[0],se+" in getcolumn&type");}
				String colname2 = new String("insert into "+tablename+"("+colname+") values(").replace("[","").replace("]","");
				////////////////getColumnValue/////////////
				for(int i = 0;i<coltype.size();i++){
					if(coltype.get(i)==4)
						colname2 = colname2+colval.get(i);
					else
						colname2 = colname2+"'"+colval.get(i)+"'";
					if(!(i+1 == coltype.size()))
						colname2 = colname2+",";
				
				}
				colname2 = colname2+")";
				dblib.insUpdateData(colname2);
				JOptionPane.showMessageDialog(jbuttons[0],"Insertion Done");	
			}
			
		}
	    	///////////////////////////////////////update statement//////////////////////////////////////////////
	       	if(e.getActionCommand().equals("Show")) {
			new TSearch(tablename,labels);
	                // new UpdateAdmin().showGUI();
	        }        
	        ///////////////////////////////////////select statement//////////////////////////////////////////////
	        if(e.getActionCommand().equals("Search")) { 
		           new TSearch(tablename,labels);
	        }	
	    	///////////////////////////////////////cancel statement//////////////////////////////////////////////
	        if(e.getActionCommand().equals("Cancel")){
			   System.exit(0);
	         }
	 } 
}


