package services;

import entity.Result;
import repository.ResultCode;
import exeption.ApplicationException;

import static constants.ApplicationExceptionMessages.UNSUPPORTED_FUNCTION_EXCEPTION_MESSAGE;

public class UnsupportedFunction implements Function{
        @Override
        public Result execute(String[] parameters){
                return new Result(ResultCode.ERROR, new ApplicationException(UNSUPPORTED_FUNCTION_EXCEPTION_MESSAGE));
        }
}
