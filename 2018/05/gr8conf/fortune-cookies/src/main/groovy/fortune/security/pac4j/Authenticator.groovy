package fortune.security.pac4j

import javax.inject.Inject

import fortune.security.user.UserProfileRepository
import org.pac4j.core.exception.CredentialsException
import org.pac4j.core.profile.CommonProfile
import org.pac4j.core.profile.UserProfile
import org.pac4j.http.credentials.UsernamePasswordCredentials
import org.pac4j.http.credentials.authenticator.UsernamePasswordAuthenticator
import org.pac4j.http.profile.HttpProfile

class Authenticator implements UsernamePasswordAuthenticator {

    @Inject
    UserProfileRepository userProfileRepository

    @Override
    void validate(UsernamePasswordCredentials credentials) {

        if (!credentials.password)
            throwsException('No password provided')

        if (!credentials.username)
            throwsException('No username provided')

        UserProfile userProfile = userProfileRepository
                .findByCredentials(credentials.username, credentials.password)

        if (!userProfile)
            throwsException('No user found')

        final HttpProfile profile = new HttpProfile()
        profile.setId(credentials.username)
        profile.addRoles(userProfile.roles)
        profile.addAttribute(CommonProfile.USERNAME, credentials.username)
        credentials.setUserProfile(profile)
    }

    protected void throwsException(final String message) {
        throw new CredentialsException(message);
    }
}
