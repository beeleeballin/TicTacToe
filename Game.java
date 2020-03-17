import java.util.Scanner;
public class Game
{
    char[][] board;
    int boardSize; 
    APlayer[] players;
    char SYMBOL_BLANK = ' ';
    char SYMBOL_CPU = 'O';
    char SYMBOL_HUMAN = 'X';
    public Game(int bs){
        board = new char[bs][bs];
        boardSize = bs;
        players = new APlayer[2];
        players[0] = new HumanPlayer(this, SYMBOL_HUMAN);
        players[1] = new CpuPlayer(this, SYMBOL_CPU);
    }
    public int getBoardSize(){
        return boardSize;
    }
    public char isValidMove(Move move){ //checks whether the move is within bounds or unoccupied
        if ((move.row >= boardSize)||(move.row < 0)) return 'R';
        else if ((move.col >= boardSize)||(move.col < 0)) return 'C';
        else if (board[move.row][move.col] != SYMBOL_BLANK) return 'O';
        else return 'V';
    }
    protected boolean executeMove(Move move, char symbol){ //place the player symbol in the board matrix
        if(isValidMove(move) == 'V'){
            board[move.row][move.col] = symbol;
            return true;
        }else return false;
    }
    public char getGameStatus(){ //'X', 'O', 'T', '?'
        for(int i=0; i<boardSize; i++){ //check horizontal
            for(int j=1; j<boardSize; j++){
                if(board[i][0] != board[i][j]) break;
                if((j+1) == boardSize) return board[i][0];
            }
        }
        for(int i=0; i<boardSize; i++){ //check vertical
            for(int j=1; j<boardSize; j++){
                if(board[0][i] != board[j][i]) break;
                if((j+1) == boardSize) return board[0][i];
            }
        }
        for(int i=0; i<boardSize; i++){ //check forward diagonal
            if(board[0][0] != board[i][i]) break;
            if((i+1) == boardSize) return board[0][0];
        }
        for(int i=0; i<boardSize; i++){ //check backward diagonal
            if(board[0][boardSize-1] != board[i][boardSize-(i+1)]) break;
            if((i+1) == boardSize) return board[0][boardSize-1];
        }
        for(int i=0; i<boardSize; i++){ //check fullness
            for(int j=0; j<boardSize; j++){
                if(board[i][j] == SYMBOL_BLANK) return '?';
                if((i == boardSize-1)&&(j == boardSize-1)) return 'T';
            }
        }
        return '?';
    }
    public char playSingleGame(){
        char res = 'q';
        int turn = (int)(2*Math.random());
        if(turn % 2 == 1) System.out.println("CPU goes first");
        else System.out.println("PLAYER goes first");
        while (res != 'Q'){
            if(turn % 2 == 1) {
                if (executeMove(players[1].pickMove(),SYMBOL_CPU)){
                    System.out.println(toString()); 
                }
            }else{
                System.out.println("Please insert your move in the form of (row)(column), such as 'B6'");
                Move thismove = players[0].pickMove();
                if(thismove == null)res = 'Q';
                else{
                    executeMove(thismove,SYMBOL_HUMAN);
                    System.out.println(toString()); 
                }
            }
            switch (getGameStatus()){
                case 'X':
                    return 'H';
                case 'O':
                    return 'C';
                case 'T':
                    return 'T';
            }
            turn++;
        }
        return res;
    }
    protected void resetGame(){
        for(int i=0; i<boardSize; i++){
            for (int j=0; j<boardSize; j++){
                board[i][j] = SYMBOL_BLANK;
            }
        }
    }
    public String toString(){ //converts board to presentable string
        String boardString = ("");
        char rowLetter = 'A';
        for(int i=0; i<boardSize; i++){
            if(i == 0){ //store the column numberings
                for(int j=1; j<=boardSize; j++){
                    boardString = (boardString+"   "+j); //start with 1, not 0
                }
            }else{ //store the dividing lines of the board
                boardString = (boardString+"  "); //store the gap before the horizontal dashed line
                for(int j=1; j<=boardSize; j++){
                    boardString = (boardString+"---"); //store the horizontal dashed line
                    if(j != boardSize) boardString = (boardString+"|");//store the vertical lines of the board
                }
            }
            boardString = (boardString+"\n"+(char)(rowLetter+i)+" "); //store the row letters and content on a new line
            for(int j=1; j<=boardSize; j++){
                boardString = (boardString+" "+board[i][j-1]+" "); //store the symbol in position
                if(j != boardSize) boardString = (boardString+"|"); //store the vertical lines of the board
            }
            boardString = (boardString+"\n"); //store delineator Strings on a new line
        }
        return boardString;
    }
    // public static void testresult(String[] args){
        // Game now = new Game(3);
        // now.board[0][0]= now.SYMBOL_BLANK;
        // now.board[0][1]='O';
        // now.board[0][2]='O';
        // now.board[1][0]= now.SYMBOL_BLANK;
        // now.board[1][1]='X';
        // now.board[1][2]='X';
        // now.board[2][0]='O';
        // now.board[2][1]='O';
        // now.board[2][2]='X';
        
        // System.out.println(now.getGameStatus());
    // }
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Game now = new Game(3);
        GameStats gs = new GameStats();
        int bs = 0;
        boolean notquit = true;
        System.out.println("Hello! Welcome to Brian's Tic-Tac-Toe game");
        System.out.println("How large would you like your playing board?\nInsert a number, and we'll make a grid with those dimensions");
        try{
            bs = scanner.nextInt();
        }catch(java.util.InputMismatchException e){
            bs = 10;
        }
        if(bs >= 1 && bs <= 9) {
            System.out.println("Will do");
            now = new Game(bs); //boardSize value assigned
        }else{
            System.out.printf("Sorry, we cannot make a board that size.\n", bs, bs);
            System.out.println("We will make a 3x3 board instead");
        }
        while(notquit){
            now.resetGame();
            System.out.println(now.toString()); 
            char g = now.playSingleGame();
            if(g == 'H') {
                System.out.println("Congratulation on the win!");
                gs.recordWin();
            }else if(g == 'T') {
                System.out.println("You tied this game");
                gs.recordTie();
            }else if(g == 'C') {
                System.out.println("Oh no you lost...");
                gs.recordLoss();
            }else if(g == 'Q') notquit = false;
        }
        System.out.println(gs.toString());
    }
}
