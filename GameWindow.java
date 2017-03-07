import java.util.TimerTask;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;

public class GameWindow extends javax.swing.JFrame {

    java.util.Timer timer;

    Player HumanPlayer = new Player();
    Player EnemyPlayer = new Player();
    
    BlindsChange Blinds;    
    DeckOfCards Deck = new DeckOfCards();
    PokerGame PG;

    // PUBLIC CONSTRUCTOR...
    public GameWindow() {
        initComponents();
        setTitle("Heads up Table");

        PG = new PokerGame(GameWindow.this);
        
        // Connect Decision buttons to PokerGame class
        Decision1.addActionListener(PG);
        Decision2.addActionListener(PG);
        Decision3.addActionListener(PG);

        // 1) Check if Nickname is not empty and correct 
        String nickName = showInputNameDialog();
        
        // Set Nicknames and chips...
        HumanPlayer.setNickName(nickName);
        EnemyPlayer.setNickName("Artificial Intelligence");
        PlayerName.setText(HumanPlayer.getNickName());
        EnemyName.setText(EnemyPlayer.getNickName());
        PlayerChips.setText(Integer.toString(HumanPlayer.getChips()));
        EnemyChips.setText(Integer.toString(EnemyPlayer.getChips()));
        
        //Hide all elements at start
        HideAllElements();
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PlayerImage = new javax.swing.JPanel();
        PlayerName = new javax.swing.JLabel();
        PlayerChips = new javax.swing.JLabel();
        EnemyName = new javax.swing.JLabel();
        EnemyChips = new javax.swing.JLabel();
        PlayerCard1 = new javax.swing.JLabel();
        PlayerCard2 = new javax.swing.JLabel();
        EnemyImage = new javax.swing.JPanel();
        EnemyCard1 = new javax.swing.JLabel();
        EnemyCard2 = new javax.swing.JLabel();
        FlopCard1 = new javax.swing.JLabel();
        FlopCard2 = new javax.swing.JLabel();
        FlopCard3 = new javax.swing.JLabel();
        TurnCard = new javax.swing.JLabel();
        RiverCard = new javax.swing.JLabel();
        Decision1 = new javax.swing.JButton();
        Decision2 = new javax.swing.JButton();
        Decision3 = new javax.swing.JButton();
        betSlider = new javax.swing.JSlider();
        betSpinner = new javax.swing.JSpinner();
        DealerPlayer = new javax.swing.JButton();
        DealerEnemy = new javax.swing.JButton();
        potValue = new javax.swing.JLabel();
        potLabel = new javax.swing.JLabel();
        startGameButton = new javax.swing.JButton();
        blindLevel = new javax.swing.JLabel();
        blindLevelLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PlayerImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PlayerImage.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout PlayerImageLayout = new javax.swing.GroupLayout(PlayerImage);
        PlayerImage.setLayout(PlayerImageLayout);
        PlayerImageLayout.setHorizontalGroup(
            PlayerImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        PlayerImageLayout.setVerticalGroup(
            PlayerImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );

        getContentPane().add(PlayerImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        PlayerName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(PlayerName, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 170, 150, 37));

        PlayerChips.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerChips.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(PlayerChips, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 150, 37));

        EnemyName.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EnemyName.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(EnemyName, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, 150, 37));

