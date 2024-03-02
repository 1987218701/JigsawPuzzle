import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameJFrame extends JFrame implements KeyListener, ActionListener {
    int[][] data = new int[4][4];
    int x = 0, y = 0;// 记录空白格的坐标
    String path = "pic\\animal\\animal1\\";
    int stepNumber = 0;
    int animalCount = 4;// 动物图片数量
    int girlCount = 4;// 美女图片数量
    int sceneryCount = 0;// 风景图片数量
    JMenu functionJMenu = new JMenu("功能选项");
    JMenu aboutJMenu = new JMenu("关于我们");
    JMenu changePicture = new JMenu("更换图片");

    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登陆");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem accountItem = new JMenuItem("公众号");

    JMenuItem beautyItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sceneryItem = new JMenuItem("风景");

    public GameJFrame() {
        //界面初始化
        initJFrame();
        //初始化菜单
        initJMenuBar();
        //初始化数据
        initData();
        //初始化图片
        initImage();
        //为窗体添加键盘事件
        this.addKeyListener(this);

        this.setVisible(true);//显示窗体
    }

    private void initData() {
        int[] arr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            int index = r.nextInt(arr.length);
            int temp = arr[i];
            arr[i] = arr[index];
            arr[index] = temp;
        }
        int index = 0;
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (arr[index] == 0) {
                    x = i;
                    y = j;
                }
                data[i][j] = arr[index];
                index++;
            }
        }
    }

    private void initImage() {
        this.getContentPane().removeAll();
        //步数组件
        JLabel step = new JLabel("步数：" + stepNumber);
        step.setBounds(50, 20, 100, 30);
        this.getContentPane().add(step);

        if (victory()) { //胜利显示胜利图片
            JLabel winJLabel = new JLabel(new ImageIcon("pic\\win.png"));
            winJLabel.setBounds(140, 170, 307, 294);
            this.getContentPane().add(winJLabel);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                ImageIcon imageIcon = new ImageIcon(path + data[i][j] + ".jpg");
                JLabel jLabel = new JLabel(imageIcon);
                jLabel.setBounds(105 * j + 84, 105 * i + 134, 105, 105);
                jLabel.setBorder(new BevelBorder(1));
                this.getContentPane().add(jLabel);
            }
        }
        // 添加背景图片
        ImageIcon background = new ImageIcon("pic\\background.png");
        JLabel backgroundJLabel = new JLabel(background);
        backgroundJLabel.setBounds(4, 38, 567, 583);
        this.getContentPane().add(backgroundJLabel);
        // 刷新界面
        this.getContentPane().repaint();
    }

    private void initJMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();


        replayItem.addActionListener(this);// 重新游戏
        reLoginItem.addActionListener(this); // 重新登陆
        closeItem.addActionListener(this); // 关闭游戏
        accountItem.addActionListener(this);// 公众号
        beautyItem.addActionListener(this);// 美女
        animalItem.addActionListener(this);// 动物
        sceneryItem.addActionListener(this);// 风景


        functionJMenu.add(changePicture);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        aboutJMenu.add(accountItem);

        changePicture.add(beautyItem);
        changePicture.add(animalItem);
        changePicture.add(sceneryItem);

        jMenuBar.add(functionJMenu);
        jMenuBar.add(aboutJMenu);

        this.setJMenuBar(jMenuBar);//将菜单添加到界面
    }

    private void initJFrame() {
        this.setSize(603, 680);//设置宽高
        this.setAlwaysOnTop(true);// 显示在最上方
        this.setTitle("拼图单机版 v1.0");// 设置标题
        this.setResizable(false);// 最大化禁用
        this.setLocationRelativeTo(null);//默认居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);// 关闭模式
        this.setLayout(null);// 取消默认居中方式
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 65) {
            this.getContentPane().removeAll();
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(84, 134, 420, 420);
            JLabel background = new JLabel(new ImageIcon("pic\\background.png"));
            background.setBounds(4, 38, 567, 583);
            this.getContentPane().add(all);
            this.getContentPane().add(background);
            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (victory()) {
            return;
        }
        int code = e.getKeyCode();
        if (code == 37 && y < 3) {// 左
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            stepNumber++;
            initImage();
        } else if (code == 38 && x < 3) { // 上
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            stepNumber++;
            initImage();
        } else if (code == 39 && y > 0) { // 右
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            stepNumber++;
            initImage();
        } else if (code == 40 && x > 0) { // 下
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            stepNumber++;
            initImage();
        } else if (code == 65) {
            initImage();
        } else if (code == 87) {
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            x = 3;
            y = 3;
            initImage();
        }
    }

    public boolean victory() {
        int[][] win = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 0}
        };
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != win[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public void picFun(int count, String fileName) {
        Random r = new Random();
        if (count > 0) {
            System.out.println("切换图片");
            int index = r.nextInt(count) + 1;
            path = "pic\\" + fileName + "\\" + fileName + index + "\\";
            stepNumber = 0;
            initData();
            initImage();
        } else {
            System.out.println("该选项下没有图片");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == reLoginItem) {
            System.out.println("重新登陆");
            this.setVisible(false);
            new LoginJFrame();
        } else if (obj == replayItem) {
            stepNumber = 0;
            initData();
            initImage();
            System.out.println("重新游戏");
        } else if (obj == closeItem) {
            System.out.println("关闭游戏");
            System.exit(0);
        } else if (obj == animalItem) {
            picFun(animalCount, "animal");
        } else if (obj == beautyItem) {
            picFun(girlCount, "girl");
        } else if (obj == sceneryItem) {
            picFun(sceneryCount, "scenery");
        }else if(obj == accountItem){
            System.out.println("公众号");
        }
    }
}
