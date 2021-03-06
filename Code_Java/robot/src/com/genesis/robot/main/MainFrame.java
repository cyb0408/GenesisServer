package com.genesis.robot.main;

import com.genesis.common.currency.Currency;
import com.genesis.common.human.HumanPropId;
import com.genesis.protobuf.HumanMessage.GCHumanDetailInfo;
import com.genesis.robot.currency.CurrencyManager;
import com.genesis.robot.font.FontFactory;
import com.genesis.robot.gm.GMFrame;
import com.genesis.robot.item.view.InventoryFrame;
import com.genesis.robot.robot.Robot;
import com.genesis.robot.ui.UiUtil;
import com.genesis.robot.human.HumanPropManager;
import com.genesis.robot.ui.PanelFactory;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * 主窗口
 * @author Joey
 *
 */
public class MainFrame extends JFrame {

    /***/
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private com.genesis.robot.robot.Robot robot;

    /**金币*/
    private JLabel goldLabel;
    /**钻石*/
    private JLabel diamondLabel;
    /**体力*/
    private JLabel tokenLabel;

    /**
     * Create the frame.
     * @param msg
     * @param tempRobot
     */
    public MainFrame(com.genesis.robot.robot.Robot tempRobot, GCHumanDetailInfo msg) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 960, 640);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        Font f = FontFactory.getfSongPlain14();

        JLabel lvTitleLabel = new JLabel("Lv");
        lvTitleLabel.setBackground(Color.ORANGE);
        lvTitleLabel.setFont(f);
        lvTitleLabel.setBounds(10, 10, 15, 15);
        contentPane.add(lvTitleLabel);

        this.robot = tempRobot;
        this.robot.initHuman(msg);

        this.setTitle(msg.getName());

        HumanPropManager humanPropManager = this.robot.getHuman().getHumanPropManager();
        Long lv = humanPropManager.get(HumanPropId.LEVEL);
        JLabel lvLabel = new JLabel(lv.toString());
        lvLabel.setFont(f);
        lvLabel.setBounds(30, 10, 54, 15);
        contentPane.add(lvLabel);

        final int yOffset = 50;

        JButton btnNewButton = new JButton("角色");
        btnNewButton.setFont(new Font("宋体", Font.BOLD, 20));
        btnNewButton.setBounds(834, 65 + yOffset, 100, 50);
        contentPane.add(btnNewButton);

        JButton button = new JButton("背包");
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                InventoryFrame frame =
                        PanelFactory.getInventoryFrame(robot.getHuman().getInventory());
                UiUtil.switchShowOrHide(frame);
            }
        });
        button.setFont(new Font("宋体", Font.BOLD, 20));
        button.setBounds(834, 150 + yOffset, 100, 50);
        contentPane.add(button);

        JButton button_1 = new JButton("任务");
        button_1.setFont(new Font("宋体", Font.BOLD, 20));
        button_1.setBounds(834, 235 + yOffset, 100, 50);
        contentPane.add(button_1);

        JButton button_2 = new JButton("每日活动");
        button_2.setFont(new Font("宋体", Font.BOLD, 14));
        button_2.setBounds(834, 320 + yOffset, 100, 50);
        contentPane.add(button_2);


        //金币
        CurrencyManager currencyManager = this.robot.getHuman().getCurrencyManager();
        final Long gold = currencyManager.get(Currency.GOLD);

        JButton btnNewButton_1 = new JButton("+");
        btnNewButton_1.setFont(new Font("幼圆", Font.BOLD, 13));
        btnNewButton_1.setBounds(205, 5, 42, 24);
        contentPane.add(btnNewButton_1);

        goldLabel = new JLabel(gold.toString());
        goldLabel.setFont(f);
        goldLabel.setBounds(94, 10, 80, 15);
        goldLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        contentPane.add(goldLabel);

        JLabel goldTitleLabel = new JLabel("金币");
        goldTitleLabel.setFont(f);
        goldTitleLabel.setBounds(175, 10, 33, 15);
        contentPane.add(goldTitleLabel);

        //钻石
        final Long diamond = currencyManager.get(Currency.DIAMOND);

        JButton button_3 = new JButton("+");
        button_3.setFont(new Font("幼圆", Font.BOLD, 13));
        button_3.setBounds(439, 5, 42, 24);
        contentPane.add(button_3);

        diamondLabel = new JLabel(diamond.toString());
        diamondLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        diamondLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        diamondLabel.setBounds(328, 10, 80, 15);
        contentPane.add(diamondLabel);

        JLabel label_1 = new JLabel("钻石");
        label_1.setFont(new Font("宋体", Font.PLAIN, 14));
        label_1.setBounds(409, 10, 33, 15);
        contentPane.add(label_1);

        //体力
        Long token = humanPropManager.get(HumanPropId.ENERGY);

        tokenLabel = new JLabel(token.toString());
        tokenLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        tokenLabel.setFont(new Font("宋体", Font.PLAIN, 14));
        tokenLabel.setBounds(564, 10, 80, 15);
        contentPane.add(tokenLabel);

        JLabel label_3 = new JLabel("体力 | ");
        label_3.setFont(new Font("宋体", Font.PLAIN, 14));
        label_3.setBounds(645, 10, 55, 15);
        contentPane.add(label_3);

        JTextField textField = new JTextField("1");
        textField.setBounds(695, 5, 25, 24);
        contentPane.add(textField);
        textField.setColumns(10);

        JButton button_4 = new JButton("+");
        button_4.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String energy = ((JTextField) contentPane.getComponent(14)).getText();
                GMFrame frame = PanelFactory.getGMFrame(robot.getHuman());
                frame.runCmd("addenergy", energy);
                frame.setVisible(true);
            }
        });
        button_4.setFont(new Font("幼圆", Font.BOLD, 13));
        button_4.setBounds(722, 5, 42, 24);
        contentPane.add(button_4);

        //属性
        JButton btnHumanAttr = new JButton("属性");
        btnHumanAttr.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                GMFrame frame = PanelFactory.getGMFrame(robot.getHuman());
                frame.runCmd("gethumanattr");
                frame.setVisible(true);
            }
        });

        btnHumanAttr.setBounds(876, 5, 60, 25);
        contentPane.add(btnHumanAttr);


        //GM命令
        JButton btnGm = new JButton("GM命令");
        btnGm.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //GM命令界面
                GMFrame frame = PanelFactory.getGMFrame(robot.getHuman());
                UiUtil.switchShowOrHide(frame);
            }
        });
        btnGm.setFont(new Font("宋体", Font.BOLD, 20));
        btnGm.setBounds(10, 542, 100, 50);
        contentPane.add(btnGm);

    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    com.genesis.robot.robot.Robot robotTemp = new Robot(null);
                    GCHumanDetailInfo msg =
                            GCHumanDetailInfo.newBuilder().setChannel("").setAccountId("123")
                                    .setId(1L).setName("joey").build();
                    MainFrame frame = new MainFrame(robotTemp, msg);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
