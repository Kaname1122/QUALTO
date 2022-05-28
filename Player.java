import java.util.Scanner;
import java.util.Arrays;
import java.io.*;
import java.net.*;
public class Player {
    String name;
    boolean isServer;

    public Player(String name){
        this.name=name;
        this.isServer=true;
    }
    //コマを選んで渡す
    Piece SelectPiece(Piece[]piece){
        announce("Player "+name+": コマを選択してください");
        while(true){
            int[]state=new int[4];
            Scanner scanner=new Scanner(System.in);

            //コマの条件決め
            String str0=send(this,"Shape: C(circle) or S(sqare): ",false);
            if(str0.equals("C")){
                state[0]=0;
            }else if(str0.equals("S")){
                state[0]=1;
            }else state[0]=-1;

            String str1=send(this,"Length: S(short) or L(long): ",false);
            if(str1.equals("S")){
                state[1]=0;
            }else if(str1.equals("L")){
                state[1]=1;
            }else state[1]=-1;

            
            String str2=send(this,"Color: W(white) B(black): ",false);
            if(str2.equals("W")){
                state[2]=0;
            }else if(str2.equals("B")){
                state[2]=1;
            }else state[2]=-1;


            String str3=send(this,"Hole :N(no) or Y(yes): ",false);
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
                        sendPiece(i);//sending piece information to client
                        return piece[i];
                    }
                    else if(piece[i].isUsed){
                        announce("そのコマは使用済みです。別のコマを選択してください");
                    }
                    if(i==15){
                        announce("そのコマは存在しません。存在するコマを選択してください。");
                        
                    }
                }
            }
            System.out.println();
        }
    }


    //コマを置く
    void PutPiece(Piece p, Board[][]b){
        announce("Player "+name+" : マスを選択してください");
        int i=0, j=0;
        while(true){
            Scanner scanner=new Scanner(System.in);
            String str1=send(this,"行を選択してください：",true);
            String str2=send(this,"列を選択してください：",true);
            System.out.println();

            i=Integer.valueOf(str1);
            j=Integer.valueOf(str2);

            if(b[i][j].isPut==true){//Boardが使用済みなら別のBoardを選択
                announce("そのマスは使用済みです。別のマス選んでください");
            }else break;
            System.out.println();
        }
        b[i][j].PutPiece(p);//PieceのstateをBoardにコピーし、Boardを使用済みに
        sendBoard(i,j);//sending board information to client
    }

    public static String send(Player p,String letter,boolean put){
        try{   
            if(put==true){
                if(p.isServer!=true){
                    System.out.println("        "+p.name+" "+letter);
                    Main.P1.out.println(letter);
                    String str=Main.P1.in.readLine();
                    System.out.println("        "+p.name+" chose "+str);
                    return str;
                }
                else{
                    System.out.println(letter);
                    Main.P1.out.println("        "+p.name+letter+"#");
                    Scanner scanner=new Scanner(System.in);
                    String str=scanner.next();
                    Main.P1.out.println("        "+p.name+" chose "+str+"#");
                    return str;
                }
            }else{
                if(p.isServer==true){
                    System.out.println(letter);
                    Main.P1.out.println("        "+p.name+letter+"#");
                    Scanner scanner=new Scanner(System.in);
                    String str=scanner.next();
                    Main.P1.out.println("        "+p.name+" chose "+str+"#");
                    return str;
                }
                else{
                    System.out.println("        "+p.name+" "+letter);
                    Main.P1.out.println(letter);
                    String str=Main.P1.in.readLine();
                    System.out.println("        "+p.name+" chose "+str);
                    return str;
                }
            }
        }catch(IOException e){
            System.out.println(e);
            return "error";
        }
    }
    public static void announce(String letter){
        System.out.println(letter);
        Main.P1.out.println(letter+"#");
        return;
    }
    public static void sendPiece(int i){
        String str=String.valueOf(i);
        Main.P1.out.println("$"+str);
    }
    public static void sendBoard(int i, int j){
        String str=String.valueOf(i)+String.valueOf(j);
        Main.P1.out.println("%"+str);
    }
}
