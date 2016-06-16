package genesis;

import java.util.List;

/**
 * Service providing default configuration values for
 * our application
 */
public interface ConfigurationService {

    /**
     * Returns valid types for our app
     *
     * @return the valid types
     */
    public List<String> getValidTypes();
}
