import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;

public class PokerGame implements ActionListener {

    private int HandsCounter = 0;
    private boolean isPlayerDealer;
    private boolean isPlayerMove;
    private int currentPotValue = 0;
    
    private GameWindow GW;
    private BlindsChange Blinds;
    
    DeckOfCards DeckofCards = dealNewHand(); 
    
    // vars helping with preflop raise + blinds calculations
    boolean humanRaisedPF = false;
    boolean enemyRaisedPF = false;
    // *****************************************************
    
    private boolean isPreFlop = false;
    private boolean isFlop = false;
    private boolean isTurn = false;
    private boolean isRiver = false;
    
    boolean isPlayerAllIn = false;
    boolean isEnemyAllIn = false;
        
    PokerGame(GameWindow aGW) {
        this.GW = aGW; 
    }
    
    public void preflop(){
        
        // Turn on Preflop...
        isPreFlop = true;
        
        // Set the dealer...
        whoIsDealer();
              
        Blinds = new BlindsChange(GW.getBlindLevel(), GW.getBlindLevelLabel()); 
        
        // Pay blinds...
        payBlinds();
        
        // Prepare betSlider and betSpinner
        defaultMovePreparation(isPlayerMove);
        
        
        // Set visible elements on Preflop...
        GW.getPlayerCard1().setVisible(true);
        GW.getPlayerCard2().setVisible(true);
        
        GW.getEnemyCard1().setVisible(true);
        GW.getEnemyCard2().setVisible(true);        
        
        GW.getBlindLevel().setVisible(true);
        GW.getBlindLevelLabel().setVisible(true);
              
        DeckofCards.dealPF(GW.getPlayerCard1(), GW.getPlayerCard2(), GW.getEnemyCard1(),GW.getEnemyCard2());
        
        GW.getDecision1Button().setVisible(true);
        GW.getDecision1Button().setText("Fold");        
        GW.getDecision2Button().setVisible(true);
        GW.getDecision2Button().setText("Call " + Blinds.getSmallBlind());
        GW.getDecision3Button().setVisible(true);
        GW.getDecision3Button().setText("Raise to " + defaultMinRaiseValue());
        
        GW.getPotLabel().setVisible(true);
        GW.getPotValue().setVisible(true);
      
        GW.getBetSlider().setVisible(true);
        GW.getBetSpinner().setVisible(true);
        GW.getBetSpinner().setValue(defaultMinRaiseValue());   
        
     }
    
    public void flop(){        
        // Disable preflop
        isPreFlop = !isPreFlop;
        // Enable flop
        isFlop = true;
        DeckofCards.dealF(GW.getFlopCard1(), GW.getFlopCard2(), GW.getFlopCard3());
        
        GW.getFlopCard1().setVisible(isFlop);
        GW.getFlopCard2().setVisible(isFlop);
        GW.getFlopCard3().setVisible(isFlop);
        
    }    
    public void turn(){
        // Disable flop
        isFlop = !isFlop;
        // Enable turn
        isTurn = true;
        
        DeckofCards.dealT(GW.getTurnCard());        
        GW.getTurnCard().setVisible(isTurn);
    }    
    public void river(){
        // Disable turn
        isTurn = !isTurn;
        // Enable river
        isRiver = true;        
        
        DeckofCards.dealR(GW.getRiverCard());        
        GW.getRiverCard().setVisible(isRiver);
    }
    
    public void showdown(){
        // Disable river
        isRiver = !isRiver;
        
        System.out.println("koniec reki");     
        
        //dealNewHand();
    }
    
    // Deals new hand
    public DeckOfCards dealNewHand(){
        HandsCounter++;
              
        // Dealer switch every hand
        if(HandsCounter != 0){
        isPlayerDealer = !isPlayerDealer;    
        }
        
        System.out.println("Hand number: " + HandsCounter);
        return new DeckOfCards();
    }
    
