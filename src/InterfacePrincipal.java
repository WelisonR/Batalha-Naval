import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class InterfacePrincipal extends JFrame {
        
        public static final int FRAME_WIDTH = 900;
        public static final int FRAME_HEIGHT = 600;
        
        private int mouseReleasedBoardLocationX, mouseReleasedBoardLocationY;
        private int mousePressedBoardLocationX, mousePressedBoardLocationY;
        
        LeitorMapa mapInformations;
        PontuacaoJogo gameScore;
        AcoesJogador actions;
	CanvasJogo canvas;
	CanvasThread updateScreenThread;

	public InterfacePrincipal() {
                
                mapInformations = new LeitorMapa(Menu.mapPath);
                gameScore = new PontuacaoJogo();
                actions = new AcoesJogador(mapInformations);
                canvas = new CanvasJogo(actions);
                updateScreenThread = new CanvasThread(canvas);
                
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                setResizable(false);
		
		getContentPane().setLayout(new BorderLayout());
		setTitle("Batalha Naval");
		getContentPane().add("Center", canvas);
                
                Color blue = new Color(1, 50, 67);
                canvas.setBackground(blue);
		
		// Define largura e altura da janela principal
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
                                
                                mouseReleasedBoardLocationY = (x-CanvasJogo.MARGIN)/canvas.RECT_WIDTH;
                                mouseReleasedBoardLocationX = (y-CanvasJogo.MARGIN)/canvas.RECT_HEIGHT;
                                
                                
                                if(!actions.verifyWinner() && !actions.verifyLooser()){
                                        if (!(x > CanvasJogo.MARGIN && y > CanvasJogo.MARGIN) ||
                                            !(x < FRAME_WIDTH-CanvasJogo.MENU_WIDTH && y < FRAME_HEIGHT)){
                                                JOptionPane.showMessageDialog(null, "Jogada não aceita. Selecione uma área dentro do tabuleiro!");
                                        }
                                        else{
                                                int attackType = actions.verifyPlayerMovement(mousePressedBoardLocationX, mousePressedBoardLocationY,
                                                                                              mouseReleasedBoardLocationX, mouseReleasedBoardLocationY);
                                                if (attackType == -1){
                                                        JOptionPane.showMessageDialog(null, "Área de ataque não válida!");
                                                }
                                                else{
                                                        if(gameScore.RemainedScore(attackType)){
                                                                actions.setMatrixUserChoices(mousePressedBoardLocationX, mousePressedBoardLocationY,
                                                                                             mouseReleasedBoardLocationX, mouseReleasedBoardLocationY);
                                                        }
                                                            
                                                
                                                }
                                        }
                                }
                                
                                if(actions.verifyWinner()){
                                        JOptionPane.showMessageDialog(null, "Parabéns, você ganhou!");
                                }
                                else{
                                        if(actions.verifyLooser()){
                                                JOptionPane.showMessageDialog(null, "Sem mais recursos. Você perdeu!");
                                        }
                                }
			}

			@Override
			public void mouseClicked(MouseEvent e) {}

			@Override
			public void mousePressed(MouseEvent e) {
                                int x = e.getX();
                                int y = e.getY();
                                
                                mousePressedBoardLocationY = (x-CanvasJogo.MARGIN)/canvas.RECT_WIDTH;
                                mousePressedBoardLocationX = (y-CanvasJogo.MARGIN)/canvas.RECT_HEIGHT;
                                
                                if (!(x > CanvasJogo.MARGIN && y > CanvasJogo.MARGIN) ||
                                    !(x < FRAME_WIDTH-CanvasJogo.MENU_WIDTH && y < FRAME_HEIGHT)){
                                         JOptionPane.showMessageDialog(null, "Jogada não aceita. Selecione uma área dentro do tabuleiro!");
                                }
                                
			}

			@Override
			public void mouseEntered(MouseEvent e) {}

			@Override
			public void mouseExited(MouseEvent e) {}

		});
                
                this.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e){
                                updateScreenThread.setRunning(false);
                                Menu frame = Menu.getInstance();
                                frame.setVisible(true);
                        }
                
                });
	}
        
}