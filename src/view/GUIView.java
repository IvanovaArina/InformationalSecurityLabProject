package view;
import view.IView;
import entity.Result;

public class GUIView implements IView{
    @Override
    public String[] getParameters(){
    //TODO getParameters GUI
        return new String[0];
    }
    @Override
    public void printResult(Result result){
        //TODO printResult GUI
        //output in windows frame
    }
}
