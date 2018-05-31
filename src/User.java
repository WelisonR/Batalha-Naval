
public class User {
        private String topUserName;
        private int topUserScore;

        public User(String topUserName, int topUserScore){
                this.topUserName = topUserName;
                this.topUserScore = topUserScore;
        }
                
        public String getTopUserName() {
                return topUserName;
        }

        public void setTopUserName(String topUserName) {
                this.topUserName = topUserName;
        }

        public int getTopUserScore() {
                return topUserScore;
        }

        public void setTopUserScore(int topUserscore) {
                this.topUserScore = topUserscore;
        }
}