    public void whoIsDealer(){
        int rand = (int)(Math.random() * 2);
        
        if(rand == 1){
            isPlayerDealer = true;
            isPlayerMove = true;
            GW.getDealerPlayer().setVisible(true);
        }
        
        else {
            isPlayerDealer = false;
            isPlayerMove = false;
            GW.getDealerEnemy().setVisible(true);
        }
             
        System.out.println("Is Player Dealer: " + isPlayerDealer);
    }
    
    public void resetHandStatus(){
        isPreFlop = false;
        isFlop = false;
        isTurn = false;
        isRiver = false;
    }
    
    public int defaultMinRaiseValue(){
        int defaultRaiseVal = Blinds.getBigBlind()*2;

        return defaultRaiseVal;

        
    }
    
    public void defaultMovePreparation(boolean isPlayerMove){        
        if(isPlayerMove){   
            // Preflop, player move, enemy called SB -> BB, change:
            if(isPreFlop == true){
                if(GW.EnemyPlayer.getPreflopDecision() == 2){
                    // decision2button to check
                    GW.getDecision2Button().setText("Check");
                }
            }           
            if(isEnemyAllIn){
                GW.getDecision2Button().setText("Call " + GW.getHumanPlayer().getChips());
                GW.getDecision3Button().setVisible(false);
            }

            // border around chips whos move it is now
            Border border = BorderFactory.createLineBorder(Color.RED, 3);                
            GW.getPlayerChips().setBorder(border);            
            Border defBorder = BorderFactory.createLineBorder(Color.black);
            GW.getEnemyChips().setBorder(defBorder);               
            
            // if enemy players are not all in, set default mode for spinner and slider...
            if(!isPlayerAllIn && !isEnemyAllIn){
                SpinnerModel sm = new SpinnerNumberModel(defaultMinRaiseValue(), defaultMinRaiseValue(), GW.HumanPlayer.getChips(), 10); //default value,lower bound,upper bound,increment by
                GW.getBetSpinner().setModel(sm);
                GW.getBetSpinner().repaint();

                GW.getBetSlider().setMinimum(defaultMinRaiseValue());
                GW.getBetSlider().setMaximum(GW.HumanPlayer.getChips());
                GW.getBetSlider().setValue(defaultMinRaiseValue());            
                GW.getBetSlider().repaint();           

                GW.getPlayerChips().repaint();
                GW.getEnemyChips().repaint();
            }
            // but if any of them is all in...
            else{
                
                if(GW.HumanPlayer.getChips() == 0 || GW.EnemyPlayer.getChips() == 0){
                    
                    // hide raise button
                    GW.getDecision3Button().setVisible(false);
                }
            }
            
                       
        }        
        if(!isPlayerMove){
            if(isPreFlop == true){
                if(GW.HumanPlayer.getPreflopDecision() == 2){
                    // decision2button to check
                    GW.getDecision2Button().setText("Check");
                }
            }
            
            if(isPlayerAllIn){
                GW.getDecision2Button().setText("Call " + GW.getEnemyPlayer().getChips());
                GW.getDecision3Button().setVisible(false);
            }
            
            // Border around chips whos move it is now
            Border border = BorderFactory.createLineBorder(Color.RED, 3);                
            GW.getEnemyChips().setBorder(border);
            Border defBorder = BorderFactory.createLineBorder(Color.black);
            GW.getPlayerChips().setBorder(defBorder);
            
            if(!isPlayerAllIn && !isEnemyAllIn){
                SpinnerModel smm = new SpinnerNumberModel(defaultMinRaiseValue(), defaultMinRaiseValue(), GW.EnemyPlayer.getChips(), 10); //default value,lower bound,upper bound,increment by
                GW.getBetSpinner().setModel(smm);
                GW.getBetSpinner().repaint();            

                GW.getBetSlider().setMinimum(defaultMinRaiseValue());
                GW.getBetSlider().setMaximum(GW.EnemyPlayer.getChips());
                GW.getBetSlider().setValue(defaultMinRaiseValue());            
                GW.getBetSlider().repaint();

                GW.getPlayerChips().repaint();
                GW.getEnemyChips().repaint();
            }
            // all in situaion...
            else{
                
            }
            
        }
    }
    
