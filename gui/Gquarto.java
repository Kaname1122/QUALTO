import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
 
public class Gquarto extends JPanel {

    // コマオブジェクト
    Pieceobj[] piece = new Pieceobj[16];
    // 自分の番ならturn=1,相手の番ならturn=0
    boolean turn = true;
    // 選択中のコマスペースが空いているかどうか
    boolean isEmptySelectedPiece = true;
    // 選択したマスが空いているかどうか
    boolean[] isEmptySelectedBoard = {true, true, true, true, true, true, true, true, true, true, true, true, true, true, true, true};
    // 選択されたコマやマスのindex
    int selectedPiece = -1;
    int selectedBoard = -1;
    int[] selectedBoardXY = {-1, -1};
    // プレイヤーが何をすべきかのアナウンス
    String announce = "空いているマスを選択してコマを置いてください。";
    // マウスがクリックされた座標
    int mx, my;


    // コンストラクタ
    public Gquarto () {
        setPreferredSize(new Dimension(1100,720));
        addMouseListener(new MouseEv());
        for (int i = 0; i < 16; i++) {
            piece[i] = new Pieceobj(i);
        }
    }

    // 画面描画
    public void paintComponent(Graphics g) {

        // ボード背景
        g.setColor(Color.black);
        g.fillRect(0, 0, 720, 720);

        // 選択されたコマ表示場所
        g.setColor(new Color(0x00ff7f));
        g.fillRect(720, 0, 380, 100);
        g.setColor(Color.black);
        g.drawString("選択されたコマ", 730, 30);

        // コメント表示
        g.setColor(Color.white);
        g.fillRect(720, 100, 380, 70);
        g.setColor(Color.black);
        g.drawString(announce, 730, 130);

        // 残りのコマ置き場
        g.setColor(Color.lightGray);
        g.fillRect(720, 170, 380, 550);
        g.setColor(Color.black);
        g.drawString("残りのコマ", 730, 200);

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
        for (int i = 0; i < 16; i++) {
            g.drawImage(piece[i].img, piece[i].x, piece[i].y, piece[i].size, piece[i].size, null);
        }
    }

    // クリックのイベントクラス
    public class MouseEv implements MouseListener {
        public void mouseClicked(MouseEvent e) {
            if(turn){
                // クリックした位置を取得
                mx = e.getX();
                my = e.getY();

                // 残りのコマから選んでいるかどうか
                if (mx > 720 && mx < 1100 && my > 170 && my < 720 && isEmptySelectedPiece) {
                    selectedPiece = getSelectedPieceNumber(mx, my);
                    selectRemainedPiece(selectedPiece);
                }
                // ボード上かどうか
                else if (mx > 10 && mx < 710 && my > 10 && my < 710) {
                    selectedBoard = getSelectedBoardNumber(mx, my);
                    selectedBoardXY = selectedBoardXY();
                    moveSelectedPiece(selectedBoardXY);
                }
            }else{
                announce = "相手の番です。";
                return;
            }
        }

        public void mousePressed(MouseEvent e) {}
        public void mouseReleased(MouseEvent e) {}
        public void mouseEntered(MouseEvent e) {}
        public void mouseExited(MouseEvent e) {}
    }


    // 選択されたコマの番号を返す
    public int getSelectedPieceNumber(int mx, int my) {
        for (int i = 0; i < 16; i++) {
            if (mx > piece[i].x && mx < piece[i].x + piece[i].size && my > piece[i].y && my < piece[i].y + piece[i].size) {
                System.out.println("選択された駒の番号は" + i + "です。");
                return i;
            }
        }
        // コマの上じゃないときは-1を返す
        return -1;
    }

    // 選択されたマスの番号を返す
    public int getSelectedBoardNumber(int mx, int my) {
        for (int i = 0; i < 16; i++) {
            if (mx > 105 + (i%4)*130 && mx < 105 + (i%4)*130 + 120 && my > 105 + (i/4)*130 && my < 105 + (i/4)*130 + 120) {
                System.out.println("選択されたマスの番号は" + i + "です。");
                return i;
            }
        }
        // マスの上じゃないときは-1を返す
        return -1;
    }

    // 選択されたマスの縦横何番目かを返す
    public int[] selectedBoardXY(){
        int[] xy = {-1, -1};
        if(selectedBoard != -1){
            xy[0] = selectedBoard % 4;
            xy[1] = selectedBoard / 4;
        }
        return xy;
    }

    // 選択されたコマの番号を引数にとり、画面描画を行う
    public void selectRemainedPiece(int selectedPiece){
        this.selectedPiece = selectedPiece;
        if(selectedPiece != -1){
            if (piece[selectedPiece].isUsed == false) {
                piece[selectedPiece].isUsed = true;
                isEmptySelectedPiece = false;
                piece[selectedPiece].x = 720 + 150;
                piece[selectedPiece].y = 15;
                
                repaint();
            }
        }
    }

    // 選択されたマスの番号を引数にとり、画面描画を行う
    public void moveSelectedPiece(int[] xy){
        if (xy[0] == -1 || xy[1] == -1) {
            return;
        }
        int boardNumber = 4*xy[1] + xy[0];
        if (isEmptySelectedPiece == false && isEmptySelectedBoard[boardNumber]) {
            isEmptySelectedBoard[boardNumber] = false;
            piece[selectedPiece].x = 105 + xy[0] * 130 + (120-piece[selectedPiece].size)/2;
            piece[selectedPiece].y = 105 + xy[1] * 130 + (120-piece[selectedPiece].size)/2;
            isEmptySelectedPiece = true;

            repaint();
        }else{
            System.out.println("選択したマスにコマを置けません。");
        }
    }

    // ターンの設定
    public void setTurn(boolean turn){
        this.turn = turn;
    }

    // 画面表示
    public void StartGUI(){
        JFrame f = new JFrame();
        f.getContentPane().setLayout(new FlowLayout());
        f.getContentPane().add(new Gquarto());
        f.pack();
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);  
    }

}