
import java.awt.Color;
import javax.swing.JLabel;

public class MenuRanking extends javax.swing.JFrame {

        Ranking rank;
        JLabel[] playerNameLabel = new JLabel[10];
        JLabel[] playerScoreLabel = new JLabel[10];
        
        public MenuRanking() {
                getContentPane().setBackground(new Color(34, 49, 63)); // blue
                
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

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Ranking");
                setBackground(new java.awt.Color(1, 50, 67));
                setForeground(new java.awt.Color(1, 50, 67));
                setMaximumSize(new java.awt.Dimension(400, 500));
                setMinimumSize(new java.awt.Dimension(400, 500));
                setPreferredSize(new java.awt.Dimension(400, 500));
                setResizable(false);

                jLabelRankingNumbers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Numeros_Rank.png"))); // NOI18N

                jLabelRankingImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Logo_Ranking1.png"))); // NOI18N

                javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
                getContentPane().setLayout(layout);
                layout.setHorizontalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(100, 100, 100)
                                .addComponent(jLabelRankingImage, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabelRankingNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                );
                layout.setVerticalGroup(
                        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabelRankingImage, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, 0)
                                .addComponent(jLabelRankingNumbers, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
                );

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        public void initComponents2(){
                
                for (int i = 0; i < 10; i++){
                        playerNameLabel[i] = new JLabel();
                        playerNameLabel[i].setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
                        playerNameLabel[i].setForeground(new java.awt.Color(240, 52, 52)); // 240, 52, 52
                        getContentPane().add(playerNameLabel[i]);
                        playerNameLabel[i].setBounds(130, 90+40*i, 170, 40);
                        
                        String topNamePlayer = rank.getNamesTop10(i);
                        playerNameLabel[i].setText(topNamePlayer);
                        System.out.println(playerNameLabel[i].getText());
                }
                
                for (int i = 0; i < 10; i++){
                        playerScoreLabel[i] = new JLabel();
                        playerScoreLabel[i].setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
                        playerScoreLabel[i].setForeground(new java.awt.Color(240, 52, 52)); // 240, 52, 52
                        getContentPane().add(playerScoreLabel[i]);
                        playerScoreLabel[i].setBounds(300, 90+40*i, 170, 40);
                        
                        String topScorePlayer = Integer.toString(rank.getScoresTop10(i));
                        playerScoreLabel[i].setText(topScorePlayer);
                        System.out.println(playerScoreLabel[i].getText());
                }
        }

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabelRankingImage;
        private javax.swing.JLabel jLabelRankingNumbers;
        // End of variables declaration//GEN-END:variables
}
