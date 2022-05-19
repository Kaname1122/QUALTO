import java.util.Scanner;
import java.util.Arrays;
public class Player {
    String name;

    public Player(String name){
        this.name=name;
    }
    //コマを選んで渡す
    Piece SelectPiece(Piece[]piece){
        System.out.println("Player "+name+": コマを選択してください");
        while(true){
            int[]state=new int[4];
            Scanner scanner=new Scanner(System.in);

            //コマの条件決め
            System.out.print("Shape: C(circle) or S(sqare): ");
            String str0=scanner.next();
            if(str0.equals("C")){
                state[0]=0;
            }else if(str0.equals("S")){
                state[0]=1;
            }else state[0]=-1;

            System.out.print("Length: S(short) or L(long): ");
            String str1=scanner.next();
            if(str1.equals("S")){
                state[1]=0;
            }else if(str1.equals("L")){
                state[1]=1;
            }else state[1]=-1;

            System.out.print("Color: W(white) B(black): ");
            String str2=scanner.next();
            if(str2.equals("W")){
                state[2]=0;
            }else if(str2.equals("B")){
                state[2]=1;
            }else state[2]=-1;

            System.out.print("Hole :N(no) or Y(yes): ");
            String str3=scanner.next();
            if(str3.equals("N")){
                state[3]=0;
            }else if(str3.equals("Y")){
                state[3]=1;
            }else state[3]=-1;
            System.out.println();
    
            //条件に一致するコマを探す
            for(int i=0;i<16;i++){
                if(Arrays.equals(piece[i].state, state)){
                    if(!piece[i].isUsed){
                        piece[i].isUsed=true;
                        return piece[i];
                    }
                    else if(piece[i].isUsed){
                        System.out.println("そのコマは使用済みです。別のコマを選択してください");
                    }
                    if(i==15){
                        System.out.println("そのコマは存在しません。存在するコマを選択してください \n");
                        
                    }
                }
            }
            System.out.println();
        }
    }


    //コマを置く
    void PutPiece(Piece p, Board[][]b){
        System.out.println("Player "+name+": マスを選択してください");
        int i=0, j=0;
        while(true){
            Scanner scanner=new Scanner(System.in);
            System.out.print("行を選択してください：");
            String str1=scanner.next();
            System.out.print("列を選択してください：");
            String str2=scanner.next();
            System.out.println();

            i=Integer.valueOf(str1);
            j=Integer.valueOf(str2);

            if(b[i][j].isPut==true){//Boardが使用済みなら別のBoardを選択
                System.out.println("そのマスは使用済みです。別のマス選んでください");
            }else break;
            System.out.println();
        }
        b[i][j].PutPiece(p);//PieceのstateをBoardにコピーし、Boardを使用済みに
    }
}
