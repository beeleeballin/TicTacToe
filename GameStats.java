public class GameStats
{
    int nlosses = 0;
    int nties = 0;
    int nwins = 0;
    public GameStats(){}
    public void recordLoss(){
        nlosses++;
    }
    public void recordTie(){
        nties++;
    }
    public void recordWin(){
        nwins++;
    }
    public String toString(){
        String s = "You have "+nwins+" wins, "+nties+" ties, and "+nlosses+" losses. Bye!";
        return s;
    }
}
