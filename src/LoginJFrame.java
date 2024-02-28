import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class LoginJFrame extends JFrame implements ActionListener {
    static ArrayList<User> users = new ArrayList();

    static {
        User root1 = new User("1987218701", "zc123");
        User root2 = new User("13187043459", "123456");
        users.add(root1);
        users.add(root2);
    }

    Font font = new Font("微软雅黑", Font.BOLD, 20);
    Font inputFont = new Font("微软雅黑", Font.PLAIN, 16);
    JButton logoIn;// 登录按钮
    JTextField usernameInput;// 用户名
    JPasswordField pwdInput;// 密码
    JTextField codeInput;// 验证码
    JLabel correctCode; // 生成的验证码
    String eyePath; // 小眼睛路径

    int x = 90, y = 130;

    public LoginJFrame() {
        initLoginJFrame();
        initLoginImage();

        this.setVisible(true);
    }

    private void initLoginImage() {
        this.getContentPane().removeAll();
        // 添加用户名
        JLabel username = new JLabel("用户名");
        username.setBounds(x, y, 60, 20);
        username.setFont(font);
        this.getContentPane().add(username);
        username.requestFocus();// 默认获得焦点
        // 添加用户名输入框
        usernameInput = new JTextField();
        usernameInput.setBounds(x + 80, y, 200, 30);
        usernameInput.setHorizontalAlignment(JTextField.LEFT);//设置水平居中模式
        usernameInput.setFont(inputFont);
        this.getContentPane().add(usernameInput);
        // 密码
        JLabel password = new JLabel("密码");
        password.setBounds(x, y + 50, 60, 20);
        password.setHorizontalAlignment(JTextField.CENTER);
        password.setFont(font);
        this.getContentPane().add(password);
        // 密码输入框
        pwdInput = new JPasswordField();
        pwdInput.setBounds(x + 80, y + 50, 200, 30);
        usernameInput.setHorizontalAlignment(JTextField.LEFT);//设置水平居中模式
        pwdInput.setFont(inputFont);
        this.getContentPane().add(pwdInput);
        //小眼睛
        eyePath = "pic\\openEyes.png";
        JLabel eyes = new JLabel(new ImageIcon(eyePath));
        eyes.setBounds(x + 282, y + 55, 28, 20);
        this.getContentPane().add(eyes);
        eyes.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                char[] pwdArr = pwdInput.getPassword();
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < pwdArr.length; i++) {
                    sb.append(pwdArr[i]);
                }
                String password = sb.toString();
                System.out.println(password);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        // 验证码
        JLabel code = new JLabel("验证码");
        code.setBounds(x, y + 100, 60, 20);
        code.setHorizontalAlignment(JTextField.CENTER);
        code.setFont(font);
        this.getContentPane().add(code);
        // 验证码输入框
        codeInput = new JTextField();
        codeInput.setBounds(x + 80, y + 100, 100, 30);
        codeInput.setHorizontalAlignment(JTextField.LEFT);//设置水平居中模式
        codeInput.setFont(inputFont);
        this.getContentPane().add(codeInput);
        //验证码生成
        correctCode = new JLabel(GameUtil.createCode());
        correctCode.setBounds(x + 200, y + 100, 100, 30);
        correctCode.setFont(new Font("宋体", Font.ITALIC, 20));
        correctCode.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                correctCode.setText(GameUtil.createCode());
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.getContentPane().add(correctCode);
        // 登录按钮
        logoIn = new JButton(new ImageIcon("pic\\login.png"));
        logoIn.setBounds(169, 290, 150, 72);
        this.getContentPane().add(logoIn);
        logoIn.addActionListener(this);
        // 注册按钮
        JButton signIn = new JButton(new ImageIcon("pic\\signIn.png"));
        signIn.setBounds(330, 330, 100, 32);
        this.getContentPane().add(signIn);
        // 添加背景图片
        JLabel bcg = new JLabel(new ImageIcon("pic\\loginBCG.png"));
        bcg.setBounds(0, 0, 472, 401);
        this.getContentPane().add(bcg);

        this.getContentPane().repaint();
    }

    private void initLoginJFrame() {
        this.setSize(488, 430);
        this.setAlwaysOnTop(true);// 显示在最上方
        this.setTitle("用户登录");// 设置标题
        this.setResizable(false);// 最大化禁用
        this.setLocationRelativeTo(null);//默认居中
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(null);
    }

    private int containsUser(ArrayList<User> users, String username) {
        for (int i = 0; i < users.size(); i++) {
            if (username.equals(users.get(i).getUsername())) {
                return i;
            }
        }
        return -1;
    }

    private void createDialog(String title, String content, int width, int height) {
        JDialog dialog = new JDialog(this, title, true);
        dialog.setSize(width, height);
        dialog.setLocationRelativeTo(null);
        JLabel text = new JLabel(content, JLabel.CENTER);
        text.setFont(inputFont);
        dialog.add(text, BorderLayout.CENTER);
        dialog.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();
        if (obj == logoIn) {
            String username = usernameInput.getText();
            char[] passwordArr = pwdInput.getPassword();
            StringBuilder password = new StringBuilder();
            for (int i = 0; i < passwordArr.length; i++) {
                password.append(passwordArr[i]);
            }
            String code = codeInput.getText();

            if (correctCode.getText().equalsIgnoreCase(code)) {
                System.out.println("验证码正确");
                int i = containsUser(users, username);
                if (i >= 0) {
                    System.out.println("用户名存在");
                    if (users.get(i).getPassword().equals(password.toString())) {
                        System.out.println("密码正确");
                        this.setVisible(false);
                        createDialog("登录提示", "登陆成功", 200, 200);
                        new GameJFrame();
                    } else {
                        System.out.println("密码错误");
                        createDialog("提示信息", "密码错误", 200, 200);
                    }
                } else {
                    System.out.println("用户名不存在");
                    createDialog("提示信息", "用户不存在", 200, 200);
                }
            } else {
                System.out.println("验证码错误");
                createDialog("提示信息", "验证码错误", 200, 200);
                correctCode.setText(GameUtil.createCode());
            }

        }
    }
}
