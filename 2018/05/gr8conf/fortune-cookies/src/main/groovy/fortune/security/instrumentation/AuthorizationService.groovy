package fortune.security.instrumentation

import javax.inject.Inject

import fortune.security.config.SecurityAwareConfig
import org.pac4j.core.profile.UserProfile

/**
 * Checks current user roles with the ones required for
 * a given path and rejects users don't having the required
 * roles
 *
 * @since 0.1.0
 */
class AuthorizationService {

    /**
     * Mapping configuration has to be declared
     * in the configuration file
     *
     * @since 0.1.0
     */
    @Inject
    SecurityAwareConfig config

    /**
     * Represents the required roles for a given path
     *
     * @since 0.1.0
     */
    static class Mapping {
        String path
        Set<String> roles
    }

    /**
     * Checks whether a user has the required roles
     * or not for a given path
     *
     * @param userProfile user information
     * @param path the path under consideration
     * @return true if the user has the required roles, false otherwise
     * @since 0.1.0
     */
    Boolean isAllowed(UserProfile userProfile, String path) {
        List<Mapping> mappings = config
            .security
            .authorization
            .mappings
            .collect(AuthorizationService.&convertToMapping)

        return resolveMapping(mappings, path)
            .map(AuthorizationService.&checkConstraints.rcurry(userProfile.roles as Set))
            .orElse(false)
    }

    Boolean isSchemaVisible() {
        return config.security.authorization.schema
    }

    Boolean allowPartials() {
        return config.security.authorization.allowPartials
    }

    static Optional<Mapping> resolveMapping(List<Mapping> mappings, String path) {
        return Optional.ofNullable(mappings.find { Mapping mapping ->
            path ==~ mapping.path
        })
    }

    static Boolean isMappingAnonymous(Mapping mapping) {
        return mapping.roles == ['ANONYMOUS'] as Set
    }

    static Boolean hasMappingRoles(Mapping mapping, Set<String> roles) {
        return mapping.roles == roles
    }

    static Boolean checkConstraints(Mapping mapping, Set<String> roles) {
        return isMappingAnonymous(mapping) || hasMappingRoles(mapping, roles)
    }

    /**
     * Converts a given {@link Map.Entry} to a {@link Mapping}
     * instance
     *
     * @param entryPath the mapping path
     * @param entryRoles the mapping roles
     * @return an instance of {@link Mapping}
     * @since 0.1.0
     */
    static Mapping convertToMapping(String entryPath, String entryRoles) {
        Set<String> roles = entryRoles
            .split(",")
            .grep()*.trim() as Set

        return new Mapping(path: "${entryPath}.*", roles: roles)
    }
}
