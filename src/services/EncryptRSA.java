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
import static constants.ApplicationExceptionMessages.ENCRYPT_RSA_EXCEPTION_MESSAGE;
import static constants.FilePaths.*;


public class EncryptRSA implements Function{
    //parameters[0] - mode of work
    //parameters[1] - public key/generate
    @Override
    public Result execute(String[] parameters){
        try {
            String textToEncrypt = readTextFromFile(TEXT);
            KeyPair keyPair = generateKeyPair();
            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            // Кодирование ключа в Base64 для сохранения
            String encodedPublicKey = Base64.getEncoder().encodeToString(publicKey.getEncoded());
            rewriteTextToFile(PUBLIC_KEY, encodedPublicKey);
            // Кодирование ключа в Base64 для сохранения
            String encodedPrivateKey = Base64.getEncoder().encodeToString(privateKey.getEncoded());
            rewriteTextToFile(PRIVATE_KEY, encodedPrivateKey);

            Cipher cipher = Cipher.getInstance(RSA);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            byte[] encryptedBytes = cipher.doFinal(textToEncrypt.getBytes(StandardCharsets.UTF_8));
            String encryptedText = Base64.getEncoder().encodeToString(encryptedBytes);
            rewriteTextToFile(ENCRYPTED_RSA, encryptedText);

        } catch (Exception e) {
            return new Result(ResultCode.ERROR, new ApplicationException(ENCRYPT_RSA_EXCEPTION_MESSAGE, e));
        }
        return new Result(ResultCode.OK);
    }
    private KeyPair generateKeyPair() throws Exception {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
}
