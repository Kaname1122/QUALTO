public class Board {
    boolean isPut;//Pieceが置かれてなければFalse, 置かれてたらTrue
    int[]state;//Board型のstate初期値は{-1, -1, -1, -1}として、BoardにPieceを置く際Pieceのstateを上書きする。

    //コンストラクタ
    public Board(){
        int[]state={-1, -1, -1, -1};
        this.isPut=false;
        this.state=state;
    }
    //Pieceを置く際そのstateを上書き。PlayerのPutPiece()に呼び出される
    void PutPiece(Piece piece){
        isPut=true;
        state=piece.state;
    }
    //勝利判定
    /*boolean isWin(Board[][]board){
        //横の判定
        //縦の判定
        //斜めの判定
    }
    */


    
}
