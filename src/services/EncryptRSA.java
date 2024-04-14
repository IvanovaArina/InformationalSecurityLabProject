package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

public class EncryptRSA implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            //TODO EncryptRSA execute method
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("EncryptRSA operation finish with exception", e));
        }
        return new Result(ResultCode.OK);
    }
}
