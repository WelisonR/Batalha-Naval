
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

// class responsible to read and store the game maps informations
public class MapReader {
        public static final int NUMBEROFBOATS = 5;
        
        private List<String> fileLines = new ArrayList<>();
        private Integer[][] gameMatrix;
        private Integer[] boatsNumber;
        private static int canvasNumberOfColumns;
	private static int canvasNumberOfLines;
        
        public MapReader(String filePath){
                fileCopy(filePath);
                readMatrixDimensions();
                readGameMatrix();
                readNumberOfBoats();
        }

        public void fileCopy(String filePath){
                Scanner file = null;
                
                try{
                        file = new Scanner(new File(filePath));
                        
                        while (file.hasNextLine()){
                                fileLines.add(file.nextLine());
                        }
                }
                catch(FileNotFoundException e){
                        JOptionPane.showMessageDialog(null, "Não foi possível abrir o arquivo com o mapa do jogo!");
                }
                finally{
                        try{
                                file.close();
                        }
                        catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Não foi possível fechar o arquivo do mapa do jogo");
                        }
                }
                
        }
        
        public void readMatrixDimensions(){
                String [] lengthMatrix = fileLines.get(1).split(" ");
                
                canvasNumberOfColumns = Integer.parseInt(lengthMatrix[0]);
                canvasNumberOfLines = Integer.parseInt(lengthMatrix[1]);
        }
        
        public void readGameMatrix(){
                gameMatrix = new Integer[canvasNumberOfLines][canvasNumberOfColumns];
                
                for (int i = 0; i < canvasNumberOfLines; i++){
                        for (int j = 0; j < canvasNumberOfColumns; j++){
                                String line = fileLines.get(i+4);
                                gameMatrix[i][j] = (int) (line.charAt(j) - '0');
                        }
                }
                
        }
        
        public void readNumberOfBoats(){
                boatsNumber = new Integer[NUMBEROFBOATS];
                
                for (int i = 0; i < NUMBEROFBOATS; i++){
                        String[] boatType = fileLines.get(i+canvasNumberOfLines+6).split(" ");
                        boatsNumber[i] = Integer.parseInt(boatType[1]);
                }
                
        }
        
        public static int getCanvasNumberOfColumns() {
                return canvasNumberOfColumns;
        }

        public static int getCanvasNumberOfLines() {
                return canvasNumberOfLines;
        }

        public Integer[][] getGameMatrix() {
                return gameMatrix;
        }

        public Integer[] getBoatsNumber() {
                return boatsNumber;
        }
        
}