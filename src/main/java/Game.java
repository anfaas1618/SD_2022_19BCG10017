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
    static enum Direction { NORTH, EAST, WEST, SOUTH }
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
        while (playerOneScore!=boardWidth||playerTwoScore!=boardWidth)
        {
            input(playerOne);
        }

    }

    private static void input(String player) {
        String input = sc.next();
        String[] pawnAndDir = getDirAndPawnToMove(input);
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
