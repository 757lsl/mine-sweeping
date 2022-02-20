import javax.swing.*;
import java.awt.*;

public class Win extends JFrame {
    ImageIcon icon = new ImageIcon("image/Fireworks.png");
    Image imageIcon = icon.getImage();

    public Win() {
        this.setTitle("赢了");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setIconImage(imageIcon);

        this.getContentPane().setBackground(new Color(255, 255, 255, 255));
        JLabel hh = new JLabel("   >.< 赢了!");
        this.add(hh);
        this.setMinimumSize(new Dimension(66, 66));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(DataClass.isWin);

        this.pack();
    }
}
