import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class GridJLabel extends JLabel {

    //二维数组，按钮的X，Y
    public  BasicGridButton[][] mineGrid = new BasicGridButton[DataClass.getMineRow()][DataClass.getMineRand()];
    public boolean[][] mineLocation = new boolean[DataClass.getMineRow()][DataClass.getMineRand()];
    //用随机数生成含有炸弹的按钮的X，Y
    {
        for(int i = 0; i< DataClass.getMineNums(); i++){
            Random row = new Random();
            int m = row.nextInt(DataClass.getMineRow());
            int n = row.nextInt(DataClass.getMineRand());
            if(i==0&&m<DataClass.getMineRow()&&n<DataClass.getMineRand()){
                mineLocation[m][n]=true;
            }else {//检查是否重复
                if( mineLocation[m][n]){
                    i=i-1;
                }
                else {
                    mineLocation[m][n]=true;
                }
            }
            mineLocation[m][n]=true;
        }
    }

    public int[][] countNumber = new int[DataClass.getMineRow()][DataClass.getMineRand()];
    //统计九宫格范围内的炸弹个数
    {
        for(int m = 0; m< DataClass.getMineRow(); m++){
            for (int n = 0; n< DataClass.getMineRand(); n++){
                int i=0;
                if(mineLocation[m][n]) {
                    continue;
                }
                for (int p =-1;p+m<DataClass.getMineRow()&&p<2;p++) {
                    if (p+m<0) continue;
                    for (int q =-1; q+n<DataClass.getMineRand()&&q<2;q++) {
                        if (q+n<0) continue;
                        if (mineLocation[p+m][q+n]) i++;
                    }
                }
                countNumber[m][n]=i;
            }
        }
    }

    FaceJLabel faceJLabel;

    public GridJLabel(){
        init();
    }

    public GridJLabel(FaceJLabel faceJLabel){
        this();
        this.faceJLabel = faceJLabel;
    }

    public void init(){
        setLayout(null);
        setBackground(new Color(191, 191, 191));
        setButton();
        repaint();
        setVisible(true);
        revalidate();
    }
//重载炸弹区域，不然炸弹格的状态的改变无法显示
    public void reset(){
        faceJLabel.reSet();
        removeAll();
        init();
    }
    //放置炸弹且统计3*3范围内的个数
    public void setButton(){
        for(int m = 0; m< DataClass.getMineRow(); m++){
            for (int n = 0; n< DataClass.getMineRand(); n++){
                //放置炸弹
                mineGrid[m][n] = new BasicGridButton(this, m, n);
                mineGrid[m][n].isMine = mineLocation[m][n];
                mineGrid[m][n].isCheat();
                mineGrid[m][n].bomb();
                mineGrid[m][n].number(countNumber[m][n]);
                mineGrid[m][n].setLocation(m * 16, n * 16 + 40);
                add(mineGrid[m][n]);
            }
        }
    }
}