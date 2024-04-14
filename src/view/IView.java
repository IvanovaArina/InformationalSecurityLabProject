package view;
import entity.Result;

public interface IView {
    String[] getParameters();
    void printResult(Result result);
}
