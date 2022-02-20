import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BasicGridButton extends JButton {

    int m;
    int n;

    boolean isMine =true;

    GridJLabel gridJLabel;

    public BasicGridButton(){
        initButton();
    }

    public BasicGridButton(GridJLabel gridJLabel,int m,int n){
        this();
        this.gridJLabel = gridJLabel;
        this.m=m;
        this.n=n;
    }

    public void initButton(){

        setLayout(null);
        setSize(16, 16);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getButton() == MouseEvent.BUTTON3&& !DataClass.isBomb[m][n]&&!DataClass.gameEnd&&!DataClass.isWin) {
                    DataClass.i[m][n] += 1;
                    if (DataClass.i[m][n]%3==1) DataClass.flagNumber++;
                    else if(DataClass.i[m][n]%3==2)DataClass.flagNumber--;
                }
                if (e.getButton() == MouseEvent.BUTTON1&& DataClass.i[m][n]%3!=1&&!DataClass.gameEnd&&!DataClass.isWin){
                    if(gridJLabel.mineLocation[m][n]){
                        DataClass.isBomb[m][n]=true;
                        DataClass.gameEnd=true;
                    } else if (!DataClass.numberDowm[m][n]){
                        blankSpace(m,n);
                        winner();
                    }
                }
                if (!DataClass.isStart){
                    DataClass.isStart=true;
                }
                gridJLabel.reset();
                gridJLabel.validate();
            }
        });
    }

    public void isCheat(){
        if(isMine && DataClass.isCheat) setIcon(new ImageIcon("image/hole.gif"));//开启作弊模式
        else setIcon(new ImageIcon(DataClass.mineGridIcon[DataClass.i[m][n]%3]));
    }

    public void bomb(){
        if(DataClass.gameEnd&& isMine) {
            if (DataClass.isBomb[m][n]) {
                setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("image/mine2.gif")));
            } else {
                setIcon(new ImageIcon("image/mine.gif"));
            }
        }
    }

    public void number(int i){
        if (DataClass.numberDowm[m][n])setIcon(new ImageIcon(DataClass.numberIcon[i]));
    }

    public void blankSpace(int m,int n){
        if (gridJLabel.mineLocation[m][n]) return;
        if (gridJLabel.countNumber[m][n]>0){
            DataClass.numberDowm[m][n]=true;
            DataClass.count++;
            return;
        }
        if (gridJLabel.countNumber[m][n]==0){
            if (DataClass.numberDowm[m][n]){
                for (int p = -1;p+m<DataClass.getMineRow()&&p<2;p++) {
                    if (p+m<0) continue;
                    for (int q = -1;q+n<DataClass.getMineRand()&&q<2;q++) {
                        if (q+n<0) continue;
                        if (p==0&&q==0) continue;
                        if (!DataClass.numberDowm[m+p][n+q])blankSpace(m+p,n+q);
                    }
                }
            } else {
                DataClass.numberDowm[m][n]=true;
                DataClass.count++;
                blankSpace(m,n);
            }

        }
    }

    public void winner(){
        int a=DataClass.getMineRow()*DataClass.getMineRand()-DataClass.getMineNums();
        if (a== DataClass.count){
            DataClass.isWin=true;
            new Win();
        }
    }
}
