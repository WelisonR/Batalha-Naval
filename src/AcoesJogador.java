
public class AcoesJogador {
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
        

        public void setMatrixUserChoices(int x, int y) {
                if (gameMatrix[x][y] > 0){
                        matrixUserChoices[x][y] = 1;
                }
                else{
                        matrixUserChoices[x][y] = -1;
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

        public Integer[] getBoatsNumber() {
                return boatsNumber;
        }
        
        
}
