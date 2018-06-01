import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;

public class CanvasJogo extends Canvas {
	
	private int canvasNumberOfRows;
	private int canvasNumberOfLines;
        private AcoesJogador actions;
        
        public static final int MENU_WIDTH = 250;
        public static final int MARGIN = 0; // 10 - good number
        public final int RECT_WIDTH;
	public final int RECT_HEIGHT;
        
        public CanvasJogo(AcoesJogador actions){
                this.actions = actions;
                canvasNumberOfRows = LeitorMapa.getCanvasNumberOfRows();
                canvasNumberOfLines = LeitorMapa.getCanvasNumberOfLines();
                RECT_WIDTH = (InterfacePrincipal.FRAME_WIDTH - MENU_WIDTH-MARGIN) / canvasNumberOfRows;
                RECT_HEIGHT = (InterfacePrincipal.FRAME_HEIGHT-MARGIN) / canvasNumberOfLines;
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
                ImageIcon oceanWave = new ImageIcon(filePaths.WAVEANIMATIONPATH);
		ImageIcon iconShot = new ImageIcon(filePaths.EXPLOSIONANIMATIONPATH);
                ImageIcon watterShot = new ImageIcon(filePaths.BUBBLESANIMATIONPATH);
                
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
                ImageIcon gameFrameBoard = new ImageIcon(filePaths.MENUGAMEFRAMEPATH);
                final Image imgGameFrameBoard = gameFrameBoard.getImage();
              
                g.drawImage(imgGameFrameBoard, (InterfacePrincipal.FRAME_WIDTH - MENU_WIDTH), 0,
                          MENU_WIDTH, InterfacePrincipal.FRAME_HEIGHT, null);
              
              
        }
        
        public void drawPlayerName(Graphics g){
                g.setFont(new Font("Bitstream Charter", 1, 24)); //  40, 20, 45
                g.setColor(new Color(240,52,52)); // ebonyClay
                
                int xPosition = (int) (InterfacePrincipal.FRAME_WIDTH - 0.7*MENU_WIDTH);
                int yPosition = (int) (0.1 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString(Menu.playerName.toUpperCase(), xPosition, yPosition);
        }
        
        public void drawPlayerPoints(Graphics g){
                int x = (int) (InterfacePrincipal.FRAME_WIDTH - 0.80*MENU_WIDTH);
                int y = (int) (0.15 * InterfacePrincipal.FRAME_HEIGHT);
                int rectWidth = (int) (0.66*MENU_WIDTH);
                int rectHeight = (int) (0.12*InterfacePrincipal.FRAME_HEIGHT);
                
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                g.setFont(new Font("Bitstream Charter", 1, 48));
                g.setColor(new Color(0, 0, 0)); // Madison
                
                int xPosition = (int) (InterfacePrincipal.FRAME_WIDTH - 0.75*MENU_WIDTH);
                int yPosition = (int) (0.25 * InterfacePrincipal.FRAME_HEIGHT);
                
                g.drawString(String.valueOf(PontuacaoJogo.ACTUALSCORE) + " $", xPosition, yPosition);
        }
        
        public void drawAttacksScore(Graphics g){
                int x = (int) (InterfacePrincipal.FRAME_WIDTH - 0.85*MENU_WIDTH);
                int y = (int) (0.33 * InterfacePrincipal.FRAME_HEIGHT);
                int rectWidth = (int) (0.63*MENU_WIDTH);
                int rectHeight = (int) (0.55*InterfacePrincipal.FRAME_HEIGHT);
                
                // x = (int) (InterfacePrincipal.FRAME_WIDTH - 0.85*MENU_WIDTH);
                // 
                
                //g.setColor(new Color(1, 50, 67));
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                
                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Bitstream Charter", 4, 18));
                
                int xPosition = (int) (InterfacePrincipal.FRAME_WIDTH - 0.84*MENU_WIDTH);
                int yPosition = (int) (0.35 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("POSIÇÃO:     " + String.valueOf(PontuacaoJogo.POSITIONATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.39 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("ÁREA:         " + String.valueOf(PontuacaoJogo.AREAATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.43 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("LINHA:        " + String.valueOf(PontuacaoJogo.LINEATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.47 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("COLUNA:     " + String.valueOf(PontuacaoJogo.COLUMNATTACKSCORE) + " $", xPosition, yPosition);
        }
        
        public void drawBoats(Graphics g){
                
                int x = (int) (InterfacePrincipal.FRAME_WIDTH - 0.85*MENU_WIDTH);
                int y =  (int) (0.93 * InterfacePrincipal.FRAME_HEIGHT);
                int rectWidth = (int) (0.50*MENU_WIDTH);
                int rectHeight = (int) (0.02*InterfacePrincipal.FRAME_HEIGHT);
                
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                
                int xPosition = (int) (InterfacePrincipal.FRAME_WIDTH - 0.85*MENU_WIDTH);
                int yPosition = (int) (0.50 * InterfacePrincipal.FRAME_HEIGHT);
                String boatPath = filePaths.BOATSPATH;
                
                for (int i = 1; i <= 5; i++){
                        String boatFinalPath = boatPath + String.valueOf(i) +".png";

                        ImageIcon boat = new ImageIcon(boatFinalPath);
                        final Image imgBoat = boat.getImage();
                        
                        g.drawImage(imgBoat, xPosition, yPosition, (int) (MENU_WIDTH * (0.3 + 0.05 * i)),
                                    (int) (0.07*InterfacePrincipal.FRAME_HEIGHT), null);
                        yPosition +=  (int) (0.09 * InterfacePrincipal.FRAME_HEIGHT);
                }
        }
        
        public void drawNumberOfBoats(Graphics g){
                int yPosition = (int) (0.575 * InterfacePrincipal.FRAME_HEIGHT);
                int BoatsLifeBar = (int) (0.5 * MENU_WIDTH);
                
                for (int i = 0; i < 5; i++){
                        int lifeBarWidth = 0;
                        int xPosition = (int) (InterfacePrincipal.FRAME_WIDTH - 0.85*MENU_WIDTH);
                        
                        if (actions.getBoatsNumber(i) != 0){
                                lifeBarWidth = (int) (BoatsLifeBar / actions.getBoatsNumber(i));
                        }
                        
                        for (int j = 0; j < actions.getBoatsNumber(i); j++){     
                                g.setColor(new Color(240,52,52));
                                g.fillRect(xPosition, yPosition, (int) (0.9 * lifeBarWidth), (int) (InterfacePrincipal.FRAME_HEIGHT * 0.01));
                                xPosition += lifeBarWidth;            
                        }
                        
                        yPosition += (int) (0.09 * InterfacePrincipal.FRAME_HEIGHT);
                }
                
        }
         
}