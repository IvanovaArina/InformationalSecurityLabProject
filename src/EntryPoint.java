import app.Application;
import controller.MainController;
import entity.Result;
import view.ConsoleView;
import view.IView;

import javax.swing.text.View;

public class EntryPoint {
    public static void main(String[] args) {
        IView view = new ConsoleView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);
        Result result = application.run();
        application.printResult(result);
    }
}