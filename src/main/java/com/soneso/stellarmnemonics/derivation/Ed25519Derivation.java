package com.soneso.stellarmnemonics.derivation;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/**
 * Ed25519Derivation used to derive m/44'/148' key from BIP39 seed.
 * Created by cristi.paval on 3/13/18.
 */
public class Ed25519Derivation {

    private static final String HMAC_SHA512 = "HmacSHA512";

    private byte[] privateKey;
    private byte[] chainCode;

    public static Ed25519Derivation fromSecretSeed(byte[] seed) throws Ed25519DerivationException {
        byte[] output;
        try {
            output = hMacSha512(seed, "ed25519 seed".getBytes("UTF-8"));
        } catch (Exception e) {
            throw new Ed25519DerivationException("Fatal error when trying to get bytes from chain code.");
        }
        byte[] privateKey = ByteUtils.byteSubArray(output, 0, 32);
        byte[] chainCode = ByteUtils.byteSubArray(output, 32, 64);
        return new Ed25519Derivation(privateKey, chainCode);
    }

    private static byte[] hMacSha512(byte[] data, byte[] key) throws Ed25519DerivationException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(key, HMAC_SHA512);
            Mac mac = Mac.getInstance(HMAC_SHA512);
            mac.init(secretKeySpec);
            return mac.doFinal(data);
        } catch (Exception e) {
            throw new Ed25519DerivationException("Fatal error when applying H_MAC SHA-512 on the seed.");
        }
    }

    private Ed25519Derivation(byte[] privateKey, byte[] chainCode) {
        this.privateKey = privateKey;
        this.chainCode = chainCode;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public Ed25519Derivation derived(int index) throws Ed25519DerivationException {
        long edge = 0x80000000L;
        if ((edge & index) != 0) {
            throw new RuntimeException("Invalid index!");
        }

        byte[] data = ByteUtils.concatByteArrays(new byte[]{0}, privateKey);
        long derivingIndex = edge + index;
        data = ByteUtils.concatByteArrays(data, ByteUtils.last4BytesFromLong(derivingIndex));

        byte[] digest = hMacSha512(data, chainCode);
        byte[] factor = ByteUtils.byteSubArray(digest, 0, 32);
        return new Ed25519Derivation(factor, ByteUtils.byteSubArray(digest, 32, 64));
    }
}
