/**
 * RMSCOTT Prototyping code
 */

package rmscott.util;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.security.SecureRandom;

/**
 * The purpose of this class is to provide a central place for generating unique
 * keys that can be used for object ids (primary keys). The algorithm should be
 * improved in the future to not be limited to the millisecond resolution.
 * 
 * @author Richard M. Scott
 */
public class KeyGenerator {
    /** Automatically generated javadoc for: LOWER_8_BITS_MASK */
    private static final int LOWER_8_BITS_MASK = 0xFF;

    private static final int LOWER_24_BITS_MASK = 0x00FFFFFF;

    private static final int NUMBER_BITS = 24;

    private static KeyGenerator generator = new KeyGenerator();

    private long address = 0;

    private SecureRandom random = null;

    private int lastRandom = 0;

    /**
     * The purpose of the KeyGenerator method is to provide a default private
     * constructor for this class.
     */
    private KeyGenerator() {
        try {
            InetAddress addr = InetAddress.getLocalHost();
            byte[] bytes = addr.getAddress();
            address = getInt(bytes);
            address = address << NUMBER_BITS; // shift over 24 bits
            random = new SecureRandom();
            lastRandom = random.nextInt() & LOWER_24_BITS_MASK;
            // clear out top 8 bits
        } catch (UnknownHostException uhex) {
        	System.err.println("ERROR generatoring key, exception below");
        	System.err.println(uhex);
        }
    }

    /**
     * Static access method.
     * 
     * @return String containing the new unique key
     */
    public static String generateKey() {
        return generator.generate();
    }

    /**
     * Static access method.
     * 
     * @return String containing the new unique key with a concatenated String
     * @param pStr
     */
    public static String generateKey(String pStr) {
        return generator.generate(pStr);
    }

    /**
     * Generate a new key value.
     * 
     * @concurrency $none
     * @post $result != null
     * @return String containing the key
     */
    public synchronized String generate() {
        StringBuffer sb = new StringBuffer();
        long addressAndRandom = 0;
        int newRandom = 0;
        long time = System.currentTimeMillis();

        // put encoded time into the key
        sb.append(Base64Util.encode(time));

        newRandom = random.nextInt() & LOWER_24_BITS_MASK;
        // clear out top 8 bits
        if (newRandom == lastRandom) {
            newRandom = random.nextInt() & LOWER_24_BITS_MASK;
            // clear out top 8 bits
        }
        lastRandom = newRandom;

        addressAndRandom = address | newRandom; // merge address and random

        // put the encoded address and random number into the key
        sb.append(Base64Util.encode(addressAndRandom));
        return sb.toString();
    }

    /**
     * Generate a new key value with a string attached.
     * 
     * @concurrency $none
     * @post $result != null
     * @return String containing the key
     * @param pKey
     */
    public synchronized String generate(String pKey) {
        StringBuffer sb = new StringBuffer();
        long addressAndRandom = 0;
        int newRandom = 0;
        long time = System.currentTimeMillis();

        sb.append(pKey);
        // put encoded time into the key
        sb.append(Base64Util.encode(time));

        newRandom = random.nextInt() & LOWER_24_BITS_MASK;
        // clear out top 8 bits
        if (newRandom == lastRandom) {
            newRandom = random.nextInt() & LOWER_24_BITS_MASK;
            // clear out top 8 bits
        }
        lastRandom = newRandom;

        addressAndRandom = address | newRandom; // merge address and random

        // put the encoded address and random number into the key
        sb.append(Base64Util.encode(addressAndRandom));
        return sb.toString();
    }

    private int getInt(byte[] pBytes) {
        int curr = 0;
        int max = NUMBER_BITS;
        for (int k = 0; max >= 0; k++) {
            int l = pBytes[k] & LOWER_8_BITS_MASK;
            curr = curr + l << max;
            max = max - 8;
        }

        return curr;
    }

}
