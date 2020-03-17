import java.util.Scanner;
public class HumanPlayer extends APlayer
{
    char SYMBOL_BLANK = ' ';
    public HumanPlayer(Game g, char player)
    {
        game = g;
        symbol = player;
    }
    public Move pickMove(){
        Scanner scanner = new Scanner (System.in);
        Move move = new Move(-1,-1);
        do{
            String rc = scanner.next();
            if(rc.compareTo("quit") == 0){
                System.out.println("Thanks for playing.");
                return null;
            }
            try{
                rc = rc.toUpperCase();
                char rowchar = rc.charAt(0);
                int row = rowchar - 'A';
                int col = rc.charAt(1) - '1';
            }catch(java.lang.StringIndexOutOfBoundsException e){
                System.out.println("Not a valid input. Try again");
                continue;
            }
            rc = rc.toUpperCase();
            char rowchar = rc.charAt(0);
            int row = rowchar - 'A';
            int col = rc.charAt(1) - '1';
            if((row >= game.boardSize)||(row < 0)||(col >= game.boardSize)||(col < 0)){
                System.out.println("The move is out of bounds. Please choose another one.");
                continue;
            }else if(game.board[row][col] != SYMBOL_BLANK){
                System.out.println("The position is already taken. Please choose another one.");
                continue;
            }else{
                move.row = row;
                move.col = col;
                return move;
            }
        }while(true);
    }
}