        EnemyChips.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EnemyChips.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(EnemyChips, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 220, 150, 37));

        PlayerCard1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerCard1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PlayerCard1.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(PlayerCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, 70, 90));

        PlayerCard2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        PlayerCard2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        PlayerCard2.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(PlayerCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 10, 70, 90));

        EnemyImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        EnemyImage.setPreferredSize(new java.awt.Dimension(150, 150));

        javax.swing.GroupLayout EnemyImageLayout = new javax.swing.GroupLayout(EnemyImage);
        EnemyImage.setLayout(EnemyImageLayout);
        EnemyImageLayout.setHorizontalGroup(
            EnemyImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );
        EnemyImageLayout.setVerticalGroup(
            EnemyImageLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 148, Short.MAX_VALUE)
        );

        getContentPane().add(EnemyImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 10, 150, -1));

        EnemyCard1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EnemyCard1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        EnemyCard1.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(EnemyCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 10, 70, 90));

        EnemyCard2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        EnemyCard2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        EnemyCard2.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(EnemyCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 10, 70, 90));

        FlopCard1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlopCard1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FlopCard1.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(FlopCard1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 390, 100, 150));

        FlopCard2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlopCard2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FlopCard2.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(FlopCard2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 390, 100, 150));

        FlopCard3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        FlopCard3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        FlopCard3.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(FlopCard3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 100, 150));

        TurnCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TurnCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TurnCard.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(TurnCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 390, 100, 150));

        RiverCard.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        RiverCard.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        RiverCard.setPreferredSize(new java.awt.Dimension(50, 50));
        getContentPane().add(RiverCard, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 390, 100, 150));

        Decision1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Decision1ActionPerformed(evt);
            }
        });
        getContentPane().add(Decision1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 320, 120, 38));

        Decision2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Decision2ActionPerformed(evt);
            }
        });
        getContentPane().add(Decision2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 320, 120, 38));

        Decision3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Decision3ActionPerformed(evt);
            }
        });
        getContentPane().add(Decision3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 320, 120, 38));

        betSlider.setMajorTickSpacing(10);
        betSlider.setMaximum(3000);
        betSlider.setSnapToTicks(true);
        betSlider.setValue(0);
        betSlider.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        betSlider.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                betSliderStateChanged(evt);
            }
        });
        getContentPane().add(betSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(212, 280, 250, 30));

        betSpinner.setModel(new javax.swing.SpinnerNumberModel(0, 0, 3000, 10));
        betSpinner.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                betSpinnerStateChanged(evt);
            }
        });
        getContentPane().add(betSpinner, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 120, 30));

        DealerPlayer.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        DealerPlayer.setText("D");
        DealerPlayer.setToolTipText("");
        getContentPane().add(DealerPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 170, -1, 38));

        DealerEnemy.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        DealerEnemy.setText("D");
        DealerEnemy.setToolTipText("");
        getContentPane().add(DealerEnemy, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 170, -1, 38));

        potValue.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        potValue.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        potValue.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        getContentPane().add(potValue, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 80, 120, 20));

        potLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        potLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        potLabel.setText("Pot:");
        getContentPane().add(potLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 180, 20));

        startGameButton.setText("Start game");
        startGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startGameButtonActionPerformed(evt);
            }
        });
        getContentPane().add(startGameButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 230, 140, -1));

        blindLevel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        blindLevel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(blindLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 30, 120, 20));

        blindLevelLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        blindLevelLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        blindLevelLabel.setText("Blinds:");
        getContentPane().add(blindLevelLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 120, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Decision1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Decision1ActionPerformed

        

    }//GEN-LAST:event_Decision1ActionPerformed

    private void betSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_betSliderStateChanged
        betSpinner.setValue(betSlider.getValue());
    }//GEN-LAST:event_betSliderStateChanged

    private void betSpinnerStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_betSpinnerStateChanged
        betSlider.setValue((int) betSpinner.getValue());
        Decision3.setText("Raise to " + betSpinner.getValue().toString());
    }//GEN-LAST:event_betSpinnerStateChanged

    private void Decision2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Decision2ActionPerformed

    }//GEN-LAST:event_Decision2ActionPerformed

    private void Decision3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Decision3ActionPerformed


    }//GEN-LAST:event_Decision3ActionPerformed

    private void startGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startGameButtonActionPerformed
        // After 3 second countdown the game begins...
        new startGameTimer(3);
    }//GEN-LAST:event_startGameButtonActionPerformed

    // MAIN FUNCTION
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameWindow().setVisible(true);
            }
        });
    }

    // FUNCTIONS...
    // 1) Check if Nickname is not empty and correct
    public final String showInputNameDialog() {
        String inputName = JOptionPane.showInputDialog("Insert your Nickname");
        try {
            if (inputName.isEmpty()) {
                inputName = showInputNameDialog();
            }
        } catch (NullPointerException ex) {
            System.exit(0);
        }
        return inputName;
    }
    
    
    // 2) Hide all UI elements
    public void HideAllElements(){
        DealerPlayer.setVisible(false);
        DealerEnemy.setVisible(false);
        
        betSlider.setVisible(false);
        betSpinner.setVisible(false);
        
        Decision1.setVisible(false);
        Decision2.setVisible(false);
        Decision3.setVisible(false);
        
        FlopCard1.setVisible(false);
        FlopCard2.setVisible(false);
        FlopCard3.setVisible(false);
        TurnCard.setVisible(false);
        RiverCard.setVisible(false);
        
        PlayerCard1.setVisible(false);
        PlayerCard2.setVisible(false);
        
        EnemyCard1.setVisible(false);
        EnemyCard2.setVisible(false);
        
        blindLevel.setVisible(false);
        blindLevelLabel.setVisible(false);
        
        potLabel.setVisible(false);
        potValue.setVisible(false);
    }
    
    // 3) Start Preflop...
    public void startPreflop(){
        
        // Start the PreFlop...
        PG.preflop();        
    }   
    
    
    //GETTERS TO GUI ELEMENTS...

    public Player getHumanPlayer() {
        return HumanPlayer;
    }

    public Player getEnemyPlayer() {
        return EnemyPlayer;
    }

    public JLabel getPotLabel() {
        return potLabel;
    }

    public JLabel getPotValue() {
        return potValue;
    }

    public JLabel getEnemyChips() {
        return EnemyChips;
    }

    public JLabel getPlayerChips() {
        return PlayerChips;
    }
    
    public JSpinner getBetSpinner() {
        return betSpinner;
    }
  
    public JSlider getBetSlider() {
        return betSlider;
    }
    
    public JLabel getBlindLevel() {    
        return blindLevel;
    }
    
    public JLabel getBlindLevelLabel() {
        return blindLevelLabel;
    }

    public JButton getStartButton() {
        return startGameButton;
    }

    public JButton getDecision1Button() {
        return Decision1;
    }

    public JButton getDecision2Button() {
        return Decision2;
    }

    public JButton getDecision3Button() {
        return Decision3;
    }

    public JLabel getFlopCard1() {
        return FlopCard1;
    }

    public JLabel getFlopCard2() {
        return FlopCard2;
    }

    public JLabel getFlopCard3() {
        return FlopCard3;
    }

    public JLabel getTurnCard() {
        return TurnCard;
    }

    public JLabel getRiverCard() {
        return RiverCard;
    }

    public JLabel getEnemyCard1() {
        return EnemyCard1;
    }

    public JLabel getEnemyCard2() {
        return EnemyCard2;
    }

    public JLabel getPlayerCard1() {
        return PlayerCard1;
    }

    public JLabel getPlayerCard2() {
        return PlayerCard2;
    }

    public JButton getDealerEnemy() {
        return DealerEnemy;
    }

    public JButton getDealerPlayer() {
        return DealerPlayer;
    }

    public BlindsChange getBlinds() {
        return Blinds;
    }

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DealerEnemy;
    private javax.swing.JButton DealerPlayer;
    private javax.swing.JButton Decision1;
    private javax.swing.JButton Decision2;
    private javax.swing.JButton Decision3;
    private javax.swing.JLabel EnemyCard1;
    private javax.swing.JLabel EnemyCard2;
    private javax.swing.JLabel EnemyChips;
    private javax.swing.JPanel EnemyImage;
    private javax.swing.JLabel EnemyName;
    private javax.swing.JLabel FlopCard1;
    private javax.swing.JLabel FlopCard2;
    private javax.swing.JLabel FlopCard3;
    private javax.swing.JLabel PlayerCard1;
    private javax.swing.JLabel PlayerCard2;
    private javax.swing.JLabel PlayerChips;
    private javax.swing.JPanel PlayerImage;
    private javax.swing.JLabel PlayerName;
    private javax.swing.JLabel RiverCard;
    private javax.swing.JLabel TurnCard;
    private javax.swing.JSlider betSlider;
    private javax.swing.JSpinner betSpinner;
    private javax.swing.JLabel blindLevel;
    private javax.swing.JLabel blindLevelLabel;
    private javax.swing.JLabel potLabel;
    private javax.swing.JLabel potValue;
    private javax.swing.JButton startGameButton;
    // End of variables declaration//GEN-END:variables

// *************************************************************************
    // INNER CLASS TIMER FOR START BUTTON   
    public class startGameTimer {

        public startGameTimer(int seconds) {

            timer = new java.util.Timer();
            timer.schedule(new RemindTask(seconds), 0, 1 * 1000);
        }

        class RemindTask extends TimerTask {

            int time;

            public RemindTask(int time) {
                this.time = time;
            }

            @Override
            public void run() {
                // If time is not equal 0, remaining seconds will be displayed on startGameButton
                if (time != 0) {
                    startGameButton.setEnabled(false);
                    startGameButton.setText(Integer.toString(time));
                    System.out.println("Time remaining: " + time);
                    time--;
                } else {
                    timer.cancel();
                    startGameButton.setVisible(false);
                    startPreflop();

                }
            }
        }
    }
    // *************************************************************************
}
