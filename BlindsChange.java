
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

public class BlindsChange {
    
    JLabel lvlLabel, valLabel;
    
    int blindTimeChange = 300;
    int blindLevel = 0;
    int smallBlindValues[] = {10,15,25,50,75,100,200,300,400,600,800,1000};
    int bigBlindValues[] = {20,30,50,100,150,200,400,600,800,1200,1600,2000};
    int anteValues[] = {0,0,0,0,0,0,25,25,50,50,75,75,100};

    // Calculates blinds according to blindLevel
    private int smallBlind = smallBlindValues[blindLevel];
    private int bigBlind = smallBlind*2;
    
    public BlindsChange(JLabel blindLevelLabel, JLabel blindValueLabel) {
        
        BlindTimer bT = new BlindTimer(blindTimeChange);
        
        int tempBlindlevel = blindLevel + 1;
        lvlLabel = blindLevelLabel;
        lvlLabel.setText("Blind Level: " + tempBlindlevel);
        valLabel = blindValueLabel;
        valLabel.setText(smallBlind + "/" + bigBlind);
        
        lvlLabel.repaint();
        valLabel.repaint();
    }

    /**
     * @return the smallBlind
     */
    public int getSmallBlind() {
        return smallBlind;
    }

    /**
     * @return the bigBlind
     */
    public int getBigBlind() {
        return bigBlind;
    }
    
    // Counts down toward changing blindLevel
    public class BlindTimer {
    Timer timer;
    
    public BlindTimer(int seconds){
        timer = new Timer();        
        timer.schedule(new RemindTask(seconds),0,1*1000);
    }
    
    class RemindTask extends TimerTask{
        int time;
                
        public RemindTask(int time){
            this.time = time;
        }
    
    public void run(){
        
        // If time is not equal zero the system decreases the time
        if(time!=0){
//            System.out.println("Time remaining: " + time + " SB: " + getSmallBlind() + " BB: " + getBigBlind() +  " level: " + blindLevel);
            time--;
        }
        // If time is equal zero the system increases the blindLevel, calculate new small and big blinds and starts a        new timer with higher values
        else{   
            timer.cancel();
            blindLevel++;
            smallBlind = smallBlindValues[blindLevel];
            bigBlind = smallBlind * 2;
            BlindTimer blTimer = new BlindTimer(blindTimeChange);
        }
    }
    }
    }
}