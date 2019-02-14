import javax.swing.JInternalFrame;
class Clients {	
	public JInternalFrame showGUI() {
		String[] labels  = {"Client Info ","Item","Code","Value","Password","DateStart","DateEnd"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "clients";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
