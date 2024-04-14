package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static constants.AlgorithmNames.RSA;
import static constants.AlgorithmNames.SHA256RSA;
import static constants.ApplicationExceptionMessages.VERIFY_DIGITAL_SIGNATURE_EXCEPTION_MESSAGE;
import static constants.FilePaths.*;
import static constants.ParameterConstants.WAS_GENERATED;
import static constants.SignatureVerificationConstants.*;

public class VerifyDigitalSignature implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            if (!parameters[1].equals( WAS_GENERATED)) {
                rewriteTextToFile(PRIVATE_KEY_SIGNATURE, parameters[1]);
            }
            String originalText = readTextFromFile(TEXT);
            String encodedSignature = new String (Files.readAllBytes(SIGNATURE.toPath()));
            byte[] decodedSignature = Base64.getDecoder().decode(encodedSignature);
            String encodedPublicKey = new String(Files.readAllBytes(PUBLIC_KEY_SIGNATURE.toPath()));
            PublicKey originalPublicKey = decodePublicKeyFromString(encodedPublicKey);
            // Создаем объект для верификации подписи
            Signature signature = Signature.getInstance(SHA256RSA);
            signature.initVerify(originalPublicKey);

            // Обновляем объект для верификации данными исходного сообщения
            signature.update(originalText.getBytes());

            // Проверяем подпись
            boolean signatureVerified = signature.verify(decodedSignature);
            if (signatureVerified) {
                // Подпись верифицирована успешно
                System.out.println(SUCCEED);
            }

        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(FAIL, e));
        }
        return new Result(ResultCode.OK);
    }
    private PublicKey decodePublicKeyFromString(String keyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }

}
