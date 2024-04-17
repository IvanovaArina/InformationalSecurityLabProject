package view;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static constants.FilePaths.ENCRYPTED_AES;
import static constants.FilePaths.SYMMETRIC_KEY;

public class ViewResponse1 implements IViewResponse {
    JFrame frame;
    private JTextField symmetricKey;
    private JTextArea encodedText;

    public ViewResponse1() throws IOException {
        frame = new JFrame("AES Encryption");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Создаем текстовую область
        encodedText = new JTextArea(30, 50);
        encodedText.setEditable(false); // Разрешаем редактирование текста
        // Устанавливаем перенос по словам
        encodedText.setLineWrap(true);
        encodedText.setWrapStyleWord(true);
        encodedText.setText(readTextFromFileToGUI(ENCRYPTED_AES));
        JScrollPane jScrollPane = new JScrollPane(encodedText);
        JPanel jPanel = new JPanel();
        jPanel.add(jScrollPane);
        jPanel.add((new JLabel("Encrypted Text")));

        symmetricKey = new JTextField(30);
        symmetricKey.setText(readTextFromFileToGUI(SYMMETRIC_KEY));
        jPanel.add(symmetricKey);
        jPanel.add(new JLabel("Symmetric Key"));
        frame.add(jPanel);
        // Создаем кнопку для сохранения текста
        frame.setVisible(true);
    }

}
