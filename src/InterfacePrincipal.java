import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class InterfacePrincipal extends JFrame {
        
        public static final int FRAME_WIDTH = 900;
        public static final int FRAME_HEIGHT = 600;
        
        LeitorMapa mapInformations;
        AcoesJogador actions;
	private CanvasJogo canvas;
	CanvasThread updateScreenThread;

	public InterfacePrincipal() {
                
                mapInformations = new LeitorMapa(Menu.mapPath);
                actions = new AcoesJogador(mapInformations);
                canvas = new CanvasJogo(actions);
                updateScreenThread = new CanvasThread(canvas);
                
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		        int x=e.getX();
		        int y=e.getY();
				
		        int y_pos = (x-CanvasJogo.MARGIN)/canvas.RECT_WIDTH;
		        int x_pos = (y-CanvasJogo.MARGIN)/canvas.RECT_HEIGHT;

                        if ((x > CanvasJogo.MARGIN && y > CanvasJogo.MARGIN) &&
                             (x < FRAME_WIDTH-CanvasJogo.MENU_WIDTH && y < FRAME_HEIGHT)){
                                actions.setMatrixUserChoices(x_pos, y_pos);
                        }
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
			}


		});
	}
        

}