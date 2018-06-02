
// simple frame just to show the game image tutorial
public class tutorialFrame extends javax.swing.JFrame {

        public tutorialFrame() {
                initComponents();
        }

        @SuppressWarnings("unchecked")
        // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
        private void initComponents() {

                jLabelTutorial = new javax.swing.JLabel();

                setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
                setTitle("Naval Warfare 2.0 - Tutorial");
                setMaximumSize(new java.awt.Dimension(600, 450));
                setMinimumSize(new java.awt.Dimension(600, 450));
                setResizable(false);
                getContentPane().setLayout(null);

                jLabelTutorial.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/tutorial_tutorial.png"))); // NOI18N
                getContentPane().add(jLabelTutorial);
                jLabelTutorial.setBounds(0, 0, 600, 450);

                pack();
                setLocationRelativeTo(null);
        }// </editor-fold>//GEN-END:initComponents

        // Variables declaration - do not modify//GEN-BEGIN:variables
        private javax.swing.JLabel jLabelTutorial;
        // End of variables declaration//GEN-END:variables
}
