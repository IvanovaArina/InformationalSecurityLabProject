package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

public class DigitalSignature implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            //TODO DigitalSignature execute method
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("DigitalSignature operation finish with exception", e));
        }
        return new Result(ResultCode.OK);
    }
}
