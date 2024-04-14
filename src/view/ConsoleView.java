package view;


import entity.Result;
import static constants.ApplicationCompletionConstants.SUCCESS;

import static constants.ApplicationCompletionConstants.EXCEPTION;

public class ConsoleView implements IView{
    @Override
//    ������ ������� � �������������
//    �������� ������ �������� � ������ ����� MainContrioller-�
    public String[] getParameters(){
        //TODO getParameters Console
        return new String[0];
    }
    @Override
    public void printResult(Result result){
        //TODO printResult Console
        switch(result.getResultCode()) {
            case OK -> System.out.println(SUCCESS);
            case ERROR -> System.out.println(EXCEPTION + result.getApplicationException().getMessage());
        }
    }

}
