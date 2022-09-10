import java.util.*;
public class Game {
    static  class Point
    {
        int pointX;
        int pointY;

        public Point(int pointX, int pointY) {
            this.pointX=pointX;
            this.pointY=pointY;
        }
    }
    static class pointsAndPos
    {
        public pointsAndPos(Map<String, Point> pawnPosMap) {
            PawnPosMap = pawnPosMap;
        }

        Map<String, Point> PawnPosMap;
    }
   static final int boardWidth=5;
   static final  int boardHeight=5;

   static String[][] board = new String[boardWidth][boardHeight];
   static String[] teamOne= new String[boardWidth];
   static String[] teamTwo= new String[boardWidth];
   static String playerOne = "P1";
    static String playerTwo = "P2";
    static enum Direction { F, B, L, R }
    static int playerOneScore=0;
    static int playerTwoScore=0;
    static pointsAndPos playerOnePoints;
    static pointsAndPos playerTwoPoints;
   static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        playerOnePoints = new pointsAndPos(new HashMap<>());
        playerTwoPoints = new pointsAndPos(new HashMap<>());

        initBoard();
        draw();
        inputPlayerAndPlace(playerOne);
        draw();
        inputPlayerAndPlace(playerTwo);
        draw();
        int x;
        while (playerOneScore!=boardWidth||playerTwoScore!=boardWidth)
        {

           do { x= input(playerOne);
           }while (x!=1);
            draw();
            do { x= input(playerTwo);
            }while (x!=1);
            draw();
        }
        if (playerTwoScore==5) System.out.println("player 2 winner");
        else System.out.println("player one winner");

    }

    private static int input(String player) {
        String input = sc.next();
        String[] pawnAndDir = getDirAndPawnToMove(input);
        String pawn = pawnAndDir[0];
        Direction direction = Direction.valueOf(pawnAndDir[1]);
        if (player.equals(playerOne))
        {
            switch (direction)
            {
                case F ->{

                    Point p =  playerOnePoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX-1,p.pointY);
                    int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerOneScore++;
                    playerOnePoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
                case B ->{
                    Point p =  playerOnePoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX+1,p.pointY);
                     int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerOneScore++;
                    playerOnePoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
                case L ->{
                    Point p =  playerOnePoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX,p.pointY-1);
                     int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerOneScore++;
                    playerOnePoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
                case R ->{
                    Point p =  playerOnePoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX,p.pointY+1);
                     int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerOneScore++;
                    playerOnePoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
            }
        }
        else {
            switch (direction)
            {
                case F ->{
                    Point p =  playerTwoPoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX+1,p.pointY);
                     int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerTwoScore++;
                    playerTwoPoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
                case B ->{
                    Point p =  playerTwoPoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX-1,p.pointY);
                     int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerTwoScore++;
                    playerTwoPoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
                case L ->{
                    Point p =  playerTwoPoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX,p.pointY-1);
                     int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerTwoScore++;
                    playerTwoPoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
                case R ->{
                    Point p =  playerTwoPoints.PawnPosMap.get(pawn);
                    Point modified = new Point(p.pointX,p.pointY+1);
                     int x= checkCollision(pawn,modified,player);
                    if(x==0) return 0;
                    if (x==2) playerTwoScore++;
                    playerTwoPoints.PawnPosMap.put(pawn,modified);
                    board[p.pointX][p.pointY]="-";
                    board[modified.pointX][modified.pointY]=pawn;
                }
            }
        }
        return 1;
    }

    private static int checkCollision(String pawn, Point modified, String player) {

        if (modified.pointX==boardHeight||modified.pointX==-1||
            modified.pointY==boardWidth||modified.pointY==-1) {
            System.out.println("cannot move away from boundary");
            return 0;
        }
        if (board[modified.pointX][modified.pointY].equals("-")) return 1;
        if (player.equals(playerOne)&&board[modified.pointX][modified.pointY].charAt(0)=='B')
            return 2;
        if (player.equals(playerTwo)&&board[modified.pointX][modified.pointY].charAt(0)=='A')
            return 2;
        else {
            System.out.println("cannot bump into same player");
            return 0;
        }
    }


    private static String[] getDirAndPawnToMove(String input) {
        String[] pawnAndDir= new String[2];
        String [] inputSplit = input.split(":");
        pawnAndDir[0]= inputSplit[0];
        pawnAndDir[1]= inputSplit[1];
        return pawnAndDir;
    }

    private static void inputPlayerAndPlace(String player) {
        for (int i = 0; i < boardWidth; i++) {
            if (player.equals(playerOne))
            {
                teamOne[i]=sc.next();
                board[boardHeight-1][i] = teamOne[i];
                Point point = new Point(boardHeight-1,i);
                playerOnePoints.PawnPosMap.put(teamOne[i], point);
            }
            else
            {
                teamTwo[i]=sc.next();
                board[0][i] = teamTwo[i];
                Point point = new Point(0,i);
                playerTwoPoints.PawnPosMap.put(teamTwo[i], point);
            }
        }
    }

    private static void initBoard() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j <boardWidth ; j++) {
                board[i][j]="-";
            }
        }
    }

    private static void draw() {
        for (int i = 0; i < boardHeight; i++) {
            for (int j = 0; j <boardWidth ; j++) {
                System.out.print(board[i][j]+" ");
            }
            System.out.println();
        }
    }
}
