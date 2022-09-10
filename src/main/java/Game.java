public class Game {
   static final int boardWidth=5;
   static final  int boardHeight=5;

   static String[][] board = new String[boardWidth][boardHeight];

    public static void main(String[] args) {
        initBoard();
        draw();

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
