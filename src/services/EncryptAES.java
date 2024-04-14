package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import static constants.ApplicationExceptionMessages.ENCRYPT_AES_EXCEPTION_MESSAGE;
import static constants.AlgorithmNames.AES;
import static constants.FilePaths.*;
public class EncryptAES implements Function{
    //parameters[0] - что делает
    //parameters[1] - SymmetricKey/random
    @Override
    public Result execute(String[] parameters){
        try {
            String textToEncrypt = readTextFromFile(TEXT);
            SecretKey secretKey = generateKeyAES128();

            // Кодирование ключа в Base64
            String encodedKey = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            rewriteTextToFile(SYMMETRIC_KEY, encodedKey);

            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8));
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            rewriteTextToFile(ENCRYPTED_AES, encryptedText);

        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(ENCRYPT_AES_EXCEPTION_MESSAGE, e));
        }
        return new Result(ResultCode.OK);
    }
    public SecretKey generateKeyAES128() throws NoSuchAlgorithmException {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(128);
            return keyGenerator.generateKey();

    }


}
