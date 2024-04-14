package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

import static constants.ApplicationExceptionMessages.VERIFY_DIGITAL_SIGNATURE_EXCEPTION_MESSAGE;

public class VerifyDigitalSignature implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            //TODO VerifyDigitalSignature execute method
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(VERIFY_DIGITAL_SIGNATURE_EXCEPTION_MESSAGE, e));
        }
        return new Result(ResultCode.OK);
    }
}
