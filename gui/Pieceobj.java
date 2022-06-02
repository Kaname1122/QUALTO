import java.io.File;
import java.awt.Image;
import javax.imageio.ImageIO;

public class Pieceobj {
    Image img;
    int num;
    int x, y;
    int size;
    boolean isUsed = false;

    // コンストラクタ
    public Pieceobj(int number){
        num = number;
        x = 745 + (num % 4) * 90;
        y = 250 + (num / 4) * 90 + (num / 8) * 20;
        try {
            switch(number){
                case 0:
                    img = ImageIO.read(new File("../PieceImages/Circle_Small_White.png"));
                    size = 60;
                    break;
                case 1:
                    img = ImageIO.read(new File("../PieceImages/Circle_Small_White_Hole.png"));
                    size = 60;
                    break;
                case 2:
                    img = ImageIO.read(new File("../PieceImages/Circle_Small_Black.png"));
                    size = 60;
                    break;
                case 3:
                    img = ImageIO.read(new File("../PieceImages/Circle_Small_Black_Hole.png"));
                    size = 60;
                    break;
                case 4:
                    img = ImageIO.read(new File("../PieceImages/Circle_Big_White.png"));
                    x -= 10;
                    size = 85;
                    break;
                case 5:
                    img = ImageIO.read(new File("../PieceImages/Circle_Big_White_Hole.png"));
                    x -= 10;
                    size = 85;
                    break;
                case 6:
                    img = ImageIO.read(new File("../PieceImages/Circle_Big_Black.png"));
                    x -= 10;
                    size = 85;
                    break;
                case 7:
                    img = ImageIO.read(new File("../PieceImages/Circle_Big_Black_Hole.png"));
                    x -= 10;
                    size = 85;
                    break;
                case 8:
                    img = ImageIO.read(new File("../PieceImages/Square_Small_White.jpeg"));
                    size = 60;
                    break;
                case 9:
                    img = ImageIO.read(new File("../PieceImages/Square_Small_White_Hole.jpeg"));
                    size = 60;
                    break;
                case 10:
                    img = ImageIO.read(new File("../PieceImages/Square_Small_Black.jpeg"));
                    size = 60;
                    break;
                case 11:
                    img = ImageIO.read(new File("../PieceImages/Square_Small_Black_Hole.jpeg"));
                    size = 60;
                    break;
                case 12:
                    img = ImageIO.read(new File("../PieceImages/Square_Big_White.jpeg"));
                    x -= 10;
                    size = 85;
                    break;
                case 13:
                    img = ImageIO.read(new File("../PieceImages/Square_Big_White_Hole.jpeg"));
                    x -= 10;
                    size = 85;
                    break;
                case 14:
                    img = ImageIO.read(new File("../PieceImages/Square_Big_Black.jpeg"));
                    x -= 10;
                    size = 85;
                    break;
                case 15:
                    img = ImageIO.read(new File("../PieceImages/Square_Big_Black_Hole.jpeg"));
                    x -= 10;
                    size = 85;
                    break;
            }
        } catch (Exception e) {
            System.out.println("画像の読み込みに失敗しました。");
            System.exit(0);
        }
    }
}
