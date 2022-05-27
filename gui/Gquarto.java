import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
 
public class Gquarto extends JPanel {
    // コンストラクタ
    public Gquarto () {
        setPreferredSize(new Dimension(1100,720));
    }

    // 画面描画
    public void paintComponent(Graphics g) {
        // ボード背景
        g.setColor(Color.black);
        g.fillRect(0, 0, 720, 720);

        // 選択されたコマ表示場所
        g.setColor(new Color(0x00ff7f));
        g.fillRect(720, 0, 400, 100);
        g.setColor(Color.black);
        g.drawString("Selected Piece", 730, 30);

        // コメント表示
        g.setColor(Color.black);
        g.drawString("Put the selected piece on the empty space.", 730, 130);

        // 残りのコマ置き場
        g.setColor(Color.lightGray);
        g.fillRect(720, 170, 400, 550);
        g.setColor(Color.black);
        g.drawString("Remaining Pieces", 730, 200);

        // ボードの円
        Color wood = new Color(0xffe5a9);
        g.setColor(wood);
        ((Graphics2D)g).setStroke(new BasicStroke(5));
        g.drawOval(10, 10, 700, 700);

        // 円形マスの描画
        int roundsize = 120;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                g.setColor(wood);
                g.fillOval(105 + j * 130, 105 + i * 130, roundsize, roundsize);
            }
        }

        // コマ画像の表示
        Pieceobj p = new Pieceobj();
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    for (int l = 0; l < 2; l++) {
                        g.drawImage(p.img[i][j][k][l], 745 + (2*k+l)*90 - j*10, 250 + (2*i+j)*90 + i*20, 60 + j*25 , 60 + j*25, null);
                    }
                }
            }
        }
    }

    // クリックのイベントクラス
    public class MouseEv implements MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            // クリックした位置を取得
            int x = e.getX();
            int y = e.getY();
            
            // クリックした位置がボード内か判定
            if (x >= 10 && x <= 710 && y >= 10 && y <= 710) {
                // クリックした位置がボード内ならば、クリックした位置を取得
                int x2 = (x - 10) / 70;
                int y2 = (y - 10) / 70;
                // クリックした位置を取得
                int x3 = x2 + 1;
                int y3 = y2 + 1;
                // クリックした位置を表示
                System.out.println("x:" + x3 + " y:" + y3);
            }
        }
    }

    // コマオブジェクト
    class Pieceobj{
        Image[][][][] img = new Image[2][2][2][2];
        // コンストラクタ
        public Pieceobj(){
            try {
                img[0][0][0][0] = ImageIO.read(new File("../PieceImages/Circle_Small_White.png"));
                img[0][0][0][1] = ImageIO.read(new File("../PieceImages/Circle_Small_White_Hole.png"));
                img[0][0][1][0] = ImageIO.read(new File("../PieceImages/Circle_Small_Black.png"));
                img[0][0][1][1] = ImageIO.read(new File("../PieceImages/Circle_Small_Black_Hole.png"));
                img[0][1][0][0] = ImageIO.read(new File("../PieceImages/Circle_Big_White.png"));
                img[0][1][0][1] = ImageIO.read(new File("../PieceImages/Circle_Big_White_Hole.png"));
                img[0][1][1][0] = ImageIO.read(new File("../PieceImages/Circle_Big_Black.png"));
                img[0][1][1][1] = ImageIO.read(new File("../PieceImages/Circle_Big_Black_Hole.png"));
                img[1][0][0][0] = ImageIO.read(new File("../PieceImages/Square_Small_White.jpeg"));
                img[1][0][0][1] = ImageIO.read(new File("../PieceImages/Square_Small_White_Hole.jpeg"));
                img[1][0][1][0] = ImageIO.read(new File("../PieceImages/Square_Small_Black.jpeg"));
                img[1][0][1][1] = ImageIO.read(new File("../PieceImages/Square_Small_Black_Hole.jpeg"));
                img[1][1][0][0] = ImageIO.read(new File("../PieceImages/Square_Big_White.jpeg"));
                img[1][1][0][1] = ImageIO.read(new File("../PieceImages/Square_Big_White_Hole.jpeg"));
                img[1][1][1][0] = ImageIO.read(new File("../PieceImages/Square_Big_Black.jpeg"));
                img[1][1][1][1] = ImageIO.read(new File("../PieceImages/Square_Big_Black_Hole.jpeg"));
            } catch (Exception e) {
                System.out.println("画像の読み込みに失敗しました。");
                System.exit(0);
            }
        }
    }

    // 起動
    public static void main(String[] args) {
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new FlowLayout());
        f.getContentPane().add(new Gquarto());
        f.pack();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}