    public void isAllin(){
        if(GW.getHumanPlayer().getChips() == 0){
            isPlayerAllIn = true;
            System.err.println("Player is All-IN");
        }        
        if(GW.getEnemyPlayer().getChips() == 0){ 
            isEnemyAllIn = true;
            System.err.println("Enemy is All-IN");
        }
        
    }
    
    public void allIn(){
        GW.getDecision1Button().setVisible(false);
        GW.getDecision2Button().setVisible(false);
        GW.getDecision3Button().setVisible(false);
        
        GW.getBetSlider().setVisible(false);
        GW.getBetSpinner().setVisible(false);
        
        System.err.println("ALL IN!");
    }
    
    public void calculateCall(int raisedValue){
        
        
    }
    
    public void calculateRaise(int raisedValue, boolean playerMove){
        int temp = raisedValue + currentPotValue;
        boolean isPlayerMove = playerMove;
        if(isPlayerMove){
            // Player raises and Enemy has more chips than raise
            if(temp < GW.getHumanPlayer().getChips()){                               
                GW.getBetSlider().setMinimum(temp);
                GW.getBetSpinner().setValue(temp);            
                GW.getDecision3Button().setText("Raise " + Integer.toString(temp));
                System.out.println("Player ReRaise - Enemy ENOUGH " + temp); 
            }            
            // Player raises and Enemy has no chips to play with - AllIn situation
            if(temp > GW.getHumanPlayer().getChips()){
                GW.getBetSlider().setMinimum(GW.getHumanPlayer().getChips());
                GW.getBetSlider().setMaximum(GW.getHumanPlayer().getChips());
                GW.getBetSpinner().setValue(GW.getHumanPlayer().getChips());            
                GW.getDecision3Button().setText("All-In " + Integer.toString(GW.getHumanPlayer().getChips()));
                System.out.println("Player ReRaise - Enemy NOT ENOUGH " + temp);
            }
        }
        
        if(!isPlayerMove){
            // Player raises and Enemy has more chips than raise
            if(temp < GW.getEnemyPlayer().getChips()){                               
                GW.getBetSlider().setMinimum(temp);
                GW.getBetSpinner().setValue(temp);            
                GW.getDecision3Button().setText("Raise " + Integer.toString(temp));
                System.out.println("Enemy ReRaise - Player ENOUGH chips" + temp); 
            }            
            // Player raises and Enemy has no chips to play with - AllIn situation
            if(temp > GW.getEnemyPlayer().getChips()){
                GW.getBetSlider().setMinimum(GW.getEnemyPlayer().getChips());
                GW.getBetSlider().setMaximum(GW.getEnemyPlayer().getChips());
                GW.getBetSpinner().setValue(GW.getEnemyPlayer().getChips());            
                GW.getDecision3Button().setText("All-In " + Integer.toString(GW.getEnemyPlayer().getChips()));
                System.out.println("Enemy ReRaise - Player NOT ENOUGH chips" + temp);
            }
        }      
        GW.getDecision2Button().repaint();
        GW.getDecision3Button().repaint();
        GW.getBetSlider().repaint();
        GW.getBetSpinner().repaint();
    }
    
