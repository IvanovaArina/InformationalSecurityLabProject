package view;

import entity.Result;

import javax.swing.*;
import java.awt.*;

import static constants.ParameterConstants.*;


public class GUIView implements IView {
    //private JFrame frame;
    private TextArea inputArea;
    private IViewMode viewMode; // Поле для хранения экземпляра ViewMode1
    private ViewResponse1 viewResponse1;

    public GUIView() {
        // Создание главного окна
        //frame = new JFrame("Application");
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setSize(40, 30);
        // Максимизация окна на весь экран
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // Отображение главного окна

        //frame.setVisible(true);
    }

    @Override
    public String[] getParameters() {
        String[] parameters = new String[2];
        // Диалоговое окно для выбора режима
        String mode = JOptionPane.showInputDialog(null, "Choose Mode", "Mode Selection", JOptionPane.PLAIN_MESSAGE,
                null, new String[]{MODE1, MODE2, MODE3, MODE4, MODE5, MODE6}, "Mode1").toString();
        parameters[0] = switch(mode){
            case MODE1 -> "1";
            case MODE2 -> "2";
            case MODE3 -> "3";
            case MODE4 -> "4";
            case MODE5 -> "5";
            case MODE6 -> "6";
            default -> "1"; // По умолчанию устанавливаем MODE1

        };
        if (parameters[0].equals("1")) {
            viewMode = new ViewMode1();
            viewMode.waitForSave();
        }
        else
            if(parameters[0].equals("2")) {
                viewMode = new ViewMode2();
                viewMode.waitForSave();
            }
        return parameters;
    }
        @Override
        public void printResult (Result result){
            // Отображаем результат в текстовом поле
            inputArea.setText(result.toString());
        }

    }
