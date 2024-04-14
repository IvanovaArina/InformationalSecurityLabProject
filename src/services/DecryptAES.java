package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import static constants.FilePaths.*;

public class DecryptAES implements Function{
    //parameters[0] - что делает
    //parameters[1] - SymmetricKey/random - impossible
    @Override
    public Result execute(String[] parameters){
        try {
            String encryptedText = readTextFromFile(ENCRYPTED_AES);
            String keyString = parameters[1];
            // Декодирование строки ключа из Base64 в массив байтов
            byte[] keyBytes = Base64.getDecoder().decode(keyString);

            // Создание объекта SecretKey из массива байтов
            SecretKey secretKey = new SecretKeySpec(keyBytes, "AES");

            // Инициализация объекта Cipher для расшифровки
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Декодирование текста из Base64 в массив байтов
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Расшифровка текста
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Преобразование массива байтов обратно в исходный текст
            String decryptedText = new String(decryptedBytes, StandardCharsets.UTF_8);
            rewriteTextToFile(DECRYPTED_AES, decryptedText);

        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("DecryptAES operation finish with exception", e));
        }
        return new Result(ResultCode.OK);
    }
}
