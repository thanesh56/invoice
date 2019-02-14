import javax.swing.JInternalFrame;
class Events {	
	public JInternalFrame showGUI() {
		String[] labels  = {"Event Info ","Event Day","Event Month","Event Year","Event Time","Title","Description"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "events";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
