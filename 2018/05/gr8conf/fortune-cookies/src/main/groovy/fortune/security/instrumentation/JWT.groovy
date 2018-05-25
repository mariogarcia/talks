package fortune.security.instrumentation

import java.nio.charset.StandardCharsets
import java.security.MessageDigest

import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.interfaces.DecodedJWT

/**
 * Crytographic functions
 *
 * @since 0.1.0
 */
class JWT {

    static final String ISSUER = 'gql'

    /**
     * Creates a new token using the provided claim and the configured
     * algorithm
     *
     * @param username principal subject
     * @param secret
     * @return a new token
     * @since 0.1.0
     */
    static String createToken(String username, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret)

        return com.auth0.jwt.JWT
                .create()
                .withIssuer(ISSUER)
                .withSubject(username)
                .withIssuedAt(new Date())
                .sign(algorithm)
    }

    /**
     * Checks whether a given token is valid or not
     *
     * @param token the token to be validated
     * @param secret
     * @return an instanceof {@link DecodedJWT}
     * @since 0.1.0
     */
    static DecodedJWT verifyToken(String token, String secret) {
        Algorithm algorithm = Algorithm.HMAC256(secret)

        return com.auth0.jwt.JWT
                .require(algorithm)
                .withIssuer(ISSUER)
                .build()
                .verify(token)
    }

    /**
     * Hashes a given text using SHA-256 as hashing algorithm
     *
     * @param text the text we would like to hash
     * @since 0.1.0
     */
    static String hash(String text) {
        return MessageDigest
                .getInstance("SHA-256")
                .digest(text.getBytes(StandardCharsets.UTF_8))
                .encodeHex()
                .toString()
    }
}

