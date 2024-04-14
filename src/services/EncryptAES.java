package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import static constants.ParameterConstants.RANDOM;
import static constants.AlgorithmNames.AES;
import static constants.FilePaths.*;
public class EncryptAES implements Function{
    //parameters[0] - что делает
    //parameters[1] - SymmetricKey/random
    @Override
    public Result execute(String[] parameters){
        try {
            String textToEncrypt = readTextFromFile(TEXT);
            String keyString = null;
            if (parameters[1] == RANDOM) {
                generateKeyAES128();
                keyString = readTextFromFile(SYMMETRIC_KEY);
            }
            else {
                keyString = parameters[1];
            }
            // Декодирование строки ключа из Base64 в массив байтов
            byte[] keyBytes = Base64.getDecoder().decode(keyString);
            // Создание объекта SecretKey из массива байтов
            SecretKey secretKey = new SecretKeySpec(keyBytes, AES);
            Cipher cipher = Cipher.getInstance(AES);
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            byte[] encryptedBytes = cipher.doFinal(textToEncrypt.getBytes());
            // Запись зашифрованного текста в файл
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("EncryptAES operation finish with exception", e));
        }
        return new Result(ResultCode.OK);
    }
    public void generateKeyAES128() {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
            keyGenerator.init(128);
            SecretKey secretKey = keyGenerator.generateKey();
            // Преобразуем ключ в массив байтов
            byte[] keyBytes = secretKey.getEncoded();
            // Кодируем ключ в строку с использованием Base64
            String keyString = Base64.getEncoder().encodeToString(keyBytes);
            rewriteTextToFile(SYMMETRIC_KEY, keyString);
        }
        catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
