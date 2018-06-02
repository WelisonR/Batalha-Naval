
import java.awt.EventQueue;

public class main {
               public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
                        @Override
			public void run() {
				try {
                                        // Show the main menu frame
                                        MainMenuFrame frame = MainMenuFrame.getInstance();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	} 
}