    private void payBlinds(){         
        Player player = GW.getHumanPlayer();
        Player enemy = GW.getEnemyPlayer();
           
        if(!isPlayerDealer){            
            // Put small and big blind into the pot
            player.setChips(player.getChips() - Blinds.getBigBlind());
            enemy.setChips(enemy.getChips() - Blinds.getSmallBlind());
            
            // Adds blinds to the current pot
            currentPotValue = Blinds.getSmallBlind() + Blinds.getBigBlind();
            
            // Refresh blinds information
            refreshChipsInfo();
            
            GW.getPotValue().setText(Integer.toString(currentPotValue));
            GW.getPotValue().repaint();            
        }
        
        else{            
            // Puts small and big blind into the pot
            player.setChips(player.getChips() - Blinds.getSmallBlind());
            enemy.setChips(enemy.getChips() - Blinds.getBigBlind());
            
            // Adds blinds to the current pot
            currentPotValue = Blinds.getSmallBlind() + Blinds.getBigBlind();
            
            // Refreshes blinds information
            GW.getPlayerChips().setText(Integer.toString(player.getChips()));
            GW.getPlayerChips().repaint();
            GW.getEnemyChips().setText(Integer.toString(enemy.getChips()));
            GW.getEnemyChips().repaint();
            GW.getPotValue().setText(Integer.toString(currentPotValue));
            GW.getPotValue().repaint();
            
        }
    }
    
    public void refreshPotInfo(){
        GW.getPotValue().setText(Integer.toString(currentPotValue));
        GW.getPotValue().repaint();
    }
    
    public void refreshChipsInfo(){
        GW.getPlayerChips().setText(Integer.toString(GW.HumanPlayer.getChips()));
        GW.getPlayerChips().repaint();
        GW.getEnemyChips().setText(Integer.toString(GW.EnemyPlayer.getChips()));
        GW.getEnemyChips().repaint();
    }
    
    public void hideAfterFoldElements(){
        GW.getDealerPlayer().setVisible(false);
        GW.getDealerEnemy().setVisible(false);
        
        GW.getBetSlider().setVisible(false);
        GW.getBetSpinner().setVisible(false);
        
        GW.getDecision1Button().setVisible(false);
        GW.getDecision2Button().setVisible(false);
        GW.getDecision3Button().setVisible(false);        
        
        GW.getFlopCard1().setVisible(false);
        GW.getFlopCard2().setVisible(false);
        GW.getFlopCard3().setVisible(false);
        GW.getTurnCard().setVisible(false);
        GW.getRiverCard().setVisible(false);
        
        GW.getPlayerCard1().setVisible(false);
        GW.getPlayerCard2().setVisible(false);        
        GW.getEnemyCard1().setVisible(false);
        GW.getEnemyCard2().setVisible(false);
        
        GW.getPotValue().setText("");        
    }
    
    // Getters...
    public boolean isPreFlop() {
        return isPreFlop;
    }
    public boolean isFlop() {
        return isFlop;
    }
    public boolean isTurn() {
        return isTurn;
    }
    public boolean isRiver() {
        return isRiver;
    }
    public DeckOfCards getDeckofCards() {
        return DeckofCards;
    }   
    
