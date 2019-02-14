import javax.swing.JInternalFrame;
class Vendors {	
	public JInternalFrame showGUI() {
		String[] labels  = {"Vendor Info ","Business","Phone","Hours","Address","Note"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "vendors";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
