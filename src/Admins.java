import javax.swing.JInternalFrame;
class Admins {	
	public JInternalFrame showGUI() {
		String[] labels  = {"Registration Form","Username","Password","Conform Password"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "admins";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
