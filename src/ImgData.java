import javax.swing.*;
import javax.xml.crypto.Data;
import java.net.URL;

public class ImgData {
    public static URL background= Data.class.getResource("/picture/背景.png");
    public  static URL headright=Data.class.getResource("/picture/headright.png");
    public  static URL headup=Data.class.getResource("/picture/headup.png");
    public  static URL headleft=Data.class.getResource("/picture/headleft.png");
    public  static URL headdown=Data.class.getResource("/picture/headdown.png");
    public  static URL apple=Data.class.getResource("/picture/苹果.png");
    public  static URL body1=Data.class.getResource("/picture/身体.png");
    public  static URL body2=Data.class.getResource("/picture/身体2.png");

    public static ImageIcon bg=new ImageIcon(background);
    public static ImageIcon up=new ImageIcon(headup);
    public static ImageIcon left=new ImageIcon(headleft);
    public static ImageIcon right=new ImageIcon(headright);
    public static ImageIcon down=new ImageIcon(headdown);
    public static ImageIcon food=new ImageIcon(apple);
    public static ImageIcon body_1=new ImageIcon(body1);
    public static ImageIcon body_2=new ImageIcon(body2);

}
