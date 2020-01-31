package org.jahia.userregistration.utils;

import org.jahia.userregistration.ConfirmRegistration;
import org.slf4j.Logger;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/***
 * Encryption and Decryption of String data; PBE(Password Based Encryption and Decryption)
 * @author Vikram
 */
public class CryptoUtil
{
    private transient static Logger logger = org.slf4j.LoggerFactory.getLogger(CryptoUtil.class);
    private static final String secretKey = "c0nT3a8fw7kp0";
    private static final String initialVector = "0123456789123456";

    private static String md5(final String input) throws NoSuchAlgorithmException {
        final MessageDigest md = MessageDigest.getInstance("MD5");
        final byte[] messageDigest = md.digest(input.getBytes());
        final BigInteger number = new BigInteger(1, messageDigest);
        return String.format("%032x", number);
    }

    private Cipher initCipher(final int mode, final String initialVectorString, final String secretKey)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        final SecretKeySpec skeySpec = new SecretKeySpec(md5(secretKey).getBytes(), "AES");
        final IvParameterSpec initialVector = new IvParameterSpec(initialVectorString.getBytes());
        final Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(mode, skeySpec, initialVector);
        return cipher;
    }

    public String encrypt(String dataToEncrypt) {
        String encryptedData = null;
        try {
            // Initialize the cipher
            final Cipher cipher = initCipher(Cipher.ENCRYPT_MODE, initialVector, secretKey);
            // Encrypt the data
            final byte[] encryptedByteArray = cipher.doFinal(dataToEncrypt.getBytes());
            // Encode using Base64
            encryptedData = (new BASE64Encoder()).encode(encryptedByteArray);
        } catch (Exception e) {
            logger.info("ALPENITE CryptoUtil - encrypt: Problem encrypting the data - " + e.getMessage());
            e.printStackTrace();
        }
        return encryptedData;
    }

    public String decrypt(String encryptedData) {
        String decryptedData = null;
        try {
            // Initialize the cipher
            final Cipher cipher = initCipher(Cipher.DECRYPT_MODE, initialVector, secretKey);
            // Decode using Base64
            final byte[] encryptedByteArray = (new BASE64Decoder()).decodeBuffer(encryptedData);
            // Decrypt the data
            final byte[] decryptedByteArray = cipher.doFinal(encryptedByteArray);
            decryptedData = new String(decryptedByteArray, "UTF8");
        } catch (Exception e) {
            logger.info("ALPENITE CryptoUtil - decrypt: Problem encrypting the data - " + e.getMessage());
            e.printStackTrace();
        }
        return decryptedData;
    }
}