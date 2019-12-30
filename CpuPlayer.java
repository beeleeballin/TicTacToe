public class CpuPlayer extends APlayer
{
    public CpuPlayer(Game g, char player)
    {
        game = g;
        symbol = player;
    }
    public Move pickMove()
    {
        Move move = new Move(-1,-1);
        boolean next = true;
        for(int i=0; i<game.boardSize; i++){ //check horizontal
            for(int j=0; j<game.boardSize; j++){
                if(next){
                    if((game.board[i][0] != game.board[i][j])&&(game.board[i][j] == ' ')){
                        move.row = i;
                        move.col = j;
                        next = false;
                    }
                }else{
                    if(game.board[i][0] != game.board[i][j]) break;
                }
                if((j+1) == game.boardSize) return move;
            }
            next = true;
        }
        for(int i=0; i<game.boardSize; i++){ //check vertical
            for(int j=0; j<game.boardSize; j++){
                if(next){
                    if((game.board[0][i] != game.board[j][i])&&(game.board[j][i] == ' ')){
                        move.row = j;
                        move.col = i;
                        next = false;
                    }
                }else{
                    if(game.board[0][i] != game.board[j][i]) break;
                }
                if((j+1) == game.boardSize) return move;
            }
            next = true;
        }
        for(int i=0; i<game.boardSize; i++){ //check forward diagonal
            if(next){
                if((game.board[0][0] != game.board[i][i])&&(game.board[i][i] == ' ')){
                    move.row = i;
                    move.col = i;
                    next = false;
                }
            }else{
                if(game.board[0][0] != game.board[i][i]) break;
            }
            if((i+1) == game.boardSize) return move;
        }
        next = true;
        for(int i=0; i<game.boardSize; i++){ //check backward diagonal
            if(next){
                if((game.board[0][game.boardSize-1] != game.board[i][game.boardSize-(i+1)])&&(game.board[i][game.boardSize-(i+1)] == ' ')){
                    move.row = i;
                    move.row = game.boardSize-(i+1);
                    next = false;
                }
            }else{
                if(game.board[0][game.boardSize-1] != game.board[i][game.boardSize-(i+1)]) break;
            }
            if((i+1) == game.boardSize) return move;
        }
        do{
            move.row = (int)(Math.random()*game.boardSize);
            move.col = (int)(Math.random()*game.boardSize);
        }while(game.board[move.row][move.col] != ' ');
        return move;
    }
}
