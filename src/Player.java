
// class to receive the player informations (name and score)
public class Player {
        private String userName;
        private int userScore;

        public Player(String topUserName, int topUserScore){
                this.userName = topUserName;
                this.userScore = topUserScore;
        }
                
        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public int getUserScore() {
                return userScore;
        }

        public void setUserScore(int topUserscore) {
                this.userScore = topUserscore;
        }
}
