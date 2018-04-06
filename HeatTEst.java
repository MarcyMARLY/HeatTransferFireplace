class CellThread extends Thread{
  public int i;
  public int j;

  public CellThread(int i, int j){
    this.i = i;
    this.j = j;
  }
  public void run(){
    while(true){
      MainThread.boardTemp[i][j] = (MainThread.boardTemp[i][j-1]+MainThread.boardTemp[i-1][j]+MainThread.boardTemp[i+1][j]+MainThread.boardTemp[i][j+1])/4;
    }
  }

}

class MainThread extends Thread{
  public static CellThread[][] boardThread = new CellThread[100][100];
  public static double[][] boardTemp = new double[100][100];

  public MainThread(){

  }
  public void run(){

      for(int i=1;i<10;i++){
        for(int j=1;j<10;j++){
          System.out.print(boardTemp[i][j]+" ");
        }
        System.out.println("\n");
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

    for(int i=2;i<10;i++){
      for(int j=2;j<10;j++){
        boardThread[i][j] = new CellThread(i,j);
        boardThread[i][j].start();

      }
    }
    MainThread t = new MainThread();
    t.start();
  }
}
