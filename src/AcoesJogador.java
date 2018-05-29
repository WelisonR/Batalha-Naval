
import static java.lang.Math.abs;


public class AcoesJogador {
        public static final int POSITIONATTACK = 1;
        public static final int AREAATTACK = 2;
        public static final int LINEATTACK = 3;
        public static final int COLUMNATTACK = 4;
        
        private LeitorMapa mapInformations;
        private int [][] matrixUserChoices;
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
        }
        
        private void initializeMatrixUserChoices(){
                matrixUserChoices = new int[canvasNumberOfLines][canvasNumberOfRows];
                for (int i = 0; i < canvasNumberOfLines; i++){
                        for (int j = 0; j < canvasNumberOfRows; j++){
                                matrixUserChoices[i][j] = 0;
                        }
                }
        }
        
        public int verifyPlayerMovement(int x1, int y1, int x2, int y2){
                int deltaX = abs(x2-x1);
                int deltaY = abs(y2-y1);
                
                if (deltaX == 0 && deltaY == 0){
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
                                }
                                else{
                                        matrixUserChoices[i][j] = -1;
                                }
                        }
                }
                
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
                
                return winner;
        }
        
        public boolean verifyLooser(){
                return (PontuacaoJogo.ACTUALSCORE - PontuacaoJogo.POSITIONATTACKSCORE) < 0;
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

        public Integer[] getBoatsNumber() {
                return boatsNumber;
        }
        
        
}