    // Action listener function...
    @Override
    public void actionPerformed(ActionEvent e) { 

        System.out.println("isPlayerMove " +  isPlayerMove);
       if(e.getSource() == GW.getDecision1Button()){
           System.out.println("Decision 1 - FOLD");

           if(isPlayerDealer){
                GW.EnemyPlayer.setChips(GW.EnemyPlayer.getChips() + currentPotValue);
           }
           
           if(!isPlayerDealer){
               GW.HumanPlayer.setChips(GW.HumanPlayer.getChips() + currentPotValue);
           }
            
           refreshChipsInfo();
           currentPotValue = 0;
           
           hideAfterFoldElements();
           //dealNewHand();
           
           isPlayerMove = !isPlayerMove;
           defaultMovePreparation(isPlayerMove);
       }
       
       if(e.getSource() == GW.getDecision2Button()){
           System.out.println("Decision 2 - CHECK");  
           
           if(isPlayerAllIn){
               if(!isPlayerMove){
                   // Enemy calls...
                   currentPotValue = currentPotValue + GW.getEnemyPlayer().getChips();
                   GW.getEnemyPlayer().setChips(0);
                   
                   refreshChipsInfo();
                   refreshPotInfo();
                   
                   allIn();
               }
           }
           if(isEnemyAllIn){
               if(isPlayerMove){
                   // Enemy calls...
                   currentPotValue = currentPotValue + GW.getHumanPlayer().getChips();
                   GW.getHumanPlayer().setChips(0);
                   
                   refreshChipsInfo();
                   refreshPotInfo();
                   
                   allIn();
               }
           }
           
           if(isRiver){
                if(isPlayerDealer && GW.HumanPlayer.getRiverDecision() == 2){
                GW.EnemyPlayer.setRiverDecision(2);
                System.out.println("E Checked 2nd on R");
                }
           
                if(isPlayerDealer && GW.HumanPlayer.getRiverDecision() == 0){
                    GW.HumanPlayer.setRiverDecision(2);
                    System.out.println("P Checked 1st on R");
                }           
           
                if(!isPlayerDealer && GW.EnemyPlayer.getRiverDecision() == 2){
                    GW.HumanPlayer.setRiverDecision(2);
                    System.out.println("P Checked 2nd on R");
                }
           
                if(!isPlayerDealer && GW.EnemyPlayer.getRiverDecision() == 0){
                    GW.EnemyPlayer.setRiverDecision(2);
                    System.out.println("E Checked 1st on R");
                }
           
                // If both players checked, deal flop...
                if(GW.HumanPlayer.getRiverDecision() == 2 && GW.EnemyPlayer.getRiverDecision() == 2){
                    showdown();                    
                }                
           }           
           
           if(isTurn){
                if(isPlayerDealer && GW.HumanPlayer.getTurnDecision() == 2){
                GW.EnemyPlayer.setTurnDecision(2);
                System.out.println("Enemy Checked 2nd");
                }
           
                if(isPlayerDealer && GW.HumanPlayer.getTurnDecision() == 0){
                    GW.HumanPlayer.setTurnDecision(2);
                    System.out.println("Player Checked 1st");
                }           
           
                if(!isPlayerDealer && GW.EnemyPlayer.getTurnDecision() == 2){
                    GW.HumanPlayer.setTurnDecision(2);
                    System.out.println("Player Checked 2nd");
                }
           
                if(!isPlayerDealer && GW.EnemyPlayer.getTurnDecision() == 0){
                    GW.EnemyPlayer.setTurnDecision(2);
                    System.out.println("Enemy Checked 1st");
                }
           
                // If both players checked, deal flop...
                if(GW.HumanPlayer.getTurnDecision() == 2 && GW.EnemyPlayer.getTurnDecision() == 2){
                    river();                    
                }                
           }           
           
           if(isFlop){
                if(isPlayerDealer && GW.HumanPlayer.getFlopDecision() == 2){
                GW.EnemyPlayer.setFlopDecision(2);
                System.out.println("Enemy Checked 2nd");
                }
           
                if(isPlayerDealer && GW.HumanPlayer.getFlopDecision() == 0){
                    GW.HumanPlayer.setFlopDecision(2);
                    System.out.println("Player Checked 1st");
                }           
           
                if(!isPlayerDealer && GW.EnemyPlayer.getFlopDecision() == 2){
                    GW.HumanPlayer.setFlopDecision(2);
                    System.out.println("Player Checked 2nd");
                }
           
                if(!isPlayerDealer && GW.EnemyPlayer.getFlopDecision() == 0){
                    GW.EnemyPlayer.setFlopDecision(2);
                    System.out.println("Enemy Checked 1st");
                }
           
                // If both players checked, deal flop...
                if(GW.HumanPlayer.getFlopDecision() == 2 && GW.EnemyPlayer.getFlopDecision() == 2){
                    turn();                    
                }                
           }
           
        if(isPreFlop){
            
            //  Player Calls on PreFlop | Order is important or it will not work, has to be ==2, ==0!
                // #1
                if(isPlayerDealer && GW.HumanPlayer.getPreflopDecision() == 2){
                    GW.EnemyPlayer.setPreflopDecision(2);
                    System.out.println("E Checked 2nd on PF");
                }
                // #2
                if(isPlayerDealer && GW.HumanPlayer.getPreflopDecision() == 0){
                    GW.HumanPlayer.setChips(GW.getHumanPlayer().getChips() - Blinds.getSmallBlind());
                    currentPotValue = currentPotValue + Blinds.getSmallBlind();
                    GW.HumanPlayer.setPreflopDecision(2);
                    
                    refreshPotInfo();
                    refreshChipsInfo();
                    System.out.println("P Checked 1st on PF");
                }
                // #3
                if(!isPlayerDealer && GW.EnemyPlayer.getPreflopDecision() == 2){
                    GW.HumanPlayer.setPreflopDecision(2);
                    System.out.println("P Checked 2nd on PF");
                }
                // #4
                if(!isPlayerDealer && GW.EnemyPlayer.getPreflopDecision() == 0){
                    GW.EnemyPlayer.setChips(GW.getEnemyPlayer().getChips() - Blinds.getSmallBlind());
                    currentPotValue = currentPotValue + Blinds.getSmallBlind();
                    GW.EnemyPlayer.setPreflopDecision(2);
                    
                    refreshPotInfo();
                    refreshChipsInfo();
                    System.out.println("E Checked 1st on PF");
                }

                //  Both players checked, deal flop...
                    if(GW.HumanPlayer.getPreflopDecision() == 2 && GW.EnemyPlayer.getPreflopDecision() == 2){
                    flop();                    
                }
           }   
           isPlayerMove = !isPlayerMove;
           defaultMovePreparation(isPlayerMove);
       }
  
       if(e.getSource() == GW.getDecision3Button()){
           System.out.println("Decision 3 - RAISE");
           
           int raisedValue = GW.getBetSlider().getValue();
           
           if(isPreFlop){
                if(isPlayerMove){                       
                    // if player raised before...
                    if(humanRaisedPF){
                        GW.HumanPlayer.setChips(GW.HumanPlayer.getChips() - raisedValue);
                        currentPotValue = currentPotValue + raisedValue;
                        GW.HumanPlayer.setPreflopDecision(3);
                    }
                    
                    // if player didnt raise before (blinds calculated into raise amount)
                    if(!humanRaisedPF){ 
                        if(isPlayerDealer){
                            GW.HumanPlayer.setChips(GW.HumanPlayer.getChips() - raisedValue + Blinds.getSmallBlind());
                            currentPotValue = currentPotValue + raisedValue - Blinds.getSmallBlind();
                        }                        
                        if(!isPlayerDealer){
                            GW.HumanPlayer.setChips(GW.HumanPlayer.getChips() - raisedValue + Blinds.getBigBlind());
                            currentPotValue = currentPotValue + raisedValue - Blinds.getBigBlind(); 
                        }
                        GW.HumanPlayer.setPreflopDecision(3);
                        humanRaisedPF = true;
                    }       

                    refreshPotInfo();
                    refreshChipsInfo();
                }
                
                if(!isPlayerMove){     
                    // if enemy raised before, calculate reraise...
                    if(enemyRaisedPF){
                        GW.EnemyPlayer.setChips(GW.EnemyPlayer.getChips() - raisedValue);
                        currentPotValue = currentPotValue + raisedValue;
                    }
                    
                    // Check if enemy had raised PF before (blinds calculated into Raise amount)      
                    if(!enemyRaisedPF){ 
                        if(isPlayerDealer){
                            GW.EnemyPlayer.setChips(GW.EnemyPlayer.getChips() - raisedValue + Blinds.getBigBlind());
                            currentPotValue = currentPotValue + raisedValue - Blinds.getBigBlind();                  
                        }                        
                        if(!isPlayerDealer) {
                            GW.EnemyPlayer.setChips(GW.EnemyPlayer.getChips() - raisedValue + Blinds.getSmallBlind());
                            currentPotValue = currentPotValue + raisedValue - Blinds.getSmallBlind();
                        }              
                        enemyRaisedPF = true;
                    } 
                    
                    GW.EnemyPlayer.setPreflopDecision(3);                   
 
                    refreshPotInfo();
                    refreshChipsInfo();
                    
                    System.err.println("Enemy raised " + raisedValue); 
                }         
            }
           
           if(isFlop){
                if(isPlayerMove){                                      
                    GW.HumanPlayer.setChips(GW.HumanPlayer.getChips() - raisedValue);
                    currentPotValue = currentPotValue + raisedValue;
                    GW.HumanPlayer.setFlopDecision(3);                 
                                                                       
                    GW.getDecision2Button().setText("Call " + currentPotValue);                    

                    refreshPotInfo();
                    refreshChipsInfo();
                    
                    System.err.println("Player raised " + raisedValue);
                }
                
                if(!isPlayerMove){     

                    GW.EnemyPlayer.setChips(GW.EnemyPlayer.getChips() - raisedValue);
                    currentPotValue = currentPotValue + raisedValue;
                    
                    
                    GW.EnemyPlayer.setFlopDecision(3);                   
                    GW.getDecision2Button().setText("Call " + currentPotValue);

                    refreshPotInfo();
                    refreshChipsInfo();
                    
                    System.err.println("Enemy raised " + raisedValue); 
                }         
            }
           
           if(isTurn){
                if(isPlayerMove){                                      
                    GW.HumanPlayer.setChips(GW.HumanPlayer.getChips() - raisedValue);
                    currentPotValue = currentPotValue + raisedValue;
                    GW.HumanPlayer.setTurnDecision(3);                 
                                                                       
                    GW.getDecision2Button().setText("Call " + currentPotValue);                    

                    refreshPotInfo();
                    refreshChipsInfo();
                    
                    System.err.println("Player raised " + raisedValue);
                }
                
                if(!isPlayerMove){     

                    GW.EnemyPlayer.setChips(GW.EnemyPlayer.getChips() - raisedValue);
                    currentPotValue = currentPotValue + raisedValue;
                    
                    
                    GW.EnemyPlayer.setTurnDecision(3);                   
                    GW.getDecision2Button().setText("Call " + currentPotValue);

                    refreshPotInfo();
                    refreshChipsInfo();
                    
                    System.err.println("Enemy raised " + raisedValue); 
                }         
            }
           
           if(isRiver){
                if(isPlayerMove){                                      
                    GW.HumanPlayer.setChips(GW.HumanPlayer.getChips() - raisedValue);
                    currentPotValue = currentPotValue + raisedValue;
                    GW.HumanPlayer.setRiverDecision(3);                 
                                                                       
                    GW.getDecision2Button().setText("Call " + currentPotValue);                    

                    refreshPotInfo();
                    refreshChipsInfo();
                    
                    System.err.println("Player raised " + raisedValue);
                }
                
                if(!isPlayerMove){     

                    GW.EnemyPlayer.setChips(GW.EnemyPlayer.getChips() - raisedValue);
                    currentPotValue = currentPotValue + raisedValue;                    
                    
                    GW.EnemyPlayer.setRiverDecision(3);                   
                    GW.getDecision2Button().setText("Call " + currentPotValue);

                    refreshPotInfo();
                    refreshChipsInfo();
                    
                    System.err.println("Enemy raised " + raisedValue); 
                }         
            }
            isAllin();
            isPlayerMove = !isPlayerMove;
            defaultMovePreparation(isPlayerMove);
            
            calculateRaise(raisedValue, isPlayerMove);
            
        }
       
    }
}
