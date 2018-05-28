import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CanvasJogo extends Canvas {
	
        private LeitorMapa mapInformations;
	private int canvasNumberOfRows;
	private int canvasNumberOfLines;
        private Integer[][] GameMatrix;
        private Integer[] BoatsNumber;
        
        public final static int MENU_WIDTH = 250;
        public static final int MARGIN = 0;
        public final int RECT_WIDTH;
	public final int RECT_HEIGHT;
	
	private int [][] explosionMatrix;
        
        public CanvasJogo(String filePath){
                mapInformations = new LeitorMapa(filePath);
                
                canvasNumberOfRows = mapInformations.getCanvasNumberOfRows();
                canvasNumberOfLines = mapInformations.getCanvasNumberOfLines();
                GameMatrix = mapInformations.getGameMatrix();
                BoatsNumber = mapInformations.getBoatsNumber();
                
                RECT_WIDTH = (InterfacePrincipal.FRAME_WIDTH - MENU_WIDTH) / canvasNumberOfRows;
                RECT_HEIGHT = InterfacePrincipal.FRAME_HEIGHT / canvasNumberOfLines;
                explosionMatrix = new int[mapInformations.getCanvasNumberOfRows()][mapInformations.getCanvasNumberOfLines()];
        }
        
	@Override
	public void paint(Graphics g) {
                drawGrid(g);
                drawWaves(g);
	}
        
        public void drawWaves(Graphics g){
                // Prepare an ImageIcon
                ImageIcon oceanWave = new ImageIcon("images/waves.gif");
		ImageIcon iconShot = new ImageIcon("images/explosion.gif");
                
		// Prepare an Image object to be used by drawImage()
		final Image imgShot = iconShot.getImage();
                final Image imgOceanWave = oceanWave.getImage();
                
                int constantWidth = (int) (0.04 * RECT_WIDTH);
                int constantHeight = (int) (0.04 * RECT_HEIGHT);
                
		for(int i = 0; i < canvasNumberOfRows; i++) {
			for(int j = 0; j < canvasNumberOfLines; j++) {
                                g.drawImage(imgOceanWave, i*RECT_WIDTH+MARGIN+constantWidth, j*RECT_HEIGHT+MARGIN+constantHeight, 
                                            RECT_WIDTH- 2*constantWidth, RECT_HEIGHT- 2*constantHeight, null);
                                
				if(explosionMatrix[i][j] == 1) {
					g.drawImage(imgShot, i*RECT_WIDTH+MARGIN, j*RECT_HEIGHT+MARGIN, RECT_WIDTH, RECT_HEIGHT, null);
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
        
	public void setShot(int x, int y) {
		explosionMatrix[x][y] = 1;
	}
	
}