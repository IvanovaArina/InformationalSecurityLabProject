package entity;

import exeption.ApplicationException;
import repository.ResultCode;

public class Result {
    private ResultCode resultCode;//Ok or ERROR

    //if ERROR to what exactly - application exception
    private ApplicationException applicationException;
    public Result(ResultCode resultCode) {
        this.resultCode = resultCode;
    }
    public Result(ResultCode resultCode, ApplicationException applicationException) {
        this.resultCode = resultCode;
        this.applicationException = applicationException;
    }
    public ResultCode getResultCode() {
        return resultCode;
    }
    public ApplicationException getApplicationException() {
        return applicationException;
    }
}
