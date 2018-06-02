
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;

// class to receive, process and update the game ranking
public class RankingProcessor {
        List<Player> playersInformation = new ArrayList<>();
        String[] rankPlayersNames = new String[10];
        Integer[] rankPlayersScores = new Integer[10];
        
        // responsible to receive all the informations of ranking.txt file
        public void readRanking(){
                Scanner rankingFile = null;
                
                try{
                        rankingFile = new Scanner (new File(FilePaths.RANKINGFILEPATH));
                        
                        for (int i = 0; i < 2; i++){
                                String trash = rankingFile.nextLine();
                        }
                        
                        while (rankingFile.hasNextLine()){
                                String phrase = rankingFile.nextLine().replace("-", "");
                                String[] splitString = phrase.split(" ");
                                
                                playersInformation.add(new Player(splitString[0], Integer.parseInt(splitString[1])));
                        }
                }
                catch(IOException e){
                        JOptionPane.showMessageDialog(null, "Não foi possível carregar o arquivo do ranking!");
                }
                finally{
                        try{
                                rankingFile.close();
                        }
                        catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Não foi possível fechar o arquivo do ranking!");
                        }
                }  
                
        }
        
        // responsible to sort the ranking information
        public void sortRanking(){
                // add the last victory
                playersInformation.add(new Player(MainMenuFrame.playerName, GameScores.ACTUALSCORE));
                
                Collections.sort(playersInformation, new Comparator<Player>() {
                        @Override
                        public int compare(Player p1, Player p2) {
                                int scoreCompare = p2.getUserScore() - p1.getUserScore();
                                int nameCompare = p1.getUserName().compareTo(p2.getUserName());
                                
                                if (scoreCompare != 0){
                                        return scoreCompare;
                                }
                                else{   
                                        return nameCompare;
                                }
                        }
                });
                
        }
        
        // responsible to update the ranking
        public void writeRanking(){
                        FileWriter fileWriter = null;
                        PrintWriter printToFile = null;
                        
                try{
                        fileWriter = new FileWriter(FilePaths.RANKINGFILEPATH);
                        printToFile = new PrintWriter(fileWriter);
                        
                        printToFile.println("# GAME RANKING");
                        printToFile.println("# NICKNAME	     SCORE");
                        
                        int count = 0;
                        for(Player xUser: playersInformation){
                                if (count < 10){
                                        int nameLength = xUser.getUserName().length();
                                        printToFile.print(xUser.getUserName() + " "); 
                                        for (int i = 0; i < 21-(nameLength+1); i++){
                                                printToFile.print("-");
                                        }
                                        printToFile.println(xUser.getUserScore());
                                }
                        count++;
                        }
                        
                }
                catch(IOException e){
                        JOptionPane.showMessageDialog(null, "Não foi possível atualizar o rank!");
                }
                finally{
                        try{
                                printToFile.close();
                        }
                        catch(Exception e){
                                JOptionPane.showMessageDialog(null, "Não foi possível fechar o arquivo do ranking!");
                        }
                }
                
        }
        
        // Responsible to fill the array of top 10 players
        public void fillRankingInformation(){
                int i = 0;
                
                for(Player xUser: playersInformation){
                        if(i < 10){
                                rankPlayersNames[i] = xUser.getUserName();
                                rankPlayersScores[i] = xUser.getUserScore();
                        }
                        
                        i++;
                }
                
        }
        
        public String getNamesTop10(int i) {
                return rankPlayersNames[i];
        }

        public Integer getScoresTop10(int i) {
                return rankPlayersScores[i];
        }

        public List<Player> getPlayersInformation() {
                return playersInformation;
        }
}
