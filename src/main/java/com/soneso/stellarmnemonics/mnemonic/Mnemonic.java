package com.soneso.stellarmnemonics.mnemonic;

import com.soneso.stellarmnemonics.derivation.ByteUtils;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.text.Normalizer;
import java.util.List;

/**
 * Mnemonic.
 * Created by cristi.paval on 3/13/18.
 */

public class Mnemonic {

    public static String create(Strength strength, WordList wordList) throws MnemonicException {
        int byteCount = strength.getRawValue() / 8;
        byte[] bytes = SecureRandom.getSeed(byteCount);
        return create(bytes, wordList);
    }

    private static String create(byte[] entropy, WordList wordList) throws MnemonicException {

        byte[] hashBits;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            hashBits = digest.digest(entropy);
        } catch (Exception e) {
            throw new MnemonicException("Fatal error! SHA-256 algorithm does not exist!");
        }

        String checkSum = ByteUtils.bytesToBinaryString(hashBits).substring(0, entropy.length * 8 / 32);
        String entropyBits = ByteUtils.bytesToBinaryString(entropy);
        String concatenatedBits = String.format("%s%s", entropyBits, checkSum);

        List<String> words = wordList.getWords();
        StringBuilder mnemonicBuilder = new StringBuilder();
        for (int index = 0; index < concatenatedBits.length() / 11; ++index) {
            int startIndex = index * 11;
            int endIndex = startIndex + 11;
            int wordIndex = Integer.parseInt(concatenatedBits.substring(startIndex, endIndex), 2);
            mnemonicBuilder.append(words.get(wordIndex)).append(' ');
        }
        mnemonicBuilder.deleteCharAt(mnemonicBuilder.length() - 1);

        return mnemonicBuilder.toString();
    }

    public static byte[] createSeed(String mnemonic, String passphrase) throws MnemonicException {

        char[] password;
        try {
            password = Normalizer.normalize(mnemonic, Normalizer.Form.NFKD).toCharArray();
        } catch (Exception e) {
            throw new MnemonicException("Fatal error at mnemonic normalization!");
        }

        byte[] salt;
        if (passphrase == null) {
            passphrase = "";
        }
        try {
            salt = Normalizer.normalize(new StringBuilder("mnemonic").append(passphrase), Normalizer.Form.NFKD).getBytes("UTF-8");
        } catch (Exception e) {
            throw new MnemonicException("Fatal error at passphrase normalization!");
        }

        try {
            KeySpec ks = new PBEKeySpec(password, salt, 2048, 512);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA512");
            return skf.generateSecret(ks).getEncoded();
        } catch (Exception e) {
            throw new MnemonicException("Fatal error when generating seed from mnemonic!");
        }
    }
}
