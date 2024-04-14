package repository;

import services.*;

public enum FunctionCode {
    ENCRYPT_AES(new EncryptAES()),
    ENCRYPT_RSA(new EncryptRSA()),
    DECRYPT_AES(new DecryptAES()),
    DECRYPT_RSA(new DecryptRSA()),
    DIGITAL_SIGNATURE(new DigitalSignature()),
    VERIFY_DIGITAL_SIGNATURE(new VerifyDigitalSignature()),
    UNSUPPORTED_FUNCTION(new UnsupportedFunction());

private Function function;
FunctionCode(Function function) {
    this.function = function;
}
public Function getFunction() { return function;}

}
