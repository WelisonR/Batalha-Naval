import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CanvasGame extends Canvas {
	
	private int canvasNumberOfColumns;
	private int canvasNumberOfLines;
        private PlayerActionsAnalyser actions;
        
        // game width and margin menu
        public static final int MENU_WIDTH = 250;
        public static final int MARGIN = 0; // 10 - good number
        // width and height of the rectangular gifs
        public final int RECT_WIDTH;
	public final int RECT_HEIGHT;
        
        public CanvasGame(PlayerActionsAnalyser actions){
                this.actions = actions;
                canvasNumberOfColumns = MapReader.getCanvasNumberOfColumns();
                canvasNumberOfLines = MapReader.getCanvasNumberOfLines();
                RECT_WIDTH = (MainGameFrame.FRAME_WIDTH - MENU_WIDTH-MARGIN) / canvasNumberOfColumns;
                RECT_HEIGHT = (MainGameFrame.FRAME_HEIGHT-MARGIN) / canvasNumberOfLines;
        }
        
	@Override
	public void paint(Graphics g) {
                drawGrid(g);
                drawBoard(g);
                drawFrameBoard(g);
                drawPlayerName(g);
                drawPlayerPoints(g);
                drawAttacksScore(g);
                drawBoats(g);
                drawNumberOfBoats(g);
	}
        
        public void drawBoard(Graphics g){
                // Prepare an ImageIcon
                ImageIcon oceanWave = new ImageIcon(getClass().getClassLoader().getResource(FilePaths.WAVEANIMATIONPATH));
		ImageIcon iconShot = new ImageIcon(getClass().getClassLoader().getResource(FilePaths.EXPLOSIONANIMATIONPATH));
                ImageIcon watterShot = new ImageIcon(getClass().getClassLoader().getResource(FilePaths.BUBBLESANIMATIONPATH));
                
		// Prepare an Image object to be used by drawImage()
		final Image imgShot = iconShot.getImage();
                final Image imgOceanWave = oceanWave.getImage();
                final Image imgWatterShot = watterShot.getImage();
                
                // a small space between gifs to make the grid area
                int constantWidth = (int) (0.04 * RECT_WIDTH);
                int constantHeight = (int) (0.04 * RECT_HEIGHT);
                
		for(int i = 0; i < canvasNumberOfLines; i++) {
			for(int j = 0; j < canvasNumberOfColumns; j++) {
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
                        g2d.drawLine(MARGIN, MARGIN+i*RECT_HEIGHT, MARGIN+RECT_WIDTH*canvasNumberOfColumns, MARGIN+i*RECT_HEIGHT);
                }
                
                for (int i = 0; i < canvasNumberOfColumns + 1; i++){
                        g2d.drawLine(MARGIN+i*RECT_WIDTH, MARGIN, MARGIN+i*RECT_WIDTH, MARGIN+RECT_HEIGHT*canvasNumberOfLines);
                }
        
        }
	
        // draw the menu game image
        public void drawFrameBoard(Graphics g){
                ImageIcon gameFrameBoard = new ImageIcon(getClass().getClassLoader().getResource(FilePaths.MENUGAMEFRAMEPATH));
                final Image imgGameFrameBoard = gameFrameBoard.getImage();
              
                g.drawImage(imgGameFrameBoard, (MainGameFrame.FRAME_WIDTH - MENU_WIDTH), 0,
                          MENU_WIDTH, MainGameFrame.FRAME_HEIGHT, null);
        }

        public void drawPlayerName(Graphics g){
                int xPosition = (int) (MainGameFrame.FRAME_WIDTH - 0.7*MENU_WIDTH);
                int yPosition = (int) (0.1 * MainGameFrame.FRAME_HEIGHT);
                
                g.setFont(new Font("Bitstream Charter", 1, 22));
                g.setColor(new Color(240,52,52));
                
                g.drawString(MainMenuFrame.playerName.toUpperCase(), xPosition, yPosition);
        }
        
        public void drawPlayerPoints(Graphics g){
                // fill the area with background collor to update the informations
                int x = (int) (MainGameFrame.FRAME_WIDTH - 0.80*MENU_WIDTH);
                int y = (int) (0.15 * MainGameFrame.FRAME_HEIGHT);
                int rectWidth = (int) (0.66*MENU_WIDTH);
                int rectHeight = (int) (0.12*MainGameFrame.FRAME_HEIGHT);
                
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                
                // draw the actual player points
                int xPosition = (int) (MainGameFrame.FRAME_WIDTH - 0.75*MENU_WIDTH);
                int yPosition = (int) (0.25 * MainGameFrame.FRAME_HEIGHT);
                
                g.setFont(new Font("Bitstream Charter", 1, 48));
                g.setColor(new Color(0, 0, 0));
                
                g.drawString(String.valueOf(GameScores.ACTUALSCORE) + " $", xPosition, yPosition);
        }
        
        public void drawAttacksScore(Graphics g){
                // fill the area with background collor to update the informations
                int x = (int) (MainGameFrame.FRAME_WIDTH - 0.85*MENU_WIDTH);
                int y = (int) (0.33 * MainGameFrame.FRAME_HEIGHT);
                int rectWidth = (int) (0.63*MENU_WIDTH);
                int rectHeight = (int) (0.55*MainGameFrame.FRAME_HEIGHT);
                
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                
                // fill on the menu game the necessary scores to make an attack
                int xPosition = (int) (MainGameFrame.FRAME_WIDTH - 0.84*MENU_WIDTH);
                int yPosition = (int) (0.35 * MainGameFrame.FRAME_HEIGHT);
                
                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Bitstream Charter", 4, 18));
                
                g.drawString("POSIÇÃO:     " + String.valueOf(GameScores.POSITIONATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.39 * MainGameFrame.FRAME_HEIGHT);
                g.drawString("ÁREA:         " + String.valueOf(GameScores.AREAATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.43 * MainGameFrame.FRAME_HEIGHT);
                g.drawString("LINHA:        " + String.valueOf(GameScores.LINEATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.47 * MainGameFrame.FRAME_HEIGHT);
                g.drawString("COLUNA:     " + String.valueOf(GameScores.COLUMNATTACKSCORE) + " $", xPosition, yPosition);
        }
        
        // draw the boats according to its length (1-5)
        public void drawBoats(Graphics g){
                // fill the area with background collor to update the informations
                int x = (int) (MainGameFrame.FRAME_WIDTH - 0.85*MENU_WIDTH);
                int y =  (int) (0.93 * MainGameFrame.FRAME_HEIGHT);
                int rectWidth = (int) (0.50*MENU_WIDTH);
                int rectHeight = (int) (0.02*MainGameFrame.FRAME_HEIGHT);
                
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                
                int xPosition = (int) (MainGameFrame.FRAME_WIDTH - 0.85*MENU_WIDTH);
                int yPosition = (int) (0.50 * MainGameFrame.FRAME_HEIGHT);
                String boatPath = FilePaths.BOATSPATH;
                
                for (int i = 1; i <= 5; i++){
                        String boatFinalPath = boatPath + String.valueOf(i) +".png";

                        ImageIcon boat = new ImageIcon(getClass().getClassLoader().getResource(boatFinalPath));
                        final Image imgBoat = boat.getImage();
                        
                        g.drawImage(imgBoat, xPosition, yPosition, (int) (MENU_WIDTH * (0.3 + 0.05 * i)), (int) (0.07*MainGameFrame.FRAME_HEIGHT), null);
                        
                        yPosition +=  (int) (0.09 * MainGameFrame.FRAME_HEIGHT);
                }
                
        }
        
        // fill 'n' rectangles under the respective boats in accordance to its remaning quantity on board
        public void drawNumberOfBoats(Graphics g){
                int yPosition = (int) (0.575 * MainGameFrame.FRAME_HEIGHT);
                int BoatsLifeBar = (int) (0.5 * MENU_WIDTH);
                
                for (int i = 0; i < 5; i++){
                        int lifeBarWidth = 0;
                        int xPosition = (int) (MainGameFrame.FRAME_WIDTH - 0.85*MENU_WIDTH);
                        
                        if (actions.getBoatsNumber(i) != 0){
                                lifeBarWidth = (int) (BoatsLifeBar / actions.getBoatsNumber(i));
                        }
                        
                        for (int j = 0; j < actions.getBoatsNumber(i); j++){     
                                g.setColor(new Color(240,52,52));
                                g.fillRect(xPosition, yPosition, (int) (0.9 * lifeBarWidth), (int) (MainGameFrame.FRAME_HEIGHT * 0.01));
                                xPosition += lifeBarWidth;            
                        }
                        
                        yPosition += (int) (0.09 * MainGameFrame.FRAME_HEIGHT);
                }
                
        }
         
}