public class Player {
    private String nickName;
    private int chips;
    private int playerPreflopDecision = 0;
    private int playerFlopDecision = 0;
    private int playerTurnDecision = 0;
    private int playerRiverDecision = 0;

    public Player() {
        this.nickName = "defaultPlayerName";
        this.chips = 3000;
    }

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the Chips
     */
    public int getChips() {
        return chips;
    }

    /**
     * @param Chips the Chips to set
     */
    public void setChips(int Chips) {
        this.chips = Chips;
    }

    public int getPreflopDecision() {
        return playerPreflopDecision;
    }

    public void setPreflopDecision(int playerPreflopDecision) {
        this.playerPreflopDecision = playerPreflopDecision;
    }

    public int getFlopDecision() {
        return playerFlopDecision;
    }

    public void setFlopDecision(int playerFlopDecision) {
        this.playerFlopDecision = playerFlopDecision;
    }

    public int getTurnDecision() {
        return playerTurnDecision;
    }

    public void setTurnDecision(int playerTurnDecision) {
        this.playerTurnDecision = playerTurnDecision;
    }

    public int getRiverDecision() {
        return playerRiverDecision;
    }

    public void setRiverDecision(int playerRiverDecision) {
        this.playerRiverDecision = playerRiverDecision;
    }
    
    
    
    
}
