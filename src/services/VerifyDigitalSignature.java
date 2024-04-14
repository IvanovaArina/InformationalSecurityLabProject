package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

public class VerifyDigitalSignature implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            //TODO VerifyDigitalSignature execute method
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("VerifyDigitalSignature operation finish with exception", e));
        }
        return new Result(ResultCode.OK);
    }
}
