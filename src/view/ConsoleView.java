package view;


import entity.Result;
import java.util.Scanner;
import static constants.ApplicationCompletionConstants.*;
import static constants.ParameterConstants.*;

public class ConsoleView implements IView{
    @Override
    public String[] getParameters(){
        String[] parameters = new String[2];
        Scanner scanner = new Scanner(System.in);
        System.out.println(CHOOSE_MODE);
        System.out.println(MODE1);
        System.out.println(MODE2);
        System.out.println(MODE3);
        System.out.println(MODE4);
        System.out.println(MODE5);
        System.out.println(MODE6);
        parameters[0] = scanner.nextLine();

        switch (parameters[0]) {
            case "3":
                System.out.println(GET_PARAMETERS_FOR_MODE3);
                System.out.println(MODE3_1);
                System.out.println(MODE3_2);
                parameters[1] = scanner.nextLine();
                if (!parameters[1].equals(WAS_GENERATED)) {
                    System.out.println(IF_NOT_GENERATED);
                    parameters[1] = scanner.nextLine();
                }
                break;
            case "4":
                System.out.println(GET_PARAMETERS_FOR_MODE4);
                System.out.println(MODE4_1);
                System.out.println(MODE4_2);
                parameters[1] = scanner.nextLine();
                if (parameters[1].equals(WAS_GENERATED)) {
                    System.out.println(IF_NOT_GENERATED);
                    parameters[1] = scanner.nextLine();
                }
                break;
            case "6":
                System.out.println(GET_PARAMETERS_FOR_MODE6);
                System.out.println(MODE6_1);
                System.out.println(MODE6_2);
                parameters[1] = scanner.nextLine();
                if (!parameters[1].equals(WAS_GENERATED)) {
                    System.out.println(IF_NOT_GENERATED);
                    parameters[1] = scanner.nextLine();
                    break;
                }
            default: break;
        }
        return parameters;
    }
    @Override
    public void printResult(Result result){
        switch(result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }

}
