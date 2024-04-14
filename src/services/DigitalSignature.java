package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;
import static constants.AlgorithmNames.SHA256RSA;
import static constants.FilePaths.*;
import static services.EncryptRSA.*;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.util.Base64;

import static constants.ApplicationExceptionMessages.DIGITAL_SIGNATURE_EXCEPTION_MESSAGE;

public class DigitalSignature implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            String textToSign = readTextFromFile(TEXT);
            KeyPair keyPair;
            keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Кодирование ключа в Base64 для сохранения
            String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            rewriteTextToFile(PUBLIC_KEY_SIGNATURE, encodedPublicKey);
            // Кодирование ключа в Base64 для сохранения
            String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            rewriteTextToFile(PRIVATE_KEY_SIGNATURE, encodedPrivateKey);
            //Создание объекта для подписи
            Signature signature = Signature.getInstance(SHA256RSA);
            signature.initSign(privateKey);
            //Обновление объекта - вдруг файл слишком большой, он тогда загружается частями
            signature.update(textToSign.getBytes());
            // Генерация электронной подписи
            byte[] digitalSignature = signature.sign();

            String encodedSignature = Base64.getEncoder().encodeToString(digitalSignature);
            rewriteTextToFile(SIGNATURE, encodedSignature);


        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(DIGITAL_SIGNATURE_EXCEPTION_MESSAGE, e));
        }
        return new Result(ResultCode.OK);
    }
}
