package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static constants.FilePaths.*;

public class ViewResponse2 implements IViewResponse {

    JFrame frame;
    private JTextField publicKey;
    private JTextField privateKey;
    private JTextArea encodedText;

    public ViewResponse2() throws IOException {
        frame = new JFrame("RSA Encryption");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Создаем текстовую область
        encodedText = new JTextArea(30, 50);
        encodedText.setEditable(false); // Разрешаем редактирование текста
        // Устанавливаем перенос по словам
        encodedText.setLineWrap(true);
        encodedText.setWrapStyleWord(true);
        encodedText.setText(readTextFromFileToGUI(ENCRYPTED_RSA));
        JScrollPane jScrollPane = new JScrollPane(encodedText);

        // Создаем панель и устанавливаем BoxLayout для вертикального расположения компонентов
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        jPanel.add(new JLabel("Encrypted Text"));
        jPanel.add(jScrollPane);

        publicKey = new JTextField(60);
        publicKey.setText(readTextFromFileToGUI(PUBLIC_KEY_RSA));
        jPanel.add(new JLabel("Public Key"));
        jPanel.add(publicKey);

        privateKey = new JTextField(60);
        privateKey.setText(readTextFromFileToGUI(PRIVATE_KEY_RSA));
        jPanel.add(new JLabel("Private Key"));
        jPanel.add(privateKey);

        frame.add(jPanel);
        // Создаем кнопку для сохранения текста
        frame.setVisible(true);
    }

}
