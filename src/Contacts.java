import javax.swing.JInternalFrame;
class Contacts {	
	public JInternalFrame showGUI() {
		String[] labels  = {"Contact Info ","First Name","Last Name","Address","Phone","Mobile","Email","WebSite","Comment","Date","Category"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "contacts";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
