import java.io.*;
import java.util.Scanner;
public class Main {
    public static void main(String[]args){
        //通信
        Connection P2 =new Connection(); 
        Scanner scanner=new Scanner(System.in);
        try{
            P2.makeclient();
            String str = P2.in.readLine();
            System.out.println(str);
            String ans=scanner.next();
            P2.out.println(ans);
            System.out.println(str);
            mainLoop(P2);
        }catch(IOException e){
            System.out.println("eeee");
        }
        
    }
    public static void mainLoop(Connection P2){
        /////////////////////////////////////
        //ボード、ピースの作成
        Board[][] board =new Board[4][4];
        for(int i=0;i<4;i++){
            for(int j=0;j<4;j++){
                board[i][j]=new Board();
            }
        }
        //16個のPieceの初期化
        int[][]initPiece=new int[16][4];
        Piece piece[]=new Piece[16];
        for(int i=0;i<16;i++){
            if(i%2==1){
                initPiece[i][3]=1;
            }else{
                initPiece[i][3]=0;
            }
            if(i%4>=2){
                initPiece[i][2]=1;
            }else{
                initPiece[i][2]=0;
            }
            if(i%8>=4){
                initPiece[i][1]=1;
            }else{
                initPiece[i][1]=0;
            }
            if(i>7){
                initPiece[i][0]=1;
            }else{
                initPiece[i][0]=0;
            }
            piece[i]=new Piece(initPiece[i]);
        }
        /////////////////////////////////////
        String str;
        String pieceStr,boardStr;


        try{
            while(true){
                Scanner scanner=new Scanner(System.in);
                str=P2.in.readLine();
                if(str==null){
                    str=P2.in.readLine();
                    break;
                }else if (str.contains("#")){
                    str=str.replace("#","");
                    System.out.println(str);
                }else if (str.contains("$")){
                    System.out.println();
                    pieceStr=str.replace("$","");
                    int i=Integer.parseInt(pieceStr);
                    piece[i].isUsed=true;
                    TestForUI.outputPiece(piece);

                    System.out.println();
                }else if (str.contains("%")){
                    System.out.println();
                    boardStr=str.replace("%","");
                    int i=Integer.parseInt(boardStr.substring(0,1));
                    int j=Integer.parseInt(boardStr.substring(1,2));
                    board[i][j].isPut=true;
                    TestForUI.outputBoard(board);
                    System.out.println();
                }else{
                    System.out.println(str);
                    String ans=scanner.next();
                    P2.out.println(ans);
                }
            }
            
        }catch(IOException e){
            System.out.println("eeee");
        }
    }
}
