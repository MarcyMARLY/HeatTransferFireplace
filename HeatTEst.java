import java.awt.*;
import javax.swing.*;

class CellThread extends Thread{
  public int i;
  public int j;

  public CellThread(int i, int j){
    this.i = i;
    this.j = j;
  }
  public void run(){
    while(true){
      if(i==1 && j==1 ){
        continue;
      }
      if((i==1 && j==2 )||(i==2&& j==1)){
        continue;
      }
      if(i==1 && j==10){
        MainThread.boardTemp[i][j] = (MainThread.boardTemp[i][j-1]+ MainThread.boardTemp[i+1][j])/2;
      }
      if(j==1 && i==10){
        MainThread.boardTemp[i][j] = (MainThread.boardTemp[i][j+1]+ MainThread.boardTemp[i-1][j])/2;
      }
      if(i==1 && j!=10){
        MainThread.boardTemp[i][j] = (MainThread.boardTemp[i][j+1]+MainThread.boardTemp[i][j-1]+ MainThread.boardTemp[i+1][j])/3;
      }
      if(j==1 && i!=10){
        MainThread.boardTemp[i][j] = (MainThread.boardTemp[i][j+1]+MainThread.boardTemp[i-1][j]+ MainThread.boardTemp[i+1][j])/3;
      }
      MainThread.boardTemp[i][j] = (MainThread.boardTemp[i][j-1]+MainThread.boardTemp[i-1][j]+MainThread.boardTemp[i+1][j]+MainThread.boardTemp[i][j+1])/4;
    }
  }

}

class MainThread extends JPanel{
  public static CellThread[][] boardThread = new CellThread[100][100];
  public static double[][] boardTemp = new double[100][100];
  public int width ;
  public int heigth;
  public MainThread(){
    width = 10;
    heigth = 10;
    repaint();
  }
    static Thread t = new Thread(){
      public void run(){

          for(int i=1;i<10;i++){
            for(int j=1;j<10;j++){
              System.out.print(boardTemp[i][j]+" ");
            }
            System.out.println("\n");
          }

        }
      };
      public void paint(Graphics g){
        g.translate(0,0);
        for(int i=1;i<=10;i++){
          for(int j=1;j<=10;j++){
            if(boardTemp[i][j]>40) g.setColor(Color.RED);
            else if(boardTemp[i][j]>20) g.setColor(Color.ORANGE);
            else if(boardTemp[i][j]>7) g.setColor(Color.YELLOW);
            else if(boardTemp[i][j]>3) g.setColor(Color.GREEN);
            else if(boardTemp[i][j]>2) g.setColor(Color.BLUE);
            else if(boardTemp[i][j]>0) g.setColor(Color.BLACK);
            else g.setColor(Color.BLACK);
            g.fillRect(i*20, j*20, 20, 20);
          }
        }
      }

  public static void main(String args[]){

    for(int i=1;i<=10;i++){
      for(int j = 1;j<=10;j++){
        boardTemp[i][j]=1;
      }
    }
    for(int i=1;i<10;i++){
      for(int j=1;j<10;j++){
        System.out.print(boardTemp[i][j]+" ");
      }
      System.out.println("\n");
    }

    boardTemp[1][1] = 100;
    boardTemp[1][2] = 95;
    boardTemp[2][1] = 95;

    for(int i=1;i<=10;i++){
      for(int j=1;j<=10;j++){
        boardThread[i][j] = new CellThread(i,j);
        boardThread[i][j].start();

      }
    }
    MainThread te = new MainThread();
    JFrame frame = new JFrame("Fireplace");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(te, BorderLayout.CENTER);
    frame.setSize(240,240);
    frame.setVisible(true);

    t.start();

  }

}
