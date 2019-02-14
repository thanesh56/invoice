import javax.swing.JInternalFrame;
class Users {	
	public JInternalFrame showGUI() {
		String[] labels  = {"Registration Form","Username","Password","Salt","Email"};
		String[] btns = {"Insert","Search","Cancel"};
		String tablename = "users";
		return new TFrame().showTFrame(tablename,btns,labels);
	}
}
