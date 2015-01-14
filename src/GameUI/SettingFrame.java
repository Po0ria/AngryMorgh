package GameUI;

import GameUI.GameWindow.GameFrame;
import GameUI.GameWindow.GamePanel;
import com.sun.javafx.geom.Vec4f;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: farzadshbfn
 * Date: 2014-12-09
 * Time: 12:22 AM
 * To change this template use File | Settings | File Templates.
 */
public class SettingFrame extends JFrame implements ActionListener {
    private JTextField widthTextField, heightTextField;
    private JLabel widthLabel, heightLabel;

    JButton okButton;

    public SettingFrame() {
        super("Settings");
        widthLabel  = new JLabel("Width :");
        heightLabel = new JLabel("Height :");

        widthTextField  = new JTextField("" + GamePanel.defaultWidth);
        heightTextField = new JTextField("" + GamePanel.defaultHeight);

        Container cp = getContentPane();
        cp.setLayout(new BorderLayout());
        JPanel textPanel = new JPanel(new GridLayout(2, 2));
        textPanel.add(widthLabel);
        textPanel.add(widthTextField);
        textPanel.add(heightLabel);
        textPanel.add(heightTextField);

        cp.add(textPanel);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 1));
        okButton = new JButton("Ok");
        buttonsPanel.add(okButton);

        cp.add(buttonsPanel, BorderLayout.EAST);

        this.setSize(new Dimension(200, 100));
        this.setResizable(false);

        okButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == okButton) {
            GameFrame.width = Integer.parseInt(widthTextField.getText());
            GameFrame.height = Integer.parseInt(heightTextField.getText());

            GameFrame gameMainWindow = GameFrame.getInstance();
            gameMainWindow.setVisible(true);
            this.dispose();
        }
    }

    public static void main(String[] args) {
        SettingFrame settingsWindow = new SettingFrame();
        settingsWindow.setVisible(true);
    }
}
