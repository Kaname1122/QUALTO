import java.util.Scanner;
import java.io.*;
import java.net.*;


public class Main {
    public static Connection P1 =new Connection();
    public static void main(String[]args){
        //4*4=16マスのBoardの初期化
        Board[][] board=new Board[4][4];
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
        
        try{
            P1.makeserver();
            //Player2人を初期化
            Scanner scanner=new Scanner(System.in);
            System.out.print("What's the name of first Player?: ");
            String str=scanner.next();
            Player A=new Player(str);


            P1.out.println("What's the name of second Player?: ");
            str = P1.in.readLine();
            System.out.println(str);
            Player B=new Player(str);
            B.isServer=false;
            System.out.println();
            //ゲームマスター的な
            Game G=new Game();
            //クアルトをプレイ
            G.Qualto(A, B, board, piece);   
        }catch(IOException e){
            System.out.println(e);
        } 
    }
}