
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

// class responsible to instantiate and process the game components
public class MainGameFrame extends JFrame {
        // if  your computer doesn't suport this resolution, change the variables values here
        public static final int FRAME_WIDTH = 900;
        public static final int FRAME_HEIGHT = 600;
        
        private int mouseReleasedBoardLocationX, mouseReleasedBoardLocationY;
        private int mousePressedBoardLocationX, mousePressedBoardLocationY;
        
        MapReader mapInformations;
        GameScores gameScore;
        PlayerActionsAnalyser actions;
	CanvasGame canvas;
	CanvasThread updateScreenThread;

	public MainGameFrame() {
                // instantiate the necessary game classes
                mapInformations = new MapReader(MainMenuFrame.mapPath);
                gameScore = new GameScores();
                actions = new PlayerActionsAnalyser(mapInformations);
                canvas = new CanvasGame(actions);
                updateScreenThread = new CanvasThread(canvas);
                
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		setTitle("Batalha Naval");
		getContentPane().add("Center", canvas);
                
                canvas.setBackground(new Color(1, 50, 67));
		
		// Define the game width and height
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
                setLocationRelativeTo(null);
		setVisible(true);
		
		// Thread with timer to repaint the screen
		updateScreenThread.start();
		
		canvas.addMouseListener(new MouseListener() {
			@Override
			public void mouseReleased(MouseEvent e) {
                                int x = e.getX();
                                int y = e.getY();
                                
                                mouseReleasedBoardLocationY = (int) ((x-CanvasGame.MARGIN)/canvas.RECT_WIDTH);
                                mouseReleasedBoardLocationX = (int) ((y-CanvasGame.MARGIN)/canvas.RECT_HEIGHT);
                                
                                // verify if the game ended
                                if(!actions.verifyWinner() && !actions.verifyLooser()){
                                        // verify if the player clicked outside the game board
                                        if (!(x > CanvasGame.MARGIN && y > CanvasGame.MARGIN) ||
                                            !(x < FRAME_WIDTH-CanvasGame.MENU_WIDTH && y < FRAME_HEIGHT)){
                                                JOptionPane.showMessageDialog(null, "Jogada não aceita. Selecione uma área dentro do tabuleiro!");
                                        }
                                        else{
                                                int attackType = -1;
                                                
                                                try{
                                                attackType = actions.verifyPlayerMovement(mousePressedBoardLocationX, mousePressedBoardLocationY,
                                                                                          mouseReleasedBoardLocationX, mouseReleasedBoardLocationY);
                                                }
                                                catch(Exception e2){
                                                        JOptionPane.showMessageDialog(null, "Jogada não aceita. Selecione uma área dentro do tabuleiro!");
                                                }
                                                
                                                // verify the player choice
                                                if (attackType == -1){
                                                        JOptionPane.showMessageDialog(null, "Área de ataque não válida!");
                                                }
                                                else{
                                                        // check the actual score and subtract if it is possible
                                                        if(gameScore.RemainedScore(attackType)){
                                                                // set the chosen area
                                                                actions.setMatrixUserChoices(mousePressedBoardLocationX, mousePressedBoardLocationY,
                                                                                             mouseReleasedBoardLocationX, mouseReleasedBoardLocationY);
                                                        }
                                                        else{
                                                                JOptionPane.showMessageDialog(null, "Não há recurso disponível para utilizar essa habilidade!");
                                                        }
                                                }
                                        }
                                        
                                }
                                
                                // determines the end of the game
                                if(actions.verifyWinner()){
                                        JOptionPane.showMessageDialog(null, "Parabéns, você ganhou!\nFeche a janela para voltar ao menu!");
                                }
                                else{
                                        if(actions.verifyLooser()){
                                                JOptionPane.showMessageDialog(null, "Sem mais recursos. Você perdeu!\nFeche a janela para voltar ao menu!");
                                        }
                                }
                                
			}

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
                                int x = e.getX();
                                int y = e.getY();
                                
                                mousePressedBoardLocationY = (x-CanvasGame.MARGIN)/canvas.RECT_WIDTH;
                                mousePressedBoardLocationX = (y-CanvasGame.MARGIN)/canvas.RECT_HEIGHT;
                                
                                // process the and verify the mouse pressed action
                                if (!(x > CanvasGame.MARGIN && y > CanvasGame.MARGIN) ||
                                    !(x < FRAME_WIDTH-CanvasGame.MENU_WIDTH && y < FRAME_HEIGHT)){
                                         JOptionPane.showMessageDialog(null, "Jogada não aceita. Selecione uma área dentro do tabuleiro!");
                                }
                                
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}
		});
                
                // kill the actual frame and set visible the main menu when the game canvas is closing
                this.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e){
                                updateScreenThread.setRunning(false);
                                MainMenuFrame frame = MainMenuFrame.getInstance();
                                dispose();
                                frame.setVisible(true);
                        }
                
                });
                
	}
        
}