import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CanvasJogo extends Canvas {
	
        //private LeitorMapa mapInformations;
	private int canvasNumberOfRows;
	private int canvasNumberOfLines;
        private AcoesJogador actions;
        //private Integer[][] GameMatrix;
        //private Integer[] BoatsNumber;
        
        public final static int MENU_WIDTH = 250;
        public static final int MARGIN = 0; // 10 - good number
        public final int RECT_WIDTH;
	public final int RECT_HEIGHT;
	
	//private int [][] explosionMatrix;
        
        public CanvasJogo(AcoesJogador actions){
                this.actions = actions;
                //mapInformations = new LeitorMapa(filePath);
                
                
                canvasNumberOfRows = LeitorMapa.getCanvasNumberOfRows();
                canvasNumberOfLines = LeitorMapa.getCanvasNumberOfLines();
                
                //GameMatrix = mapInformations.getGameMatrix();
                //BoatsNumber = mapInformations.getBoatsNumber();
                
                RECT_WIDTH = (InterfacePrincipal.FRAME_WIDTH - MENU_WIDTH-MARGIN) / canvasNumberOfRows;
                RECT_HEIGHT = (InterfacePrincipal.FRAME_HEIGHT-MARGIN) / canvasNumberOfLines;
                //explosionMatrix = new int[mapInformations.getCanvasNumberOfRows()][mapInformations.getCanvasNumberOfLines()];
        }
        
	@Override
	public void paint(Graphics g) {
                drawGrid(g);
                drawBoard(g);
                drawFrameBoard(g);
	}
        
        public void drawBoard(Graphics g){
                // Prepare an ImageIcon
                ImageIcon oceanWave = new ImageIcon("images/waves.gif");
		ImageIcon iconShot = new ImageIcon("images/explosion.gif");
                ImageIcon watterShot = new ImageIcon("images/bubbles.gif");
                
		// Prepare an Image object to be used by drawImage()
		final Image imgShot = iconShot.getImage();
                final Image imgOceanWave = oceanWave.getImage();
                final Image imgWatterShot = watterShot.getImage();
                
                int constantWidth = (int) (0.04 * RECT_WIDTH);
                int constantHeight = (int) (0.04 * RECT_HEIGHT);
                
		for(int i = 0; i < canvasNumberOfLines; i++) {
			for(int j = 0; j < canvasNumberOfRows; j++) {
                                g.drawImage(imgOceanWave, j*RECT_WIDTH+MARGIN+constantWidth, i*RECT_HEIGHT+MARGIN+constantHeight, 
                                            RECT_WIDTH- 2*constantWidth, RECT_HEIGHT- 2*constantHeight, null);
                                
				if(actions.getMatrixUserChoices(i, j) == 1) {
					g.drawImage(imgShot, j*RECT_WIDTH+MARGIN, i*RECT_HEIGHT+MARGIN, RECT_WIDTH, RECT_HEIGHT, null);
				}
                                if(actions.getMatrixUserChoices(i, j) == -1){
                                        g.drawImage(imgWatterShot, j*RECT_WIDTH+MARGIN, i*RECT_HEIGHT+MARGIN, RECT_WIDTH, RECT_HEIGHT, null);
                                }
			}

		}
        }
        
        // Draw the grid area of the game board
        public void drawGrid(Graphics g){
                Graphics2D g2d = (Graphics2D) g;
                g2d.setStroke(new BasicStroke(3));
                g2d.setColor(Color.gray);
                
                for (int i = 0; i < canvasNumberOfLines + 1; i++){
                        g2d.drawLine(MARGIN, MARGIN+i*RECT_HEIGHT, MARGIN+RECT_WIDTH*canvasNumberOfRows, MARGIN+i*RECT_HEIGHT);
                }
                for (int i = 0; i < canvasNumberOfRows + 1; i++){
                        g2d.drawLine(MARGIN+i*RECT_WIDTH, MARGIN, MARGIN+i*RECT_WIDTH, MARGIN+RECT_HEIGHT*canvasNumberOfLines);
                }
        
        }
	
        
        public void drawFrameBoard(Graphics g){
              ImageIcon gameFrameBoard = new ImageIcon("images/gameFrame.png");
              final Image imgGameFrameBoard = gameFrameBoard.getImage();
              
              g.drawImage(imgGameFrameBoard, (InterfacePrincipal.FRAME_WIDTH - MENU_WIDTH), 0,
                          MENU_WIDTH, InterfacePrincipal.FRAME_HEIGHT, null);
              
              
        }
        
}