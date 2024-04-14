package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;
import javax.crypto.Cipher;
import java.io.File;
import java.nio.file.Files;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import static constants.AlgorithmNames.RSA;
import static constants.ApplicationExceptionMessages.DECRYPT_RSA_EXCEPTION_MESSAGE;
import static constants.FilePaths.*;
import static constants.ParameterConstants.WAS_GENERATED;

public class DecryptRSA implements Function{
    @Override
    public Result execute(String[] parameters){
        try {
            if(!parameters[1].equals( WAS_GENERATED)) {
                rewriteTextToFile(PRIVATE_KEY_RSA, parameters[1]);
            }
            String encryptedText = new String(Files.readAllBytes(ENCRYPTED_RSA.toPath()));
            String encodedPrivateKey = new String(Files.readAllBytes(PRIVATE_KEY_RSA.toPath()));

            PrivateKey originalPrivateKey = decodePrivateKeyFromString(encodedPrivateKey);
            // Декодируем текст из Base64 в байты
            byte[] encryptedTextBytes = Base64.getDecoder().decode(encryptedText);
            //Расшифровываем текст
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.DECRYPT_MODE, originalPrivateKey);
            byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
           //Создаем расшифрованную строку из байтов
            String decryptedText = new String(decryptedTextBytes);

            rewriteTextToFile(DECRYPTED_RSA, decryptedText);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(DECRYPT_RSA_EXCEPTION_MESSAGE, e));
        }
        return new Result(ResultCode.OK);
    }
    private PrivateKey decodePrivateKeyFromString(String keyString) throws Exception {
        byte[] keyBytes = Base64.getDecoder().decode(keyString);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
        return keyFactory.generatePrivate(keySpec);
    }

}
