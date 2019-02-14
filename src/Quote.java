import javax.swing.JInternalFrame;
class Quote {	
	public JInternalFrame showGUI() {
		String[] labels  = {"quote Info ","Name","Phone","Address","Date","Invoice No.","Work","Subject","Tax","Total","Paid","Status"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "quote";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
