import javax.swing.JLabel;

public class DeckOfCards {

    private int[] deck;
    private String PlayerCard1suit;
    private String PlayerCard1rank;
    
    private String PlayerCard2suit;
    private String PlayerCard2rank;
    
    private String AICard1suit;
    private String AICard1rank;
    
    private String AICard2suit;
    private String AICard2rank;
    
    private String Flop1suit;
    private String Flop1rank;
    private String Flop2suit;
    private String Flop2rank;
    private String Flop3suit;
    private String Flop3rank;
    
    private String Turnsuit;
    private String Turnrank;
    
    private String Riversuit;
    private String Riverrank;
    
    DeckOfCards() {
   
        deck = new int[52];
        
        // "Spades", "Hearts", "Diamonds", "Clubs"
        String[] suits = {"s", "h", "d", "c"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "T", "J", "Q", "K"};

        for(int i = 0; i < deck.length; i++) deck[i] = i;

        for( int i = 0; i < deck.length; i++) {

                int index = (int)(Math.random() * deck.length);
                int temp = deck[i];
                deck[i] = deck[index];
                deck[index] = temp;
        }
//                for( int i = 0; i < 9; i++) {
//                String suit = suits[deck[i] / 13];
//                String rank = ranks[deck[i] % 13];
//                System.out.println("Card number " + deck[i] + ": " + rank + " of " + suit);
//                }
        
                PlayerCard1suit = suits[deck[0] / 13];
                PlayerCard1rank = ranks[deck[0] % 13];
        
                PlayerCard2suit = suits[deck[1] / 13];
                PlayerCard2rank = ranks[deck[1] % 13];
        
                AICard1suit = suits[deck[2] / 13];
                AICard1rank = ranks[deck[2] % 13];
                
                AICard2suit = suits[deck[3] / 13];
                AICard2rank = ranks[deck[3] % 13];
                
                Flop1suit = suits[deck[4] / 13];
                Flop1rank = ranks[deck[4] % 13];
                
                Flop2suit = suits[deck[5] / 13];
                Flop2rank = ranks[deck[5] % 13];
                
                Flop3suit = suits[deck[6] / 13];
                Flop3rank = ranks[deck[6] % 13];
                
                Turnsuit = suits[deck[7] / 13];
                Turnrank = ranks[deck[7] % 13];
                
                Riversuit = suits[deck[8] / 13];
                Riverrank = ranks[deck[8] % 13];
    }
    
    public void dealPF(JLabel PlayerCard1, JLabel PlayerCard2, JLabel AICard1, JLabel AICard2){
        
        PlayerCard1.setText(PlayerCard1rank + PlayerCard1suit);
        PlayerCard1.repaint();
        PlayerCard2.setText(PlayerCard2rank + PlayerCard2suit);
        PlayerCard2.repaint();
        
        AICard1.setText(AICard1rank + AICard1suit);
        AICard1.repaint();
        AICard2.setText(AICard2rank + AICard2suit);
        AICard2.repaint();
    }
    
    void dealF(JLabel Flop1, JLabel Flop2, JLabel Flop3){
        
        Flop1.setText(Flop1rank + Flop1suit);
        Flop2.setText(Flop2rank + Flop2suit);
        Flop3.setText(Flop3rank + Flop3suit);
        
    }
    
    void dealT(JLabel Turn){
        
        Turn.setText(Turnrank + Turnsuit);
    }
    
    void dealR(JLabel River){
        
        River.setText(Riverrank + Riversuit);
    }
}
    

