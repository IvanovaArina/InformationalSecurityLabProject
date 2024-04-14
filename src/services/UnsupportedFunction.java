package services;

import entity.Result;
import repository.ResultCode;
import exeption.ApplicationException;

public class UnsupportedFunction implements Function{
        @Override
        public Result execute(String[] parameters){
                return new Result(ResultCode.ERROR, new ApplicationException("UnsupportedFunction"));
        }
}
