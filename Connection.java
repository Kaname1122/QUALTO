import java.io.*;
import java.net.*;
import java.util.Scanner;

public class Connection{
    public static int PORT;
    public static BufferedReader in;
    public static PrintWriter out;
    public void makeserver()
        throws IOException{
            PORT = 0000;
            ServerSocket s = new ServerSocket(PORT);
            System.out.println("Connected to player 2:"+s);
            try {
                Socket socket = s.accept();
                try {
                    System.out.println("Connection accpeted:"+socket);
                    in=
                        new BufferedReader(
                            new InputStreamReader(
                                socket.getInputStream()));
                    out =
                        new PrintWriter(
                            new BufferedWriter(
                                new OutputStreamWriter(
                                    socket.getOutputStream())),true);
                    
                } finally {
                    return;
                }
            }finally {
                    s.close();
            }
        }
    public static void makeclient()
        throws IOException {
            Scanner scanner=new Scanner(System.in);
            System.out.println("input PORT number");
            String port = scanner.next();
            PORT=Integer.parseInt(port);
            InetAddress addr = InetAddress.getByName("localhost");
            System.out.println("addr=" + addr);
            Socket socket = new Socket(addr, PORT);
            try {
                System.out.println("socket=" + socket);
                in = 
                    new BufferedReader(
                        new InputStreamReader(
                            socket.getInputStream()));
                out = 
                    new PrintWriter(
                        new BufferedWriter(
                            new OutputStreamWriter(
                                socket.getOutputStream())),true);
                //操作
            } finally {
                return;
            }
        }

}