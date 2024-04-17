package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import static constants.FilePaths.TEXT;
import static java.awt.AWTEventMulticaster.add;

public class ViewMode1 implements IViewMode {
    JFrame frame;
    private JTextArea textToEncode;

    private Semaphore semaphore;

    public ViewMode1() {
        semaphore = new Semaphore(0);
        frame = new JFrame("AES Encryption");
        frame.getContentPane().setBackground(Color.GRAY);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Создаем текстовую область
        textToEncode = new JTextArea(30, 50);
        textToEncode.setEditable(true); // Разрешаем редактирование текста
        // Устанавливаем перенос по словам
        textToEncode.setLineWrap(true);
        textToEncode.setWrapStyleWord(true);
        JScrollPane jScrollPane = new JScrollPane(textToEncode);
        JPanel jPanel = new JPanel();
        jPanel.add(jScrollPane);
        jPanel.add((new JLabel("Text to Encode")));
        // Создаем кнопку для сохранения текста
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    rewriteTextToFileFromGUI(TEXT,textToEncode.getText().toString());
                    semaphore.release();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        jPanel.add(saveButton); // Добавляем кнопку на панель

        frame.add(jPanel);
        //waitForSave();


        // Отображение главного окна
        frame.setVisible(true);
    }

    @Override
    public void waitForSave() {
        try {
            semaphore.acquire(); // Ожидаем, пока счетчик семафора не станет больше 0
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
