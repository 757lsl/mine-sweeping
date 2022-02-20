import javax.swing.*;
import java.awt.*;

public  class MineJFrame extends JFrame {

    ImageIcon icon = new ImageIcon("image/icon.gif");
    Image imageIcon = icon.getImage();

    DataClass dataClass = new DataClass();
    FaceJLabel faceJLabel = new FaceJLabel(this);
    GridJLabel gridJLabel = new GridJLabel(faceJLabel);

    public MineJFrame() {
        setFrame();
        mineMenu();
        initframe();
        setVisible(true);
    }

    /*
     * 加载GridJPanel，设置JFrame尺寸
     */
    public void initframe(){
        //dataClass.reStart();
        add(faceJLabel);
        add(gridJLabel);
        setMinimumSize(new Dimension(DataClass.getMineRow()*16+16, DataClass.getMineRand()*16+104));
        repaint();
        pack();
    }
    /*
     * 重置
     */
    public void reStart(){
        dataClass.reStart();
        remove(gridJLabel);
        gridJLabel = new GridJLabel(faceJLabel);
        add(gridJLabel);
        faceJLabel.reSet();
    }
/*
*实现菜单
*/
    protected void mineMenu(){
        JMenuBar menuBar=new JMenuBar();
        JMenu menu = new JMenu("菜单");
        menu.setMargin(new Insets(0,0,0,0));//设置字边距；
        menu.addSeparator();//添加分割线；
        JMenu difficulty = new JMenu("难度");
        JMenuItem primary = new JMenuItem("初级");
        JMenuItem intermediate = new JMenuItem("中级");
        JMenuItem senior = new JMenuItem("高级");
        difficulty.addSeparator();//添加分割线；
        JMenu cheat = new JMenu("作弊模式");
        JMenuItem cheatOpen = new JMenuItem("开");
        JMenuItem cheatClose = new JMenuItem("关");

        primary.addActionListener(e -> {
            DataClass.setMineGrid(9,9,10);
            initframe();
            reStart();
            revalidate();

        });

        intermediate.addActionListener(e -> {
            DataClass.setMineGrid(16,16,40);
            initframe();
            reStart();

            validate();
        });

        senior.addActionListener(e -> {
            DataClass.setMineGrid(32,16,99);
            initframe();
            reStart();

            validate();
        });

        cheatOpen.addActionListener(e -> {
            DataClass.isCheat=true;
            initframe();
            reStart();
            validate();
        });

        cheatClose.addActionListener(e -> {
            DataClass.isCheat=false;
            initframe();
            reStart();
            validate();
        });

        difficulty.add(primary);
        difficulty.add(intermediate);
        difficulty.add(senior);

        cheat.add(cheatOpen);
        cheat.add(cheatClose);

        menu.add(difficulty);
        menu.add(cheat);
        menuBar.add(menu);
        setJMenuBar(menuBar);
    }

    public void setFrame(){
        setTitle("扫雷");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(imageIcon);
        getContentPane().setBackground(new Color(191, 191, 191, 255));
        setResizable(false);
        setLocationRelativeTo(null);

        validate();
    }


}
