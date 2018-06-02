
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
public class Ranking {
        List<User> usersInformation = new ArrayList<>();
        String[] rankUserNames = new String[10];
        Integer[] rankUserScores = new Integer[10];
        
        // responsible to receive all the informations of ranking.txt file
        public void readRanking(){
                Scanner rankingFile = null;
                
                try{
                        rankingFile = new Scanner (new File(filePaths.RANKINGFILEPATH));
                        
                        for (int i = 0; i < 2; i++){
                                String trash = rankingFile.nextLine();
                        }
                        
                        while (rankingFile.hasNextLine()){
                                String phrase = rankingFile.nextLine().replace("-", "");
                                String[] splitString = phrase.split(" ");
                                
                                usersInformation.add(new User(splitString[0], Integer.parseInt(splitString[1])));
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
                usersInformation.add(new User(Menu.playerName, PontuacaoJogo.ACTUALSCORE));
                
                Collections.sort(usersInformation, new Comparator<User>() {
                        @Override
                        public int compare(User p1, User p2) {
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
                        fileWriter = new FileWriter(filePaths.RANKINGFILEPATH);
                        printToFile = new PrintWriter(fileWriter);
                        
                        printToFile.println("# GAME RANKING");
                        printToFile.println("# NICKNAME	     SCORE");
                        
                        int count = 0;
                        for(User xUser: usersInformation){
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
                
                for(User xUser: usersInformation){
                        if(i < 10){
                                rankUserNames[i] = xUser.getUserName();
                                rankUserScores[i] = xUser.getUserScore();
                        }
                        i++;
                }
                
        }
        
        public String getNamesTop10(int i) {
                return rankUserNames[i];
        }

        public Integer getScoresTop10(int i) {
                return rankUserScores[i];
        }

        public List<User> getUsersInformation() {
                return usersInformation;
        }
}
