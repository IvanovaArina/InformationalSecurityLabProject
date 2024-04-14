package services;

import entity.Result;
import exeption.ApplicationException;
import repository.ResultCode;

import javax.crypto.Cipher;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import static constants.AlgorithmNames.RSA;
import static constants.FilePaths.*;
import static constants.ParameterConstants.GENERATE;

public class EncryptRSA implements Function{
    //parameters[0] - mode of work
    //parameters[1] - public key/generate
    //parameters[2] - private key
    @Override
    public Result execute(String[] parameters){
        try {

            if (parameters[1].equalsIgnoreCase(GENERATE)) {
                KeyPair keyPair = generateKeyPair();
                saveKeyToFile(keyPair.getPublic(), PUBLIC_KEY);
                saveKeyToFile(keyPair.getPrivate(), PRIVATE_KEY);
            }
            else {
                rewriteTextToFile(PUBLIC_KEY, parameters[1]);
                rewriteTextToFile(PRIVATE_KEY, parameters[2]);
            }
            PublicKey publicKey = readPublicKeyFromFile(PUBLIC_KEY);
            String textToEncrypt = readTextFromFile(TEXT);
            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8));
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            rewriteTextToFile(ENCRYPTED_RSA, encryptedText);

        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException("EncryptRSA operation finish with exception", e));
        }
        return new Result(ResultCode.OK);
    }
    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }

    private void saveKeyToFile(Key key, File file) throws Exception {
        byte[] keyBytes = key.getEncoded();
        String keyString = Base64.getEncoder().encodeToString(keyBytes);
        rewriteTextToFile(file, keyString);
    }
    private PublicKey readPublicKeyFromFile(File file) throws Exception {
        // Чтение строки с ключом из файла
        String keyString = readTextFromFile(file);

        // Декодирование строки ключа из Base64 в массив байтов
        byte[] keyBytes = Base64.getDecoder().decode(keyString);

        // Создание объекта PublicKey из массива байтов
        KeyFactory keyFactory = KeyFactory.getInstance(RSA);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        return keyFactory.generatePublic(keySpec);
    }
}
