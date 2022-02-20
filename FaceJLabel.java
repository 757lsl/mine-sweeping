import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class FaceJLabel extends JLabel {
    MineJFrame mineJFrame;

    public FaceJLabel(){
        init();
        setVisible(true);
    }

    public FaceJLabel(MineJFrame mineJFrame){
        this();
        this.mineJFrame=mineJFrame;
    }

    public void init(){
        setLayout(null);
        setBackground(new Color(191, 191, 191, 82));
        setLocation(2,2);
        setSize(DataClass.getMineRow()*16+12, 36);
        flagJLabel();
        countTime();
        timeJLabel();
        faceButton();
        setVisible(true);
    }

    public void reSet(){
        removeAll();
        init();
        revalidate();
        this.repaint();
    }

    public void faceButton(){
        int x=DataClass.getMineRow()*16+12;
        JButton face = new JButton();
        face.setIcon(new ImageIcon("image/face0.gif"));
        if (DataClass.gameEnd)face.setIcon(new ImageIcon("image/face3.gif"));
        else if (DataClass.isWin)face.setIcon(new ImageIcon("image/face4.gif"));
        face.setSize(26, 26);
        face.setLayout(null);
        face.setLocation((x-46)/2, 5);
        face.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getButton()==MouseEvent.BUTTON1) {
                    mineJFrame.initframe();
                    mineJFrame.reStart();
                    mineJFrame.revalidate();
                    repaint();
                    System.out.println(DataClass.isStart);
                }
            }
        });
        this.add(face);
    }

    public void flagJLabel(){
        int i=DataClass.getMineNums()-DataClass.flagNumber;
        int i1=0,i2,i3;
        if (i<0){
            i=-i;
            i1=10;
        } else if(i>98) {
            i=99;
        }

        i3=i%10;
        i2=(i-i3)/10;
        JLabel f100= new JLabel();
        f100.setLayout(null);
        f100.setLocation(2,7);
        f100.setSize(13,23);
        f100.setIcon(new ImageIcon(DataClass.timeAndFlagNumberIcon[i1]));
        f100.setVisible(true);
        add(f100);

        JLabel f010= new JLabel();
        f010.setLayout(null);
        f010.setLocation(15,7);
        f010.setSize(13,23);
        f010.setIcon(new ImageIcon(DataClass.timeAndFlagNumberIcon[i2]));
        f010.setVisible(true);
        add(f010);

        JLabel f001= new JLabel();
        f001.setLayout(null);
        f001.setLocation(28,7);
        f001.setSize(13,23);
        f001.setIcon(new ImageIcon(DataClass.timeAndFlagNumberIcon[i3]));
        f001.setVisible(true);
        add(f001);
    }

    public void countTime(){
        if (DataClass.isStart&&!DataClass.isCount){
            DataClass.isCount=true;
            TimerTask addCountTime = new TimerTask() {
                @Override
                public void run() {
                    if (DataClass.isWin||DataClass.gameEnd||!DataClass.isStart)cancel();
                    else if (DataClass.countTime<999){
                        DataClass.countTime++;

                    }
                    else {
                        DataClass.countTime=999;
                        cancel();
                    }
                    reSet();
                    updateUI();
                    System.out.println(DataClass.countTime);
                }
            };
            java.util.Timer timer = new Timer();
            timer.schedule(addCountTime,new Date(),1000L);
        }
    }

    public void timeJLabel(){
        int x=DataClass.getMineRow()*16+12;

        int i001=DataClass.countTime%10;
        int i010=(DataClass.countTime%100-i001)/10;
        int i100=(DataClass.countTime-DataClass.countTime%100)/100;
        JLabel t100= new JLabel();
        t100.setLayout(null);
        t100.setLocation(x-30,7);
        t100.setSize(13,23);
        t100.setIcon(new ImageIcon(DataClass.timeAndFlagNumberIcon[i001]));
        t100.setVisible(true);
        add(t100);

        JLabel t010= new JLabel();
        t010.setLayout(null);
        t010.setLocation(x-43,7);
        t010.setSize(13,23);
        t010.setIcon(new ImageIcon(DataClass.timeAndFlagNumberIcon[i010]));
        t010.setVisible(true);
        add(t010);

        JLabel t001= new JLabel();
        t001.setLayout(null);
        t001.setLocation(x-56,7);
        t001.setSize(13,23);
        t001.setIcon(new ImageIcon(DataClass.timeAndFlagNumberIcon[i100]));
        t001.setVisible(true);
        add(t001);
    }
}

