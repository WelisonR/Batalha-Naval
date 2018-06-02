
// class to receive the player informations (name and score)
public class User {
        private String userName;
        private int userScore;

        public User(String topUserName, int topUserScore){
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
