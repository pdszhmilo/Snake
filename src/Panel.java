import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class Panel extends JPanel implements KeyListener, ActionListener {

   int length;
   int[] snakeX=new int[600];
   int[] snakeY=new int[600];
   int[] food=new int[2];
   Random random=new Random();
    String fx="R"; //蛇头方向
    Boolean isStart=false;
    Boolean dead=false;
    int score;
    int speed;
    Timer timer=new Timer(50,this);
   public  Panel(){
       init();
       //获取键盘 监听
       this.setFocusable(true);
       this.addKeyListener(this);
       timer.start();
   }
   //蛇的初始化
   public void  init(){
       length=3;
       score=0;
       snakeX[0]=100;snakeY[0]=100;
       snakeX[1]=75;snakeY[1]=100;
       snakeX[2]=50;snakeY[2]=100;
       food[0]=25+25*random.nextInt(32);
       food[1]=25+25*random.nextInt(22);
       food = FoodXY(food[0], food[1]);
       timer.setInitialDelay(speed);

   }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  //父类先清屏
          //画个背景
            ImgData.bg.paintIcon(this,g,0,0);

        //画蛇头
        switch (fx) {
            case "R":
                ImgData.right.paintIcon(this,g,snakeX[0],snakeY[0]);
                break;
            case "U":
                ImgData.up.paintIcon(this,g,snakeX[0],snakeY[0]);
                break;
            case "D":
                ImgData.down.paintIcon(this,g,snakeX[0],snakeY[0]);
                break;
            case "L":
                ImgData.left.paintIcon(this,g,snakeX[0],snakeY[0]);
                break;
        }

         for(int i=1;i<length;i++){
             ImgData.body_1.paintIcon(this,g,snakeX[i],snakeY[i]);  //循环打印蛇的身体
         }

      ImgData.food.paintIcon(this,g,food[0],food[1]);

        g.setColor(Color.white);
        g.setFont(new Font("微软雅黑",Font.BOLD,18));
        g.drawString("长度"+length,750,35);
        g.drawString("分数"+score,750,55);

        //游戏开始提醒
         if(isStart==false){
             g.setColor(Color.BLACK);
             g.setFont(new Font("微软雅黑",Font.BOLD,50));
             g.drawString("按下空格开始游戏",250,300);
         }
         //游戏失败提醒
        if(dead){
            g.setColor(Color.RED);
            g.setFont(new Font("微软雅黑",Font.BOLD,50));
            g.drawString("游戏失败,按下空格开始游戏",250,300);
        }
    }



    @Override
    public void keyPressed(KeyEvent e){
       int keyCode=e.getKeyCode();
       if(keyCode==KeyEvent.VK_SPACE ){
           if(dead){
               dead=false;
               init();
           }else{
               isStart=!isStart;
           }
           repaint();
       }
        if(keyCode==KeyEvent.VK_LEFT&& fx != "R"){
                fx="L";
        }
        if(keyCode==KeyEvent.VK_RIGHT&& fx != "L"){
               fx="R";
        }
        if(keyCode==KeyEvent.VK_DOWN&& fx != "U"){
               fx="D";
        }
        if(keyCode==KeyEvent.VK_UP&& fx != "D"){
              fx="U";
        }
}

    @Override
    public void keyReleased(KeyEvent e) {

    }
    @Override
    public void keyTyped(KeyEvent e) {
    }

    //定时器刷新帧率
    @Override
    public void actionPerformed(ActionEvent e) {
            if(isStart&&dead==false){

                for(int i=length;i>0;i--){
                    snakeX[i]=snakeX[i-1];
                    snakeY[i]=snakeY[i-1];
                }
                switch (fx) {
                    case "R":
                        snakeX[0]=snakeX[0]+25;
                        if(snakeX[0]>850) snakeX[0]=0;
                        break;
                    case "U":
                        snakeY[0]=snakeY[0]-25;
                        if(snakeY[0]<5) snakeY[0]=600;
                        break;
                    case "D":
                        snakeY[0]=snakeY[0]+25;
                        if(snakeY[0]>600) snakeY[0]=0;
                        break;
                    case "L":
                        snakeX[0]=snakeX[0]-25;
                        if(snakeX[0]<5) snakeX[0]=850;
                        break;
                }

                //吃掉食物，刷新食物
                if(snakeX[0]==food[0]&&snakeY[0]==food[1]){
                    length++;
                    food = FoodXY(food[0], food[1]);
                    score+=15;
                }
                //死亡
                for(int i=1;i<length;i++){
                    if(snakeX[0]==snakeX[i]&&snakeY[0]==snakeY[i]){
                        dead=true;
                    }
                }

                repaint();
            }
            timer.start();
    }



    public int[] FoodXY(int foodXX,int foodYY){

        for (int i = 0; i <length ; i++) {
            if (foodXX==snakeX[i]&&foodYY==snakeY[i]){
                foodXX = 25+25*random.nextInt(32);
                foodYY =25+25*random.nextInt(22);
                FoodXY(foodXX,foodYY); //如果实物的坐标与蛇身的坐标重合在重新生成调用自己
            }

        }
        return new int[] {foodXX,foodYY};
    }
}
