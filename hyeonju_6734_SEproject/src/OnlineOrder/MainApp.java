package OnlineOrder;

import javax.swing.JFrame;

public class MainApp{
	Login loginView;
	
	public static void main(String[] args) {
		
		// main class 
		MainApp main = new MainApp();
		main.loginView = new Login(); 
		main.loginView.setMain(main); 
	}
	
	
	public void showFrameTest(){
		loginView.dispose(); // close log in screen
		Menu frame = new Menu(); // open menu
		frame.setVisible(true);
	}

}
