package runtime

import java.security.MessageDigest

class StringUtils {

    static String encodeAsMD5(String s){
        return MessageDigest
          .getInstance("MD5")
          .digest(s.bytes)
          .encodeHex()
          .toString()
    }

    static String encodeAsSHA1(String s){
        return MessageDigest
          .getInstance("SHA1")
          .digest(s.bytes)
          .encodeHex()
          .toString()
    }
}
