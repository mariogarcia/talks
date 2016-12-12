package compile.gro.local.ms

import groovy.json.JsonSlurper
import groovy.json.JsonOutput

/**
 * Utilities to be able to convert from/to JSON
 *
 * @since 0.1.0
 */
trait Json {

    /**
     * Converts a given {@link Map} to a JSON {@link String}
     *
     * @param map a {@link Map} instance
     * @return a JSON representation of the given map
     * @since 0.1.0
     */
    static String toJson(Map map) {
        return JsonOutput.toJson(map)
    }

    /**
     * Converts a string JSON representation to a given {@link Map}
     *
     * @param json a JSON string
     * @return an instance of {@link Map}
     * @since 0.1.0
     */
    static Map parseText(String json) {
        return new JsonSlurper().parseText(json)
    }
}
