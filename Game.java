import java.util.Arrays;
public class Game {
     //マスが埋まっているか判定　埋まっていれば引き分け
     boolean isFull(Board[][]board){
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                if(board[i][j].isPut==false){
                    return false;
                }
            }
        }
        System.out.println("引き分けです");
        return true;
    }

    //勝利判定
    boolean isWin(Board[][]board, Player player){
        int []state=new int[4];
        //横の判定
        int i=0, j=1, k=0;
        for(k=0;k<4;k++){
            if(board[k][0].isPut){
                state=board[k][0].state;
                while(i<4){
                    while(j<4){
                        if(!board[k][j].isPut){
                            break;
                        }
                        if(state[i]!=board[k][j].state[i]){
                            break;
                        }
                        j++;
                    }
                    if(j==4){
                        System.out.println("Player "+player.name+" Wins!!");
                        return true;
                    }
                    i++;
                }
            }
        }
       
        //縦の判定
        i=0; j=1; k=0;
        for(k=0;k<4;k++){
            if(board[0][k].isPut){
                state=board[0][k].state;
                while(i<4){
                    while(j<4){
                        if(!board[j][k].isPut){
                            break;
                        }
                        if(state[i]!=board[j][k].state[i]){
                            break;
                        }
                        j++;
                    }
                    if(j==4){
                        System.out.println("Player "+player.name+" Wins!!");
                        return true;
                    }
                    i++;
                }
            }
        }

        //斜めの判定
        i=0; j=1; k=0;
        //右肩下がり
        if(board[0][0].isPut){
            state=board[0][0].state;
            while(i<4){
                while(j<4){
                    if(!board[j][j].isPut){
                        break;
                    }
                    if(state[i]!=board[j][j].state[i]){
                        break;
                    }
                    j++;
                }
                if(j==4){
                    System.out.println("Player "+player.name+" Wins!!");
                    return true;
                }
                i++;
            }
        }

        //右肩上がり
        if(board[0][3].isPut){
            state=board[0][3].state;
            while(i<4){
                while(j<4){
                    if(!board[j][3-j].isPut){
                        break;
                    }
                    if(state[i]!=board[j][3-j].state[i]){
                        break;
                    }
                    j++;
                }
                if(j==4){
                    System.out.println("Player "+player.name+" Wins!!");
                    return true;
                }
                i++;
            }
        }
        return false;
    }


    //どの様にな条件で勝ったか
    /*
    void howWin(){}
    */



    //クアルトをプレイ
    void Qualto(Player A, Player B, Board[][]board, Piece piece[]){
        System.out.println("Qualto Start!");
        System.out.println();
        Piece p=new Piece();
        while(true){
            p=B.SelectPiece(piece);
            A.PutPiece(p, board);
            if(isWin(board, A)){
                break;
            }
            if(isFull(board)){
                break;
            }
            p=A.SelectPiece(piece);
            B.PutPiece(p, board);
            if(isWin(board, B)){
                break;
            }
            if(isFull(board)){
                break;
            }
        }   
        System.out.println("Thank you for playing Qualto!");
    }
}
