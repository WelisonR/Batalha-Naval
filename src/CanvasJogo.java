import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

import javax.swing.ImageIcon;

public class CanvasJogo extends Canvas {
	
	private int canvasNumberOfRows = 8;
	private int canvasNumberOfLines = 7;
        
        public final static int MENU_WIDTH = 250;
        public final int RECT_WIDTH = (InterfacePrincipal.FRAME_WIDTH - MENU_WIDTH) / canvasNumberOfRows;
	public final int RECT_HEIGHT = InterfacePrincipal.FRAME_HEIGHT / canvasNumberOfLines;
	public static final int MARGIN = 0;
	
	private int [][] explosionMatrix = new int[canvasNumberOfRows][canvasNumberOfLines];
        
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
                
		for(int i = 0; i < canvasNumberOfRows; i++) {
			for(int j = 0; j < canvasNumberOfLines; j++) {				
                                g.drawImage(imgOceanWave, i*RECT_WIDTH+MARGIN, j*RECT_HEIGHT+MARGIN, RECT_WIDTH, RECT_HEIGHT, null);
                                
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

	public int getCanvasNumberOfRows() {
		return canvasNumberOfRows;
	}

	public void setCanvasNumberOfRows(int canvasNumberOfRows) {
		this.canvasNumberOfRows = canvasNumberOfRows;
	}

	public int getCanvasNumberOfLines() {
		return canvasNumberOfLines;
	}

	public void setCanvasNumberOfLines(int canvasNumberOfLines) {
		this.canvasNumberOfLines = canvasNumberOfLines;
	}
	
}