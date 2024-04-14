package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

import static constants.ApplicationExceptionMessages.DIGITAL_SIGNATURE_EXCEPTION_MESSAGE;

public class DigitalSignature implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            //TODO DigitalSignature execute method
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(DIGITAL_SIGNATURE_EXCEPTION_MESSAGE, e));
        }
        return new Result(ResultCode.OK);
    }
}
