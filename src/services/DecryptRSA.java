package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

public class DecryptRSA implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            //TODO DecryptRSA execute method
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("DecryptRSA operation finish with exception", e));
        }
        return new Result(ResultCode.OK);
    }
}
