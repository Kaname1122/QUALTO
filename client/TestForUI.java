public class TestForUI {
    public static void outputPiece(Piece[] piece){
        for(int k=0;k<16;k++){
            System.out.print(piece[k].isUsed+",");
        }
        System.out.println();
    }
    public static void outputBoard(Board[][] board){
        for(int k=0;k<4;k++){
            for(int l=0;l<4;l++){
                System.out.print(board[k][l].isPut+",");
                }
                System.out.println();
        }
        System.out.println();
    }
}