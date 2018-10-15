package madridgug.delegate

import java.security.MessageDigest

class MD5 {
    String hash(String word) {
        return  MessageDigest
            .getInstance("MD5")
            .digest(s.bytes)
            .encodeHex()
            .toString()
    }
}
