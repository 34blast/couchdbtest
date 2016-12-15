/**
 * RMSCOTT Prototyping code
 */
package rmscott.util;

/**
 * Encodes a long as a base64 string.
 * 
 * @author Richard M. Scott
 *  
 */
public class Base64Util {
    /** Automatically generated javadoc for: SIX_BITS */
    private static final int SIX_BITS = 0x3F;
    /** Automatically generated javadoc for: CONTENT_LENGTH */
    private static final int CONTENT_LENGTH = 32;
    /**
     * Automatically generated constructor for utility class
     */
    private Base64Util() {
    }

    private static final char[] ENCODESTR = { 'A', 'B', 'C', 'D', 'E', 'F',
        'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
        'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
        'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
        '_', '$' };

//  Mapping table from Base64 characters to 6-bit nibbles.
    private static final byte[] BYTEMAP = new byte[128];
       static {
          for (int i = 0; i < BYTEMAP.length; i++) BYTEMAP[i] = -1;
          for (int i = 0; i < 64; i++) BYTEMAP[ENCODESTR[i]] = (byte)i; }

    /**
     * Encodes a long as a base64 string.
     *
     * @return String
     * @param pLong 
     */
    public static String encode(long pLong) {
        StringBuffer sb = new StringBuffer(CONTENT_LENGTH);
        int val;

        while (pLong != 0) {
            // extract bottom 6 bits from value and convert to a character
            val = (int) (pLong & SIX_BITS);
            sb.append(ENCODESTR[val]);
            pLong >>>= 6;
        }

        return sb.toString();
    }

    /**
    * Encodes a string into Base64 format.
    * No blanks or line breaks are inserted.
    * @param s  a String to be encoded.
    * @return   A String with the Base64 encoded data.
    */
    public static String encodeString (String s) {
       return new String(encode(s.getBytes())); }

    /**
    * Encodes a byte array into Base64 format.
    * No blanks or line breaks are inserted.
    * @param in  an array containing the data bytes to be encoded.
    * @return    A character array with the Base64 encoded data.
    */
    public static char[] encode (byte[] in) {
       return encode(in,in.length); }

    /**
    * Encodes a byte array into Base64 format.
    * No blanks or line breaks are inserted.
    * @param in   an array containing the data bytes to be encoded.
    * @param iLen number of bytes to process in <code>in</code>.
    * @return     A character array with the Base64 encoded data.
    */
    public static char[] encode (byte[] in, int iLen) {
       int oDataLen = (iLen*4+2)/3;       // output length without padding
       int oLen = ((iLen+2)/3)*4;         // output length including padding
       char[] out = new char[oLen];
       int ip = 0;
       int op = 0;
       while (ip < iLen) {
          int i0 = in[ip++] & 0xff;
          int i1 = ip < iLen ? in[ip++] & 0xff : 0;
          int i2 = ip < iLen ? in[ip++] & 0xff : 0;
          int o0 = i0 >>> 2;
          int o1 = ((i0 &   3) << 4) | (i1 >>> 4);
          int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
          int o3 = i2 & 0x3F;
          out[op++] = ENCODESTR[o0];
          out[op++] = ENCODESTR[o1];
          out[op] = op < oDataLen ? ENCODESTR[o2] : '='; op++;
          out[op] = op < oDataLen ? ENCODESTR[o3] : '='; op++; }
       return out; }

    /**
    * Decodes a string from Base64 format.
    * @param s  a Base64 String to be decoded.
    * @return   A String containing the decoded data.
    * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
    */
    public static String decodeString (String s) {
       return new String(decode(s)); }

    /**
    * Decodes a byte array from Base64 format.
    * @param s  a Base64 String to be decoded.
    * @return   An array containing the decoded data bytes.
    * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
    */
    public static byte[] decode (String s) {
       return decode(s.toCharArray()); }

    /**
    * Decodes a byte array from Base64 format.
    * No blanks or line breaks are allowed within the Base64 encoded data.
    * @param in  a character array containing the Base64 encoded data.
    * @return    An array containing the decoded data bytes.
    * @throws    IllegalArgumentException if the input is not valid Base64 encoded data.
    */
    public static byte[] decode (char[] in) {
       int iLen = in.length;
       if (iLen%4 != 0) throw new IllegalArgumentException ("Length of Base64 encoded input string is not a multiple of 4.");
       while (iLen > 0 && in[iLen-1] == '=') iLen--;
       int oLen = (iLen*3) / 4;
       byte[] out = new byte[oLen];
       int ip = 0;
       int op = 0;
       while (ip < iLen) {
          int i0 = in[ip++];
          int i1 = in[ip++];
          int i2 = ip < iLen ? in[ip++] : 'A';
          int i3 = ip < iLen ? in[ip++] : 'A';
          if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
             throw new IllegalArgumentException ("Illegal character in Base64 encoded data.");
          int b0 = BYTEMAP[i0];
          int b1 = BYTEMAP[i1];
          int b2 = BYTEMAP[i2];
          int b3 = BYTEMAP[i3];
          if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
             throw new IllegalArgumentException ("Illegal character in Base64 encoded data.");
          int o0 = ( b0       <<2) | (b1>>>4);
          int o1 = ((b1 & 0xf)<<4) | (b2>>>2);
          int o2 = ((b2 &   3)<<6) |  b3;
          out[op++] = (byte)o0;
          if (op<oLen) out[op++] = (byte)o1;
          if (op<oLen) out[op++] = (byte)o2; }
       return out; }
    
}
