public class APlayer
{
    protected Game game;
    protected char symbol;
    public APlayer() {
    }
    public APlayer(Game g, char player){
        game = g;
        symbol = player;
    }
    public char getSymbol(){
        return symbol;
    }
    public Move pickMove(){
        return null;
    }
}
