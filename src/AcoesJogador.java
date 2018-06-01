
import static java.lang.Math.abs;
import static java.lang.Math.abs;


public class AcoesJogador {
        public static final int POSITIONATTACK = 1;
        public static final int AREAATTACK = 2;
        public static final int LINEATTACK = 3;
        public static final int COLUMNATTACK = 4;
        
        private LeitorMapa mapInformations;
        private int [][] matrixUserChoices;
        private int [][] booleanMatrixUserChoices;
        private int [][] booleanFoundedBoats;
        private Integer[][] gameMatrix;
        private int canvasNumberOfLines;
        private int canvasNumberOfRows;
        private Integer[] boatsNumber;
        
        public AcoesJogador(LeitorMapa mapInformations){
                this.mapInformations = mapInformations;
                gameMatrix = mapInformations.getGameMatrix();
                boatsNumber = mapInformations.getBoatsNumber();
                
                canvasNumberOfRows = LeitorMapa.getCanvasNumberOfRows();
                canvasNumberOfLines = LeitorMapa.getCanvasNumberOfLines();
                initializeMatrixUserChoices();
                initializeBooleanMatrixUserChoices();
                initializeBooleanFoundedBoats();
        }
        
        public void initializeMatrixUserChoices(){
                matrixUserChoices = new int[canvasNumberOfLines][canvasNumberOfRows];
                for (int i = 0; i < canvasNumberOfLines; i++){
                        for (int j = 0; j < canvasNumberOfRows; j++){
                                matrixUserChoices[i][j] = 0;
                        }
                }
        }
        
        public void initializeBooleanMatrixUserChoices(){
                booleanMatrixUserChoices = new int[canvasNumberOfLines][canvasNumberOfRows];
                for (int i = 0; i < canvasNumberOfLines; i++){
                        for (int j = 0; j < canvasNumberOfRows; j++){
                                booleanMatrixUserChoices[i][j] = 0;
                        }
                }
        }
        
        public void initializeBooleanFoundedBoats(){
                booleanFoundedBoats = new int[canvasNumberOfLines][canvasNumberOfRows];
                for (int i = 0; i < canvasNumberOfLines; i++){
                        for (int j = 0; j < canvasNumberOfRows; j++){
                                booleanFoundedBoats[i][j] = 0;
                        }
                }
        }
        public int verifyPlayerMovement(int x1, int y1, int x2, int y2){
                int deltaX = abs(x2-x1);
                int deltaY = abs(y2-y1);
                
                if ((deltaX == 0 && deltaY == 0) && (matrixUserChoices[x1][y1] == 1 || matrixUserChoices[x1][y1] == -1)){
                        return -1;
                }
                else if (deltaX == 0 && deltaY == 0){
                        return POSITIONATTACK;
                }
                else if (deltaX == 1 && deltaY == 1){
                        return AREAATTACK;
                }
                else if (deltaX == 0 && deltaY == canvasNumberOfRows-1){
                        return LINEATTACK;
                }
                else if (deltaY == 0 && deltaX == canvasNumberOfLines-1){
                        return COLUMNATTACK;
                }
                if ((deltaX == 0 && deltaY == 0) && (gameMatrix[x1][y1] == 1)){
                        return -1;
                }
                else{
                        return -1;
                }
        }
        
        public void setMatrixUserChoices(int x1, int y1, int x2, int y2) {
                int menorX = 0, maiorX = 0, menorY = 0, maiorY = 0;
                
                if (x1 >= x2){
                        maiorX = x1;
                        menorX = x2;
                }
                else{
                        maiorX = x2;
                        menorX = x1;
                }
                
                if (y1 >= y2){
                        maiorY = y1;
                        menorY = y2;
                }
                else{
                        maiorY = y2;
                        menorY = y1;
                }
                
                for(int i = menorX; i <= maiorX; i++){
                        for(int j = menorY; j <= maiorY; j++){
                                if(gameMatrix[i][j] > 0){
                                        matrixUserChoices[i][j] = 1;
                                        booleanMatrixUserChoices[i][j] = 1;
                                }
                                else{
                                        matrixUserChoices[i][j] = -1;
                                }
                        }
                }
                
                verifyDestroyedBoatsOnLines();
                verifyDestroyedBoatsOnRows();
                
        }
        
        public boolean verifyWinner(){
                boolean winner = true;
                externfor: for (int i = 0; i < canvasNumberOfLines; i++){
                        for (int j = 0; j < canvasNumberOfRows; j++){
                                if (matrixUserChoices[i][j] != 1 && gameMatrix[i][j] > 0){
                                        winner = false;
                                        break externfor;
                                }
                        }
                }
                
                if (winner == true){
                        Ranking r1 = new Ranking();
                        r1.readRanking();
                        r1.sortRanking();
                        r1.writeRanking();
                }
                
                return winner;
        }
        
        public boolean verifyLooser(){
                return (PontuacaoJogo.ACTUALSCORE - PontuacaoJogo.POSITIONATTACKSCORE) < 0;
        }
        
