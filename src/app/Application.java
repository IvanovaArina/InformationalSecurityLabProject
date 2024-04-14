package app;

import controller.MainController;
import entity.Result;
import repository.FunctionCode;
import services.Function;
import static constants.FunctionCodeConstants.*;

public class Application {
    private final MainController mainController;
    public Application(MainController mainController) {
        this.mainController = mainController;
    }
    public Result run(){
        String[] parameters = mainController.getView().getParameters();
        String mode = parameters[0];
        Function function = getFunction(mode);
        return function.execute(parameters);
    }
    private static Function getFunction(String mode) {
        return switch (mode) {
            case "1" -> FunctionCode.valueOf(ENCRYPT_AES).getFunction();
            case "2" -> FunctionCode.valueOf(ENCRYPT_RSA).getFunction();
            case "3" -> FunctionCode.valueOf(DECRYPT_AES).getFunction();
            case "4" -> FunctionCode.valueOf(DECRYPT_RSA).getFunction();
            case "5" -> FunctionCode.valueOf(DIGITAL_SIGNATURE).getFunction();
            case "6" -> FunctionCode.valueOf(VERIFY_DIGITAL_SIGNATURE).getFunction();
            default ->FunctionCode.valueOf(UNSUPPORTED_FUNCTION).getFunction();
        };
    }
    public void printResult (Result result){
        mainController.getView().printResult(result);
    }
}
