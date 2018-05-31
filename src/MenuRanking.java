
import javax.swing.JLabel;

public class MenuRanking extends javax.swing.JFrame {

        Ranking rank;
        JLabel[] playerNameLabel = new JLabel[10];
        JLabel[] playerScoreLabel = new JLabel[10];
        
        public MenuRanking() {
                rank = new Ranking();
                rank.readRanking();
                rank.fillRankingInformation();
                initComponents();
                initComponents2();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabelRankingNumbers = new javax.swing.JLabel();
                jLabelRankingImage = new javax.swing.JLabel();
                jLabelBackground = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Ranking");
                setBackground(new java.awt.Color(1, 50, 67));
                setForeground(new java.awt.Color(1, 50, 67));
                setMaximumSize(new java.awt.Dimension(400, 500));
                setMinimumSize(new java.awt.Dimension(400, 500));
                setPreferredSize(new java.awt.Dimension(400, 500));
                setResizable(false);
                getContentPane().setLayout(null);

                jLabelRankingNumbers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ranking_numbers.png"))); // NOI18N
                getContentPane().add(jLabelRankingNumbers);
                jLabelRankingNumbers.setBounds(0, 80, 110, 410);

                jLabelRankingImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ranking_logo.png"))); // NOI18N
                getContentPane().add(jLabelRankingImage);
                jLabelRankingImage.setBounds(100, 20, 210, 50);

                jLabelBackground.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/ranking_background.jpg"))); // NOI18N
                getContentPane().add(jLabelBackground);
                jLabelBackground.setBounds(0, 0, 400, 520);

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        public void initComponents2(){
                
                for (int i = 0; i < 10; i++){
                        playerNameLabel[i] = new JLabel();
                        playerNameLabel[i].setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
                        playerNameLabel[i].setForeground(new java.awt.Color(108,122,137)); // 240, 52, 52
                        getContentPane().add(playerNameLabel[i]);
                        playerNameLabel[i].setBounds(130, 90+40*i, 170, 40);
                        
                        String topNamePlayer = rank.getNamesTop10(i);
                        playerNameLabel[i].setText(topNamePlayer);
                        
                        jLabelBackground.add(playerNameLabel[i]);
                }
                
                for (int i = 0; i < 10; i++){
                        playerScoreLabel[i] = new JLabel();
                        playerScoreLabel[i].setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
                        playerScoreLabel[i].setForeground(new java.awt.Color(108,122,137)); // 240, 52, 52
                        getContentPane().add(playerScoreLabel[i]);
                        playerScoreLabel[i].setBounds(300, 90+40*i, 170, 40);
                        
                        String topScorePlayer = Integer.toString(rank.getScoresTop10(i));
                        playerScoreLabel[i].setText(topScorePlayer);
                        
                        jLabelBackground.add(playerScoreLabel[i]);
                }
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabelBackground;
        private javax.swing.JLabel jLabelRankingImage;
        private javax.swing.JLabel jLabelRankingNumbers;
        // End of variables declaration//GEN-END:variables
}
