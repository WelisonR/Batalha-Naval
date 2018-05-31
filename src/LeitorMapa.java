import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LeitorMapa {
        
        public static final int NUMBEROFBOATS = 5;
        private List<String> fileLines = new ArrayList<>();
        private static int canvasNumberOfRows;
	private static int canvasNumberOfLines;
        private Integer[][] gameMatrix;
        private Integer[] boatsNumber;
        
        public LeitorMapa(String filePath){
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
                        System.out.println("Could not found file!");
                }
                finally{
                        try{
                                file.close();
                        }
                        catch(Exception e){
                                System.out.println("Could not close file");
                        }
                }
        }
        
        public void readMatrixDimensions(){
                String [] lengthMatrix = fileLines.get(1).split(" ");
                
                canvasNumberOfRows = Integer.parseInt(lengthMatrix[0]);
                canvasNumberOfLines = Integer.parseInt(lengthMatrix[1]);
        }
        
        public void readGameMatrix(){
                gameMatrix = new Integer[canvasNumberOfLines][canvasNumberOfRows];
                
                for (int i = 0; i < canvasNumberOfLines; i++){
                        for (int j = 0; j < canvasNumberOfRows; j++){
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
        
        public static int getCanvasNumberOfRows() {
                return canvasNumberOfRows;
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