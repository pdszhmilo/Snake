import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {


  public  Window(){
      this.setTitle("贪吃蛇小游戏");
      this.setSize(850, 600);
      this.setLocation(200, 200);
      this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      this.setResizable(false);
      this.setVisible(true);
      Container contentPane = this.getContentPane();
      Panel panel = new Panel();
      contentPane.add(panel);
      try {
          Thread.sleep(1000);
          repaint();
      } catch (InterruptedException e) {
      }

  }

}
