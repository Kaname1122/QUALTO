package qualto.main;

public class Piece{
    int[]state;
    boolean isUsed;//盤に置かれたか　Falese:置かれてない　True：置かれた
    
    //コンストラクタ
    public Piece(){
        int[]s={-1, -1, -1, -1}; 
        this.state=s;
        this.isUsed=false;
    }
    public Piece(int[]state){
        /*State
        0:Shape:  0:丸　　1:四角
        1:Length  0:短い　1:長い
        2:Color   0:白　　1:黒
        3:Hole    0:なし　1:あり
        →４要素の配列
        */
        this.state=state;
        this.isUsed=false;
    }
}