        public void verifyDestroyedBoatsOnLines(){
                
                
                for (int i = 0; i < canvasNumberOfLines; i++){
                        int start = 0;
                        int startCopy = 0;
                        for (int j = 0; j < canvasNumberOfRows; j++){
                                if (gameMatrix[i][j] != 0 && booleanMatrixUserChoices[i][j] == 1 && booleanFoundedBoats[i][j] == 0){
                                        if (startCopy != gameMatrix[i][j] || start == 0){
                                                start = gameMatrix[i][j]+1;
                                                startCopy = gameMatrix[i][j];
                                        }
                                }
                                
                                if (gameMatrix[i][j] == 0){
                                        start = 0;
                                        startCopy = 0;
                                }
                                
                                if (start > 0 && booleanMatrixUserChoices[i][j] == 1){
                                        start--;
                                }
                                
                                if (start == 1){
                                        if (startCopy == 1){
                                                boatsNumber[0] -= 1;
                                                booleanFoundedBoats[i][j] = 1;
                                        }
                                        else if (startCopy == 2){
                                                boatsNumber[1] -= 1;
                                                booleanFoundedBoats[i][j] = 1;
                                                booleanFoundedBoats[i][j-1] = 1;
                                        }
                                        else if (startCopy == 3){
                                                boatsNumber[2] -= 1;
                                                booleanFoundedBoats[i][j] = 1;
                                                booleanFoundedBoats[i][j-1] = 1;
                                                booleanFoundedBoats[i][j-2] = 1;
                                        }
                                        else if (startCopy == 4){
                                                boatsNumber[3] -= 1;
                                                booleanFoundedBoats[i][j] = 1;
                                                booleanFoundedBoats[i][j-1] = 1;
                                                booleanFoundedBoats[i][j-2] = 1;
                                                booleanFoundedBoats[i][j-3] = 1;
                                        }
                                        else if (startCopy == 5){
                                                boatsNumber[4] -= 1;
                                                booleanFoundedBoats[i][j] = 1;
                                                booleanFoundedBoats[i][j-1] = 1;
                                                booleanFoundedBoats[i][j-2] = 1;
                                                booleanFoundedBoats[i][j-3] = 1;
                                                booleanFoundedBoats[i][j-4] = 1;
                                        }
                                        
                                        
                                        
                                        start = 0;
                                        startCopy = 0;
                                }
                        }
                        
                }
                
                
                    
        }
        
        public void verifyDestroyedBoatsOnRows(){
                
                for (int i = 0; i < canvasNumberOfRows; i++){
                        int start = 0;
                        int startCopy = 0;
                        for (int j = 0; j < canvasNumberOfLines; j++){
                                if (gameMatrix[j][i] != 0 && booleanMatrixUserChoices[j][i] == 1 && booleanFoundedBoats[j][i] == 0){
                                        if (startCopy != gameMatrix[j][i] || start == 0){
                                                start = gameMatrix[j][i]+1;
                                                startCopy = gameMatrix[j][i];
                                        }
                                }
                                
                                if (gameMatrix[j][i] == 0){
                                        start = 0;
                                        startCopy = 0;
                                }
                                
                                if (start > 0 && booleanMatrixUserChoices[j][i] == 1){
                                        start--;
                                }
                                
                                if (start == 1){
                                        if (startCopy == 1){
                                                boatsNumber[0] -= 1;
                                                booleanFoundedBoats[j][i] = 1;
                                        }
                                        else if (startCopy == 2){
                                                boatsNumber[1] -= 1;
                                                booleanFoundedBoats[j][i] = 1;
                                                booleanFoundedBoats[j-1][i] = 1;
                                        }
                                        else if (startCopy == 3){
                                                boatsNumber[2] -= 1;
                                                booleanFoundedBoats[j][i] = 1;
                                                booleanFoundedBoats[j-1][i] = 1;
                                                booleanFoundedBoats[j-2][i] = 1;
                                        }
                                        else if (startCopy == 4){
                                                boatsNumber[3] -= 1;
                                                booleanFoundedBoats[j][i] = 1;
                                                booleanFoundedBoats[j-1][i] = 1;
                                                booleanFoundedBoats[j-2][i] = 1;
                                                booleanFoundedBoats[j-3][i] = 1;
                                        }
                                        else if (startCopy == 5){
                                                System.out.println("flag");
                                                boatsNumber[4] -= 1;
                                                System.out.println(boatsNumber[4]);
                                                booleanFoundedBoats[j][i] = 1;
                                                booleanFoundedBoats[j-1][i] = 1;
                                                booleanFoundedBoats[j-2][i] = 1;
                                                booleanFoundedBoats[j-3][i] = 1;
                                                booleanFoundedBoats[j-4][i] = 1;
                                        }
                                        
                                        start = 0;
                                        startCopy = 0;
                                }
                                

                        }
                        
                }
                
        }
        
        public int getMatrixUserChoices(int x, int y) {
                return matrixUserChoices[x][y];
        }

        public Integer[][] getGameMatrix() {
                return gameMatrix;
        }

        public void setGameMatrix(Integer[][] gameMatrix) {
                this.gameMatrix = gameMatrix;
        }

        public int getCanvasNumberOfRows() {
                return canvasNumberOfRows;
        }

        public int getCanvasNumberOfLines() {
                return canvasNumberOfLines;
        }

        public Integer getBoatsNumber(Integer x) {
                return boatsNumber[x];
        }  
        
}
