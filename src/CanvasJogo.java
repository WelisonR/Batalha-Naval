import java.awt.BasicStroke;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
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
        
        public static final int MENU_WIDTH = 250;
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
                drawPlayerName(g);
                drawPlayerPoints(g);
                drawAttacksScore(g);
                drawBoats(g);
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
              ImageIcon gameFrameBoard = new ImageIcon("images/menuFrame.png");
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
                //Color blue = new Color(255, 255, 255);
                int x = (int) (InterfacePrincipal.FRAME_WIDTH - 0.80*MENU_WIDTH);
                int y = (int) (0.15 * InterfacePrincipal.FRAME_HEIGHT);
                int rectWidth = (int) (0.66*MENU_WIDTH);
                int rectHeight = (int) (0.12*InterfacePrincipal.FRAME_HEIGHT);
                
                //Color blue = new Color(1, 50, 67);
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                //g.fillRect((int) (InterfacePrincipal.FRAME_WIDTH - 0.85*MENU_WIDTH), y, WIDTH, HEIGHT);
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
                
                g.setColor(new Color(1, 50, 67));
                g.fillRect(x, y, rectWidth, rectHeight);
                
                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("Bitstream Charter", 4, 18));
                
                int xPosition = (int) (InterfacePrincipal.FRAME_WIDTH - 0.84*MENU_WIDTH);
                int yPosition = (int) (0.37 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("POSIÇÃO:     " + String.valueOf(PontuacaoJogo.POSITIONATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.41 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("ÁREA:         " + String.valueOf(PontuacaoJogo.AREAATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.45 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("LINHA:        " + String.valueOf(PontuacaoJogo.LINEATTACKSCORE) + " $", xPosition, yPosition);
                
                yPosition = (int) (0.49 * InterfacePrincipal.FRAME_HEIGHT);
                g.drawString("COLUNA:     " + String.valueOf(PontuacaoJogo.COLUMNATTACKSCORE) + " $", xPosition, yPosition);
                //g.setColor(new Color(0, 0, 0));
                //g.setFont(new Font("Bitstream Charter", 4, 48));
        }
        
        public void drawBoats(Graphics g){
                int xPosition = (int) (InterfacePrincipal.FRAME_WIDTH - 0.85*MENU_WIDTH);
                int yPosition = (int) (0.50 * InterfacePrincipal.FRAME_HEIGHT);
    
                
                String boatPath = "images/boat";
                
                for (int i = 1; i <= 5; i++){
                        String boatFinalPath = boatPath + String.valueOf(i) +".png";

                        ImageIcon boat = new ImageIcon(boatFinalPath);
                        final Image imgBoat = boat.getImage();
                        
                        g.drawImage(imgBoat, xPosition, yPosition, (int) (MENU_WIDTH * (0.3 + 0.05 * i)),
                                    (int) (0.07*InterfacePrincipal.FRAME_HEIGHT), null);
                        
                        //g.setColor(new Color(255, 255, 255));
                        //g.fillRect(xPosition, yPosition, aux1, aux2);
                        
                        
                        //System.out.println(boatFinalPath);
                        //System.out.println("xPos: " + xPosition + "yPos: " + yPosition + "aux1: " + aux1 + "aux2" + aux2);
                        yPosition = yPosition +  (int) (0.09 * InterfacePrincipal.FRAME_HEIGHT);
                }
        }
        
}