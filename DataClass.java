import java.awt.*;

public class DataClass {

    private static int mineRow=9;
    private static int mineRand=9;
    private static int mineNums=10;
    public static int countTime=0;
    public static int count=0;//用于记录已经点开的数字格数
    public static int flagNumber=0;

    public static int[][] i=new int[32][16];//用于储存插旗和问号的状态，随便命名的；

    public static boolean isStart = false;
    public static boolean gameEnd = false;
    public static boolean isWin = false;
    public static boolean isCheat = false;
    public static boolean isCount = false;
    public static boolean[][] isBomb=new boolean[32][16];
    public static boolean[][] numberDowm=new boolean[32][16];

    public static Image[] mineGridIcon = new Image[]{
            Toolkit.getDefaultToolkit().getImage("image/blank.gif"),
            Toolkit.getDefaultToolkit().getImage("image/flag.gif"),
            Toolkit.getDefaultToolkit().getImage("image/ask.gif")

    };

    public static Image[] numberIcon = new Image[9];
    static {
        for(int i=0;i<9;i++){
            numberIcon[i]=Toolkit.getDefaultToolkit().getImage("image/"+i+".gif");
        }
    }

    public static Image[] timeAndFlagNumberIcon = new Image[11];
    static {
        for(int i=0;i<10;i++){
            timeAndFlagNumberIcon[i]=Toolkit.getDefaultToolkit().getImage("image/d"+i+".gif");
        }
        timeAndFlagNumberIcon[10]=Toolkit.getDefaultToolkit().getImage("image/d10.gif");
    }

    public static void setMineGrid(int row, int rand, int nums){
        mineRow=row;
        mineRand=rand;
        mineNums=nums;
    }
    public static int getMineRow(){
        return mineRow;
    }
    public static int getMineRand(){
        return mineRand;
    }
    public static int getMineNums(){
        return mineNums;
    }
    //重置
    public void reStart(){
        flagNumber=0;
        isStart = false;
        count=0;
        countTime=0;
        i=new int[32][16];
        gameEnd=false;
        isWin = false;
        isCount = false;
        isBomb=new boolean[32][16];
        numberDowm=new boolean[32][16];
    }
}
