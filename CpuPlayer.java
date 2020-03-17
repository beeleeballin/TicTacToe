public class CpuPlayer extends APlayer
{
    char SYMBOL_BLANK = ' ';
    public CpuPlayer(Game g, char player)
    {
        game = g;
        symbol = player;
    }
    public Move pickMove()
    {
        Move move = new Move(-1,-1);
        do{
            move.row = (int)(Math.random()*game.boardSize);
            move.col = (int)(Math.random()*game.boardSize);
        }while(game.board[move.row][move.col] != SYMBOL_BLANK);
        return move;
    }
}
