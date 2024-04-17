import app.Application;
import controller.MainController;
import entity.Result;
import view.*;

import javax.swing.text.View;
import java.io.IOException;

public class EntryPoint {
    public static void main(String[] args) throws IOException {
        IView view = new GUIView();
        MainController mainController = new MainController(view);
        Application application = new Application(mainController);
        Result result = application.run();
        IViewResponse viewResponse2 = new ViewResponse2();
        application.printResult(result);
    }
}