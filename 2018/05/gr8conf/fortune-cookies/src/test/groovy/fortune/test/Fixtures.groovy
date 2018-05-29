package fortune.test

import graphql.schema.DataFetchingEnvironment
import org.pac4j.core.profile.UserProfile
import ratpack.handling.Context

/**
 * Utility class to build required auxiliary instances
 *
 * @since 0.1.0
 */
class Fixtures {

    /**
     * Creates an instance of {@link DataFetchingEnvironment}
     *
     * @param context an instance of {@link Context}
     * @param arguments the arguments coming from client
     * @return an instance of {@link DataFetchingEnvironment}
     * @since 0.1.0
     */
    static DataFetchingEnvironment dataFetchingEnvironment(Context context, Map<String, Object> arguments) {
        return new DefaultDataFetchingEnvironment(context: context, arguments: arguments)
    }

    /**
     * Creates a map representing a fortune cookie
     *
     * @param index a identifier to differentiate the cookie
     * @return a {@link Map} representing a fortune cookie
     * @since 0.1.0
     */
    static Map createCookieWithIndex(Integer index) {
        return [
            author: "anonymous",
            text: "cookie-$index"
        ]
    }

    /**
     * Creates a {@link UserProfile} having a specific username
     *
     * @param username the {@link UserProfile} username attribute
     * @return an instance of {@link UserProfile}
     * @since 0.1.0
     */
    static UserProfile userProfileWithUsername(String username) {
        UserProfile userProfile = new UserProfile()

        userProfile.addAttribute('username', username)

        return userProfile
    }
}
