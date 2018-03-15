package com.soneso.stellarmnemonics.derivation;

import java.nio.ByteBuffer;

/**
 * Util class.
 * Created by cristi.paval on 3/13/18.
 */
public class ByteUtils {
    public static byte[] concatByteArrays(byte[] a, byte[] b) {
        byte[] c = new byte[a.length + b.length];
        System.arraycopy(a, 0, c, 0, a.length);
        System.arraycopy(b, 0, c, a.length, b.length);
        return c;
    }

    public static String bytesToBinaryString(byte[] bytes) {
        StringBuilder binaryStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            binaryStringBuilder.append(String.format("%8s", Integer.toBinaryString(b & 0xFF)).replace(' ', '0'));
        }
        return binaryStringBuilder.toString();
    }

    public static byte[] byteSubArray(byte[] source, int startIndex, int endIndex) {
        byte[] subArray = new byte[endIndex - startIndex];
        System.arraycopy(source, startIndex, subArray, 0, endIndex - startIndex);
        return subArray;
    }

    public static byte[] last4BytesFromLong(long x) {
        ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
        buffer.putLong(x);
        return byteSubArray(buffer.array(), 4, 8);
    }
}
