import javax.swing.JInternalFrame;
class Invoice {	
	public JInternalFrame showGUI() {
		String[] labels  = {"Invoice Info ","Name","Phone","Address","Date","Invoice No.","Work","Subject","Tax","Total","Paid","Status"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "invoice";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
