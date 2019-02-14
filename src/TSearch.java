//package invoice_pack;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.util.*; 
public class TSearch extends JFrame implements ActionListener {
	DBLib con;
	//Initializing Components
	JLabel[] jlabels;
	JTextField[] jtfields;
	JButton[] jbuttons;
	String tablename;
	JComboBox<Object> cb = new JComboBox<Object>();
	ArrayList<String> colname = new ArrayList<String>();
	ArrayList<Integer> coltype = new ArrayList<Integer>();
	//Creating Constructor for initializing JFrame components
	TSearch(String tablename,String... labels) {
		this.tablename = tablename;
	 	this.setLayout(null);
		con = new DBLib(); 	   
	     	con.getStatement("jdbc:mysql://localhost/invoice","root","thaneshd");    
     	   	////////////////////////////////////////select Data////////////////////////////
	   	try {
	      		ResultSet rs=con.selectData("select * from "+tablename+"");
       			//Vector vusr = new Vector();
	          	while(rs.next())
				cb.addItem(rs.getString(1)+" "+rs.getString(2));
		            	//or vusr.add(rs.getString(1)+"  "+rs.getString(2));
		        //final DefaultComboBoxModel cbmodel = new DefaultComboBoxModel(vusr);
		        //cb = new JComboBox(cbmodel);
			/////////////////////getColumnName////////
			ResultSetMetaData rsmd = rs.getMetaData();
			int ccount = rsmd.getColumnCount();
			for(int i = 1;i<=ccount;i++) {
				colname.add(rsmd.getColumnName(i));
				coltype.add(rsmd.getColumnType(i));
			}
        	}	
        	catch(Exception e){}
		        cb.setBounds(80, 20, 200, 20); 
		        cb.addActionListener(this);
			this.add(cb);
			int labelcount = 0;
			for(int i = 0;i<labels.length;i++) {
				if(labels[i]=="Conform Password")
					continue;
				labelcount++;
			}
			jlabels = new JLabel[labelcount+1];
			jtfields = new JTextField[labelcount];
			///////////////title Defination/////////////////////////////////////////////
		        jlabels[0] = new JLabel("Fetching "+tablename+" information from database");
		        jlabels[0].setBounds(25, 60, 450, 25);
		        jlabels[0].setForeground(Color.red);
		        jlabels[0].setFont(new Font("Serif", Font.BOLD, 20));
			this.add(jlabels[0]);
			/////////////////id defination//////////////////////////////////////////////
			jlabels[1] = new JLabel("ID");
			jlabels[1].setBounds(80,90,100,20);
			this.add(jlabels[1]);
			jtfields[0] = new JTextField();
			jtfields[0].setBounds(165,90,200,20);
			jtfields[0].setEditable(false);
			this.add(jtfields[0]);
			int yframe = 0;
			for(int i = 2;i<=labels.length;i++) {
				if(labels[i-1] == "Conform Password")
					continue;
				//jlabel defination	
				jlabels[i] = new JLabel(labels[i-1]);
				yframe = 90+(i-1)*25;
				jlabels[i].setBounds(80,yframe,100,20);
				//jtextfield defination
				jtfields[i-1] = new JTextField();
				jtfields[i-1].setBounds(165,90+(i-1)*25,200,20);
				this.add(jtfields[i-1]);			
				this.add(jlabels[i]);
			}
			//jbutton defination
			String[] btns = {"Back","Update","Close"};
			jbuttons = new JButton[btns.length];
			for(int i = 0;i< 3;i++){
				//jbuttons defination
				jbuttons[i] = new JButton(btns[i]);
				yframe = 90+(jlabels.length-1)*30;
				jbuttons[i].setBounds(80+i*95,yframe,90,25);
				jbuttons[i].addActionListener(this);
				this.add(jbuttons[i]);
			}
			this.setSize(500,yframe+80);
	  		this.setDefaultCloseOperation(1);
		        this.setVisible(true); 
	     }

	    public void actionPerformed(ActionEvent e) {
	    con = new DBLib();                                                      
	    con.getStatement("jdbc:mysql://localhost/invoice","root","thaneshd");   
	    /////////////////ComboBox clicked //////////////////////// 
	    if(e.getSource() == cb) {
            //Create DataBase Coonection and Fetching Records      
        	    String str = (String)cb.getSelectedItem();
		    String[] colval = str.split("(?<=\\D)(?<=\\d)|(?<=\\d)(?=\\D)",2);      
	            try {
			    ResultSet rs  = con.selectData("select * from "+tablename+" where "+colname.get(0)+" = "+colval[0].trim()+" && "+colname.get(1)+" ='"+colval[1].trim()+"'");
			    if (rs.next()) {
				    for(int i =0;i<jtfields.length;i++) {
					    jtfields[i].setText(rs.getString(i+1));
			 	    }	
		            } 
		     	    else {
				    JOptionPane.showMessageDialog(jtfields[0], "Name not Found");
               		    } 
		            
	            }
	            catch (Exception ex) {
	           	 System.out.println(ex);
	            }
	     }	
	     //////////////////////////////////////back statement/////////////////////
	     if(e.getActionCommand().equals("Back")) {
		     this.dispose();	   
	     }
	     ///////////////////////////////////////cancel statement///////////////
	     if(e.getActionCommand().equals("Update")) {
		     ArrayList<String> colval = new ArrayList<String>();
		     //System.out.println(jpasswords.length+"\n"+jtfields.length+"\n"+jlabels.length);
			int datacount = 0;
			do {
			        	if(!jtfields[datacount].getText().trim().isEmpty()) {
						datacount++;
					}
					else{
					       JOptionPane.showMessageDialog(jbuttons[0],jlabels[datacount+1].getText()+" Missing");
				               break;
					}
			} while((jtfields.length)!=datacount);
		        if((jtfields.length)==datacount) {
				for(int i = 0;i<(datacount);i++) {
					        //System.out.println(jpasswords[p2].getText());
						colval.add(jtfields[i].getText().trim());
				}
				////////////////////////SQL Operation////////////////
				//System.out.println(coltype);
				String sql = new String("update "+tablename+" set ");
				////////////////getColumnValue/////////////
				for(int i = 1;i<coltype.size();i++){
					if(coltype.get(i)==4)
						sql = sql+"`"+colname.get(i)+"` = "+colval.get(i);
					else
						sql = sql+"`"+colname.get(i)+"` = '"+colval.get(i)+"'";
					if(!(i+1 == coltype.size()))
						sql = sql+",";

				}
				sql = sql+" where `"+colname.get(0)+"` = "+colval.get(0);
				//System.out.println(sql);
				con.insUpdateData(sql);
				JOptionPane.showMessageDialog(jbuttons[0],"Updation Done");
			}   	     		     
	     }
             ///////////////////////////////////////Close statement///////////////
	     if(e.getActionCommand().equals("Close")) {
			   System.exit(0);
	     }

      }		
} 
