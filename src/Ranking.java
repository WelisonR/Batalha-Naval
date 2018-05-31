
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;


public class Ranking {
        
        List<User> usersInformation = new ArrayList<>();
        String[] rankUserNames = new String[10];
        Integer[] rankUserScores = new Integer[10];
        
        public Ranking(){}
        
        public void readRanking(){
                Scanner file = null;
                
                try{
                        file = new Scanner(new File(filePaths.RANKINGFILEPATH));
                        
                        for (int i = 0; i < 2; i++){
                                String trash = file.nextLine();
                        }
                        
                        while (file.hasNextLine()){
                                String phrase = file.nextLine().replace("-", "");
                                String[] splitString = phrase.split(" ");
                                
                                usersInformation.add(new User(splitString[0], Integer.parseInt(splitString[1])));
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
                                System.out.println("Could not close file!");
                        }
                }
                
        }
        
        public void sortRanking(){
                usersInformation.add(new User(Menu.playerName, PontuacaoJogo.ACTUALSCORE));
                
                Collections.sort(usersInformation, new Comparator<User>() {
                        @Override
                        public int compare(User o1, User o2) {
                                int scoreCompare = o2.getUserScore() - o1.getUserScore();
                                
                                if (scoreCompare != 0){
                                        return scoreCompare;
                                }
                                else{
                                        int nameCompare = o1.getUserName().compareTo(o2.getUserName());
                                        
                                        return nameCompare;
                                }
                        }
                   
                        
                });
                
        }
        
        public void writeRanking(){
                        FileWriter fw = null;
                        PrintWriter pw = null;
                try{
                        fw = new FileWriter(filePaths.RANKINGFILEPATH);
                        pw = new PrintWriter(fw);
                        
                        pw.println("# GAME RANKING");
                        pw.println("# NICKNAME	     SCORE");
                        
                        int count = 0;
                        for(User xUser: usersInformation){
                                if (count < 10){
                                        int nameLength = xUser.getUserName().length();
                                        pw.print(xUser.getUserName() + " "); 
                                        for (int i = 0; i < 21-(nameLength+1); i++){
                                                pw.print("-");
                                        }
                                        pw.println(xUser.getUserScore());
                                }
                        count++;
                        }
                        
                }
                catch(IOException e){
                        System.out.println("Não foi possível atualizar o rank!");
                }
                finally{
                        try{
                                pw.close();
                        }
                        catch(Exception e){
                                System.out.println("Não foi possível fechar o arquivo!");
                        }
                }
        }
        
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
