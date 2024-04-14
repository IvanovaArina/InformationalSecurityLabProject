package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

import static constants.AlgorithmNames.AES;
import static constants.ApplicationExceptionMessages.DECRYPT_AES_EXCEPTION_MESSAGE;
import static constants.FilePaths.*;
import static constants.ParameterConstants.WAS_GENERATED;

public class DecryptAES implements Function{
    //parameters[0] - в каком моде работаем
    //parameters[1] - SymmetricKey/random - impossible
    @Override
    public Result execute(String[] parameters){
        try {
            if(parameters[1] != WAS_GENERATED) {
                rewriteTextToFile(SYMMETRIC_KEY, parameters[1]);
            }
            String encryptedText = new String(Files.readAllBytes(ENCRYPTED_AES.toPath()));
            String encodedKey = new String(Files.readAllBytes(SYMMETRIC_KEY.toPath()));

            //Декодируем ключ из Base64 в байты
            byte[] decodedKey = Base64.getDecoder().decode(encodedKey);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, AES);

            // Декодируем текст из Base64 в байты
            byte[] encryptedTextBytes = Base64.getDecoder().decode(encryptedText);

            // Расшифровываем текст
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.DECRYPT_MODE, originalKey);
            byte[] decryptedTextBytes = cipher.doFinal(encryptedTextBytes);
            // Создаем расшифрованную строку из байтов
            String decryptedText = new String(decryptedTextBytes);

            rewriteTextToFile(DECRYPTED_AES, decryptedText);

        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(DECRYPT_AES_EXCEPTION_MESSAGE, e));
        }
        return new Result(ResultCode.OK);
    }
}
