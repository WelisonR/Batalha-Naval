import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class Menu extends javax.swing.JFrame {

        public static String playerName = "";
        public static String mapPath = "";
        JFileChooser mapFile = new JFileChooser();
        
        public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//InterfacePrincipal frame = new InterfacePrincipal();
                                        Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
        
        public Menu() {
                initComponents();
                jTextFieldPlayerName.setBackground(new Color(0, 0, 0, 196)); // transparent with gradient
                jButtonPlay.setBackground(new Color(40, 20, 45)); // purple
                jButtonRanking.setBackground(new Color(50, 30, 55)); // purple
                
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jButtonPlay = new javax.swing.JButton();
                jButtonRanking = new javax.swing.JButton();
                jTextFieldPlayerName = new javax.swing.JTextField();
                jLabelLogo = new javax.swing.JLabel();
                jLabelFundoMenu = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
                setTitle("Naval Warfare 2.0");
                setBackground(new java.awt.Color(1, 50, 67));
                setMaximumSize(new java.awt.Dimension(900, 600));
                setMinimumSize(new java.awt.Dimension(900, 600));
                setPreferredSize(new java.awt.Dimension(900, 600));
                setResizable(false);
                getContentPane().setLayout(null);

                jButtonPlay.setBackground(new java.awt.Color(236, 100, 75));
                jButtonPlay.setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
                jButtonPlay.setForeground(new java.awt.Color(0, 0, 0));
                jButtonPlay.setText("JOGAR");
                jButtonPlay.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonPlayActionPerformed(evt);
                        }
                });
                getContentPane().add(jButtonPlay);
                jButtonPlay.setBounds(310, 300, 320, 55);

                jButtonRanking.setBackground(new java.awt.Color(236, 100, 75));
                jButtonRanking.setFont(new java.awt.Font("Bitstream Charter", 1, 24)); // NOI18N
                jButtonRanking.setForeground(new java.awt.Color(0, 0, 0));
                jButtonRanking.setText("RANKING");
                jButtonRanking.addActionListener(new java.awt.event.ActionListener() {
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                                jButtonRankingActionPerformed(evt);
                        }
                });
                getContentPane().add(jButtonRanking);
                jButtonRanking.setBounds(310, 420, 320, 45);

                jTextFieldPlayerName.setFont(new java.awt.Font("Bitstream Charter", 1, 18)); // NOI18N
                jTextFieldPlayerName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
                jTextFieldPlayerName.addFocusListener(new java.awt.event.FocusAdapter() {
                        public void focusGained(java.awt.event.FocusEvent evt) {
                                jTextFieldPlayerNameFocusGained(evt);
                        }
                        public void focusLost(java.awt.event.FocusEvent evt) {
                                jTextFieldPlayerNameFocusLost(evt);
                        }
                });
                getContentPane().add(jTextFieldPlayerName);
                jTextFieldPlayerName.setBounds(170, 230, 570, 33);

                jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Logo_Batalha_Naval1.png"))); // NOI18N
                getContentPane().add(jLabelLogo);
                jLabelLogo.setBounds(130, 110, 650, 89);

                jLabelFundoMenu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/Blue_background.jpg"))); // NOI18N
                getContentPane().add(jLabelFundoMenu);
                jLabelFundoMenu.setBounds(0, 0, 1610, 850);

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        private void jButtonPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPlayActionPerformed
                boolean occurredAnError = false;
                
                if (jTextFieldPlayerName.getText().trim().equals("Digite o seu nickname") || jTextFieldPlayerName.getText().trim().equals("")){
                        JOptionPane.showMessageDialog(null, "Digite um nickname!");
                }
                else{
                        playerName = jTextFieldPlayerName.getText();
                        mapFile.showOpenDialog(null);
                        try{
                                mapPath = mapFile.getSelectedFile().getAbsolutePath();
                        }
                        catch(Exception e){
                                occurredAnError = true;
                                JOptionPane.showMessageDialog(null, "Mapa do jogo não encontrado!");
                        }
                        
                        if (!occurredAnError && !mapPath.substring(mapPath.length()-9, mapPath.length()-9+3).equals("map")){
                                occurredAnError = true;
                                JOptionPane.showMessageDialog(null, "O Arquivo selecionado não corresponde ao mapa do jogo!");
                        }
                        
                        if (!occurredAnError){
                                InterfacePrincipal frame = new InterfacePrincipal();
                                frame.setVisible(true);
                                setVisible(false);   
                        }
                        
                }
        }//GEN-LAST:event_jButtonPlayActionPerformed

        private void jTextFieldPlayerNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPlayerNameFocusGained
                if (jTextFieldPlayerName.getText().trim().equals("Digite o seu nickname")){
                        jTextFieldPlayerName.setText("");
                } 
                
        }//GEN-LAST:event_jTextFieldPlayerNameFocusGained

        private void jTextFieldPlayerNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPlayerNameFocusLost
                if(jTextFieldPlayerName.getText().trim().equals("")){
                        jTextFieldPlayerName.setText("Digite o seu nickname");
                }
        }//GEN-LAST:event_jTextFieldPlayerNameFocusLost

        private void jButtonRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRankingActionPerformed
                // TODO add your handling code here:
                MenuRanking rank1 = new MenuRanking();
                rank1.setVisible(true);
                //String rank = "";
                
                //JOptionPane.showMessageDialog(null, rank);
        }//GEN-LAST:event_jButtonRankingActionPerformed

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JButton jButtonPlay;
        private javax.swing.JButton jButtonRanking;
        private javax.swing.JLabel jLabelFundoMenu;
        private javax.swing.JLabel jLabelLogo;
        private javax.swing.JTextField jTextFieldPlayerName;
        // End of variables declaration//GEN-END:variables
}
